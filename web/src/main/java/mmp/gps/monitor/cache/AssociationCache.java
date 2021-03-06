package mmp.gps.monitor.cache;

import mmp.gps.domain.monitor.MonitorTarget;
import mmp.gps.domain.monitor.UserMonitorTarget;
import mmp.gps.monitor.service.AssociationsService;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 设备与用户关联缓存
 */
@Component
public class AssociationCache {
    /**
     * 设备关联用户
     * <p>
     * 支持一个设备对应多个用户<br>
     * key:设备号<br>
     * value:用户id列表
     * </p>
     */
    private static final Map<String, Set<String>> deviceAssociatedUsers = new ConcurrentHashMap<>();

    // EXO me???
    /**
     * 用户关联设备
     * <p>
     * 支持一个设备对应多个用户<br>
     * key:设备号<br>
     * value:用户id列表
     * </p>
     */
    private static final Map<String, UserMonitorTarget> userAssociatedDevices = new ConcurrentHashMap<>();
    private static AssociationsService associationsService;

    // @Autowired的注入是构造器完成之后
    @Autowired(required = true)
    public AssociationCache(@Qualifier("associationsService") AssociationsService associationsService) {
        AssociationCache.associationsService = associationsService;
    }

    /**
     * 获取用户监控对象
     */
    public static UserMonitorTarget getUserMonitorTarget(String userId) {
        UserMonitorTarget monitor = userAssociatedDevices.get(userId);
        if (monitor == null) {
            monitor = reloadUserMonitorTarget(userId, false);
        }
        if (!monitor.isValid()) {
            monitor = refreshUserMonitorTarget(userId);
        }

        return monitor;
    }

    /**
     * 刷新用户监控对象
     */
    public static UserMonitorTarget refreshUserMonitorTarget(String userId) {
        return reloadUserMonitorTarget(userId, true);
    }

    /**
     * 设置用户监控对象无效
     */
    public static void setUserMonitorTargetInvalid(String userId) {
        UserMonitorTarget monitor = userAssociatedDevices.get(userId);
        if (monitor != null) {
            monitor.setValid(false);
        }
    }

    /**
     * 重新载入用户监控对象
     */
    private synchronized static UserMonitorTarget reloadUserMonitorTarget(String userId, boolean force) {
        UserMonitorTarget monitor = userAssociatedDevices.get(userId);
        if (monitor != null && !force)
            return monitor;
        try {
            List<MonitorTarget> targets = associationsService.getMonitorTargets(userId);
            monitor = new UserMonitorTarget();
            monitor.setUserId(userId);
            Map<String, MonitorTarget> devices = monitor.getDevices();
            List<MonitorTarget> motorcades = monitor.getMotorcades();

            targets.forEach(target -> {
                if (target.getType() == 0)
                    devices.put(target.getDeviceNumber(), target);
                else
                    motorcades.add(target);
            });

            AssociationCache.put(monitor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monitor;
    }

    /**
     * 获取设备所对应的用户列表
     */
    public static Set<String> getUsers(String deviceNumber) {
        return deviceAssociatedUsers.get(deviceNumber);
    }

    /**
     * 将用户监控对象放入缓存
     */
    public static void put(UserMonitorTarget target) {
        // 检查是否已载入缓存
        UserMonitorTarget old = userAssociatedDevices.get(target.getUserId());
        if (old == null) {// 从未载入
            // 存入缓存
            userAssociatedDevices.put(target.getUserId(), target);
            // 更新设备与用户关系
            for (String deviceNumber : target.getDevices().keySet()) {
                Set<String> users = deviceAssociatedUsers.get(deviceNumber);
                if (users == null) {// 为空则初始化
                    users = Collections.synchronizedSet(new HashSet<String>());
                    deviceAssociatedUsers.put(deviceNumber, users);
                }

                users.add(target.getUserId());
            }
        } else {// 已载入
            List<String> now = new ArrayList<>(target.getDevices().keySet());
            List<String> befor = new ArrayList<>(old.getDevices().keySet());
            if (now.removeAll(befor)) {// 得到新增的设备号
                for (String deviceNumber : now) {
                    Set<String> users = deviceAssociatedUsers.get(deviceNumber);
                    if (users == null) {
                        users = Collections.synchronizedSet(new HashSet<String>());
                        deviceAssociatedUsers.put(deviceNumber, users);
                    }

                    users.add(target.getUserId());
                }
            }
            now = new ArrayList<>(target.getDevices().keySet());
            if (befor.removeAll(now)) {// 得到失效的设备号

                befor.forEach(deviceNumber -> {
                    Set<String> users = deviceAssociatedUsers.get(deviceNumber);
                    if (users != null) {
                        users.remove(target.getUserId());
                    }
                });

            }
            // 覆盖缓存
            userAssociatedDevices.put(target.getUserId(), target);
        }
    }
}
