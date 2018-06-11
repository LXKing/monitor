package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.common.Tuple;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.owner.Owner;
import com.rayton.gps.dao.baseinfo.owner.OwnerInfo;
import com.rayton.gps.dao.baseinfo.user.User;
import com.rayton.gps.dao.baseinfo.vehicle.VehicleInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.model.baseinfo.OwnerModel;
import com.rayton.gps.service.OwnerService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OwnerController {
    @Autowired
    private OwnerService ownerService;


    @RequiresPermissions("baseinfo.owner")
    @Log(name = "打开车主管理页面")
    @GetMapping("/owner/owner.iframe")
    public String frame() {
        return "/baseinfo/owner/owner.iframe";
    }


    @GetMapping("/owner/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<OwnerInfo> result = ownerService.query(identity.getCompanyId(), filter, page, limit);

        result.code = 0;
        result.count = result.total;
        result.data = result.rows;
        // result.msg = "mmp";
        return result;
    }

    @GetMapping("/owner/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam String ownerId) throws Exception {
        Page<VehicleInfo> page = new Page<>();
        page.rows = ownerService.assignedVehicles(ownerId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/owner/addVehicles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addSections(@RequestParam String ownerId, @RequestParam String vehicles) throws Exception {

        List<String> list = Arrays.asList(vehicles.split(","));
        // List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        ownerService.addVehicles(ownerId, list);
        return ResponseEntityWrapper.OK();
        // try {
        //     List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        //     ownerService.addVehicles(ownerId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/owner/removeVehicle")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam String ownerId, @RequestParam String vehicleId) throws Exception {
        ownerService.removeVehicle(ownerId, vehicleId);
        return ResponseEntityWrapper.OK();
        // try {
        //     ownerService.removeVehicle(ownerId, vehicleId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/owner/create.form")
    public String create(Model model) {
        Owner owner = new Owner();
        User user = new User();
        OwnerModel m = new OwnerModel();
        m.fill(owner);
        m.fill(user);
        model.addAttribute("editor", m);
        return "/baseinfo/owner/create.form";
    }

    @PostMapping(value = "/owner/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("editor") @Valid OwnerModel m, BindingResult binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setCompanyId(identity.getCompanyId());
        m.setCompanyId(identity.getCompanyId());
        ownerService.create(m.getOwner(), m.getUser());
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     m.setCompanyId(identity.getCompanyId());
        //     m.setCompanyId(identity.getCompanyId());
        //     ownerService.create(m.getOwner(), m.getUser());
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/owner/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Tuple<Owner, User> tuple = ownerService.fetch(id);
        OwnerModel m = new OwnerModel();
        m.fill(tuple.e);
        m.fill(tuple.t);
        return m;
        // model.addAttribute("editor", m);
        // return "/baseinfo/owner/edit.form";
    }

    @PostMapping(value = "/owner/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("editor") @Valid OwnerModel m, BindingResult binding) throws Exception {
        if (binding.hasErrors())
            // return "/baseinfo/owner/edit.form";
            return ResponseEntityWrapper.Failed();
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setCompanyId(identity.getCompanyId());
        ownerService.update(m.getOwner(), m.getUser());
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     m.setCompanyId(identity.getCompanyId());
        //     ownerService.update(m.getOwner(), m.getUser());
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/owner/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id, RedirectAttributes r) throws Exception {
        ownerService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     ownerService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }
}
