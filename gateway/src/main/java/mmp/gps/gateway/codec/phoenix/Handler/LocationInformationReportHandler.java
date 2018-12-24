package mmp.gps.gateway.codec.phoenix.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.GpsTime;
import mmp.gps.protocol.jtt808.body.LocationInformationAddonInfo;
import mmp.gps.protocol.jtt808.body.LocationInformationBaseInfo;
import mmp.gps.protocol.jtt808.body.LocationInformationReportBody;
import mmp.gps.protocol.jtt808.body.RawBody;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class LocationInformationReportHandler implements ICommandHandler {

    private static Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Integer.valueOf(512);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        try {
            JTT808Packet e = (JTT808Packet) raw;
            RawBody rawBody = (RawBody) e.getBody();
            JTT808Packet packet = new JTT808Packet(new LocationInformationReportBody());
            packet.from(data);
            LocationInformationReportBody body8 = (LocationInformationReportBody) packet.getBody();
            LocationInformationBaseInfo locationBody = body8.getBaseInfo();
            LocationInformationAddonInfo addonInfo = body8.getAddonInfo();
            cmdLog.debug("位置信息 ：" + IoBuffer.wrap(data).getHexDump() + " --->" + locationBody + "m:" + addonInfo.getMileage());
            LocationInformationBaseInfo body = new LocationInformationBaseInfo();
            body.setLat(locationBody.getLat());
            body.setLng(locationBody.getLng());
            body.setSpeed(locationBody.getSpeed() * 10);
            body.setAngle(locationBody.getAngle());
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            GpsTime gpsTime = GpsTime.parse(sdformat.format(new Date()));
            body.setGpstime(gpsTime);
            body.setAlarms(locationBody.getAlarms());
            body.setAltitude(locationBody.getAltitude());
            body.setStatus(locationBody.getStatus());
            LocationInformationReportBody locationInformationReportBody = new LocationInformationReportBody();
            locationInformationReportBody.setBaseInfo(body);
            LocationInformationAddonInfo locationInformationAddonInfo = new LocationInformationAddonInfo();
            locationInformationAddonInfo.setMileage(addonInfo.getMileage());
            locationInformationReportBody.setAddonInfo(locationInformationAddonInfo);
            JTT808Packet reply = new JTT808Packet(locationInformationReportBody);
            reply.setBody(locationInformationReportBody);
            reply.setCommand(512);
            reply.setSerialNumber(JTT808Util.getSerialNumber());
            reply.setNumber(e.getNumber());
            reply.setPaging(false);
            reply.setEncrypting(false);
            byte[] datamsg = reply.array();
            DataSender.getCurrent().send(e.getNumber(), 1, "", "", datamsg);
        } catch (Exception var17) {
            ExcepPrinter.print(var17);
        }

    }

    public static void main(String[] args) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GpsTime gpsTime = GpsTime.parse(sdformat.format(new Date()));
        System.out.println(gpsTime);
        GpsTime gpsTime2 = GpsTime.parse("2014-4-2 9:2:11");
        System.out.println(gpsTime2);
    }
}
