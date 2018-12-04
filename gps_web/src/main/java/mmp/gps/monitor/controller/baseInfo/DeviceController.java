package mmp.gps.monitor.controller.baseInfo;

import mmp.gps.common.util.Page;
import mmp.gps.common.util.Tuple;
import mmp.gps.domain.company.CompanyInfoVo;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.device.DeviceInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.user.User;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.IDeviceDao;
import mmp.gps.monitor.model.baseinfo.DeviceModel;
import mmp.gps.monitor.service.CompanyService;
import mmp.gps.monitor.service.DeviceService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CompanyService companyService;

    //
    @Autowired
    private IDeviceDao deviceDao;

    @RequiresPermissions("baseinfo.device")
    @Log(name = "打开终端管理页面")
    @GetMapping("/device/device.iframe")
    public String index() {
        return "/baseinfo/device/device.iframe";
    }

    @GetMapping(value = "/device/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Page<DeviceInfo> result = deviceService.query(identity.getCompanyId(), filter, page, limit);


        result.code = 0;
        result.count = result.total;
        result.data = result.rows;
        return result;
    }

    @PostMapping(value = "/device/search")
    @ResponseBody
    public Object search(@RequestParam(required = false) String filter, @RequestParam int pageIndex, @RequestParam int pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        filter = filter == null ? "" : filter;
        return deviceService.search(identity.getCompanyId(), filter, pageIndex, pageSize);

    }

    @PostMapping(value = "/device/free")
    @ResponseBody
    public Object free(@RequestParam(required = false) String deviceNumber, @RequestParam(required = false) Integer pageIndex, @RequestParam(required = false) Integer pageSize) {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        deviceNumber = deviceNumber == null ? "" : deviceNumber;
        return deviceService.free(identity.getCompanyId());

    }

    @GetMapping(value = "/device/create.form")
    public String create(Model model) {
        DeviceModel m = new DeviceModel();
        m.setEnable(true);
        model.addAttribute("editor", m);
        return "/baseinfo/device/create.form";
    }

    @PostMapping(value = "/device/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("editor") @Valid DeviceModel m, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/device/create.form";

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setEDITUSER(identity.getAccount());
        m.setCompanyId(identity.getCompanyId());
        deviceService.create(m.getDevice(), m.getUser());
        return ResponseEntityWrapper.OK();


    }

    @GetMapping("/device/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Tuple<Device, User> tuple = deviceService.fetch(id);
        DeviceModel m = new DeviceModel();
        m.fill(tuple.e);
        // m.fill(tuple.t);
        return m;
        // model.addAttribute("editor", m);
        // return "/baseinfo/device/edit.form";
    }

    @PostMapping(value = "/device/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("editor") @Valid DeviceModel m, BindingResult binding) {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/device/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        m.setEDITUSER(identity.getAccount());
        m.setCompanyId(identity.getCompanyId());
        deviceService.update(m.getDevice(), m.getUser());
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/device/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id, RedirectAttributes r) {
        deviceService.delete(id);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/device/exist")
    @ResponseBody
    public Object exists(@RequestParam String deviceNumber, @RequestParam(required = false) String id, @RequestParam boolean checkId) throws Exception {
        return checkId ? !deviceService.exist(deviceNumber, id) : !deviceService.exist(deviceNumber);

    }

    @GetMapping(value = "/device/get")
    @ResponseBody
    public Object g(@RequestParam String deviceNumber) throws Exception {
        return deviceDao.getByNum(deviceNumber);

    }

    @GetMapping(value = "/device/getD")
    @ResponseBody
    public Object gD(@RequestParam String deviceNumber) throws Exception {
        return deviceDao.getD(deviceNumber);

    }

    @PostMapping(value = "/device/getVid", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getVid() throws Exception {

        return getGuid();

    }

    @PostMapping(value = "/device/getClique", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getClique() throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        CompanyInfoVo companyInfoVo = companyService.getMmp(identity.getCompanyId());
        if (identity.kind == 0) {
            companyInfoVo.setNow("");
        } else {
            companyInfoVo.setNow(identity.getCompanyId());
        }
        System.out.println(companyInfoVo);
        return companyInfoVo;

    }

    @PostMapping(value = "/device/getAllCompany", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getAllCompany(@RequestParam String id) throws Exception {
        List<CompanyInfoVo> companyInfoVo = companyService.getCompanyByPid(id);
        return companyInfoVo;

    }


    /**
     * 20位末尾的数字id
     */
    public static int Guid = 100;

    public static String getGuid() {

        Guid += 1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran = 0;
        if (Guid > 999) {
            Guid = 100;
        }
        ran = Guid;

        return time + info.substring(2, info.length()) + ran;
    }
}
