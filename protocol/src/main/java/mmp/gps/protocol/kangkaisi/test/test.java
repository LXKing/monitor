package mmp.gps.protocol.kangkaisi.test;

import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket79;
import mmp.gps.protocol.kangkaisi.KangkaisiUtil;
import mmp.gps.protocol.kangkaisi.StringUtils;
import mmp.gps.protocol.kangkaisi.body.AlarmDataBody;
import mmp.gps.protocol.kangkaisi.body.AlarmDataMultiFenceBody;
import mmp.gps.protocol.kangkaisi.body.GPSAddressRequestBody;
import mmp.gps.protocol.kangkaisi.body.HeartbeatBody;
import mmp.gps.protocol.kangkaisi.body.LBSAddressRequestBody;
import mmp.gps.protocol.kangkaisi.body.LBSExtendBody;
import mmp.gps.protocol.kangkaisi.body.LocationBody;
import mmp.gps.protocol.kangkaisi.body.LoginBody;
import mmp.gps.protocol.kangkaisi.body.OnlineInstructionReplyBody;
import mmp.gps.protocol.kangkaisi.body.ServerReplyAddressRequestBody;
import mmp.gps.protocol.kangkaisi.body.ServerSendOnlineInstructionBody;
import mmp.gps.protocol.kangkaisi.body.StateCourse;

public class test {

    public static void main(String[] args) {
        AlarmDataBodytest();
    }

