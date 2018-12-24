package mmp.gps.domain.gateway;

import java.util.ArrayList;
import java.util.List;

public class DataResolveRequest {
    private String host;
    private List<RawData> data;

    /**
     * 创建新的数据对象
     *
     * @param capacity 容量
     */
    public DataResolveRequest(int capacity) {
        data = new ArrayList<RawData>(capacity);
    }

    public DataResolveRequest() {

    }

    /**
     * 获取网关地址
     *
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置网关地址
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取原始数据列表
     *
     * @return
     */
    public List<RawData> getData() {
        return data;
    }

    /**
     * 设置原始数据列表
     *
     * @param data
     */
    public void setData(List<RawData> data) {
        this.data = data;
    }

    /**
     * 添加数据到列表
     *
     * @param raw 原始数据
     */
    public void apped(RawData raw) {
        data.add(raw);
    }

    /**
     * 添加数据到列表
     *
     * @param id   设备号
     * @param type 协议类型
     * @param raw  原始数据
     */
    public void append(String id, int type, String msn, String mcm, String raw) {
        data.add(new RawData(id, type, msn, mcm, raw));
    }

    /**
     * 添加数据到列表
     *
     * @param id   设备号
     * @param type 协议类型
     * @param raw  原始数据
     */
    public void append(String id, int type, String msn, String mcm, byte[] raw) {
        data.add(new RawData(id, type, msn, mcm, raw));
    }
}
