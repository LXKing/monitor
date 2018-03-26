package com.rayton.gps.godp;

import com.edata.godp.domain.AppResponse;
import com.edata.godp.domain.device.*;
import com.edata.godp.domain.drivingrecorder.*;
import com.edata.godp.domain.drivingrecorder.LocateLog;
import com.edata.godp.domain.drivingrecorder.SpeedLog;
import com.edata.godp.domain.drivingrecorder.SpeedStatusLog;
import com.edata.godp.domain.feature.FeatureInfo;
import com.edata.godp.domain.feature.MatchFeatureRequest;
import com.edata.godp.domain.feature.MatchFeatureResponse;
import com.edata.godp.domain.instruct.*;
import com.edata.godp.domain.locate.LatestInfo;
import com.edata.godp.domain.locate.QueryLatestRequest;
import com.edata.godp.domain.locate.QueryLatestResponse;
import com.edata.godp.domain.multimedia.*;
import com.edata.godp.domain.parameter.LoadParameterInfo;
import com.edata.godp.domain.parameter.LoadParametersRequest;
import com.edata.godp.domain.parameter.LoadParametersResponse;
import com.edata.godp.domain.statistics.*;
import com.edata.godp.domain.track.*;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.common.DataLogDto;
import com.rayton.gps.dao.common.DeviceStatusDto;
import com.rayton.gps.dao.devicedata.DrivingRecorderInfoDto;
import com.rayton.gps.dao.devicedata.PhotoInfoDto;
import com.rayton.gps.dao.devicedata.log.*;
import com.rayton.gps.dao.instruct.CommandDto;
import com.rayton.gps.dao.instruct.FeatureDto;
import com.rayton.gps.dao.instruct.InstructDto;
import com.rayton.gps.dao.instruct.ParameterDto;
import com.rayton.gps.dao.locate.LatestDto;
import com.rayton.gps.dao.locate.TrackDto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Map.Entry;

@Repository
public class GodpDao implements IGodpDao {

    @Override
    public Map<String, LatestDto> loadLatest(List<String> numbers) throws Exception {
        HashMap<String, LatestDto> result = new HashMap<String, LatestDto>();

        QueryLatestRequest request = new QueryLatestRequest();
        request.setNumbers(numbers);

        QueryLatestResponse response = GodpPortal.execute(QueryLatestResponse.class, request, "locate.latests");

        Map<String, LatestInfo> data = response.getLatests();
        if (data == null)
            return result;
        for (Entry<String, LatestInfo> entry : data.entrySet()) {
            String number = entry.getKey();
            LatestInfo info = entry.getValue();

            LatestDto dto = new LatestDto();
            dto.a = info.a;
            dto.d = info.d;
            dto.val = info.val;
            dto.lat = info.lat;
            dto.lng = info.lng;
            dto.s = info.s;
            dto.sp = info.sp;
            dto.gt = info.gt;
            dto.st = info.st;
            dto.o = info.o;
            dto.m = info.m;
            dto.oil = info.oil;
            dto.vss = info.vss;
            dto.ovt = info.ovt;
            dto.oid = info.oid;
            dto.iot = info.iot;
            dto.iid = info.iid;
            dto.iof = info.iof;
            dto.rt = info.rt;
            dto.rid = info.rid;
            dto.rf = info.rf;
            dto.aid = info.aid;
            dto.exs = info.exs;
            dto.ios = info.ios;
            dto.ad0 = info.ad0;
            dto.ad1 = info.ad1;
            dto.net = info.net;
            dto.sat = info.sat;

            result.put(number, dto);
        }
        return result;
    }

    @Override
    public void syncDeviceInUser(String number, boolean bind) throws RuntimeException {
        DeviceBindUserRequest request = new DeviceBindUserRequest();
        request.setNumber(number);
        request.setBind(bind);
        GodpPortal.execute(AppResponse.class, request, "device.bind.user");
    }

    @Override
    public DeviceStatusDto getDeviceStatus(String number) throws Exception {
        DeviceStatusRequest request = new DeviceStatusRequest();
        request.setNumber(number);

        DeviceStatusResponse response = GodpPortal.execute(DeviceStatusResponse.class, request, "device.status");

        DeviceStatusDto dto = new DeviceStatusDto();
        dto.curVer = response.getCurVer();
        dto.debugging = response.isDebugging();
        dto.matching = response.isMatching();
        dto.matchResult = response.getMatchResult();
        dto.matchTime = response.getMatchTime();
        dto.number = response.getNumber();
        dto.preVer = response.getPreVer();
        dto.repairing = response.isRepairing();
        dto.sleeping = response.isSleeping();
        dto.upgradeEnd = response.getUpgradeEnd();
        dto.upgradeStart = response.getUpgradeStart();
        dto.upgrading = response.isUpgrading();

        return dto;
    }

