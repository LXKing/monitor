package com.rayton.gps.controller.baseInfo;

import com.rayton.gps.aop.Log;
import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.MonitorInfo;
import com.rayton.gps.dao.baseinfo.company.ICompanyDao;
import com.rayton.gps.dao.baseinfo.user.User;
import com.rayton.gps.dao.baseinfo.user.UserInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.domain.role.RoleInfo;
import com.rayton.gps.model.baseinfo.UserOptionModel;
import com.rayton.gps.service.UserService;
import com.rayton.gps.util.ResponseEntityWrapper;
import com.rayton.gps.util.enums.UserKinds;
import com.rayton.gps.util.enums.UserOptions;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ICompanyDao companyDao;

    @RequiresPermissions("baseinfo.user")
    @Log(name = "打开用户管理页面")
    @GetMapping("/user/user.iframe")
    public String index() {
        return "/baseinfo/user/user.iframe";
    }

    @GetMapping(value = "/user/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<UserInfo> result = userService.query(identity.getCompanyId(), filter, page, limit);

        result.code = 0;
        result.count = result.total;
        result.data = result.rows;
        // result.msg = "mmp";
        return result;
    }

    @GetMapping("/user/targets")
    @ResponseBody
    public Object targets(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return userService.getTargets(identity.getCompanyId(), filter, pageIndex, pageSize);
    }

    @GetMapping("/user/monitors")
    @ResponseBody
    public Object monitors(@RequestParam String userId) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        Page<MonitorInfo> page = new Page<MonitorInfo>();
        page.rows = userService.assignedMonitors(userId, identity.getCompanyId());
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/user/addMonitors")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addMonitors(@RequestParam String userId, @RequestParam String targets) {
        List<String> list = Arrays.asList(targets.split(","));
        // List<String> list = JsonMapper.toObject(targets, List.class, String.class);
        userService.addMonitors(userId, list);
        return ResponseEntityWrapper.OK();
        // try {
        //     List<String> list = JsonMapper.toObject(targets, List.class, String.class);
        //     userService.addMonitors(userId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/user/removeMonitor")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeMonitor(@RequestParam String userId, @RequestParam String targetId) {
        userService.removeMonitor(userId, targetId);
        return ResponseEntityWrapper.OK();
        // try {
        //     userService.removeMonitor(userId, targetId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @RequestMapping("/user/roles")
    @ResponseBody
    public Object roles(@RequestParam String userId) throws Exception {
        Page<RoleInfo> page = new Page<>();
        page.rows = userService.assignedRoles(userId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/user/addRoles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addRoles(@RequestParam String userId, @RequestParam String roles) {

        List<String> list = Arrays.asList(roles.split(","));
        // List<String> list = JsonMapper.toObject(roles, List.class, String.class);
        userService.addRoles(userId, list);
        return ResponseEntityWrapper.OK();
        // try {
        //     List<String> list = JsonMapper.toObject(roles, List.class, String.class);
        //     userService.addRoles(userId, list);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/user/removeRole")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeRole(@RequestParam String userId, @RequestParam String roleId) {
        userService.removeRole(userId, roleId);
        return ResponseEntityWrapper.OK();
        // try {
        //     userService.removeRole(userId, roleId);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping(value = "/user/create.form")
    public String create(Model model) {
        User user = new User();
        user.setEnable(true);
        model.addAttribute("user", user);
        return "/baseinfo/user/create.form";
    }

    @PostMapping(value = "/user/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("user") @Valid User user, BindingResult binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/user/create.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        user.setCompanyId(identity.getCompanyId());


        user.setCompany(companyDao.fetch(identity.getCompanyId()).getFullName());
        userService.create(user);
        return ResponseEntityWrapper.OK();
        // try {
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     user.setCompanyId(identity.getCompanyId());
        //     userService.create(user);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @GetMapping("/user/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        User user = userService.fetch(id);
        return user;
        // model.addAttribute("user", user);
        // return "/baseinfo/user/edit.form";
    }

    @PostMapping(value = "/user/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("user") @Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/user/edit.form";
        user.setKind(UserKinds.CompanyUser.getIndex());

        userService.update(user);
        return ResponseEntityWrapper.OK();
        // try {
        //     userService.update(user);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/user/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id) {
        userService.delete(id);
        return ResponseEntityWrapper.OK();
        // try {
        //     userService.delete(id);
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }

    @PostMapping(value = "/user/exist")
    @ResponseBody
    public Object exists(@RequestParam String account, @RequestParam(required = false) String id, @RequestParam boolean checkId) throws Exception {
        return checkId ? !userService.exist(account, id) : !userService.exist(account);
        // if (checkId) {
        //     response.getWriter().print(!userService.exist(account, id));
        // } else {
        //     response.getWriter().print(!userService.exist(account));
        // }
    }

    @GetMapping("/user/getOption")
    @ResponseBody
    public Object getOption(@RequestParam int type) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return userService.getUserOptions(identity.getId(), UserOptions.of(type));
    }

    @PostMapping(value = "/user/saveOption")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> saveOption(UserOptionModel op, RedirectAttributes r, HttpServletRequest request) {

        UserOptions kind = UserOptions.of(op.getType());
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        userService.saveUserOptions(identity.getId(), kind, op.getOptions());

        return ResponseEntityWrapper.OK();
        // try {
        //     UserOptions kind = UserOptions.of(op.getType());
        //     IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        //     userService.saveUserOptions(identity.getId(), kind, op.getOptions());
        //     WebUtil.success(r);
        // } catch (Exception ex) {
        //     WebUtil.error(r, ex.getMessage());
        // }
        //
        // return "redirect:/result";
    }
}
