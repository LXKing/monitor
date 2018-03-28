package com.rayton.gps.dao.gateway;

import java.util.ArrayList;
import java.util.List;

public class BulkDataDto {
    /**
     * 轨迹数据列表
     */
    public List<TrackDto> tracks;
    /**
     * 故障数据列表
     */
    public List<FaultDto> faults;
    /**
     * 获取数据流列表
     */
    public List<FlowDto> flows;

    /**
     * 数据日志列表
     */
    public List<DataLogDto> dataLogs;
    /**
     * 行程报告列表
     */
    public List<TripDto> trips;
    /**
     * 指令结果列表
     */
    public List<InstructResultDto> instructResults;

    /**
     * 添加数据日志
     */
    public void add(DataLogDto log) {
        if (dataLogs == null)
            dataLogs = new ArrayList<DataLogDto>();
        dataLogs.add(log);
    }

    /**
     * 添加轨迹数据
     */
    public void add(TrackDto track) {
        if (tracks == null)
            tracks = new ArrayList<TrackDto>();
        tracks.add(track);
    }

    /**
     * 添加故障数据
     */
    public void add(FaultDto fault) {
        if (faults == null)
            faults = new ArrayList<FaultDto>();
        faults.add(fault);
    }

    /**
     * 添加行程数据
     */
    public void add(TripDto trip) {
        if (trips == null)
            trips = new ArrayList<TripDto>();
        trips.add(trip);
    }

    /**
     * 添加关键数据流数据
     */
    public void add(FlowDto flow) {
        if (flows == null)
            flows = new ArrayList<FlowDto>();
        flows.add(flow);
    }

    /**
     * 添加指令结果
     */
    public void add(InstructResultDto result) {
        if (instructResults == null)
            instructResults = new ArrayList<InstructResultDto>();
        instructResults.add(result);
    }

}
