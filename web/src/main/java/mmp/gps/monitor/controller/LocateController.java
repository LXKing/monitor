package mmp.gps.monitor.controller;

import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.locate.GodpDataBlock;
import mmp.gps.domain.locate.GroupVehicle;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.vehicle.VehicleInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.service.LocateService;
import mmp.gps.monitor.service.UserService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LocateController {
    @Autowired
    private LocateService locateService;
    @Autowired
    private UserService userService;


    @RequiresPermissions("center.locate")
    @Log(name = "打开实时监控页面")
    @GetMapping("/locate/locate.iframe")
    public String index() {
        return "/center/locate/locate.iframe";
    }


    @GetMapping("/locate/locate.panel")
    public String locate() {
        return "/center/new/locate/map.panel";
    }

    @GetMapping("/locate/maptools.panel")
    public String maptools() {
        return "/center/locate/maptools.panel";
    }

    @PostMapping(value = "/locate/groupVehicles")
    @ResponseBody
    public Object groupVehicles(@RequestParam boolean force, HttpServletRequest request) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<GroupVehicle> list = locateService.loadGroupVehicles(identity.getId(), true);
        GroupVehicle top = new GroupVehicle();
        top.setId(identity.getId());
        top.setType(2);
        top.setNa(identity.FULLNAME);
        list.add(top);

        return list;

    }

    /**
     * 处理godp发送过来的数据
     */
    @PostMapping(value = "/locate/realtime")
    @ResponseBody
    public Object devices(@RequestBody GodpDataBlock block) {
        System.out.println("GODP");
        System.out.println(block);
        locateService.handleGodpData(block);
        return new AppResponse();
    }

    @PostMapping(value = "/locate/latests")
    @ResponseBody
    public Object latests(@RequestParam List<String> list) throws Exception {
        return locateService.loadLatests(list);
    }


    @RequiresPermissions("center.locate.vehileinfo")
    @Log(name = "查询车辆资料")
    @GetMapping(value = "/locate/vehicleinfo.iframe")
    public String vehicleInfo(@RequestParam String vehicleId, Model model) throws Exception {
        VehicleInfo vehicle = locateService.vehicleInfo(vehicleId);
        model.addAttribute("vehicle", vehicle);

        return "/center/locate/vehicleinfo.iframe";
    }

    @PostMapping(value = "/locate/unreadMultimediaEvent")
    @ResponseBody
    public Object loadUnreadMultimediaEvent(HttpServletRequest request) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return locateService.loadUnreadMultimediaEvent(identity.getId());
    }

    @PostMapping(value = "/locate/readMultmediaEventReport")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> readMultmediaEventReport(@RequestParam String id, RedirectAttributes r) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        locateService.readMultmediaEventReport(id, identity.getId(), identity.getName());
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/locate/unreadDeviceEvent")
    @ResponseBody
    public Object loadUnreadDeviceEvent() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return locateService.loadUnreadDeviceEvent(identity.getId());
    }

    @PostMapping(value = "/locate/readDeviceEventReport")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> readDeviceEventReport(@RequestParam String id) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        locateService.readDeviceEventReport(id, identity.getId(), identity.getName());
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/locate/unreadDeviceUpgradeResultReport")
    @ResponseBody
    public Object loadUnreadDeviceUpgradeResultReport() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return locateService.loadUnreadDeviceUpgradeResultReport(identity.getId());
    }

    @PostMapping(value = "/locate/readDeviceUpgradeResultReport")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> readDeviceUpgradeResultReport(@RequestParam String id) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        locateService.readDeviceUpgradeResultReport(id, identity.getId(), identity.getName());
        Map<Object, Object> map = new HashMap<Object, Object>() {
            {
                put("error", "ok...");
            }
        };
        return new ResponseEntity<>(map, HttpStatus.OK);

        // return "redirect:/result";
    }
}
