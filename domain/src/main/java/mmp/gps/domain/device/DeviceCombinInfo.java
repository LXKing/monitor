package mmp.gps.domain.device;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;

public class DeviceCombinInfo {


    private String id;
    private String pid;
    private String deviceId;
    private String userId;
    private String groupId;
    private int type;
    private String number;
    private String name;
    private Integer enable;
    private String sim;
    @JSONField(format = "yyyy-MM-dd")
    private Date ServiceStartTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date serviceEndTime;
    private String remark;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Override
    public String toString() {
        return "DeviceCombinInfo{" + "id='" + id + '\'' + ", pid='" + pid + '\'' + ", deviceId='" + deviceId + '\'' + ", userId='" + userId + '\'' + ", groupId='" + groupId + '\'' + ", type=" + type + ", number='" + number + '\'' + ", name='" + name + '\'' + ", enable=" + enable + ", sim='" + sim + '\'' + ", " + "ServiceStartTime=" + ServiceStartTime + ", serviceEndTime=" + serviceEndTime + ", remark='" + remark + '\'' + ", createTime=" + createTime + '}';
    }

    /**
     * 获取ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取父ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父ID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取设备ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取组别ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置组别ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取类型：0为设备组，1为设备
     */
    public int getType() {
        return type;
    }

    /**
     * 设置类型：0为设备组，1为设备
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取设备号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置设备号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取电话
     */
    public String getSim() {
        return sim;
    }

    /**
     * 设置电话
     */
    public void setSim(String sim) {
        this.sim = sim;
    }

    /**
     * 获取服务开始时间
     */
    public Date getServiceStartTime() {
        return ServiceStartTime;
    }

    /**
     * 设置服务开始时间
     */
    public void setServiceStartTime(Date serviceStartTime) {
        ServiceStartTime = serviceStartTime;
    }

    /**
     * 获取服务结束时间
     */
    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    /**
     * 设置服务结束时间
     */
    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
