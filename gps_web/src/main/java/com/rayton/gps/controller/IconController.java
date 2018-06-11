package com.rayton.gps.controller;

import com.rayton.gps.dao.Page;
import com.rayton.gps.dao.baseinfo.MarkerFileInfo;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.IconService;
import com.rayton.gps.util.IconUtil;
import com.rayton.gps.util.enums.MarkerType;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IconController {

    @Autowired
    private IconService iconService;


    @PostMapping("/icon/poi/upload")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> upload(MultipartFile file, String name, double lng, double lat, HttpServletRequest request) throws Exception {
        System.out.println(file.isEmpty());
        // Assert.isTrue(mmp.isEmpty(), "文件不能为空！");
        String path = request.getServletContext().getRealPath("/WEB-INF/classes/static/icons/");
        System.out.println("path" + path);
        String fileName = IconUtil.uploadPic(path, file);
        System.out.println("fileName" + fileName);
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        MarkerFileInfo markerFileInfo = new MarkerFileInfo();
        markerFileInfo.setName(fileName);
        markerFileInfo.setType(MarkerType.POI.getType());
        markerFileInfo.setCompanyId(identity.getCompanyId());
        markerFileInfo.setAlt(lat);
        markerFileInfo.setLng(lng);
        markerFileInfo.setShowName(name);
        // markerFileInfo.setTime(new Date());
        iconService.insert(markerFileInfo);
        Map<Object, Object> map = new HashMap<>();
        map.put("error", "ok...");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @GetMapping(value = "/icon/poi/query")
    @ResponseBody
    public Object query() {
        // String path = request.getServletContext().getRealPath("/static/icons");
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<MarkerFileInfo> page = new Page<>();
        page.rows = iconService.listByType(MarkerType.POI.getType(), identity.getCompanyId());
        page.total = page.rows.size();
        return page;
    }

}
