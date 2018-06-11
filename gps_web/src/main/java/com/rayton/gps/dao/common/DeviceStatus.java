package com.rayton.gps.dao.common;

import java.util.Date;

public class DeviceStatus {

    private String number;
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

    @Override
    public String toString() {
        return "DeviceStatus{" + "number='" + number + '\'' + ", debugging=" + debugging + ", upgrading=" + upgrading + ", matching=" + matching + ", sleeping=" + sleeping + ", repairing=" + repairing + ", preVer='" + preVer + '\'' + ", upgradeStart=" + upgradeStart + ", curVer='" + curVer + '\'' + ", upgradeEnd=" + upgradeEnd + ", matchResult='" + matchResult + '\'' + ", matchTime=" + matchTime + '}';
    }

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
}
