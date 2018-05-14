package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.sim.Simcard;
import com.rayton.gps.dao.baseinfo.sim.SimcardInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.SimcardService;
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
public class SimcardController {
    @Autowired
    private SimcardService simcardService;


    @RequiresPermissions("baseinfo.simcard")
    @Log(name = "打开SIM卡管理页面")
    @GetMapping(value = "/simcard/simcard.iframe")
    public String index() {
        return "/baseinfo/simcard/simcard.iframe";
    }

    @GetMapping(value = "/simcard/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int
            limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<SimcardInfo> result = simcardService.query(identity.getCompanyId(), filter, page, limit);


        result.code = 0;
        result.count = result.total;
        result.data = result.rows;
        // result.msg = "mmp";
        return result;
        // try {
        //     Page<SimcardInfo> page = simcardService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
        //     return page;
        // } catch (Exception ex) {
        //     return null;
        // }
    }

    @PostMapping(value = "/simcard/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam
            int pageSize) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        return simcardService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        // try {
        //         //     Page<SimcardSearchInfo> page = simcardService.search(identity.getCompanyId(), filter,
        // pageIndex, pageSize);
        //         //     return page;
        //         // } catch (Exception ex) {
        //         //     return null;
        //         // }
    }

    @PostMapping(value = "/simcard/free")
    @ResponseBody
    public Object findfree(@RequestParam(required = false) String phoneNumber, @RequestParam int pageIndex,
                           @RequestParam int pageSize) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        phoneNumber = phoneNumber == null ? "" : phoneNumber;
        return simcardService.free(identity.getCompanyId(), phoneNumber, pageIndex, pageSize);
        // try {
        //     Page<SimcardSearchInfo> page = simcardService.free(identity.getCompanyId(), phoneNumber, pageIndex,
        //             pageSize);
        //     return page;
        // } catch (Exception ex) {
        //     return null;
        // }
    }

    @GetMapping(value = "/simcard/create.form")
    @ResponseBody
    public Object create(Model model) {
        Simcard simcard = new Simcard();
        return simcard;
        // model.addAttribute("simcard", simcard);
        //
        // return "/baseinfo/simcard/create.form";
    }

    @PostMapping(value = "/simcard/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("simcard") @Valid Simcard simcard,
                                                      BindingResult binding) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/simcard/create.form";
        simcard.setCompanyId(identity.getCompanyId());
        simcardService.create(simcard);
        return ResponseEntityWrapper.OK();
        // try {
        //     simcard.setCompanyId(identity.getCompanyId());
        //     simcardService.create(simcard);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/simcard/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Simcard simcard = simcardService.fetch(id);
        return simcard;
        // model.addAttribute("simcard", simcard);
        //
        // return "/baseinfo/simcard/edit.form";
    }

    @PostMapping(value = "/simcard/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("simcard") @Valid Simcard simcard, BindingResult
            binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/simcard/edit.form";
        simcardService.update(simcard);
        return ResponseEntityWrapper.OK();
        // try {
        //     simcardService.update(simcard);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/simcard/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id) throws Exception {
        simcardService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     simcardService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/simcard/exist")
    @ResponseBody
    public Object exists(@RequestParam String phoneNumber, @RequestParam(required = false) String id, @RequestParam
            boolean checkId) throws Exception {

        return checkId ? !simcardService.exist(phoneNumber, id) : !simcardService.exist(phoneNumber);
        // if (checkId) {
        //     response.getWriter().print(!simcardService.exist(phoneNumber, id));
        // } else {
        //     response.getWriter().print(!simcardService.exist(phoneNumber));
        // }
    }
}