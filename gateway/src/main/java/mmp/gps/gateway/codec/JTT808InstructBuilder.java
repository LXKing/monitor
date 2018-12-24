package mmp.gps.gateway.codec;

import mmp.gps.common.util.ByteIO;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.contract.IPacket;
import mmp.gps.gateway.Common;
import mmp.gps.gateway.contract.IInstructBuilder;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.common.util.CodecUtil;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.protocol.gbt19056.Gbt19056Packet;
import mmp.gps.protocol.jtt808.JTT808Packet;
import mmp.gps.protocol.jtt808.body.RawBody;
import mmp.gps.protocol.gbt19056.body.DrivingTime;
import mmp.gps.protocol.gbt19056.body.PulseBody;
import mmp.gps.protocol.gbt19056.body.ReadMileageReplyBody;
import mmp.gps.protocol.gbt19056.body.ReadRecordRequestBody;
import mmp.gps.protocol.gbt19056.body.ReadStatusSettingReplyBody;
import mmp.gps.protocol.gbt19056.body.VehicleInfoBody;
import mmp.gps.protocol.jtt808.body.CameraShootingImmediatelyRequestBody;
import mmp.gps.protocol.jtt808.body.CircularAreaInfo;
import mmp.gps.protocol.jtt808.body.CircularAreaRemoveBody;
import mmp.gps.protocol.jtt808.body.CircularAreaSetupBody;
import mmp.gps.protocol.jtt808.body.ConfirmAlarmBody;
import mmp.gps.protocol.jtt808.body.DrivingRecordInquiryRequestBody;
import mmp.gps.protocol.jtt808.body.DrivingRecordSetupBody;
import mmp.gps.protocol.jtt808.body.EventSetupBody;
import mmp.gps.protocol.jtt808.body.EventSetupInfo;
import mmp.gps.protocol.jtt808.body.GpsTime;
import mmp.gps.protocol.jtt808.body.InformationMenuSetupBody;
import mmp.gps.protocol.jtt808.body.InformationMenuSetupInfo;
import mmp.gps.protocol.jtt808.body.MultimediaDataOnceUploadBody;
import mmp.gps.protocol.jtt808.body.MultimediaDataRetrievalRequestBody;
import mmp.gps.protocol.jtt808.body.MultimediaDataTimespanUploadBody;
import mmp.gps.protocol.jtt808.body.PhoneCallBackBody;
import mmp.gps.protocol.jtt808.body.PolygonAreaPointInfo;
import mmp.gps.protocol.jtt808.body.PolygonAreaRemoveBody;
import mmp.gps.protocol.jtt808.body.PolygonAreaSetupBody;
import mmp.gps.protocol.jtt808.body.QuestionAnswerInfo;
import mmp.gps.protocol.jtt808.body.QuestionRequestBody;
import mmp.gps.protocol.jtt808.body.RecordingBody;
import mmp.gps.protocol.jtt808.body.RectangularAreaInfo;
import mmp.gps.protocol.jtt808.body.RectangularAreaRemoveBody;
import mmp.gps.protocol.jtt808.body.RectangularAreaSetupBody;
import mmp.gps.protocol.jtt808.body.RoutePointInfo;
import mmp.gps.protocol.jtt808.body.RouteRemoveBody;
import mmp.gps.protocol.jtt808.body.RouteSetupBody;
import mmp.gps.protocol.jtt808.body.TelephoneInfo;
import mmp.gps.protocol.jtt808.body.TelephoneSetupBody;
import mmp.gps.protocol.jtt808.body.TemporaryPositionTrackingControlBody;
import mmp.gps.protocol.jtt808.body.TerminalControlRequestBody;
import mmp.gps.protocol.jtt808.body.TerminalLinkServerInfo;
import mmp.gps.protocol.jtt808.body.TerminalParameterInfo;
import mmp.gps.protocol.jtt808.body.TerminalParametersSetBody;
import mmp.gps.protocol.jtt808.body.TerminalQuerySpecificParametersBody;
import mmp.gps.protocol.jtt808.body.TerminalWirelessUpgradeInfo;
import mmp.gps.protocol.jtt808.body.TextMessageIssuedBody;
import mmp.gps.protocol.jtt808.body.VehicleControlRequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JTT808InstructBuilder implements IInstructBuilder {

    public int getProtocolKind() {
        return 1;
    }

    public String getName() {
        return "JTT808指令生成器";
    }

    public byte[] build(String platformSerialNumber, String deviceNumber, String command, String para, ReplyValidator validator) {
        int deviceSerialNumber = Common.getSerialNumber();
        Object instruct = null;
        int key = Integer.valueOf(command, 16).intValue();
        boolean putvalidator = true;
        byte[] instruct1;
        switch (key) {
            case '\u8103':
                instruct1 = this.makeSetTerminalParameters(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8105':
                instruct1 = this.makeTerminalControlRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8106':
                instruct1 = this.makeQueryTerminalSpecificParameters(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8107':
                instruct1 = this.makeWithoutPara(deviceSerialNumber, deviceNumber, key);
                validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                putvalidator = false;
                break;
            case '\u8202':
                instruct1 = this.makeTemporaryPositionTrackingControl(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8203':
                instruct1 = this.makeConfirmAlarm(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8300':
                instruct1 = this.makeTextMessageIssued(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8301':
                instruct1 = this.makeEventSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8302':
                instruct1 = this.makeQuestionRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8303':
                instruct1 = this.makeInformationMenuSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8400':
                instruct1 = this.makePhoneCallBack(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8401':
                instruct1 = this.makeTelephoneSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8500':
                instruct1 = this.makeVehicleControlRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8600':
                instruct1 = this.makeCircularAreaSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8601':
                instruct1 = this.makeCircularAreaRemove(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8602':
                instruct1 = this.makeRectangularAreaStup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8603':
                instruct1 = this.makeRectangularAreaRemove(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8604':
                instruct1 = this.makePolygonAreaSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8605':
                instruct1 = this.makePolygonAreaRemove(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8606':
                instruct1 = this.makeRouteSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8607':
                instruct1 = this.makeRouteRemove(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8700':
                instruct1 = this.makeDrivingRecordInquiryRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8701':
                instruct1 = this.makeDrivingRecordSetup(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8702':
                instruct1 = this.makeWithoutPara(deviceSerialNumber, deviceNumber, key);
                validator.put(deviceNumber + 1794, new InstructInfo(platformSerialNumber, command));
                putvalidator = false;
                break;
            case '\u8801':
                instruct1 = this.makeCameraShootingImmediatelyRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8802':
                instruct1 = this.makeMultimediaDataRetrievalRequest(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8803':
                instruct1 = this.makeMultimediaDataTimespanUpload(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8804':
                instruct1 = this.makeRecording(deviceSerialNumber, deviceNumber, key, para);
                break;
            case '\u8805':
                instruct1 = this.makeMultimediaDataOnceUpload(deviceSerialNumber, deviceNumber, key, para);
                break;
            default:
                instruct1 = this.makeWithoutPara(deviceSerialNumber, deviceNumber, key);
        }

        if (putvalidator) {
            validator.put(deviceNumber + deviceSerialNumber, new InstructInfo(platformSerialNumber, command));
        }

        return instruct1;
    }

    private byte[] makeDrivingRecordSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        DrivingRecordSetupBody body = new DrivingRecordSetupBody();
        String p = map.get("command").toString();
        short cmm = Short.parseShort(p, 16);
        body.setCommand(cmm);
        Object drBody = null;
        switch (cmm) {
            case 130:
                VehicleInfoBody gbt4 = new VehicleInfoBody();
                p = map.get("vehicleIdcode").toString();
                gbt4.setVehicleIdcode(p);
                p = map.get("plateNumber").toString();
                gbt4.setPlateNumber(p);
                p = map.get("category").toString();
                gbt4.setCategory(p);
                drBody = gbt4;
                break;
            case 131:
            case 194:
                p = map.get("time").toString();
                DrivingTime gbt3 = DrivingTime.parse(p);
                drBody = gbt3;
                break;
            case 132:
                ReadStatusSettingReplyBody gbt2 = new ReadStatusSettingReplyBody();
                p = map.get("name0").toString();
                gbt2.setName0(p);
                p = map.get("name1").toString();
                gbt2.setName1(p);
                p = map.get("name2").toString();
                gbt2.setName2(p);
                p = map.get("name3").toString();
                gbt2.setName3(p);
                p = map.get("name4").toString();
                gbt2.setName4(p);
                p = map.get("name5").toString();
                gbt2.setName5(p);
                p = map.get("name6").toString();
                gbt2.setName6(p);
                p = map.get("name7").toString();
                gbt2.setName7(p);
                drBody = gbt2;
                break;
            case 195:
                PulseBody gbt1 = new PulseBody();
                p = map.get("pulse").toString();
                gbt1.setCoefficient(Integer.parseInt(p));
                drBody = gbt1;
                break;
            case 196:
                ReadMileageReplyBody gbt = new ReadMileageReplyBody();
                p = map.get("mileage").toString();
                double packet = Double.parseDouble(p) * 10.0D;
                long mileage1 = (long) packet;
                String bcd = String.valueOf(mileage1);
                long mileage2 = Long.parseLong(bcd, 16);
                gbt.setInitialMileage(mileage2);
                drBody = gbt;
        }

        Gbt19056Packet gbt5 = new Gbt19056Packet();
        gbt5.setBody((IPacket) drBody);
        gbt5.setCommand(cmm);
        gbt5.setHeader('\uaa75');
        body.setCommand(cmm);
        body.setData(gbt5.array());
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeDrivingRecordInquiryRequest(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        DrivingRecordInquiryRequestBody body = new DrivingRecordInquiryRequestBody();
        String p = map.get("command").toString();
        short cmm = Short.parseShort(p, 16);
        body.setCommand(cmm);
        if (map.containsKey("start") && map.containsKey("end") && map.containsKey("blocks")) {
            ReadRecordRequestBody packet = new ReadRecordRequestBody();
            p = map.get("start").toString();
            packet.setStart(DrivingTime.parse(p));
            p = map.get("end").toString();
            packet.setEnd(DrivingTime.parse(p));
            p = map.get("blocks").toString();
            packet.setBlocks(Integer.parseInt(p));
            Gbt19056Packet gbt = new Gbt19056Packet(packet);
            gbt.setCommand(cmm);
            gbt.setHeader('\uaa75');
            body.setData(gbt.array());
        }

        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeRouteSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        RouteSetupBody body = new RouteSetupBody();
        String p = map.get("areaId").toString();
        long id = Long.parseLong(p);
        body.setId(id);
        p = map.get("flag").toString();
        int flag = Integer.parseInt(p);
        body.setFlag(flag);
        p = map.get("startTime").toString();
        body.setStartTime(GpsTime.parse(p));
        p = map.get("endTime").toString();
        body.setEndTime(GpsTime.parse(p));
        ArrayList sections = new ArrayList();
        List items = (List) map.get("sections");
        Iterator var14 = items.iterator();

        while (var14.hasNext()) {
            Map packet = (Map) var14.next();
            RoutePointInfo info = new RoutePointInfo();
            p = packet.get("pointId").toString();
            long pointId = Long.parseLong(p);
            info.setPointId(pointId);
            p = packet.get("sectionId").toString();
            long sectionId = Long.parseLong(p);
            info.setSectionId(sectionId);
            p = packet.get("lat").toString();
            double lat = Double.parseDouble(p);
            info.setLat((long) (lat * 1000000.0D));
            p = packet.get("lng").toString();
            double lng = Double.parseDouble(p);
            info.setLng((long) (lng * 1000000.0D));
            p = packet.get("width").toString();
            short width = Short.parseShort(p);
            info.setWidth(width);
            p = packet.get("width").toString();
            short attr = Short.parseShort(p);
            info.setFlag(attr);
            p = packet.get("maxTime").toString();
            int maxTime = Integer.parseInt(p);
            info.setMaxTime(maxTime);
            p = packet.get("minTime").toString();
            int minTime = Integer.parseInt(p);
            info.setMinTime(minTime);
            p = packet.get("maxSpeed").toString();
            int maxSpeed = Integer.parseInt(p);
            info.setMaxSpeed(maxSpeed);
            p = packet.get("overspeedSeconds").toString();
            short overSpeedSeconds = Short.parseShort(p);
            info.setOverspeedSeconds(overSpeedSeconds);
            sections.add(info);
        }

        body.setPoints(sections);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeRouteRemove(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        RouteRemoveBody body = new RouteRemoveBody();
        ArrayList list = new ArrayList();
        List items = (List) map.get("list");
        Iterator var10 = items.iterator();

        while (var10.hasNext()) {
            String packet = (String) var10.next();
            Long id = Long.valueOf(Long.parseLong(packet));
            list.add(id);
        }

        body.setIdList(list);
        body.setTotal((byte) list.size());
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makePolygonAreaSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        PolygonAreaSetupBody body = new PolygonAreaSetupBody();
        String p = map.get("areaId").toString();
        long id = Long.parseLong(p);
        body.setId(id);
        p = map.get("flag").toString();
        int flag = Integer.parseInt(p);
        body.setFlag(flag);
        p = map.get("startTime").toString();
        body.setStartTime(GpsTime.parse(p));
        p = map.get("endTime").toString();
        body.setEndTime(GpsTime.parse(p));
        p = map.get("maxSpeed").toString();
        int maxSpeed = Integer.parseInt(p);
        body.setMaxSpeed(maxSpeed);
        p = map.get("overspeedSeconds").toString();
        short overspeedSeconds = Short.parseShort(p);
        body.setOverspeedSeconds(overspeedSeconds);
        ArrayList points = new ArrayList();
        List items = (List) map.get("points");
        Iterator var16 = items.iterator();

        while (var16.hasNext()) {
            Map packet = (Map) var16.next();
            PolygonAreaPointInfo info = new PolygonAreaPointInfo();
            p = packet.get("lat").toString();
            double lat = Double.parseDouble(p);
            info.setLat((long) (lat * 1000000.0D));
            p = packet.get("lng").toString();
            double lng = Double.parseDouble(p);
            info.setLng((long) (lng * 1000000.0D));
            points.add(info);
        }

        body.setPoints(points);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makePolygonAreaRemove(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        PolygonAreaRemoveBody body = new PolygonAreaRemoveBody();
        ArrayList list = new ArrayList();
        List items = (List) map.get("list");
        Iterator var10 = items.iterator();

        while (var10.hasNext()) {
            String packet = (String) var10.next();
            Long id = Long.valueOf(Long.parseLong(packet));
            list.add(id);
        }

        body.setIdList(list);
        body.setTotal((byte) list.size());
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeRectangularAreaStup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        RectangularAreaSetupBody body = new RectangularAreaSetupBody();
        String p = map.get("action").toString();
        byte action = Byte.parseByte(p);
        body.setAction(action);
        ArrayList areas = new ArrayList();
        List items = (List) map.get("areas");
        Iterator var12 = items.iterator();

        while (var12.hasNext()) {
            Map packet = (Map) var12.next();
            RectangularAreaInfo info = new RectangularAreaInfo();
            p = packet.get("areaId").toString();
            long id = Long.parseLong(p);
            info.setId(id);
            p = packet.get("flag").toString();
            int flag = Integer.parseInt(p);
            info.setFlag(flag);
            p = packet.get("ulLat").toString();
            double ulLat = Double.parseDouble(p);
            info.setUlLat((long) (ulLat * 1000000.0D));
            p = packet.get("ulLng").toString();
            double ulLng = Double.parseDouble(p);
            info.setUlLng((long) (ulLng * 1000000.0D));
            p = packet.get("brLat").toString();
            double brLat = Double.parseDouble(p);
            info.setUlLat((long) (brLat * 1000000.0D));
            p = packet.get("brLng").toString();
            double brLng = Double.parseDouble(p);
            info.setUlLng((long) (brLng * 1000000.0D));
            p = packet.get("startTime").toString();
            info.setStartTime(GpsTime.parse(p));
            p = packet.get("endTime").toString();
            info.setEndTime(GpsTime.parse(p));
            p = packet.get("maxSpeed").toString();
            int maxSpeed = Integer.parseInt(p);
            info.setMaxSpeed(maxSpeed);
            p = packet.get("overspeedSeconds").toString();
            short overspeedSeconds = Short.parseShort(p);
            info.setOverSpeedSeconds(overspeedSeconds);
            areas.add(info);
        }

        body.setAreas(areas);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeRectangularAreaRemove(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        RectangularAreaRemoveBody body = new RectangularAreaRemoveBody();
        ArrayList list = new ArrayList();
        List items = (List) map.get("list");
        Iterator var10 = items.iterator();

        while (var10.hasNext()) {
            String packet = (String) var10.next();
            Long id = Long.valueOf(Long.parseLong(packet));
            list.add(id);
        }

        body.setIdList(list);
        body.setTotal((byte) list.size());
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeCircularAreaSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        CircularAreaSetupBody body = new CircularAreaSetupBody();
        String p = map.get("action").toString();
        byte action = Byte.parseByte(p);
        body.setAction(action);
        ArrayList areas = new ArrayList();
        List items = (List) map.get("areas");
        Iterator var12 = items.iterator();

        while (var12.hasNext()) {
            Map packet = (Map) var12.next();
            CircularAreaInfo info = new CircularAreaInfo();
            p = packet.get("areaId").toString();
            long id = Long.parseLong(p);
            info.setId(id);
            p = packet.get("flag").toString();
            int flag = Integer.parseInt(p);
            info.setFlag(flag);
            p = packet.get("lat").toString();
            double lat = Double.parseDouble(p);
            info.setCenterLat((long) (lat * 1000000.0D));
            p = packet.get("lng").toString();
            double lng = Double.parseDouble(p);
            info.setCenterLng((long) (lng * 1000000.0D));
            p = packet.get("radius").toString();
            long radius = Long.parseLong(p);
            info.setRadius(radius);
            p = packet.get("startTime").toString();
            info.setStartTime(GpsTime.parse(p));
            p = packet.get("endTime").toString();
            info.setEndTime(GpsTime.parse(p));
            p = packet.get("maxSpeed").toString();
            int maxSpeed = Integer.parseInt(p);
            info.setMaxSpeed(maxSpeed);
            p = packet.get("overspeedSeconds").toString();
            short overspeedSeconds = Short.parseShort(p);
            info.setOverspeedSeconds(overspeedSeconds);
            areas.add(info);
        }

        body.setAreas(areas);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeCircularAreaRemove(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        CircularAreaRemoveBody body = new CircularAreaRemoveBody();
        ArrayList list = new ArrayList();
        List items = (List) map.get("list");
        Iterator var10 = items.iterator();

        while (var10.hasNext()) {
            String packet = (String) var10.next();
            Long id = Long.valueOf(Long.parseLong(packet));
            list.add(id);
        }

        body.setIdList(list);
        body.setTotal((byte) list.size());
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeMultimediaDataOnceUpload(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        MultimediaDataOnceUploadBody body = new MultimediaDataOnceUploadBody();
        String p = map.get("mediaId").toString();
        long mediaId = Long.parseLong(p);
        body.setMediaId(mediaId);
        p = map.get("action").toString();
        byte action = Byte.parseByte(p);
        body.setAction(action);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeTelephoneSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        TelephoneSetupBody body = new TelephoneSetupBody();
        String p = map.get("type").toString();
        byte type = Byte.parseByte(p);
        body.setType(type);
        ArrayList phones = new ArrayList();
        List items = (List) map.get("list");
        Iterator var12 = items.iterator();

        while (var12.hasNext()) {
            Map packet = (Map) var12.next();
            TelephoneInfo info = new TelephoneInfo();
            p = packet.get("flag").toString();
            byte flag = Byte.parseByte(p);
            info.setFlag(flag);
            p = packet.get("phone").toString();
            info.setPhone(p);
            p = packet.get("name").toString();
            info.setName(p);
            phones.add(info);
        }

        body.setList(phones);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeInformationMenuSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        InformationMenuSetupBody body = new InformationMenuSetupBody();
        String p = map.get("type").toString();
        short type = Short.parseShort(p);
        body.setType(type);
        ArrayList menus = new ArrayList();
        List items = (List) map.get("menus");
        Iterator var12 = items.iterator();

        while (var12.hasNext()) {
            Map packet = (Map) var12.next();
            InformationMenuSetupInfo info = new InformationMenuSetupInfo();
            p = packet.get("id").toString();
            short id = Short.parseShort(p);
            info.setId(id);
            p = packet.get("content").toString();
            info.setContent(p);
            menus.add(info);
        }

        body.setMenus(menus);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeQuestionRequest(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        QuestionRequestBody body = new QuestionRequestBody();
        List flags = (List) map.get("flag");
        long flag = CodecUtil.bitSwitch(flags);
        body.setFlag((byte) ((int) flag));
        String p = map.get("question").toString();
        body.setQuestion(p);
        ArrayList answers = new ArrayList();
        List items = (List) map.get("answers");
        Iterator var14 = items.iterator();

        while (var14.hasNext()) {
            Map packet = (Map) var14.next();
            QuestionAnswerInfo info = new QuestionAnswerInfo();
            p = packet.get("id").toString();
            short id = Short.parseShort(p);
            info.setId(id);
            p = packet.get("content").toString();
            info.setContent(p);
            answers.add(info);
        }

        body.setAnswers(answers);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeConfirmAlarm(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        ConfirmAlarmBody body = new ConfirmAlarmBody();
        String p = map.get("alarmId").toString();
        int alarmId = Integer.parseInt(p);
        body.setAlarmId(alarmId);
        List switchs = (List) map.get("type");
        long type = CodecUtil.bitSwitch(switchs);
        body.setType(type);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeQueryTerminalSpecificParameters(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        TerminalQuerySpecificParametersBody body = new TerminalQuerySpecificParametersBody();
        ArrayList keys = new ArrayList();
        List items = (List) map.get("keys");
        Iterator var10 = items.iterator();

        while (var10.hasNext()) {
            String packet = (String) var10.next();
            Long id = Long.valueOf(Long.parseLong(packet, 16));
            keys.add(id);
        }

        body.setKeys(keys);
        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeEventSetup(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        EventSetupBody body = new EventSetupBody();
        String p = map.get("type").toString();
        byte type = Byte.parseByte(p);
        body.setType(type);
        if (type > 0) {
            ArrayList packet = new ArrayList();
            List items = (List) map.get("events");
            Iterator var12 = items.iterator();

            while (var12.hasNext()) {
                Map item = (Map) var12.next();
                EventSetupInfo info = new EventSetupInfo();
                info.setType(type);
                p = item.get("id").toString();
                short id = Short.parseShort(p);
                info.setId(id);
                p = item.get("content").toString();
                info.setContent(p);
                packet.add(info);
            }

            body.setEvents(packet);
        }

        JTT808Packet packet1 = new JTT808Packet(body);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeTerminalControlRequest(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        TerminalControlRequestBody body = new TerminalControlRequestBody();
        String p = map.get("command").toString();
        short key = Short.parseShort(p);
        body.setCommand(key);
        int tcpPort;
        int udpPort;
        switch (key) {
            case 1:
                TerminalWirelessUpgradeInfo packet1 = new TerminalWirelessUpgradeInfo();
                p = map.get("url").toString();
                packet1.setUrl(p);
                p = map.get("apn").toString();
                packet1.setApn(p);
                p = map.get("account").toString();
                packet1.setAccount(p);
                p = map.get("password").toString();
                packet1.setPassword(p);
                p = map.get("address").toString();
                packet1.setAddress(p);
                p = map.get("tcpPort").toString();
                int flag1 = Integer.parseInt(p);
                packet1.setTcpPort(flag1);
                p = map.get("udpPort").toString();
                tcpPort = Integer.parseInt(p);
                packet1.setUdpPort(tcpPort);
                p = map.get("factoryId").toString();
                packet1.setFactoryId(p.getBytes(Charsets.ASCII));
                p = map.get("hardwareVer").toString();
                packet1.setHardwareVer(p);
                p = map.get("firmwareVer").toString();
                packet1.setFirmwareVer(p);
                p = map.get("timeout").toString();
                udpPort = Integer.parseInt(p);
                packet1.setTimeout(udpPort);
                body.setParameter(packet1.array());
                break;
            case 2:
                TerminalLinkServerInfo packet = new TerminalLinkServerInfo();
                p = map.get("flag").toString();
                byte flag = Byte.parseByte(p);
                packet.setFlag(flag);
                if (flag == 0) {
                    p = map.get("authenticationCode").toString();
                    packet.setAuthenticationCode(p);
                    p = map.get("apn").toString();
                    packet.setApn(p);
                    p = map.get("account").toString();
                    packet.setAccount(p);
                    p = map.get("password").toString();
                    packet.setPassword(p);
                    p = map.get("address").toString();
                    packet.setAddress(p);
                    p = map.get("tcpPort").toString();
                    tcpPort = Integer.parseInt(p);
                    packet.setTcpPort(tcpPort);
                    p = map.get("udpPort").toString();
                    udpPort = Integer.parseInt(p);
                    packet.setUdpPort(udpPort);
                    p = map.get("timeout").toString();
                    int timeout = Integer.parseInt(p);
                    packet.setTimeout(timeout);
                }

                body.setParameter(packet.array());
        }

        JTT808Packet packet2 = new JTT808Packet(body);
        packet2.setCommand(command);
        packet2.setNumber(deviceNumber);
        packet2.setSerialNumber(deviceSerialNumber);
        return packet2.array();
    }

    private byte[] makeTemporaryPositionTrackingControl(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        TemporaryPositionTrackingControlBody body = new TemporaryPositionTrackingControlBody();
        String p = map.get("interval").toString();
        int interval = Integer.parseInt(p);
        body.setInterval(interval);
        p = map.get("seconds").toString();
        long seconds = Long.parseLong(p);
        body.setSeconds(seconds);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeTextMessageIssued(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        TextMessageIssuedBody body = new TextMessageIssuedBody();
        List list = (List) map.get("flag");
        long flag = CodecUtil.bitSwitch(list);
        body.setFlag((short) ((int) flag));
        String p = map.get("message").toString();
        body.setMessage(p);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makePhoneCallBack(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        PhoneCallBackBody body = new PhoneCallBackBody();
        String p = map.get("flag").toString();
        byte flag = Byte.parseByte(p);
        body.setFlag(flag);
        p = map.get("phone").toString();
        body.setPhone(p);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeVehicleControlRequest(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        VehicleControlRequestBody body = new VehicleControlRequestBody();
        long flag = CodecUtil.bitSwitch((List) map.get("flag"));
        body.setFlag((short) ((int) flag));
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeMultimediaDataRetrievalRequest(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        MultimediaDataRetrievalRequestBody body = new MultimediaDataRetrievalRequestBody();
        String p = map.get("mediaType").toString();
        byte mediaType = Byte.parseByte(p);
        body.setMediaType(mediaType);
        p = map.get("channelId").toString();
        byte channelId = Byte.parseByte(p);
        body.setChannelId(channelId);
        p = map.get("eventType").toString();
        byte eventType = Byte.parseByte(p);
        body.setEventType(eventType);
        p = map.get("startTime").toString();
        body.setStartTime(GpsTime.parse(p));
        p = map.get("endTime").toString();
        body.setEndTime(GpsTime.parse(p));
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeMultimediaDataTimespanUpload(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        MultimediaDataTimespanUploadBody body = new MultimediaDataTimespanUploadBody();
        String p = map.get("action").toString();
        byte action = Byte.parseByte(p);
        body.setAction(action);
        p = map.get("channelId").toString();
        byte channelId = Byte.parseByte(p);
        body.setChannelId(channelId);
        p = map.get("eventType").toString();
        byte eventType = Byte.parseByte(p);
        body.setEventType(eventType);
        p = map.get("mediaType").toString();
        byte mediaType = Byte.parseByte(p);
        body.setMediaType(mediaType);
        p = map.get("startTime").toString();
        body.setStartTime(GpsTime.parse(p));
        p = map.get("endTime").toString();
        body.setEndTime(GpsTime.parse(p));
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeRecording(int deviceSerialNumber, String deviceNumber, int command, String para) {
        RecordingBody body = (RecordingBody) JsonMapper.toObject(para, RecordingBody.class);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(command);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeSetTerminalParameters(int deviceSerialNumber, String deviceNumber, int command, String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        ArrayList parameters = new ArrayList();
        List items = (List) map.get("parameters");
        Iterator packet = items.iterator();

        while (packet.hasNext()) {
            Map body = (Map) packet.next();

            TerminalParameterInfo parm;
            for (Iterator var11 = body.keySet().iterator(); var11.hasNext(); parameters.add(parm)) {
                String mapKey = (String) var11.next();
                int key = Integer.parseInt(mapKey, 16);
                Object val = body.get(mapKey);
                parm = new TerminalParameterInfo();
                parm.setId((long) key);
                long settings;
                ByteIO space;
                String settings1;
                Map settings2;
                int list2;
                long list4;
                ByteIO control2;
                switch (key) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 24:
                    case 25:
                    case 27:
                    case 28:
                    case 32:
                    case 33:
                    case 34:
                    case 39:
                    case 40:
                    case 41:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 69:
                    case 85:
                    case 86:
                    case 87:
                    case 88:
                    case 89:
                    case 90:
                    case 112:
                    case 113:
                    case 114:
                    case 115:
                    case 116:
                    case 128:
                    case 147:
                    case 149:
                    case 256:
                    case 258:
                        settings1 = val.toString();
                        list4 = Long.parseLong(settings1);
                        control2 = new ByteIO(4);
                        control2.putUint(list4);
                        parm.setContent(control2.array());
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 26:
                    case 29:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 72:
                    case 73:
                    case 131:
                        settings1 = val.toString();
                        byte[] list5 = settings1.getBytes(Charsets.GBK);
                        parm.setContent(list5);
                        break;
                    case 49:
                    case 92:
                    case 94:
                    case 129:
                    case 130:
                    case 257:
                    case 259:
                        settings1 = val.toString();
                        list2 = Integer.parseInt(settings1);
                        space = new ByteIO(2);
                        space.putUshort(list2);
                        parm.setContent(space.array());
                        break;
                    case 70:
                    case 71:
                        settings1 = val.toString();
                        list4 = 0L;
                        if (settings1.equalsIgnoreCase("FFFFFFFF")) {
                            list4 = Long.parseLong(settings1, 16);
                        } else {
                            list4 = Long.parseLong(settings1);
                        }

                        control2 = new ByteIO(4);
                        control2.putUint(list4);
                        parm.setContent(control2.array());
                        break;
                    case 80:
                    case 81:
                    case 82:
                    case 83:
                    case 84:
                        settings = CodecUtil.bitSwitch((List) val);
                        space = new ByteIO(4);
                        space.putUint(settings);
                        parm.setContent(space.array());
                        break;
                    case 91:
                        settings1 = val.toString();
                        double list3 = Double.parseDouble(settings1) * 10.0D;
                        control2 = new ByteIO(2);
                        control2.putUshort((int) list3);
                        parm.setContent(control2.array());
                        break;
                    case 93:
                        settings2 = (Map) val;
                        list2 = Integer.parseInt(settings2.get("acceleration").toString());
                        int space2 = Integer.parseInt(settings2.get("time").toString());
                        int control1 = list2 << 8 | space2;
                        ByteIO io = new ByteIO(2);
                        io.putUshort(control1);
                        parm.setContent(io.array());
                        break;
                    case 100:
                    case 101:
                        settings2 = (Map) val;
                        Object list1 = settings2.get("switchs");
                        Object space1 = settings2.get("interval");
                        long control = CodecUtil.bitSwitch((List) list1);
                        long interval = Long.parseLong(space1.toString());
                        interval <<= 16;
                        control &= interval;
                        ByteIO io1 = new ByteIO(4);
                        io1.putUint(control);
                        parm.setContent(io1.array());
                        break;
                    case 132:
                    case 145:
                    case 146:
                    case 148:
                        settings1 = val.toString();
                        short list = Short.parseShort(settings1);
                        space = new ByteIO(1);
                        space.putUbyte(list);
                        parm.setContent(space.array());
                        break;
                    case 144:
                        settings = CodecUtil.bitSwitch((List) val);
                        space = new ByteIO(1);
                        space.putUbyte((short) ((int) settings));
                        parm.setContent(space.array());
                }
            }
        }

        TerminalParametersSetBody body1 = new TerminalParametersSetBody();
        body1.setTotal((short) parameters.size());
        body1.setParameters(parameters);
        JTT808Packet packet1 = new JTT808Packet(body1);
        packet1.setCommand(command);
        packet1.setNumber(deviceNumber);
        packet1.setSerialNumber(deviceSerialNumber);
        return packet1.array();
    }

    private byte[] makeCameraShootingImmediatelyRequest(int deviceSerialNumber, String deviceNumber, int key, String para) {
        CameraShootingImmediatelyRequestBody body = (CameraShootingImmediatelyRequestBody) JsonMapper.toObject(para, CameraShootingImmediatelyRequestBody.class);
        JTT808Packet packet = new JTT808Packet(body);
        packet.setCommand(key);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }

    private byte[] makeWithoutPara(int deviceSerialNumber, String deviceNumber, int key) {
        JTT808Packet packet = new JTT808Packet(new RawBody());
        packet.setCommand(key);
        packet.setNumber(deviceNumber);
        packet.setSerialNumber(deviceSerialNumber);
        return packet.array();
    }
}
