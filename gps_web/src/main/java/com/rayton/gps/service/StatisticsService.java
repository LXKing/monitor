package com.rayton.gps.service;

import com.rayton.gps.domain.statistics.MileageOilCountResult;
import com.rayton.gps.cache.AssociationCache;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.cache.UserMonitorTarget;
import com.rayton.gps.dao.cache.association.MonitorTarget;
import com.rayton.gps.dao.security.OperateLog;
import com.rayton.gps.dao.security.OperateLogDto;
import com.rayton.gps.dao.statistics.*;
import com.rayton.gps.godp.IGodpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

@Service
public class StatisticsService {
    @Autowired
    private IStatisticsDao statisticsServiceDao;
    @Autowired
    private IGodpDao godpDao;

    private List<MonitorTarget> findMotorcade(String userId, String motorcade) {
        // 获取用户监控对象
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        List<MonitorTarget> list = new ArrayList<MonitorTarget>();
        if (motorcade == null || motorcade.length() <= 0)
            list = target.getMotorcades();
        else {
            for (MonitorTarget mt : target.getMotorcades()) {
                if (mt.getName().contains(motorcade))
                    list.add(mt);
            }
        }

        List<MonitorTarget> allMotorcades = new ArrayList<MonitorTarget>();
        // 去除企业
        for (MonitorTarget mt : list) {
            if (mt.getType() == 1) {
                allMotorcades.add(mt);
            }
        }
        return allMotorcades;
    }

    // 组分页
    private List<MonitorTarget> pageMotorcade(List<MonitorTarget> allmotorcades, int pageIndex, int pageSize) {
        List<MonitorTarget> pageMontorcades = new ArrayList<MonitorTarget>();

        int startIndex = (pageIndex - 1) * pageSize;
        if (allmotorcades.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < allmotorcades.size(); index++) {
                pageMontorcades.add(allmotorcades.get(index));
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }

        return pageMontorcades;
    }

    private List<MonitorTarget> findVehicleInMotorcade(String userId, String motorcadeId) {
        // 获取用户监控对象
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = new ArrayList<MonitorTarget>();
        for (MonitorTarget device : allDevices) {
            if (device.getPid().equals(motorcadeId)) {
                motorcadeDevices.add(device);
            }
        }

        return motorcadeDevices;
    }

