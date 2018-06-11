package com.rayton.gps.dao.baseinfo.maintain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 车辆保养类
 *
 * @author yangzs
 */
public class Maintain {
    private String id;
    private String companyId;
    @NotEmpty
    private String vehicleId;
    @NotEmpty
    private String plateNumber;
    @JSONField(format = "yyyy-MM-dd")
    private Date doneDate;
    private String type;
    private String content;
    private int mileage;
    private double fee;
    private String garage;
    private String agent;
    @JSONField(format = "yyyy-MM-dd")
    private Date nextDate;
    private int nextMileage;
    private String userId;
    private String userName;
    private Timestamp editTime;

    @Override
    public String toString() {
        return "Maintain{" + "id='" + id + '\'' + ", companyId='" + companyId + '\'' + ", vehicleId='" + vehicleId + '\'' + ", plateNumber='" + plateNumber + '\'' + ", doneDate=" + doneDate + ", type='" + type + '\'' + ", content='" + content + '\'' + ", mileage=" + mileage + ", fee=" + fee + ", garage='" + garage + '\'' + ", agent='" + agent + '\'' + ", nextDate=" + nextDate + ", nextMileage=" + nextMileage + ", " + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", editTime=" + editTime + '}';
    }

    /**
     * 获取录唯一编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置录唯一编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取公司唯一编号
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司唯一编号
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取车辆id
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车辆id
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 获取车辆号
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * 设置车辆号
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * 获取保养日期
     */
    public Date getDoneDate() {
        return doneDate;
    }

    /**
     * 设置保养日期
     */
    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    /**
     * 获取保养类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置保养类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取保养内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置保养内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取保养里程
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * 设置保养里程
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * 获取保养费用
     */
    public double getFee() {
        return fee;
    }

    /**
     * 设置保养费用
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * 获取保养单位
     */
    public String getGarage() {
        return garage;
    }

    /**
     * 设置保养单位
     */
    public void setGarage(String garage) {
        this.garage = garage;
    }

    /**
     * 获取经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置经办人
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * 获取下次保养日期
     */
    public Date getNextDate() {
        return nextDate;
    }

    /**
     * 设置下次保养日期
     */
    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    /**
     * 获取下次保养里程
     */
    public int getNextMileage() {
        return nextMileage;
    }

    /**
     * 设置下次保养里程
     */
    public void setNextMileage(int nextMileage) {
        this.nextMileage = nextMileage;
    }

    /**
     * 获取操作员ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置操作员ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取操作员姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作员姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取时间戳
     */
    public Timestamp getEditTime() {
        return editTime;
    }

    /**
     * 设置时间戳
     */
    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

}
