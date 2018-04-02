package com.rayton.gps.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        System.out.println("ddddd");
        return "/center/replay/replay.iframe";
    }

    @RequestMapping(value = "/center/replay/replay.iframe?deviceNumber=10189415505&plateNumber=京B37A93", method =
            RequestMethod.GET)
    public String s() {
        System.out.println("66666");
        return "/center/replay/replay.iframe";
    }
}
