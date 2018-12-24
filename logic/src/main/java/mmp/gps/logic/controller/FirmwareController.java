package mmp.gps.logic.controller;


import mmp.gps.common.util.MapUtil;
import mmp.gps.domain.firmware.*;
import mmp.gps.logic.service.FirmwareService;
import mmp.gps.logic.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FirmwareController {

    @Autowired
    private FirmwareService firmwareService;


    @RequestMapping(
            value = {"/firmware/index"},
            method = {RequestMethod.GET}
    )
    public String index() {
        return "/firmware/index";
    }

    @RequestMapping(
            value = {"/firmware/list"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object list(ListFirmwareInfoRequest request) {
        try {
            return this.firmwareService.list(request.getFilter(), request.getPage(), request.getPageSize());
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/firmware/create.form"},
            method = {RequestMethod.GET}
    )
    public String create(Model model) {
        CreateFirmwareRequest firmware = new CreateFirmwareRequest();
        model.addAttribute("firmware", firmware);
        model.addAttribute("factories", MapUtil.factories());
        return "/firmware/create.form";
    }

    @RequestMapping(
            value = {"/firmware/create.form"},
            method = {RequestMethod.POST}
    )
    public String create(@ModelAttribute("firmware") @Valid CreateFirmwareRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("factories", MapUtil.factories());
            return "/firmware/create.form";
        } else {
            try {
                this.firmwareService.create(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/firmware/edit.form"},
            method = {RequestMethod.GET}
    )
    public String edit(FetchFirmwareRequest request, Model model) throws Exception {
        FetchFirmwareResponse firmware = this.firmwareService.fetch(request.getId());
        model.addAttribute("firmware", firmware);
        model.addAttribute("factories", MapUtil.factories());
        return "/firmware/edit.form";
    }

    @RequestMapping(
            value = {"/firmware/edit.form"},
            method = {RequestMethod.POST}
    )
    public String edit(@ModelAttribute("firmware") @Valid EditFirmwareRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("factories", MapUtil.factories());
            return "/firmware/edit.form";
        } else {
            try {
                this.firmwareService.update(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/firmware/delete"},
            method = {RequestMethod.POST}
    )
    public String delete(DeleteFirmwareRequest request, RedirectAttributes r) {
        try {
            this.firmwareService.delete(request.getId());
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/firmware/upload.form"},
            method = {RequestMethod.GET}
    )
    public String upload(@RequestParam("id") String id, Model model) throws Exception {
        UploadFirmwareRequest request = new UploadFirmwareRequest();
        request.setId(id);
        model.addAttribute("firmware", request);
        return "/firmware/upload.form";
    }

    @RequestMapping(
            value = {"/firmware/upload.form"},
            method = {RequestMethod.POST}
    )
    public String upload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file, RedirectAttributes r) {
        try {
            if (file.isEmpty()) {
                WebUtil.error(r, "文件不能为空！");
            } else {
                byte[] ex = file.getBytes();
                this.firmwareService.upload(id, ex);
                WebUtil.success(r);
            }
        } catch (Exception var5) {
            WebUtil.error(r, var5.getMessage());
        }

        return "redirect:/result";
    }
}
