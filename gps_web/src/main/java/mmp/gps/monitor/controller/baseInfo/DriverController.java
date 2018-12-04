package mmp.gps.monitor.controller.baseInfo;

import mmp.gps.common.util.Page;
import mmp.gps.domain.driver.Driver;
import mmp.gps.domain.driver.DriverInfo;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.vehicle.VehicleInfo;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.dao.ICompanyDao;
import mmp.gps.monitor.service.DriverService;
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

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DriverController {
    @Autowired
    private DriverService driverService;

    @Autowired
    private ICompanyDao companyDao;

    @RequiresPermissions("baseinfo.driver")
    @Log(name = "打开驾驶员管理页面")
    @GetMapping(value = "/driver/driver.iframe")
    public String index() {
        return "/baseinfo/driver/driver.iframe";
    }


    @GetMapping(value = "/driver/query")
    @ResponseBody
    public Object query(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        Page<DriverInfo> result = driverService.query(identity.getCompanyId(), filter, page, limit);
        result.code = 0;
        result.count = result.total;
        result.data = result.rows;

        return result;

    }

    @GetMapping("/driver/vehicles")
    @ResponseBody
    public Object vehicles(@RequestParam String driverId) throws Exception {
        Page<VehicleInfo> page = new Page<>();
        page.rows = driverService.assignedVehicles(driverId);
        page.total = page.rows == null ? 0 : page.rows.size();
        return page;
    }

    @PostMapping(value = "/driver/addVehicles")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> addSections(@RequestParam String driverId, @RequestParam String vehicles) throws Exception {
        List<String> list = Arrays.asList(vehicles.split(","));
        // List<String> list = JsonMapper.toObject(vehicles, List.class, String.class);
        driverService.addVehicles(driverId, list);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/driver/removeVehicle")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> removeSection(@RequestParam String driverId, @RequestParam String vehicleId) throws Exception {
        driverService.removeVehicle(driverId, vehicleId);
        return ResponseEntityWrapper.OK();

    }

    @GetMapping(value = "/driver/create.form")
    public String create(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);

        return "/baseinfo/driver/create.form";
    }

    @PostMapping(value = "/driver/create.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> create(@ModelAttribute("driver") @Valid Driver driver, BindingResult binding) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        if (binding.hasErrors())
            // return "/baseinfo/driver/create.form";
            return ResponseEntityWrapper.Failed();
        driver.setCOMPANY(companyDao.fetch(identity.getCompanyId()).getFullName());
        driver.setCompanyId(identity.getCompanyId());
        driverService.create(driver);
        return ResponseEntityWrapper.OK();


    }

    @GetMapping(value = "/driver/edit.form")
    @ResponseBody
    public Object edit(@RequestParam String id, Model model) throws Exception {
        Driver driver = driverService.fetch(id);

        return driver;

    }

    @PostMapping(value = "/driver/edit.form")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> edit(@ModelAttribute("driver") @Valid Driver driver, BindingResult binding) throws Exception {
        if (binding.hasErrors()) {
            List<FieldError> errors = binding.getFieldErrors();
            Map<Object, Object> map = new HashMap<>();
            errors.forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntityWrapper.Failed(map);
        }
        // return "/baseinfo/driver/edit.form";
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        driver.setCompanyId(identity.getCompanyId());
        driverService.update(driver);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/driver/delete")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> delete(@RequestParam String id) throws Exception {
        driverService.delete(id);
        return ResponseEntityWrapper.OK();

    }
}
