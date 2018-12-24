package mmp.gps.monitor.dao;

import mmp.gps.domain.security.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISecurityDao {


    IdentityDto get(String account) throws Exception;

    // WTF？？？自己看
    IdentityDto login(String account, String password) throws Exception;

    String getPasswordByUnid(String unid) throws Exception;

    String getPasswodById(String id) throws Exception;

    MyinfoDto getMyInfo(String id) throws Exception;

    int saveMyInfo(MyinfoDto dto) throws Exception;

    int saveMyKey(String id, String newKey) throws Exception;

    void saveOperateLogs(List<OperateLog> operateLogs) throws Exception;

    List<RoleAuthorizeDto> roleAuthorizes() throws Exception;

    ParentDto getParentCompany(String companyId) throws Exception;

    List<String> getRoles(String id) throws Exception;
}
