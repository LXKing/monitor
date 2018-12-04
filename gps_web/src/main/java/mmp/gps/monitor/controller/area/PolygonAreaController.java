package mmp.gps.monitor.controller.area;

import mmp.gps.common.util.JsonMapper;
import mmp.gps.common.util.LatLng;
import mmp.gps.common.util.Page;
import mmp.gps.domain.area.PolygonArea;
import mmp.gps.domain.area.PolygonAreaInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.model.baseinfo.PolygonAreaModel;
import mmp.gps.monitor.service.area.PolygonAreaService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class PolygonAreaController {
    @Autowired
    private PolygonAreaService polygonAreaService;

    @RequiresPermissions("baseinfo.polygonArea")
    @Log(name = "打开多边形区域管理页面")
    @RequestMapping("/polygonArea/polygonArea.iframe")
    public String index() {
        return "/baseinfo/polygonArea/polygonArea.iframe";
    }

    @GetMapping(value = "/polygonArea/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<PolygonAreaInfo> result = polygonAreaService.query(identity.getCompanyId(), filter, page, limit);

        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;
    }


    @PostMapping(value = "/polygonArea/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        return polygonAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);

    }

    @GetMapping(value = "/polygonArea/create.form")
    public String create(Model model) {
        PolygonAreaModel polygonArea = new PolygonAreaModel();
        polygonArea.setDeviceCatch(true);
        model.addAttribute("polygonArea", polygonArea);
        return "/baseinfo/polygonArea/create.form";
    }

    @PostMapping(value = "/polygonArea/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("polygonArea") @Valid PolygonAreaModel polygonArea, BindingResult binding) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/polygonArea/create.form";

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        polygonArea.setCompanyId(identity.getCompanyId());
        String path = polygonArea.getPath();
        List<LatLng> points = JsonMapper.toObject(path, ArrayList.class, LatLng.class);
        polygonArea.setPoints(points);
        polygonAreaService.create(polygonArea);
        return ResponseEntityWrapper.OK();

    }

    @RequestMapping("/polygonArea/edit.form")
    @ResponseBody
    public Object edit(@RequestParam long id, Model model) throws Exception {
        PolygonArea polygon = polygonAreaService.fetch(id);
        return polygon;

    }

    @PostMapping(value = "/polygonArea/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("polygonArea") @Valid PolygonAreaModel polygonArea, BindingResult binding) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/polygonArea/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        String path = polygonArea.getPath();
        List<LatLng> points = JsonMapper.toObject(path, ArrayList.class, LatLng.class);
        polygonArea.setPoints(points);

        polygonArea.setEditTime(new Timestamp(System.currentTimeMillis()));
        polygonAreaService.update(identity.getUnid(), identity.getName(), polygonArea);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/polygonArea/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam long id) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        polygonAreaService.delete(identity.getUnid(), identity.getName(), id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/polygonArea/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        return checkId ? !polygonAreaService.exist(name, identity.getCompanyId(), id) : !polygonAreaService.exist(name, identity
                .getCompanyId());


    }

    @GetMapping("/polygonArea/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam long polygonAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return polygonAreaService.assignedVehicles(polygonAreaId, pageIndex, pageSize);
    }

    @PostMapping(value = "/polygonArea/addVehicles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addVehicles(@RequestParam long polygonAreaId, @RequestParam String vehicles, HttpServletRequest request, RedirectAttributes r) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
//        List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        List<String> list = Arrays.asList(vehicles.split(","));
        polygonAreaService.addVehicles(identity.getUnid(), identity.getName(), polygonAreaId, list);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/polygonArea/removeVehicle")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam long polygonAreaId, @RequestParam String number) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        polygonAreaService.removeVehicle(identity.getUnid(), identity.getName(), polygonAreaId, number);

        return ResponseEntityWrapper.OK();

    }
}
