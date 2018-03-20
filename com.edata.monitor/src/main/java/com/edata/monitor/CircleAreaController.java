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
import com.edata.monitor.domain.baseinfo.CircleArea;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.CircleAreaService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;

@Controller
public class CircleAreaController {
	@Autowired
	private CircleAreaService circleAreaService;

	@ServiceMethod(id = "baseinfo.circleArea", pid = "baseinfo", prefix = "打开", name = "圆形区域", suffix = "页面")
	@RequestMapping("/circleArea/circleArea.iframe")
	public String index() {
		return "/baseinfo/circleArea/circleArea.iframe";
	}

	@RequestMapping(value = "/circleArea/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return circleAreaService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping(value = "/circleArea/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
			HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		filter = filter == null ? "" : filter;
		try {
			return circleAreaService.search(identify.getCompanyId(), filter, pageIndex, pageSize);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/circleArea/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		CircleArea circleArea = new CircleArea();
		circleArea.setDeviceCatch(true);
		model.addAttribute("circleArea", circleArea);
		return "/baseinfo/circleArea/create.form";
	}

	@RequestMapping(value = "/circleArea/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/circleArea/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			circleArea.setCompanyId(identify.getCompanyId());
			circleAreaService.create(circleArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/circleArea/edit.form")
	public String edit(@RequestParam long id, Model model) throws Exception {
		CircleArea circleArea = circleAreaService.fetch(id);
		model.addAttribute("circleArea", circleArea);
		return "/baseinfo/circleArea/edit.form";
	}

	@RequestMapping(value = "/circleArea/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/circleArea/edit.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			circleAreaService.update(identify.getUnid(), identify.getName(), circleArea);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/circleArea/delete", method = RequestMethod.POST)
	public String delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			circleAreaService.delete(identify.getUnid(), identify.getName(), id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/circleArea/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		if (checkId) {
			response.getWriter().print(!circleAreaService.exist(name, identify.getCompanyId(), id));
		} else {
			response.getWriter().print(!circleAreaService.exist(name, identify.getCompanyId()));
		}
	}

	@RequestMapping("/circleArea/vehicles")
	@ResponseBody
	public Object vehicles(@RequestParam long circleAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return circleAreaService.assignedVehicles(circleAreaId, pageIndex, pageSize);
	}

	@RequestMapping(value = "/circleArea/addVehicles", method = RequestMethod.POST)
	public String addVehicles(@RequestParam long circleAreaId, @RequestParam String vehicles, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
			circleAreaService.addVehicles(identify.getUnid(), identify.getName(), circleAreaId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/circleArea/removeVehicle", method = RequestMethod.POST)
	public String removeSection(@RequestParam long circleAreaId, @RequestParam String number, HttpServletRequest request, RedirectAttributes r) {
		try {
			Identify identify = (Identify) request.getAttribute("user");
			circleAreaService.removeVehicle(identify.getUnid(), identify.getName(), circleAreaId, number);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
