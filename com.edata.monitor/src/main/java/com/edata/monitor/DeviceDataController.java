package com.edata.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edata.common.ObjectId;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.cache.AuthorizeCache;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.model.baseinfo.AdminMenu;
import com.edata.monitor.service.DeviceDataService;
import com.edata.monitor.service.InstructService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;

@Controller
public class DeviceDataController {
	@Autowired
	private DeviceDataService deviceDataService;
	@Autowired
	private InstructService instructService;

	@ServiceMethod(id = "center.deviceData", pid = "center", prefix = "打开", name = "数据查询", suffix = "页面")
	@RequestMapping("/deviceData/deviceData.iframe")
	public String index() {
		return "/center/deviceData/deviceData.iframe";
	}

	@RequestMapping("/deviceData/menus")
	@ResponseBody
	public Object menus(HttpServletRequest request) throws Exception {
		ArrayList<AdminMenu> menus = new ArrayList<AdminMenu>();

		int count = 0;

		AdminMenu menuMultimedia = new AdminMenu();
		menuMultimedia.setId(ObjectId.next());
		menuMultimedia.setText("多媒体");
		menuMultimedia.setLeaf(false);

		if (AuthorizeCache.hasAuthorized("center.deviceData.multimedia", request)) {
			AdminMenu menuPhoto = new AdminMenu();
			menuPhoto.setId(ObjectId.next());
			menuPhoto.setPid(menuMultimedia.getId());
			menuPhoto.setText("多媒体数据");
			menuPhoto.setUrl("../deviceData/multimedia.iframe");
			menuPhoto.setLeaf(true);
			menus.add(menuPhoto);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.multimediaEvent", request)) {
			AdminMenu menuMultimediaEvent = new AdminMenu();
			menuMultimediaEvent.setId(ObjectId.next());
			menuMultimediaEvent.setPid(menuMultimedia.getId());
			menuMultimediaEvent.setText("多媒体事件");
			menuMultimediaEvent.setUrl("../deviceData/multimediaEvent.iframe");
			menuMultimediaEvent.setLeaf(true);
			menus.add(menuMultimediaEvent);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.multimediaRetrieval", request)) {
			AdminMenu menuMultimediaDataRetrieval = new AdminMenu();
			menuMultimediaDataRetrieval.setId(ObjectId.next());
			menuMultimediaDataRetrieval.setPid(menuMultimedia.getId());
			menuMultimediaDataRetrieval.setText("多媒体数据检索");
			menuMultimediaDataRetrieval.setUrl("../deviceData/multimediaRetrieval.iframe");
			menuMultimediaDataRetrieval.setLeaf(true);
			menus.add(menuMultimediaDataRetrieval);
			count++;
		}

		if (count > 0)
			menus.add(menuMultimedia);
		count = 0;

		AdminMenu menuDrivingRecord = new AdminMenu();
		menuDrivingRecord.setId(ObjectId.next());
		menuDrivingRecord.setText("行驶记录");
		menuDrivingRecord.setLeaf(false);

		if (AuthorizeCache.hasAuthorized("center.deviceData.drivingRecorder", request)) {
			AdminMenu menuTravelingDataRecorder = new AdminMenu();
			menuTravelingDataRecorder.setId(ObjectId.next());
			menuTravelingDataRecorder.setPid(menuDrivingRecord.getId());
			menuTravelingDataRecorder.setText("行驶记录仪信息");
			menuTravelingDataRecorder.setUrl("../deviceData/drivingRecorder.iframe");
			menuTravelingDataRecorder.setLeaf(true);
			menus.add(menuTravelingDataRecorder);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.accidentDoubtLog", request)) {
			AdminMenu menuAccidentDoubtLog = new AdminMenu();
			menuAccidentDoubtLog.setId(ObjectId.next());
			menuAccidentDoubtLog.setPid(menuDrivingRecord.getId());
			menuAccidentDoubtLog.setText("事故疑点日志");
			menuAccidentDoubtLog.setUrl("../deviceData/accidentDoubtLog.iframe");
			menuAccidentDoubtLog.setLeaf(true);
			menus.add(menuAccidentDoubtLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.powerLog", request)) {
			AdminMenu menuPowerSupplyLog = new AdminMenu();
			menuPowerSupplyLog.setId(ObjectId.next());
			menuPowerSupplyLog.setPid(menuDrivingRecord.getId());
			menuPowerSupplyLog.setText("外部供电日志");
			menuPowerSupplyLog.setUrl("../deviceData/powerLog.iframe");
			menuPowerSupplyLog.setLeaf(true);
			menus.add(menuPowerSupplyLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.timeoutLog", request)) {
			AdminMenu menuTimeoutDrivingLog = new AdminMenu();
			menuTimeoutDrivingLog.setId(ObjectId.next());
			menuTimeoutDrivingLog.setPid(menuDrivingRecord.getId());
			menuTimeoutDrivingLog.setText("超时驾驶日志");
			menuTimeoutDrivingLog.setUrl("../deviceData/timeoutLog.iframe");
			menuTimeoutDrivingLog.setLeaf(true);
			menus.add(menuTimeoutDrivingLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.parameterLog", request)) {
			AdminMenu menuParameterChangeLog = new AdminMenu();
			menuParameterChangeLog.setId(ObjectId.next());
			menuParameterChangeLog.setPid(menuDrivingRecord.getId());
			menuParameterChangeLog.setText("参数修改日志");
			menuParameterChangeLog.setUrl("../deviceData/parameterLog.iframe");
			menuParameterChangeLog.setLeaf(true);
			menus.add(menuParameterChangeLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.loginLog", request)) {
			AdminMenu menuDriverLoginLog = new AdminMenu();
			menuDriverLoginLog.setId(ObjectId.next());
			menuDriverLoginLog.setPid(menuDrivingRecord.getId());
			menuDriverLoginLog.setText("驾驶员登签日志");
			menuDriverLoginLog.setUrl("../deviceData/loginLog.iframe");
			menuDriverLoginLog.setLeaf(true);
			menus.add(menuDriverLoginLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.speedStatusLog", request)) {
			AdminMenu menuSpeedStatusLog = new AdminMenu();
			menuSpeedStatusLog.setId(ObjectId.next());
			menuSpeedStatusLog.setPid(menuDrivingRecord.getId());
			menuSpeedStatusLog.setText("速度状态日志");
			menuSpeedStatusLog.setUrl("../deviceData/speedStatusLog.iframe");
			menuSpeedStatusLog.setLeaf(true);
			menus.add(menuSpeedStatusLog);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.locateLog", request)) {
			AdminMenu menuPositionRecord = new AdminMenu();
			menuPositionRecord.setId(ObjectId.next());
			menuPositionRecord.setPid(menuDrivingRecord.getId());
			menuPositionRecord.setText("位置记录");
			menuPositionRecord.setUrl("../deviceData/locateLog.iframe");
			menuPositionRecord.setLeaf(true);
			menus.add(menuPositionRecord);
			count++;
		}

		if (AuthorizeCache.hasAuthorized("center.deviceData.speedLog", request)) {
			AdminMenu menuSpeedRecord = new AdminMenu();
			menuSpeedRecord.setId(ObjectId.next());
			menuSpeedRecord.setPid(menuDrivingRecord.getId());
			menuSpeedRecord.setText("速度记录");
			menuSpeedRecord.setUrl("../deviceData/speedLog.iframe");
			menuSpeedRecord.setLeaf(true);
			menus.add(menuSpeedRecord);
			count++;
		}

		if (count > 0)
			menus.add(menuDrivingRecord);
		count = 0;

		AdminMenu menuTerminalReport = new AdminMenu();
		menuTerminalReport.setId(ObjectId.next());
		menuTerminalReport.setText("事件报告");
		menuTerminalReport.setLeaf(false);

		if (AuthorizeCache.hasAuthorized("center.deviceData.deviceEventReport", request)) {
			AdminMenu menuEventReport = new AdminMenu();
			menuEventReport.setId(ObjectId.next());
			menuEventReport.setPid(menuTerminalReport.getId());
			menuEventReport.setText("事件报告");
			menuEventReport.setUrl("../deviceData/deviceEventReport.iframe");
			menuEventReport.setLeaf(true);
			menus.add(menuEventReport);
			count++;
		}
		if (AuthorizeCache.hasAuthorized("center.deviceData.deviceUpgradeResultReport", request)) {
			AdminMenu menuUpgrade = new AdminMenu();
			menuUpgrade.setId(ObjectId.next());
			menuUpgrade.setPid(menuTerminalReport.getId());
			menuUpgrade.setText("升级结果报告");
			menuUpgrade.setUrl("../deviceData/deviceUpgradeResultReport.iframe");
			menuUpgrade.setLeaf(true);
			menus.add(menuUpgrade);
			count++;
		}

		if (count > 0)
			menus.add(menuTerminalReport);

		return menus;
	}

	@ServiceMethod(id = "center.deviceData.multimedia", pid = "center.deviceData", prefix = "打开", name = "多媒体数据", suffix = "页面")
	@RequestMapping("/deviceData/multimedia.iframe")
	public String multimedia() {
		return "/center/deviceData/multimedia.iframe";
	}

	@RequestMapping(value = "/deviceData/multimedia/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryMultimedia(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryMultimedia(deviceNumber, start, end, pageIndex, pageSize);
	}

	@RequestMapping(value = "/deviceData/multimedia/get", method = RequestMethod.GET)
	public void getMultimedia(@RequestParam String id, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpg");
		byte[] content = deviceDataService.readMultimedia(id);
		response.getOutputStream().write(content);
	}

	@ServiceMethod(id = "center.deviceData.multimediaEvent", pid = "center.deviceData", prefix = "打开", name = "多媒体事件", suffix = "页面")
	@RequestMapping("/deviceData/multimediaEvent.iframe")
	public String multimediaEvent() {
		return "/center/deviceData/multimediaEvent.iframe";
	}

	@RequestMapping(value = "/deviceData/multimediaEventReport/query", method = RequestMethod.POST)
	@ResponseBody
	public Object multimediaEventReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return deviceDataService.queryMultimediaEventReport(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.multimediaRetrieval", pid = "center.deviceData", prefix = "打开", name = "多媒体数据检索", suffix = "页面")
	@RequestMapping("/deviceData/multimediaRetrieval.iframe")
	public String multimediaRetrieval() {
		return "/center/deviceData/multimediaRetrieval.iframe";
	}

	@RequestMapping(value = "/deviceData/multimediaRetrieval/query", method = RequestMethod.POST)
	@ResponseBody
	public Object multimediaRetrieval(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return deviceDataService.queryMultimediaDataRetrieval(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.deviceEventReport", pid = "center.deviceData", prefix = "打开", name = "事件报告", suffix = "页面")
	@RequestMapping("/deviceData/deviceEventReport.iframe")
	public String deviceEventReport() {
		return "/center/deviceData/deviceEventReport.iframe";
	}

	@RequestMapping(value = "/deviceData/deviceEventReport/query", method = RequestMethod.POST)
	@ResponseBody
	public Object deviceEventReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryDeviceEventReport(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.deviceUpgradeResultReport", pid = "center.deviceData", prefix = "打开", name = "升级结果报告", suffix = "页面")
	@RequestMapping("/deviceData/deviceUpgradeResultReport.iframe")
	public String deviceUpgradeResultReport() {
		return "/center/deviceData/deviceUpgradeResultReport.iframe";
	}

	@RequestMapping(value = "/deviceData/deviceUpgradeResultReport/query", method = RequestMethod.POST)
	@ResponseBody
	public Object deviceUpgradeResultReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return deviceDataService.deviceUpgradeResultReport(deviceNumber, start, end, pageIndex, pageSize);
	}

	@RequestMapping(value = "/deviceData/pickupMultmedia", method = RequestMethod.POST)
	public String pickupMultmedia(@RequestParam String deviceNumber, @RequestParam int mediaId, RedirectAttributes r, HttpServletRequest request) {
		Identify identify = (Identify) request.getAttribute("user");
		try {
			// CommandDto dto = new CommandDto();
			// dto.serialNumber = UUID.randomUUID().toString();
			// dto.deviceNumber = deviceNumber;
			// dto.unid = identify.getUnid();
			// dto.user = identify.getName();
			// dto.command = "8805";
			// dto.name = "上传多媒体数据";
			// {"mediaId":mediaId,"action":0}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mediaId", mediaId);
			map.put("action", 0);
			String params = JsonMapper.toJson(map);

			instructService.send(identify.getId(), UUID.randomUUID().toString(), deviceNumber, identify.getUnid(), identify.getName(), "8805",
					"上传多媒体数据", params, null);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@ServiceMethod(id = "center.deviceData.drivingRecorder", pid = "center.deviceData", prefix = "打开", name = "行驶记录仪信息", suffix = "页面")
	@RequestMapping("/deviceData/drivingRecorder.iframe")
	public String drivingRecorder() {
		return "/center/deviceData/drivingRecorder.iframe";
	}

	@RequestMapping(value = "/deviceData/drivingRecorder", method = RequestMethod.POST)
	@ResponseBody
	public Object querydrivingRecorder(@RequestParam String deviceNumber) throws Exception {
		return deviceDataService.querydrivingRecorder(deviceNumber);
	}

	@ServiceMethod(id = "center.deviceData.accidentDoubtLog", pid = "center.deviceData", prefix = "打开", name = "事故疑点日志", suffix = "页面")
	@RequestMapping("/deviceData/accidentDoubtLog.iframe")
	public String accidentDoubtLog() {
		return "/center/deviceData/accidentDoubtLog.iframe";
	}

	@RequestMapping(value = "/deviceData/accidentDoubtLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAccidentDoubtLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return deviceDataService.queryAccidentDoubtLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.powerLog", pid = "center.deviceData", prefix = "打开", name = "外部供电日志", suffix = "页面")
	@RequestMapping("/deviceData/powerLog.iframe")
	public String powerLog() {
		return "/center/deviceData/powerLog.iframe";
	}

	@RequestMapping(value = "/deviceData/powerLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPowerLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryPowerLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.timeoutLog", pid = "center.deviceData", prefix = "打开", name = "超时驾驶日志", suffix = "页面")
	@RequestMapping("/deviceData/timeoutLog.iframe")
	public String timeoutLog() {
		return "/center/deviceData/timeoutLog.iframe";
	}

	@RequestMapping(value = "/deviceData/timeoutLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryTimeoutLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryTimeoutLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.parameterLog", pid = "center.deviceData", prefix = "打开", name = "参数修改日志", suffix = "页面")
	@RequestMapping("/deviceData/parameterLog.iframe")
	public String parameterLog() {
		return "/center/deviceData/parameterLog.iframe";
	}

	@RequestMapping(value = "/deviceData/parameterLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryParameterLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryParameterLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.loginLog", pid = "center.deviceData", prefix = "打开", name = "驾驶员登签日志", suffix = "页面")
	@RequestMapping("/deviceData/loginLog.iframe")
	public String loginLog() {
		return "/center/deviceData/loginLog.iframe";
	}

	@RequestMapping(value = "/deviceData/loginLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryLoginLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryLoginLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.speedStatusLog", pid = "center.deviceData", prefix = "打开", name = "速度状态", suffix = "页面")
	@RequestMapping("/deviceData/speedStatusLog.iframe")
	public String speedStatusLog() {
		return "/center/deviceData/speedStatusLog.iframe";
	}

	@RequestMapping(value = "/deviceData/speedStatusLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object querySpeedStatusLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
			@RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
		return deviceDataService.querySpeedStatusLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.locateLog", pid = "center.deviceData", prefix = "打开", name = "位置记录", suffix = "页面")
	@RequestMapping("/deviceData/locateLog.iframe")
	public String locateLog() {
		return "/center/deviceData/locateLog.iframe";
	}

	@RequestMapping(value = "/deviceData/locateLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryLocateLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.queryLocateLog(deviceNumber, start, end, pageIndex, pageSize);
	}

	@ServiceMethod(id = "center.deviceData.speedLog", pid = "center.deviceData", prefix = "打开", name = "速度记录", suffix = "页面")
	@RequestMapping("/deviceData/speedLog.iframe")
	public String speedLog() {
		return "/center/deviceData/speedLog.iframe";
	}

	@RequestMapping(value = "/deviceData/speedLog/query", method = RequestMethod.POST)
	@ResponseBody
	public Object querySpeedLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return deviceDataService.querySpeedLog(deviceNumber, start, end, pageIndex, pageSize);
	}
}
