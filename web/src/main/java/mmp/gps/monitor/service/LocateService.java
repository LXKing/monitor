package mmp.gps.monitor.service;

import mmp.gps.common.util.JsonMapper;
import mmp.gps.common.util.MessageKinds;
import mmp.gps.common.util.ObjectId;
import mmp.gps.common.enums.*;
import mmp.gps.domain.alarm.Alarm;
import mmp.gps.domain.alarm.AlarmDto;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.gateway.MultimediaDataUploadReport;
import mmp.gps.domain.locate.*;
import mmp.gps.domain.monitor.MonitorTarget;
import mmp.gps.domain.monitor.UserMonitorTarget;
import mmp.gps.domain.push.*;
import mmp.gps.domain.statistics.DeviceOnlineOfflineReport;
import mmp.gps.domain.vehicle.VehicleBaseInfoDto;
import mmp.gps.domain.vehicle.VehicleInfo;
import mmp.gps.monitor.cache.AreaCatcherCache;
import mmp.gps.monitor.cache.AssociationCache;
import mmp.gps.monitor.cache.SynchronizerCache;
import mmp.gps.monitor.configuration.AppConfig;
import mmp.gps.monitor.dao.IDeviceDao;
import mmp.gps.monitor.dao.ILocateDao;
import mmp.gps.monitor.dao.ISimcardDao;
import mmp.gps.monitor.dao.IVehicleDao;
import mmp.gps.monitor.godp.IGodpDao;
import mmp.gps.monitor.websocket.SystemWebSocketHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

@Service
public class LocateService {
    @Autowired
    private ILocateDao locateDao;
    @Autowired
    private IGodpDao godpDao;
    @Autowired
    private AppConfig webConfig;

    @Autowired
    private IDeviceDao deviceDao;


    /**
     * 获取用户组别车辆列表
     *
     * @param userId 用户ID
     * @param force  是否强制从数据库加载
     * @throws Exception
     */
    public List<GroupVehicle> loadGroupVehicles(String userId, boolean force) throws Exception {
        List<GroupVehicle> result = null;
        UserMonitorTarget target = null;
        if (force)
            target = AssociationCache.refreshUserMonitorTarget(userId);
        else
            target = AssociationCache.getUserMonitorTarget(userId);

        // 从缓存加载
        result = loadGroupVehiclesFromCache(target);
        return result;
    }

    private List<GroupVehicle> loadGroupVehiclesFromCache(UserMonitorTarget target) throws Exception {
        int total = target.getDevices().size() + target.getMotorcades().size();
        List<GroupVehicle> result = new ArrayList<GroupVehicle>(total);

        List<String> numbers = new ArrayList<String>(total);
        for (Entry<String, MonitorTarget> entry : target.getDevices().entrySet()) {
            MonitorTarget mt = entry.getValue();
            numbers.add(mt.getDeviceNumber());
            GroupVehicle groupVehicle = new GroupVehicle();
            groupVehicle.setId(mt.getId());
            groupVehicle.setPid(mt.getPid());
            groupVehicle.setNa(mt.getName());
            groupVehicle.setDn(entry.getKey());
            groupVehicle.setType(mt.getType());
            groupVehicle.setMarker(mt.getMarker());
            groupVehicle.setRotate(mt.getRotate());


            Device device = deviceDao.getByNum(mt.getDeviceNumber());


            result.add(groupVehicle);
        }

        // 读取最后位置数据
        Map<String, LatestDto> map = godpDao.loadLatest(numbers);


        List<Latest> tracks = new ArrayList<>();


        for (GroupVehicle gv : result) {
            LatestDto dto = map.get(gv.getDn());
            if (dto == null)
                continue;

            Latest latest = new Latest();
            BeanUtils.copyProperties(dto, latest);
            latest.setDn(gv.getDn());
            tracks.add(latest);

            gv.setA(dto.a);
            gv.setD(dto.d);
            gv.setVal(dto.val);
            gv.setLat(dto.lat);
            gv.setLng(dto.lng);
            gv.setS(dto.s);
            gv.setSp(dto.sp);
            gv.setGt(dto.gt);
            gv.setSt(dto.st);
            gv.setO(dto.o);
            gv.setM(dto.m);
            gv.setOil(dto.oil);
            gv.setVss(dto.vss);
            gv.setOvt(dto.ovt);
            gv.setOid(dto.oid);
            gv.setIot(dto.iot);
            gv.setIid(dto.iid);
            gv.setIof(dto.iof);
            gv.setRid(dto.rid);
            gv.setRt(dto.rt);
            gv.setRf(dto.rf);
            gv.setAid(dto.aid);
            gv.setExs(dto.exs);
            gv.setIos(dto.ios);
            gv.setAd0(dto.ad0);
            gv.setAd1(dto.ad1);
            gv.setNet(dto.net);
            gv.setSat(dto.sat);
        }

        fuck(tracks);

        for (MonitorTarget monitor : target.getMotorcades()) {
            numbers.add(monitor.getDeviceNumber());
            GroupVehicle groupVehicle = new GroupVehicle();
            groupVehicle.setId(monitor.getId());
            groupVehicle.setPid(monitor.getPid());
            groupVehicle.setNa(monitor.getName());
            groupVehicle.setDn(monitor.getDeviceNumber());
            groupVehicle.setType(monitor.getType());
            result.add(groupVehicle);
        }

        return result;
    }


