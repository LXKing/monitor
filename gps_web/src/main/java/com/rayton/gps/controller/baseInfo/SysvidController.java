package com.rayton.gps.controller.baseInfo;


import com.rayton.gps.dao.baseinfo.company.Company;
import com.rayton.gps.dao.baseinfo.company.ICompanyDao;
import com.rayton.gps.dao.baseinfo.device.Device;
import com.rayton.gps.dao.baseinfo.device.IDeviceDao;
import com.rayton.gps.dao.baseinfo.driver.Driver;
import com.rayton.gps.dao.baseinfo.motorcade.Motorcade;
import com.rayton.gps.dao.baseinfo.sim.Simcard;
import com.rayton.gps.dao.baseinfo.sysvid.Sysvid;
import com.rayton.gps.dao.baseinfo.user.User;
import com.rayton.gps.dao.baseinfo.vehicle.Vehicle;
import com.rayton.gps.dao.lisence.DrivingLisence;
import com.rayton.gps.dao.lisence.VehicleLicense;
import com.rayton.gps.dao.lisence.VehicleRegister;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.*;
import com.rayton.gps.service.lisence.*;
import com.rayton.gps.service.sysvid.SysvidService;
import com.rayton.gps.util.Assist;
import com.rayton.gps.util.ResponseEntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class SysvidController {

    @Autowired
    private SysvidService sysvidService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private IDeviceDao deviceDao;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverdlService driverdlService;

    @Autowired
    private ICompanyDao companyDao;

    @Autowired
    private DrivingLisenceService drivingLisenceService;

    @Autowired
    private MotorcadeService motorcadeService;

    @Autowired
    private SimcardService simcardService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DrivervlService drivervlService;
    @Autowired
    private VehicleLicenseService vehicleLicenseService;


    @Autowired
    private DrivervrService drivervrService;

    @Autowired
    private VehicleRegisterService vehicleRegisterService;

    @RequiresPermissions("baseinfo.sysvid")
    @GetMapping("/svid/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String filter, @RequestParam int page, @RequestParam int limit) throws Exception {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        Assist row = new Assist();
        row.setRequires(Assist.andEq("COMID", identity.getCompanyId()));

        Map map = new HashMap();
        map.put("code", 0);
        map.put("count", sysvidService.getSysvidRowCount(row));
        Assist assist = new Assist();
        assist.setRowSize(limit);
        assist.setStartRow((page - 1) * limit);

        if (StringUtils.isNotEmpty(filter)) {
            // assist.setRequires(Assist.andLike("owner_number", filter));

        }
        assist.setRequires(Assist.andEq("COMID", identity.getCompanyId()));
        // assist.setRequires(Assist.andLike("",""));
        // List<DrivingLisence> result = drivingLisenceService.selectDrivingLisence(assist);

        map.put("data", sysvidService.selectSysvid(assist));
        // result.data = result.rows;
        // result.msg = "mmp";
        return map;
    }


    @PostMapping(value = "/svid/add")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> add(Sysvid sysvid) throws Exception {


        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        sysvid.setCOMID(identity.getCompanyId());
        sysvidService.insertSysvid(sysvid);
        return ResponseEntityWrapper.OK();


    }

    @GetMapping("/svid/get")
    @ResponseBody
    public Object get(@RequestParam String id) throws Exception {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        Sysvid sysvid = new Sysvid();
        sysvid.setCOMID(identity.getCompanyId());
        sysvid.setID(Integer.valueOf(id));


        return sysvidService.selectSysvidByObj(sysvid);
    }


    @GetMapping("/svid/getDrivers")
    @ResponseBody
    public Object getDrivers(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Driver> re = new ArrayList<>();

        for (String i : list) {
            Driver driver = driverService.fetch(i);
            if (driver != null) {
                re.add(driverService.fetch(i));
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }

    @GetMapping("/svid/getVehicles")
    @ResponseBody
    public Object getVehicles(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Vehicle> re = new ArrayList<>();

        for (String i : list) {
            Vehicle driver = vehicleService.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getVehicleLicense")
    @ResponseBody
    public Object getVehicleLicense(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<VehicleLicense> re = new ArrayList<>();

        for (String i : list) {
            VehicleLicense driver = vehicleLicenseService.selectVehicleLicenseById(Integer.valueOf(i));
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getVehicleRegister")
    @ResponseBody
    public Object getVehicleRegister(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<VehicleRegister> re = new ArrayList<>();

        for (String i : list) {
            VehicleRegister driver = vehicleRegisterService.selectVehicleRegisterById(Integer.valueOf(i));
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getDrivingLis")
    @ResponseBody
    public Object getDrivingLis(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<DrivingLisence> re = new ArrayList<>();

        for (String i : list) {
            DrivingLisence driver = drivingLisenceService.selectDrivingLisenceById(Integer.valueOf(i));
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getDevice")
    @ResponseBody
    public Object getDevice(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Device> re = new ArrayList<>();

        for (String i : list) {
            Device driver = deviceDao.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getSIM")
    @ResponseBody
    public Object getSIM(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Simcard> re = new ArrayList<>();

        for (String i : list) {
            Simcard driver = simcardService.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getCG")
    @ResponseBody
    public Object getCG(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Motorcade> re = new ArrayList<>();

        for (String i : list) {
            Motorcade driver = motorcadeService.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getCompany")
    @ResponseBody
    public Object getCompany(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<Company> re = new ArrayList<>();

        for (String i : list) {
            Company driver = companyDao.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @GetMapping("/svid/getUser")
    @ResponseBody
    public Object getUser(@RequestParam String id) throws Exception {

        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        List<String> list = Arrays.asList(id.split(","));

        List<User> re = new ArrayList<>();

        for (String i : list) {
            User driver = userService.fetch(i);
            if (driver != null) {
                re.add(driver);
            }

        }

        Map map = new HashMap();
        map.put("total", re.size());
        map.put("code", 0);
        map.put("rows", re);
        return map;
    }


    @PostMapping(value = "/svid/update")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> update(Sysvid sysvid) throws Exception {

        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        sysvid.setCOMID(identity.getCompanyId());
        sysvidService.updateNonEmptySysvidById(sysvid);
        return ResponseEntityWrapper.OK();


    }


    @PostMapping(value = "/svid/remove")
    @ResponseBody
    public ResponseEntity<Map<Object, Object>> remove(@RequestParam String id) throws Exception {
        Assist a = new Assist();
        a.setRequires(Assist.andEq("ID", id));

        sysvidService.deleteSysvid(a);
        return ResponseEntityWrapper.OK();

    }

    @PostMapping(value = "/svid/exist")
    @ResponseBody
    public Object exists(String sysvid) throws Exception {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Assist row = new Assist();
        row.setRequires(Assist.andEq("COMID", identity.getCompanyId()));
        row.setRequires(Assist.andEq("SYSVID", sysvid));


        return sysvidService.selectSysvid(row).size() == 0 ? true : false;
    }

}
