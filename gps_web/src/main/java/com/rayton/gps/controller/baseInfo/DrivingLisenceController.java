package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.dao.lisence.DrivingLisence;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.lisence.DrivingLisenceService;
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
public class DrivingLisenceController {

    @Autowired
    private DrivingLisenceService drivingLisenceService;







    @RequiresPermissions("baseinfo.licence.drivingLicence")
    @GetMapping(value = "/drivingLicence/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int
            limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        Assist row = new Assist();
        row.setRequires(Assist.andEq("company_id", identity.getCompanyId()));

        Map map = new HashMap();
        map.put("code", 0);
        map.put("count", drivingLisenceService.getDrivingLisenceRowCount(row));
        Assist assist = new Assist();
        assist.setRowSize(limit);
        assist.setStartRow((page - 1) * limit);
        assist.setRequires(Assist.andEq("company_id", identity.getCompanyId()));
        // assist.setRequires(Assist.andLike("",""));
        // List<DrivingLisence> result = drivingLisenceService.selectDrivingLisence(assist);

        map.put("data", drivingLisenceService.selectDrivingLisence(assist));
        // result.data = result.rows;
        // result.msg = "mmp";
        return map;

    }


    @PostMapping(value = "/drivingLicence/create.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(@ModelAttribute("drivingLicence") @Valid DrivingLisence
                                                                  drivingLicence, BindingResult binding) throws
            Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors())

            return ResponseEntityWrapper.Failed();
        drivingLicence.setCompanyId(identity.getCompanyId());
        drivingLisenceService.insertDrivingLisence(drivingLicence);
        return ResponseEntityWrapper.OK();


    }

    @GetMapping(value = "/drivingLicence/edit.form")
    @ResponseBody
    public Object edit(@RequestParam Integer id) throws Exception {
        DrivingLisence drivingLisence = drivingLisenceService.selectDrivingLisenceById(id);

        return drivingLisence;

    }

    @PostMapping(value = "/drivingLicence/edit.form")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> edit(@ModelAttribute("drivingLicence") @Valid DrivingLisence
                                                                drivingLicence, BindingResult binding) throws
            Exception {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/driver/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        drivingLicence.setCompanyId(identity.getCompanyId());
        drivingLisenceService.updateDrivingLisenceById(drivingLicence);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/drivingLicence/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Integer id) throws Exception {
        drivingLisenceService.deleteDrivingLisenceById(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/drivingLicence/exist")
    @ResponseBody
    public Object exists(@RequestParam String name) throws Exception {

        Assist row = new Assist();
        row.setRequires(Assist.andEq("name", name));
        long a = drivingLisenceService.getDrivingLisenceRowCount(row);
        return a == 0 ? true : false;


    }
}
