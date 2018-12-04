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
import mmp.gps.protocol.jtt808.definition.Alarm;
import mmp.gps.protocol.jtt808.definition.StatePosition;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.AlarmDataBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import mmp.gps.protocol.kangkaisi.body.StateCourse;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class AlarmDataRequestHandler implements ICommandHandler {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 38);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        try {
            KangkaisiPacket e = (KangkaisiPacket) raw;
            MemMsInfo memMsInfo = (MemMsInfo) SessionManager.getCurrent().getid(session);
            RawBody rawBody = (RawBody) e.getBody();
            AlarmDataBody alarmDataBody = new AlarmDataBody();
            alarmDataBody.from(rawBody.getRaw());
            this.cmdLog.debug("报警：" + IoBuffer.wrap(data).getHexDump() + "---数据---" + alarmDataBody + " => " + memMsInfo.getId());
            LocationInformationBaseInfo body = new LocationInformationBaseInfo();
            body.setLat((long) ((double) ((float) alarmDataBody.getLat()) / 1.8D));
            body.setLng((long) ((double) ((float) alarmDataBody.getLng()) / 1.8D));
            body.setSpeed(alarmDataBody.getSpeed() * 10);
            StateCourse stateCourse = new StateCourse();
            stateCourse.from(alarmDataBody.getState());
            body.setAngle(stateCourse.getCourse());
            GpsTime gpsTime = GpsTime.parse(alarmDataBody.getTime().toString());
            body.setGpstime(gpsTime);
            body.setAlarms(0L);
            body.setAltitude(0);
            Alarm alarm = new Alarm();
            if (alarmDataBody.getAlarm() != 0) {
                if (alarmDataBody.getAlarm() == 1) {
                    alarm.setUrgent(1);
                } else if (alarmDataBody.getAlarm() == 2) {
                    alarm.setMainPowerSupply(1);
                } else if (alarmDataBody.getAlarm() != 3) {
                    if (alarmDataBody.getAlarm() == 4) {
                        alarm.setImportAndExportArea(1);
                    } else if (alarmDataBody.getAlarm() == 5) {
                        alarm.setImportAndExportArea(1);
                    } else if (alarmDataBody.getAlarm() == 6) {
                        alarm.setSpeeding(1);
                    } else if (alarmDataBody.getAlarm() != 9 && alarmDataBody.getAlarm() != 10 && alarmDataBody.getAlarm() != 11 && alarmDataBody.getAlarm() != 12 && alarmDataBody.getAlarm() != 13 && alarmDataBody.getAlarm() != 14 && alarmDataBody.getAlarm() != 15 && alarmDataBody.getAlarm() != 16 && alarmDataBody.getAlarm() != 17 && alarmDataBody.getAlarm() != 18 && alarmDataBody.getAlarm() != 19 && alarmDataBody.getAlarm() != 20 && alarmDataBody.getAlarm() != 21 && alarmDataBody.getAlarm() != 22 && alarmDataBody.getAlarm() != 23 && alarmDataBody.getAlarm() != 255) {
                        alarmDataBody.getAlarm();
                    }
                }
            }

            body.setAlarms((long) alarmDataBody.getAlarm());
            StatePosition statePosition = new StatePosition();
            statePosition.setAcc(alarmDataBody.getTerminalInformationContentclass().getAcc());
            statePosition.setLat(stateCourse.getWeft());
            statePosition.setLng(stateCourse.getThrough());
            statePosition.setLocation(stateCourse.getIsGps());
            statePosition.setGps(stateCourse.getGpsRealTime());
            body.setStatus((long) statePosition.to());
            this.cmdLog.debug("1");
            LocationInformationAddonInfo locationInformationAddonInfo = new LocationInformationAddonInfo();
            locationInformationAddonInfo.setNetworkSignal(Short.valueOf((short) (alarmDataBody.getGsm() * 6)));
            if (alarmDataBody.getMileage() != null) {
                locationInformationAddonInfo.setMileage(alarmDataBody.getMileage());
            }

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
            datamsg = JTT808Util.antisense(datamsg);
            this.cmdLog.debug("转换：" + IoBuffer.wrap(datamsg).getHexDump());
            DataSender.getCurrent().send(memMsInfo.getId(), 1, "", "", datamsg);
        } catch (Exception var17) {
            ExcepPrinter.print(var17);
        }

    }
}
