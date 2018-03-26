package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.circleArea.CircleAreaInfo;
import com.rayton.gps.dao.baseinfo.mapLayer.MapLayer;
import com.rayton.gps.dao.baseinfo.mapLayer.MapLayerInfo;
import com.rayton.gps.dao.baseinfo.poi.PoiInfo;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonAreaInfo;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleAreaInfo;
import com.rayton.gps.dao.baseinfo.routeArea.RouteAreaInfo;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.service.MapLayerService;
import com.rayton.gps.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MapLayerController {
    @Autowired
    private MapLayerService mapLayerService;

    @ServiceMethod(id = "baseinfo.mapLayer", pid = "baseinfo", prefix = "打开", name = "地图图层管理", suffix = "页面")
    @RequestMapping("/mapLayer/mapLayer.iframe")
    public String iframe() {
        return "/baseinfo/mapLayer/mapLayer.iframe";
    }

    @RequestMapping("/mapLayer/query")
    @ResponseBody
    public Object query(@RequestParam String filter, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<MapLayerInfo> page = new Page<MapLayerInfo>();
        page.rows = mapLayerService.query(identity.getId(), filter);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping("/mapLayer/mapLayerInfo")
    @ResponseBody
    public Object getMapLayInfo(@RequestParam String mapLayerId) throws Exception {
        return mapLayerService.getMapLayInfo(mapLayerId);
    }

    @RequestMapping(value = "/mapLayer/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        MapLayer mapLayer = new MapLayer();
        model.addAttribute("mapLayer", mapLayer);
        return "/baseinfo/mapLayer/create.form";
    }

    @RequestMapping(value = "/mapLayer/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("mapLayer") @Valid MapLayer mapLayer, BindingResult binding, Model model,
                         HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/mapLayer/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            mapLayer.setCompanyId(identity.getCompanyId());
            mapLayer.setUserId(identity.getId());
            mapLayerService.create(mapLayer);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/edit.form", method = RequestMethod.GET)
    public String edit(@RequestParam String id, Model model) throws Exception {
        MapLayer mapLayer = mapLayerService.fetch(id);
        model.addAttribute("mapLayer", mapLayer);
        return "/baseinfo/mapLayer/edit.form";
    }

    @RequestMapping(value = "/mapLayer/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("mapLayer") @Valid MapLayer mapLayer, BindingResult binding, Model model,
                       HttpServletRequest request, RedirectAttributes r) throws Exception {
        if (binding.hasErrors())
            return "/baseinfo/mapLayer/edit.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            mapLayer.setCompanyId(identity.getCompanyId());
            mapLayer.setUserId(identity.getId());
            mapLayerService.update(mapLayer);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            mapLayerService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/mapLayer/circleAreas")
    @ResponseBody
    public Object getCircleAreas(@RequestParam String mapLayerId) throws Exception {
        Page<CircleAreaInfo> page = new Page<CircleAreaInfo>();
        page.rows = mapLayerService.getCircleAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping(value = "/mapLayer/addCircleAreas", method = RequestMethod.POST)
    public String addCircleAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds,
                                 RedirectAttributes r) {
        try {
            mapLayerService.addCircleAreas(mapLayerId, areaIds);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/removeCircleArea", method = RequestMethod.POST)
    public String removeCircleArea(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes r) {
        try {
            mapLayerService.removeCircleArea(mapLayerId, areaId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/mapLayer/rectangleAreas")
    @ResponseBody
    public Object getRectangleAreas(@RequestParam String mapLayerId) throws Exception {
        Page<RectangleAreaInfo> page = new Page<RectangleAreaInfo>();
        page.rows = mapLayerService.getRectangleAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping(value = "/mapLayer/addRectangleAreas", method = RequestMethod.POST)
    public String addRectangleAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds,
                                    RedirectAttributes r) {
        try {
            mapLayerService.addRectangleAreas(mapLayerId, areaIds);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/removeRectangleArea", method = RequestMethod.POST)
    public String removeRectangleArea(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes
            r) {
        try {
            mapLayerService.removeRectangleArea(mapLayerId, areaId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/mapLayer/polygonAreas")
    @ResponseBody
    public Object getPolygonAreas(@RequestParam String mapLayerId) throws Exception {
        Page<PolygonAreaInfo> page = new Page<PolygonAreaInfo>();
        page.rows = mapLayerService.getPolygonAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping(value = "/mapLayer/addPolygonAreas", method = RequestMethod.POST)
    public String addPolygonAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds,
                                  RedirectAttributes r) {
        try {
            mapLayerService.addPolygonAreas(mapLayerId, areaIds);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/removePolygonArea", method = RequestMethod.POST)
    public String removePolygonArea(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes r) {
        try {
            mapLayerService.removePolygonArea(mapLayerId, areaId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/mapLayer/routeAreas")
    @ResponseBody
    public Object getRouteAreas(@RequestParam String mapLayerId) throws Exception {
        Page<RouteAreaInfo> page = new Page<RouteAreaInfo>();
        page.rows = mapLayerService.getRouteAreas(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping(value = "/mapLayer/addRouteAreas", method = RequestMethod.POST)
    public String addRouteAreas(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds,
                                RedirectAttributes r) {
        try {
            mapLayerService.addRouteAreas(mapLayerId, areaIds);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/removeRouteArea", method = RequestMethod.POST)
    public String removeRouteArea(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes r) {
        try {
            mapLayerService.removeRouteArea(mapLayerId, areaId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/mapLayer/pois")
    @ResponseBody
    public Object getPois(@RequestParam String mapLayerId) throws Exception {
        Page<PoiInfo> page = new Page<PoiInfo>();
        page.rows = mapLayerService.getPois(mapLayerId);
        page.total = page.rows.size();
        return page;
    }

    @RequestMapping(value = "/mapLayer/addPois", method = RequestMethod.POST)
    public String addPois(@RequestParam String mapLayerId, @RequestParam("list[]") List<Long> areaIds,
                          RedirectAttributes r) {
        try {
            mapLayerService.addPois(mapLayerId, areaIds);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/removePoi", method = RequestMethod.POST)
    public String removePoi(@RequestParam String mapLayerId, @RequestParam long areaId, RedirectAttributes r) {
        try {
            mapLayerService.removePoi(mapLayerId, areaId);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/mapLayer/setting.form", method = RequestMethod.GET)
    public String setting() throws Exception {
        return "/baseinfo/mapLayer/setting.form";
    }

    @RequestMapping(value = "/mapLayer/visible", method = RequestMethod.POST)
    public String visible(@RequestParam String mapLayerId, @RequestParam boolean visible, RedirectAttributes r) {
        try {
            mapLayerService.setVisible(mapLayerId, visible);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

}
