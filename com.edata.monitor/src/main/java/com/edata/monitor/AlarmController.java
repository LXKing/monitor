package com.edata.monitor;

import java.util.Date;
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

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.domain.alarm.Alarm;
import com.edata.monitor.domain.alarm.ProcessAlarmAll;
import com.edata.monitor.domain.alarm.ProcessAlarmOnce;
import com.edata.monitor.domain.baseinfo.DictionaryItemInfo;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.service.AlarmService;
import com.edata.monitor.service.DictionaryService;
import com.edata.monitor.util.WebUtil;
import com.edata.monitor.util.kind.DictionaryKinds;

@Controller
public class AlarmController {
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private DictionaryService dictionaryService;

	@ServiceMethod(id = "center.alarm", pid = "center", prefix = "打开", name = "报警查询", suffix = "页面")
	@RequestMapping("/alarm/alarm.iframe")
	public String index() {
		return "/center/alarm/alarm.iframe";
	}
	
	@RequestMapping("/alarm/maptools.panel")
	public String maptools() {
		return "/center/alarm/maptools.panel";
	}

	/**
	 * 获取当前用户所有未处理报警
	 */
	@RequestMapping(value = "/alarm/unhandled", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object untreated(HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return alarmService.unhandled(identify.getId());
	}

	/**
	 * 获取指定设备号所有未处理报警
	 */
	@RequestMapping(value = "/alarm/untreated", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object untreated(@RequestParam String deviceNumber) throws Exception {
		Page<Alarm> alarms = new Page<Alarm>();
		alarms.rows = alarmService.untreated(deviceNumber);
		alarms.total = alarms.rows == null ? 0 : alarms.rows.size();

		return alarms;
	}

	/**
	 * 获取指定设备号所有已处理报警
	 */
	@RequestMapping(value = "/alarm/processed", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object processed(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex,
			@RequestParam int pageSize) throws Exception {
		return alarmService.processed(deviceNumber, start, end, pageIndex, pageSize);
	}

	/**
	 * 处理一条报警数据
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/alarm/processonce.form", method = RequestMethod.GET)
	public String processOnce(@RequestParam String alarmId, Model model) throws Exception {

		ProcessAlarmOnce alarm = null;
		try {
			alarm = alarmService.getProcessAlarm(alarmId);
		} catch (Exception ex) {
			model.addAttribute("error", ex.getMessage());
			return "/error.form";
		}
		model.addAttribute("alarm", alarm);

		List<DictionaryItemInfo> types = dictionaryService.list(DictionaryKinds.AlarmProcessType.getIndex());
		model.addAttribute("types", types);

		return "/center/alarm/processalarmonce.form";
	}

	/**
	 * 处理一条报警数据
	 */
	@RequestMapping(value = "/alarm/processonce.form", method = RequestMethod.POST)
	public String processOnce(@ModelAttribute("alarm") @Valid ProcessAlarmOnce alarm, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) throws Exception {
		if (binding.hasErrors())
			return "/center/alarm/processalarmonce.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			alarmService.processOnce(alarm.getAlarmId(), alarm.getAlarmTimestamp(), alarm.getUserMethod(), alarm.getUserRemark(), identify.getId(),
					identify.getName());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	/**
	 * 处理指定设备号所有未处理报警数据
	 */
	@RequestMapping(value = "/alarm/processall.form", method = RequestMethod.GET)
	public String processAll(Model model) throws Exception {
		ProcessAlarmAll alarm = new ProcessAlarmAll();
		model.addAttribute("alarm", alarm);

		List<DictionaryItemInfo> types = dictionaryService.list(DictionaryKinds.AlarmProcessType.getIndex());
		model.addAttribute("types", types);

		return "/center/alarm/processalarmall.form";
	}

	/**
	 * 处理指定设备号所有未处理报警数据
	 */
	@RequestMapping(value = "/alarm/processall.form", method = RequestMethod.POST)
	public String processAll(@ModelAttribute("alarm") @Valid ProcessAlarmAll alarm, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) throws Exception {
		if (binding.hasErrors())
			return "/center/alarm/processalarmall.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			alarmService.processAll(alarm.getDeviceNumbers().split(","), alarm.getUserMethod(), alarm.getUserRemark(), identify.getId(),
					identify.getName());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
