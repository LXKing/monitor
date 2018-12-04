package mmp.gps.logic.controller;


import mmp.gps.domain.exist.ExistRequest;
import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.faultcode.ExistFaultCodeRequest;
import mmp.gps.logic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RemoteController {

    @Autowired
    private FirmwareService firmwareService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @RequestMapping(
            value = {"/remote/user/exists"},
            method = {RequestMethod.POST}
    )
    public void userExists(ExistRequest request, HttpServletResponse response) throws Exception {
        boolean result = this.userService.exists(request.getKey(), request.getId(), request.isCheckId());
        if (result) {
            response.getWriter().print(false);
        } else {
            response.getWriter().print(true);
        }

    }

    @RequestMapping(
            value = {"/remote/role/exists"},
            method = {RequestMethod.POST}
    )
    public void roleExists(ExistRequest request, HttpServletResponse response) throws Exception {
        boolean result = this.roleService.exists(request.getKey(), request.getId(), request.isCheckId());
        if (result) {
            response.getWriter().print(false);
        } else {
            response.getWriter().print(true);
        }

    }

    @RequestMapping(
            value = {"/remote/permission/exists"},
            method = {RequestMethod.POST}
    )
    public void permissionExists(ExistRequest request, HttpServletResponse response) throws Exception {
        boolean result = this.permissionService.exists(request.getKey(), request.getId(), request.isCheckId());
        if (result) {
            response.getWriter().print(false);
        } else {
            response.getWriter().print(true);
        }

    }

    @RequestMapping(
            value = {"/remote/firmware/exists"},
            method = {RequestMethod.POST}
    )
    public void firmwareExists(ExistRequest request, HttpServletResponse response) throws Exception {
        boolean result = this.firmwareService.exists(Short.parseShort(request.getKey()), request.getId(), request.isCheckId());
        if (result) {
            response.getWriter().print(false);
        } else {
            response.getWriter().print(true);
        }

    }

    @RequestMapping(
            value = {"/remote/faultcode/exists"},
            method = {RequestMethod.POST}
    )
    public void faultCodeExists(ExistFaultCodeRequest request, HttpServletResponse response) throws Exception {
        ExistResponse er = FaultCodeService.exists(request);
        if (er.isExistent()) {
            response.getWriter().print(false);
        } else {
            response.getWriter().print(true);
        }

    }
}
