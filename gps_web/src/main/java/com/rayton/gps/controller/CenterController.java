package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CenterController {

    @RequiresPermissions("center")
    @Log(name = "打开监控中心页面")
    @GetMapping("/center")
    public String index() throws Exception {

        // throw new Exception("xxx");
        // return "/center/index";
        // TODO
        return "/center/new/index";
    }
}
