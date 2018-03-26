package com.rayton.gps.controller;

import com.edata.common.LatLng;
import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.baseinfo.polygonArea.PolygonArea;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.model.baseinfo.PolygonAreaModel;
import com.rayton.gps.service.PolygonAreaService;
import com.rayton.gps.util.JsonMapper;
import com.rayton.gps.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PolygonAreaController {
    @Autowired
    private PolygonAreaService polygonAreaService;

    @ServiceMethod(id = "baseinfo.polygonArea", pid = "baseinfo", prefix = "打开", name = "多边形区域", suffix = "管理页面")
    @RequestMapping("/polygonArea/polygonArea.iframe")
    public String index() {
        return "/baseinfo/polygonArea/polygonArea.iframe";
    }

    @RequestMapping(value = "/polygonArea/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return polygonAreaService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }


    @RequestMapping(value = "/polygonArea/search", method = RequestMethod.POST)
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam
            int pageSize, HttpServletRequest request) {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        try {
            return polygonAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/polygonArea/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        PolygonAreaModel polygonArea = new PolygonAreaModel();
        polygonArea.setDeviceCatch(true);
        model.addAttribute("polygonArea", polygonArea);
        return "/baseinfo/polygonArea/create.form";
    }

    @RequestMapping(value = "/polygonArea/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("polygonArea") @Valid PolygonAreaModel polygonArea, BindingResult binding,
                         Model model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/polygonArea/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            polygonArea.setCompanyId(identity.getCompanyId());
            String path = polygonArea.getPath();
            List<LatLng> points = JsonMapper.toObject(path, ArrayList.class, LatLng.class);
            polygonArea.setPoints(points);
            polygonAreaService.create(polygonArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/polygonArea/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        PolygonArea polygon = polygonAreaService.fetch(id);
        PolygonAreaModel polygonArea = JsonMapper.convertValue(polygon, PolygonAreaModel.class);
        model.addAttribute("polygonArea", polygonArea);
        return "/baseinfo/polygonArea/edit.form";
    }

    @RequestMapping(value = "/polygonArea/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("polygonArea") @Valid PolygonAreaModel polygonArea, BindingResult binding,
                       Model model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/polygonArea/edit.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            String path = polygonArea.getPath();
            List<LatLng> points = JsonMapper.toObject(path, ArrayList.class, LatLng.class);
            polygonArea.setPoints(points);
            polygonAreaService.update(identity.getUnid(), identity.getName(), polygonArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/polygonArea/delete", method = RequestMethod.POST)
    public String delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            polygonAreaService.delete(identity.getUnid(), identity.getName(), id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/polygonArea/exist", method = RequestMethod.POST)
    public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean
            checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if (checkId) {
            response.getWriter().print(!polygonAreaService.exist(name, identity.getCompanyId(), id));
        } else {
            response.getWriter().print(!polygonAreaService.exist(name, identity.getCompanyId()));
        }
    }

    @RequestMapping("/polygonArea/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam long polygonAreaId, @RequestParam int pageIndex, @RequestParam int pageSize)
            throws Exception {
        return polygonAreaService.assignedVehicles(polygonAreaId, pageIndex, pageSize);
    }

    @RequestMapping(value = "/polygonArea/addVehicles", method = RequestMethod.POST)
    public String addVehicles(@RequestParam long polygonAreaId, @RequestParam String vehicles, HttpServletRequest
            request, RedirectAttributes r) {
        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
            polygonAreaService.addVehicles(identity.getUnid(), identity.getName(), polygonAreaId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/polygonArea/removeVehicle", method = RequestMethod.POST)
    public String removeSection(@RequestParam long polygonAreaId, @RequestParam String number, HttpServletRequest
            request, RedirectAttributes r) {
        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            polygonAreaService.removeVehicle(identity.getUnid(), identity.getName(), polygonAreaId, number);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
