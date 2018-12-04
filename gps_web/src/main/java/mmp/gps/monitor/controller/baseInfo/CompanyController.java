package mmp.gps.monitor.controller.baseInfo;

import mmp.gps.common.util.Page;
import mmp.gps.common.util.Tuple;
import mmp.gps.common.enums.UserKinds;
import mmp.gps.domain.company.Company;
import mmp.gps.domain.company.CompanyInfoVo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.user.User;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.model.baseinfo.CompanyModel;
import mmp.gps.monitor.service.CompanyService;
import mmp.gps.monitor.service.SecurityService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @Autowired
    private SecurityService securityService;

    @GetMapping("baseinfo.company")
    @Log(name = "打开企业管理页面")
    @RequestMapping("/company/company.iframe")
    public String iframe() {
        return "/baseinfo/company/company.iframe";
    }

    @GetMapping("/company/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit)
            throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<CompanyInfoVo> result = companyService.query(identity.getCompanyId(), filter, page, limit);
        for (int i = 0; i < result.rows.size(); i++) {
            result.rows.get(i).setCompanyPName(identity.getFULLNAME());
        }
        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;
        // return companyService.getMmp(identity.getCompanyId());
    }

    @GetMapping("/company/getMmp")
    @ResponseBody
    public Object getMmp() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        CompanyInfoVo companyInfoVo = companyService.getMmp(identity.getCompanyId());
        if (identity.kind == 0) {
            companyInfoVo.setNow("");
        } else {
            companyInfoVo.setNow(identity.getCompanyId());
        }


        return companyInfoVo;
    }

    @GetMapping(value = "/company/createPermission")
    public void createPermission(Model model) {
        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        // Permission permission = new Permission();
        // // permission.setCOMPANYID(identity.getCompanyId());
        // permission.setCOMPANYID("5a72ab073d769a75b6309dec");
        // permission.setId("test");
        // permission.setName("测试");
        // permission.setPid("baseinfo");
        // securityService.createPermission(permission);


    }

    @GetMapping(value = "/company/create.form")
    public String create(Model model) {
        CompanyModel m = new CompanyModel();
        model.addAttribute("editor", m);
        return "/baseinfo/company/create.form";
    }

    @PostMapping(value = "/company/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding)
            throws Exception {
        if (binding.hasErrors())
            // return "/baseinfo/company/create.form";

            return ResponseEntityWrapper.Failed();
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setPid(identity.getCompanyId());
        m.setKind(UserKinds.CompanyAdmin.getIndex());
        companyService.create(m.getCompany(), m.getUser());

        return ResponseEntityWrapper.OK();


    }


    @GetMapping(value = "/company/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id) throws Exception {
        Tuple<Company, User> tuple = companyService.fetch(id);
        CompanyModel m = new CompanyModel();
        m.fill(tuple.t);
        m.fill(tuple.e);
        return m;
        // model.addAttribute("editor", m);
        // return "/baseinfo/company/edit.form";
    }

    @PostMapping(value = "/company/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("editor") @Valid CompanyModel m, BindingResult binding)
            throws Exception {
        if (binding.hasErrors())

            // return "/baseinfo/company/edit.form";
            return ResponseEntityWrapper.Failed();

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setPid(identity.getCompanyId());
        m.setUserEditTime(new Timestamp(System.currentTimeMillis()));
        m.setCompanyEditTime(new Timestamp(System.currentTimeMillis()));

        m.setKind(UserKinds.CompanyAdmin.getIndex());
        companyService.update(m.getCompany(), m.getUser());
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/company/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id) throws Exception {
        companyService.delete(id);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping(value = "/company/authorize.form")
    public String authorize() throws Exception {
        return "/baseinfo/company/authorize.form";
    }

    @GetMapping("/company/authorizes")
    @ResponseBody
    public Object authorizes(@RequestParam String companyId) throws Exception {
        return companyService.authorizes(companyId);
    }

    @PostMapping(value = "/company/authorize")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> authorize(@RequestParam String companyId, @RequestParam(required = false, defaultValue = "") List<String> list)
            throws Exception {
        companyService.authorize(companyId, list);
        return ResponseEntityWrapper.OK();

    }

}
