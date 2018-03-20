package com.edata.monitor;

import java.util.List;

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

import com.edata.common.Tuple;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.domain.baseinfo.Company;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.model.baseinfo.CompanyModel;
import com.edata.monitor.service.CompanyService;
import com.edata.monitor.util.WebUtil;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@ServiceMethod(id = "baseinfo.company", pid = "baseinfo", prefix = "打开", name = "企业管理", suffix = "页面")
	@RequestMapping("/company/company.iframe")
	public String iframe() {
		return "/baseinfo/company/company.iframe";
	}

	@RequestMapping("/company/query")
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return companyService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/company/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		CompanyModel m = new CompanyModel();
		model.addAttribute("editor", m);
		return "/baseinfo/company/create.form";
	}

	@RequestMapping(value = "/company/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/company/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			m.setPid(identify.getCompanyId());
			companyService.create(m.getCompany(), m.getUser());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/company/edit.form", method = RequestMethod.GET)
	public String edit(@RequestParam String id, Model model) throws Exception {
		Tuple<Company, User> tuple = companyService.fetch(id);
		CompanyModel m = new CompanyModel();
		m.fill(tuple.t);
		m.fill(tuple.e);
		model.addAttribute("editor", m);
		return "/baseinfo/company/edit.form";
	}

	@RequestMapping(value = "/company/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) throws Exception {
		if (binding.hasErrors())
			return "/baseinfo/company/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			m.setPid(identify.getCompanyId());
			companyService.update(m.getCompany(), m.getUser());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/company/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id, RedirectAttributes r) {
		try {
			companyService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/company/authorize.form", method = RequestMethod.GET)
	public String authorize(@RequestParam String id) throws Exception {
		return "/baseinfo/company/authorize.form";
	}

	@RequestMapping("/company/authorizes")
	@ResponseBody
	public Object authorizes(@RequestParam String companyId) throws Exception {
		return companyService.authorizes(companyId);
	}

	@RequestMapping(value = "/company/authorize", method = RequestMethod.POST)
	public String authorize(@RequestParam String companyId, @RequestParam("list[]") List<String> list, RedirectAttributes r) throws Exception {
		try {
			companyService.authorize(companyId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

}
