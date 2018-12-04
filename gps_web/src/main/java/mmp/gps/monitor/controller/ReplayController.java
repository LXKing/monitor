package mmp.gps.monitor.controller;

import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.model.replay.CountTracks;
import mmp.gps.monitor.service.ReplayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping(value = "/replay/count")
    @ResponseBody
    public Object tracksCount(@RequestParam String number, @RequestParam Date start, @RequestParam Date end) throws Exception {
        int total = replayService.countTracks(number, start, end);

        return new CountTracks(total);
    }

    @PostMapping(value = "/replay/load")
    @ResponseBody
    public Object tracksLoad(@RequestParam String number, @RequestParam Date start, @RequestParam Date end, @RequestParam int pageIndex, @RequestParam int pageSize) throws Exception {
        return replayService.loadTracks(number, start, end, pageIndex, pageSize);
    }
}