package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.alarm.Alarm;
import com.rayton.gps.dao.alarm.ProcessAlarmAll;
import com.rayton.gps.dao.alarm.ProcessAlarmOnce;
import com.rayton.gps.dao.baseinfo.dictionary.DictionaryItemInfo;
import com.rayton.gps.dao.security.*;
import com.rayton.gps.service.AlarmService;
import com.rayton.gps.service.DictionaryService;
import com.rayton.gps.util.WebUtil;
import com.rayton.gps.util.enums.DictionaryKinds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    @RequestMapping(value = "/alarm/unhandled", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object untreated(HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        return alarmService.unhandled(identity.getId());
    }

    /**
     * 获取指定设备号所有未处理报警
     */
    @RequestMapping(value = "/alarm/untreated", method = {RequestMethod.POST, RequestMethod.GET})
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
    @RequestMapping(value = "/alarm/processed", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object processed(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
                            @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
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
    public String processOnce(@ModelAttribute("alarm") @Valid ProcessAlarmOnce alarm, BindingResult binding, Model
            model, HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/center/alarm/processalarmonce.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            alarmService.processOnce(alarm.getAlarmId(), alarm.getAlarmTimestamp(), alarm.getUserMethod(), alarm
                    .getUserRemark(), identity.getId(), identity.getName());
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
    public String processAll(@ModelAttribute("alarm") @Valid ProcessAlarmAll alarm, BindingResult binding, Model
            model, HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/center/alarm/processalarmall.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            alarmService.processAll(alarm.getDeviceNumbers().split(","), alarm.getUserMethod(), alarm.getUserRemark()
                    , identity.getId(), identity.getName());
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
