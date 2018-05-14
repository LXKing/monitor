package com.rayton.gps.controller;


import com.rayton.gps.common.DateFormats;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.dao.statistics.HistoryOnlineOfflineCount;
import com.rayton.gps.export.ExcelView;
import com.rayton.gps.export.PdfView;
import com.rayton.gps.service.StatisticsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.*;

@Controller
public class TestController {

    @Autowired
    private StatisticsService statisticsService;

    //
    @GetMapping(value = "/admin")
    public ModelAndView test() {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("name", identity.getName());
        return modelAndView;
    }

    @GetMapping(value = "/download")
    public ModelAndView download() throws Exception {
        String motorcade = "车队";


        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date start = c.getTime();


        Date end = new Date();
        String type = "excel";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<HistoryOnlineOfflineCount> list = statisticsService.historyOnlineOfflineCount(identity.getId(), motorcade, start, end);

        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("fileName", "HistoryOnlineOfflineCount");
        map.put("title", "车辆历史上线率统计");
        map.put("headers", new String[]{"车队", "开始时间", "结束时间", "车辆总数", "上线数量", "上线率", "下线数量", "下线率"});
        map.put("widths", new float[]{20f, 15f, 15f, 10f, 10f, 10f, 10f, 10f});

        List<List<String>> rows = new ArrayList<List<String>>();
        for (HistoryOnlineOfflineCount count : list) {
            List<String> row = new ArrayList<String>();
            row.add(count.getMotorcade());
            row.add(DateFormats.toDateString(count.getStart()));
            row.add(DateFormats.toDateString(count.getEnd()));
            row.add(count.getTotal() + "");
            row.add(count.getOnline() + "");
            row.add(count.getOnlineRate() + "%");
            row.add(count.getOffline() + "");
            row.add(count.getOfflineRate() + "%");

            rows.add(row);
        }
        map.put("list", rows);
        View view = type.equals("pdf") ? new PdfView() : new ExcelView();
        ModelAndView modelAndView = new ModelAndView(view, map);
        return modelAndView;
//        return this.export(type, map);
        // if (type.equals("pdf")) {
        //     PdfView pdfView = new PdfView();
        //     return new ModelAndView(pdfView, map);
        // } else {
        //     ExcelView viewExcel = new ExcelView();
        //     return new ModelAndView(viewExcel, map);
        // }
    }

}
