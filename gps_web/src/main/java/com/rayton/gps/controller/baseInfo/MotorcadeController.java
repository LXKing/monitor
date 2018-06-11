package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.company.ICompanyDao;
import com.rayton.gps.dao.baseinfo.motorcade.Motorcade;
import com.rayton.gps.dao.baseinfo.motorcade.MotorcadeInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.MotorcadeService;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MotorcadeController {
    @Autowired
    private MotorcadeService motorcadeService;
    @Autowired
    private ICompanyDao companyDao;

    @RequiresPermissions("baseinfo.motorcade")
    @Log(name = "打开车队管理页面")
    @GetMapping(value = "/motorcade/motorcade.iframe")
    public String index() {
        return "/baseinfo/motorcade/motorcade.iframe";
    }

    @GetMapping(value = "/motorcade/query")
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "true") boolean grid) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (grid) {
            Page<MotorcadeInfo> page = new Page<>();
            page.rows = motorcadeService.list(identity.getCompanyId());
            page.total = page.rows.size();
            return page;
        }
        return motorcadeService.list(identity.getCompanyId());

    }

    @GetMapping(value = "/motorcade/create.form")
    public String create(Model model) {
        Motorcade motorcade = new Motorcade();
        model.addAttribute("motorcade", motorcade);

        return "/baseinfo/motorcade/create.form";
    }

    @PostMapping(value = "/motorcade/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("motorcade") @Valid Motorcade motorcade, BindingResult binding) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        // try {
        //     motorcade.setCompanyId(identity.getCompanyId());
        //     motorcadeService.create(motorcade);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";

        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }

        motorcade.setCOMPANY(companyDao.fetch(identity.getCompanyId()).getFullName());
        // return "/baseinfo/motorcade/create.form";
        motorcade.setCompanyId(identity.getCompanyId());
        motorcadeService.create(motorcade);
        return ResponseEntityWrapper.OK();


    }

    @GetMapping(value = "/motorcade/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Motorcade motorcade = motorcadeService.fetch(id);
        return motorcade;
        // model.addAttribute("motorcade", motorcade);
        //
        // return "/baseinfo/motorcade/edit.form";
    }

    @PostMapping(value = "/motorcade/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("motorcade") @Valid Motorcade motorcade, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/motorcade/edit.form";
        motorcadeService.update(motorcade);
        return ResponseEntityWrapper.OK();
        // try {
        //     motorcadeService.update(motorcade);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/motorcade/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id) {
        motorcadeService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     motorcadeService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/motorcade/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) String id, @RequestParam boolean checkId) throws Exception {
        // Identity user = (Identity) request.getAttribute("user");
        IdentityDto user = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return checkId ? !motorcadeService.exist(name, user.getCompanyId(), id) : !motorcadeService.exist(name, user.getCompanyId());
        // if (checkId) {
        //     response.getWriter().print(!motorcadeService.exist(name, user.getCompanyId(), id));
        // } else {
        //     response.getWriter().print(!motorcadeService.exist(name, user.getCompanyId()));
        // }
    }
}
