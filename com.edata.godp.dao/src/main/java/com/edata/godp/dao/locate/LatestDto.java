package com.edata.godp.dao.locate;

import java.util.Date;

/**
 * 最后位置信息
 */
public class LatestDto {
    /**
     * 设备号
     */
    public String dn;
    /**
     * 定位时间
     */
    public Date gt;
    /**
     * 服务器时间
     */
    public Date st;
    /**
     * 经度
     */
    public double lng;
    /**
     * 纬度
     */
    public double lat;
    /**
     * 速度
     */
    public int sp;
    /**
     * 方向
     */
    public int d;
    /**
     * 报警
     */
    public long a;
    /**
     * 状态
     */
    public long s;
    /**
     * 里程
     */
    public long m;
    /**
     * 油量
     */
    public float oil;

    /**
     * 获取车速
     */
    public float vss;

    /**
     * 超速区域类型
     */
    public byte ovt;

    /**
     * 超速区域id
     */
    public long oid;

    /**
     * 进出区域类型
     */
    public byte iot;

    /**
     * 进出区域id
     */
    public long iid;

    /**
     * 进出区域方向
     */
    public byte iof;

    /**
     * 行驶路段id
     */
    public long rid;

    /**
     * 行驶路段时间
     */
    public int rt;

    /**
     * 行驶路段结果
     */
    public byte rf;
    /**
     * 人工确认报警id
     */
    public int aid;

    /**
     * 扩展车辆信号状态位
     */
    public long exs;

    /**
     * IO状态位
     */
    public int ios;

    /**
     * 模拟量AD0
     */
    public int ad0;

    /**
     * 模拟量AD1
     */
    public int ad1;

    /**
     * 网络强度
     */
    public short net;

    /**
     * 卫星数量
     */
    public short sat;

}
