package com.rayton.gps.dao.baseinfo.maintain;

import java.sql.Date;
import java.util.List;

public interface IMaintainDao {
    /**
     * 查询保养总行数
     */
    int queryPageCount(String companyId, String plateNumber, Date from, Date to);

    /**
     * 查询保养页内容
     */
    List<MaintainInfo> queryPageDetail(String companyId, String plateNumber, Date from, Date to, int pageIndex, int
            pageSize);

    /**
     * 创建新的保养
     */
    void create(Maintain dto);

    /**
     * 修改保养
     */
    int update(Maintain dto);

    /**
     * 更新下次保养日期
     */
    void updateNextMaintainDate(String vehicleId, Date date);

    /**
     * 删除保养
     */
    void delete(String id);

    /**
     * 读取保养
     */
    Maintain fetch(String id);
}
