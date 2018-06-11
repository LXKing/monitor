package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.UserService;
import com.rayton.gps.util.ResponseEntityWrapper;
import com.rayton.gps.util.enums.UserOptions;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.Map;

@Controller
public class MapOptionController {
    @Autowired
    private UserService userService;


    @RequiresPermissions("baseinfo.mapOption")
    @Log(name = "打开地图设置页面")
    @GetMapping("/mapOption/mapOption.iframe")
    public String index() {
        return "/baseinfo/mapOption/mapOption.iframe";
    }

    @GetMapping("/mapOption/query")
    @ResponseBody
    public Object query() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return userService.getUserOptions(identity.getId(), UserOptions.MapOption);
    }

    @PostMapping(value = "/mapOption/save")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> save(@RequestParam double lat, @RequestParam double lng, @RequestParam int zoom) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        Map<Object, Object> values = new Hashtable<Object, Object>();
        values.put("lat", lat);
        values.put("lng", lng);
        values.put("zoom", zoom);

        userService.saveUserOptions(identity.getId(), UserOptions.MapOption, values);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //
        //     Map<Object, Object> values = new Hashtable<Object, Object>();
        //     values.put("lat", lat);
        //     values.put("lng", lng);
        //     values.put("zoom", zoom);
        //
        //     userService.saveUserOptions(identity.getId(), UserOptions.MapOption, values);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

}
