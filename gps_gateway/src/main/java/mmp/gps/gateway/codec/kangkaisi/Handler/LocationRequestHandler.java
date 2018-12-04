package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.codec.kangkaisi.beans.MemMsInfo;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.SessionManager;
import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.jtt808.body.GpsTime;
import mmp.gps.protocol.jtt808.body.LocationInformationAddonInfo;
import mmp.gps.protocol.jtt808.body.LocationInformationBaseInfo;
import mmp.gps.protocol.jtt808.body.LocationInformationReportBody;
import mmp.gps.protocol.jtt808.definition.StatePosition;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.LocationBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import mmp.gps.protocol.kangkaisi.body.StateCourse;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class LocationRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 34);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        try {
            KangkaisiPacket e = (KangkaisiPacket) raw;
            MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
            RawBody rawBody = (RawBody) e.getBody();
            LocationBody locationBody = new LocationBody();
            locationBody.from(rawBody.getRaw());
            this.cmdLog.debug("定位：" + IoBuffer.wrap(data).getHexDump() + "---数据---" + locationBody + "=>" + memMsInfo.getId());
            LocationInformationBaseInfo body = new LocationInformationBaseInfo();
            body.setLat((long) ((double) ((float) locationBody.getLat()) / 1.8D));
            body.setLng((long) ((double) ((float) locationBody.getLng()) / 1.8D));
            body.setSpeed(locationBody.getSpeed() * 10);
            StateCourse stateCourse = new StateCourse();
            stateCourse.from(locationBody.getState());
            body.setAngle(stateCourse.getCourse());
            GpsTime gpsTime = GpsTime.parse(locationBody.getTime().toString());
            body.setGpstime(gpsTime);
            body.setAlarms(0L);
            body.setAltitude(0);
            StatePosition statePosition = new StatePosition();
            statePosition.setAcc(locationBody.getAcc());
            statePosition.setLat(stateCourse.getWeft());
            statePosition.setLng(stateCourse.getThrough());
            statePosition.setLocation(stateCourse.getIsGps());
            statePosition.setGps(stateCourse.getGpsRealTime());
            body.setStatus((long) statePosition.to());
            LocationInformationAddonInfo locationInformationAddonInfo = new LocationInformationAddonInfo();
            locationInformationAddonInfo.setNetworkSignal(Short.valueOf((short) (memMsInfo.getGSM() * 6)));
            if (locationBody.getMileageStatistics() != null) {
                locationInformationAddonInfo.setMileage(Long.valueOf(Math.round((double) locationBody.getMileageStatistics().longValue() * 0.01D)));
            }

            locationInformationAddonInfo.setSatellites(Short.valueOf((short) locationBody.getGpsS()));
            LocationInformationReportBody locationInformationReportBody = new LocationInformationReportBody();
            locationInformationReportBody.setBaseInfo(body);
            locationInformationReportBody.setAddonInfo(locationInformationAddonInfo);
            JTT808Packet reply = new JTT808Packet(locationInformationReportBody);
            reply.setBody(locationInformationReportBody);
            reply.setCommand(512);
            reply.setSerialNumber(JTT808Util.getSerialNumber());
            reply.setNumber(memMsInfo.getId());
            reply.setPaging(false);
            reply.setEncrypting(false);
            byte[] datamsg = reply.array();
            this.cmdLog.debug("转换：" + IoBuffer.wrap(datamsg).getHexDump());
            datamsg = JTT808Util.antisense(datamsg);
            DataSender.getCurrent().send(memMsInfo.getId(), 1, "", "", datamsg);
        } catch (Exception var16) {
            ExcepPrinter.print(var16);
        }

    }

    public static void main(String[] args) {
        double d = 176.87D;
        long l = Math.round(d);
        System.out.println(" l " + l);
    }
}
