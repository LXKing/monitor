package com.rayton.gps.controller.baseInfo;


import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.lisence.Driverdl;
import com.rayton.gps.dao.lisence.DrivingLisence;
import com.rayton.gps.service.lisence.*;
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
public class DriverLicenceController {

    @Autowired
    private DriverdlService driverdlService;


    @Autowired
    private DrivingLisenceService drivingLisenceService;


    @RequiresPermissions("baseinfo.driver")
    @GetMapping("/driver/driverdl")
    @ResponseBody
    public Object vehicles(@RequestParam String driverId) throws Exception {


        Assist row = new Assist();
        row.setRequires(Assist.andEq("driver_id", driverId));
        List<Driverdl> driverdl = driverdlService.selectDriverdl(row);
        // Assist a = new Assist();
        List<DrivingLisence> drivingLisenceList = new ArrayList<>();
        driverdl.forEach(driverdl1 -> drivingLisenceList.add(drivingLisenceService.selectDrivingLisenceById
                (driverdl1.getDlId())));

        Page<DrivingLisence> page = new Page<>();
        page.rows = drivingLisenceList;
        page.total = drivingLisenceList.size();
        return page;
    }
    @RequiresPermissions("baseinfo.driver.addDriverLicence")
    @PostMapping(value = "/driver/addDriverdl")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addSections(@RequestParam String driverId, @RequestParam String dl)
            throws Exception {
        List<String> list = Arrays.asList(dl.split(","));
        // List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);

        list.forEach(str -> {
            Driverdl driverdl = new Driverdl();
            driverdl.setDriverId(driverId);
            driverdl.setDlId(Integer.valueOf(str));
            driverdlService.insertDriverdl(driverdl);
        });

        return ResponseEntityWrapper.OK();

    }


    @RequiresPermissions("baseinfo.driver.removeDriverLicence")
    @PostMapping(value = "/driver/removeDriverdl")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeSection(@RequestParam String driverId, @RequestParam String dl)
            throws Exception {
        Assist a = new Assist();
        a.setRequires(Assist.andEq("driver_id", driverId));
        a.setRequires(Assist.andEq("d_l_id", dl));
        driverdlService.deleteDriverdl(a);
        return ResponseEntityWrapper.OK();

    }


}
