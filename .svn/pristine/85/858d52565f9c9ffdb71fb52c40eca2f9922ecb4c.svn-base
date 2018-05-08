package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.service.UserService;
import com.rayton.gps.util.WebUtil;
import com.rayton.gps.util.enums.UserOptions;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;
import java.util.Map;

@Controller
public class MapOptionController {
    @Autowired
    private UserService userService;

    @ServiceMethod(id = "baseinfo.mapOption", pid = "baseinfo", prefix = "打开", name = "地图设置", suffix = "页面")
    @RequestMapping("/mapOption/mapOption.iframe")
    public String index() {
        return "/baseinfo/mapOption/mapOption.iframe";
    }

    @RequestMapping("/mapOption/query")
    @ResponseBody
    public Object query(HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return userService.getUserOptions(identity.getId(), UserOptions.MapOption);
    }

    @RequestMapping(value = "/mapOption/save", method = RequestMethod.POST)
    public String save(@RequestParam double lat, @RequestParam double lng, @RequestParam int zoom, HttpServletRequest
            request, RedirectAttributes r) {

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

            Map<Object, Object> values = new Hashtable<Object, Object>();
            values.put("lat", lat);
            values.put("lng", lng);
            values.put("zoom", zoom);

            userService.saveUserOptions(identity.getId(), UserOptions.MapOption, values);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

}
