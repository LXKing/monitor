package com.rayton.gps.controller;

import com.rayton.gps.common.DateFormats;
import com.rayton.gps.dao.baseinfo.company.CompanyInfo;
import com.rayton.gps.dao.baseinfo.vehicle.VehicleInfo;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.export.ExcelView;
import com.rayton.gps.export.PdfView;
import com.rayton.gps.service.OverviewService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class OverviewController {
    @Autowired
    private OverviewService overviewService;

    @RequestMapping(value = "/overview/companyServiceExpired.form", method = RequestMethod.GET)
    public String companyServiceExpired(@RequestParam int days, Model model) {
        model.addAttribute("days", days);
        return "/overview/companyServiceExpired.form";
    }

    @RequestMapping(value = "/overview/companyServiceExpired", method = RequestMethod.POST)
    @ResponseBody
    public Object companyServiceExpired(@RequestParam int days, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return overviewService.companyServiceExpired(identity.getId(), days);
    }

    @RequestMapping(value = "/overview/companyServiceExpiredExcel", method = RequestMethod.GET)
    public ModelAndView companyServiceExpiredExcel(@RequestParam int days, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<CompanyInfo> list = overviewService.companyServiceExpired(identity.getId(), days);
        ExcelView viewExcel = new ExcelView();

        Map<String, Object> map = makeCompanyServiceExpiredRows(list, days);
        return new ModelAndView(viewExcel, map);
    }

    @RequestMapping(value = "/overview/companyServiceExpiredPdf", method = RequestMethod.GET)
    public ModelAndView companyServiceExpiredPdf(@RequestParam int days, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<CompanyInfo> list = overviewService.companyServiceExpired(identity.getId(), days);

        PdfView pdfView = new PdfView();

        Map<String, Object> map = makeCompanyServiceExpiredRows(list, days);
        return new ModelAndView(pdfView, map);
    }

    private Map<String, Object> makeCompanyServiceExpiredRows(List<CompanyInfo> list, int days) {
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("fileName", "CompanyService");
        map.put("title", "企业服务到期" + (days > 0 ? "(" + days + "天内)" : ""));
        map.put("headers", new String[]{"简称", "全称", "办公地址", "服务开始时间", "服务结束时间", "值班电话", "入网时间", "备注"});
        map.put("widths", new float[]{10f, 20f, 18f, 10f, 10f, 12f, 10f, 10f});

        List<List<String>> rows = new ArrayList<List<String>>();

        list.forEach(companyInfo -> {
            List<String> row = new ArrayList<>();
            row.add(companyInfo.getShortName());
            row.add(companyInfo.getFullName());
            row.add(companyInfo.getOfficeAddress());
            row.add(DateFormats.toDateString(companyInfo.getServiceStartDate()));
            row.add(DateFormats.toDateString(companyInfo.getServiceEndDate()));
            row.add(companyInfo.getOndutyPhone());
            row.add(DateFormats.toDateString(companyInfo.getCreateTime()));
            row.add(companyInfo.getRemark());

            rows.add(row);
        });


        // for (CompanyInfo info : list) {
        //     List<String> row = new ArrayList<String>();
        //     row.add(info.getShortName());
        //     row.add(info.getFullName());
        //     row.add(info.getOfficeAddress());
        //     row.add(DateFormats.toDateString(info.getServiceStartDate()));
        //     row.add(DateFormats.toDateString(info.getServiceEndDate()));
        //     row.add(info.getOndutyPhone());
        //     row.add(DateFormats.toDateString(info.getCreateTime()));
        //     row.add(info.getRemark());
        //
        //     rows.add(row);
        // }
        map.put("list", rows);
        return map;
    }

    @RequestMapping(value = "/overview/vehicleServiceExpired.form", method = RequestMethod.GET)
    public String vehicleServiceExpired(@RequestParam int days, Model model) {
        model.addAttribute("days", days);
        return "/overview/vehicleServiceExpired.form";
    }

    @RequestMapping(value = "/overview/vehicleServiceExpired", method = RequestMethod.POST)
    @ResponseBody
    public Object VehicleServiceExpired(@RequestParam int days, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return overviewService.vehicleServiceExpired(identity.getId(), days);
    }

    @RequestMapping(value = "/overview/vehicleServiceExpiredExcel", method = RequestMethod.GET)
    public ModelAndView VehicleServiceExpiredExcel(@RequestParam int days, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleServiceExpired(identity.getId(), days);
        ExcelView viewExcel = new ExcelView();

        Map<String, Object> map = makeVehicleServiceExpiredRows(list, days);
        return new ModelAndView(viewExcel, map);
    }

    @RequestMapping(value = "/overview/vehicleServiceExpiredPdf", method = RequestMethod.GET)
    public ModelAndView VehicleServiceExpiredPdf(@RequestParam int days, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleServiceExpired(identity.getId(), days);

        PdfView pdfView = new PdfView();

        Map<String, Object> map = makeVehicleServiceExpiredRows(list, days);
        return new ModelAndView(pdfView, map);
    }

    private Map<String, Object> makeVehicleServiceExpiredRows(List<VehicleInfo> list, int days) {
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("fileName", "VehicleService");
        map.put("title", "车辆服务到期" + (days > 0 ? "(" + days + "天内)" : ""));
        map.put("headers", new String[]{"车牌号", "设备号", "电话号码", "车队", "服务开始时间", "服务结束时间", "安装时间", "备注"});
        map.put("widths", new float[]{15f, 15f, 10f, 20f, 10f, 10f, 10f, 10f});

        List<List<String>> rows = new ArrayList<List<String>>();
        for (VehicleInfo info : list) {
            List<String> row = new ArrayList<String>();
            row.add(info.getPlateNumber());
            row.add(info.getDeviceNumber());
            row.add(info.getPhoneNumber());
            row.add(info.getMotorcade());
            row.add(DateFormats.toDateString(info.getServiceStartDate()));
            row.add(DateFormats.toDateString(info.getServiceEndDate()));
            row.add(DateFormats.toDateString(info.getInstallDate()));
            row.add(info.getRemark());

            rows.add(row);
        }
        map.put("list", rows);
        return map;
    }

    @RequestMapping(value = "/overview/vehicleMaintainExpired.form", method = RequestMethod.GET)
    public String vehicleMaintainExpired(@RequestParam int days, Model model) {
        model.addAttribute("days", days);
        return "/overview/vehicleMaintainExpired.form";
    }

    @RequestMapping(value = "/overview/vehicleMaintainExpired", method = RequestMethod.POST)
    @ResponseBody
    public Object vehicleMaintainExpired(@RequestParam int days, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return overviewService.vehicleMaintainExpired(identity.getId(), days);
    }

    @RequestMapping(value = "/overview/vehicleMaintainExpiredExcel", method = RequestMethod.GET)
    public ModelAndView vehicleMaintainExpiredExcel(@RequestParam int days, HttpServletRequest request,
                                                    HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleMaintainExpired(identity.getId(), days);
        ExcelView viewExcel = new ExcelView();

        Map<String, Object> map = makeVehicleMaintainExpiredRows(list, days);
        return new ModelAndView(viewExcel, map);
    }

    @RequestMapping(value = "/overview/vehicleMaintainExpiredPdf", method = RequestMethod.GET)
    public ModelAndView vehicleMaintainExpiredPdf(@RequestParam int days, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleMaintainExpired(identity.getId(), days);

        PdfView pdfView = new PdfView();

        Map<String, Object> map = makeVehicleMaintainExpiredRows(list, days);
        return new ModelAndView(pdfView, map);
    }

    private Map<String, Object> makeVehicleMaintainExpiredRows(List<VehicleInfo> list, int days) {
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("fileName", "VehicleMaintain");
        map.put("title", "车辆保养到期" + (days > 0 ? "(" + days + "天内)" : ""));
        map.put("headers", new String[]{"车牌号", "设备号", "电话号码", "车队", "安装时间", "保养时间", "备注"});
        map.put("widths", new float[]{15f, 15f, 10f, 20f, 10f, 10f, 10f});

        List<List<String>> rows = new ArrayList<List<String>>();
        for (VehicleInfo info : list) {
            List<String> row = new ArrayList<String>();
            row.add(info.getPlateNumber());
            row.add(info.getDeviceNumber());
            row.add(info.getPhoneNumber());
            row.add(info.getMotorcade());
            row.add(DateFormats.toDateString(info.getInstallDate()));
            row.add(DateFormats.toDateString(info.getNextMaintainDate()));
            row.add(info.getRemark());

            rows.add(row);
        }
        map.put("list", rows);
        return map;
    }

    @RequestMapping(value = "/overview/vehicleAnnualSurveyExpired.form", method = RequestMethod.GET)
    public String vehicleAnnualSurveyExpired(@RequestParam int days, Model model) {
        model.addAttribute("days", days);
        return "/overview/vehicleAnnualSurveyExpired.form";
    }

    @RequestMapping(value = "/overview/vehicleAnnualSurveyExpired", method = RequestMethod.POST)
    @ResponseBody
    public Object vehicleAnnualSurveyExpired(@RequestParam int days, HttpServletRequest request) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return overviewService.vehicleAnnualSurveyExpired(identity.getId(), days);
    }

    @RequestMapping(value = "/overview/vehicleAnnualSurveyExpiredExcel", method = RequestMethod.GET)
    public ModelAndView vehicleAnnualSurveyExpiredExcel(@RequestParam int days, HttpServletRequest request,
                                                        HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleAnnualSurveyExpired(identity.getId(), days);
        ExcelView viewExcel = new ExcelView();

        Map<String, Object> map = makeVehicleAnnualSurveyExpiredRows(list, days);
        return new ModelAndView(viewExcel, map);
    }

    @RequestMapping(value = "/overview/vehicleAnnualSurveyExpiredPdf", method = RequestMethod.GET)
    public ModelAndView vehicleAnnualSurveyExpiredPdf(@RequestParam int days, HttpServletRequest request,
                                                      HttpServletResponse response) throws Exception {
        IdentifyDto identity = (IdentifyDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<VehicleInfo> list = overviewService.vehicleAnnualSurveyExpired(identity.getId(), days);

        PdfView pdfView = new PdfView();

        Map<String, Object> map = makeVehicleAnnualSurveyExpiredRows(list, days);
        return new ModelAndView(pdfView, map);
    }

    private Map<String, Object> makeVehicleAnnualSurveyExpiredRows(List<VehicleInfo> list, int days) {
        Map<String, Object> map = new Hashtable<String, Object>();
        map.put("fileName", "VehicleAnnualSurvey");
        map.put("title", "车辆年检到期" + (days > 0 ? "(" + days + "天内)" : ""));
        map.put("headers", new String[]{"车牌号", "设备号", "电话号码", "车队", "安装时间", "年检时间", "备注"});
        map.put("widths", new float[]{15f, 15f, 10f, 20f, 10f, 10f, 10f});

        List<List<String>> rows = new ArrayList<List<String>>();
        for (VehicleInfo info : list) {
            List<String> row = new ArrayList<String>();
            row.add(info.getPlateNumber());
            row.add(info.getDeviceNumber());
            row.add(info.getPhoneNumber());
            row.add(info.getMotorcade());
            row.add(DateFormats.toDateString(info.getInstallDate()));
            row.add(DateFormats.toDateString(info.getAnnualSurveyDate()));
            row.add(info.getRemark());

            rows.add(row);
        }
        map.put("list", rows);
        return map;
    }
}
