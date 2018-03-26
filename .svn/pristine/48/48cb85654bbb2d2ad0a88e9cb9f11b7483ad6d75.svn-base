package com.edata.monitor.godp;

import com.edata.godp.domain.statistics.MileageOilCountResult;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.common.DataLogDto;
import com.edata.monitor.dao.common.DeviceStatusDto;
import com.edata.monitor.dao.devicedata.DrivingRecorderInfoDto;
import com.edata.monitor.dao.devicedata.PhotoInfoDto;
import com.edata.monitor.dao.devicedata.log.*;
import com.edata.monitor.dao.instruct.CommandDto;
import com.edata.monitor.dao.instruct.FeatureDto;
import com.edata.monitor.dao.instruct.InstructDto;
import com.edata.monitor.dao.instruct.ParameterDto;
import com.edata.monitor.dao.locate.LatestDto;
import com.edata.monitor.dao.locate.TrackDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IGodpDao {
    /**
     * 查询最后位置数据
     */
    Map<String, LatestDto> loadLatest(List<String> numbers) throws Exception;

    /**
     * 同步设备与godp用户关系
     */
    void syncDeviceInUser(String number, boolean bind) throws RuntimeException;

    /**
     * 获取设备状态
     */
    DeviceStatusDto getDeviceStatus(String number) throws Exception;

    /**
     * 按页读取数据日志
     */
    Page<DataLogDto> loadDataLog(String number, Date start, Date end, int pageIndex, int pageSize) throws Exception;

    /**
     * 启用或禁止设备数据
     */
    Object setDataLog(String number, boolean enable) throws Exception;

    /**
     * 查询多媒体信息
     */
    Page<PhotoInfoDto> queryMultimedia(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 查询多媒体内容
     */
    byte[] readMultimedia(String id) throws Exception;

    /**
     * 查询行驶记录事故疑点日志
     */
    Page<AccidentDoubtLogDto> queryAccidentDoubtLog(String deviceNumber, Date start, Date end, int pageIndex, int
            pageSize) throws Exception;

    /**
     * 查询行驶记录仪信息
     */
    DrivingRecorderInfoDto querydrivingRecorder(String deviceNumber) throws Exception;

    /**
     * 查询行驶记录外部供电日志
     */
    Page<PowerLogDto> queryPowerLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 查询行驶记录超时驾驶日志
     */
    Page<TimeoutLogDto> queryTimeoutLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception;

    /**
     * 查询行驶记录参数设置日志
     */
    Page<ParameterLogDto> queryParameterLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize)
            throws Exception;

    /**
     * 查询行驶记录驾驶员登签日志
     */
    Page<LoginLogDto> queryLoginLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 查询行驶记录速度状态日志
     */
    Page<SpeedStatusLogDto> querySpeedStatusLog(String deviceNumber, Date start, Date end, int pageIndex, int
            pageSize) throws Exception;

    /**
     * 查询行驶记录位置日志
     */
    Page<LocateLogDto> queryLocateLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 查询行驶记录速度日志
     */
    Page<SpeedLogDto> querySpeedLog(String deviceNumber, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 匹配设备信令信息
     */
    List<FeatureDto> loadFeatures(String number) throws Exception;

    /**
     * 读取信令参数信息
     */
    List<ParameterDto> loadParameter(String pid, String featureId) throws Exception;

    /**
     * 发送指令
     */
    void sendInstruct(CommandDto dto) throws Exception;

    /**
     * 查询历史指令
     */
    Page<InstructDto> queryInstruct(String deviceNumber, String unid, Date start, Date end, int pageIndex, int
            pageSize) throws Exception;

    /**
     * 查询单条历史指令
     */
    InstructDto fetchInstruct(String id) throws Exception;

    /**
     * 查询轨迹页总数
     */
    int queryTrackPageCount(String number, Date start, Date end) throws Exception;

    /**
     * 查询轨迹页内容
     */
    List<TrackDto> queryTrackPageDetail(String number, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    /**
     * 历史上线设备统计
     *
     * @param numbers 所要检查的设备号列表
     * @param start   开始时间
     * @param end     结束时间
     * @return 在时间段内有上线的的设备号列表
     */
    List<String> historyOnlineOfflineStatistics(List<String> numbers, Date start, Date end) throws Exception;

    /**
     * 历史在线时长统计
     *
     * @param numbers 所要统计的设备号列表
     * @param start   开始时间
     * @param end     结束时间
     * @return 在时间段在线时长的统计结果列表
     * @throws Exception
     */
    Map<String, Integer> historyOnlineTimeStatistics(List<String> numbers, Date start, Date end) throws Exception;

    /**
     * 历史上线设备统计
     *
     * @param numbers 所要检查的设备号列表
     * @return 当前有上线的的设备号列表
     * @throws Exception
     */
    List<String> currentOnlineOfflineStatistics(List<String> numbers) throws Exception;

    /**
     * 统计时间段内里程、油耗
     *
     * @param numbers 所要检查的设备号列表
     * @param start   开始时间
     * @param end     结束时间
     * @return 间段内里程、油耗统计结果
     * @throws Exception
     */
    List<MileageOilCountResult> mileageOilCount(List<String> numbers, Date start, Date end) throws Exception;
}
