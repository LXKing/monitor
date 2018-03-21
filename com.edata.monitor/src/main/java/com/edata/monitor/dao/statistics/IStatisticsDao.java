package com.edata.monitor.dao.statistics;

import com.edata.monitor.dao.security.OperateLogDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IStatisticsDao {
    /**
     * 查询用户操作日志
     *
     * @param companyId 公司id
     * @param user      用户名
     * @param start     开始时间
     * @param end       结束时间
     */
    List<OperateLogDto> operateAllLog(String companyId, String user, Date start, Date end);

    /**
     * 查询用户操作日志行总数
     */
    int operateLogPageCount(String companyId, String user, Date start, Date end);

    /**
     * 查询用户操作日志页内容
     *
     * @param companyId 公司id
     * @param user      用户名
     * @param start     开始时间
     * @param end       结束时间
     * @param pageIndex 页号
     * @param pageSize  页大小
     */
    List<OperateLogDto> operateLogPageDetail(String companyId, String user, Date start, Date end, int pageIndex, int
            pageSize);

    /**
     * 车辆警情统计
     *
     * @param numbers 设备号列表
     * @param start   开始时间
     * @param end     结束时间
     */
    List<VehicleAlarmDto> vehicleAlarmCount(Map<String, Object> parms);

    /**
     * 疲劳驾驶统计
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<TimeMileageRecord> vehicleFatigueDrivings(String number, Date start, Date end);

    /**
     * 超速统计
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<Date> vehicleOverspeeds(String number, Date start, Date end);

    /**
     * 路段超速
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<TimeNameRecord> sectionOverspeeds(String number, Date start, Date end);

    /**
     * 区域超速
     *
     * @param numbers 设备号列表
     * @param start   开始时间
     * @param end     结束时间
     */
    List<AreaOverspeedRecord> areaOverspeeds(String numbers, Date start, Date end);

    /**
     * 停车超时统计
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<Date> timeoutParkings(String numbers, Date start, Date end);

    /**
     * 路线偏离统计
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<Date> routeDeviations(String number, Date start, Date end);

    /**
     * 进出区域统计
     *
     * @param number 设备号
     * @param start  开始时间
     * @param end    结束时间
     */
    List<Byte> areaIoRecords(String number, Date start, Date end);
}
