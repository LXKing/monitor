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

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.domain.baseinfo.Simcard;
import com.edata.monitor.domain.baseinfo.SimcardInfo;
import com.edata.monitor.domain.baseinfo.SimcardSearchInfo;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.SimcardService;
import com.edata.monitor.util.WebUtil;

@Controller
public class SimcardController {
	@Autowired
	private SimcardService simcardService;

	@ServiceMethod(id = "baseinfo.simcard", pid = "baseinfo", prefix = "打开", name = "SIM卡管理", suffix = "页面")
	@RequestMapping(value = "/simcard/simcard.iframe", method = RequestMethod.GET)
	public String index() {
		return "/baseinfo/simcard/simcard.iframe";
	}

	@RequestMapping(value = "/simcard/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		try {
			Page<SimcardInfo> page = simcardService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
			return page;
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/simcard/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		filter = filter == null ? "" : filter;
		try {
			Page<SimcardSearchInfo> page = simcardService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
			return page;
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/simcard/free", method = RequestMethod.POST)
	@ResponseBody
	public Object findfree(@RequestParam(required = false) String phoneNumber, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		phoneNumber = phoneNumber == null ? "" : phoneNumber;
		try {
			Page<SimcardSearchInfo> page = simcardService.free(identify.getCompanyId(), phoneNumber, pageIndex, pageSize);
			return page;
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/simcard/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		Simcard simcard = new Simcard();
		model.addAttribute("simcard", simcard);

		return "/baseinfo/simcard/create.form";
	}

	@RequestMapping(value = "/simcard/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("simcard") @Valid Simcard simcard, BindingResult binding, Model model, RedirectAttributes r,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		if (binding.hasErrors())
			return "/baseinfo/simcard/create.form";

		try {
			simcard.setCompanyId(identify.getCompanyId());
			simcardService.create(simcard);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/simcard/edit.form", method = RequestMethod.GET)
	public String edit(@RequestParam String id, Model model) throws Exception {
		Simcard simcard = simcardService.fetch(id);
		model.addAttribute("simcard", simcard);

		return "/baseinfo/simcard/edit.form";
	}

	@RequestMapping(value = "/simcard/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("simcard") @Valid Simcard simcard, BindingResult binding, Model model, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/simcard/edit.form";

		try {
			simcardService.update(simcard);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/simcard/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id, RedirectAttributes r) {
		try {
			simcardService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/simcard/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String phoneNumber, @RequestParam(required = false) String id, @RequestParam boolean checkId,
			HttpServletResponse response) throws Exception {
		if (checkId) {
			response.getWriter().print(!simcardService.exist(phoneNumber, id));
		} else {
			response.getWriter().print(!simcardService.exist(phoneNumber));
		}
	}
}
