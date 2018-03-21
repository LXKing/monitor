package com.edata.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ObdController {
    @RequestMapping("/obd")
    public String index() {
        return "/obd/index";
    }

}
