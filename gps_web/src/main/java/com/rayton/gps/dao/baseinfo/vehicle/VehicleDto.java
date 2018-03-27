package com.rayton.gps.dao.baseinfo.vehicle;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车辆数据传输类
 *
 * @author yangzs
 */
public class VehicleDto {
    /**
     * 唯一编号
     */
    public String id;
    /**
     * 所属公司唯一编号
     */
    public String companyId;

    /**
     * 所属车队唯一编号
     */
    public String motorcadeId;

    /**
     * 所属车队
     */
    public String motorcade;

    /**
     * 设备唯一编号
     */
    public String deviceId;
    /**
     * 设备号
     */
    public String deviceNumber;

    /**
     * 车牌号码
     */
    public String plateNumber;

    /**
     * 车牌颜色
     */
    public String plateColor;

    /**
     * 车辆颜色
     */
    public String vehicleColor;

    /**
     * 车辆类型
     */
    public String vehicleType;

    /**
     * 车辆电压
     */
    public String vehicleVoltage;

    /**
     * 载运类型
     */
    public String carryType;

    /**
     * 初始里程
     */
    public int initialMileage;

    /**
     * 百公里油耗
     */
    public double oilWear;

    /**
     * 使用年限
     */
    public int usefullife;

    /**
     * 安装日期
     */
    public Date installDate;

    /**
     * 年检日期
     */
    public Date annualSurveyDate;

    /**
     * 所属行政区域
     */
    public String adminArea;
    /**
     * 所属车辆图标
     */
    public String marker;
    /**
     * 旋转车辆图标否
     */
    public boolean rotate;

    /**
     * 备注
     */
    public String remark;

    /**
     * 创建时间
     */
    public Date createTime;

    /**
     * 时间戳
     */
    public Timestamp editTime;

}
