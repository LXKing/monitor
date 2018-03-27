package com.rayton.gps.controller;

import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.service.CommonService;
import com.rayton.gps.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CommonContorller {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private CommonService commonService;



    @RequestMapping(value = "/common/device/status", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object deviceStatus(@RequestParam String number) throws Exception {
        return commonService.getDeviceStatus(number);
    }

    @RequestMapping(value = "/common/device/vehicle", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object deviceVehicle(@RequestParam String number, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return commonService.getDeviceVehicle(identity.getId(), number);
    }

    @RequestMapping(value = "/common/device/log/load", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object deviceLoadDataLog(@RequestParam String number, @RequestParam Date start, @RequestParam Date end,
                                    @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest
                                                request) throws Exception {

        return commonService.loadDataLog(number, start, end, pageIndex, pageSize);
    }

    @RequestMapping(value = "/common/device/log/enable", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object deviceEnableDataLog(@RequestParam String number, @RequestParam boolean enable) throws Exception {

        return commonService.setDataLog(number, enable);
    }

    @RequestMapping("/common/authorizes")
    @ResponseBody
    public Object authorizes(HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return securityService.authorizes(identity.getCompanyId());
    }

}
