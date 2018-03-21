package com.edata.monitor.controller;

import com.edata.monitor.dao.Page;
import com.edata.monitor.dao.instruct.FeatureInfo;
import com.edata.monitor.dao.instruct.InstructInfo;
import com.edata.monitor.dao.instruct.ParameterInfo;
import com.edata.monitor.dao.security.Identity;
import com.edata.monitor.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class InstructController {
    @Autowired
    private InstructService instructService;

    @RequestMapping(value = "/instruct/features", method = RequestMethod.POST)
    @ResponseBody
    public Object features(@RequestParam String number) throws Exception {
        List<FeatureInfo> features = instructService.features(number);

        return features;
    }

    @RequestMapping(value = "/instruct/params", method = RequestMethod.POST)
    @ResponseBody
    public Object params(@RequestParam String pid, @RequestParam String featureId) throws Exception {
        List<ParameterInfo> features = instructService.parameters(pid, featureId);
        return features;
    }

    @RequestMapping(value = "/instruct/send", method = RequestMethod.POST)
    @ResponseBody
    public Object send(@RequestParam String deviceNumber, @RequestParam String featureId, @RequestParam String
            command, @RequestParam String name, @RequestParam String params, @RequestParam String confirm,
                       HttpServletRequest request) throws Exception {
        String serialNumber = UUID.randomUUID().toString();
        Identity user = (Identity) request.getAttribute("user");
        InstructInfo info = instructService.send(user.getId(), serialNumber, deviceNumber, user.getUnid(), user
                .getName(), command, name, params, confirm);
        return info;
    }

    @RequestMapping(value = "/instruct/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(@RequestParam String deviceNumber, @RequestParam Date start, @RequestParam Date end,
                        @RequestParam int pageIndex, @RequestParam int pageSize, HttpServletRequest request) throws
            Exception {
        Identity identity = (Identity) request.getAttribute("user");
        Page<InstructInfo> page = instructService.query(deviceNumber, identity.getUnid(), start, end, pageIndex,
                pageSize);

        return page;
    }

    @RequestMapping(value = "/instruct/details.form", method = RequestMethod.GET)
    public String details(@RequestParam String id, Model model) throws Exception {
        InstructInfo info = instructService.fetch(id);
        String result = info.getResult().replace("\r\n", "<br />");
        info.setResult(result);
        model.addAttribute("instruct", info);

        return "/instruct/details.form";
    }
}
