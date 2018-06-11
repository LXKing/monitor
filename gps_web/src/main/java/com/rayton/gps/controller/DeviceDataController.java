package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import com.rayton.gps.common.ObjectId;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.model.baseinfo.AdminMenu;
import com.rayton.gps.service.DeviceDataService;
import com.rayton.gps.service.InstructService;
import com.rayton.gps.service.SecurityService;
import com.rayton.gps.util.JsonMapper;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class DeviceDataController {
    @Autowired
    private DeviceDataService deviceDataService;
    @Autowired
    private InstructService instructService;

    @Autowired
    private SecurityService securityService;

    @Log(name = "打开数据查询页面")
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

        if (securityService.hasAuthorized("center.deviceData.multimedia")) {
            AdminMenu menuPhoto = new AdminMenu();
            menuPhoto.setId(ObjectId.next());
            menuPhoto.setPid(menuMultimedia.getId());
            menuPhoto.setText("多媒体数据");
            menuPhoto.setUrl("../deviceData/multimedia.iframe");
            menuPhoto.setLeaf(true);
            menus.add(menuPhoto);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.multimediaEvent")) {
            AdminMenu menuMultimediaEvent = new AdminMenu();
            menuMultimediaEvent.setId(ObjectId.next());
            menuMultimediaEvent.setPid(menuMultimedia.getId());
            menuMultimediaEvent.setText("多媒体事件");
            menuMultimediaEvent.setUrl("../deviceData/multimediaEvent.iframe");
            menuMultimediaEvent.setLeaf(true);
            menus.add(menuMultimediaEvent);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.multimediaRetrieval")) {
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

        if (securityService.hasAuthorized("center.deviceData.drivingRecorder")) {
            AdminMenu menuTravelingDataRecorder = new AdminMenu();
            menuTravelingDataRecorder.setId(ObjectId.next());
            menuTravelingDataRecorder.setPid(menuDrivingRecord.getId());
            menuTravelingDataRecorder.setText("行驶记录仪信息");
            menuTravelingDataRecorder.setUrl("../deviceData/drivingRecorder.iframe");
            menuTravelingDataRecorder.setLeaf(true);
            menus.add(menuTravelingDataRecorder);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.accidentDoubtLog")) {
            AdminMenu menuAccidentDoubtLog = new AdminMenu();
            menuAccidentDoubtLog.setId(ObjectId.next());
            menuAccidentDoubtLog.setPid(menuDrivingRecord.getId());
            menuAccidentDoubtLog.setText("事故疑点日志");
            menuAccidentDoubtLog.setUrl("../deviceData/accidentDoubtLog.iframe");
            menuAccidentDoubtLog.setLeaf(true);
            menus.add(menuAccidentDoubtLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.powerLog")) {
            AdminMenu menuPowerSupplyLog = new AdminMenu();
            menuPowerSupplyLog.setId(ObjectId.next());
            menuPowerSupplyLog.setPid(menuDrivingRecord.getId());
            menuPowerSupplyLog.setText("外部供电日志");
            menuPowerSupplyLog.setUrl("../deviceData/powerLog.iframe");
            menuPowerSupplyLog.setLeaf(true);
            menus.add(menuPowerSupplyLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.timeoutLog")) {
            AdminMenu menuTimeoutDrivingLog = new AdminMenu();
            menuTimeoutDrivingLog.setId(ObjectId.next());
            menuTimeoutDrivingLog.setPid(menuDrivingRecord.getId());
            menuTimeoutDrivingLog.setText("超时驾驶日志");
            menuTimeoutDrivingLog.setUrl("../deviceData/timeoutLog.iframe");
            menuTimeoutDrivingLog.setLeaf(true);
            menus.add(menuTimeoutDrivingLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.parameterLog")) {
            AdminMenu menuParameterChangeLog = new AdminMenu();
            menuParameterChangeLog.setId(ObjectId.next());
            menuParameterChangeLog.setPid(menuDrivingRecord.getId());
            menuParameterChangeLog.setText("参数修改日志");
            menuParameterChangeLog.setUrl("../deviceData/parameterLog.iframe");
            menuParameterChangeLog.setLeaf(true);
            menus.add(menuParameterChangeLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.loginLog")) {
            AdminMenu menuDriverLoginLog = new AdminMenu();
            menuDriverLoginLog.setId(ObjectId.next());
            menuDriverLoginLog.setPid(menuDrivingRecord.getId());
            menuDriverLoginLog.setText("驾驶员登签日志");
            menuDriverLoginLog.setUrl("../deviceData/loginLog.iframe");
            menuDriverLoginLog.setLeaf(true);
            menus.add(menuDriverLoginLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.speedStatusLog")) {
            AdminMenu menuSpeedStatusLog = new AdminMenu();
            menuSpeedStatusLog.setId(ObjectId.next());
            menuSpeedStatusLog.setPid(menuDrivingRecord.getId());
            menuSpeedStatusLog.setText("速度状态日志");
            menuSpeedStatusLog.setUrl("../deviceData/speedStatusLog.iframe");
            menuSpeedStatusLog.setLeaf(true);
            menus.add(menuSpeedStatusLog);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.locateLog")) {
            AdminMenu menuPositionRecord = new AdminMenu();
            menuPositionRecord.setId(ObjectId.next());
            menuPositionRecord.setPid(menuDrivingRecord.getId());
            menuPositionRecord.setText("位置记录");
            menuPositionRecord.setUrl("../deviceData/locateLog.iframe");
            menuPositionRecord.setLeaf(true);
            menus.add(menuPositionRecord);
            count++;
        }

        if (securityService.hasAuthorized("center.deviceData.speedLog")) {
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

        if (securityService.hasAuthorized("center.deviceData.deviceEventReport")) {
            AdminMenu menuEventReport = new AdminMenu();
            menuEventReport.setId(ObjectId.next());
            menuEventReport.setPid(menuTerminalReport.getId());
            menuEventReport.setText("事件报告");
            menuEventReport.setUrl("../deviceData/deviceEventReport.iframe");
            menuEventReport.setLeaf(true);
            menus.add(menuEventReport);
            count++;
        }
        if (securityService.hasAuthorized("center.deviceData.deviceUpgradeResultReport")) {
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


    @RequiresPermissions("center.deviceData.multimedia")
    @Log(name = "打开多媒体数据页面")
    @RequestMapping("/deviceData/multimedia.iframe")
    public String multimedia() {
        return "/center/deviceData/multimedia.iframe";
    }

    @RequestMapping(value = "/deviceData/multimedia/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryMultimedia(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryMultimedia(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequestMapping(value = "/deviceData/multimedia/get", method = RequestMethod.GET)
    public void getMultimedia(@RequestParam String id, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpg");
        byte[] content = deviceDataService.readMultimedia(id);
        response.getOutputStream().write(content);
    }


    @RequiresPermissions("center.deviceData.multimediaEvent")
    @Log(name = "打开多媒体事件页面")
    @RequestMapping("/deviceData/multimediaEvent.iframe")
    public String multimediaEvent() {
        return "/center/deviceData/multimediaEvent.iframe";
    }

    @RequestMapping(value = "/deviceData/multimediaEventReport/query", method = RequestMethod.POST)
    @ResponseBody
    public Object multimediaEventReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryMultimediaEventReport(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.multimediaRetrieval")
    @Log(name = "打开多媒体数据检索页面")
    @RequestMapping("/deviceData/multimediaRetrieval.iframe")
    public String multimediaRetrieval() {
        return "/center/deviceData/multimediaRetrieval.iframe";
    }

    @RequestMapping(value = "/deviceData/multimediaRetrieval/query", method = RequestMethod.POST)
    @ResponseBody
    public Object multimediaRetrieval(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryMultimediaDataRetrieval(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.deviceEventReport")
    @Log(name = "打开事件报告页面")
    @RequestMapping("/deviceData/deviceEventReport.iframe")
    public String deviceEventReport() {
        return "/center/deviceData/deviceEventReport.iframe";
    }

    @RequestMapping(value = "/deviceData/deviceEventReport/query", method = RequestMethod.POST)
    @ResponseBody
    public Object deviceEventReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryDeviceEventReport(deviceNumber, start, end, pageIndex, pageSize);
    }


    @RequiresPermissions("center.deviceData.deviceUpgradeResultReport")
    @Log(name = "打开升级结果报告页面")
    @RequestMapping("/deviceData/deviceUpgradeResultReport.iframe")
    public String deviceUpgradeResultReport() {
        return "/center/deviceData/deviceUpgradeResultReport.iframe";
    }

    @RequestMapping(value = "/deviceData/deviceUpgradeResultReport/query", method = RequestMethod.POST)
    @ResponseBody
    public Object deviceUpgradeResultReport(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.deviceUpgradeResultReport(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequestMapping(value = "/deviceData/pickupMultmedia", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> pickupMultmedia(@RequestParam String deviceNumber, @RequestParam int mediaId, RedirectAttributes r, HttpServletRequest request) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        // CommandDto dto = new CommandDto();
        // dto.serialNumber = UUID.randomUUID().toString();
        // dto.deviceNumber = deviceNumber;
        // dto.unid = identity.getUnid();
        // dto.user = identity.getName();
        // dto.command = "8805";
        // dto.name = "上传多媒体数据";
        // {"mediaId":mediaId,"action":0}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mediaId", mediaId);
        map.put("action", 0);
        String params = JsonMapper.toJson(map);

        instructService.send(identity.getId(), UUID.randomUUID()
                .toString(), deviceNumber, identity.getUnid(), identity.getName(), "8805", "上传多媒体数据", params, null);
        return ResponseEntityWrapper.OK();

        // return "redirect:/result";
    }

    @RequiresPermissions("center.deviceData.drivingRecorder")
    @Log(name = "打开行驶记录仪信息页面")
    @RequestMapping("/deviceData/drivingRecorder.iframe")
    public String drivingRecorder() {
        return "/center/deviceData/drivingRecorder.iframe";
    }

    @RequestMapping(value = "/deviceData/drivingRecorder", method = RequestMethod.POST)
    @ResponseBody
    public Object querydrivingRecorder(@RequestParam String deviceNumber) throws Exception {
        return deviceDataService.querydrivingRecorder(deviceNumber);
    }

    @RequiresPermissions("center.deviceData.accidentDoubtLog")
    @Log(name = "打开事故疑点日志页面")
    @RequestMapping("/deviceData/accidentDoubtLog.iframe")
    public String accidentDoubtLog() {
        return "/center/deviceData/accidentDoubtLog.iframe";
    }

    @RequestMapping(value = "/deviceData/accidentDoubtLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryAccidentDoubtLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryAccidentDoubtLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.powerLog")
    @Log(name = "打开外部供电日志页面")
    @RequestMapping("/deviceData/powerLog.iframe")
    public String powerLog() {
        return "/center/deviceData/powerLog.iframe";
    }

    @RequestMapping(value = "/deviceData/powerLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryPowerLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryPowerLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.timeoutLog")
    @Log(name = "打开超时驾驶日志页面")
    @RequestMapping("/deviceData/timeoutLog.iframe")
    public String timeoutLog() {
        return "/center/deviceData/timeoutLog.iframe";
    }

    @RequestMapping(value = "/deviceData/timeoutLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryTimeoutLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryTimeoutLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.parameterLog")
    @Log(name = "打开参数修改日志页面")
    @RequestMapping("/deviceData/parameterLog.iframe")
    public String parameterLog() {
        return "/center/deviceData/parameterLog.iframe";
    }

    @RequestMapping(value = "/deviceData/parameterLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryParameterLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryParameterLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.loginLog")
    @Log(name = "打开驾驶员登签日志页面")
    @RequestMapping("/deviceData/loginLog.iframe")
    public String loginLog() {
        return "/center/deviceData/loginLog.iframe";
    }

    @RequestMapping(value = "/deviceData/loginLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryLoginLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryLoginLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.speedStatusLog")
    @Log(name = "打开速度状态页面")
    @RequestMapping("/deviceData/speedStatusLog.iframe")
    public String speedStatusLog() {
        return "/center/deviceData/speedStatusLog.iframe";
    }

    @RequestMapping(value = "/deviceData/speedStatusLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object querySpeedStatusLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.querySpeedStatusLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.locateLog")
    @Log(name = "打开位置记录页面")
    @RequestMapping("/deviceData/locateLog.iframe")
    public String locateLog() {
        return "/center/deviceData/locateLog.iframe";
    }

    @RequestMapping(value = "/deviceData/locateLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object queryLocateLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.queryLocateLog(deviceNumber, start, end, pageIndex, pageSize);
    }

    @RequiresPermissions("center.deviceData.speedLog")
    @Log(name = "打开速度记录页面")
    @RequestMapping("/deviceData/speedLog.iframe")
    public String speedLog() {
        return "/center/deviceData/speedLog.iframe";
    }

    @RequestMapping(value = "/deviceData/speedLog/query", method = RequestMethod.POST)
    @ResponseBody
    public Object querySpeedLog(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return deviceDataService.querySpeedLog(deviceNumber, start, end, pageIndex, pageSize);
    }
}
