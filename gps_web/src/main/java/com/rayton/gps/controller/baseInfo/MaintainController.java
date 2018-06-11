package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.baseinfo.maintain.Maintain;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.MaintainService;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MaintainController {
    @Autowired
    private MaintainService maintainService;


    @RequiresPermissions("baseinfo.maintain")
    @Log(name = "打开车辆保养页面")
    @RequestMapping("/maintain/maintain.iframe")
    public String frame() {
        return "/baseinfo/maintain/maintain.iframe";
    }

    @PostMapping("/maintain/query")
    @ResponseBody
    public Object query(@RequestParam String plateNumber, @RequestParam Date from, @RequestParam Date to, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return maintainService.query(identity.getCompanyId(), plateNumber, from, to, pageIndex, pageSize);
    }

    @GetMapping(value = "/maintain/create.form")
    public String create(Model model) {
        Maintain maintain = new Maintain();
        model.addAttribute("maintain", maintain);
        return "/baseinfo/maintain/create.form";
    }

    @PostMapping(value = "/maintain/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/maintain/create.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        maintain.setCompanyId(identity.getCompanyId());
        maintain.setUserId(identity.getId());
        maintain.setUserName(identity.getName());
        maintainService.Create(maintain);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     maintain.setCompanyId(identity.getCompanyId());
        //     maintain.setUserId(identity.getId());
        //     maintain.setUserName(identity.getName());
        //     maintainService.Create(maintain);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/maintain/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Maintain maintain = maintainService.fetch(id);
        return maintain;
        // model.addAttribute("maintain", maintain);
        // return "/baseinfo/maintain/edit.form";
    }

    @PostMapping(value = "/maintain/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding) throws Exception {
        if (binding.hasErrors())
            // return "/baseinfo/maintain/edit.form";
            return ResponseEntityWrapper.Failed();

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        maintain.setCompanyId(identity.getCompanyId());
        maintain.setUserId(identity.getId());
        maintain.setUserName(identity.getName());
        maintainService.update(maintain);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     maintain.setCompanyId(identity.getCompanyId());
        //     maintain.setUserId(identity.getId());
        //     maintain.setUserName(identity.getName());
        //     maintainService.update(maintain);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/maintain/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id, RedirectAttributes r) {
        maintainService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     maintainService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }
}