    public static void Login() {
        LoginBody loginBody = new LoginBody();
        loginBody.setNumberID("123456789123456");
        loginBody.setTimeZoneLanguage(800);
        loginBody.setTypeCode(110);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(loginBody);
        kangkaisiPacket.setProtocolNumber((short) 1);
        kangkaisiPacket.setSerialNumber(KangkaisiUtil.getSerialNumber());
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        LoginBody loginBody2 = new LoginBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(loginBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void LoginReply() {
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket();
        kangkaisiPacket.setProtocolNumber((short) 1);
        kangkaisiPacket.setSerialNumber(5);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket();

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void heartbeatBody() {
        HeartbeatBody heartbeatBody = new HeartbeatBody();
        HeartbeatBody.TerminalInformationc terminalInformationc = new HeartbeatBody.TerminalInformationc();
        terminalInformationc.setAcc(1);
        terminalInformationc.setCharge(1);
        terminalInformationc.setElectric(1);
        terminalInformationc.setGps(1);
        terminalInformationc.setIsFortification(1);
        heartbeatBody.setTerminalInformationContent((short) terminalInformationc.to());
        heartbeatBody.setVoltageLevel((short) 1);
        heartbeatBody.setGSM((short) 0);
        heartbeatBody.setLanguage(1);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(heartbeatBody);
        kangkaisiPacket.setProtocolNumber((short) 19);
        kangkaisiPacket.setSerialNumber(KangkaisiUtil.getSerialNumber());
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        HeartbeatBody heartbeatBody2 = new HeartbeatBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(heartbeatBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
        HeartbeatBody.TerminalInformationc terminalInformationc2 = new HeartbeatBody.TerminalInformationc();
        terminalInformationc2.from(((HeartbeatBody) kangkaisiPacket2.getBody()).getTerminalInformationContent());
        System.out.println(terminalInformationc2);
    }

    public static void heartbeatReply() {
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket();
        kangkaisiPacket.setProtocolNumber((short) 19);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket();

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void LocationBodyTest() {
        LocationBody locationBody2 = new LocationBody();
        byte[] b = new byte[]{(byte) 120, (byte) 120, (byte) 34, (byte) 34, (byte) 18, (byte) 5, (byte) 3, (byte) 6, (byte) 28, (byte) 57, (byte) -56, (byte) 2, (byte) 124, (byte) 92, (byte) 92, (byte) 12, (byte) 43, (byte) 106, (byte) -32, (byte) 0, (byte) 20, (byte) 35, (byte) 1, (byte) -52, (byte) 1, (byte) 81, (byte) 122, (byte) 0, (byte) -74, (byte) -16, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 118, (byte) 84, (byte) -10, (byte) 13, (byte) 10};
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(locationBody2);

        try {
            kangkaisiPacket2.from(b);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.out.println(locationBody2);
        StateCourse stateCourse2 = new StateCourse();
        System.out.println(kangkaisiPacket2);
        stateCourse2.from(((LocationBody) kangkaisiPacket2.getBody()).getState());
        System.out.println(stateCourse2);
    }

    public static void LBSExtendtest() {
        LBSExtendBody lbsExtend = new LBSExtendBody();
        lbsExtend.setTime("0F0C1D023305");
        lbsExtend.setMCC(1);
        lbsExtend.setMNC((short) 2);
        lbsExtend.setLAC(3);
        lbsExtend.setCI(4);
        lbsExtend.setRSSI((short) 5);
        lbsExtend.setNLAC1(6);
        lbsExtend.setNCI1(7);
        lbsExtend.setNRSSI1((short) 8);
        lbsExtend.setNLAC2(9);
        lbsExtend.setNCI2(10);
        lbsExtend.setMCCNRSSI2((short) 11);
        lbsExtend.setNLAC3(12);
        lbsExtend.setNCI3(12);
        lbsExtend.setNRSSI3((short) 13);
        lbsExtend.setNLAC4(14);
        lbsExtend.setNCI4(15);
        lbsExtend.setNRSSI4((short) 16);
        lbsExtend.setNLAC5(17);
        lbsExtend.setNCI5(18);
        lbsExtend.setNRSSI5((short) 19);
        lbsExtend.setNLAC6(20);
        lbsExtend.setNCI6(21);
        lbsExtend.setNRSSI6((short) 22);
        lbsExtend.setTimeLead((short) 23);
        lbsExtend.setLanguage(24);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(lbsExtend);
        kangkaisiPacket.setProtocolNumber((short) 40);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        LBSExtendBody lbsExtend2 = new LBSExtendBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(lbsExtend2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void AlarmDataBodytest() {
        AlarmDataBody alarmDataBody2 = new AlarmDataBody();
        byte[] bytes = new byte[]{(byte) 120, (byte) 120, (byte) 37, (byte) 38, (byte) 18, (byte) 5, (byte) 24, (byte) 9, (byte) 14, (byte) 3, (byte) -49, (byte) 4, (byte) 15, (byte) -115, (byte) -96, (byte) 12, (byte) 19, (byte) 40, (byte) 80, (byte) 43, (byte) 20, (byte) -79, (byte) 9, (byte) 1, (byte) -52, (byte) 1, (byte) 53, (byte) 5, (byte) 0, (byte) 90, (byte) -83, (byte) 70, (byte) 6, (byte) 4, (byte) 6, (byte) 0, (byte) 11, (byte) -43, (byte) 93, (byte) -118, (byte) 13, (byte) 10};
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(alarmDataBody2);

        try {
            kangkaisiPacket2.from(bytes);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.out.println(kangkaisiPacket2.getBody());
    }

    public static void AlarmDataMultiFenceBodytest() {
        AlarmDataMultiFenceBody alarmDataBody = new AlarmDataMultiFenceBody();
        alarmDataBody.setTime("0F0C1D023305");
        alarmDataBody.setGpsSum((short) 1);
        alarmDataBody.setLat(2L);
        alarmDataBody.setLng(3L);
        alarmDataBody.setSpeed((short) 4);
        alarmDataBody.setState(5);
        alarmDataBody.setLbs((short) 6);
        alarmDataBody.setMcc(7);
        alarmDataBody.setMnc((short) 8);
        alarmDataBody.setLac(9);
        alarmDataBody.setCell(10);
        alarmDataBody.setTerminalInformationContent((short) 11);
        alarmDataBody.setVoltageLevel((short) 12);
        alarmDataBody.setGSM((short) 13);
        alarmDataBody.setLanguage(14);
        alarmDataBody.setFencingNumber((short) 15);
        alarmDataBody.setMileage(16L);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(alarmDataBody);
        kangkaisiPacket.setProtocolNumber((short) 38);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        AlarmDataMultiFenceBody alarmDataBody2 = new AlarmDataMultiFenceBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(alarmDataBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void GPSAddressRequestBodyTest() {
        GPSAddressRequestBody gpsAddressRequestBody = new GPSAddressRequestBody();
        StateCourse stateCourse = new StateCourse();
        stateCourse.setGpsRealTime(0);
        stateCourse.setIsGps(1);
        stateCourse.setThrough(0);
        stateCourse.setWeft(1);
        stateCourse.setCourse(332);
        gpsAddressRequestBody.setTime("0F0C1D023305");
        gpsAddressRequestBody.setGpsSum((short) 1);
        gpsAddressRequestBody.setLat(3600000L);
        gpsAddressRequestBody.setLng(225000000L);
        gpsAddressRequestBody.setSpeed((short) 10);
        gpsAddressRequestBody.setState(stateCourse.to());
        gpsAddressRequestBody.setTelephone("123456789987");
        gpsAddressRequestBody.setLanguage(12);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(gpsAddressRequestBody);
        kangkaisiPacket.setProtocolNumber((short) 42);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        GPSAddressRequestBody locationBody2 = new GPSAddressRequestBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(locationBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        StateCourse stateCourse2 = new StateCourse();
        System.out.println(kangkaisiPacket2);
        stateCourse2.from(((GPSAddressRequestBody) kangkaisiPacket2.getBody()).getState());
        System.out.println(stateCourse2);
    }

    public static void LBSAddressRequestBodyTest() {
        LBSAddressRequestBody lbsAddressRequestBody = new LBSAddressRequestBody();
        lbsAddressRequestBody.setMcc(1);
        lbsAddressRequestBody.setMnc((short) 2);
        lbsAddressRequestBody.setLac(3);
        lbsAddressRequestBody.setCell(4);
        lbsAddressRequestBody.setTelephone("123456789987");
        lbsAddressRequestBody.setLanguage(12);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(lbsAddressRequestBody);
        kangkaisiPacket.setProtocolNumber((short) 23);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        LBSAddressRequestBody lbsAddressRequestBody2 = new LBSAddressRequestBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(lbsAddressRequestBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void ServerSendOnlineInstructionBodyTest() {
        ServerSendOnlineInstructionBody serverReplyOnlineInstructionBody = new ServerSendOnlineInstructionBody();
        serverReplyOnlineInstructionBody.setServerFlag(0L);
        serverReplyOnlineInstructionBody.setInstructionContent("STATUS#");
        serverReplyOnlineInstructionBody.setLanguage(1);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(serverReplyOnlineInstructionBody);
        kangkaisiPacket.setProtocolNumber((short) 128);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        ServerSendOnlineInstructionBody serverReplyOnlineInstructionBody2 = new ServerSendOnlineInstructionBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(serverReplyOnlineInstructionBody2);

        try {
            kangkaisiPacket2.from(kangkaisiPacket.array());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(kangkaisiPacket2);
    }

    public static void OnlineInstructionReplyBodyTest() {
        OnlineInstructionReplyBody onlineInstructionReplyBody = new OnlineInstructionReplyBody();
        onlineInstructionReplyBody.setCode((short) 1);
        onlineInstructionReplyBody.setServerFlag(2L);
        onlineInstructionReplyBody.setContent("Battery:3.89V,NORMAL; GPRS:Link Up; GSM Signal Level:Strong; GPS:OFF; Defense:ON");
        KangkaisiPacket79 kangkaisiPacket = new KangkaisiPacket79(onlineInstructionReplyBody);
        kangkaisiPacket.setProtocolNumber((short) 33);
        kangkaisiPacket.setSerialNumber(13);
        System.out.println(StringUtils.buff2HexAsciiString(kangkaisiPacket.array()));
        OnlineInstructionReplyBody onlineInstructionReplyBody2 = new OnlineInstructionReplyBody();
        KangkaisiPacket79 kangkaisiPacket2 = new KangkaisiPacket79(onlineInstructionReplyBody2);
        kangkaisiPacket2.from(kangkaisiPacket.array());
        System.out.println(kangkaisiPacket2);
    }

    public static void ServerReplyAddressRequestBodytest() {
        byte[] b = new byte[]{(byte) 120, (byte) 120, (byte) 110, (byte) 23, (byte) 104, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 65, (byte) 68, (byte) 68, (byte) 82, (byte) 69, (byte) 83, (byte) 83, (byte) 38, (byte) 38, (byte) 79, (byte) 77, (byte) 127, (byte) 110, (byte) 0, (byte) 58, (byte) 94, (byte) 127, (byte) 78, (byte) 28, (byte) 119, (byte) 1, (byte) 0, (byte) 46, (byte) 96, (byte) -32, (byte) 93, (byte) -34, (byte) 94, (byte) 2, (byte) 0, (byte) 46, (byte) 96, (byte) -32, (byte) 87, (byte) -50, (byte) 83, (byte) 58, (byte) 0, (byte) 46, (byte) 78, (byte) -111, (byte) 92, (byte) 113, (byte) -119, (byte) 127, (byte) -115, (byte) -17, (byte) 0, (byte) 46, (byte) 121, (byte) -69, (byte) 96, (byte) -32, (byte) 93, (byte) -34, (byte) 94, (byte) 2, (byte) 91, (byte) 102, (byte) 89, (byte) 39, (byte) 101, (byte) 89, (byte) -128, (byte) -78, (byte) 126, (byte) -90, (byte) 0, (byte) 50, (byte) 0, (byte) 53, (byte) 124, (byte) 115, (byte) 0, (byte) 46, (byte) 38, (byte) 38, (byte) 56, (byte) 54, (byte) 49, (byte) 51, (byte) 52, (byte) 50, (byte) 49, (byte) 54, (byte) 51, (byte) 50, (byte) 54, (byte) 57, (byte) 57, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 35, (byte) 35, (byte) 0, (byte) 22, (byte) -63, (byte) -20, (byte) 13, (byte) 10};
        ServerReplyAddressRequestBody serverReplyOnlineInstructionBody = new ServerReplyAddressRequestBody();
        KangkaisiPacket kangkaisiPacket2 = new KangkaisiPacket(serverReplyOnlineInstructionBody);

        try {
            kangkaisiPacket2.from(b);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.out.println(serverReplyOnlineInstructionBody);
    }

    public static void test() {
        String s = "STATUS#";
        System.out.println(StringUtils.buff2HexAsciiString(s.getBytes()));
    }
}
