package com.edata.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edata.monitor.aop.ServiceMethod;

@Controller
public class CenterController {
	@ServiceMethod(id = "center", prefix = "打开", name = "监控中心", suffix = "页面")
	@RequestMapping("/center")
	public String index() {
		
		return "/center/index";
	}
}