    public List<HistoryOnlineOfflineCount> historyOnlineOfflineCount(String userId, String motorcade, Date start,
                                                                     Date end) throws Exception {
        List<HistoryOnlineOfflineCount> rows = new ArrayList<HistoryOnlineOfflineCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineOfflineCount> links = new HashMap<String, HistoryOnlineOfflineCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            HistoryOnlineOfflineCount count = new HistoryOnlineOfflineCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setTotal(count.getTotal() + 1);
                    count.setOffline(count.getTotal());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 统计历史上线
        List<String> onlines = godpDao.historyOnlineOfflineStatistics(numbers, start, end);
        for (String number : onlines) {
            HistoryOnlineOfflineCount count = links.get(number);
            count.setOnline(count.getOnline() + 1);
            count.setOnlineRate((int) (count.getOnline() * 1.0 / count.getTotal() * 100));
            count.setOffline(count.getTotal() - count.getOnline());
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return rows;
    }

    /**
     * 统计历史上线下线率，根据车队汇总
     */
    public Page<HistoryOnlineOfflineCount> historyOnlineOfflineCount(String userId, String motorcade, Date start,
                                                                     Date end, int pageIndex, int pageSize) throws Exception {
        Page<HistoryOnlineOfflineCount> page = new Page<HistoryOnlineOfflineCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineOfflineCount> links = new HashMap<String, HistoryOnlineOfflineCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            HistoryOnlineOfflineCount count = new HistoryOnlineOfflineCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setTotal(count.getTotal() + 1);
                    count.setOffline(count.getTotal());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 统计历史上线
        List<String> onlines = godpDao.historyOnlineOfflineStatistics(numbers, start, end);
        for (String number : onlines) {
            HistoryOnlineOfflineCount count = links.get(number);
            count.setOnline(count.getOnline() + 1);
            count.setOnlineRate((int) (count.getOnline() * 1.0 / count.getTotal() * 100));
            count.setOffline(count.getTotal() - count.getOnline());
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return page;
    }

    /**
     * 统计历史上线下线率，根据车辆明细
     */
    public List<HistoryOnlineOfflineDetail> historyOnlineOfflineDetail(String userId, String motorcadeId, String
            motorcade, Date start, Date end) throws Exception {
        List<HistoryOnlineOfflineDetail> rows = new ArrayList<HistoryOnlineOfflineDetail>();
        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineOfflineDetail> links = new HashMap<String, HistoryOnlineOfflineDetail>();
        for (MonitorTarget mt : motorcadeDevices) {
            HistoryOnlineOfflineDetail detail = new HistoryOnlineOfflineDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 统计历史上线
        List<String> onlines = godpDao.historyOnlineOfflineStatistics(pageNumbers, start, end);
        for (String number : onlines) {
            HistoryOnlineOfflineDetail detail = links.get(number);
            detail.setOnline(true);
        }

        return rows;

    }

    /**
     * 统计历史上线下线率，根据车辆明细
     */
    public Page<HistoryOnlineOfflineDetail> historyOnlineOfflineDetail(String userId, String motorcadeId, String
            motorcade, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<HistoryOnlineOfflineDetail> page = new Page<HistoryOnlineOfflineDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;

        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineOfflineDetail> links = new HashMap<String, HistoryOnlineOfflineDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                HistoryOnlineOfflineDetail detail = new HistoryOnlineOfflineDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 统计历史上线
        List<String> onlines = godpDao.historyOnlineOfflineStatistics(pageNumbers, start, end);
        for (String number : onlines) {
            HistoryOnlineOfflineDetail detail = links.get(number);
            detail.setOnline(true);
        }

        return page;

    }

    /**
     * 历史在线时长统计
     */
    public List<HistoryOnlineTimeCount> historyOnlineTimeCount(String userId, String motorcade, Date start, Date end)
            throws Exception {
        List<HistoryOnlineTimeCount> rows = new ArrayList<HistoryOnlineTimeCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线时间
        Map<String, HistoryOnlineTimeCount> links = new HashMap<String, HistoryOnlineTimeCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            HistoryOnlineTimeCount count = new HistoryOnlineTimeCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 基本时长
        int base = (int) ((end.getTime() - start.getTime()) / 1000 / 60);
        // 统计历史在线时长
        Map<String, Integer> result = godpDao.historyOnlineTimeStatistics(numbers, start, end);
        for (Entry<String, Integer> entry : result.entrySet()) {
            HistoryOnlineTimeCount count = links.get(entry.getKey());
            count.setMust(count.getMust() + base);
            count.setReal(count.getReal() + entry.getValue() / 60);
            count.setOnlineRate((count.getReal() * 1.0f) / (count.getMust() <= 0 ? 1 : count.getMust()) * 100);
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return rows;
    }

    /**
     * 历史在线时长统计
     */
    public Page<HistoryOnlineTimeCount> historyOnlineTimeCount(String userId, String motorcade, Date start, Date end,
                                                               int pageIndex, int pageSize) throws Exception {
        Page<HistoryOnlineTimeCount> page = new Page<HistoryOnlineTimeCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线时间
        Map<String, HistoryOnlineTimeCount> links = new HashMap<String, HistoryOnlineTimeCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            HistoryOnlineTimeCount count = new HistoryOnlineTimeCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 基本时长
        int base = (int) ((end.getTime() - start.getTime()) / 1000 / 60);
        // 统计历史在线时长
        Map<String, Integer> result = godpDao.historyOnlineTimeStatistics(numbers, start, end);
        for (Entry<String, Integer> entry : result.entrySet()) {
            HistoryOnlineTimeCount count = links.get(entry.getKey());
            count.setMust(count.getMust() + base);
            count.setReal(count.getReal() + entry.getValue() / 60);
            count.setOnlineRate((count.getReal() * 1.0f) / (count.getMust() <= 0 ? 1 : count.getMust()) * 100);
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return page;
    }

    /**
     * 历史在线时长明细
     */
    public List<HistoryOnlineTimeDetail> historyOnlineTimeDetail(String userId, String motorcadeId, String motorcade,
                                                                 Date start, Date end) throws Exception {
        List<HistoryOnlineTimeDetail> rows = new ArrayList<HistoryOnlineTimeDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineTimeDetail> links = new HashMap<String, HistoryOnlineTimeDetail>();
        for (MonitorTarget mt : motorcadeDevices) {
            HistoryOnlineTimeDetail detail = new HistoryOnlineTimeDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }

        if (pageNumbers.size() <= 0)
            return rows;

        // 基本时长
        int base = (int) ((end.getTime() - start.getTime()) / 1000 / 60);
        // 统计历史在线时长
        Map<String, Integer> result = godpDao.historyOnlineTimeStatistics(pageNumbers, start, end);
        for (Entry<String, Integer> entry : result.entrySet()) {
            HistoryOnlineTimeDetail count = links.get(entry.getKey());
            count.setMust(base);
            count.setReal(entry.getValue() / 60);
            count.setOnlineRate((count.getReal() * 1.0f / (base <= 0 ? 1 : base)) * 100);
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return rows;

    }

    /**
     * 历史在线时长明细
     */
    public Page<HistoryOnlineTimeDetail> historyOnlineTimeDetail(String userId, String motorcadeId, String motorcade,
                                                                 Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<HistoryOnlineTimeDetail> page = new Page<HistoryOnlineTimeDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, HistoryOnlineTimeDetail> links = new HashMap<String, HistoryOnlineTimeDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                HistoryOnlineTimeDetail detail = new HistoryOnlineTimeDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 基本时长
        int base = (int) ((end.getTime() - start.getTime()) / 1000 / 60);
        // 统计历史在线时长
        Map<String, Integer> result = godpDao.historyOnlineTimeStatistics(pageNumbers, start, end);
        for (Entry<String, Integer> entry : result.entrySet()) {
            HistoryOnlineTimeDetail count = links.get(entry.getKey());
            count.setMust(base);
            count.setReal(entry.getValue() / 60);
            count.setOnlineRate((count.getReal() * 1.0f / (base <= 0 ? 1 : base)) * 100);
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return page;

    }

    /**
     * 当前在线情况统计
     *
     * @throws Exception
     */
    public List<CurrentOnlineOfflineCount> currentOnlineOfflineCount(String userId, String motorcade) throws Exception {
        List<CurrentOnlineOfflineCount> rows = new ArrayList<CurrentOnlineOfflineCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, CurrentOnlineOfflineCount> links = new HashMap<String, CurrentOnlineOfflineCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            CurrentOnlineOfflineCount count = new CurrentOnlineOfflineCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setTotal(count.getTotal() + 1);
                    count.setOffline(count.getTotal());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 统计当前上线
        List<String> onlines = godpDao.currentOnlineOfflineStatistics(numbers);
        for (String number : onlines) {
            CurrentOnlineOfflineCount count = links.get(number);
            count.setOnline(count.getOnline() + 1);
            count.setOnlineRate((int) (count.getOnline() * 1.0 / count.getTotal() * 100));
            count.setOffline(count.getTotal() - count.getOnline());
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return rows;
    }

    /**
     * 当前在线情况统计
     *
     * @throws Exception
     */
    public Page<CurrentOnlineOfflineCount> currentOnlineOfflineCount(String userId, String motorcade, int pageIndex,
                                                                     int pageSize) throws Exception {
        Page<CurrentOnlineOfflineCount> page = new Page<CurrentOnlineOfflineCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, CurrentOnlineOfflineCount> links = new HashMap<String, CurrentOnlineOfflineCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            CurrentOnlineOfflineCount count = new CurrentOnlineOfflineCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setTotal(count.getTotal() + 1);
                    count.setOffline(count.getTotal());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        // 统计当前上线
        List<String> onlines = godpDao.currentOnlineOfflineStatistics(numbers);
        for (String number : onlines) {
            CurrentOnlineOfflineCount count = links.get(number);
            count.setOnline(count.getOnline() + 1);
            count.setOnlineRate((int) (count.getOnline() * 1.0 / count.getTotal() * 100));
            count.setOffline(count.getTotal() - count.getOnline());
            count.setOfflineRate(100 - count.getOnlineRate());
        }
        return page;
    }

    /**
     * 当前在线情况明细
     *
     * @throws Exception
     */
    public List<CurrentOnlineOfflineDetail> currentOnlineOfflineDetail(String userId, String motorcadeId, String
            motorcade) throws Exception {
        List<CurrentOnlineOfflineDetail> rows = new ArrayList<CurrentOnlineOfflineDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;

        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, CurrentOnlineOfflineDetail> links = new HashMap<String, CurrentOnlineOfflineDetail>();
        for (MonitorTarget mt : motorcadeDevices) {
            CurrentOnlineOfflineDetail detail = new CurrentOnlineOfflineDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 统计历史上线
        List<String> onlines = godpDao.currentOnlineOfflineStatistics(pageNumbers);
        for (String number : onlines) {
            CurrentOnlineOfflineDetail detail = links.get(number);
            detail.setOnline(true);
        }

        return rows;
    }

    /**
     * 当前在线情况明细
     *
     * @throws Exception
     */
    public Object currentOnlineOfflineDetail(String userId, String motorcadeId, String motorcade, int pageIndex, int
            pageSize) throws Exception {
        Page<CurrentOnlineOfflineDetail> page = new Page<CurrentOnlineOfflineDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;

        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, CurrentOnlineOfflineDetail> links = new HashMap<String, CurrentOnlineOfflineDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                CurrentOnlineOfflineDetail detail = new CurrentOnlineOfflineDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 统计历史上线
        List<String> onlines = godpDao.currentOnlineOfflineStatistics(pageNumbers);
        for (String number : onlines) {
            CurrentOnlineOfflineDetail detail = links.get(number);
            detail.setOnline(true);
        }

        return page;
    }

    /**
     * 统计里程及油耗
     */
    public List<MileageOilCount> mileageOilCount(String userId, String motorcade, Date start, Date end) throws
            Exception {
        List<MileageOilCount> rows = new ArrayList<MileageOilCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, MileageOilCount> links = new HashMap<String, MileageOilCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            MileageOilCount count = new MileageOilCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setVehicles(count.getVehicles() + 1);
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 统计里程、油耗
        List<MileageOilCountResult> list = godpDao.mileageOilCount(numbers, start, end);
        for (MileageOilCountResult result : list) {
            MileageOilCount count = links.get(result.number);
            count.setMileages(count.getMileages() + result.mileages);
            count.setOils(count.getOils() + result.oils);
        }
        return rows;
    }

    /**
     * 统计里程及油耗
     */
    public Page<MileageOilCount> mileageOilCount(String userId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<MileageOilCount> page = new Page<MileageOilCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新在线数量
        Map<String, MileageOilCount> links = new HashMap<String, MileageOilCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            MileageOilCount count = new MileageOilCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 统计车辆总数
                    count.setVehicles(count.getVehicles() + 1);
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 统计里程、油耗
        List<MileageOilCountResult> list = godpDao.mileageOilCount(numbers, start, end);
        for (MileageOilCountResult result : list) {
            MileageOilCount count = links.get(result.number);
            count.setMileages(count.getMileages() + result.mileages);
            count.setOils(count.getOils() + result.oils);
        }
        return page;
    }

    public List<MileageOilDetail> mileageOilDetail(String userId, String motorcadeId, String motorcade, Date start,
                                                   Date end) throws Exception {
        List<MileageOilDetail> rows = new ArrayList<MileageOilDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, MileageOilDetail> links = new HashMap<String, MileageOilDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            MileageOilDetail detail = new MileageOilDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 统计里程、油耗
        List<MileageOilCountResult> list = godpDao.mileageOilCount(pageNumbers, start, end);
        for (MileageOilCountResult result : list) {
            MileageOilDetail detail = links.get(result.number);
            detail.setMileages(detail.getMileages() + result.mileages);
            detail.setOils(detail.getOils() + result.oils);
        }
        return rows;
    }

    public Object mileageOilDetail(String userId, String motorcadeId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<MileageOilDetail> page = new Page<MileageOilDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, MileageOilDetail> links = new HashMap<String, MileageOilDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                MileageOilDetail detail = new MileageOilDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 统计里程、油耗
        List<MileageOilCountResult> list = godpDao.mileageOilCount(pageNumbers, start, end);
        for (MileageOilCountResult result : list) {
            MileageOilDetail detail = links.get(result.number);
            detail.setMileages(detail.getMileages() + result.mileages);
            detail.setOils(detail.getOils() + result.oils);
        }
        return page;
    }

    public List<OperateLog> operateAllLog(String companyId, String user, Date start, Date end) {
        List<OperateLogDto> list = statisticsServiceDao.operateAllLog(companyId, user, start, end);
        List<OperateLog> rows = new ArrayList<OperateLog>();
        for (OperateLogDto dto : list) {
            OperateLog log = new OperateLog();
            log.setAction(dto.action);
            log.setTime(dto.time);
            log.setUser(dto.user);

            rows.add(log);
        }

        return rows;
    }

    public Page<OperateLog> operateLog(String companyId, String user, Date start, Date end, int pageIndex, int
            pageSize) {
        int total = statisticsServiceDao.operateLogPageCount(companyId, user, start, end);
        Page<OperateLog> query = new Page<OperateLog>();
        query.total = total;

        if (total > 0) {
            List<OperateLogDto> rows = statisticsServiceDao.operateLogPageDetail(companyId, user, start, end,
                    (pageIndex - 1) * pageSize, pageSize);

            for (OperateLogDto dto : rows) {
                OperateLog log = new OperateLog();
                log.setAction(dto.action);
                log.setTime(dto.time);
                log.setUser(dto.user);

                query.rows.add(log);
            }
        }

        return query;
    }

    public List<VehicleAlarmCount> vehicleAlarmCount(String userId, String motorcade, Date start, Date end) {
        List<VehicleAlarmCount> rows = new ArrayList<VehicleAlarmCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleAlarmCount> links = new HashMap<String, VehicleAlarmCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            VehicleAlarmCount count = new VehicleAlarmCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        Map<String, Object> parms = new HashMap<String, Object>();
        parms.put("numbers", numbers);
        parms.put("start", start);
        parms.put("end", end);
        // 统计警情
        List<VehicleAlarmDto> list = statisticsServiceDao.vehicleAlarmCount(parms);
        for (VehicleAlarmDto result : list) {
            VehicleAlarmCount count = links.get(result.number);

            if (((result.alarms >> 1) & 1) == 1) {
                switch (result.ovt) {
                    // 0:无特定位置; 1:圆形区域; 2:矩形区域; 3:多边形区域; 4:路段
                    case 0:
                        count.setOverspeedNoneArea(count.getOverspeedNoneArea() + 1);// 普通超速
                        break;
                    case 1:
                    case 2:
                    case 3:
                        count.setOverspeedInArea(count.getOverspeedInArea() + 1);// 区域超速
                        break;
                    case 4:
                        count.setOverspeedInSection(count.getOverspeedInSection() + 1);// 路段超速
                        break;
                }
            }
            // 疲劳驾驶
            if (((result.alarms >> 2) & 1) == 1) {
                count.setFatigueDriving(count.getFatigueDriving() + 1);
            }
            // 区域停车超时
            if (((result.alarms >> 19) & 1) == 1) {
                count.setParkingTimeout(count.getParkingTimeout() + 1);
            }
            // 路线偏离
            if (((result.alarms >> 23) & 1) == 1) {
                count.setRouteDeparture(count.getRouteDeparture() + 1);
            }
        }
        return rows;
    }

    public Page<VehicleAlarmCount> vehicleAlarmCount(String userId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) {
        Page<VehicleAlarmCount> page = new Page<VehicleAlarmCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleAlarmCount> links = new HashMap<String, VehicleAlarmCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            VehicleAlarmCount count = new VehicleAlarmCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }
        Map<String, Object> parms = new HashMap<String, Object>();
        parms.put("numbers", numbers);
        parms.put("start", start);
        parms.put("end", end);
        // 统计警情
        List<VehicleAlarmDto> list = statisticsServiceDao.vehicleAlarmCount(parms);
        for (VehicleAlarmDto result : list) {
            VehicleAlarmCount count = links.get(result.number);

            if (((result.alarms >> 1) & 1) == 1) {
                switch (result.ovt) {
                    // 0:无特定位置; 1:圆形区域; 2:矩形区域; 3:多边形区域; 4:路段
                    case 0:
                        count.setOverspeedNoneArea(count.getOverspeedNoneArea() + 1);// 普通超速
                        break;
                    case 1:
                    case 2:
                    case 3:
                        count.setOverspeedInArea(count.getOverspeedInArea() + 1);// 区域超速
                        break;
                    case 4:
                        count.setOverspeedInSection(count.getOverspeedInSection() + 1);// 路段超速
                        break;
                }
            }
            // 疲劳驾驶
            if (((result.alarms >> 2) & 1) == 1) {
                count.setFatigueDriving(count.getFatigueDriving() + 1);
            }
            // 区域停车超时
            if (((result.alarms >> 19) & 1) == 1) {
                count.setParkingTimeout(count.getParkingTimeout() + 1);
            }
            // 路线偏离
            if (((result.alarms >> 23) & 1) == 1) {
                count.setRouteDeparture(count.getRouteDeparture() + 1);
            }
        }
        return page;
    }

    public List<VehicleAlarmDetail> vehicleAlarmDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end) throws Exception {
        List<VehicleAlarmDetail> rows = new ArrayList<VehicleAlarmDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleAlarmDetail> links = new HashMap<String, VehicleAlarmDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            VehicleAlarmDetail detail = new VehicleAlarmDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        Map<String, Object> parms = new HashMap<String, Object>();
        parms.put("numbers", pageNumbers);
        parms.put("start", start);
        parms.put("end", end);
        // 统计警情
        List<VehicleAlarmDto> list = statisticsServiceDao.vehicleAlarmCount(parms);
        for (VehicleAlarmDto result : list) {
            VehicleAlarmDetail detail = links.get(result.number);
            if (((result.alarms >> 1) & 1) == 1) {
                switch (result.ovt) {
                    // 0:无特定位置; 1:圆形区域; 2:矩形区域; 3:多边形区域; 4:路段
                    case 0:
                        detail.setOverspeedNoneArea(detail.getOverspeedNoneArea() + 1);// 普通超速
                        break;
                    case 1:
                    case 2:
                    case 3:
                        detail.setOverspeedInArea(detail.getOverspeedInArea() + 1);// 区域超速
                        break;
                    case 4:
                        detail.setOverspeedInSection(detail.getOverspeedInSection() + 1);// 路段超速
                        break;
                }
            }
            // 疲劳驾驶
            if (((result.alarms >> 2) & 1) == 1) {
                detail.setFatigueDriving(detail.getFatigueDriving() + 1);
            }
            // 区域停车超时
            if (((result.alarms >> 19) & 1) == 1) {
                detail.setParkingTimeout(detail.getParkingTimeout() + 1);
            }
            // 路线偏离
            if (((result.alarms >> 23) & 1) == 1) {
                detail.setRouteDeparture(detail.getRouteDeparture() + 1);
            }
        }
        return rows;
    }

    public Object vehicleAlarmDetail(String userId, String motorcadeId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<VehicleAlarmDetail> page = new Page<VehicleAlarmDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleAlarmDetail> links = new HashMap<String, VehicleAlarmDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                VehicleAlarmDetail detail = new VehicleAlarmDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        Map<String, Object> parms = new HashMap<String, Object>();
        parms.put("numbers", pageNumbers);
        parms.put("start", start);
        parms.put("end", end);
        // 统计警情
        List<VehicleAlarmDto> list = statisticsServiceDao.vehicleAlarmCount(parms);
        for (VehicleAlarmDto result : list) {
            VehicleAlarmDetail detail = links.get(result.number);
            if (((result.alarms >> 1) & 1) == 1) {
                switch (result.ovt) {
                    // 0:无特定位置; 1:圆形区域; 2:矩形区域; 3:多边形区域; 4:路段
                    case 0:
                        detail.setOverspeedNoneArea(detail.getOverspeedNoneArea() + 1);// 普通超速
                        break;
                    case 1:
                    case 2:
                    case 3:
                        detail.setOverspeedInArea(detail.getOverspeedInArea() + 1);// 区域超速
                        break;
                    case 4:
                        detail.setOverspeedInSection(detail.getOverspeedInSection() + 1);// 路段超速
                        break;
                }
            }
            // 疲劳驾驶
            if (((result.alarms >> 2) & 1) == 1) {
                detail.setFatigueDriving(detail.getFatigueDriving() + 1);
            }
            // 区域停车超时
            if (((result.alarms >> 19) & 1) == 1) {
                detail.setParkingTimeout(detail.getParkingTimeout() + 1);
            }
            // 路线偏离
            if (((result.alarms >> 23) & 1) == 1) {
                detail.setRouteDeparture(detail.getRouteDeparture() + 1);
            }
        }
        return page;
    }

    public List<VehicleFatigueDrivingCount> vehicleFatigueDrivingCount(String userId, String motorcade, Date start,
                                                                       Date end) throws Exception {
        List<VehicleFatigueDrivingCount> rows = new ArrayList<VehicleFatigueDrivingCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleFatigueDrivingCount> links = new HashMap<String, VehicleFatigueDrivingCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            VehicleFatigueDrivingCount count = new VehicleFatigueDrivingCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 疲劳驾驶
        List<VehicleFatigueDrivingDto> list = new ArrayList<VehicleFatigueDrivingDto>();
        for (String number : numbers) {
            List<TimeMileageRecord> records = statisticsServiceDao.vehicleFatigueDrivings(number, start, end);
            VehicleFatigueDrivingDto dto = new VehicleFatigueDrivingDto();
            dto.number = number;

            Date prev = null;
            Double topMileage = null;
            Double endMileage = null;
            for (TimeMileageRecord record : records) {
                dto.times += 1;
                if (prev == null) {
                    prev = record.time;
                    topMileage = record.mileage;
                    continue;
                }
                Date curr = record.time;
                endMileage = record.mileage;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 8 * 60 * 60 * 1000) {// 如时间间隔在8个小时内
                    dto.duration += mm;
                }
                prev = curr;
            }

            if (endMileage != null && topMileage != null && endMileage > topMileage)
                dto.mileages = endMileage - topMileage;
            list.add(dto);
        }
        for (VehicleFatigueDrivingDto dto : list) {
            VehicleFatigueDrivingCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setMileages(count.getMileages() + dto.mileages);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<VehicleFatigueDrivingCount> vehicleFatigueDrivingCount(String userId, String motorcade, Date start,
                                                                       Date end, int pageIndex, int pageSize) throws Exception {
        Page<VehicleFatigueDrivingCount> page = new Page<VehicleFatigueDrivingCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleFatigueDrivingCount> links = new HashMap<String, VehicleFatigueDrivingCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            VehicleFatigueDrivingCount count = new VehicleFatigueDrivingCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 疲劳驾驶
        List<VehicleFatigueDrivingDto> list = new ArrayList<VehicleFatigueDrivingDto>();
        for (String number : numbers) {
            List<TimeMileageRecord> records = statisticsServiceDao.vehicleFatigueDrivings(number, start, end);
            VehicleFatigueDrivingDto dto = new VehicleFatigueDrivingDto();
            dto.number = number;

            Date prev = null;
            Double topMileage = null;
            Double endMileage = null;
            for (TimeMileageRecord record : records) {
                dto.times += 1;
                if (prev == null) {
                    prev = record.time;
                    topMileage = record.mileage;
                    continue;
                }
                Date curr = record.time;
                endMileage = record.mileage;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 8 * 60 * 60 * 1000) {// 如时间间隔在8个小时内
                    dto.duration += mm;
                }
                prev = curr;
            }

            if (endMileage != null && topMileage != null && endMileage > topMileage)
                dto.mileages = endMileage - topMileage;
            list.add(dto);
        }
        for (VehicleFatigueDrivingDto dto : list) {
            VehicleFatigueDrivingCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setMileages(count.getMileages() + dto.mileages);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<VehicleFatigueDrivingDetail> vehicleFatigueDrivingDetail(String userId, String motorcadeId, String
            motorcade, Date start, Date end) throws Exception {
        List<VehicleFatigueDrivingDetail> rows = new ArrayList<VehicleFatigueDrivingDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleFatigueDrivingDetail> links = new HashMap<String, VehicleFatigueDrivingDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            VehicleFatigueDrivingDetail detail = new VehicleFatigueDrivingDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 疲劳驾驶
        List<VehicleFatigueDrivingDto> list = new ArrayList<VehicleFatigueDrivingDto>();
        for (String number : pageNumbers) {
            List<TimeMileageRecord> records = statisticsServiceDao.vehicleFatigueDrivings(number, start, end);
            VehicleFatigueDrivingDto dto = new VehicleFatigueDrivingDto();
            dto.number = number;

            Date prev = null;
            Double topMileage = null;
            Double endMileage = null;
            for (TimeMileageRecord record : records) {
                dto.times += 1;
                if (prev == null) {
                    prev = record.time;
                    topMileage = record.mileage;
                    continue;
                }
                Date curr = record.time;
                endMileage = record.mileage;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 8 * 60 * 60 * 1000) {// 如时间间隔在8个小时内
                    dto.duration += mm;
                }
                prev = curr;
            }

            if (endMileage != null && topMileage != null && endMileage > topMileage)
                dto.mileages = endMileage - topMileage;
            list.add(dto);
        }
        for (VehicleFatigueDrivingDto dto : list) {
            VehicleFatigueDrivingDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setMileages(dto.mileages);
            detail.setTimes(dto.times);
        }
        return rows;
    }

    public Page<VehicleFatigueDrivingDetail> vehicleFatigueDrivingDetail(String userId, String motorcadeId, String
            motorcade, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<VehicleFatigueDrivingDetail> page = new Page<VehicleFatigueDrivingDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleFatigueDrivingDetail> links = new HashMap<String, VehicleFatigueDrivingDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                VehicleFatigueDrivingDetail detail = new VehicleFatigueDrivingDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 疲劳驾驶
        List<VehicleFatigueDrivingDto> list = new ArrayList<VehicleFatigueDrivingDto>();
        for (String number : pageNumbers) {
            List<TimeMileageRecord> records = statisticsServiceDao.vehicleFatigueDrivings(number, start, end);
            VehicleFatigueDrivingDto dto = new VehicleFatigueDrivingDto();
            dto.number = number;

            Date prev = null;
            Double topMileage = null;
            Double endMileage = null;
            for (TimeMileageRecord record : records) {
                dto.times += 1;
                if (prev == null) {
                    prev = record.time;
                    topMileage = record.mileage;
                    continue;
                }
                Date curr = record.time;
                endMileage = record.mileage;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 8 * 60 * 60 * 1000) {// 如时间间隔在8个小时内
                    dto.duration += mm;
                }
                prev = curr;
            }

            if (endMileage != null && topMileage != null && endMileage > topMileage)
                dto.mileages = endMileage - topMileage;
            list.add(dto);
        }
        for (VehicleFatigueDrivingDto dto : list) {
            VehicleFatigueDrivingDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setMileages(dto.mileages);
            detail.setTimes(dto.times);
        }
        return page;
    }

    public List<VehicleOverspeedCount> vehicleOverspeedCount(String userId, String motorcade, Date start, Date end)
            throws Exception {
        List<VehicleOverspeedCount> rows = new ArrayList<VehicleOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleOverspeedCount> links = new HashMap<String, VehicleOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            VehicleOverspeedCount count = new VehicleOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 超速
        List<VehicleOverspeedDto> list = new ArrayList<VehicleOverspeedDto>();
        for (String number : numbers) {
            VehicleOverspeedDto dto = new VehicleOverspeedDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.vehicleOverspeeds(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (VehicleOverspeedDto dto : list) {
            VehicleOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<VehicleOverspeedCount> vehicleOverspeedCount(String userId, String motorcade, Date start, Date end,
                                                             int pageIndex, int pageSize) throws Exception {
        Page<VehicleOverspeedCount> page = new Page<VehicleOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleOverspeedCount> links = new HashMap<String, VehicleOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            VehicleOverspeedCount count = new VehicleOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 超速
        List<VehicleOverspeedDto> list = new ArrayList<VehicleOverspeedDto>();
        for (String number : numbers) {
            VehicleOverspeedDto dto = new VehicleOverspeedDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.vehicleOverspeeds(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (VehicleOverspeedDto dto : list) {
            VehicleOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<VehicleOverspeedDetail> vehicleOverspeedDetail(String userId, String motorcadeId, String motorcade,
                                                               Date start, Date end) throws Exception {
        List<VehicleOverspeedDetail> rows = new ArrayList<VehicleOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleOverspeedDetail> links = new HashMap<String, VehicleOverspeedDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            VehicleOverspeedDetail detail = new VehicleOverspeedDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 超速
        List<VehicleOverspeedDto> list = new ArrayList<VehicleOverspeedDto>();
        for (String number : pageNumbers) {
            VehicleOverspeedDto dto = new VehicleOverspeedDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.vehicleOverspeeds(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (VehicleOverspeedDto dto : list) {
            VehicleOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setTimes(dto.times);
        }
        return rows;
    }

    public Page<VehicleOverspeedDetail> vehicleOverspeedDetail(String userId, String motorcadeId, String motorcade,
                                                               Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<VehicleOverspeedDetail> page = new Page<VehicleOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, VehicleOverspeedDetail> links = new HashMap<String, VehicleOverspeedDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                VehicleOverspeedDetail detail = new VehicleOverspeedDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 超速
        List<VehicleOverspeedDto> list = new ArrayList<VehicleOverspeedDto>();
        for (String number : pageNumbers) {
            VehicleOverspeedDto dto = new VehicleOverspeedDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.vehicleOverspeeds(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (VehicleOverspeedDto dto : list) {
            VehicleOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setTimes(dto.times);
        }
        return page;
    }

    public List<SectionOverspeedCount> sectionOverspeedCount(String userId, String motorcade, Date start, Date end)
            throws Exception {
        List<SectionOverspeedCount> rows = new ArrayList<SectionOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, SectionOverspeedCount> links = new HashMap<String, SectionOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            SectionOverspeedCount count = new SectionOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 路段超速
        List<SectionOverspeedDto> list = new ArrayList<SectionOverspeedDto>();
        for (String number : numbers) {
            SectionOverspeedDto dto = new SectionOverspeedDto();
            dto.number = number;

            List<TimeNameRecord> records = statisticsServiceDao.sectionOverspeeds(number, start, end);
            Date prev = null;
            String section = null;
            for (TimeNameRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    section = record.name;
                    dto.section = section;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(section)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new SectionOverspeedDto();
                    dto.number = number;
                    dto.section = name;
                    dto.times = 1;
                }
                prev = curr;
                section = name;
            }

            list.add(dto);
        }
        for (SectionOverspeedDto dto : list) {
            SectionOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<SectionOverspeedCount> sectionOverspeedCount(String userId, String motorcade, Date start, Date end,
                                                             int pageIndex, int pageSize) throws Exception {
        Page<SectionOverspeedCount> page = new Page<SectionOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, SectionOverspeedCount> links = new HashMap<String, SectionOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            SectionOverspeedCount count = new SectionOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 路段超速
        List<SectionOverspeedDto> list = new ArrayList<SectionOverspeedDto>();
        for (String number : numbers) {
            SectionOverspeedDto dto = new SectionOverspeedDto();
            dto.number = number;

            List<TimeNameRecord> records = statisticsServiceDao.sectionOverspeeds(number, start, end);
            Date prev = null;
            String section = null;
            for (TimeNameRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    section = record.name;
                    dto.section = section;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(section)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new SectionOverspeedDto();
                    dto.number = number;
                    dto.section = name;
                    dto.times = 1;
                }
                prev = curr;
                section = name;
            }

            list.add(dto);
        }
        for (SectionOverspeedDto dto : list) {
            SectionOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<SectionOverspeedDetail> sectionOverspeedDetail(String userId, String motorcadeId, String motorcade,
                                                               Date start, Date end) throws Exception {
        List<SectionOverspeedDetail> rows = new ArrayList<SectionOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, SectionOverspeedDetail> links = new HashMap<String, SectionOverspeedDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            SectionOverspeedDetail detail = new SectionOverspeedDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 路段超速
        List<SectionOverspeedDto> list = new ArrayList<SectionOverspeedDto>();
        for (String number : pageNumbers) {
            SectionOverspeedDto dto = new SectionOverspeedDto();
            dto.number = number;

            List<TimeNameRecord> records = statisticsServiceDao.sectionOverspeeds(number, start, end);
            Date prev = null;
            String section = null;
            for (TimeNameRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    section = record.name;
                    dto.section = section;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(section)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new SectionOverspeedDto();
                    dto.number = number;
                    dto.section = name;
                    dto.times = 1;
                }
                prev = curr;
                section = name;
            }

            list.add(dto);
        }
        for (SectionOverspeedDto dto : list) {
            SectionOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setTimes(dto.times);
            detail.getDetail().add(dto);
        }
        return rows;
    }

    public Page<SectionOverspeedDetail> sectionOverspeedDetail(String userId, String motorcadeId, String motorcade,
                                                               Date start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<SectionOverspeedDetail> page = new Page<SectionOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, SectionOverspeedDetail> links = new HashMap<String, SectionOverspeedDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                SectionOverspeedDetail detail = new SectionOverspeedDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 路段超速
        List<SectionOverspeedDto> list = new ArrayList<SectionOverspeedDto>();
        for (String number : pageNumbers) {
            SectionOverspeedDto dto = new SectionOverspeedDto();
            dto.number = number;

            List<TimeNameRecord> records = statisticsServiceDao.sectionOverspeeds(number, start, end);
            Date prev = null;
            String section = null;
            for (TimeNameRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    section = record.name;
                    dto.section = section;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(section)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new SectionOverspeedDto();
                    dto.number = number;
                    dto.section = name;
                    dto.times = 1;
                }
                prev = curr;
                section = name;
            }

            list.add(dto);
        }
        for (SectionOverspeedDto dto : list) {
            SectionOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(dto.duration);
            detail.setTimes(dto.times);
            detail.getDetail().add(dto);
        }
        return page;
    }

    public List<AreaOverspeedCount> areaOverspeedCount(String userId, String motorcade, Date start, Date end) throws
            Exception {
        List<AreaOverspeedCount> rows = new ArrayList<AreaOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaOverspeedCount> links = new HashMap<String, AreaOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            AreaOverspeedCount count = new AreaOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 区域超速
        List<AreaOverspeedDto> list = new ArrayList<AreaOverspeedDto>();
        for (String number : numbers) {
            AreaOverspeedDto dto = new AreaOverspeedDto();
            dto.number = number;

            List<AreaOverspeedRecord> records = statisticsServiceDao.areaOverspeeds(number, start, end);
            Date prev = null;
            String area = null;
            for (AreaOverspeedRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    area = record.name;
                    dto.area = area;
                    dto.areaType = record.areaType;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                String areaType = record.areaType;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(area)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new AreaOverspeedDto();
                    dto.number = number;
                    dto.area = name;
                    dto.areaType = areaType;
                    dto.times = 1;
                }
                prev = curr;
                area = name;
            }
            list.add(dto);
        }
        for (AreaOverspeedDto dto : list) {
            AreaOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<AreaOverspeedCount> areaOverspeedCount(String userId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<AreaOverspeedCount> page = new Page<AreaOverspeedCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaOverspeedCount> links = new HashMap<String, AreaOverspeedCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            AreaOverspeedCount count = new AreaOverspeedCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 区域超速
        // 区域超速
        List<AreaOverspeedDto> list = new ArrayList<AreaOverspeedDto>();
        for (String number : numbers) {
            AreaOverspeedDto dto = new AreaOverspeedDto();
            dto.number = number;

            List<AreaOverspeedRecord> records = statisticsServiceDao.areaOverspeeds(number, start, end);
            Date prev = null;
            String area = null;
            for (AreaOverspeedRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    area = record.name;
                    dto.area = area;
                    dto.areaType = record.areaType;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                String areaType = record.areaType;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(area)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new AreaOverspeedDto();
                    dto.number = number;
                    dto.area = name;
                    dto.areaType = areaType;
                    dto.times = 1;
                }
                prev = curr;
                area = name;
            }
            list.add(dto);
        }
        for (AreaOverspeedDto dto : list) {
            AreaOverspeedCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<AreaOverspeedDetail> areaOverspeedDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end) throws Exception {
        List<AreaOverspeedDetail> rows = new ArrayList<AreaOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaOverspeedDetail> links = new HashMap<String, AreaOverspeedDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            AreaOverspeedDetail detail = new AreaOverspeedDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 区域超速
        List<AreaOverspeedDto> list = new ArrayList<AreaOverspeedDto>();
        for (String number : pageNumbers) {
            AreaOverspeedDto dto = new AreaOverspeedDto();
            dto.number = number;

            List<AreaOverspeedRecord> records = statisticsServiceDao.areaOverspeeds(number, start, end);
            Date prev = null;
            String area = null;
            for (AreaOverspeedRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    area = record.name;
                    dto.area = area;
                    dto.areaType = record.areaType;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                String areaType = record.areaType;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(area)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new AreaOverspeedDto();
                    dto.number = number;
                    dto.area = name;
                    dto.areaType = areaType;
                    dto.times = 1;
                }
                prev = curr;
                area = name;
            }
            list.add(dto);
        }
        for (AreaOverspeedDto dto : list) {
            AreaOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
            detail.getDetail().add(dto);
        }
        return rows;
    }

    public Page<AreaOverspeedDetail> areaOverspeedDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<AreaOverspeedDetail> page = new Page<AreaOverspeedDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaOverspeedDetail> links = new HashMap<String, AreaOverspeedDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                AreaOverspeedDetail detail = new AreaOverspeedDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 区域超速
        List<AreaOverspeedDto> list = new ArrayList<AreaOverspeedDto>();
        for (String number : pageNumbers) {
            AreaOverspeedDto dto = new AreaOverspeedDto();
            dto.number = number;

            List<AreaOverspeedRecord> records = statisticsServiceDao.areaOverspeeds(number, start, end);
            Date prev = null;
            String area = null;
            for (AreaOverspeedRecord record : records) {
                if (prev == null) {
                    dto.times += 1;
                    prev = record.time;
                    area = record.name;
                    dto.area = area;
                    dto.areaType = record.areaType;
                    continue;
                }
                Date curr = record.time;
                String name = record.name;
                String areaType = record.areaType;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000 && name.equals(area)) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                    dto.times += 1;
                } else {
                    list.add(dto);
                    dto = new AreaOverspeedDto();
                    dto.number = number;
                    dto.area = name;
                    dto.areaType = areaType;
                    dto.times = 1;
                }
                prev = curr;
                area = name;
            }
            list.add(dto);
        }
        for (AreaOverspeedDto dto : list) {
            AreaOverspeedDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
            detail.getDetail().add(dto);
        }
        return page;
    }

    public List<TimeoutParkingCount> timeoutParkingCount(String userId, String motorcade, Date start, Date end)
            throws Exception {
        List<TimeoutParkingCount> rows = new ArrayList<TimeoutParkingCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, TimeoutParkingCount> links = new HashMap<String, TimeoutParkingCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            TimeoutParkingCount count = new TimeoutParkingCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 停车超时
        List<TimeoutParkingDto> list = new ArrayList<TimeoutParkingDto>();
        for (String number : numbers) {
            TimeoutParkingDto dto = new TimeoutParkingDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.timeoutParkings(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (TimeoutParkingDto dto : list) {
            TimeoutParkingCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<TimeoutParkingCount> timeoutParkingCount(String userId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<TimeoutParkingCount> page = new Page<TimeoutParkingCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, TimeoutParkingCount> links = new HashMap<String, TimeoutParkingCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            TimeoutParkingCount count = new TimeoutParkingCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 停车超时
        List<TimeoutParkingDto> list = new ArrayList<TimeoutParkingDto>();
        for (String number : numbers) {
            TimeoutParkingDto dto = new TimeoutParkingDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.timeoutParkings(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (TimeoutParkingDto dto : list) {
            TimeoutParkingCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<TimeoutParkingDetail> timeoutParkingDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end) throws Exception {
        List<TimeoutParkingDetail> rows = new ArrayList<TimeoutParkingDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, TimeoutParkingDetail> links = new HashMap<String, TimeoutParkingDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            TimeoutParkingDetail detail = new TimeoutParkingDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 停车超时
        // 停车超时
        List<TimeoutParkingDto> list = new ArrayList<TimeoutParkingDto>();
        for (String number : pageNumbers) {
            TimeoutParkingDto dto = new TimeoutParkingDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.timeoutParkings(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (TimeoutParkingDto dto : list) {
            TimeoutParkingDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<TimeoutParkingDetail> timeoutParkingDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end, int pageIndex, int pageSize) throws Exception {
        Page<TimeoutParkingDetail> page = new Page<TimeoutParkingDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, TimeoutParkingDetail> links = new HashMap<String, TimeoutParkingDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                TimeoutParkingDetail detail = new TimeoutParkingDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 停车超时
        List<TimeoutParkingDto> list = new ArrayList<TimeoutParkingDto>();
        for (String number : pageNumbers) {
            TimeoutParkingDto dto = new TimeoutParkingDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.timeoutParkings(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }

            list.add(dto);
        }
        for (TimeoutParkingDto dto : list) {
            TimeoutParkingDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
        }
        return page;
    }

    public List<RouteDeviationCount> routeDeviationCount(String userId, String motorcade, Date start, Date end)
            throws Exception {
        List<RouteDeviationCount> rows = new ArrayList<RouteDeviationCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, RouteDeviationCount> links = new HashMap<String, RouteDeviationCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            RouteDeviationCount count = new RouteDeviationCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 路段偏离
        List<RouteDeviationDto> list = new ArrayList<RouteDeviationDto>();
        for (String number : numbers) {
            RouteDeviationDto dto = new RouteDeviationDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.routeDeviations(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }
            list.add(dto);
        }
        for (RouteDeviationDto dto : list) {
            RouteDeviationCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return rows;
    }

    public Page<RouteDeviationCount> routeDeviationCount(String userId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<RouteDeviationCount> page = new Page<RouteDeviationCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, RouteDeviationCount> links = new HashMap<String, RouteDeviationCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            RouteDeviationCount count = new RouteDeviationCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 路段偏离
        List<RouteDeviationDto> list = new ArrayList<RouteDeviationDto>();
        for (String number : numbers) {
            RouteDeviationDto dto = new RouteDeviationDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.routeDeviations(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }
            list.add(dto);
        }
        for (RouteDeviationDto dto : list) {
            RouteDeviationCount count = links.get(dto.number);
            count.setDuration(count.getDuration() + dto.duration);
            count.setTimes(count.getTimes() + dto.times);
        }
        return page;
    }

    public List<RouteDeviationDetail> routeDeviationDetail(String userId, String motorcadeId, String motorcade, Date
            start, Date end) throws Exception {
        List<RouteDeviationDetail> rows = new ArrayList<RouteDeviationDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, RouteDeviationDetail> links = new HashMap<String, RouteDeviationDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            RouteDeviationDetail detail = new RouteDeviationDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 路段偏离
        // 路段偏离
        List<RouteDeviationDto> list = new ArrayList<RouteDeviationDto>();
        for (String number : pageNumbers) {
            RouteDeviationDto dto = new RouteDeviationDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.routeDeviations(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }
            list.add(dto);
        }
        for (RouteDeviationDto dto : list) {
            RouteDeviationDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
        }
        return rows;
    }

    public Object routeDeviationDetail(String userId, String motorcadeId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<RouteDeviationDetail> page = new Page<RouteDeviationDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, RouteDeviationDetail> links = new HashMap<String, RouteDeviationDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                RouteDeviationDetail detail = new RouteDeviationDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 路段偏离
        List<RouteDeviationDto> list = new ArrayList<RouteDeviationDto>();
        for (String number : pageNumbers) {
            RouteDeviationDto dto = new RouteDeviationDto();
            dto.number = number;

            List<Date> times = statisticsServiceDao.routeDeviations(number, start, end);

            Date prev = null;
            for (Date time : times) {
                dto.times += 1;
                if (prev == null) {
                    prev = time;
                    continue;
                }
                Date curr = time;
                long currTimes = curr.getTime();
                long prevTimes = prev.getTime();
                long mm = currTimes - prevTimes;
                if (mm < 30 * 60 * 1000) {// 如时间间隔在30分钟内
                    dto.duration += mm;
                }
                prev = curr;
            }
            list.add(dto);
        }
        for (RouteDeviationDto dto : list) {
            RouteDeviationDetail detail = links.get(dto.number);
            detail.setDuration(detail.getDuration() + dto.duration);
            detail.setTimes(detail.getTimes() + dto.times);
        }
        return page;
    }

    public List<AreaIoCount> areaIoCount(String userId, String motorcade, Date start, Date end) throws Exception {
        List<AreaIoCount> rows = new ArrayList<AreaIoCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);
        if (allmotorcades.size() <= 0)
            return rows;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaIoCount> links = new HashMap<String, AreaIoCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : allmotorcades) {
            // 创建统计行
            AreaIoCount count = new AreaIoCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 进出路线
        List<AreaIoDto> list = new ArrayList<AreaIoDto>();
        for (String number : numbers) {
            AreaIoDto dto = new AreaIoDto();
            dto.number = number;

            List<Byte> records = statisticsServiceDao.areaIoRecords(number, start, end);

            for (byte io : records) {
                if (io == 0)
                    dto.in += 1;
                else
                    dto.out += 1;
            }
            list.add(dto);
        }
        for (AreaIoDto dto : list) {
            AreaIoCount count = links.get(dto.number);
            count.setIn(count.getIn() + dto.in);
            count.setOut(count.getOut() + dto.out);
        }
        return rows;
    }

    public Page<AreaIoCount> areaIoCount(String userId, String motorcade, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {
        Page<AreaIoCount> page = new Page<AreaIoCount>();
        // 获取用户监控对象
        List<MonitorTarget> allmotorcades = findMotorcade(userId, motorcade);

        page.total = allmotorcades.size();

        List<MonitorTarget> pageMontorcades = pageMotorcade(allmotorcades, pageIndex, pageSize);

        if (pageMontorcades.size() <= 0)
            return page;

        // 收集设备号
        List<String> numbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaIoCount> links = new HashMap<String, AreaIoCount>();

        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Collection<MonitorTarget> allDevices = target.getDevices().values();
        for (MonitorTarget mo : pageMontorcades) {
            // 创建统计行
            AreaIoCount count = new AreaIoCount();
            count.setMotorcadeId(mo.getId());
            count.setMotorcade(mo.getName());
            count.setStart(start);
            count.setEnd(end);
            page.rows.add(count);

            for (MonitorTarget device : allDevices) {
                if (device.getPid().equals(mo.getId())) {
                    numbers.add(device.getDeviceNumber());
                    // 放入关系表
                    links.put(device.getDeviceNumber(), count);
                }
            }
        }

        // 进出路线
        List<AreaIoDto> list = new ArrayList<AreaIoDto>();
        for (String number : numbers) {
            AreaIoDto dto = new AreaIoDto();
            dto.number = number;

            List<Byte> records = statisticsServiceDao.areaIoRecords(number, start, end);

            for (byte io : records) {
                if (io == 0)
                    dto.in += 1;
                else
                    dto.out += 1;
            }
            list.add(dto);
        }
        for (AreaIoDto dto : list) {
            AreaIoCount count = links.get(dto.number);
            count.setIn(count.getIn() + dto.in);
            count.setOut(count.getOut() + dto.out);
        }
        return page;
    }

    public List<AreaIoDetail> areaIoDetail(String userId, String motorcadeId, String motorcade, Date start, Date end)
            throws Exception {
        List<AreaIoDetail> rows = new ArrayList<AreaIoDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);
        if (motorcadeDevices.size() <= 0)
            return rows;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaIoDetail> links = new HashMap<String, AreaIoDetail>();

        for (MonitorTarget mt : motorcadeDevices) {
            AreaIoDetail detail = new AreaIoDetail();
            detail.setDeviceNumber(mt.getDeviceNumber());
            detail.setStart(start);
            detail.setEnd(end);
            detail.setMotorcade(motorcade);
            detail.setPlateNumber(mt.getName());

            rows.add(detail);
            links.put(detail.getDeviceNumber(), detail);

            pageNumbers.add(mt.getDeviceNumber());
        }
        if (pageNumbers.size() <= 0)
            return rows;

        // 进出路线
        List<AreaIoDto> list = new ArrayList<AreaIoDto>();
        for (String number : pageNumbers) {
            AreaIoDto dto = new AreaIoDto();
            dto.number = number;

            List<Byte> records = statisticsServiceDao.areaIoRecords(number, start, end);

            for (byte io : records) {
                if (io == 0)
                    dto.in += 1;
                else
                    dto.out += 1;
            }
            list.add(dto);
        }
        for (AreaIoDto dto : list) {
            AreaIoDetail detail = links.get(dto.number);
            detail.setIn(dto.in);
            detail.setOut(dto.out);
        }
        return rows;
    }

    public Object areaIoDetail(String userId, String motorcadeId, String motorcade, Date start, Date end, int
            pageIndex, int pageSize) throws Exception {
        Page<AreaIoDetail> page = new Page<AreaIoDetail>();

        // 查找车队所有设备
        List<MonitorTarget> motorcadeDevices = findVehicleInMotorcade(userId, motorcadeId);

        page.total = motorcadeDevices.size();
        if (page.total <= 0)
            return page;
        // 收集设备号
        List<String> pageNumbers = new ArrayList<String>();
        // 建立关系，以便从后台查到数据后更新
        Map<String, AreaIoDetail> links = new HashMap<String, AreaIoDetail>();
        // 分页
        int startIndex = (pageIndex - 1) * pageSize;
        if (motorcadeDevices.size() > startIndex) {
            int count = 0;
            for (int index = startIndex; index < motorcadeDevices.size(); index++) {
                MonitorTarget mt = motorcadeDevices.get(index);

                AreaIoDetail detail = new AreaIoDetail();
                detail.setDeviceNumber(mt.getDeviceNumber());
                detail.setStart(start);
                detail.setEnd(end);
                detail.setMotorcade(motorcade);
                detail.setPlateNumber(mt.getName());

                page.rows.add(detail);
                links.put(detail.getDeviceNumber(), detail);

                pageNumbers.add(mt.getDeviceNumber());
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        if (pageNumbers.size() <= 0)
            return page;

        // 进出路线
        List<AreaIoDto> list = new ArrayList<AreaIoDto>();
        for (String number : pageNumbers) {
            AreaIoDto dto = new AreaIoDto();
            dto.number = number;

            List<Byte> records = statisticsServiceDao.areaIoRecords(number, start, end);

            for (byte io : records) {
                if (io == 0)
                    dto.in += 1;
                else
                    dto.out += 1;
            }
            list.add(dto);
        }
        for (AreaIoDto dto : list) {
            AreaIoDetail detail = links.get(dto.number);
            detail.setIn(dto.in);
            detail.setOut(dto.out);
        }
        return page;
    }
}
