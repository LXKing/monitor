package mmp.gps.logic.controller;

import mmp.gps.common.util.CookieUtil;
import mmp.gps.common.util.Data;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.domain.app.AppRequest;
import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.device.DeviceCountOnlineResponse;
import mmp.gps.domain.security.*;
import mmp.gps.logic.Identity;
import mmp.gps.logic.config.GodpGlobalConfiguration;
import mmp.gps.logic.portal.ServicePortal;
import mmp.gps.logic.service.DeviceService;
import mmp.gps.logic.service.LocateService;
import mmp.gps.logic.service.SecurityService;
import mmp.gps.logic.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class HomeController {

    private static final String UTF_8 = "utf-8";

    private ServicePortal portal = new ServicePortal();
    @Autowired
    private SecurityService securityService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private LocateService locateService;

    @Autowired
    GodpGlobalConfiguration godpGlobalConfiguration;


    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.GET}
    )
    public String logon(Model model, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.clearCookies(request, response);
        model.addAttribute("login", new LoginRequest());
        return "login";
    }

    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.POST}
    )
    public String logon(@ModelAttribute("login") @Valid LoginRequest login, BindingResult binding, HttpServletRequest request, HttpServletResponse response) {
        if (binding.hasErrors()) {
            return "login";
        } else {
            try {
                LoginResponse ex = this.securityService.login(login.getAccount(), login.getPwd());
                Cookie error1 = new Cookie("token", ex.getToken());
                response.addCookie(error1);
                return "redirect:/home/index";
            } catch (Exception var7) {
                String error = var7.getMessage();
                binding.addError(new ObjectError("", error));
                return "login";
            }
        }
    }

    @RequestMapping(
            value = {"/logout"},
            method = {RequestMethod.POST}
    )
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.clearCookies(request, response);
        return "redirect:/";
    }

    @RequestMapping(
            value = {"/home/index"},
            method = {RequestMethod.GET}
    )
    public String index(Model model) {
        try {
            DeviceCountOnlineResponse response = this.deviceService.queryOnline(this.godpGlobalConfiguration.getOfflineTimeout());
            model.addAttribute("device", response);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return "/home/index";
    }

    @RequestMapping(
            value = {"/myinfo.form"},
            method = {RequestMethod.GET}
    )
    public String myinfo(Model model, HttpServletRequest request) throws Exception {
        Identity user = (Identity) request.getAttribute("user");
        GetMyInfoResponse me = this.securityService.getMyinfo(user);
        SaveMyInfoRequest info = new SaveMyInfoRequest();
        info.setAccount(me.getAccount());
        info.setContact(me.getContact());
        info.setEditTime(me.getEditTime());
        info.setEmail(me.getEmail());
        info.setId(me.getId());
        info.setName(me.getName());
        info.setPhone(me.getPhone());
        model.addAttribute("myinfo", info);
        return "/myinfo.form";
    }

    @RequestMapping(
            value = {"/myinfo.form"},
            method = {RequestMethod.POST}
    )
    public String myinfo(@ModelAttribute("myinfo") @Valid SaveMyInfoRequest info, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            return "/myinfo.form";
        } else {
            try {
                this.securityService.saveMyInfo(info);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/mykey.form"},
            method = {RequestMethod.GET}
    )
    public String mykey(Model model) {
        model.addAttribute("mykey", new SaveMyKeyRequest());
        return "/mykey.form";
    }

    @RequestMapping(
            value = {"/mykey.form"},
            method = {RequestMethod.POST}
    )
    public String mykey(@ModelAttribute("mykey") @Valid SaveMyKeyRequest mykey, BindingResult binding, Model model, HttpServletRequest request, RedirectAttributes r) {
        try {
            Identity ex = (Identity) request.getAttribute("user");
            this.securityService.saveMyKey(ex, mykey);
        } catch (Exception var7) {
            WebUtil.error(r, var7.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping({"/result"})
    @ResponseBody
    public Object result(@RequestParam int code, @RequestParam String error) {
        HashMap map = new HashMap();
        map.put("code", Integer.valueOf(code));
        map.put("error", error);
        return map;
    }

    @RequestMapping({"/device/getmileage"})
    @ResponseBody
    public Data index(@RequestParam String dn) throws Exception {
        return this.locateService.getmileage(dn);
    }

    @RequestMapping(
            value = {"/api"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object apiCall(HttpServletRequest request) throws Exception {
        AppResponse response = new AppResponse();
        String encoding = request.getCharacterEncoding();
        String error = this.checkRequest(request);
        if (error != null) {
            response.setErrorMessage(error);
        } else {
            if (encoding == null || encoding.isEmpty()) {
                encoding = "utf-8";
            }

            int length = request.getContentLength();
            byte[] raw = new byte[length];

            int len;
            for (int count = 0; (len = request.getInputStream().read(raw, count, length - count)) != -1; count += len) {
                ;
            }

            String content = new String(raw, encoding);
            AppRequest ar = (AppRequest) JsonMapper.toObject(content, AppRequest.class);
            response = this.portal.execute(ar);
        }

        return response;
    }

    private String checkRequest(HttpServletRequest req) {
        if (req.getContentLength() <= 0) {
            return "请求内容不能为空！";
        } else {
            String contentType = req.getContentType();
            return contentType != null && contentType.toLowerCase().contains("application/json") ? null : "请求内容需用json格式";
        }
    }
}
