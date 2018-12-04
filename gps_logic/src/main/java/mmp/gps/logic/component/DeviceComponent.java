package mmp.gps.logic.component;

import mmp.gps.domain.device.*;
import mmp.gps.logic.Identity;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class DeviceComponent {


    @Autowired

    private DeviceService deviceService;


    public DeviceComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "device.log.enable",
            description = "启用或禁止设备数据日志记录"
    )
    public boolean dataLogs(SetDataLogRequest request) throws Exception {
        Device device = Devices.getCurrent().get(request.getNumber());
        if (device == null) {
            throw new Exception("设备不存在");
        } else {
            this.deviceService.setDebuggingMode(device, request.isEnable());
            return true;
        }
    }

    @ServiceMethod(
            value = "device.log.load",
            description = "读取设备数据日志记录"
    )
    public LoadDataLogResponse loadLogs(LoadDataLogRequest request) throws Exception {
        return this.deviceService.loadLogs(request);
    }

    @ServiceMethod(
            value = "device.count.online",
            description = "统计用户在线设备数量"
    )
    public DeviceCountOnlineResponse count(DeviceCountOnlineRequest request) throws Exception {
        return this.deviceService.countOnline(request.getNumbers(), request.getTimeOut());
    }

    @ServiceMethod(
            value = "device.query.online",
            description = "查询平台在线设备数量"
    )
    public DeviceCountOnlineResponse query(DeviceCountOnlineRequest request) throws Exception {
        return this.deviceService.queryOnline(request.getTimeOut());
    }

    @ServiceMethod(
            value = "device.status",
            description = "查询设备状态"
    )
    public DeviceStatusResponse status(DeviceStatusRequest request) throws Exception {
        return this.deviceService.status(request.getNumber());
    }

    @ServiceMethod(
            value = "device.bind.user",
            description = "设备绑定用户"
    )
    public boolean bindUser(DeviceBindUserRequest request, Identity identity) throws Exception {
        this.deviceService.bindUser(request, identity);
        return true;
    }
}
