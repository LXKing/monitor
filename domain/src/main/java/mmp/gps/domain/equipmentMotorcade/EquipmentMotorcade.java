package mmp.gps.domain.equipmentMotorcade;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 设备车队类
 */
public class EquipmentMotorcade {


    private String id;
    private String deviceeId;
    private String deviceNumber;
    private String motorcadeId;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceeId() {
        return deviceeId;
    }

    public void setDeviceeId(String deviceeId) {
        this.deviceeId = deviceeId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getMotorcadeId() {
        return motorcadeId;
    }

    public void setMotorcadeId(String motorcadeId) {
        this.motorcadeId = motorcadeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "EquipmentMotorcade{" +
                "id='" + id + '\'' +
                ", deviceeId='" + deviceeId + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", motorcadeId='" + motorcadeId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
