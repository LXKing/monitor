package mmp.gps.logic.controller;


import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.permission.CreatePermissionRequest;
import mmp.gps.logic.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(
            value = {"/permission/index"},
            method = {RequestMethod.GET}
    )
    public String index() {
        return "/permission/index";
    }

    @RequestMapping(
            value = {"/permission/list"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object list() {
        try {
            return this.permissionService.list();
        } catch (Exception var2) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/permission/collect"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object collect() {
        try {
            return this.permissionService.collect();
        } catch (Exception var2) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/permission/create"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object create(CreatePermissionRequest request) {
        AppResponse response = new AppResponse();

        try {
            this.permissionService.create(request);
        } catch (Exception var4) {
            response.setErrorMessage(var4.getMessage());
        }

        return response;
    }

    @RequestMapping(
            value = {"/permission/delete"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object delete(@RequestParam String id) {
        AppResponse response = new AppResponse();

        try {
            this.permissionService.delete(id);
        } catch (Exception var4) {
            response.setErrorMessage(var4.getMessage());
        }

        return response;
    }
}
