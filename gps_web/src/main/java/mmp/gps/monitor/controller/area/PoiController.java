package mmp.gps.monitor.controller.area;

import mmp.gps.common.util.Page;
import mmp.gps.domain.poi.Poi;
import mmp.gps.domain.poi.PoiInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.service.area.PoiService;
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
import java.util.Map;

@Controller
public class PoiController {
    @Autowired
    private PoiService poiService;

    @RequiresPermissions("baseinfo.poi")
    @Log(name = "打开兴趣点管理页面")
    @GetMapping("/poi/poi.iframe")
    public String index() {
        return "/baseinfo/poi/poi.iframe";
    }

    @GetMapping(value = "/poi/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<PoiInfo> result = poiService.query(identity.getCompanyId(), filter, page, limit);

        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;
    }

    @PostMapping(value = "/poi/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) throws RuntimeException {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        filter = filter == null ? "" : filter;
        return poiService.search(identity.getCompanyId(), filter, pageIndex, pageSize);

    }

    @GetMapping(value = "/poi/create.form")
    public String create(Model model) {
        Poi poi = new Poi();
        model.addAttribute("poi", poi);
        return "/baseinfo/poi/create.form";
    }

    @PostMapping(value = "/poi/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("poi") @Valid Poi poi, BindingResult binding) throws RuntimeException {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        poi.setCompanyId(identity.getCompanyId());
        poiService.create(poi);

        return ResponseEntityWrapper.OK();

    }


    @GetMapping("/poi/edit.form")
    public String edit(@RequestParam long id, Model model) throws Exception {
        Poi poi = poiService.fetch(id);
        model.addAttribute("poi", poi);
        return "/baseinfo/poi/edit.form";
    }

    @PostMapping(value = "/poi/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("poi") @Valid Poi poi, BindingResult binding) {
        if (binding.hasErrors())
            return ResponseEntityWrapper.Failed();
        // return "/baseinfo/poi/edit.form";
        poiService.update(poi);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/poi/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam long id) {
        poiService.delete(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/poi/exist")
    @ResponseBody
    public Object exists(@RequestParam String name, @RequestParam(required = false) Long id, @RequestParam boolean checkId) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Object re = checkId ? !poiService.exist(name, identity.getCompanyId(), id) : !poiService.exist(name, identity.getCompanyId());

        return re;

    }


}
