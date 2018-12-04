package mmp.gps.monitor.controller.area;

import mmp.gps.common.util.Page;
import mmp.gps.domain.area.CircleArea;
import mmp.gps.domain.area.CircleAreaInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.service.area.CircleAreaService;
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
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class CircleAreaController {
    @Autowired
    private CircleAreaService circleAreaService;


    @RequiresPermissions("baseinfo.circleArea")
    @Log(name = "打开圆形区域页面")
    @GetMapping("/circleArea/circleArea.iframe")
    public String index() {
        return "/baseinfo/circleArea/circleArea.iframe";
    }

    @GetMapping(value = "/circleArea/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        Page<CircleAreaInfo> result = circleAreaService.query(identity.getCompanyId(), filter, page, limit);
        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;

    }

    @PostMapping(value = "/circleArea/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        filter = filter == null ? "" : filter;
        return circleAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);

    }

    @GetMapping(value = "/circleArea/create.form")
    public String create(Model model) {
        CircleArea circleArea = new CircleArea();
        circleArea.setDeviceCatch(true);
        model.addAttribute("circleArea", circleArea);
        return "/baseinfo/circleArea/create.form";
    }

    @PostMapping(value = "/circleArea/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/circleArea/create.form";

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        circleArea.setCompanyId(identity.getCompanyId());
        circleAreaService.create(circleArea);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping("/circleArea/edit.form")
    @ResponseBody
    public Object edit(@RequestParam long id, Model model) throws Exception {
        CircleArea circleArea = circleAreaService.fetch(id);
        return circleArea;

    }

    @PostMapping(value = "/circleArea/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("circleArea") @Valid CircleArea circleArea, BindingResult binding, Model model, HttpServletRequest request, RedirectAttributes r) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/circleArea/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        circleArea.setEditTime(new Timestamp(System.currentTimeMillis()));
        circleAreaService.update(identity.getUnid(), identity.getName(), circleArea);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/circleArea/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam long id, HttpServletRequest request, RedirectAttributes r) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        circleAreaService.delete(identity.getUnid(), identity.getName(), id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/circleArea/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return checkId ? !circleAreaService.exist(name, identity.getCompanyId(), id) : !circleAreaService.exist(name, identity
                .getCompanyId());


    }

    @RequestMapping("/circleArea/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam long circleAreaId, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return circleAreaService.assignedVehicles(circleAreaId, pageIndex, pageSize);
    }

    @PostMapping(value = "/circleArea/addVehicles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addVehicles(@RequestParam long circleAreaId, @RequestParam String vehicles, HttpServletRequest request, RedirectAttributes r) {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
//        List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        List<String> list = Arrays.asList(vehicles.split(","));
        circleAreaService.addVehicles(identity.getUnid(), identity.getName(), circleAreaId, list);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/circleArea/removeVehicle")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam long circleAreaId, @RequestParam String number, HttpServletRequest request, RedirectAttributes r) {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        circleAreaService.removeVehicle(identity.getUnid(), identity.getName(), circleAreaId, number);

        return ResponseEntityWrapper.OK();

    }
}
