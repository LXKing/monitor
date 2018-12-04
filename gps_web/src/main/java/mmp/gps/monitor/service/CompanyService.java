package mmp.gps.monitor.service;

import mmp.gps.common.util.*;
import mmp.gps.common.enums.UserKinds;
import mmp.gps.domain.company.Company;
import mmp.gps.domain.company.CompanyInfoVo;
import mmp.gps.domain.role.Role;
import mmp.gps.domain.user.User;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.ICompanyDao;
import mmp.gps.monitor.dao.IUserDao;
import mmp.gps.monitor.service.shiro.ShiroRealm;
import mmp.gps.monitor.util.MD5;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司服务类
 */
@Service
public class CompanyService {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private IUserDao userDao;


    @Autowired
    private UserService userService;

    @Autowired
    private ShiroRealm shiroRealm;

    public Page<CompanyInfoVo> query(String pid, String filter, int pageIndex, int pageSize) throws Exception {
        int total = companyDao.queryPageCount(pid, filter);
        List<CompanyInfoVo> rows = companyDao.queryPageDetail(pid, filter, (pageIndex - 1) * pageSize, pageSize);


        // rows.forEach(companyInfoDto -> {
        //     System.out.println(companyInfoDto);
        // });
        Page<CompanyInfoVo> query = new Page<>();
        query.total = total;
        query.rows.addAll(rows);


        return query;
    }

    public CompanyInfoVo getMmp(String pid) {
        CompanyInfoVo companyInfoVo = companyDao.getCur(getTop(pid));
        setSub(companyInfoVo);
        return companyInfoVo;
    }

    private String getTop(String id) {

        CompanyInfoVo list = companyDao.getSup(id);
        if (list != null) {

            return getTop(list.getId());

        } else {
            return id;
        }

    }

    private void setSub(CompanyInfoVo companyInfoVo) {

        List<CompanyInfoVo> list = companyDao.getSub(companyInfoVo.getId());

        if (list.size() > 0) {
            companyInfoVo.setNodes(list);
            list.forEach(vo -> setSub(vo));
        }


    }

    /**
     * 查询总公司
     *
     * @param id
     * @return
     */
    public List<CompanyInfoVo> getCompanyByPid(String id) {
        return companyDao.getCompanyByPid(id);

    }

    public Page<CompanyInfoVo> querys(String pid, String filter, int pageIndex, int pageSize) throws Exception {
        int total = companyDao.queryPageCount(pid, filter);
        List<CompanyInfoVo> rows = companyDao.queryPageDetails(pid, filter, (pageIndex - 1) * pageSize, pageSize);

        // rows.forEach(companyInfoDto -> {
        //     System.out.println(companyInfoDto);
        // });
        Page<CompanyInfoVo> query = new Page<>();
        query.total = total;
        query.rows.addAll(rows);


        return query;
    }

    @RequiresPermissions("baseinfo.company.create")
    @Log(name = "创建企业资料")
    @Transactional
    public void create(Company company, User user) throws Exception {
        String id = ObjectId.next();
        company.setId(id);
        user.setId(id);

        Company c = new Company();
        BeanUtils.copyProperties(company, c);

        // user.setPassword("888888");
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


        String credentials = "888888";

        user.setPassword(credentials);
        try {
            credentials = MD5.doMD5(user);
            user.setPassword(credentials);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("dd");
        try {
            int a = userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // userService.doCreate(user);

        // userService.create(user);
        // System.out.println("ee");
        Role role = new Role();
        role.setId(id);
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

    @RequiresPermissions("baseinfo.company.update")
    @Log(name = "修改企业资料")
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

        String credentials = "888888";

        user.setPassword(credentials);
        try {
            credentials = MD5.doMD5(user);
            user.setPassword(credentials);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
        rows = userDao.update(user);
        if (rows != 1)
            throw new RuntimeException(Errors.anotherEdited);
    }

    @RequiresPermissions("baseinfo.company.delete")
    @Log(name = "删除企业资料")
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

    @RequiresPermissions("baseinfo.company.authorize")
    @Log(name = "企业授权")
    @Transactional
    public void authorize(String companyId, List<String> list) throws Exception {
        List<KeyValue> rows = new ArrayList<>();
        for (String id : list) {
            KeyValue row = new KeyValue();
            row.setKey(companyId);
            row.setValue(id);

            rows.add(row);
        }
        // 大写的服。先全部删除然后再次授权
        companyDao.deleteCompanyauthorize(companyId);
        companyDao.deleteRoleauthorize(companyId);
        companyDao.deleteAdminAuthorize(companyId);
        if (rows.size() != 0) {
            companyDao.assignCompanyAuthorize(rows);
            companyDao.assingAdminAuthorize(rows);
        }

        companyDao.cleanRoleauthorize(companyId);
        shiroRealm.clearCached();
        // AuthorizeCache.reload();
    }

}
