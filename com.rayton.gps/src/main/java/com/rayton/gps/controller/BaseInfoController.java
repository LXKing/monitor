package com.rayton.gps.controller;

import com.edata.common.ObjectId;
import com.rayton.gps.aop.ServiceMethod;
import com.rayton.gps.cache.AuthorizeCache;
import com.rayton.gps.model.baseinfo.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BaseInfoController {
    @ServiceMethod(id = "baseinfo", prefix = "打开", name = "基础信息", suffix = "页面")
    @RequestMapping("/baseinfo")
    public String index() {
        return "/baseinfo/index";
    }

    @RequestMapping(value = "/baseinfo/menus", method = RequestMethod.POST)
    @ResponseBody
    public Object menus(HttpServletRequest request) throws Exception {
        // Identity user = (Identity) request.getAttribute("user");

        ArrayList<AdminMenu> menus = new ArrayList<AdminMenu>();

        int count = 0;

        AdminMenu menuAccountAdmin = new AdminMenu();
        menuAccountAdmin.setId(ObjectId.next());
        menuAccountAdmin.setText("帐号管理");
        menuAccountAdmin.setLeaf(false);

        // 这些鬼东西不是应该直接写到数据库然后读出来放到一个类里面吗
        if (AuthorizeCache.hasAuthorized("baseinfo.company", request)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("企业管理");
            menuCompany.setUrl("company/company.iframe");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.role", request)) {
            AdminMenu menuRole = new AdminMenu();
            menuRole.setId(ObjectId.next());
            menuRole.setPid(menuAccountAdmin.getId());
            menuRole.setText("角色管理");
            menuRole.setUrl("role/role.iframe");
            menuRole.setLeaf(true);
            menus.add(menuRole);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.user", request)) {
            AdminMenu menuUser = new AdminMenu();
            menuUser.setId(ObjectId.next());
            menuUser.setPid(menuAccountAdmin.getId());
            menuUser.setText("用户管理");
            menuUser.setUrl("user/user.iframe");
            menuUser.setLeaf(true);
            menus.add(menuUser);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.owner", request)) {
            AdminMenu menuOwner = new AdminMenu();
            menuOwner.setId(ObjectId.next());
            menuOwner.setPid(menuAccountAdmin.getId());
            menuOwner.setText("车主管理");
            menuOwner.setUrl("owner/owner.iframe");
            menuOwner.setLeaf(true);
            menus.add(menuOwner);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.driver", request)) {
            AdminMenu menuDriver = new AdminMenu();
            menuDriver.setId(ObjectId.next());
            menuDriver.setPid(menuAccountAdmin.getId());
            menuDriver.setText("驾驶员管理");
            menuDriver.setUrl("driver/driver.iframe");
            menuDriver.setLeaf(true);
            menus.add(menuDriver);
            count++;
        }

        if (count > 0)
            menus.add(menuAccountAdmin);
        count = 0;

        AdminMenu menuVehicleAdmin = new AdminMenu();
        menuVehicleAdmin.setId(ObjectId.next());
        menuVehicleAdmin.setText("车辆运维");
        menuVehicleAdmin.setLeaf(false);

        if (AuthorizeCache.hasAuthorized("baseinfo.simcard", request)) {
            AdminMenu menuSimcard = new AdminMenu();
            menuSimcard.setId(ObjectId.next());
            menuSimcard.setPid(menuVehicleAdmin.getId());
            menuSimcard.setText("SIM卡管理");
            menuSimcard.setUrl("simcard/simcard.iframe");
            menuSimcard.setLeaf(true);
            menus.add(menuSimcard);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.device", request)) {
            AdminMenu menuDevice = new AdminMenu();
            menuDevice.setId(ObjectId.next());
            menuDevice.setPid(menuVehicleAdmin.getId());
            menuDevice.setText("终端管理");
            menuDevice.setUrl("device/device.iframe");
            menuDevice.setLeaf(true);
            menus.add(menuDevice);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.motorcade", request)) {
            AdminMenu menuMotorcade = new AdminMenu();
            menuMotorcade.setId(ObjectId.next());
            menuMotorcade.setPid(menuVehicleAdmin.getId());
            menuMotorcade.setText("车队管理");
            menuMotorcade.setUrl("motorcade/motorcade.iframe");
            menuMotorcade.setLeaf(true);
            menus.add(menuMotorcade);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.vehicle", request)) {
            AdminMenu menuVehicle = new AdminMenu();
            menuVehicle.setId(ObjectId.next());
            menuVehicle.setPid(menuVehicleAdmin.getId());
            menuVehicle.setText("车辆管理");
            menuVehicle.setUrl("vehicle/vehicle.iframe");
            menuVehicle.setLeaf(true);
            menus.add(menuVehicle);
            count++;
        }

        if (count > 0)
            menus.add(menuVehicleAdmin);
        count = 0;

        AdminMenu menuVehicleMaintain = new AdminMenu();
        menuVehicleMaintain.setId(ObjectId.next());
        menuVehicleMaintain.setText("车辆维护");
        menuVehicleMaintain.setLeaf(false);

        if (AuthorizeCache.hasAuthorized("baseinfo.maintain", request)) {
            AdminMenu menuMaintain = new AdminMenu();
            menuMaintain.setId(ObjectId.next());
            menuMaintain.setPid(menuVehicleMaintain.getId());
            menuMaintain.setText("车辆保养");
            menuMaintain.setUrl("maintain/maintain.iframe");
            menuMaintain.setLeaf(true);
            menus.add(menuMaintain);
            count++;
        }

        if (count > 0)
            menus.add(menuVehicleMaintain);
        count = 0;

        AdminMenu menuMapAdmin = new AdminMenu();
        menuMapAdmin.setId(ObjectId.next());
        menuMapAdmin.setText("地图管理");
        menuMapAdmin.setLeaf(false);

        if (AuthorizeCache.hasAuthorized("baseinfo.circleArea", request)) {
            AdminMenu menuCircle = new AdminMenu();
            menuCircle.setId(ObjectId.next());
            menuCircle.setPid(menuMapAdmin.getId());
            menuCircle.setText("圆形区域");
            menuCircle.setUrl("circleArea/circleArea.iframe");
            menuCircle.setLeaf(true);
            menus.add(menuCircle);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.rectangleArea", request)) {
            AdminMenu menuRectangle = new AdminMenu();
            menuRectangle.setId(ObjectId.next());
            menuRectangle.setPid(menuMapAdmin.getId());
            menuRectangle.setText("矩形区域");
            menuRectangle.setUrl("rectangleArea/rectangleArea.iframe");
            menuRectangle.setLeaf(true);
            menus.add(menuRectangle);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.polygonArea", request)) {
            AdminMenu menuPolygon = new AdminMenu();
            menuPolygon.setId(ObjectId.next());
            menuPolygon.setPid(menuMapAdmin.getId());
            menuPolygon.setText("多边形区域");
            menuPolygon.setUrl("polygonArea/polygonArea.iframe");
            menuPolygon.setLeaf(true);
            menus.add(menuPolygon);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.sectionArea", request)) {
            AdminMenu menuSection = new AdminMenu();
            menuSection.setId(ObjectId.next());
            menuSection.setPid(menuMapAdmin.getId());
            menuSection.setText("路段");
            menuSection.setUrl("sectionArea/sectionArea.iframe");
            menuSection.setLeaf(true);
            menus.add(menuSection);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.routeArea", request)) {
            AdminMenu menuRoute = new AdminMenu();
            menuRoute.setId(ObjectId.next());
            menuRoute.setPid(menuMapAdmin.getId());
            menuRoute.setText("路线");
            menuRoute.setUrl("routeArea/routeArea.iframe");
            menuRoute.setLeaf(true);
            menus.add(menuRoute);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.poi", request)) {
            AdminMenu menuPoi = new AdminMenu();
            menuPoi.setId(ObjectId.next());
            menuPoi.setPid(menuMapAdmin.getId());
            menuPoi.setText("兴趣点");
            menuPoi.setUrl("poi/poi.iframe");
            menuPoi.setLeaf(true);
            menus.add(menuPoi);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.mapOption", request)) {
            AdminMenu menuMapOption = new AdminMenu();
            menuMapOption.setId(ObjectId.next());
            menuMapOption.setPid(menuMapAdmin.getId());
            menuMapOption.setText("地图设置");
            menuMapOption.setUrl("mapOption/mapOption.iframe");
            menuMapOption.setLeaf(true);
            menus.add(menuMapOption);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.mapLayer", request)) {
            AdminMenu menuMapLayer = new AdminMenu();
            menuMapLayer.setId(ObjectId.next());
            menuMapLayer.setPid(menuMapAdmin.getId());
            menuMapLayer.setText("地图图层");
            menuMapLayer.setUrl("mapLayer/mapLayer.iframe");
            menuMapLayer.setLeaf(true);
            menus.add(menuMapLayer);
            count++;
        }

        if (AuthorizeCache.hasAuthorized("baseinfo.marker", request)) {
            AdminMenu menuVehicleMarker = new AdminMenu();
            menuVehicleMarker.setId(ObjectId.next());
            menuVehicleMarker.setPid(menuMapAdmin.getId());
            menuVehicleMarker.setText("车辆图标");
            menuVehicleMarker.setUrl("marker/marker.iframe");
            menuVehicleMarker.setLeaf(true);
            menus.add(menuVehicleMarker);
            count++;
        }

        if (count > 0)
            menus.add(menuMapAdmin);
        count = 0;

        AdminMenu menuSystem = new AdminMenu();
        menuSystem.setId(ObjectId.next());
        menuSystem.setText("系统管理");
        menuSystem.setLeaf(false);

        if (AuthorizeCache.hasAuthorized("baseinfo.dictionary", request)) {
            AdminMenu menuDataDict = new AdminMenu();
            menuDataDict.setId(ObjectId.next());
            menuDataDict.setPid(menuSystem.getId());
            menuDataDict.setText("数据字典");
            menuDataDict.setUrl("dictionary/dictionary.iframe");
            menuDataDict.setLeaf(true);
            menus.add(menuDataDict);
            count++;
        }

        if (count > 0)
            menus.add(menuSystem);

        return menus;
    }
}
