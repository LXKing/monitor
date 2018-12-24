package mmp.gps.logic.dao;

import mmp.gps.domain.fault.FaultDto;
import mmp.gps.domain.flow.FlowDto;
import mmp.gps.domain.gateway.DeviceOnlineOfflineReportDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.gateway.MultimediaDto;
import mmp.gps.domain.log.DataLogDto;
import mmp.gps.domain.track.TrackDto;
import mmp.gps.domain.trip.TripDto;

import java.util.List;

/**
 * 网关接口
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
