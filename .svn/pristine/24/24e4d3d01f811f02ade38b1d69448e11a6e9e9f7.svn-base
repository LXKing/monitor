package com.rayton.gps.controller.area;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.baseinfo.circleArea.CircleArea;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.area.CircleAreaService;
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
import java.util.List;

@Controller
public class CircleAreaController {
    @Autowired
    private CircleAreaService circleAreaService;


    @RequiresPermissions("baseinfo.circleArea")
    @Log(name = "打开圆形区域页面")
    @RequestMapping("/circleArea/circleArea.iframe")
    public String index() {
        return "/baseinfo/circleArea/circleArea.iframe";
    }

    @RequestMapping(value = "/circleArea/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize,
                        HttpServletRequest request) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return circleAreaService.query(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @RequestMapping(value = "/circleArea/search", method = RequestMethod.POST)
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam
            int pageSize, HttpServletRequest request) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        filter = filter == null ? "" : filter;
        try {
            return circleAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        } catch (Exception ex) {
            return null;
        }
    }

    @RequestMapping(value = "/circleArea/create.form", method = RequestMethod.GET)
    public String create(Model model) {
        CircleArea circleArea = new CircleArea();
        circleArea.setDeviceCatch(true);
        model.addAttribute("circleArea", circleArea);
        return "/baseinfo/circleArea/create.form";
    }

    @RequestMapping(value = "/circleArea/create.form", method = RequestMethod.POST)
    public String create(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model
            model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/circleArea/create.form";

        try {
            IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            circleArea.setCompanyId(identity.getCompanyId());
            circleAreaService.create(circleArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping("/circleArea/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        CircleArea circleArea = circleAreaService.fetch(id);
        model.addAttribute("circleArea", circleArea);
        return "/baseinfo/circleArea/edit.form";
    }

    @RequestMapping(value = "/circleArea/edit.form", method = RequestMethod.POST)
    public String edit(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model
            model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return "/baseinfo/circleArea/edit.form";

        try {
            IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            circleAreaService.update(identity.getUnid(), identity.getName(), circleArea);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/circleArea/delete", method = RequestMethod.POST)
    public String delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
        try {
            IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            circleAreaService.delete(identity.getUnid(), identity.getName(), id);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/circleArea/exist", method = RequestMethod.POST)
    public void exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean
            checkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if (checkId) {
            response.getWriter().print(!circleAreaService.exist(name, identity.getCompanyId(), id));
        } else {
            response.getWriter().print(!circleAreaService.exist(name, identity.getCompanyId()));
        }
    }

    @RequestMapping("/circleArea/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam long circleAreaId, @RequestParam int pageIndex, @RequestParam int pageSize)
            throws Exception {
        return circleAreaService.assignedVehicles(circleAreaId, pageIndex, pageSize);
    }

    @RequestMapping(value = "/circleArea/addVehicles", method = RequestMethod.POST)
    public String addVehicles(@RequestParam long circleAreaId, @RequestParam String vehicles, HttpServletRequest
            request, RedirectAttributes r) {
        try {
            IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
            circleAreaService.addVehicles(identity.getUnid(), identity.getName(), circleAreaId, list);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(value = "/circleArea/removeVehicle", method = RequestMethod.POST)
    public String removeSection(@RequestParam long circleAreaId, @RequestParam String number, HttpServletRequest
            request, RedirectAttributes r) {
        try {
            IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            circleAreaService.removeVehicle(identity.getUnid(), identity.getName(), circleAreaId, number);
            WebUtil.success(r);
        } catch (Exception ex) {
            WebUtil.error(r, ex.getMessage());
        }

        return "redirect:/result";
    }
}
