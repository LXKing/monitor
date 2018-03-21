package com.edata.monitor.controller;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.baseinfo.driver.Driver;
import com.edata.monitor.dao.baseinfo.driver.DriverInfo;
import com.edata.monitor.dao.baseinfo.vehicle.VehicleInfo;
import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.service.DriverService;
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
public class DriverController {
    @Autowired
    private DriverService driverService;

    @ServiceMethod(id = "baseinfo.driver", pid = "baseinfo", prefix = "打开", name = "驾驶员管理", suffix = "页面")
    @RequestMapping(value = "/driver/driver.iframe", method = RequestMethod.GET)
    public String index() {
        return "/baseinfo/driver/driver.iframe";
    }

    @RequestMapping(value = "/driver/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) {
        Identity identity = (Identity) request.getAttribute("user");
        try {
            Page<DriverInfo> page = driverService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
            return page;
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping("/driver/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam String driverId) throws Exception {
        Page<VehicleInfo> page = new Page<VehicleInfo>();
        page.rows = driverService.assignedVehicles(driverId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @RequestMapping(value = "/driver/addVehicles", method = RequestMethod.POST)
    public String addSections(@RequestParam String driverId, @RequestParam String vehicles, RedirectAttributes r) {
        try {
            List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
            driverService.addVehicles(driverId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/driver/removeVehicle", method = RequestMethod.POST)
    public String removeSection(@RequestParam String driverId, @RequestParam String vehicleId, RedirectAttributes r) {
        try {
            driverService.removeVehicle(driverId, vehicleId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/driver/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);

        return "/baseinfo/driver/create.form";
    }

    @RequestMapping(value = "/driver/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("driver") @Valid Driver driver, BindingResult binding, Model model,
                         RedirectAttributes r, HttpServletRequest request) {
        Identity identity = (Identity) request.getAttribute("user");

        if (binding.hasErrors())
            return "/baseinfo/driver/create.form";

        try {
            driver.setCompanyId(identity.getCompanyId());
            driverService.create(driver);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/driver/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        Driver driver = driverService.fetch(id);
        model.addAttribute("driver", driver);

        return "/baseinfo/driver/edit.form";
    }

    @RequestMapping(value = "/driver/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("driver") @Valid Driver driver, BindingResult binding, Model model,
                       RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/driver/edit.form";

        try {
            driverService.update(driver);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/driver/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            driverService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
