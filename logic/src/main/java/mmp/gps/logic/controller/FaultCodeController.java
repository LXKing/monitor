package mmp.gps.logic.controller;


import mmp.gps.common.util.MapUtil;
import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.faultcode.*;
import mmp.gps.logic.service.FaultCodeService;
import mmp.gps.logic.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FaultCodeController {

    @RequestMapping(
            value = {"/faultcode/index"},
            method = {RequestMethod.GET}
    )
    public String index(Model model) {
        model.addAttribute("modes", MapUtil.faultCodeModes());
        return "/faultcode/index";
    }

    @RequestMapping(
            value = {"/faultcode/query"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object query(QueryFaultCodeRequest request) {
        try {
            return FaultCodeService.query(request);
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/faultcode/create.form"},
            method = {RequestMethod.GET}
    )
    public String create(Model model) {
        CreateFaultCodeRequest faultcode = new CreateFaultCodeRequest();
        model.addAttribute("faultcode", faultcode);
        model.addAttribute("levels", MapUtil.faultCodeLevels());
        model.addAttribute("modes", MapUtil.faultCodeModes());
        model.addAttribute("clears", MapUtil.faultCodeClears());
        return "/faultcode/create.form";
    }

    @RequestMapping(
            value = {"/faultcode/create.form"},
            method = {RequestMethod.POST}
    )
    public String create(@ModelAttribute("faultcode") @Valid CreateFaultCodeRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("levels", MapUtil.faultCodeLevels());
            model.addAttribute("modes", MapUtil.faultCodeModes());
            model.addAttribute("clears", MapUtil.faultCodeClears());
            return "/faultcode/create.form";
        } else {
            try {
                FaultCodeService.create(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/faultcode/edit.form"},
            method = {RequestMethod.GET}
    )
    public String edit(FetchFaultCodeRequest request, Model model) throws Exception {
        FetchFaultCodeResponse faultcode = FaultCodeService.fetch(request.getId());
        model.addAttribute("faultcode", faultcode);
        model.addAttribute("levels", MapUtil.faultCodeLevels());
        model.addAttribute("modes", MapUtil.faultCodeModes());
        model.addAttribute("clears", MapUtil.faultCodeClears());
        return "/faultcode/edit.form";
    }

    @RequestMapping(
            value = {"/faultcode/edit.form"},
            method = {RequestMethod.POST}
    )
    public String edit(@ModelAttribute("faultcode") @Valid EditFaultCodeRequest request, BindingResult binding, Model model, @CookieValue String token, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("levels", MapUtil.faultCodeLevels());
            model.addAttribute("modes", MapUtil.faultCodeModes());
            model.addAttribute("clears", MapUtil.faultCodeClears());
            return "/faultcode/edit.form";
        } else {
            try {
                FaultCodeService.update(request);
                WebUtil.success(r);
            } catch (Exception var7) {
                WebUtil.error(r, var7.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/faultcode/delete"},
            method = {RequestMethod.POST}
    )
    public String delete(DeleteFaultCodeRequest request, RedirectAttributes r) {
        try {
            FaultCodeService.delete(request.getId());
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/faultcode/import.form"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object upload(
            @RequestParam(
                    value = "file",
                    required = false
            ) MultipartFile file) {
        AppResponse response = new AppResponse();
        int total = 0;

        try {
            if (file.isEmpty()) {
                response.setErrorMessage("文件不能为空！");
            } else {
                String ex = file.getOriginalFilename().toLowerCase();
                if (!ex.contains(".xls")) {
                    response.setErrorMessage("请上传Excel2003格式的文件！");
                }

                total = FaultCodeService.importExcel2003File(file.getInputStream());
            }
        } catch (Exception var5) {
            response.setErrorMessage(var5.getMessage());
        }

        response.setErrorMessage("文件导入完成，共导入" + total + "行！");
        return response;
    }
}
