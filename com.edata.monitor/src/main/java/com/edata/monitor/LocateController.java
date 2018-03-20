package com.edata.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edata.godp.domain.AppResponse;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.domain.locate.GodpDataBlock;
import com.edata.monitor.domain.locate.GroupVehicle;
import com.edata.monitor.domain.locate.VehicleInfo;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.LocateService;
import com.edata.monitor.service.UserService;
import com.edata.monitor.util.WebUtil;

@Controller
public class LocateController {
	@Autowired
	private LocateService locateService;
	@Autowired
	private UserService userService;

	@ServiceMethod(id = "center.locate", pid = "center", prefix = "打开", name = "实时监控", suffix = "页面")
	@RequestMapping("/locate/locate.iframe")
	public String index() {
		return "/center/locate/locate.iframe";
	}

	@RequestMapping("/locate/maptools.panel")
	public String maptools() {
		return "/center/locate/maptools.panel";
	}

	@RequestMapping(value = "/locate/groupVehicles", method = RequestMethod.POST)
	@ResponseBody
	public Object groupVehicles(@RequestParam boolean force, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		List<GroupVehicle> list = locateService.loadGroupVehicles(identify.getId(), force);
		GroupVehicle top = new GroupVehicle();
		top.setId(identify.getId());
		top.setType(2);
		top.setNa("全部");
		list.add(top);
		for (GroupVehicle groupVehicle : list) {
			switch (groupVehicle.getType()) {
			case 1:
				groupVehicle.setIcon("../resources/css/x16/flag.png");
				break;
			case 2:
				groupVehicle.setIcon("../resources/css/x16/company.png");
				break;
			default:
				groupVehicle.setIcon(groupVehicle.getO() == 1 ? "../resources/css/x16/online.png" : "../resources/css/x16/offline.png");
				break;
			}
		}
		return list;
	}

	/**
	 * 处理godp发送过来的数据
	 */
	@RequestMapping(value = "/locate/realtime", method = RequestMethod.POST)
	@ResponseBody
	public Object devices(@RequestBody GodpDataBlock block) {
		locateService.handleGodpData(block);
		return new AppResponse();
	}

	@RequestMapping(value = "/locate/latests", method = RequestMethod.POST)
	@ResponseBody
	public Object latests(@RequestParam List<String> list) throws Exception {
		return locateService.loadLatests(list);
	}

	@ServiceMethod(id = "center.locate.vehileinfo", pid = "center.locate", prefix = "查询", name = "车辆资料")
	@RequestMapping(value = "/locate/vehicleinfo.iframe", method = RequestMethod.GET)
	public String vehicleInfo(@RequestParam String vehicleId, Model model) throws Exception {
		VehicleInfo vehicle = locateService.vehicleInfo(vehicleId);
		model.addAttribute("vehicle", vehicle);

		return "/center/locate/vehicleinfo.iframe";
	}

	@RequestMapping(value = "/locate/unreadMultimediaEvent", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUnreadMultimediaEvent(HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return locateService.loadUnreadMultimediaEvent(identify.getId());
	}

	@RequestMapping(value = "/locate/readMultmediaEventReport", method = RequestMethod.POST)
	public String readMultmediaEventReport(@RequestParam String id, RedirectAttributes r, HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		try {
			locateService.readMultmediaEventReport(id, identify.getId(), identify.getName());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/locate/unreadDeviceEvent", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUnreadDeviceEvent(HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return locateService.loadUnreadDeviceEvent(identify.getId());
	}

	@RequestMapping(value = "/locate/readDeviceEventReport", method = RequestMethod.POST)
	public String readDeviceEventReport(@RequestParam String id, RedirectAttributes r, HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		try {
			locateService.readDeviceEventReport(id, identify.getId(), identify.getName());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/locate/unreadDeviceUpgradeResultReport", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUnreadDeviceUpgradeResultReport(HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return locateService.loadUnreadDeviceUpgradeResultReport(identify.getId());
	}

	@RequestMapping(value = "/locate/readDeviceUpgradeResultReport", method = RequestMethod.POST)
	public String readDeviceUpgradeResultReport(@RequestParam String id, RedirectAttributes r, HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		try {
			locateService.readDeviceUpgradeResultReport(id, identify.getId(), identify.getName());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
