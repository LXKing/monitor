package com.rayton.gps.dao.locate;

import java.sql.Date;

/**
 * 车辆基本信息类
 *
 * @author yangzs
 */
public class VehicleBaseInfoDto {
    /**
     * 所属企业
     */
    public String company;

    /**
     * 所属车队
     */

    public String motorcade;

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
    public int usefulLife;

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
     * 备注
     */
    public String remark;

}
