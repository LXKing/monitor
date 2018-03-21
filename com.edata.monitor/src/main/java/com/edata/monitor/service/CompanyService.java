package com.edata.monitor.service;

import com.edata.common.ObjectId;
import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AuthorizeCache;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.company.Company;
import com.edata.monitor.dao.baseinfo.company.CompanyInfo;
import com.edata.monitor.dao.baseinfo.company.ICompanyDao;
import com.edata.monitor.dao.baseinfo.role.Role;
import com.edata.monitor.dao.baseinfo.user.IUserDao;
import com.edata.monitor.dao.baseinfo.user.User;
import com.edata.monitor.util.KeyValue;
import com.edata.monitor.util.enums.UserKinds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司服务类
 *
 * @author yangzs
 */
@Service
public class CompanyService {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private IUserDao userDao;

    public Page<CompanyInfo> query(String pid, String filter, int pageIndex, int pageSize) throws Exception {
        int total = companyDao.queryPageCount(pid, filter);
        List<CompanyInfo> rows = companyDao.queryPageDetail(pid, filter, (pageIndex - 1) * pageSize, pageSize);

        // rows.forEach(companyInfoDto -> {
        //     System.out.println(companyInfoDto);
        // });
        Page<CompanyInfo> query = new Page<CompanyInfo>();
        query.total = total;
        query.rows.addAll(rows);


        return query;
    }

    @ServiceMethod(id = "baseinfo.company.create", pid = "baseinfo.company", name = "创建企业资料")
    @Transactional
    public void create(Company company, User user) throws Exception {
        String id = ObjectId.next();
        company.setId(id);
        user.setId(id);

        Company c = new Company();
        BeanUtils.copyProperties(company, c);

        user.setPassword("888888");
        user.setKind(UserKinds.CompanyAdmin.getIndex());
        user.setCompanyId(user.getId());
        // UserDto u = new UserDto();
        // u.id = user.getId();
        // u.pid = user.getPid();
        // u.companyId = u.id;
        // u.kind = UserKinds.CompanyAdmin.getIndex();
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

        companyDao.create(c);
        userDao.create(user);

        Role role = new Role();
        role.setId(ObjectId.next());
        role.setCompanyId(company.getId());
        role.setName("企业管理员");
        role.setRemark("");

        companyDao.createAdminRole(role);
        companyDao.assignAdminRole(user.getId(), role.getId());
    }

    public Tuple<Company, User> fetch(String id) throws Exception {
        Company c = companyDao.fetch(id);


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

        return Tuple.of(c, user);

    }

    @ServiceMethod(id = "baseinfo.company.update", pid = "baseinfo.company", name = "修改企业资料")
    @Transactional
    public void update(Company company, User user) throws Exception {
        Company c = new Company();
        BeanUtils.copyProperties(company, c);

        user.setKind(UserKinds.CompanyAdmin.getIndex());
        user.setCompanyId(user.getId());


        // UserDto u = new UserDto();
        // u.id = user.getId();
        // u.pid = user.getPid();
        // u.companyId = u.id;
        // u.kind = UserKinds.CompanyAdmin.getIndex();
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

        int rows = companyDao.update(c);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
        rows = userDao.update(user);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @ServiceMethod(id = "baseinfo.company.delete", pid = "baseinfo.company", name = "删除企业资料")
    @Transactional
    public void delete(String id) throws Exception {
        companyDao.deleteCircle(id);
        companyDao.deleteDevice(id);
        companyDao.deleteDriver(id);
        companyDao.deleteLog(id);
        companyDao.deleteMaintain(id);
        companyDao.deleteMaplayer(id);
        companyDao.deleteMotorcade(id);
        companyDao.deleteOwner(id);
        companyDao.deletePoi(id);
        companyDao.deletePolygon(id);
        companyDao.deleteRectangle(id);
        companyDao.deleteRole(id);
        companyDao.deleteRoleinuser(id);
        companyDao.deleteRoleauthorize(id);
        companyDao.deleteRoute(id);
        companyDao.deleteSimcard(id);
        companyDao.deleteVehicle(id);
        companyDao.deleteCompany(id);
        companyDao.deleteCompanyauthorize(id);
        companyDao.deleteUser(id);
    }

    public List<String> authorizes(String companyId) throws Exception {
        return companyDao.authorizes(companyId);
    }

    @ServiceMethod(id = "baseinfo.company.authorize", pid = "baseinfo.company", name = "企业授权")
    @Transactional
    public void authorize(String companyId, List<String> list) throws Exception {
        List<KeyValue> rows = new ArrayList<KeyValue>();
        for (String id : list) {
            KeyValue row = new KeyValue();
            row.setKey(companyId);
            row.setValue(id);

            rows.add(row);
        }
        companyDao.deleteCompanyauthorize(companyId);
        companyDao.deleteRoleauthorize(companyId);
        companyDao.deleteAdminAuthorize(companyId);
        companyDao.assignCompanyAuthorize(rows);
        companyDao.assingAdminAuthorize(rows);
        companyDao.cleanRoleauthorize(companyId);
        AuthorizeCache.reload();
    }
}
