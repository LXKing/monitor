package com.rayton.gps.controller.area;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleArea;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleAreaInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.area.RectangleAreaService;
import com.rayton.gps.util.JsonMapper;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class RectangleAreaController {
    @Autowired
    private RectangleAreaService rectangleAreaService;


    @RequiresPermissions("baseinfo.rectangleArea")
    @Log(name = "打开矩形区域管理页面")
    @GetMapping("/rectangleArea/rectangleArea.iframe")
    public String index() {
        return "/baseinfo/rectangleArea/rectangleArea.iframe";
    }

    @GetMapping(value = "/rectangleArea/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<RectangleAreaInfo> result = rectangleAreaService.query(identity.getCompanyId(), filter, page, limit);

        result.code = 0;
        result.count = result.total;
        result.data = result.rows;
        // result.msg = "mmp";
        return result;
    }

    @PostMapping(value = "/rectangleArea/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        return rectangleAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        // try {
        //     return rectangleAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);
        // } catch (Exception ex) {
        //     return null;
        // }
    }

    @GetMapping(value = "/rectangleArea/create.form")
    public String create(Model model) {
        RectangleArea rectangleArea = new RectangleArea();
        rectangleArea.setDeviceCatch(true);
        model.addAttribute("rectangleArea", rectangleArea);
        return "/baseinfo/rectangleArea/create.form";
    }

    @PostMapping(value = "/rectangleArea/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("rectangleArea") @Valid RectangleArea rectangleArea, BindingResult binding) {
        if (binding.hasErrors())
            // return "/baseinfo/rectangleArea/create.form";
            return ResponseEntityWrapper.Failed();

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        rectangleArea.setCompanyId(identity.getCompanyId());
        rectangleAreaService.create(rectangleArea);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     rectangleArea.setCompanyId(identity.getCompanyId());
        //     rectangleAreaService.create(rectangleArea);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/rectangleArea/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        RectangleArea rectangleArea = rectangleAreaService.fetch(id);
        model.addAttribute("rectangleArea", rectangleArea);
        return "/baseinfo/rectangleArea/edit.form";
    }

    @PostMapping(value = "/rectangleArea/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("rectangleArea") @Valid RectangleArea rectangleArea, BindingResult binding) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/rectangleArea/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        rectangleAreaService.update(identity.getUnid(), identity.getName(), rectangleArea);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     rectangleAreaService.update(identity.getUnid(), identity.getName(), rectangleArea);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/rectangleArea/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam long id) {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        rectangleAreaService.delete(identity.getUnid(), identity.getName(), id);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     rectangleAreaService.delete(identity.getUnid(), identity.getName(), id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/rectangleArea/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        return checkId ? !rectangleAreaService.exist(name, identity.getCompanyId(), id) : !rectangleAreaService.exist(name, identity
                .getCompanyId());
        // if (checkId) {
        //     response.getWriter().print(!rectangleAreaService.exist(name, identity.getCompanyId(), id));
        // } else {
        //     response.getWriter().print(!rectangleAreaService.exist(name, identity.getCompanyId()));
        // }
    }

    @GetMapping("/rectangleArea/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam long rectangleAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return rectangleAreaService.assignedVehicles(rectangleAreaId, pageIndex, pageSize);
    }

    @PostMapping(value = "/rectangleArea/addVehicles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addVehicles(@RequestParam long rectangleAreaId, @RequestParam String vehicles) {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        rectangleAreaService.addVehicles(identity.getUnid(), identity.getName(), rectangleAreaId, list);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        //     rectangleAreaService.addVehicles(identity.getUnid(), identity.getName(), rectangleAreaId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/rectangleArea/removeVehicle")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam long rectangleAreaId, @RequestParam String number) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        rectangleAreaService.removeVehicle(identity.getUnid(), identity.getName(), rectangleAreaId, number);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     rectangleAreaService.removeVehicle(identity.getUnid(), identity.getName(), rectangleAreaId, number);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }
}
