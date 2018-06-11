package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.lisence.Drivervl;
import com.rayton.gps.dao.lisence.VehicleLicense;
import com.rayton.gps.service.lisence.DrivervlService;
import com.rayton.gps.service.lisence.VehicleLicenseService;
import com.rayton.gps.util.Assist;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class VehicleLicenceContorller {


    @Autowired
    private DrivervlService drivervlService;
    @Autowired
    private VehicleLicenseService vehicleLicenseService;

    @RequiresPermissions("baseinfo.driver")
    @GetMapping("/driver/drivervl")
    @ResponseBody
    public Object vehicles(@RequestParam String driverId) throws Exception {


        Assist row = new Assist();
        row.setRequires(Assist.andEq("driver_id", driverId));
        List<Drivervl> driverdl = drivervlService.selectDrivervl(row);
        // Assist a = new Assist();
        List<VehicleLicense> drivingLisenceList = new ArrayList<>();
        driverdl.forEach(drivervl1 -> drivingLisenceList.add(vehicleLicenseService.selectVehicleLicenseById(drivervl1.getVehicleLicenceId())));

        Page<VehicleLicense> page = new Page<>();
        page.rows = drivingLisenceList;
        page.total = drivingLisenceList.size();
        return page;
    }

    @RequiresPermissions("baseinfo.driver.addVehicleLicence")
    @PostMapping(value = "/driver/addDrivervl")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addSections(@RequestParam String driverId, @RequestParam String vl) throws Exception {
        List<String> list = Arrays.asList(vl.split(","));
        // List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);

        list.forEach(str -> {
            Drivervl driverdl = new Drivervl();
            driverdl.setDriverId(driverId);
            driverdl.setVehicleLicenceId(Integer.valueOf(str));
            drivervlService.insertDrivervl(driverdl);
        });

        return ResponseEntityWrapper.OK();

    }

    @RequiresPermissions("baseinfo.driver.removeVehicleLicence")
    @PostMapping(value = "/driver/removeDrivervl")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam String driverId, @RequestParam String vl) throws Exception {
        Assist a = new Assist();
        a.setRequires(Assist.andEq("driver_id", driverId));
        a.setRequires(Assist.andEq("vehicle_licence_id", vl));
        drivervlService.deleteDrivervl(a);
        return ResponseEntityWrapper.OK();

    }

}
