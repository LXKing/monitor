package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.common.Tuple;
import com.rayton.gps.dao.baseinfo.company.Company;
import com.rayton.gps.dao.baseinfo.user.User;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.model.baseinfo.CompanyModel;
import com.rayton.gps.service.CompanyService;
import com.rayton.gps.service.SecurityService;
import com.rayton.gps.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @Autowired
    private SecurityService securityService;

    @RequiresPermissions("baseinfo.company")
    @ServiceMethod(id = "baseinfo.company", pid = "baseinfo", prefix = "打开", name = "企业管理", suffix = "页面")
    @RequestMapping("/company/company.iframe")
    public String iframe() {
        return "/baseinfo/company/company.iframe";
    }

    @RequestMapping("/company/query")
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return companyService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @ServiceMethod(id = "test", pid = "baseinfo", prefix = "mmp", name = "-", suffix = "mdzz")
    @RequestMapping(value = "/company/createPermission", method = RequestMethod.GET)
    public void createPermission(Model model) {
        // IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        // Permission permission = new Permission();
        // // permission.setCOMPANYID(identity.getCompanyId());
        // permission.setCOMPANYID("5a72ab073d769a75b6309dec");
        // permission.setId("test");
        // permission.setName("测试");
        // permission.setPid("baseinfo");
        // securityService.createPermission(permission);


    }

    @RequestMapping(value = "/company/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        CompanyModel m = new CompanyModel();
        model.addAttribute("editor", m);
        return "/baseinfo/company/create.form";
    }

    @RequestMapping(value = "/company/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding, Model model,
                         HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/company/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            m.setPid(identity.getCompanyId());
            companyService.create(m.getCompany(), m.getUser());
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/company/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        Tuple<Company, User> tuple = companyService.fetch(id);
        CompanyModel m = new CompanyModel();
        m.fill(tuple.t);
        m.fill(tuple.e);
        model.addAttribute("editor", m);
        return "/baseinfo/company/edit.form";
    }

    @RequestMapping(value = "/company/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding, Model model,
                       HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/baseinfo/company/edit.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            m.setPid(identity.getCompanyId());
            companyService.update(m.getCompany(), m.getUser());
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/company/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            companyService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/company/authorize.form", method = RequestMethod.GET)
    public String authorize(@RequestParam String id) throws Exception {
        return "/baseinfo/company/authorize.form";
    }

    @RequestMapping("/company/authorizes")
    @ResponseBody
    public Object authorizes(@RequestParam String companyId) throws Exception {
        return companyService.authorizes(companyId);
    }

    @RequestMapping(value = "/company/authorize", method = RequestMethod.POST)
    public String authorize(@RequestParam String companyId, @RequestParam("list[]") List<String> list,
                            RedirectAttributes r) throws Exception {
        try {
            companyService.authorize(companyId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

}
