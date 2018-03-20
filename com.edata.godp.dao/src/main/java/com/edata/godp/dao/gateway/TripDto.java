package com.edata.godp.dao.gateway;

/**
 * 行程数据传输类
 *
 * @author 生
 */
public class TripDto {
    /**
     * 设备ID
     */
    public String number;
    /**
     * 开始时间
     */
    public String startTime;
    /**
     * 结束时间
     */
    public String endTime;
    /**
     * 里程
     */
    public int mileage;
    /**
     * 最大速度
     */
    public short maxSpeed;
    /**
     * 平均速度
     */
    public short avgSpeed;
    /**
     * 超速次数
     */
    public short overSpeed;
    /**
     * 超速时长
     */
    public short overSpeedTime;
    /**
     * 急刹车次数
     */
    public short breaks;
    /**
     * 急加速次数
     */
    public short speedUp;
    /**
     * 最高水温
     */
    public short maxEct;
    /**
     * 最高转速
     */
    public short maxRpm;
    /**
     * 平均电压
     */
    public float avgBv;
    /**
     * 总油耗
     */
    public float totalOil;
    /**
     * 平均油耗
     */
    public float avgOil;
    /**
     * 疲劳驾驶时长
     */
    public int fatigueTime;
    /**
     * 怠速时长
     */
    public int idleTime;
}
