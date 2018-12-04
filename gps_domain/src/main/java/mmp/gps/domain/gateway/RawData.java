package mmp.gps.domain.gateway;

import com.ning.http.util.Base64;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 原始数据
 */
public class RawData {
    private Date time = new Date();
    private String dn;
    private int type;
    private String msn;
    private String mcm;
    private String top;
    private List<String> after = new ArrayList<String>();

    /**
     * 创建新的原始数据对象
     */
    public RawData() {
    }

    /**
     * 创建新的原始数据对象
     *
     * @param dn   设备号
     * @param type 协议类型
     * @param raw  原始数据
     */
    public RawData(String dn, int type, String msn, String mcm, String top) {
        this.dn = dn;
        this.type = type;
        this.msn = msn;
        this.mcm = mcm;
        this.top = top;
    }

    /**
     * 创建新的原始数据对象
     *
     * @param dn   设备号
     * @param type 协议类型
     * @param raw  原始数据
     */
    public RawData(String dn, int type, String msn, String mcm, byte[] top) {
        this.dn = dn;
        this.type = type;
        this.msn = msn;
        this.mcm = mcm;
        this.top = Base64.encode(top);
    }

    /**
     * 获取接收时间
     *
     * @return 接收时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置接收时间
     *
     * @param time 接收时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取设备号
     *
     * @return
     */
    public String getDn() {
        return dn;
    }

    /**
     * 设置设备号
     *
     * @param dn
     */
    public void setDn(String dn) {
        this.dn = dn;
    }

    /**
     * 获取协议类型
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * 设置协议类型
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取对应平台流水号
     */
    public String getMsn() {
        return msn;
    }

    /**
     * 设置对应平台流水号
     *
     * @param msn
     */
    public void setMsn(String msn) {
        this.msn = msn;
    }

    /**
     * 获取对应消息命令
     *
     * @return 命令
     */
    public String getMcm() {
        return mcm;
    }

    /**
     * 设置对应消息命令
     *
     * @param mcm 命令
     */
    public void setMcm(String mcm) {
        this.mcm = mcm;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public List<String> getAfter() {
        return after;
    }

    public void setAfter(List<String> after) {
        this.after = after;
    }

    public void addAfter(byte[] raw) {
        this.after.add(Base64.encode(raw));
    }
}
