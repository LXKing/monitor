package com.rayton.gps.service;

import com.rayton.gps.aop.Log;
import com.rayton.gps.common.ObjectId;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.maintain.IMaintainDao;
import com.rayton.gps.dao.baseinfo.maintain.Maintain;
import com.rayton.gps.dao.baseinfo.maintain.MaintainInfo;
import com.rayton.gps.util.Errors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class MaintainService {
    @Autowired
    private IMaintainDao maintainDao;

    public Page<MaintainInfo> query(String companyId, String plateNumber, Date from, Date to, int pageIndex, int
            pageSize) {
        int total = maintainDao.queryPageCount(companyId, plateNumber, from, to);

        Page<MaintainInfo> query = new Page<MaintainInfo>();
        query.total = total;
        if (total > 0) {
            List<MaintainInfo> rows = maintainDao.queryPageDetail(companyId, plateNumber, from, to, (pageIndex - 1) *
                    pageSize, pageSize);
            query.rows.addAll(rows);

        }
        return query;
    }

    @RequiresPermissions("baseinfo.maintain.create")
    @Log(name = "创建新的车辆保养记录")
    @Transactional
    public void Create(Maintain maintain) {
        maintain.setId(ObjectId.next());


        maintainDao.create(maintain);
        maintainDao.updateNextMaintainDate(maintain.getVehicleId(), maintain.getNextDate());
    }

    public Maintain fetch(String id) {
        Maintain maintain = maintainDao.fetch(id);


        return maintain;
    }

    @RequiresPermissions("baseinfo.maintain.update")
    @Log(name = "修改车辆保养记录")
    @Transactional
    public void update(Maintain maintain) {


        int rows = maintainDao.update(maintain);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);

        maintainDao.updateNextMaintainDate(maintain.getVehicleId(), maintain.getNextDate());
    }

    @RequiresPermissions("baseinfo.maintain.delete")
    @Log(name = "删除车辆保养记录")
    @Transactional
    public void delete(String id) {
        maintainDao.delete(id);
    }

}
