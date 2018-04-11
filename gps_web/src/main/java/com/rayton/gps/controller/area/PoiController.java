package com.rayton.gps.controller.area;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.baseinfo.poi.Poi;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.service.area.PoiService;
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

@Controller
public class PoiController {
    @Autowired
    private PoiService poiService;


    @RequiresPermissions("baseinfo.poi")
    @Log(id = "baseinfo.poi", pid = "baseinfo", prefix = "打开", name = "兴趣点", suffix = "管理页面")
    @RequestMapping("/poi/poi.iframe")
    public String index() {
        return "/baseinfo/poi/poi.iframe";
    }

    @RequestMapping(value = "/poi/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return poiService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @RequestMapping(value = "/poi/search", method = RequestMethod.POST)
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam
            int pageSize, HttpServletRequest request) {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        try {
            return poiService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/poi/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        Poi poi = new Poi();
        model.addAttribute("poi", poi);
        return "/baseinfo/poi/create.form";
    }

    @RequestMapping(value = "/poi/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("poi") @Valid Poi poi, BindingResult binding, Model model,
                         HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/poi/create.form";

        try {
            IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            poi.setCompanyId(identity.getCompanyId());
            poiService.create(poi);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/poi/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        Poi poi = poiService.fetch(id);
        model.addAttribute("poi", poi);
        return "/baseinfo/poi/edit.form";
    }

    @RequestMapping(value = "/poi/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("poi") @Valid Poi poi, BindingResult binding, Model model, RedirectAttributes
            r) {
        if (binding.hasErrors())
            return "/baseinfo/poi/edit.form";

        try {
            poiService.update(poi);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/poi/delete", method = RequestMethod.POST)
    public String delete(@RequestParam long id, RedirectAttributes r) {
        try {
            poiService.delete(id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/poi/exist", method = RequestMethod.POST)
    public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean
            checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if (checkId) {
            response.getWriter().print(!poiService.exist(name, identity.getCompanyId(), id));
        } else {
            response.getWriter().print(!poiService.exist(name, identity.getCompanyId()));
        }
    }
}
