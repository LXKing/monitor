package com.rayton.gps.controller;

import com.rayton.gps.aop.Log;
import com.rayton.gps.model.replay.CountTracks;
import com.rayton.gps.service.ReplayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ReplayController {
    @Autowired
    private ReplayService replayService;


    @RequiresPermissions("center.replay")
    @Log(name = "打开轨迹回放页面")
    @GetMapping("/replay/replay.iframe")
    public String index() {
        return "/center/replay/replay.iframe";
    }

    @GetMapping("/replay/maptools.panel")
    public String maptools() {
        return "/center/replay/maptools.panel";
    }

    @GetMapping(value = "/replay/count" )
    @ResponseBody
    public Object tracksCount(@RequestParam String number, @RequestParam Date start, @RequestParam Date end) throws
            Exception {
        int total = replayService.countTracks(number, start, end);

        return new CountTracks(total);
    }

    @GetMapping(value = "/replay/load" )
    @ResponseBody
    public Object tracksLoad(@RequestParam String number, @RequestParam Date start, @RequestParam Date end,
                             @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return replayService.loadTracks(number, start, end, pageIndex, pageSize);
    }
}
