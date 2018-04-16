package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.MarkerFileInfo;
import com.rayton.gps.dao.baseinfo.driver.DriverInfo;
import com.rayton.gps.dao.baseinfo.owner.OwnerInfo;
import com.rayton.gps.dao.baseinfo.vehicle.Vehicle;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.MarkerService;
import com.rayton.gps.service.VehicleService;
import com.rayton.gps.util.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private MarkerService markerService;


    @RequiresPermissions("baseinfo.vehicle")
    @Log(name = "打开车辆管理页面")
    @GetMapping("/vehicle/vehicle.iframe")
    public ModelAndView index() {
        return new ModelAndView("/baseinfo/vehicle/vehicle.iframe");
    }

    @PostMapping(value = "/vehicle/query")
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize) throws
            Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return vehicleService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @PostMapping(value = "/vehicle/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String plateNumber, @RequestParam int pageIndex,
                         @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        plateNumber = plateNumber == null ? "" : plateNumber;
        return vehicleService.search(identity.getCompanyId(), plateNumber, pageIndex, pageSize);

    }

    @GetMapping("/vehicle/owners")
    @ResponseBody
    public Object owners(@RequestParam String vehicleId) throws Exception {
        Page<OwnerInfo> page = new Page<>();
        page.rows = vehicleService.assignedOwners(vehicleId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/vehicle/addOwners")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addOwners(@RequestParam String vehicleId, @RequestParam String owners) {

        List<String> list = JsonMapper.toObject(owners, List.class, String.class);
        vehicleService.addOwners(vehicleId, list);
        return this.responseEntityOK();
        // Map<String, Object> map = new HashMap<>();
        // map.put("error", "ok...");
        // return new ResponseEntity<>(map, HttpStatus.OK);
        // try {
        //     List<String> list = JsonMapper.toObject(owners, List.class, String.class);
        //     vehicleService.addOwners(vehicleId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/vehicle/removeOwner")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeOwner(@RequestParam String vehicleId, @RequestParam String
            ownerId) {
        vehicleService.removeOwner(vehicleId, ownerId);
        // Map<String, Object> map = new HashMap<>();
        // map.put("error", "ok...");
        // return new ResponseEntity<>(map, HttpStatus.OK);
        return this.responseEntityOK();
        // try {
        //     vehicleService.removeOwner(vehicleId, ownerId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/vehicle/drivers")
    @ResponseBody
    public Object drivers(@RequestParam String vehicleId) throws Exception {
        Page<DriverInfo> page = new Page<DriverInfo>();
        page.rows = vehicleService.assignedDrivers(vehicleId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/vehicle/addDrivers")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addDrivers(@RequestParam String vehicleId, @RequestParam String
            drivers) {

        List<String> list = JsonMapper.toObject(drivers, List.class, String.class);
        vehicleService.addDrivers(vehicleId, list);
        return this.responseEntityOK();
        // try {
        //     List<String> list = JsonMapper.toObject(drivers, List.class, String.class);
        //     vehicleService.addDrivers(vehicleId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/vehicle/removeDriver")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeDriver(@RequestParam String vehicleId, @RequestParam String
            driverId) {
        vehicleService.removeDriver(vehicleId, driverId);
        return this.responseEntityOK();
        // try {
        //     vehicleService.removeDriver(vehicleId, driverId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/vehicle/create.form")
    public ModelAndView create(Model model, HttpServletRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRotate(true);
        model.addAttribute("vehicle", vehicle);
        loadMarkers(request, model);
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("vehicle", vehicle);
        modelAndView.setViewName("/baseinfo/vehicle/create.form");
        return modelAndView;
        // return "/baseinfo/vehicle/create.form";
    }

    @PostMapping(value = "/vehicle/create.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(@ModelAttribute("vehicle") @Valid Vehicle vehicle,
                                                      BindingResult binding) {
        if (binding.hasErrors()) {
            // loadMarkers(request, model);
            // return "/baseinfo/vehicle/create.form";
            return responseEntityFailed("failed");
        }
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        vehicle.setCompanyId(identity.getCompanyId());
        vehicleService.create(vehicle);
        return responseEntityOK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     vehicle.setCompanyId(identity.getCompanyId());
        //     vehicleService.create(vehicle);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/vehicle/edit.form")
    public String edit(@RequestParam String id, Model model, HttpServletRequest request) throws Exception {
        Vehicle vehicle = vehicleService.fetch(id);
        model.addAttribute("vehicle", vehicle);
        loadMarkers(request, model);
        return "/baseinfo/vehicle/edit.form";
    }

    @PostMapping(value = "/vehicle/edit.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult
            binding) {
        if (binding.hasErrors()) {
            // loadMarkers(request, model);
            // return "/baseinfo/vehicle/edit.form";
            return responseEntityFailed("failed");
        }
        vehicleService.update(vehicle);
        return responseEntityOK();
        // try {
        //     vehicleService.update(vehicle);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/vehicle/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@RequestParam String id) throws Exception {
        vehicleService.delete(id);
        return this.responseEntityOK();
        // Map<String, Object> map = new HashMap<>();
        // map.put("error", "ok...");
        // return new ResponseEntity<>(map, HttpStatus.OK);
        // try {
        //     vehicleService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }

    }

    @PostMapping(value = "/vehicle/exist")
    @ResponseBody
    public Object vehicleExists(@RequestParam String plateNumber, @RequestParam(required = false) String id,
                                @RequestParam boolean checkId) throws Exception {
        return checkId ? !vehicleService.exist(plateNumber, id) : !vehicleService.exist(plateNumber);

        // if (checkId) {
        //     response.getWriter().print(!vehicleService.exist(plateNumber, id));
        // } else {
        //     response.getWriter().print(!vehicleService.exist(plateNumber));
        // }
    }

    private ResponseEntity<Map<String, Object>> responseEntityOK() {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "ok...");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> responseEntityFailed(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private void loadMarkers(HttpServletRequest request, Model model) {
        String path = request.getServletContext().getRealPath("/resources/icons");
        List<MarkerFileInfo> icons = markerService.getMarkerFiles(path);
        model.addAttribute("icons", icons);
    }
}
