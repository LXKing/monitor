package com.rayton.gps.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    //
    @GetMapping(value = "/api")
    public String test() {

        return "swagger-ui.html";
    }

}
