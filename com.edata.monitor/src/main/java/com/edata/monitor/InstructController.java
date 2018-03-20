package com.edata.monitor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edata.monitor.dao.Page;
import com.edata.monitor.domain.instruct.FeatureInfo;
import com.edata.monitor.domain.instruct.InstructInfo;
import com.edata.monitor.domain.instruct.ParameterInfo;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.InstructService;

@Controller
public class InstructController {
	@Autowired
	private InstructService instructService;

	@RequestMapping(value = "/instruct/features", method = RequestMethod.POST)
	@ResponseBody
	public Object features(@RequestParam String number) throws Exception {
		List<FeatureInfo> features = instructService.features(number);

		return features;
	}

	@RequestMapping(value = "/instruct/params", method = RequestMethod.POST)
	@ResponseBody
	public Object params(@RequestParam String pid, @RequestParam String featureId) throws Exception {
		List<ParameterInfo> features = instructService.parameters(pid, featureId);
		return features;
	}

	@RequestMapping(value = "/instruct/send", method = RequestMethod.POST)
	@ResponseBody
	public Object send(@RequestParam String deviceNumber, @RequestParam String featureId, @RequestParam String command, @RequestParam String name,
			@RequestParam String params, @RequestParam String confirm, HttpServletRequest request) throws Exception {
		String serialNumber = UUID.randomUUID().toString();
		Identify user = (Identify) request.getAttribute("user");
		InstructInfo info = instructService.send(user.getId(), serialNumber, deviceNumber, user.getUnid(), user.getName(), command, name, params,
				confirm);
		return info;
	}

	@RequestMapping(value = "/instruct/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		Page<InstructInfo> page = instructService.query(deviceNumber, identify.getUnid(), start, end, pageIndex, pageSize);

		return page;
	}

	@RequestMapping(value = "/instruct/details.form", method = RequestMethod.GET)
	public String details(@RequestParam String id, Model model) throws Exception {
		InstructInfo info = instructService.fetch(id);
		String result = info.getResult().replace("\r\n", "<br />");
		info.setResult(result);
		model.addAttribute("instruct", info);

		return "/instruct/details.form";
	}
}
