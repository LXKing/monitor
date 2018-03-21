package com.edata.monitor.service;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.maintain.IMaintainDao;
import com.edata.monitor.dao.baseinfo.maintain.Maintain;
import com.edata.monitor.dao.baseinfo.maintain.MaintainInfo;
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

    @ServiceMethod(id = "baseinfo.maintain.create", pid = "baseinfo.maintain", name = "创建新的车辆保养记录")
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

    @ServiceMethod(id = "baseinfo.maintain.update", pid = "baseinfo.maintain", name = "修改车辆保养记录")
    @Transactional
    public void update(Maintain maintain) {


        int rows = maintainDao.update(maintain);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);

        maintainDao.updateNextMaintainDate(maintain.getVehicleId(), maintain.getNextDate());
    }

    @ServiceMethod(id = "baseinfo.maintain.delete", pid = "baseinfo.maintain", name = "删除车辆保养记录")
    @Transactional
    public void delete(String id) {
        maintainDao.delete(id);
    }

}
