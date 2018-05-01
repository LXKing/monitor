package com.rayton.gps.controller.baseInfo;


import com.rayton.gps.dao.lisence.VehicleRegister;
import com.rayton.gps.dao.security.IdentityDto;
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
public class VehicleRegisterController {

    @Autowired
    private VehicleRegisterService vehicleRegisterService;



    @RequiresPermissions("baseinfo.licence.VehicleRegister")
    @GetMapping(value = "/vehicleRegister/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int
            limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        Assist row = new Assist();
        row.setRequires(Assist.andEq("company_id", identity.getCompanyId()));

        Map map = new HashMap();
        map.put("code", 0);
        map.put("count", vehicleRegisterService.getVehicleRegisterRowCount(row));
        Assist assist = new Assist();
        assist.setRowSize(limit);
        assist.setStartRow((page - 1) * limit);
        assist.setRequires(Assist.andEq("company_id", identity.getCompanyId()));
        // assist.setRequires(Assist.andLike("",""));
        // List<DrivingLisence> result = drivingLisenceService.selectDrivingLisence(assist);

        map.put("data", vehicleRegisterService.selectVehicleRegister(assist));
        // result.data = result.rows;
        // result.msg = "mmp";
        return map;

    }


    @PostMapping(value = "/vehicleRegister/create.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(@ModelAttribute("vehicleRegister") @Valid VehicleRegister
                                                                  vehicleRegister, BindingResult binding) throws
            Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors())

            return ResponseEntityWrapper.Failed();
        vehicleRegister.setCompanyId(identity.getCompanyId());
        vehicleRegisterService.insertVehicleRegister(vehicleRegister);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping(value = "/vehicleRegister/edit.form")
    @ResponseBody
    public Object edit(@RequestParam Integer id) throws Exception {
        VehicleRegister vehicleRegister = vehicleRegisterService.selectVehicleRegisterById(id);

        return vehicleRegister;

    }

    @PostMapping(value = "/vehicleRegister/edit.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@ModelAttribute("vehicleRegister") @Valid VehicleRegister
                                                                vehicleRegister, BindingResult binding) throws
            Exception {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/driver/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        vehicleRegister.setCompanyId(identity.getCompanyId());
        vehicleRegisterService.updateNonEmptyVehicleRegisterById(vehicleRegister);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/vehicleRegister/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Integer id) throws Exception {
        vehicleRegisterService.deleteVehicleRegisterById(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/vehicleRegister/exist")
    @ResponseBody
    public Object exists(@RequestParam String name) throws Exception {

        Assist row = new Assist();
        row.setRequires(Assist.andEq("name", name));
        long a = vehicleRegisterService.getVehicleRegisterRowCount(row);
        return a == 0 ? true : false;


    }

}
