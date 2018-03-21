package com.edata.monitor.controller;

import com.edata.monitor.aop.ServiceMethod;
import com.edata.monitor.replay.CountTracks;
import com.edata.monitor.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ReplayController {
    @Autowired
    private ReplayService replayService;

    @ServiceMethod(id = "center.replay", pid = "center", prefix = "打开", name = "轨迹回放", suffix = "页面")
    @RequestMapping("/replay/replay.iframe")
    public String index() {
        return "/center/replay/replay.iframe";
    }

    @RequestMapping("/replay/maptools.panel")
    public String maptools() {
        return "/center/replay/maptools.panel";
    }

    @RequestMapping(value = "/replay/count", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object tracksCount(@RequestParam String number, @RequestParam Date start, @RequestParam Date end) throws
            Exception {
        int total = replayService.countTracks(number, start, end);

        return new CountTracks(total);
    }

    @RequestMapping(value = "/replay/load", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object tracksLoad(@RequestParam String number, @RequestParam Date start, @RequestParam Date end,
                             @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return replayService.loadTracks(number, start, end, pageIndex, pageSize);
    }
}