    private void fuck(List<Latest> tracks) {
        // // 解析godp发送过来的数据

        // 以用户名为主键创建下发的数据数块
//        Map<String, List<GodpDataBlock>> map = new HashMap<String, List<GodpDataBlock>>();
        // 收集报警数据，以便入库后，用户做处理
        List<AlarmDto> alarms = new ArrayList<AlarmDto>();

        if (tracks != null) {
            for (Latest latest : tracks) {
                // 发送未同步的指令
//                SynchronizerCache.sendNext(latest.getDn());
                // 平台区域计算
                boolean hasAlarm = AreaCatcherCache.hasAlarm(latest);
//
//                GodpDataBlock block = new GodpDataBlock();
//                block.setKind(MessageKinds.device_realtime_track);

                // 收集报警
                if (latest.getA() != 0) {
                    AlarmDto dto = new AlarmDto();

                    dto.dn = latest.getDn();
                    dto.a = latest.getA();
                    dto.d = latest.getD();
                    dto.val = latest.getVal();
                    dto.lat = latest.getLat();
                    dto.lng = latest.getLng();
                    dto.alt = latest.getAlt();
                    dto.s = latest.getS();
                    dto.sp = latest.getSp();
                    dto.gt = latest.getGt();
                    dto.st = latest.getSt();
                    dto.m = latest.getM();
                    dto.oil = latest.getOil();
                    dto.vss = latest.getVss();
                    dto.ovt = latest.getOvt();
                    dto.oid = latest.getOid();
                    dto.iot = latest.getIot();
                    dto.iid = latest.getIid();
                    dto.iof = latest.getIof();
                    dto.rid = latest.getRid();
                    dto.rt = latest.getRt();
                    dto.rf = latest.getRf();
                    dto.aid = latest.getAid();
                    dto.exs = latest.getExs();
                    dto.ios = latest.getIos();
                    dto.ad0 = latest.getAd0();
                    dto.ad1 = latest.getAd1();
                    dto.net = latest.getNet();
                    dto.sat = latest.getSat();

                    dto.id = ObjectId.next();
                    dto.from = (byte) (hasAlarm ? 1 : 0);

                    alarms.add(dto);
                    Alarm alarm = JsonMapper.convertValue(latest, Alarm.class);
                    alarm.setId(dto.id);
                    alarm.setFrom(dto.from);
                    System.out.println("===============ccc================");
                    System.out.println(alarm);
//                    block.setData(alarm);
                }
// else
//                    block.setData(latest);

//                String deviceNumber = latest.getDn();
                // 查找对应的用户id
//                Set<String> users = AssociationCache.getUsers(deviceNumber);
//                if (users == null)
//                    continue;
//                // 每用户生成一份数据
//                for (String userId : users) {
//                    // 检查用户是否在线
//                    // if (!SystemWebSocketHandler.isOnline(userId))
//                    //     continue;
//                    List<GodpDataBlock> blocks = map.get(userId);
//                    if (blocks == null) {
//                        blocks = new ArrayList<GodpDataBlock>();
//                        map.put(userId, blocks);
//                    }
//
//                    blocks.add(block);
//                }
            }
        }
//        // 下发数据
//         SystemWebSocketHandler handler = new SystemWebSocketHandler();
//         handler.sendMessageToUser(map);

        try {
            // 保存报警
            if (alarms.size() > 0)
                locateDao.saveAlarms(alarms);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }


    public List<Latest> loadLatests(List<String> numbers) throws Exception {
        List<Latest> result = new ArrayList<Latest>();

        Map<String, LatestDto> map = godpDao.loadLatest(numbers);
        if (map == null || map.size() <= 0)
            return result;

        for (Entry<String, LatestDto> entry : map.entrySet()) {
            String number = entry.getKey();
            LatestDto dto = entry.getValue();
            Latest l = new Latest();
            l.setDn(number);

            if (dto != null) {
                l.setA(dto.a);
                l.setD(dto.d);
                l.setVal(dto.val);
                l.setLat(dto.lat);
                l.setLng(dto.lng);
                l.setS(dto.s);
                l.setSp(dto.sp);
                l.setGt(dto.gt);
                l.setSt(dto.st);
                l.setO(dto.o);
                l.setM(dto.m);
                l.setOil(dto.oil);
                l.setVss(dto.vss);
                l.setOvt(dto.ovt);
                l.setOid(dto.oid);
                l.setIot(dto.iot);
                l.setIid(dto.iid);
                l.setIof(dto.iof);
                l.setRid(dto.rid);
                l.setRt(dto.rt);
                l.setRf(dto.rf);
                l.setAid(dto.aid);
                l.setExs(dto.exs);
                l.setIos(dto.ios);
                l.setAd0(dto.ad0);
                l.setAd1(dto.ad1);
                l.setNet(dto.net);
                l.setSat(dto.sat);
            }

            result.add(l);
        }

        return result;
    }

    /**
     * 处理godp推送的消息
     */
    public void handleGodpData(GodpDataBlock block) {
        switch (block.getKind()) {
            case MessageKinds.device_realtime_data:
                handleDeviceRealtimeData(block.getData());
                break;
            case MessageKinds.gateway_statistics_device_onlineoffline:
                handleDeviceLineStatusChanged(block.getData());
                break;
            case MessageKinds.device_multimedia_upload_percent:
                handleDeviceMultimediaUploadPercent(block.getData());
                break;
            case MessageKinds.device_multimedia_event_report:
                handleDeviceMultimediaEventReport(block.getData());
                break;
            case MessageKinds.device_multimedia_retrieval_reply:
                handleDeviceMultimediaRetrievalReply(block.getData());
                break;
            case MessageKinds.device_event_report:
                handleDeviceEventReport(block.getData());
                break;
            case MessageKinds.device_upgrade_result_report:
                handleDeviceUpgradeResultReport(block.getData());
                break;
            case MessageKinds.device_instruct_result:
                handleDeviceInstructResult(block.getData());
                break;
        }
    }

    /**
     * 处理指令结果
     */
    private void handleDeviceInstructResult(Object data) {
        InstructResultMessage message = JsonMapper.convertValue(data, InstructResultMessage.class);
        SynchronizerCache.remove(message.number, message.id);
        SynchronizerCache.sendNext(message.number);
        sendToUser(message, message.number, MessageKinds.device_instruct_result);
    }

    /**
     * 处理设备升级结果报告
     */
    private void handleDeviceUpgradeResultReport(Object data) {
        UpgradeResultReportMessage message = JsonMapper.convertValue(data, UpgradeResultReportMessage.class);

        UpgradeResultReportDto report = new UpgradeResultReportDto();
        report.id = ObjectId.next();
        report.number = message.number;
        report.time = new Date();
        report.type = UpgradeTypes.getName(message.type);
        report.result = UpgradeResults.getName(message.result);
        locateDao.saveUpgradeResultReport(report);

        sendToUser(report, message.number, MessageKinds.device_upgrade_result_report);
    }

    /**
     * 处理事件报告
     */
    private void handleDeviceEventReport(Object data) {
        EventReportMessage message = JsonMapper.convertValue(data, EventReportMessage.class);

        DeviceEventReportDto report = new DeviceEventReportDto();
        report.id = ObjectId.next();
        report.number = message.number;
        report.time = new Date();
        report.eventId = message.id;
        report.content = locateDao.getEventReportDescription(report.number, report.eventId);
        locateDao.saveDeviceEventReport(report);
        sendToUser(report, message.number, MessageKinds.device_event_report);
    }

    /**
     * 处理多媒体检索应答
     */
    private void handleDeviceMultimediaRetrievalReply(Object data) {
        List<MultimediaDataRetrievalMessage> messages = JsonMapper.convertValue(data, List.class, MultimediaDataRetrievalMessage.class);
        List<MultimediaRetrievalDto> list = new ArrayList<MultimediaRetrievalDto>();
        for (MultimediaDataRetrievalMessage message : messages) {
            MultimediaRetrievalDto dto = new MultimediaRetrievalDto();
            dto.number = message.number;
            dto.mediaId = message.mediaId;
            dto.mediaType = message.mediaType;
            dto.channelId = message.channelId;
            dto.eventType = message.eventType;
            dto.alarms = message.alarms;
            dto.status = message.status;
            dto.lat = message.lat;
            dto.lng = message.lng;
            dto.altitude = message.altitude;
            dto.speed = message.speed;
            dto.angle = message.angle;
            dto.gpstime = message.gpstime;
            list.add(dto);
        }
        locateDao.saveMultimediaRetrieval(list);
    }

    /**
     * 处理多媒体事件报告
     */
    private void handleDeviceMultimediaEventReport(Object data) {
        MultimediaEventReportMessage message = JsonMapper.convertValue(data, MultimediaEventReportMessage.class);
        String deviceNumber = message.number;

        MultimediaEventReportDto dto = new MultimediaEventReportDto();
        dto.id = ObjectId.next();
        dto.number = deviceNumber;
        dto.time = new Date();
        dto.mediaId = message.mediaId;
        dto.mediaType = message.mediaType;
        dto.formatType = message.formatType;
        dto.eventType = message.eventType;
        dto.channelId = message.channelId;

        locateDao.saveMultimediaEventReport(dto);

        MultimediaEventReport report = new MultimediaEventReport();
        report.setId(dto.id);
        report.setNumber(dto.number);
        report.setTime(dto.time);
        report.setMediaId(dto.mediaId);
        report.setMediaType(MediaTypes.getName(dto.mediaType));
        report.setFormatType(MediaFormatTypes.getName(dto.formatType));
        report.setEventType(MediaEventTypes.getName(dto.eventType));
        report.setChannelId(dto.channelId);

        sendToUser(report, deviceNumber, MessageKinds.device_multimedia_event_report);
    }

    /**
     * 处理拍照进度
     */
    private void handleDeviceMultimediaUploadPercent(Object data) {
        MultimediaDataUploadReport report = JsonMapper.convertValue(data, MultimediaDataUploadReport.class);
        String deviceNumber = report.number;
        sendToUser(report, deviceNumber, MessageKinds.device_multimedia_upload_percent);
    }

    private void sendToUser(Object message, String deviceNumber, String messageKind) {
        // // 以用户名为主键创建下发的数据数块
        Map<String, List<GodpDataBlock>> map = new HashMap<String, List<GodpDataBlock>>();
        // 查找对应的用户id
        Set<String> users = AssociationCache.getUsers(deviceNumber);
        if (users == null)
            return;
        // 每用户生成一份数据
        for (String userId : users) {
            // 检查用户是否在线
            if (!SystemWebSocketHandler.isOnline(userId))
                continue;
            List<GodpDataBlock> blocks = map.get(userId);
            if (blocks == null) {
                blocks = new ArrayList<GodpDataBlock>();
                map.put(userId, blocks);
            }

            GodpDataBlock block = new GodpDataBlock();
            block.setKind(messageKind);
            block.setData(message);
            blocks.add(block);
        }

        // 下发数据
        SystemWebSocketHandler handler = new SystemWebSocketHandler();
        handler.sendMessageToUser(map);
    }

    /**
     * 处理设备上下线改变通知
     */
    private void handleDeviceLineStatusChanged(Object data) {
        DeviceOnlineOfflineReport report = JsonMapper.convertValue(data, DeviceOnlineOfflineReport.class);
        String deviceNumber = report.number;
        if (report.on)
            SynchronizerCache.sendNext(deviceNumber);
        sendToUser(report, deviceNumber, MessageKinds.gateway_statistics_device_onlineoffline);
    }

    /**
     * 处理设备实时数据
     */
    private void handleDeviceRealtimeData(Object source) {
        // // 解析godp发送过来的数据
        RealtimeDataBlock data = JsonMapper.convertValue(source, RealtimeDataBlock.class);
        // 以用户名为主键创建下发的数据数块
        Map<String, List<GodpDataBlock>> map = new HashMap<String, List<GodpDataBlock>>();
        // 收集报警数据，以便入库后，用户做处理
        List<AlarmDto> alarms = new ArrayList<AlarmDto>();

        List<Latest> tracks = data.getTracks();
        if (tracks != null) {
            for (Latest latest : tracks) {
                // 发送未同步的指令
                SynchronizerCache.sendNext(latest.getDn());
                // 平台区域计算
                boolean hasAlarm = AreaCatcherCache.hasAlarm(latest);

                GodpDataBlock block = new GodpDataBlock();
                block.setKind(MessageKinds.device_realtime_track);

                // 收集报警
                if (latest.getA() != 0) {
                    AlarmDto dto = new AlarmDto();

                    dto.dn = latest.getDn();
                    dto.a = latest.getA();
                    dto.d = latest.getD();
                    dto.val = latest.getVal();
                    dto.lat = latest.getLat();
                    dto.lng = latest.getLng();
                    dto.alt = latest.getAlt();
                    dto.s = latest.getS();
                    dto.sp = latest.getSp();
                    dto.gt = latest.getGt();
                    dto.st = latest.getSt();
                    dto.m = latest.getM();
                    dto.oil = latest.getOil();
                    dto.vss = latest.getVss();
                    dto.ovt = latest.getOvt();
                    dto.oid = latest.getOid();
                    dto.iot = latest.getIot();
                    dto.iid = latest.getIid();
                    dto.iof = latest.getIof();
                    dto.rid = latest.getRid();
                    dto.rt = latest.getRt();
                    dto.rf = latest.getRf();
                    dto.aid = latest.getAid();
                    dto.exs = latest.getExs();
                    dto.ios = latest.getIos();
                    dto.ad0 = latest.getAd0();
                    dto.ad1 = latest.getAd1();
                    dto.net = latest.getNet();
                    dto.sat = latest.getSat();

                    dto.id = ObjectId.next();
                    dto.from = (byte) (hasAlarm ? 1 : 0);

                    alarms.add(dto);
                    Alarm alarm = JsonMapper.convertValue(latest, Alarm.class);
                    alarm.setId(dto.id);
                    alarm.setFrom(dto.from);
                    block.setData(alarm);
                } else
                    block.setData(latest);

                String deviceNumber = latest.getDn();
                // 查找对应的用户id
                Set<String> users = AssociationCache.getUsers(deviceNumber);
                if (users == null)
                    continue;
                // 每用户生成一份数据
                for (String userId : users) {
                    // 检查用户是否在线
                    // if (!SystemWebSocketHandler.isOnline(userId))
                    //     continue;
                    List<GodpDataBlock> blocks = map.get(userId);
                    if (blocks == null) {
                        blocks = new ArrayList<GodpDataBlock>();
                        map.put(userId, blocks);
                    }

                    blocks.add(block);
                }
            }
        }
        // 下发数据
        // SystemWebSocketHandler handler = new SystemWebSocketHandler();
        // handler.sendMessageToUser(map);

        try {
            // 保存报警
            if (alarms.size() > 0)
                locateDao.saveAlarms(alarms);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 查看车辆资料
     */
    public VehicleInfo vehicleInfo(String vehicleId) {
        VehicleBaseInfoDto vehicleBaseInfoDto = locateDao.vehicleBaseInfo(vehicleId);
        List<OwnerBaseInfoDto> owners = locateDao.ownerBaseInfo(vehicleId);
        List<DriverBaseInfoDto> drivers = locateDao.driverBaseInfo(vehicleId);

        VehicleInfo info = new VehicleInfo();

        return info;
    }

    public Object loadUnreadMultimediaEvent(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Map<String, MonitorTarget> devices = target.getDevices();
        List<String> numbers = new ArrayList<String>(devices.keySet());

        List<MultimediaEventReportDto> result = numbers.isEmpty() ? null : locateDao.loadUnreadMultimediaEvent(numbers);
        List<MultimediaEventReport> reports = new ArrayList<MultimediaEventReport>();

        Optional.ofNullable(result).ifPresent(multimediaEventReportDtos -> {
            for (MultimediaEventReportDto dto : result) {
                MultimediaEventReport report = new MultimediaEventReport();
                MonitorTarget device = devices.get(dto.number);
                report.setId(dto.id);
                report.setNumber(dto.number);
                report.setPlateNumber(device == null ? dto.number : device.getName());
                report.setTime(dto.time);
                report.setMediaId(dto.mediaId);
                report.setMediaType(MediaTypes.getName(dto.mediaType));
                report.setFormatType(MediaFormatTypes.getName(dto.formatType));
                report.setEventType(MediaEventTypes.getName(dto.eventType));
                report.setChannelId(dto.channelId);

                reports.add(report);
            }
        });


        return reports;
    }

    public void readMultmediaEventReport(String id, String userId, String userName) {
        locateDao.readMultmediaEventReport(id, userId, userName);
    }

    public Object loadUnreadDeviceEvent(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Map<String, MonitorTarget> devices = target.getDevices();
        List<String> numbers = new ArrayList<String>(devices.keySet());

        List<DeviceEventReportDto> result = numbers.isEmpty() ? null : locateDao.loadUnreadDeviceEvent(numbers);
        List<DeviceEventReport> reports = new ArrayList<DeviceEventReport>();

        Optional.ofNullable(result).ifPresent(deviceEventReportDtos -> {
            for (DeviceEventReportDto dto : result) {
                DeviceEventReport report = new DeviceEventReport();
                MonitorTarget device = devices.get(dto.number);
                report.setId(dto.id);
                report.setNumber(dto.number);
                report.setPlateNumber(device == null ? dto.number : device.getName());
                report.setTime(dto.time);
                report.setEventId(dto.eventId);
                report.setContent(dto.content);

                reports.add(report);
            }
        });


        return reports;
    }

    public void readDeviceEventReport(String id, String userId, String userName) {
        locateDao.readDeviceEventReport(id, userId, userName);
    }


    public Object loadUnreadDeviceUpgradeResultReport(String userId) {
        UserMonitorTarget target = AssociationCache.getUserMonitorTarget(userId);
        Map<String, MonitorTarget> devices = target.getDevices();
        List<String> numbers = new ArrayList<String>(devices.keySet());

        List<UpgradeResultReportDto> result = null;
        if (numbers.size() != 0) {
            result = locateDao.loadUnreadDeviceUpgradeResultReport(numbers);
        }

        List<UpgradeResultReport> reports = new ArrayList<UpgradeResultReport>();

        Optional.ofNullable(result)
                .ifPresent(upgradeResultReportDtos -> upgradeResultReportDtos.forEach(upgradeResultReportDto -> {

                    UpgradeResultReport report = new UpgradeResultReport();
                    MonitorTarget device = devices.get(upgradeResultReportDto.number);
                    report.setId(upgradeResultReportDto.id);
                    report.setNumber(upgradeResultReportDto.number);
                    report.setPlateNumber(device == null ? upgradeResultReportDto.number : device.getName());
                    report.setTime(upgradeResultReportDto.time);
                    report.setType(upgradeResultReportDto.type);
                    report.setResult(upgradeResultReportDto.result);

                    reports.add(report);

                }));



        return reports;
    }

    public void readDeviceUpgradeResultReport(String id, String userId, String userName) {
        locateDao.readDeviceUpgradeResultReport(id, userId, userName);
    }
}
