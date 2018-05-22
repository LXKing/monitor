package com.rayton.gps.controller;


import com.rayton.gps.common.ObjectId;
import com.rayton.gps.model.baseinfo.AdminMenu;
import com.rayton.gps.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ReportController {


    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/report/menus")
    @ResponseBody
    public Object menus() throws Exception {

        ArrayList<AdminMenu> menus = new ArrayList<AdminMenu>();

        int count = 0;

        AdminMenu menuDrivingBehaviorAnalysis = new AdminMenu();
        menuDrivingBehaviorAnalysis.setId(ObjectId.next());
        menuDrivingBehaviorAnalysis.setText("报表管理");
        menuDrivingBehaviorAnalysis.setLeaf(false);

        //    if (securityService.hasAuthorized("statistics.sectionOverspeed")) {
        AdminMenu menuOverspeed = new AdminMenu();
        menuOverspeed.setId(ObjectId.next());
        menuOverspeed.setPid(menuDrivingBehaviorAnalysis.getId());
        menuOverspeed.setText("超速报表");
        menuOverspeed.setUrl("/report/OverspeedCount");
        menuOverspeed.setLeaf(true);
        menus.add(menuOverspeed);
        count++;
        //    }

        AdminMenu menuParking = new AdminMenu();
        menuParking.setId(ObjectId.next());
        menuParking.setPid(menuDrivingBehaviorAnalysis.getId());
        menuParking.setText("报表停留");
        menuParking.setUrl("/report/ParkingCount");
        menuParking.setLeaf(true);
        menus.add(menuParking);
        count++;

        AdminMenu menuACC = new AdminMenu();
        menuACC.setId(ObjectId.next());
        menuACC.setPid(menuDrivingBehaviorAnalysis.getId());
        menuACC.setText("ACC报表");
        menuACC.setUrl("/report/ACCCount");
        menuACC.setLeaf(true);
        menus.add(menuACC);
        count++;

        AdminMenu menuTrip = new AdminMenu();
        menuTrip.setId(ObjectId.next());
        menuTrip.setPid(menuDrivingBehaviorAnalysis.getId());
        menuTrip.setText("行程报表");
        menuTrip.setUrl("/report/TripCount");
        menuTrip.setLeaf(true);
        menus.add(menuTrip);
        count++;

        AdminMenu menuDriving = new AdminMenu();
        menuDriving.setId(ObjectId.next());
        menuDriving.setPid(menuDrivingBehaviorAnalysis.getId());
        menuDriving.setText("行驶记录表");
        menuDriving.setUrl("/report/DrivingCount");
        menuDriving.setLeaf(true);
        menus.add(menuDriving);
        count++;

        //    if (securityService.hasAuthorized("statistics.sectionOverspeed")) {
        //      AdminMenu menuSectionOverspeed = new AdminMenu();
        //      menuSectionOverspeed.setId(ObjectId.next());
        //      menuSectionOverspeed.setPid(menuDrivingBehaviorAnalysis.getId());
        //      menuSectionOverspeed.setText("车辆路段超速");
        //      menuSectionOverspeed.setUrl("/statistics/sectionOverspeedCount");
        //      menuSectionOverspeed.setLeaf(true);
        //      menus.add(menuSectionOverspeed);
        //      count++;
        //    }

        //    if (securityService.hasAuthorized("statistics.areaOverspeed")) {
        //      AdminMenu menuAreaOverspeed = new AdminMenu();
        //      menuAreaOverspeed.setId(ObjectId.next());
        //      menuAreaOverspeed.setPid(menuDrivingBehaviorAnalysis.getId());
        //      menuAreaOverspeed.setText("车辆区域超速");
        //      menuAreaOverspeed.setUrl("/statistics/areaOverspeedCount");
        //      menuAreaOverspeed.setLeaf(true);
        //      menus.add(menuAreaOverspeed);
        //      count++;
        //    }
        //
        //    if (securityService.hasAuthorized("statistics.timeoutParking")) {
        //      AdminMenu menuTimeoutParking = new AdminMenu();
        //      menuTimeoutParking.setId(ObjectId.next());
        //      menuTimeoutParking.setPid(menuDrivingBehaviorAnalysis.getId());
        //      menuTimeoutParking.setText("车辆停车超时");
        //      menuTimeoutParking.setUrl("/statistics/timeoutParkingCount");
        //      menuTimeoutParking.setLeaf(true);
        //      menus.add(menuTimeoutParking);
        //      count++;
        //    }

        if (count > 0) {
            menus.add(menuDrivingBehaviorAnalysis);
        }
        count = 0;

        return menus;
    }

}
