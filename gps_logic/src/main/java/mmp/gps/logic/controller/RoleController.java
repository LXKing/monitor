package mmp.gps.logic.controller;


import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.role.AuthorizeRequest;
import mmp.gps.domain.role.CreateRoleRequest;
import mmp.gps.domain.role.EditRoleRequest;
import mmp.gps.domain.role.FetchRoleResponse;
import mmp.gps.logic.service.RoleService;
import mmp.gps.logic.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping(
            value = {"/role/index"},
            method = {RequestMethod.GET}
    )
    public String index() {
        return "/role/index";
    }

    @RequestMapping(
            value = {"/role/list"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object list() {
        try {
            return this.roleService.list(false);
        } catch (Exception var2) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/role/create.form"},
            method = {RequestMethod.GET}
    )
    public String create(Model model) {
        CreateRoleRequest role = new CreateRoleRequest();
        model.addAttribute("role", role);
        return "/role/create.form";
    }

    @RequestMapping(
            value = {"/role/create.form"},
            method = {RequestMethod.POST}
    )
    public String create(@ModelAttribute("role") @Valid CreateRoleRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            return "/role/create.form";
        } else {
            try {
                this.roleService.create(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/role/edit.form"},
            method = {RequestMethod.GET}
    )
    public String edit(@RequestParam String id, Model model) throws Exception {
        FetchRoleResponse role = this.roleService.fetch(id);
        model.addAttribute("role", role);
        return "/role/edit.form";
    }

    @RequestMapping(
            value = {"/role/edit.form"},
            method = {RequestMethod.POST}
    )
    public String edit(@ModelAttribute("role") @Valid EditRoleRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            return "/role/edit.form";
        } else {
            try {
                this.roleService.update(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/role/delete"},
            method = {RequestMethod.POST}
    )
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            this.roleService.delete(id);
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/role/permissions"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object permissions(@RequestParam String id) {
        try {
            return this.roleService.getPermissions(id);
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/role/authorize"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object authorize(AuthorizeRequest request) {
        AppResponse response = new AppResponse();

        try {
            this.roleService.authorize(request.getRoleId(), request.getPermissionId(), request.isAllow());
        } catch (Exception var4) {
            response.setErrorMessage(var4.getMessage());
        }

        return response;
    }
}
