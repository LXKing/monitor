package mmp.gps.logic.component;

import mmp.gps.domain.exist.ExistRequest;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.role.AuthorizeRequest;
import mmp.gps.domain.user.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class UserComponent {

    @Autowired
    private UserService userService;


    public UserComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "user.create",
            description = "创建新的用户信息"
    )
    public void create(CreateUserRequest request) throws Exception {
        this.userService.create(request);
    }

    @ServiceMethod(
            value = "user.update",
            description = "更新用户信息"
    )
    public void update(EditUserRequest request) throws Exception {
        this.userService.update(request);
    }

    @ServiceMethod(
            value = "user.fetch",
            description = "读取用户信息"
    )
    public FetchUserResponse fetch(FetchUserRequest request) throws Exception {
        return this.userService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "user.query",
            description = "查询用户信息"
    )
    public QueryUserResponse query(QueryUserRequest request) throws Exception {
        return this.userService.query(request.getFilter(), request.getPage(), request.getPageSize());
    }

    @ServiceMethod(
            value = "user.delete",
            description = "删除用户信息"
    )
    public void delete(DeleteUserRequest request) throws Exception {
        this.userService.delete(request.getId());
    }

    @ServiceMethod(
            value = "user.authorize",
            description = "用户授权"
    )
    public void authorize(AuthorizeRequest request) throws Exception {
        this.userService.authorize(

                request.getRoleId(), request.getUserId(), request.isAllow());
    }

    @ServiceMethod(
            value = "user.exists",
            allowAnoumous = true,
            description = "检查是否已存在用户信息"
    )
    public ExistResponse exists(ExistRequest request) throws Exception {
        ExistResponse response = new ExistResponse();
        response.setExistent(this.userService.exists(request.getKey(), request.getId(), request.isCheckId()));
        return response;
    }

    @ServiceMethod(
            value = "user.roles",
            description = "读取用户已授角色"
    )
    public UserRolesResponse getRoles(UserRolesRequest request) throws Exception {
        return this.userService.getRoles(request.getUserId());
    }
}
