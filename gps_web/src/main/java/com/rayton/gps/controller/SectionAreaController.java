package com.rayton.gps.controller;

import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.dao.baseinfo.sectionArea.SectionArea;
import com.rayton.gps.dao.baseinfo.sectionArea.SectionPoint;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.Identity;
import com.rayton.gps.model.baseinfo.SectionAreaModel;
import com.rayton.gps.service.SectionAreaService;
import com.rayton.gps.util.JsonMapper;
import com.rayton.gps.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class SectionAreaController {
    @Autowired
    private SectionAreaService sectionAreaService;


    @RequiresPermissions("baseinfo.sectionArea")
    @ServiceMethod(id = "baseinfo.sectionArea", pid = "baseinfo", prefix = "打开", name = "路段", suffix = "管理页面")
    @RequestMapping("/sectionArea/sectionArea.iframe")
    public String index() {
        return "/baseinfo/sectionArea/sectionArea.iframe";
    }

    @RequestMapping(value = "/sectionArea/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return sectionAreaService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @RequestMapping(value = "/sectionArea/search", method = RequestMethod.POST)
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam
            int pageSize, HttpServletRequest request) {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        try {
            return sectionAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/sectionArea/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        SectionAreaModel sectionArea = new SectionAreaModel();
        model.addAttribute("sectionArea", sectionArea);
        return "/baseinfo/sectionArea/create.form";
    }

    @RequestMapping(value = "/sectionArea/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding,
                         Model model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/sectionArea/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            sectionArea.setCompanyId(identity.getCompanyId());
            String path = sectionArea.getPath();
            List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
            sectionArea.setPoints(points);
            sectionAreaService.create(sectionArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            ex.printStackTrace();
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/sectionArea/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        SectionArea route = sectionAreaService.fetch(id);
        SectionAreaModel sectionArea = JsonMapper.convertValue(route, SectionAreaModel.class);
        model.addAttribute("sectionArea", sectionArea);
        return "/baseinfo/sectionArea/edit.form";
    }

    @RequestMapping(value = "/sectionArea/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding,
                       Model model, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/sectionArea/edit.form";

        try {
            String path = sectionArea.getPath();
            List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
            sectionArea.setPoints(points);
            sectionAreaService.update(sectionArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/sectionArea/delete", method = RequestMethod.POST)
    public String delete(@RequestParam long id, RedirectAttributes r) {
        try {
            sectionAreaService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/sectionArea/exist", method = RequestMethod.POST)
    public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean
            checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if (checkId) {
            response.getWriter().print(!sectionAreaService.exist(name, identity.getCompanyId(), id));
        } else {
            response.getWriter().print(!sectionAreaService.exist(name, identity.getCompanyId()));
        }
    }
}
