package com.edata.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripController {
	@RequestMapping("/trip")
	public String index() {
		return "/trip/index";
	}

}
