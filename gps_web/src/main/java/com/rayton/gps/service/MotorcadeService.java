package com.rayton.gps.service;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.common.ObjectId;
import com.rayton.gps.dao.baseinfo.motorcade.IMotorcadeDao;
import com.rayton.gps.dao.baseinfo.motorcade.Motorcade;
import com.rayton.gps.dao.baseinfo.motorcade.MotorcadeInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 车队服务类
 *
 * @author yangzs
 */
@Service
public class MotorcadeService {
    @Autowired
    private IMotorcadeDao motorcadeDao;

    /**
     * 读取企业所有车队
     *
     * @param companyId 企业唯一编号
     */
    public List<MotorcadeInfo> list(String companyId) {
        List<Motorcade> list = motorcadeDao.list(companyId);
        List<MotorcadeInfo> motorcades = new ArrayList<MotorcadeInfo>(list.size());
        list.forEach(motorcade -> {
            MotorcadeInfo info = new MotorcadeInfo();
            BeanUtils.copyProperties(motorcade, info);
            motorcades.add(info);
        });
        // for (Motorcade dto : list) {
        //     MotorcadeInfo info = new MotorcadeInfo();
        //     info.setId(dto.id);
        //     info.setType(dto.type);
        //     info.setName(dto.name);
        //     info.setParentVisible(dto.parentVisible);
        //     info.setRemark(dto.remark);
        //
        //     motorcades.add(info);
        // }
        return motorcades;
    }

    /**
     * 创建新的车队
     */
    @RequiresPermissions("baseinfo.motorcade.create")
    @ServiceMethod(id = "baseinfo.motorcade.create", pid = "baseinfo.motorcade", name = "创建新的车队")
    @Transactional
    public void create(Motorcade motorcade) {
        motorcade.setId(ObjectId.next());


        motorcadeDao.create(motorcade);
    }

    /**
     * 读取车队资料
     *
     * @param id 车队唯一编号
     */
    public Motorcade fetch(String id) {
        Motorcade motorcade = motorcadeDao.fetch(id);


        return motorcade;
    }

    /**
     * 更新车队资料
     */
    @RequiresPermissions("baseinfo.motorcade.update")
    @ServiceMethod(id = "baseinfo.motorcade.update", pid = "baseinfo.motorcade", name = "更新车队资料")
    @Transactional
    public void update(Motorcade motorcade) {


        int rows = motorcadeDao.update(motorcade);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    /**
     * 删除车队
     *
     * @param id 车队唯一编号
     */
    @RequiresPermissions("baseinfo.motorcade.delete")
    @ServiceMethod(id = "baseinfo.motorcade.delete", pid = "baseinfo.motorcade", name = "删除车队资料")
    @Transactional
    public void delete(String id) {
        if (motorcadeDao.hasVehicle(id))
            throw new RuntimeException("该车队已存放车辆资料，不能被删除！");
        motorcadeDao.delete(id);
    }

    /**
     * 是否已存在企业车队
     *
     * @param name      车队名称
     * @param companyId 企业唯一编号
     */
    public boolean exist(String name, String companyId) {
        return motorcadeDao.exist(name, companyId);
    }

    /**
     * 是否已存在企业车队
     *
     * @param name      车队名称
     * @param companyId 企业唯一编号
     * @param id        车队唯一编号
     */
    public boolean exist(String name, String companyId, String id) {
        return motorcadeDao.existOutId(name, companyId, id);
    }
}
