package com.edata.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edata.godp.domain.role.RoleInfo;
import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.dao.Page;
import com.edata.monitor.domain.baseinfo.MonitorInfo;
import com.edata.monitor.domain.baseinfo.User;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.model.baseinfo.UserOptionModel;
import com.edata.monitor.service.UserService;
import com.edata.monitor.util.JsonMapper;
import com.edata.monitor.util.WebUtil;
import com.edata.monitor.util.kind.UserOptions;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@ServiceMethod(id = "baseinfo.user", pid = "baseinfo", prefix = "打开", name = "用户管理", suffix = "页面")
	@RequestMapping("/user/user.iframe")
	public String index() {
		return "/baseinfo/user/user.iframe";
	}

	@RequestMapping(value = "/user/query", method = RequestMethod.POST)
	@ResponseBody
	public Object query(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return userService.query(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping("/user/targets")
	@ResponseBody
	public Object targets(@RequestParam String filter, @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request)
			throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return userService.getTargets(identify.getCompanyId(), filter, pageIndex, pageSize);
	}

	@RequestMapping("/user/monitors")
	@ResponseBody
	public Object monitors(@RequestParam String userId, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");

		Page<MonitorInfo> page = new Page<MonitorInfo>();
		page.rows = userService.assignedMonitors(userId, identify.getCompanyId());
		page.total = page.rows == null ? 0 : page.rows.size();
		return page;
	}

	@RequestMapping(value = "/user/addMonitors", method = RequestMethod.POST)
	public String addMonitors(@RequestParam String userId, @RequestParam String targets, RedirectAttributes r) {
		try {
			List<String> list = JsonMapper.toObject(targets, List.class, String.class);
			userService.addMonitors(userId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/user/removeMonitor", method = RequestMethod.POST)
	public String removeMonitor(@RequestParam String userId, @RequestParam String targetId, RedirectAttributes r) {
		try {
			userService.removeMonitor(userId, targetId);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/user/roles")
	@ResponseBody
	public Object roles(@RequestParam String userId) throws Exception {
		Page<RoleInfo> page = new Page<RoleInfo>();
		page.rows = userService.assignedRoles(userId);
		page.total = page.rows == null ? 0 : page.rows.size();
		return page;
	}

	@RequestMapping(value = "/user/addRoles", method = RequestMethod.POST)
	public String addRoles(@RequestParam String userId, @RequestParam String roles, RedirectAttributes r) {
		try {
			List<String> list = JsonMapper.toObject(roles, List.class, String.class);
			userService.addRoles(userId, list);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/user/removeRole", method = RequestMethod.POST)
	public String removeRole(@RequestParam String userId, @RequestParam String roleId, RedirectAttributes r) {
		try {
			userService.removeRole(userId, roleId);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/user/create.form", method = RequestMethod.GET)
	public String create(Model model) {
		User user = new User();
		user.setEnable(true);
		model.addAttribute("user", user);
		return "/baseinfo/user/create.form";
	}

	@RequestMapping(value = "/user/create.form", method = RequestMethod.POST)
	public String create(@ModelAttribute("user") @Valid User user, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/user/create.form";

		try {
			Identify identify = (Identify) request.getAttribute("user");
			user.setCompanyId(identify.getCompanyId());
			userService.create(user);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping("/user/edit.form")
	public String edit(@RequestParam String id, Model model) throws Exception {
		User user = userService.fetch(id);
		model.addAttribute("user", user);
		return "/baseinfo/user/edit.form";
	}

	@RequestMapping(value = "/user/edit.form", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") @Valid User user, BindingResult binding, Model model, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/baseinfo/user/edit.form";

		try {
			userService.update(user);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String delete(@RequestParam String id, RedirectAttributes r) {
		try {
			userService.delete(id);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/user/exist", method = RequestMethod.POST)
	public void exists(@RequestParam String account, @RequestParam(required = false) String id, @RequestParam boolean checkId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (checkId) {
			response.getWriter().print(!userService.exist(account, id));
		} else {
			response.getWriter().print(!userService.exist(account));
		}
	}

	@RequestMapping("/user/getOption")
	@ResponseBody
	public Object getOption(@RequestParam int type, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");
		return userService.getUserOptions(identify.getId(), UserOptions.of(type));
	}

	@RequestMapping(value = "/user/saveOption", method = RequestMethod.POST)
	public String saveOption(UserOptionModel op, RedirectAttributes r, HttpServletRequest request) {
		try {
			UserOptions kind = UserOptions.of(op.getType());
			Identify identify = (Identify) request.getAttribute("user");
			userService.saveUserOptions(identify.getId(), kind, op.getOptions());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}
}
