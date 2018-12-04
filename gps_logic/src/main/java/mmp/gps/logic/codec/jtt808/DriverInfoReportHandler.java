package mmp.gps.logic.codec.jtt808;

import mmp.gps.common.util.DateFormats;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.RawData;
import mmp.gps.domain.push.DriverInfoReportMessage;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.contract.ICommandHandler;
import mmp.gps.logic.push.Pushers;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.DriverInfoReportBody;

import java.util.Date;

public class DriverInfoReportHandler implements ICommandHandler {

    private Object[] keys = new Object[]{Integer.valueOf(1794)};


    public Object[] getKeys() {
        return this.keys;
    }

    public void process(RawData raw, byte[] data, BulkDataDto bulk) {
        Devices.getCurrent().get(raw.getDn(), raw.getType());
        DriverInfoReportBody body = new DriverInfoReportBody();
        JTT808Packet packet = new JTT808Packet(body);

        try {

            packet.from(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DriverInfoReportMessage message = new DriverInfoReportMessage();
        message.number = raw.getDn();
        message.status = body.getStatus() == 1 ? "IC卡插入" : " IC卡拔出";
        String gpsTimeString = body.getTime().toString();

        try {
            message.time = DateFormats.DateTimeFormat.parse(gpsTimeString);
        } catch (Exception var10) {
            message.time = new Date();
        }

        switch (body.getResult()) {
            case 0:
                message.result = "IC卡读卡成功";
                break;
            case 1:
                message.result = "读卡失败,原因为卡片密钥认证未通过";
                break;
            case 2:
                message.result = "读卡失败,原因为卡片已被锁定";
                break;
            case 3:
                message.result = "读卡失败,原因为卡片被拔出";
                break;
            case 4:
                message.result = "读卡失败,原因为数据校验错误";
        }

        message.name = body.getDriverName();
        message.certificate = body.getCertificateNumber();
        message.issuingAgency = body.getIssuingAgencyName();
        gpsTimeString = Integer.toHexString(body.getValidYear1() & 255) + Integer.toHexString(body.getValidYear2() & 255);
        gpsTimeString = gpsTimeString + "-" + Integer.toHexString(body.getValidMonth() & 255);
        gpsTimeString = gpsTimeString + "-" + Integer.toHexString(body.getValidDay() & 255);

        try {
            message.limit = DateFormats.DateFormat.parse(gpsTimeString);
        } catch (Exception var9) {
            message.limit = null;
        }

        Pushers.getCurrent().push(packet.getNumber(), "device.driverinfo.report", message);
    }
}
