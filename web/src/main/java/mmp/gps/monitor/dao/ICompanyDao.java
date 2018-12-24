package mmp.gps.monitor.dao;

import mmp.gps.common.util.KeyValue;
import mmp.gps.domain.company.Company;
import mmp.gps.domain.company.CompanyInfoVo;
import mmp.gps.domain.permission.Permission;
import mmp.gps.domain.role.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司资料数据访问接口
 */

@Repository

public interface ICompanyDao {
    int queryPageCount(String pid, String filter);

    /**
     * 查询公司资料
     *
     * @param pid
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<CompanyInfoVo> queryPageDetail(String pid, String filter, int pageIndex, int pageSize);

    /**
     * 查询集团
     *
     * @param pid
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<CompanyInfoVo> queryPageDetails(String pid, String filter, int pageIndex, int pageSize);

    /**
     * 创建公司资料
     */
    void create(Company company);

    void createAdminRole(Role role);

    void assignAdminRole(String userId, String roleId);

    /**
     * 修改公司资料
     */
    int update(Company company);

    /**
     * 删除公司资料
     */
    void deleteCompany(String id);

    void deleteCircle(String id);

    void deleteDevice(String id);

    void deleteDriver(String id);

    void deleteEventmenu(String id);

    void deleteLog(String id);

    void deleteMaintain(String id);

    void deleteMaplayer(String id);

    void deleteMotorcade(String id);

    void deleteOwner(String id);

    void deletePoi(String id);

    void deletePolygon(String id);

    void deleteRectangle(String id);

    void deleteRole(String id);

    void deleteRoleinuser(String id);

    void deleteRoleauthorize(String id);

    void deleteRoute(String id);

    void deleteSimcard(String id);

    void deleteVehicle(String id);

    void deleteCompanyauthorize(String id);

    void deleteUser(String id);

    void deleteAdminAuthorize(String id);

    /**
     * 读取公司资料
     */
    Company fetch(String id);

    /**
     * 是否有子公司资料
     */
    boolean hasSub(String id);


    List<CompanyInfoVo> getSub(String id);

    CompanyInfoVo getSup(String id);

    CompanyInfoVo getCur(String id);


    /**
     * 读取公司已授权限
     */
    List<String> authorizes(String companyId);

    /**
     * 公司授权
     */
    void assignCompanyAuthorize(List<KeyValue> rows);

    void assingAdminAuthorize(List<KeyValue> rows);

    /**
     * 清理企业角色已分配，但现在已解除的权限
     */
    void cleanRoleauthorize(String companyId);

    Integer createPermission(Permission permission);

    Integer deletePermission(Permission permission);

    Integer updatePermission(Permission permission);


    /**
     * 查询总公司
     *
     * @param id
     * @return
     */
    List<CompanyInfoVo> getCompanyByPid(String id);

}
