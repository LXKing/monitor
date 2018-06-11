package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.circleArea.CircleAreaInfo;
import com.rayton.gps.dao.baseinfo.mapLayer.MapLayer;
import com.rayton.gps.dao.baseinfo.mapLayer.MapLayerInfo;
import com.rayton.gps.dao.baseinfo.poi.PoiInfo;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonAreaInfo;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleAreaInfo;
import com.rayton.gps.dao.baseinfo.routeArea.RouteAreaInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.MapLayerService;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MapLayerController {
    @Autowired
    private MapLayerService mapLayerService;


    @RequiresPermissions("baseinfo.mapLayer")
    @Log(name = "打开地图图层管理页面")
    @GetMapping("/mapLayer/mapLayer.iframe")
    public String iframe() {
        return "/baseinfo/mapLayer/mapLayer.iframe";
    }

    @GetMapping("/mapLayer/query")
    @ResponseBody
    public Object query(@RequestParam String filter) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<MapLayerInfo> page = new Page<MapLayerInfo>();
        page.rows = mapLayerService.query(identity.getId(), filter);
        page.total = page.rows.size();
        return page;
    }

    @GetMapping("/mapLayer/mapLayerInfo")
    @ResponseBody
    public Object getMapLayInfo(@RequestParam String mapLayerId) throws Exception {
        return mapLayerService.getMapLayInfo(mapLayerId);
    }

    @GetMapping(value = "/mapLayer/create.form")
    public String create(Model model) {
        MapLayer mapLayer = new MapLayer();
        model.addAttribute("mapLayer", mapLayer);
        return "/baseinfo/mapLayer/create.form";
    }

    @PostMapping(value = "/mapLayer/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("mapLayer") @Valid MapLayer mapLayer, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/mapLayer/create.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        mapLayer.setCompanyId(identity.getCompanyId());
        mapLayer.setUserId(identity.getId());
        mapLayerService.create(mapLayer);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     mapLayer.setCompanyId(identity.getCompanyId());
        //     mapLayer.setUserId(identity.getId());
        //     mapLayerService.create(mapLayer);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/mapLayer/edit.form")
    public String edit(@RequestParam String id, Model model) throws Exception {
        MapLayer mapLayer = mapLayerService.fetch(id);
        model.addAttribute("mapLayer", mapLayer);
        return "/baseinfo/mapLayer/edit.form";
    }

    @PostMapping(value = "/mapLayer/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("mapLayer") @Valid MapLayer mapLayer, BindingResult binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/mapLayer/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        mapLayer.setCompanyId(identity.getCompanyId());
        mapLayer.setUserId(identity.getId());
        mapLayerService.update(mapLayer);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     mapLayer.setCompanyId(identity.getCompanyId());
        //     mapLayer.setUserId(identity.getId());
        //     mapLayerService.update(mapLayer);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id, RedirectAttributes r) {
        mapLayerService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/mapLayer/circleAreas")
    @ResponseBody
    public Object getCircleAreas(@RequestParam String mapLayerId) throws Exception {
        Page<CircleAreaInfo> page = new Page<>();
        page.rows = mapLayerService.getCircleAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @PostMapping(value = "/mapLayer/addCircleAreas")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addCircleAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds) {
        mapLayerService.addCircleAreas(mapLayerId, areaIds);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.addCircleAreas(mapLayerId, areaIds);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/removeCircleArea")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeCircleArea(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes r) {
        mapLayerService.removeCircleArea(mapLayerId, areaId);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.removeCircleArea(mapLayerId, areaId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/mapLayer/rectangleAreas")
    @ResponseBody
    public Object getRectangleAreas(@RequestParam String mapLayerId) throws Exception {
        Page<RectangleAreaInfo> page = new Page<>();
        page.rows = mapLayerService.getRectangleAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @PostMapping(value = "/mapLayer/addRectangleAreas")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addRectangleAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds) {
        mapLayerService.addRectangleAreas(mapLayerId, areaIds);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.addRectangleAreas(mapLayerId, areaIds);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/removeRectangleArea")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeRectangleArea(@RequestParam String mapLayerId, @RequestParam long areaId) {

        mapLayerService.removeRectangleArea(mapLayerId, areaId);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.removeRectangleArea(mapLayerId, areaId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/mapLayer/polygonAreas")
    @ResponseBody
    public Object getPolygonAreas(@RequestParam String mapLayerId) throws Exception {
        Page<PolygonAreaInfo> page = new Page<>();
        page.rows = mapLayerService.getPolygonAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @PostMapping(value = "/mapLayer/addPolygonAreas")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addPolygonAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds) {
        mapLayerService.addPolygonAreas(mapLayerId, areaIds);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.addPolygonAreas(mapLayerId, areaIds);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/removePolygonArea")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removePolygonArea(@RequestParam String mapLayerId, @RequestParam long areaId) {
        mapLayerService.removePolygonArea(mapLayerId, areaId);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.removePolygonArea(mapLayerId, areaId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/mapLayer/routeAreas")
    @ResponseBody
    public Object getRouteAreas(@RequestParam String mapLayerId) throws Exception {
        Page<RouteAreaInfo> page = new Page<>();
        page.rows = mapLayerService.getRouteAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @PostMapping(value = "/mapLayer/addRouteAreas")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addRouteAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds) {

        mapLayerService.addRouteAreas(mapLayerId, areaIds);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.addRouteAreas(mapLayerId, areaIds);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/removeRouteArea")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeRouteArea(@RequestParam String mapLayerId, @RequestParam long areaId) {
        mapLayerService.removeRouteArea(mapLayerId, areaId);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.removeRouteArea(mapLayerId, areaId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/mapLayer/pois")
    @ResponseBody
    public Object getPois(@RequestParam String mapLayerId) throws Exception {
        Page<PoiInfo> page = new Page<>();
        page.rows = mapLayerService.getPois(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @PostMapping(value = "/mapLayer/addPois")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addPois(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds) {

        mapLayerService.addPois(mapLayerId, areaIds);
        return ResponseEntityWrapper.OK();
        // try {
        //     mapLayerService.addPois(mapLayerId, areaIds);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/mapLayer/removePoi")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removePoi(@RequestParam String mapLayerId, @RequestParam long areaId) {

        mapLayerService.removePoi(mapLayerId, areaId);
        return ResponseEntityWrapper.OK();
        //
        // try {
        //     mapLayerService.removePoi(mapLayerId, areaId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/mapLayer/setting.form")
    public String setting() throws Exception {
        return "/baseinfo/mapLayer/setting.form";
    }

    @PostMapping(value = "/mapLayer/visible")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> visible(@RequestParam String mapLayerId, @RequestParam boolean visible) {
        mapLayerService.setVisible(mapLayerId, visible);
        return ResponseEntityWrapper.OK();

        // try {
        //     mapLayerService.setVisible(mapLayerId, visible);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

}
