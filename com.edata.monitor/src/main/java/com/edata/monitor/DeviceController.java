package com.edata.monitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.domain.baseinfo.Device;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.model.baseinfo.DeviceModel;
import com.edata.monitor.service.DeviceService;
import com.edata.monitor.util.WebUtil;

@Controller
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	@ServiceMethod(id = "baseinfo.device", pid = "baseinfo", prefix = "打开", name = "终端管理", suffix = "页面")
	@RequestMapping("/device/device.iframe")
	public String index() {
		return "/baseinfo/device/device.iframe";
	}

	@RequestMapping(value = "/device/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return deviceService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/device/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		filter = filter == null ? "" : filter;
		try {
			return deviceService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/device/free", method = RequestMethod.POST)
	@ResponseBody
	public Object free(@RequestParam(required = false) String deviceNumber, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		deviceNumber = deviceNumber == null ? "" : deviceNumber;
		try {
			return deviceService.free(identify.getCompanyId(), deviceNumber, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/device/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		DeviceModel m = new DeviceModel();
		m.setEnable(true);
		model.addAttribute("editor", m);
		return "/baseinfo/device/create.form";
	}

	@RequestMapping(value = "/device/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("editor") @Valid DeviceModel m, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/device/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			m.setCompanyId(identify.getCompanyId());
			deviceService.create(m.getDevice(), m.getUser());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/device/edit.form")
	public String edit(@RequestParam String id, Model model) throws Exception {
		Tuple<Device, User> tuple = deviceService.fetch(id);
		DeviceModel m = new DeviceModel();
		m.fill(tuple.e);
		m.fill(tuple.t);
		model.addAttribute("editor", m);
		return "/baseinfo/device/edit.form";
	}

	@RequestMapping(value = "/device/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("editor") @Valid DeviceModel m, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/device/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");

			m.setCompanyId(identify.getCompanyId());
			deviceService.update(m.getDevice(), m.getUser());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/device/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id, RedirectAttributes r) {
		try {
			deviceService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/device/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String deviceNumber, @RequestParam(required = false) String id, @RequestParam boolean checkId,
			HttpServletResponse response) throws Exception {
		if (checkId) {
			response.getWriter().print(!deviceService.exist(deviceNumber, id));
		} else {
			response.getWriter().print(!deviceService.exist(deviceNumber));
		}
	}
}
