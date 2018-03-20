package com.edata.monitor;

import java.util.List;

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
import com.edata.monitor.domain.baseinfo.RouteArea;
import com.edata.monitor.domain.baseinfo.SectionAreaInfo;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.RouteAreaService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;

@Controller
public class RouteAreaController {
	@Autowired
	private RouteAreaService routeAreaService;

	@ServiceMethod(id = "baseinfo.routeArea", pid = "baseinfo", prefix = "打开", name = "路线", suffix = "管理页面")
	@RequestMapping("/routeArea/routeArea.iframe")
	public String index() {
		return "/baseinfo/routeArea/routeArea.iframe";
	}

	@RequestMapping(value = "/routeArea/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return routeAreaService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/routeArea/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		filter = filter == null ? "" : filter;
		try {
			return routeAreaService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/routeArea/sections", method = RequestMethod.POST)
	@ResponseBody
	public Object sections(@RequestParam long routeId, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");

		Page<SectionAreaInfo> page = new Page<SectionAreaInfo>();
		page.rows = routeAreaService.assignedSections(identify.getCompanyId(), routeId);
		page.total = page.rows == null ? 0 : page.rows.size();
		return page;
	}

	@RequestMapping(value = "/routeArea/addSections", method = RequestMethod.POST)
	public String addSections(@RequestParam long routeId, @RequestParam String list, RedirectAttributes r) {
		try {
			List<Long> ids = JsonMapper.toObject(list, List.class, Long.class);
			routeAreaService.addSections(routeId, ids);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/routeArea/removeSection", method = RequestMethod.POST)
	public String removeSection(@RequestParam long routeId, @RequestParam long sectionId, RedirectAttributes r) {
		try {
			routeAreaService.removeSection(routeId, sectionId);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/routeArea/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		RouteArea routeArea = new RouteArea();
		routeArea.setDeviceCatch(true);
		model.addAttribute("routeArea", routeArea);
		return "/baseinfo/routeArea/create.form";
	}

	@RequestMapping(value = "/routeArea/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("routeArea") @Valid RouteArea routeArea, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/routeArea/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			routeArea.setCompanyId(identify.getCompanyId());
			routeAreaService.create(routeArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/routeArea/edit.form")
	public String edit(@RequestParam long id, Model model) throws Exception {
		RouteArea routeArea = routeAreaService.fetch(id);
		model.addAttribute("routeArea", routeArea);
		return "/baseinfo/routeArea/edit.form";
	}

	@RequestMapping(value = "/routeArea/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("routeArea") @Valid RouteArea routeArea, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/routeArea/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			routeAreaService.update(identify.getUnid(), identify.getName(), routeArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/routeArea/delete", method = RequestMethod.POST)
	public String delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			routeAreaService.delete(identify.getUnid(), identify.getName(), id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/routeArea/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		if (checkId) {
			response.getWriter().print(!routeAreaService.exist(name, identify.getCompanyId(), id));
		} else {
			response.getWriter().print(!routeAreaService.exist(name, identify.getCompanyId()));
		}
	}

	@RequestMapping("/routeArea/vehicles")
	@ResponseBody
	public Object vehicles(@RequestParam long routeAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return routeAreaService.assignedVehicles(routeAreaId, pageIndex, pageSize);
	}

	@RequestMapping(value = "/routeArea/addVehicles", method = RequestMethod.POST)
	public String addVehicles(@RequestParam long routeAreaId, @RequestParam String vehicles, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
			routeAreaService.addVehicles(identify.getUnid(), identify.getName(), routeAreaId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/routeArea/removeVehicle", method = RequestMethod.POST)
	public String removeSection(@RequestParam long routeAreaId, @RequestParam String number, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			routeAreaService.removeVehicle(identify.getUnid(), identify.getName(), routeAreaId, number);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
