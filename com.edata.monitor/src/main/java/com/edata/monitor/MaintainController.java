package com.edata.monitor;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
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

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.domain.baseinfo.Maintain;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.MaintainService;
import com.edata.monitor.util.WebUtil;

@Controller
public class MaintainController {
	@Autowired
	private MaintainService maintainService;

	@ServiceMethod(id = "baseinfo.maintain", pid = "baseinfo", prefix = "打开", name = "车辆保养", suffix = "页面")
	@RequestMapping("/maintain/maintain.iframe")
	public String frame() {
		return "/baseinfo/maintain/maintain.iframe";
	}

	@RequestMapping("/maintain/query")
	@ResponseBody
	public Object query(@RequestParam String plateNumber, @RequestParam Date from, @RequestParam Date to, @RequestParam int pageIndex,
			@RequestParam int pageSize, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return maintainService.query(identify.getCompanyId(), plateNumber, from, to, pageIndex, pageSize);
	}

	@RequestMapping(value = "/maintain/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		Maintain maintain = new Maintain();
		model.addAttribute("maintain", maintain);
		return "/baseinfo/maintain/create.form";
	}

	@RequestMapping(value = "/maintain/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/maintain/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			maintain.setCompanyId(identify.getCompanyId());
			maintain.setUserId(identify.getId());
			maintain.setUserName(identify.getName());
			maintainService.Create(maintain);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/maintain/edit.form", method = RequestMethod.GET)
	public String edit(@RequestParam String id, Model model) throws Exception {
		Maintain maintain = maintainService.fetch(id);
		model.addAttribute("maintain", maintain);
		return "/baseinfo/maintain/edit.form";
	}

	@RequestMapping(value = "/maintain/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("maintain") @Valid Maintain maintain, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) throws Exception {
		if (binding.hasErrors())
			return "/baseinfo/maintain/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			maintain.setCompanyId(identify.getCompanyId());
			maintain.setUserId(identify.getId());
			maintain.setUserName(identify.getName());
			maintainService.update(maintain);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/maintain/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id, RedirectAttributes r) {
		try {
			maintainService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