    @Override
    public Page<DataLogDto> loadDataLog(String number, Date start, Date end, int pageIndex, int pageSize) throws
            Exception {
        LoadDataLogRequest request = new LoadDataLogRequest();
        request.setNumber(number);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        LoadDataLogResponse response = GodpPortal.execute(LoadDataLogResponse.class, request, "device.log.load");

        Page<DataLogDto> page = new Page<DataLogDto>();
        page.total = response.getTotal();
        page.rows = new ArrayList<DataLogDto>();

        if (response.getLogs() == null)
            return page;

        for (DataLog log : response.getLogs()) {
            DataLogDto dto = new DataLogDto();

            dto.time = log.getTime();
            dto.raw = log.getRaw();

            page.rows.add(dto);
        }

        return page;
    }

    @Override
    public Object setDataLog(String number, boolean enable) throws Exception {
        SetDataLogRequest request = new SetDataLogRequest();
        request.setEnable(enable);
        request.setNumber(number);

        AppResponse response = GodpPortal.execute(AppResponse.class, request, "device.log.enable");

        return response;
    }

    @Override
    public Page<PhotoInfoDto> queryMultimedia(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        Page<PhotoInfoDto> query = new Page<PhotoInfoDto>();
        QueryMultimediaRequest request = new QueryMultimediaRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryMultimediaResponse response = GodpPortal.execute(QueryMultimediaResponse.class, request, "multimedia" +
                ".query");
        query.total = response.getTotal();
        for (MultimediaInfo info : response.getRows()) {
            PhotoInfoDto dto = new PhotoInfoDto();
            dto.id = info.getId();
            dto.gt = info.getGt();
            dto.mediaId = info.getMediaId();
            dto.mediaType = info.getMediaType();
            dto.formatType = info.getFormatType();
            dto.eventType = info.getEventType();
            dto.channelId = info.getChannelId();
            dto.lng = info.getLng();
            dto.lat = info.getLat();
            dto.sp = info.getSp();
            dto.d = info.getD();
            dto.a = info.getA();
            dto.s = info.getS();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public byte[] readMultimedia(String id) throws Exception {
        ReadMultimediaRequest request = new ReadMultimediaRequest();
        request.setId(id);
        ReadMultimediaResponse response = GodpPortal.execute(ReadMultimediaResponse.class, request, "multimedia.read");
        return response.getContent();
    }

    @Override
    public DrivingRecorderInfoDto querydrivingRecorder(String deviceNumber) throws Exception {
        QueryDrivingRecorderInfoRequest request = new QueryDrivingRecorderInfoRequest();
        request.setNumber(deviceNumber);

        QueryDrivingRecorderInfoResponse response = GodpPortal.execute(QueryDrivingRecorderInfoResponse.class,
                request, "drivingrecorder.info");
        DrivingRecorderInfoDto dto = new DrivingRecorderInfoDto();
        dto.number = response.getNumber();
        dto.revision = response.getRevision();
        dto.license = response.getLicense();
        dto.initialMileage = response.getInitialMileage();
        dto.totalMileage = response.getTotalMileage();
        dto.pulseFactor = response.getPulseFactor();
        dto.vehicleIdCode = response.getVehicleIdCode();
        dto.plateNumber = response.getPlateNumber();
        dto.plateType = response.getPlateType();
        dto.cccCode = response.getCccCode();
        dto.model = response.getModel();
        dto.productionDate = response.getProductionDate();
        dto.serialNumber = response.getSerialNumber();
        dto.d0 = response.getD0();
        dto.d1 = response.getD1();
        dto.d2 = response.getD2();
        dto.d3 = response.getD3();
        dto.d4 = response.getD4();
        dto.d5 = response.getD5();
        dto.d6 = response.getD6();
        dto.d7 = response.getD7();

        return dto;
    }

    @Override
    public Page<AccidentDoubtLogDto> queryAccidentDoubtLog(String deviceNumber, Date start, Date end, int pageIndex,
                                                           int pageSize) throws Exception {
        Page<AccidentDoubtLogDto> query = new Page<AccidentDoubtLogDto>();
        QueryAccidentDoubtLogRequest request = new QueryAccidentDoubtLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryAccidentDoubtLogResponse response = GodpPortal.execute(QueryAccidentDoubtLogResponse.class, request,
                "drivingrecorder.accidentdoubtlog.query");
        query.total = response.getTotal();
        for (AccidentDoubt info : response.getRows()) {
            AccidentDoubtLogDto dto = new AccidentDoubtLogDto();
            dto.number = info.getNumber();
            dto.license = info.getLicense();
            dto.time = info.getTime();
            dto.lat = info.getLat();
            dto.lng = info.getLng();
            dto.alt = info.getAlt();
            dto.content = info.getContent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<PowerLogDto> queryPowerLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        Page<PowerLogDto> query = new Page<PowerLogDto>();
        QueryPowerLogRequest request = new QueryPowerLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryPowerLogResponse response = GodpPortal.execute(QueryPowerLogResponse.class, request, "drivingrecorder" +
                ".powerlog.query");
        query.total = response.getTotal();
        for (PowerSupplyLog info : response.getRows()) {
            PowerLogDto dto = new PowerLogDto();
            dto.number = info.getNumber();
            dto.time = info.getTime();
            dto.event = info.getEvent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<TimeoutLogDto> queryTimeoutLog(String deviceNumber, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {
        Page<TimeoutLogDto> query = new Page<TimeoutLogDto>();
        QueryTimeoutLogRequest request = new QueryTimeoutLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryTimeoutLogResponse response = GodpPortal.execute(QueryTimeoutLogResponse.class, request,
                "drivingrecorder.timeoutlog.query");
        query.total = response.getTotal();
        for (TimeoutDriving info : response.getRows()) {
            TimeoutLogDto dto = new TimeoutLogDto();
            dto.number = info.getNumber();
            dto.startTime = info.getStartTime();
            dto.endTime = info.getEndTime();
            dto.license = info.getLicense();
            dto.startLng = info.getStartLng();
            dto.startLat = info.getStartLat();
            dto.startAlt = info.getStartAlt();
            dto.endLng = info.getEndLng();
            dto.endLat = info.getEndLat();
            dto.endAlt = info.getEndAlt();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<ParameterLogDto> queryParameterLog(String deviceNumber, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {
        Page<ParameterLogDto> query = new Page<ParameterLogDto>();
        QueryParameterLogRequest request = new QueryParameterLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryParameterLogResponse response = GodpPortal.execute(QueryParameterLogResponse.class, request,
                "drivingrecorder.parameterlog.query");
        query.total = response.getTotal();
        for (ParameterChangeLog info : response.getRows()) {
            ParameterLogDto dto = new ParameterLogDto();
            dto.number = info.getNumber();
            dto.time = info.getTime();
            dto.event = info.getEvent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<LoginLogDto> queryLoginLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        Page<LoginLogDto> query = new Page<LoginLogDto>();
        QueryLoginLogRequest request = new QueryLoginLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryLoginLogResponse response = GodpPortal.execute(QueryLoginLogResponse.class, request, "drivingrecorder" +
                ".loginlog.query");
        query.total = response.getTotal();
        for (LoginLogoutLog info : response.getRows()) {
            LoginLogDto dto = new LoginLogDto();
            dto.number = info.getNumber();
            dto.time = info.getTime();
            dto.license = info.getLicense();
            dto.event = info.getEvent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<SpeedStatusLogDto> querySpeedStatusLog(String deviceNumber, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {
        Page<SpeedStatusLogDto> query = new Page<SpeedStatusLogDto>();
        QuerySpeedStatusLogRequest request = new QuerySpeedStatusLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QuerySpeedStatusLogResponse response = GodpPortal.execute(QuerySpeedStatusLogResponse.class, request,
                "drivingrecorder.speedstatuslog.query");
        query.total = response.getTotal();
        for (SpeedStatusLog info : response.getRows()) {
            SpeedStatusLogDto dto = new SpeedStatusLogDto();
            dto.startTime = info.getStart();
            dto.endTime = info.getEnd();
            dto.state = info.getStatus();
            dto.content = info.getContent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<LocateLogDto> queryLocateLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        Page<LocateLogDto> query = new Page<LocateLogDto>();
        QueryLocateLogRequest request = new QueryLocateLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QueryLocateLogResponse response = GodpPortal.execute(QueryLocateLogResponse.class, request, "drivingrecorder"
                + ".locatelog.query");
        query.total = response.getTotal();
        for (LocateLog info : response.getRows()) {
            LocateLogDto dto = new LocateLogDto();
            dto.number = info.getNumber();
            dto.time = info.getTime();
            dto.lng = info.getLng();
            dto.lat = info.getLat();
            dto.alt = info.getAlt();
            dto.speed = info.getSpeed();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public Page<SpeedLogDto> querySpeedLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        Page<SpeedLogDto> query = new Page<SpeedLogDto>();
        QuerySpeedLogRequest request = new QuerySpeedLogRequest();
        request.setDeviceNumber(deviceNumber);
        request.setStart(start);
        request.setEnd(end);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);

        QuerySpeedLogResponse response = GodpPortal.execute(QuerySpeedLogResponse.class, request, "drivingrecorder" +
                ".speedlog.query");
        query.total = response.getTotal();
        for (SpeedLog info : response.getRows()) {
            SpeedLogDto dto = new SpeedLogDto();
            dto.time = info.getTime();
            dto.content = info.getContent();

            query.rows.add(dto);
        }

        return query;
    }

    @Override
    public List<FeatureDto> loadFeatures(String number) throws Exception {
        List<FeatureDto> result = new ArrayList<FeatureDto>();

        MatchFeatureRequest request = new MatchFeatureRequest();
        request.setNumber(number);

        MatchFeatureResponse response = GodpPortal.execute(MatchFeatureResponse.class, request, "feature.match");

        for (FeatureInfo info : response.getFeatures()) {
            FeatureDto dto = new FeatureDto();
            dto.command = info.getCommand();
            dto.description = info.getDescription();
            dto.id = info.getId();
            dto.index = info.getIndex();
            dto.name = info.getName();
            dto.params = info.getParams();

            result.add(dto);
        }
        return result;
    }

    @Override
    public List<ParameterDto> loadParameter(String pid, String featureId) throws Exception {
        List<ParameterDto> result = new ArrayList<ParameterDto>();

        LoadParametersRequest request = new LoadParametersRequest();
        request.setPid(pid);
        request.setFeatureId(featureId);

        LoadParametersResponse response = GodpPortal.execute(LoadParametersResponse.class, request, "parameter.load");
        for (LoadParameterInfo info : response.getParameters()) {
            ParameterDto dto = new ParameterDto();
            dto.id = info.getId();
            dto.pid = info.getPid();
            dto.featureId = info.getFeatureId();
            dto.index = info.getIndex();
            dto.name = info.getName();
            dto.label = info.getLabel();
            dto.type = info.getType();
            dto.selectValue = info.getSelectValue();
            dto.dictionaryKey = info.getDictionaryKey();
            dto.switchBit = info.getSwitchBit();
            dto.rows = info.getRows();
            dto.columns = info.getColumns();
            dto.defaultValue = info.getDefaultValue();
            dto.description = info.getDescription();

            result.add(dto);
        }
        return result;
    }

    @Override
    public void sendInstruct(CommandDto dto) throws Exception {
        SendInstructRequest request = new SendInstructRequest();
        request.setCommand(dto.command);
        request.setDeviceNumber(dto.deviceNumber);
        request.setName(dto.name);
        request.setParams(dto.params);
        request.setSerialNumber(dto.serialNumber);
        request.setCommand(dto.command);
        request.setUserId(dto.unid);
        request.setUser(dto.user);

        GodpPortal.execute(AppResponse.class, request, "instruct.send");
    }

    @Override
    public Page<InstructDto> queryInstruct(String deviceNumber, String unid, Date start, Date end, int pageIndex, int
            pageSize) throws Exception {

        Page<InstructDto> page = new Page<InstructDto>();

        QueryInstructRequest request = new QueryInstructRequest();
        request.setEnd(end);
        request.setNumber(deviceNumber);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        request.setStart(start);
        request.setUserId(unid);

        QueryInstructResponse response = GodpPortal.execute(QueryInstructResponse.class, request, "instruct.query");
        if (response.getInstructs() == null || response.getInstructs().size() <= 0)
            return page;
        page.total = response.getTotal();
        for (InstructInfo info : response.getInstructs()) {
            InstructDto dto = new InstructDto();
            dto.command = info.getCommand();
            dto.id = info.getId();
            dto.name = info.getName();
            dto.number = info.getNumber();
            dto.parameter = info.getParameter();
            dto.replyTime = info.getReplyTime();
            dto.result = info.getResult();
            dto.sendTime = info.getSendTime();
            dto.status = info.getStatus();

            page.rows.add(dto);
        }

        return page;

    }

    @Override
    public InstructDto fetchInstruct(String id) throws Exception {
        FetchInstructRequest request = new FetchInstructRequest();
        request.setId(id);

        FetchInstructResponse response = GodpPortal.execute(FetchInstructResponse.class, request, "instruct.fetch");

        InstructDto dto = new InstructDto();
        dto.command = response.getCommand();
        dto.id = response.getId();
        dto.name = response.getName();
        dto.number = response.getNumber();
        dto.parameter = response.getParameter();
        dto.replyTime = response.getReplyTime();
        dto.result = response.getResult();
        dto.sendTime = response.getSendTime();
        dto.status = response.getStatus();

        return dto;
    }

    @Override
    public int queryTrackPageCount(String number, Date start, Date end) throws Exception {
        CountTrackRequest request = new CountTrackRequest();
        request.setEnd(end);
        request.setNumber(number);
        request.setStart(start);

        CountTrackResponse response = GodpPortal.execute(CountTrackResponse.class, request, "track.count");

        return response.getTotal();
    }

    @Override
    public List<TrackDto> queryTrackPageDetail(String number, Date start, Date end, int pageIndex, int pageSize)
            throws Exception {
        LoadTrackRequest request = new LoadTrackRequest();
        request.setEnd(end);
        request.setNumber(number);
        request.setPageIndex(pageIndex);
        request.setPageSize(pageSize);
        request.setStart(start);

        LoadTrackResponse response = GodpPortal.execute(LoadTrackResponse.class, request, "track.load");

        List<Track> list = response.getTracks();
        List<TrackDto> tracks = new ArrayList<TrackDto>();
        if (response.getTracks() == null)
            return tracks;
        for (Track track : list) {
            TrackDto dto = new TrackDto();
            dto.a = track.getA();
            dto.d = track.getD();
            dto.lat = track.getLat();
            dto.lng = track.getLng();
            dto.sp = track.getSp();
            dto.s = track.getS();
            dto.gt = track.getGt();
            dto.st = track.getSt();
            dto.m = track.getM();
            dto.oil = track.getOil();
            dto.vss = track.getVss();
            dto.ovt = track.getOvt();
            dto.oid = track.getOid();
            dto.iot = track.getIot();
            dto.iid = track.getIid();
            dto.iof = track.getIof();
            dto.rid = track.getRid();
            dto.rt = track.getRt();
            dto.rf = track.getRf();
            dto.rid = track.getRid();
            dto.rf = track.getRf();
            dto.aid = track.getAid();
            dto.exs = track.getExs();
            dto.ios = track.getIos();
            dto.ad0 = track.getAd0();
            dto.ad1 = track.getAd1();
            dto.net = track.getNet();
            dto.sat = track.getSat();

            tracks.add(dto);
        }
        return tracks;
    }

    @Override
    public List<String> historyOnlineOfflineStatistics(List<String> numbers, Date start, Date end) throws Exception {
        HistoryOnlineOfflineStatisticsRequest request = new HistoryOnlineOfflineStatisticsRequest();
        request.setNumbers(numbers);
        request.setStart(start);
        request.setEnd(end);

        HistoryOnlineOfflineStatisticsResponse response = GodpPortal.execute(HistoryOnlineOfflineStatisticsResponse
                .class, request, "statistics.history.onlineoffline");

        return response.getNumbers();
    }

    @Override
    public Map<String, Integer> historyOnlineTimeStatistics(List<String> numbers, Date start, Date end) throws
            Exception {
        HistoryOnlineTimeStatisticsRequest request = new HistoryOnlineTimeStatisticsRequest();
        request.setNumbers(numbers);
        request.setStart(start);
        request.setEnd(end);

        HistoryOnlineTimeStatisticsResponse response = GodpPortal.execute(HistoryOnlineTimeStatisticsResponse.class,
                request, "statistics.history.onlinetime");

        return response.getResult();
    }

    @Override
    public List<String> currentOnlineOfflineStatistics(List<String> numbers) throws Exception {
        CurrentOnlineOfflineStatisticsRequest request = new CurrentOnlineOfflineStatisticsRequest();
        request.setNumbers(numbers);

        CurrentOnlineOfflineStatisticsResponse response = GodpPortal.execute(CurrentOnlineOfflineStatisticsResponse
                .class, request, "statistics.current.onlineoffline");

        return response.getNumbers();
    }

    @Override
    public List<MileageOilCountResult> mileageOilCount(List<String> numbers, Date start, Date end) throws Exception {
        MileageOilCountRequest request = new MileageOilCountRequest();
        request.setEnd(end);
        request.setStart(start);
        request.setNumbers(numbers);

        MileageOilCountResponse response = GodpPortal.execute(MileageOilCountResponse.class, request, "statistics" +
                ".count.mileageoil");

        return response.getResults();
    }
}
