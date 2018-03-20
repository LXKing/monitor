package com.edata.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {
	@RequestMapping("/log")
	public String index() {
		return "/log/index";
	}
}
