package com.edata.monitor.controller;

import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.owner.Owner;
import com.edata.monitor.dao.baseinfo.user.User;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfo;
import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.model.baseinfo.OwnerModel;
import com.edata.monitor.service.OwnerService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;
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
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @ServiceMethod(id = "baseinfo.owner", pid = "baseinfo", prefix = "打开", name = "车主管理", suffix = "页面")
    @RequestMapping("/owner/owner.iframe")
    public String frame() {
        return "/baseinfo/owner/owner.iframe";
    }

    @RequestMapping("/owner/query")
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        Identity identity = (Identity) request.getAttribute("user");
        return ownerService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @RequestMapping("/owner/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam String ownerId) throws Exception {
        Page<VehicleInfo> page = new Page<VehicleInfo>();
        page.rows = ownerService.assignedVehicles(ownerId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @RequestMapping(value = "/owner/addVehicles", method = RequestMethod.POST)
    public String addSections(@RequestParam String ownerId, @RequestParam String vehicles, RedirectAttributes r) {
        try {
            List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
            ownerService.addVehicles(ownerId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/owner/removeVehicle", method = RequestMethod.POST)
    public String removeSection(@RequestParam String ownerId, @RequestParam String vehicleId, RedirectAttributes r) {
        try {
            ownerService.removeVehicle(ownerId, vehicleId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/owner/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        Owner owner = new Owner();
        User user = new User();
        OwnerModel m = new OwnerModel();
        m.fill(owner);
        m.fill(user);
        model.addAttribute("editor", m);
        return "/baseinfo/owner/create.form";
    }

    @RequestMapping(value = "/owner/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("editor") @Valid OwnerModel m, BindingResult binding, Model model,
                         HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/owner/create.form";

        try {
            Identity identity = (Identity) request.getAttribute("user");
            m.setCompanyId(identity.getCompanyId());
            m.setCompanyId(identity.getCompanyId());
            ownerService.create(m.getOwner(), m.getUser());
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/owner/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        Tuple<Owner, User> tuple = ownerService.fetch(id);
        OwnerModel m = new OwnerModel();
        m.fill(tuple.e);
        m.fill(tuple.t);
        model.addAttribute("editor", m);
        return "/baseinfo/owner/edit.form";
    }

    @RequestMapping(value = "/owner/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("editor") @Valid OwnerModel m, BindingResult binding, Model model,
                       HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/baseinfo/owner/edit.form";

        try {
            Identity identity = (Identity) request.getAttribute("user");
            m.setCompanyId(identity.getCompanyId());
            ownerService.update(m.getOwner(), m.getUser());
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/owner/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            ownerService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
