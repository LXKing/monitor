package com.rayton.gps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {
    @GetMapping("/log")
    public String index() {
        return "/log/index";
    }
}
