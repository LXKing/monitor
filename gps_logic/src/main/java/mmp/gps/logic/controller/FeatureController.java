package mmp.gps.logic.controller;


import mmp.gps.common.util.MapUtil;
import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.feature.*;
import mmp.gps.domain.parameter.*;
import mmp.gps.logic.service.FeatureService;
import mmp.gps.logic.service.ParameterService;
import mmp.gps.logic.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FeatureController {

    @Autowired
    private FeatureService featureService;
    @Autowired
    private ParameterService parameterService;


    @RequestMapping(
            value = {"/feature/index"},
            method = {RequestMethod.GET}
    )
    public String index(Model model) {
        model.addAttribute("protocolKinds", MapUtil.protocolKinds());
        return "/feature/index";
    }

    @RequestMapping(
            value = {"/feature/list"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object list(ListFeatureRequest request) {
        try {
            return this.featureService.list(request.getKind());
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/feature/create.form"},
            method = {RequestMethod.GET}
    )
    public String create(Model model) {
        CreateFeatureRequest feature = new CreateFeatureRequest();
        model.addAttribute("feature", feature);
        model.addAttribute("protocolKinds", MapUtil.protocolKinds());
        return "/feature/create.form";
    }

    @RequestMapping(
            value = {"/feature/create.form"},
            method = {RequestMethod.POST}
    )
    public String create(@ModelAttribute("feature") @Valid CreateFeatureRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("protocolKinds", MapUtil.protocolKinds());
            return "/feature/create.form";
        } else {
            try {
                this.featureService.create(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/feature/edit.form"},
            method = {RequestMethod.GET}
    )
    public String edit(FetchFeatureRequest request, @CookieValue String token, Model model) throws Exception {
        FetchFeatureResponse feature = this.featureService.fetch(request.getId());
        model.addAttribute("feature", feature);
        model.addAttribute("protocolKinds", MapUtil.protocolKinds());
        return "/feature/edit.form";
    }

    @RequestMapping(
            value = {"/feature/edit.form"},
            method = {RequestMethod.POST}
    )
    public String edit(@ModelAttribute("feature") @Valid EditFeatureRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("protocolKinds", MapUtil.protocolKinds());
            return "/feature/edit.form";
        } else {
            try {
                this.featureService.update(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/feature/delete"},
            method = {RequestMethod.POST}
    )
    public String delete(DeleteFeatureRequest request, RedirectAttributes r) {
        try {
            this.featureService.delete(request.getId());
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/feature/parameters"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object parameters(ListParameterRequest request) {
        try {
            return this.parameterService.list(request.getFeatureId());
        } catch (Exception var3) {
            return null;
        }
    }

    @RequestMapping(
            value = {"/feature/createParameter.form"},
            method = {RequestMethod.GET}
    )
    public String createParameter(@RequestParam("pid") String pid, @RequestParam("featureId") String featureId, Model model) {
        CreateParameterRequest request = new CreateParameterRequest();
        request.setPid(pid);
        request.setFeatureId(featureId);
        model.addAttribute("parameter", request);
        model.addAttribute("types", MapUtil.parameterTypes());
        return "/feature/createParameter.form";
    }

    @RequestMapping(
            value = {"/feature/createParameter.form"},
            method = {RequestMethod.POST}
    )
    public String createParameter(@ModelAttribute("parameter") @Valid CreateParameterRequest request, BindingResult binding, Model model, @CookieValue String token, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("types", MapUtil.parameterTypes());
            return "/feature/createParameter.form";
        } else {
            try {
                this.parameterService.create(request);
                WebUtil.success(r);
            } catch (Exception var7) {
                WebUtil.error(r, var7.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/feature/editParameter.form"},
            method = {RequestMethod.GET}
    )
    public String editParameter(FetchParameterRequest request, Model model) throws Exception {
        FetchParameterResponse parameter = this.parameterService.fetch(request.getId());
        model.addAttribute("parameter", parameter);
        model.addAttribute("types", MapUtil.parameterTypes());
        return "/feature/editParameter.form";
    }

    @RequestMapping(
            value = {"/feature/editParameter.form"},
            method = {RequestMethod.POST}
    )
    public String editParameter(@ModelAttribute("parameter") @Valid EditParameterRequest request, BindingResult binding, Model model, RedirectAttributes r) {
        if (binding.hasErrors()) {
            model.addAttribute("types", MapUtil.parameterTypes());
            return "/feature/editParameter.form";
        } else {
            try {
                this.parameterService.update(request);
                WebUtil.success(r);
            } catch (Exception var6) {
                WebUtil.error(r, var6.getMessage());
            }

            return "redirect:/result";
        }
    }

    @RequestMapping(
            value = {"/feature/deleteParameter"},
            method = {RequestMethod.POST}
    )
    public String deleteParameter(DeleteParameterRequest request, RedirectAttributes r) {
        try {
            this.parameterService.delete(request.getId());
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/feature/copyParameter"},
            method = {RequestMethod.POST}
    )
    public String copyParameter(DeleteParameterRequest request, RedirectAttributes r) {
        try {
            this.parameterService.copy(request.getId());
            WebUtil.success(r);
        } catch (Exception var4) {
            WebUtil.error(r, var4.getMessage());
        }

        return "redirect:/result";
    }

    @RequestMapping(
            value = {"/feature/moveParameter"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object moveParameter(MoveParameterRequest request) {
        AppResponse response = new AppResponse();

        try {
            this.parameterService.move(request.getId(), request.getPid());
        } catch (Exception var4) {
            response.setErrorMessage(var4.getMessage());
        }

        return response;
    }
}
