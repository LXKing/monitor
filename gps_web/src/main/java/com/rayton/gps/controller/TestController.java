package com.rayton.gps.controller;


import com.rayton.gps.dao.MOTO2CDao;
import com.rayton.gps.dao.baseinfo.company.Company;
import com.rayton.gps.dao.baseinfo.company.ICompanyDao;
import com.rayton.gps.dao.baseinfo.device.Device;
import com.rayton.gps.dao.baseinfo.device.IDeviceDao;
import com.rayton.gps.dao.baseinfo.motorcade.IMotorcadeDao;
import com.rayton.gps.dao.baseinfo.motorcade.Motorcade;
import com.rayton.gps.dao.baseinfo.vehicle.IVehicleDao;
import com.rayton.gps.dao.baseinfo.vehicle.Vehicle;
import com.rayton.gps.dao.security.IdentityDto;
import com.rayton.gps.service.CompanyService;
import com.rayton.gps.service.DeviceService;
import com.rayton.gps.service.MotorcadeService;
import com.rayton.gps.service.StatisticsService;
import com.rayton.gps.util.AnotherBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class TestController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MotorcadeService motorcadeService;

    @Autowired
    private IVehicleDao vehicleDao;

    @Autowired
    private IDeviceDao deviceDao;

    @Autowired
    private IMotorcadeDao motorcadeDao;

    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private MOTO2CDao moto2CDao;

    //
    @GetMapping(value = "/admin")
    public ModelAndView test() {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("name", identity.getName());
        return modelAndView;
    }

    @GetMapping(value = "/multi")
    public ModelAndView multi() {
        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("multi");
        modelAndView.addObject("name", identity.getName());
        return modelAndView;
    }

    @GetMapping(value = "/ins")
    public ModelAndView download() throws Exception {

        ModelAndView modelAndView = new ModelAndView("ins");
        return modelAndView;

    }

    @GetMapping(value = "/monitor")
    public ModelAndView monitor() throws Exception {

        ModelAndView modelAndView = new ModelAndView("monitor");
        return modelAndView;

    }

    @GetMapping(value = "/report")
    public ModelAndView report() throws Exception {

        ModelAndView modelAndView = new ModelAndView("report");
        return modelAndView;

    }

    @GetMapping(value = "/replay")
    public ModelAndView replay() throws Exception {

        ModelAndView modelAndView = new ModelAndView("replay");
        return modelAndView;

    }


    @GetMapping(value = "/a")
    public ModelAndView a() throws Exception {

        ModelAndView modelAndView = new ModelAndView("a");
        return modelAndView;

    }

    @PostMapping(value = "/data")
    public void data(AnotherBean json) throws Exception {
        List<AnotherBean.Client> client = json.getClient();
        List<AnotherBean.Device> device = json.getDevice();
        List<AnotherBean.Group> groups = json.getGroup();


        Map<String, List<String>> car2group = new HashMap<>();
        Map<String, List<String>> group2car = new HashMap<>();


        if (client != null && client.size() != 0) {


            for (AnotherBean.Client c : client) {
                Company company = new Company();
                company.setEMAIL(c.email);
                company.setId(c.id);
                company.setShortName(c.clientName);
                company.setPID(c.pid);
                company.setParentVisible(true);
                company.setCorporation(c.person);
                company.setOfficeAddress(c.address);
                company.setFullName(c.company);
                company.setOrganCode("");
                company.setOndutyPhone("");
                company.setRegistAddress("");
                company.setEditTime(new Timestamp(new Date().getTime()));


                companyDao.create(company);

            }


        }
        if (device != null && device.size() != 0) {


            device.forEach(dev -> {

                Device d = new Device();
                d.setId(dev.msId);
                d.setIMEI(dev.MSName);
                d.setModel(dev.mSType);
                d.setCompanyId(dev.clientId);
                d.setEditTime(new Timestamp(new Date().getTime()));
                d.setSensors("");
                d.setHasMicrophone(true);
                d.setHasNavigation(true);
                d.setCameras((byte) 99);
                d.setFactoryNumber("");
                d.setFactoryName("");
                d.setDeviceNumber(dev.msId);
                d.setSimcardId(dev.msId);


                List<String> stringArray = Arrays.asList(dev.grpList);
                car2group.put(dev.msId, stringArray);


                deviceDao.create(d);


                Vehicle vehicle = new Vehicle();
                vehicle.setId(dev.msId);
                vehicle.setCompanyId(dev.clientId);
                vehicle.setDeviceId(dev.msId);
                vehicle.setMotorcadeId("");
                vehicle.setPlateNumber("");
                vehicle.setPlateColor("");


                vehicleDao.create(vehicle);


            });


        }


        if (groups != null && groups.size() != 0) {


            groups.forEach(g -> {

                Motorcade motorcade = new Motorcade();

                motorcade.setId(g.grpId);
                motorcade.setName(g.grpName);
                motorcade.setType(g.grpType);
                motorcade.setParentVisible(true);
                motorcade.setPID(g.grpPId);
                motorcade.setCompanyId(g.clientId);
                motorcade.setLinkMan("");
                motorcade.setPhone("");
                motorcade.setRemark("");
                motorcade.setEditTime(new Timestamp(new Date().getTime()));

                List<String> stringArray = Arrays.asList(g.msList);
                group2car.put(g.grpId, stringArray);


                motorcadeService.create(motorcade);
            });


        }


        group2car.forEach((k, v) -> v.forEach(id -> vehicleDao.addGC(id, k)));
        group2car.forEach((k, v) -> v.forEach(id -> moto2CDao.add(k, id)));
        car2group.forEach((k, v) -> v.forEach(id -> moto2CDao.add(id, k)));


    }

}
