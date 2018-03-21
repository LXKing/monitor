package com.edata.monitor.service;

import com.edata.common.ObjectId;
import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.owner.IOwnerDao;
import com.edata.monitor.dao.baseinfo.owner.Owner;
import com.edata.monitor.dao.baseinfo.owner.OwnerInfo;
import com.edata.monitor.dao.baseinfo.user.IUserDao;
import com.edata.monitor.dao.baseinfo.user.User;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfo;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfoDto;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.enums.UserKinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 车主服务类
 *
 * @author yangzs
 */
@Service
public class OwnerService {
    @Autowired
    private IOwnerDao ownerDao;
    @Autowired
    private IUserDao userDao;

    public Page<OwnerInfo> query(String ownerId, String filter, int pageIndex, int pageSize) throws Exception {
        int total = ownerDao.queryPageCount(ownerId, filter);
        List<OwnerInfo> rows = ownerDao.queryPageDetail(ownerId, filter, (pageIndex - 1) * pageSize, pageSize);
        Page<OwnerInfo> query = new Page<OwnerInfo>();
        query.total = total;
        query.rows.addAll(rows);


        return query;
    }

    @ServiceMethod(id = "baseinfo.owner.create", pid = "baseinfo.owner", name = "创建新的车主资料")
    @Transactional
    public void create(Owner owner, User user) throws Exception {
        String id = ObjectId.next();
        owner.setId(id);
        user.setId(id);

        user.setKind(UserKinds.Owner.getIndex());
        user.setPassword("888888");
        // UserDto u = new UserDto();
        // u.id = user.getId();
        // u.pid = user.getPid();
        // u.companyId = user.getCompanyId();
        // u.kind = UserKinds.Owner.getIndex();
        // u.account = user.getAccount();
        // u.name = user.getName();
        // u.contact = user.getContact();
        // u.createTime = user.getCreateTime();
        // u.email = user.getEmail();
        // u.enable = user.isEnable();
        // u.password = "888888";
        // u.phone = user.getPhone();
        // u.remark = user.getRemark();
        // u.serviceEndDate = user.getServiceEndDate();
        // u.serviceStartDate = user.getServiceStartDate();

        ownerDao.create(owner);
        userDao.create(user);
    }

    public Tuple<Owner, User> fetch(String id) throws Exception {
        Owner owner = ownerDao.fetch(id);


        User user = userDao.fetch(id);
        // User user = new User();
        // user.setId(u.id);
        // user.setPid(u.pid);
        // user.setCompanyId(u.companyId);
        // user.setKind(u.kind);
        // user.setAccount(u.account);
        // user.setName(u.name);
        // user.setEmail(u.email);
        // user.setPhone(u.phone);
        // user.setContact(u.contact);
        // user.setCreateTime(u.createTime);
        // user.setEnable(u.enable);
        // user.setEditTime(u.editTime);
        // user.setServiceStartDate(u.serviceStartDate);
        // user.setServiceEndDate(u.serviceEndDate);
        // user.setRemark(u.remark);

        return Tuple.of(owner, user);
    }

    @ServiceMethod(id = "baseinfo.owner.update", pid = "baseinfo.owner", name = "修改车主资料")
    @Transactional
    public void update(Owner owner, User user) throws Exception {

        user.setKind(UserKinds.Owner.getIndex());
        // UserDto u = new UserDto();
        // u.id = user.getId();
        // u.pid = user.getPid();
        // u.companyId = user.getCompanyId();
        // u.kind = UserKinds.Owner.getIndex();
        // u.account = user.getAccount();
        // u.name = user.getName();
        // u.contact = user.getContact();
        // u.createTime = user.getCreateTime();
        // u.email = user.getEmail();
        // u.enable = user.isEnable();
        // u.phone = user.getPhone();
        // u.remark = user.getRemark();
        // u.serviceEndDate = user.getServiceEndDate();
        // u.serviceStartDate = user.getServiceStartDate();
        // u.editTime = user.getEditTime();

        int rows = ownerDao.update(owner);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
        rows = userDao.update(user);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @ServiceMethod(id = "baseinfo.owner.delete", pid = "baseinfo.owner", name = "删除车主资料")
    public void delete(String id) throws Exception {
        ownerDao.delete(id);
    }

    public List<VehicleInfo> assignedVehicles(String ownerId) throws Exception {
        List<VehicleInfoDto> vehicles = ownerDao.assignedVehicles(ownerId);

        List<VehicleInfo> list = new ArrayList<VehicleInfo>();
        for (VehicleInfoDto dto : vehicles) {
            VehicleInfo info = new VehicleInfo();
            info.setId(dto.id);
            info.setDeviceNumber(dto.deviceNumber);
            info.setPhoneNumber(dto.phoneNumber);
            info.setPlateNumber(dto.plateNumber);
            info.setPlateColor(dto.plateColor);
            info.setInstallDate(dto.installDate);
            info.setAnnualSurveyDate(dto.annualSurveyDate);
            info.setMotorcade(dto.motorcade);
            info.setRemark(dto.remark);
            list.add(info);
        }

        return list;
    }

    @ServiceMethod(id = "baseinfo.owner.addVehicles", pid = "baseinfo.owner", name = "车主绑定车辆")
    public void addVehicles(String ownerId, List<String> vehicles) throws Exception {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (String id : vehicles) {
            KeyValue row = new KeyValue();
            row.setKey(ownerId);
            row.setValue(id);

            rows.add(row);
        }
        ownerDao.addVehicles(rows);
    }

    @ServiceMethod(id = "baseinfo.owner.removeVehicle", pid = "baseinfo.owner", name = "车主解除车辆")
    public void removeVehicle(String ownerId, String vehicleId) throws Exception {
        ownerDao.removeVehicle(ownerId, vehicleId);
    }
}
