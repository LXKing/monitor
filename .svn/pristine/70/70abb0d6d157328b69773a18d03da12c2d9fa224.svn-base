package com.edata.monitor.dao.baseinfo.sim;

import java.util.List;

/**
 * sim卡数据访问接口
 *
 * @author yangzs
 */
public interface ISimcardDao {
    /**
     * 查询sim卡总行数
     */
    int queryPageCount(String companyId, String filter);

    /**
     * 查询sim卡内容
     */
    List<Simcard> queryPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    /**
     * 创建sim卡
     */
    void create(Simcard dto);

    /**
     * 修改sim卡
     */
    int update(Simcard dto);

    /**
     * 是否已绑定设备
     */
    String assignedDevice(String id);

    /**
     * 删除sim卡
     */
    void delete(String id);

    /**
     * 读取sim卡
     */
    Simcard fetch(String id);

    /**
     * 是否已存在sim卡
     */
    boolean exist(String simcardNumber);

    /**
     * 是否已存在sim卡
     */
    boolean existOutId(String simcardNumber, String id);

    /**
     * 搜索simk总行数
     */
    int searchPageCount(String companyId, String filter);

    /**
     * 搜索simk页内容
     */
    List<SimcardSearchInfo> searchPageDetail(String companyId, String filter, int pageIndex, int pageSize);

    /**
     * 查找未绑定设备的sim卡总行数
     */
    int freePageCount(String companyId, String phoneNumber);

    /**
     * 查找未绑定设备的sim卡页内容
     */
    List<SimcardSearchInfo> freePageDetail(String companyId, String phoneNumber, int pageIndex, int pageSize);
}
