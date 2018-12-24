package mmp.gps.gateway.test;

import com.alibaba.fastjson.JSON;
import com.ning.http.util.Base64;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.common.util.StringUtils;
import mmp.gps.gateway.codec.kangkaisi.beans.baidu;
import mmp.gps.gateway.codec.kangkaisi.kangkaisiProtocolCodecFactory;
import mmp.gps.gateway.domain.DataSender;
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
import mmp.gps.protocol.kangkaisi.body.LocationBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import mmp.gps.protocol.kangkaisi.body.StateCourse;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        testL();
    }

    private static void baiduTest() {
        String result = "";
        baidu baidu = (baidu) JSON.parseObject(result, baidu.class);
        System.out.println(baidu);
    }

    private static void baidu() {
        String lat = "23.168982";
        String lng = "113.429244";
        String s = DataSender.getCurrent().sendbaidu(lat, lng);
        System.out.println("s" + s);
    }

    public static void code() {
        String top = "eHgiIhIDEAUYNcwCfFtMDCtrcAAVBgHMAVF6ALbwAQABAm97EQ0K";
        byte[] iiii = Base64.decode(top);
        System.out.println("top" + StringUtils.byte2String(iiii));
    }

    public static void TCPClient() {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000L);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new kangkaisiProtocolCodecFactory(1024)));
        byte[] b = new byte[]{(byte) 120, (byte) 120, (byte) 34, (byte) 34, (byte) 18, (byte) 3, (byte) 16, (byte) 3, (byte) 44, (byte) 34, (byte) -55, (byte) 2, (byte) 124, (byte) 89, (byte) -48, (byte) 12, (byte) 43, (byte) 105, (byte) 80, (byte) 0, (byte) 20, (byte) 61, (byte) 1, (byte) -52, (byte) 1, (byte) 81, (byte) 122, (byte) 0, (byte) -70, (byte) 107, (byte) 1, (byte) 0, (byte) 1, (byte) 0, (byte) 74, (byte) 93, (byte) 114, (byte) 13, (byte) 10};
        connector.setHandler(new TCPClientHandler(b));
        connector.connect(new InetSocketAddress("localhost", 5808));
    }

    private static void strtext() {
        String para = "{\"SPEED\":[{\"set\":{\"A\":\"OFF\",\"B\":\"20秒\",\"C\":\"100km/h\",\"M\":\"1\"}}]}";
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SPEED");
        Iterator var4 = items.iterator();

        while (var4.hasNext()) {
            Map T = (Map) var4.next();
            Iterator var6 = T.keySet().iterator();

            while (var6.hasNext()) {
                String mapKey = (String) var6.next();
                switch (mapKey.hashCode()) {
                    case 106079:
                        if (mapKey.equals("key")) {
                            Object val = T.get(mapKey);
                            System.out.println(" T:" + val.toString());
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = T.get(mapKey);
                            System.out.println(" val2:" + val2);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object B = settings.get("B");
                            System.out.println(" A:" + A + " :: B" + B);
                        }
                }
            }
        }

        String T1 = map.get("SPEED").toString();
        System.out.println(" T:" + T1);
    }

    public static void testL() {
        byte[] b = new byte[]{(byte) 120, (byte) 120, (byte) 37, (byte) 38, (byte) 18, (byte) 5, (byte) 25, (byte) 1, (byte) 37, (byte) 36, (byte) -49, (byte) 4, (byte) 12, (byte) 109, (byte) 88, (byte) 12, (byte) 19, (byte) -118, (byte) -128, (byte) 41, (byte) 20, (byte) 93, (byte) 9, (byte) 1, (byte) -52, (byte) 1, (byte) 53, (byte) 8, (byte) 0, (byte) 81, (byte) -100, (byte) 70, (byte) 6, (byte) 4, (byte) 6, (byte) 0, (byte) 21, (byte) -21, (byte) -70, (byte) -101, (byte) 13, (byte) 10};
        alarm(b);
    }

    public static void l(byte[] data) {
        KangkaisiPacket packet = null;

        try {
            packet = new KangkaisiPacket();
            packet.setBody(new RawBody());
            packet.from(data);
        } catch (Exception var15) {
            ;
        }

        RawBody rawBody = (RawBody) packet.getBody();
        LocationBody locationBody = new LocationBody();
        locationBody.from(rawBody.getRaw());
        LocationInformationBaseInfo body = new LocationInformationBaseInfo();
        body.setLat((long) ((double) ((float) locationBody.getLat()) / 1.8D));
        body.setLng((long) ((double) ((float) locationBody.getLng()) / 1.8D));
        body.setSpeed(locationBody.getSpeed());
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
        locationInformationAddonInfo.setNetworkSignal(Short.valueOf((short) 24));
        if (locationBody.getMileageStatistics() != null) {
            locationInformationAddonInfo.setMileage(locationBody.getMileageStatistics());
        }

        locationInformationAddonInfo.setSatellites(Short.valueOf((short) locationBody.getGpsS()));
        LocationInformationReportBody locationInformationReportBody = new LocationInformationReportBody();
        locationInformationReportBody.setBaseInfo(body);
        locationInformationReportBody.setAddonInfo(locationInformationAddonInfo);
        JTT808Packet reply = new JTT808Packet(locationInformationReportBody);
        reply.setBody(locationInformationReportBody);
        reply.setCommand(512);
        reply.setSerialNumber(JTT808Util.getSerialNumber());
        reply.setNumber("120190994308");
        reply.setPaging(false);
        reply.setEncrypting(false);
        byte[] datamsg = reply.array();
        System.out.println("转换：" + IoBuffer.wrap(datamsg).getHexDump());
        datamsg = JTT808Util.antisense(datamsg);
        JTT808Packet packet1 = new JTT808Packet(new LocationInformationReportBody());

        try {
            packet1.from(datamsg);
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    public static void alarm(byte[] data) {
        KangkaisiPacket packet = null;

        try {
            packet = new KangkaisiPacket();
            packet.setBody(new RawBody());
            packet.from(data);
        } catch (Exception var13) {
            ;
        }

        RawBody rawBody = (RawBody) packet.getBody();
        AlarmDataBody alarmDataBody = new AlarmDataBody();
        alarmDataBody.from(rawBody.getRaw());
        LocationInformationBaseInfo body = new LocationInformationBaseInfo();
        body.setLat((long) ((double) ((float) alarmDataBody.getLat()) / 1.8D));
        body.setLng((long) ((double) ((float) alarmDataBody.getLng()) / 1.8D));
        body.setSpeed(alarmDataBody.getSpeed());
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

        body.setAlarms((long) alarm.to());
        StatePosition statePosition = new StatePosition();
        statePosition.setAcc(alarmDataBody.getTerminalInformationContentclass().getAcc());
        statePosition.setLat(stateCourse.getWeft());
        statePosition.setLng(stateCourse.getThrough());
        statePosition.setLocation(stateCourse.getIsGps());
        statePosition.setGps(stateCourse.getGpsRealTime());
        body.setStatus((long) statePosition.to());
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
        reply.setNumber("120190994308");
        reply.setPaging(false);
        reply.setEncrypting(false);
        byte[] datamsg = reply.array();
        datamsg = JTT808Util.antisense(datamsg);
        DataSender.getCurrent().send("120190994308", 1, "", "", datamsg);
    }
}
