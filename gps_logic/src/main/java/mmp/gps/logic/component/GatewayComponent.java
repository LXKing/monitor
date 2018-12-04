package mmp.gps.logic.component;

import mmp.gps.common.util.PackageUtil;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.*;
import mmp.gps.domain.push.InstructResultMessage;
import mmp.gps.domain.statistics.DeviceOnlineOfflineReport;
import mmp.gps.domain.track.Track;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.contract.IRawDataParser;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.push.Pushers;
import mmp.gps.logic.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
@ServiceComponent
public class GatewayComponent {

    @Autowired
    private GatewayService gatewayService;
    private Pushers pushers = null;
    private HashMap parsers = new HashMap();
    private boolean initialized;


    public GatewayComponent() {
        ServiceComponents.regist(this);
    }

    private synchronized void init() {
        if (!this.initialized) {
            this.initialized = true;
            this.pushers = Pushers.getCurrent();
            List classess = PackageUtil.getSubClasses("mmp.gps.logic.codec", IRawDataParser.class);
            Iterator var2 = classess.iterator();

            while (var2.hasNext()) {
                Class c = (Class) var2.next();

                try {
                    IRawDataParser e = (IRawDataParser) c.newInstance();
                    this.parsers.put(Integer.valueOf(e.getProtocolKind()), e);
                } catch (InstantiationException var5) {
                    var5.printStackTrace();
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                }
            }

            this.pushers.reload();
        }
    }

    private BulkDataDto parse(DataResolveRequest request) {
        if (!this.initialized) {
            this.init();
        }

        List list = request.getData();
        BulkDataDto bulk = new BulkDataDto();

        for (int i = 0; i < list.size(); ++i) {
            RawData data = (RawData) list.get(i);
            String deviceNumber = data.getDn();
            Device device = Devices.getCurrent().get(deviceNumber, data.getType());
            device.setHost(request.getHost());
            device.setOnline();
            IRawDataParser parser = (IRawDataParser) this.parsers.get(Integer.valueOf(data.getType()));
            if (parser != null) {
                parser.parse(data, bulk);
            }
        }

        return bulk;
    }

    @ServiceMethod(
            value = "gateway.data.resolve",
            allowAnoumous = true,
            description = "解析设备原始数据"
    )
    public boolean resolve(DataResolveRequest request) {
        BulkDataDto bulk = this.parse(request);
        this.gatewayService.save(bulk);
        this.pushers.push(bulk);
        return true;
    }

    @ServiceMethod(
            value = "gateway.instruct.status",
            allowAnoumous = true,
            description = "更新平台下发指令状态"
    )
    public boolean instructStatus(InstructStatusRequest request) {
        this.gatewayService.save(request);
        InstructResultMessage message = new InstructResultMessage();
        message.id = request.getId();
        message.number = request.getNumber();
        message.result = request.getResult();
        Pushers.getCurrent().push(request.getNumber(), "device.instruct.result", message);
        return true;
    }

    @ServiceMethod(
            value = "gateway.multimedia.save",
            allowAnoumous = true,
            description = "保存多媒体数据"
    )
    public boolean multimediaSave(MultimediaSaveRequest request) {
        this.gatewayService.save(request);
        return true;
    }

    @ServiceMethod(
            value = "gateway.multimedia.upload.percent",
            allowAnoumous = true,
            description = "多媒体数据上传进度"
    )
    public boolean multimediaUpload(MultimediaDataUploadReport report) {
        Pushers.getCurrent().push(report.number, "device.multimedia.upload.percent", report);
        return true;
    }

    @ServiceMethod(
            value = "gateway.statistics.device.onlineoffline",
            allowAnoumous = true,
            description = "设备上线下线通知"
    )
    public boolean onlineoffline(DeviceOnlineOfflineReport report) {
        Device device = Devices.getCurrent().get(report.number);
        if (device != null) {
            Track track = device.getTrack();
            if (track != null) {
                track.setO((byte) (report.on ? 1 : 0));
            }
        }

        this.gatewayService.save(report);
        Pushers.getCurrent().push(report.number, "gateway.statistics.device.onlineoffline", report);
        return true;
    }
}
