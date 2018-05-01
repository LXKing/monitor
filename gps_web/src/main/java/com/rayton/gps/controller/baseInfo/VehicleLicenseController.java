package com.rayton.gps.controller.baseInfo;


import com.rayton.gps.dao.lisence.VehicleLicense;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.lisence.VehicleLicenseService;
import com.rayton.gps.service.lisence.VehicleRegisterService;
import com.rayton.gps.util.Assist;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class VehicleLicenseController {

    @Autowired
    private VehicleLicenseService vehicleLicenseService;


    @RequiresPermissions("baseinfo.licence.VehicleLicence")
    @GetMapping(value = "/vehicleLicence/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int
            limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        Assist row = new Assist();
        row.setRequires(Assist.andEq("company_id", identity.getCompanyId()));

        Map map = new HashMap();
        map.put("code", 0);
        map.put("count", vehicleLicenseService.getVehicleLicenseRowCount(row));
        Assist assist = new Assist();
        assist.setRowSize(limit);
        assist.setStartRow((page - 1) * limit);
        assist.setRequires(Assist.andEq("company_id", identity.getCompanyId()));
        // assist.setRequires(Assist.andLike("",""));
        // List<DrivingLisence> result = drivingLisenceService.selectDrivingLisence(assist);

        map.put("data", vehicleLicenseService.selectVehicleLicense(assist));
        // result.data = result.rows;
        // result.msg = "mmp";
        return map;

    }


    @PostMapping(value = "/vehicleLicense/create.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(@ModelAttribute("vehicleLicense") @Valid VehicleLicense
                                                                  vehicleLicense, BindingResult binding) throws
            Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors())

            return ResponseEntityWrapper.Failed();
        vehicleLicense.setCompanyId(identity.getCompanyId());
        vehicleLicenseService.insertVehicleLicense(vehicleLicense);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping(value = "/vehicleLicense/edit.form")
    @ResponseBody
    public Object edit(@RequestParam Integer id) throws Exception {
        VehicleLicense vehicleLicense = vehicleLicenseService.selectVehicleLicenseById(id);

        return vehicleLicense;

    }

    @PostMapping(value = "/vehicleLicense/edit.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@ModelAttribute("vehicleLicense") @Valid VehicleLicense
                                                                vehicleLicense, BindingResult binding) throws
            Exception {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/driver/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        vehicleLicense.setCompanyId(identity.getCompanyId());
        vehicleLicenseService.updateNonEmptyVehicleLicenseById(vehicleLicense);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/vehicleLicense/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Integer id) throws Exception {
        vehicleLicenseService.deleteVehicleLicenseById(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/vehicleLicense/exist")
    @ResponseBody
    public Object exists(@RequestParam String name) throws Exception {

        Assist row = new Assist();
        row.setRequires(Assist.andEq("name", name));
        long a = vehicleLicenseService.getVehicleLicenseRowCount(row);
        return a == 0 ? true : false;


    }

}
