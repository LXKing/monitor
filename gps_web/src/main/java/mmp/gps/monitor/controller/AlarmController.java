package mmp.gps.monitor.controller;

import mmp.gps.common.util.Page;
import mmp.gps.common.enums.DictionaryKinds;
import mmp.gps.domain.alarm.Alarm;
import mmp.gps.domain.alarm.ProcessAlarmAll;
import mmp.gps.domain.alarm.ProcessAlarmOnce;
import mmp.gps.domain.dictionary.DictionaryItemInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.service.AlarmService;
import mmp.gps.monitor.service.DictionaryService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AlarmController {

    @Autowired
    private AlarmService alarmService;
    @Autowired
    private DictionaryService dictionaryService;


    @RequiresPermissions("center.alarm")
    @Log(name = "打开报警查询页面")
    @GetMapping("/alarm/alarm.iframe")
    public String index() {
        return "/center/alarm/alarm.iframe";
    }

    @GetMapping("/alarm/maptools.panel")
    public String maptools() {
        return "/center/alarm/maptools.panel";
    }

    /**
     * 获取当前用户所有未处理报警
     */
    @PostMapping(value = "/alarm/unhandled")
    @ResponseBody
    public Object untreated() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        return alarmService.unhandled(identity.getId());
    }

    @PostMapping(value = "/alarm/all")
    @ResponseBody
    public Object all(@RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize) throws Exception {
//
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<Alarm> alarms = new Page<Alarm>();
        alarms.rows = alarmService.all(identity.getId(), pageIndex, pageSize);
        alarms.total = alarmService.getCount(identity.getId());

        return alarms;
    }

    /**
     * 获取指定设备号所有未处理报警
     */
    @PostMapping(value = "/alarm/untreated")
    @ResponseBody
    public Object untreated(@RequestParam String deviceNumber, @RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize, @RequestParam Date start, @RequestParam Date end) throws Exception {
        Page<Alarm> alarms = new Page<Alarm>();
        alarms.rows = alarmService.untreated(deviceNumber, pageIndex, pageSize, start, end);
        alarms.total = alarmService.getDnUntreated(deviceNumber, start, end);

        return alarms;
    }

    /**
     * 获取指定设备号所有已处理报警
     */
    @PostMapping(value = "/alarm/processed")
    @ResponseBody
    public Object processed(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return alarmService.processed(deviceNumber, start, end, pageIndex, pageSize);

    }

    /**
     * 处理一条报警数据
     *
     * @throws Exception
     */
    @GetMapping(value = "/alarm/processonce.form")
    public String processOnceA(@RequestParam String alarmId, Model model) throws Exception {

        ProcessAlarmOnce alarm = alarmService.getProcessAlarm(alarmId);

        model.addAttribute("alarm", alarm);

        List<DictionaryItemInfo> types = dictionaryService.list(DictionaryKinds.AlarmProcessType.getIndex());
        model.addAttribute("types", types);

        return "/center/alarm/processalarmonce.form";
    }

    /**
     * 处理一条报警数据
     */
    @PostMapping(value = "/alarm/processonce.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> processOnce(@ModelAttribute("alarm") @Valid ProcessAlarmOnce alarm, BindingResult binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            // loadMarkers(request, model);
            // return "/baseinfo/vehicle/create.form";
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/center/alarm/processalarmonce.form";

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        alarm.setAlarmTimestamp(new Timestamp(System.currentTimeMillis()));
        alarmService.processOnce(alarm.getAlarmId(), alarm.getAlarmTimestamp(), alarm.getUserMethod(), alarm.getUserRemark(), identity
                .getId(), identity.getName());

        return ResponseEntityWrapper.OK();

    }

    /**
     * 处理指定设备号所有未处理报警数据
     */
    @GetMapping(value = "/alarm/processall.form")
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
    @PostMapping(value = "/alarm/processall.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> processAllPost(@ModelAttribute("alarm") @Valid ProcessAlarmAll alarm, BindingResult binding) throws Exception {
        if (binding.hasErrors())
            // return "/center/alarm/processalarmall.form";
            return ResponseEntityWrapper.Failed();
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        alarmService.processAll(alarm.getDeviceNumbers()
                .split(","), alarm.getUserMethod(), alarm.getUserRemark(), identity.getId(), identity.getName());
        return ResponseEntityWrapper.OK();

    }
}
