package mmp.gps.logic.controller;


import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.role.AuthorizeRequest;
import mmp.gps.domain.user.*;
import mmp.gps.logic.service.UserService;
import mmp.gps.logic.util.WebUtil;
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
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(
            value = {"/user/index"},
            method = {RequestMethod.GET}
    )
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/user/index";
    }

    @RequestMapping(
            value = {"/user/query"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object query(QueryUserRequest request) {
        try {
            return this.userService.query(request.getFilter(), request.getPage(), request.getPageSize());
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/user/create.form"},
            method = {RequestMethod.GET}
    )
    public String create(Model model) {
        CreateUserRequest user = new CreateUserRequest();
        model.addAttribute("user", user);
        return "/user/create.form";
    }

    @RequestMapping(
            value = {"/user/create.form"},
            method = {RequestMethod.POST}
    )
    public String create(@ModelAttribute("user") @Valid CreateUserRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            return "/user/create.form";
        } else {
            try {
                this.userService.create(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/user/edit.form"},
            method = {RequestMethod.GET}
    )
    public String edit(FetchUserRequest request, Model model) throws Exception {
        FetchUserResponse user = this.userService.fetch(request.getId());
        model.addAttribute("user", user);
        return "/user/edit.form";
    }

    @RequestMapping(
            value = {"/user/edit.form"},
            method = {RequestMethod.POST}
    )
    public String edit(@ModelAttribute("user") @Valid EditUserRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            return "/user/edit.form";
        } else {
            try {
                this.userService.update(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/user/delete"},
            method = {RequestMethod.POST}
    )
    public String delete(@RequestParam String id, RedirectAttributes r) {
        try {
            this.userService.delete(id);
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/user/roles"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object roles(UserRolesRequest request) {
        try {
            return this.userService.getRoles(request.getUserId());
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/user/authorize"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object authorize(AuthorizeRequest request, @CookieValue String token) {
        AppResponse response = new AppResponse();

        try {
            this.userService.authorize(request.getRoleId(), request.getUserId(), request.isAllow());
        } catch (Exception var5) {
            response.setErrorMessage(var5.getMessage());
        }

        return response;
    }
}
