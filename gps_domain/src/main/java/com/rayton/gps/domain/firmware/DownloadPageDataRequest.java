package com.rayton.gps.domain.firmware;

import org.hibernate.validator.constraints.Range;

/**
 * 下载页数据
 *
 * @author 生
 */
public class DownloadPageDataRequest {
    @Range(min = 0, max = 254)
    private short factoryId;
    @Range(min = 0, max = 254)
    private short pageIndex;

    /**
     * 获取车厂id
     */
    public short getFactoryId() {
        return factoryId;
    }

    /**
     * 设置车厂id
     */
    public void setFactoryId(short factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取页序号
     */
    public short getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置页序号
     */
    public void setPageIndex(short pageIndex) {
        this.pageIndex = pageIndex;
    }
}
