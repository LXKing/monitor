package com.rayton.gps.domain.device;

import com.rayton.gps.domain.track.Track;

import java.util.Date;

/**
 * 设备信息类
 *
 * @author 生
 */
public class Device {
    private String number;
    private String host;
    private int protocolKind;
    private boolean debugging;
    private boolean upgrading;
    private boolean matching;
    private boolean sleeping;
    private boolean repairing;
    private String preVer;
    private Date upgradeStart;
    private String curVer;
    private Date upgradeEnd;
    private String matchResult;
    private Date matchTime;
    private Date serverTime = new Date();
    private Track track;

    /**
     * 获取设备号
     *
     * @return 设备号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置设备号
     *
     * @param number 设备号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取设备所在主机
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置设备所在主机
     */
    public void setHost(String host) {
        if (!host.equals(this.host))
            this.host = host;
    }

    /**
     * 获取协议类型
     */
    public int getProtocolKind() {
        return protocolKind;
    }

    /**
     * 设置协议类型
     */
    public void setProtocolKind(int protocolKind) {
        this.protocolKind = protocolKind;
    }

    /**
     * 获取是否调试中
     *
     * @return 是否调试中
     */
    public boolean isDebugging() {
        return debugging;
    }

    /**
     * 设置是否调试中
     *
     * @param debugging 是否调试中
     */
    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }

    /**
     * 获取是否升级中
     *
     * @return 是否升级中
     */
    public boolean isUpgrading() {
        return upgrading;
    }

    /**
     * 设置是否升级中
     *
     * @param upgrading 是否升级中
     */
    public void setUpgrading(boolean upgrading) {
        this.upgrading = upgrading;
    }

    /**
     * 获取是否全车匹配中
     *
     * @return 是否全车匹配中
     */
    public boolean isMatching() {
        return matching;
    }

    /**
     * 设置是否全车匹配中
     *
     * @param matching 是否全车匹配中
     */
    public void setMatching(boolean matching) {
        this.matching = matching;
    }

    /**
     * 获取是否休眠中
     *
     * @return 是否休眠中
     */
    public boolean isSleeping() {
        return sleeping;
    }

    /**
     * 设置是否休眠中
     *
     * @param sleeping 是否休眠中
     */
    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    /**
     * 获取是否为维修模式
     *
     * @return 是否为维修模式
     */
    public boolean isRepairing() {
        return repairing;
    }

    /**
     * 设置是否为维修模式
     *
     * @param repairing 是否为维修模式
     */
    public void setRepairing(boolean repairing) {
        this.repairing = repairing;
    }

    /**
     * 获取上一固件版本号
     *
     * @return 上一固件版本号
     */
    public String getPreVer() {
        return preVer;
    }

    /**
     * 设置上一固件版本号
     *
     * @param preVer 上一固件版本号
     */
    public void setPreVer(String preVer) {
        this.preVer = preVer;
    }

    /**
     * 获取升级开始时间
     *
     * @return 升级开始时间
     */
    public Date getUpgradeStart() {
        return upgradeStart;
    }

    /**
     * 设置升级开始时间
     *
     * @param upgradeStart 升级开始时间
     */
    public void setUpgradeStart(Date upgradeStart) {
        this.upgradeStart = upgradeStart;
    }

    /**
     * 获取当前固件版本号
     *
     * @return 当前固件版本号
     */
    public String getCurVer() {
        return curVer;
    }

    /**
     * 设置当前固件版本号
     *
     * @param curVer 当前固件版本号
     */
    public void setCurVer(String curVer) {
        this.curVer = curVer;
    }

    /**
     * 获取升级结束时间
     *
     * @return 升级结束时间
     */
    public Date getUpgradeEnd() {
        return upgradeEnd;
    }

    /**
     * 设置升级结束时间
     *
     * @param upgradeEnd 升级结束时间
     */
    public void setUpgradeEnd(Date upgradeEnd) {
        this.upgradeEnd = upgradeEnd;
    }

    /**
     * 获取匹配结果
     *
     * @return 匹配结果
     */
    public String getMatchResult() {
        return matchResult;
    }

    /**
     * 设置匹配结果
     *
     * @param matchResult 匹配结果
     */
    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    /**
     * 获取匹配完成时间
     *
     * @return 匹配完成时间
     */
    public Date getMatchTime() {
        return matchTime;
    }

    /**
     * 设置匹配完成时间
     *
     * @param matchTime 匹配完成时间
     */
    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    /**
     * 获取服务器时间
     */
    public Date getServerTime() {
        return serverTime;
    }

    /**
     * 设置服务器时间
     */
    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * 设置在线状态
     */
    public void setOnline() {
        serverTime = new Date();
    }

    /**
     * 是否在线
     *
     * @param timeout 超时时间，毫秒
     */
    public int isOnline(int timeout) {
        if (serverTime == null)
            return 0;
        Date now = new Date();
        return Math.abs((serverTime.getTime() - now.getTime())) < timeout ? 1 : 0;
    }

    /**
     * 获取当前轨迹
     */
    public Track getTrack() {
        return track;
    }

    /**
     * 设置当前轨迹
     */
    public synchronized void setTrack(Track dto) {
        if (dto.getVal() == 0)
            return;
        if (this.track == null) {
            this.track = dto;
            return;
        }
        long prev = this.track.getGt().getTime();
        long curr = dto.getGt().getTime();

        if (curr > prev)
            this.track = dto;
    }
}
