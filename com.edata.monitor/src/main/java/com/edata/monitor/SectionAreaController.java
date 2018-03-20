package com.edata.monitor;

import java.util.ArrayList;
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
import com.edata.monitor.domain.baseinfo.SectionArea;
import com.edata.monitor.domain.baseinfo.SectionPoint;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.model.baseinfo.SectionAreaModel;
import com.edata.monitor.service.SectionAreaService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;

@Controller
public class SectionAreaController {
	@Autowired
	private SectionAreaService sectionAreaService;

	@ServiceMethod(id = "baseinfo.sectionArea", pid = "baseinfo", prefix = "打开", name = "路段", suffix = "管理页面")
	@RequestMapping("/sectionArea/sectionArea.iframe")
	public String index() {
		return "/baseinfo/sectionArea/sectionArea.iframe";
	}

	@RequestMapping(value = "/sectionArea/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return sectionAreaService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/sectionArea/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		filter = filter == null ? "" : filter;
		try {
			return sectionAreaService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/sectionArea/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		SectionAreaModel sectionArea = new SectionAreaModel();
		model.addAttribute("sectionArea", sectionArea);
		return "/baseinfo/sectionArea/create.form";
	}

	@RequestMapping(value = "/sectionArea/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding, Model model,
			HttpServletRequest request, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/sectionArea/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			sectionArea.setCompanyId(identify.getCompanyId());
			String path = sectionArea.getPath();
			List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
			sectionArea.setPoints(points);
			sectionAreaService.create(sectionArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/sectionArea/edit.form")
	public String edit(@RequestParam long id, Model model) throws Exception {
		SectionArea route = sectionAreaService.fetch(id);
		SectionAreaModel sectionArea = JsonMapper.convertValue(route, SectionAreaModel.class);
		model.addAttribute("sectionArea", sectionArea);
		return "/baseinfo/sectionArea/edit.form";
	}

	@RequestMapping(value = "/sectionArea/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding, Model model, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/sectionArea/edit.form";

		try {
			String path = sectionArea.getPath();
			List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
			sectionArea.setPoints(points);
			sectionAreaService.update(sectionArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/sectionArea/delete", method = RequestMethod.POST)
	public String delete(@RequestParam long id, RedirectAttributes r) {
		try {
			sectionAreaService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/sectionArea/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		if (checkId) {
			response.getWriter().print(!sectionAreaService.exist(name, identify.getCompanyId(), id));
		} else {
			response.getWriter().print(!sectionAreaService.exist(name, identify.getCompanyId()));
		}
	}
}
