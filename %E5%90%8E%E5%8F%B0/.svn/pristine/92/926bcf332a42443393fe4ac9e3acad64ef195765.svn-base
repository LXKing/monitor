package com.rayton.gps.dao.gateway;

import java.util.List;

/**
 * 网关接口
 *
 * @author 生
 */
public interface IGatewayDao {
    /**
     * 保存指令状态
     *
     * @throws Exception
     */
    void saveInstructStatus(InstructResultDto dto);

    /**
     * 保存轨迹数据
     */
    void saveTracks(List<TrackDto> tracks);

    /**
     * 保存故障数据
     */
    void saveFaults(List<FaultDto> faults);

    /**
     * 保存数据流
     */
    void saveFlows(List<FlowDto> flows);

    /**
     * 保存数据日志
     */
    void saveDataLogs(List<DataLogDto> dataLogs);

    /**
     * 保存行程数据
     */
    void saveTrips(List<TripDto> trips);

    /**
     * 保存指令应答结果
     */
    void saveInstructResults(List<InstructResultDto> instructResults);

    /**
     * 保存多媒体数据
     */
    void saveMultimedia(MultimediaDto dto);

    /**
     * 保存设备上下线通知
     */
    void saveDeviceOnlineOfflineReport(DeviceOnlineOfflineReportDto dto);
}
