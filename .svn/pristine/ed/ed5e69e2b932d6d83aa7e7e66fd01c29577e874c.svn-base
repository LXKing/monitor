package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.role.Role;
import com.rayton.gps.dao.baseinfo.role.RoleInfo;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.service.RoleService;
import com.rayton.gps.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ServiceMethod(id = "baseinfo.role", pid = "baseinfo", prefix = "打开", name = "角色管理", suffix = "页面")
    @RequestMapping(value = "/role/role.iframe", method = RequestMethod.GET)
    public String index() {
        return "/baseinfo/role/role.iframe";
    }

    @RequestMapping(value = "/role/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request) {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        try {
            Page<RoleInfo> page = new Page<RoleInfo>();
            page.rows = roleService.list(identity.getCompanyId());
            page.total = page.rows.size();
            return page;
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/role/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);

        return "/baseinfo/role/create.form";
    }

    @RequestMapping(value = "/role/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("role") @Valid Role role, BindingResult binding, Model model,
                         RedirectAttributes r, HttpServletRequest request) {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors())
            return "/baseinfo/role/create.form";

        try {
            role.setCompanyId(identity.getCompanyId());
            roleService.create(role);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/role/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        Role role = roleService.fetch(id);
        model.addAttribute("role", role);

        return "/baseinfo/role/edit.form";
    }

    @RequestMapping(value = "/role/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("role") @Valid Role role, BindingResult binding, Model model,
                       RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/role/edit.form";

        try {
            roleService.update(role);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            roleService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/role/authorize.form", method = RequestMethod.GET)
    public String authorize() throws Exception {
        return "/baseinfo/role/authorize.form";
    }

    @RequestMapping(value = "/role/authorizes", method = RequestMethod.POST)
    @ResponseBody
    public Object authorizes(@RequestParam String roleId) {
        try {
            return roleService.authorizes(roleId);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/role/authorize", method = RequestMethod.POST)
    @ResponseBody
    public String authorize(@RequestParam String roleId, @RequestParam("list[]") List<String> list,
                            RedirectAttributes r, HttpServletRequest request) {
        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            roleService.authorize(identity.getCompanyId(), roleId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/role/exist", method = RequestMethod.POST)
    public void exists(@RequestParam String name, @RequestParam(required = false) String id, @RequestParam boolean
            checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Identity user = (Identity) request.getAttribute("user");
        if (checkId) {
            response.getWriter().print(!roleService.exist(name, user.getCompanyId(), id));
        } else {
            response.getWriter().print(!roleService.exist(name, user.getCompanyId()));
        }
    }
}
