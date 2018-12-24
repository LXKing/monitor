package mmp.gps.monitor.controller.area;

import mmp.gps.common.util.JsonMapper;
import mmp.gps.common.util.Page;
import mmp.gps.domain.area.SectionArea;
import mmp.gps.domain.area.SectionAreaInfo;
import mmp.gps.domain.area.SectionPoint;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.model.baseinfo.SectionAreaModel;
import mmp.gps.monitor.service.area.SectionAreaService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SectionAreaController {
    @Autowired
    private SectionAreaService sectionAreaService;


    @RequiresPermissions("baseinfo.sectionArea")
    @Log(name = "打开路段管理页面")
    @GetMapping("/sectionArea/sectionArea.iframe")
    public String index() {
        return "/baseinfo/sectionArea/sectionArea.iframe";
    }

    @PostMapping(value = "/sectionArea/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<SectionAreaInfo> result = sectionAreaService.query(identity.getCompanyId(), filter, page, limit);


        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;
    }

    @PostMapping(value = "/sectionArea/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        return sectionAreaService.search(identity.getCompanyId(), filter, pageIndex, pageSize);

    }

    @GetMapping(value = "/sectionArea/create.form")
    public String create(Model model) {
        SectionAreaModel sectionArea = new SectionAreaModel();
        model.addAttribute("sectionArea", sectionArea);
        return "/baseinfo/sectionArea/create.form";
    }

    @PostMapping(value = "/sectionArea/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/sectionArea/create.form";

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        sectionArea.setCompanyId(identity.getCompanyId());
        String path = sectionArea.getPath();
        List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
        sectionArea.setPoints(points);
        sectionAreaService.create(sectionArea);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping("/sectionArea/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        SectionArea route = sectionAreaService.fetch(id);
        SectionAreaModel sectionArea = JsonMapper.convertValue(route, SectionAreaModel.class);
        model.addAttribute("sectionArea", sectionArea);
        return "/baseinfo/sectionArea/edit.form";
    }

    @PostMapping(value = "/sectionArea/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("sectionArea") @Valid SectionAreaModel sectionArea, BindingResult binding) {
        if (binding.hasErrors())
            // return "/baseinfo/sectionArea/edit.form";
            return ResponseEntityWrapper.Failed();

        String path = sectionArea.getPath();
        List<SectionPoint> points = JsonMapper.toObject(path, ArrayList.class, SectionPoint.class);
        sectionArea.setPoints(points);
        sectionAreaService.update(sectionArea);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/sectionArea/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam long id) {
        sectionAreaService.delete(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/sectionArea/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        return checkId ? !sectionAreaService.exist(name, identity.getCompanyId(), id) : !sectionAreaService.exist(name, identity
                .getCompanyId());

    }
}
