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
import com.edata.monitor.domain.baseinfo.RectangleArea;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.RectangleAreaService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;

@Controller
public class RectangleAreaController {
	@Autowired
	private RectangleAreaService rectangleAreaService;

	@ServiceMethod(id = "baseinfo.rectangleArea", pid = "baseinfo", prefix = "打开", name = "矩形区域", suffix = "管理页面")
	@RequestMapping("/rectangleArea/rectangleArea.iframe")
	public String index() {
		return "/baseinfo/rectangleArea/rectangleArea.iframe";
	}

	@RequestMapping(value = "/rectangleArea/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return rectangleAreaService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/rectangleArea/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");

		filter = filter == null ? "" : filter;
		try {
			return rectangleAreaService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/rectangleArea/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		RectangleArea rectangleArea = new RectangleArea();
		rectangleArea.setDeviceCatch(true);
		model.addAttribute("rectangleArea", rectangleArea);
		return "/baseinfo/rectangleArea/create.form";
	}

	@RequestMapping(value = "/rectangleArea/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("rectangleArea") @Valid RectangleArea rectangleArea, BindingResult binding, Model model,
			HttpServletRequest request, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/rectangleArea/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			rectangleArea.setCompanyId(identify.getCompanyId());
			rectangleAreaService.create(rectangleArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/rectangleArea/edit.form")
	public String edit(@RequestParam long id, Model model) throws Exception {
		RectangleArea rectangleArea = rectangleAreaService.fetch(id);
		model.addAttribute("rectangleArea", rectangleArea);
		return "/baseinfo/rectangleArea/edit.form";
	}

	@RequestMapping(value = "/rectangleArea/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("rectangleArea") @Valid RectangleArea rectangleArea, BindingResult binding, Model model,
			HttpServletRequest request, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/rectangleArea/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			rectangleAreaService.update(identify.getUnid(), identify.getName(), rectangleArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/rectangleArea/delete", method = RequestMethod.POST)
	public String delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			rectangleAreaService.delete(identify.getUnid(), identify.getName(), id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/rectangleArea/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		if (checkId) {
			response.getWriter().print(!rectangleAreaService.exist(name, identify.getCompanyId(), id));
		} else {
			response.getWriter().print(!rectangleAreaService.exist(name, identify.getCompanyId()));
		}
	}

	@RequestMapping("/rectangleArea/vehicles")
	@ResponseBody
	public Object vehicles(@RequestParam long rectangleAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return rectangleAreaService.assignedVehicles(rectangleAreaId, pageIndex, pageSize);
	}

	@RequestMapping(value = "/rectangleArea/addVehicles", method = RequestMethod.POST)
	public String addVehicles(@RequestParam long rectangleAreaId, @RequestParam String vehicles, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
			rectangleAreaService.addVehicles(identify.getUnid(), identify.getName(), rectangleAreaId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/rectangleArea/removeVehicle", method = RequestMethod.POST)
	public String removeSection(@RequestParam long rectangleAreaId, @RequestParam String number, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			rectangleAreaService.removeVehicle(identify.getUnid(), identify.getName(), rectangleAreaId, number);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
