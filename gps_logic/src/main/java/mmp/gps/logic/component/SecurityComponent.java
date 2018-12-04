package mmp.gps.logic.component;

import mmp.gps.domain.security.*;
import mmp.gps.logic.Identity;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class SecurityComponent {

    @Autowired
    private SecurityService securityService;


    public SecurityComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "security.login",
            allowAnoumous = true,
            description = "用户登录"
    )
    public LoginResponse login(LoginRequest request) throws Exception {
        return this.securityService.login(request.getAccount(), request.getPwd());
    }

    @ServiceMethod(
            value = "security.myinfo.get",
            description = "读取个人信息"
    )
    public GetMyInfoResponse getMyinfo(Identity identity) throws Exception {
        return this.securityService.getMyinfo(identity);
    }

    @ServiceMethod(
            value = "security.myinfo.save",
            description = "保存个人信息"
    )
    public boolean saveMyinfo(SaveMyInfoRequest request) throws Exception {
        this.securityService.saveMyInfo(request);
        return true;
    }

    @ServiceMethod(
            value = "security.mykey.save",
            description = "保存密码设置"
    )
    public void saveMyinfo(Identity identity, SaveMyKeyRequest request) throws Exception {
        this.securityService.saveMyKey(identity, request);
    }
}
