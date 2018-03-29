package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.baseinfo.maintain.Maintain;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.service.MaintainService;
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
import java.sql.Date;

@Controller
public class MaintainController {
    @Autowired
    private MaintainService maintainService;


    @RequiresPermissions("baseinfo.maintain")
    @ServiceMethod(id = "baseinfo.maintain", pid = "baseinfo", prefix = "打开", name = "车辆保养", suffix = "页面")
    @RequestMapping("/maintain/maintain.iframe")
    public String frame() {
        return "/baseinfo/maintain/maintain.iframe";
    }

    @RequestMapping("/maintain/query")
    @ResponseBody
    public Object query(@RequestParam String plateNumber, @RequestParam Date from, @RequestParam Date to,
                        @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request) throws
            Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return maintainService.query(identity.getCompanyId(), plateNumber, from, to, pageIndex, pageSize);
    }

    @RequestMapping(value = "/maintain/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        Maintain maintain = new Maintain();
        model.addAttribute("maintain", maintain);
        return "/baseinfo/maintain/create.form";
    }

    @RequestMapping(value = "/maintain/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding, Model model,
                         HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/maintain/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            maintain.setCompanyId(identity.getCompanyId());
            maintain.setUserId(identity.getId());
            maintain.setUserName(identity.getName());
            maintainService.Create(maintain);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/maintain/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        Maintain maintain = maintainService.fetch(id);
        model.addAttribute("maintain", maintain);
        return "/baseinfo/maintain/edit.form";
    }

    @RequestMapping(value = "/maintain/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding, Model model,
                       HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/baseinfo/maintain/edit.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            maintain.setCompanyId(identity.getCompanyId());
            maintain.setUserId(identity.getId());
            maintain.setUserName(identity.getName());
            maintainService.update(maintain);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/maintain/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            maintainService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
