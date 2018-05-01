package com.rayton.gps.controller;


import com.rayton.gps.dao.Viewport;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.ViewportService;
import com.rayton.gps.util.Assist;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewpointController {


    @Autowired
    private ViewportService viewportService;

    @GetMapping(value = "/viewport/get")
    @ResponseBody
    public Object get() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Viewport viewport = new Viewport();
        viewport.setUserId(identity.getCompanyId());

        Assist assist=new Assist();
        assist.setRequires(Assist.andEq("userId",identity.getCompanyId()));
        return viewportService.selectViewport(assist);
    }

    @PostMapping(value = "/viewport/set")
    @ResponseBody
    public Object set(Viewport viewport) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        viewport.setUserId(identity.getCompanyId());
        return viewportService.insertViewport(viewport);


    }


}
