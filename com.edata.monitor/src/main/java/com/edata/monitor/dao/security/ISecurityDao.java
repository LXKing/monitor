package com.edata.monitor.dao.security;

import java.util.List;

public interface ISecurityDao {

    IdentifyDto login(String account, String password) throws Exception;

    String getPasswordByUnid(String unid) throws Exception;

    String getPasswodById(String id) throws Exception;

    MyInfoDto getMyInfo(String id) throws Exception;

    int saveMyInfo(MyInfoDto dto) throws Exception;

    int saveMyKey(String id, String newKey) throws Exception;

    void saveOperateLogs(List<OperateLog> operateLogs) throws Exception;

    List<RoleAuthorizeDto> roleAuthorizes() throws Exception;

    ParentDto getParentCompany(String companyId) throws Exception;

    List<String> getRoles(String id) throws Exception;
}
