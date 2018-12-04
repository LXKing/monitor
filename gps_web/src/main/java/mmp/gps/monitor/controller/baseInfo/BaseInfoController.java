package mmp.gps.monitor.controller.baseInfo;

import mmp.gps.common.util.ObjectId;
import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.security.RoleAuthorize;
import mmp.gps.monitor.aop.Log;
import mmp.gps.monitor.model.baseinfo.AdminMenu;
import mmp.gps.monitor.service.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseInfoController {


    @Autowired
    private SecurityService securityService;

    @RequiresPermissions("baseinfo")
    @Log(name = "打开基础信息页面")
    @GetMapping("/baseinfo")
    public String index() {
        return "/baseinfo/index";
    }

    @GetMapping(value = "/baseinfo/menus")
    @ResponseBody
    public Object menus() throws Exception {
        // Identity user = (Identity) request.getAttribute("user");

        List<RoleAuthorize> list = securityService.getAllShit();
        IdentityDto user = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<String> roles = securityService.getAllRoles(user);

        ArrayList<AdminMenu> menus = new ArrayList<AdminMenu>();

        int count = 0;
        AdminMenu menuAccountAdmin = new AdminMenu();
        menuAccountAdmin.setId(ObjectId.next());
        menuAccountAdmin.setText("企业资料");
        menuAccountAdmin.setLeaf(false);
        menuAccountAdmin.setIcon("");



        if (securityService.hasAuthorized("baseinfo.company.one", list, roles, user)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("一级管理");
            menuCompany.setUrl("/company/query/one");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.company.two", list, roles, user)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("二级管理");
            menuCompany.setUrl("/company/query/two");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.company.three", list, roles, user)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("三级管理");
            menuCompany.setUrl("/company/query/three");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }


        if (securityService.hasAuthorized("baseinfo.company.four", list, roles, user)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("四级管理");
            menuCompany.setUrl("/company/query/four");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.company.five", list, roles, user)) {
            AdminMenu menuCompany = new AdminMenu();
            menuCompany.setId(ObjectId.next());
            menuCompany.setPid(menuAccountAdmin.getId());
            menuCompany.setText("五级管理");
            menuCompany.setUrl("/company/query/five");
            menuCompany.setLeaf(true);
            menus.add(menuCompany);
            count++;
        }


        if (count > 0)
            menus.add(menuAccountAdmin);
        count = 0;


        AdminMenu menuPositionAdmin = new AdminMenu();
        menuPositionAdmin.setId(ObjectId.next());
        menuPositionAdmin.setText("职员管理");
        menuPositionAdmin.setLeaf(false);


        if (securityService.hasAuthorized("baseinfo.staffRole", list, roles, user)) {
            AdminMenu menustaffRole = new AdminMenu();
            menustaffRole.setId(ObjectId.next());
            menustaffRole.setPid(menuPositionAdmin.getId());
            menustaffRole.setText("职员角色");
            menustaffRole.setUrl("/staffRole/query");
            menustaffRole.setLeaf(true);
            menus.add(menustaffRole);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.positionRole", list, roles, user)) {
            AdminMenu menupositionRole = new AdminMenu();
            menupositionRole.setId(ObjectId.next());
            menupositionRole.setPid(menuPositionAdmin.getId());
            menupositionRole.setText("职位角色");
            menupositionRole.setUrl("/positionRole/query");
            menupositionRole.setLeaf(true);
            menus.add(menupositionRole);
            count++;
        }
        if (count > 0)
            menus.add(menuPositionAdmin);


        AdminMenu menuVehicleAdmin = new AdminMenu();
        menuVehicleAdmin.setId(ObjectId.next());
        menuVehicleAdmin.setText("基础资料");
        menuVehicleAdmin.setLeaf(false);


        if (securityService.hasAuthorized("baseinfo.simcard", list, roles, user)) {
            AdminMenu menuSimcard = new AdminMenu();
            menuSimcard.setId(ObjectId.next());
            menuSimcard.setPid(menuVehicleAdmin.getId());
            menuSimcard.setText("SIM卡管理");
            menuSimcard.setUrl("/simcard/query");
            menuSimcard.setLeaf(true);
            menus.add(menuSimcard);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.device", list, roles, user)) {
            AdminMenu menuDevice = new AdminMenu();
            menuDevice.setId(ObjectId.next());
            menuDevice.setPid(menuVehicleAdmin.getId());
            menuDevice.setText("终端管理");
            menuDevice.setUrl("/device/query");
            menuDevice.setLeaf(true);
            menus.add(menuDevice);
            count++;
        }


        if (securityService.hasAuthorized("baseinfo.vehicle", list, roles, user)) {
            AdminMenu menuVehicle = new AdminMenu();
            menuVehicle.setId(ObjectId.next());
            menuVehicle.setPid(menuVehicleAdmin.getId());
            menuVehicle.setText("车辆管理");
            menuVehicle.setUrl("/vehicle/query");
            menuVehicle.setLeaf(true);
            menus.add(menuVehicle);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.driver", list, roles, user)) {
            AdminMenu menuVehicle = new AdminMenu();
            menuVehicle.setId(ObjectId.next());
            menuVehicle.setPid(menuVehicleAdmin.getId());
            menuVehicle.setText("驾驶员管理");
            menuVehicle.setUrl("/driver/query");
            menuVehicle.setLeaf(true);
            menus.add(menuVehicle);
            count++;
        }
        /*if (securityService.hasAuthorized("baseinfo.simcard")) {
            AdminMenu menuSimcard = new AdminMenu();
            menuSimcard.setId(ObjectId.next());
            menuSimcard.setPid(menuVehicleAdmin.getId());
            menuSimcard.setText("SIM卡管理");
            menuSimcard.setUrl("/simcard/query");
            menuSimcard.setLeaf(true);
            menus.add(menuSimcard);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.device")) {
            AdminMenu menuDevice = new AdminMenu();
            menuDevice.setId(ObjectId.next());
            menuDevice.setPid(menuVehicleAdmin.getId());
            menuDevice.setText("终端管理");
            menuDevice.setUrl("/device/query");
            menuDevice.setLeaf(true);
            menus.add(menuDevice);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.motorcade")) {
            AdminMenu menuMotorcade = new AdminMenu();
            menuMotorcade.setId(ObjectId.next());
            menuMotorcade.setPid(menuVehicleAdmin.getId());
            menuMotorcade.setText("车队管理");
            menuMotorcade.setUrl("/motorcade/query");
            menuMotorcade.setLeaf(true);
            menus.add(menuMotorcade);
            count++;
        }

        if (securityService.hasAuthorized("baseinfo.vehicle")) {
            AdminMenu menuVehicle = new AdminMenu();
            menuVehicle.setId(ObjectId.next());
            menuVehicle.setPid(menuVehicleAdmin.getId());
            menuVehicle.setText("车辆管理");
            menuVehicle.setUrl("/vehicle/query");
            menuVehicle.setLeaf(true);
            menus.add(menuVehicle);
            count++;
        }*/

        if (count > 0)
            menus.add(menuVehicleAdmin);
        count = 0;

        AdminMenu dl = new AdminMenu();
        dl.setId(ObjectId.next());
        dl.setText("证件管理");
        dl.setLeaf(false);

        if (securityService.hasAuthorized("baseinfo.licence.VehicleLicence", list, roles, user)) {
            AdminMenu menuMaintain = new AdminMenu();
            menuMaintain.setId(ObjectId.next());
            menuMaintain.setPid(dl.getId());
            menuMaintain.setText("行驶证");
            menuMaintain.setUrl("/vehicleLicence/query");
            menuMaintain.setLeaf(true);
            menus.add(menuMaintain);
            count++;
        }
        if (securityService.hasAuthorized("baseinfo.licence.VehicleRegister", list, roles, user)) {
            AdminMenu menuMaintain = new AdminMenu();
            menuMaintain.setId(ObjectId.next());
            menuMaintain.setPid(dl.getId());
            menuMaintain.setText("登记证");
            menuMaintain.setUrl("/vehicleRegister/query");
            menuMaintain.setLeaf(true);
            menus.add(menuMaintain);
            count++;
        }
//        if (securityService.hasAuthorized("baseinfo.licence.drivingLicence", list,roles,user)) {
//            AdminMenu menuMaintain = new AdminMenu();
//            menuMaintain.setId(ObjectId.next());
//            menuMaintain.setPid(dl.getId());
//            menuMaintain.setText("准运证");
//            menuMaintain.setUrl("/drivingLicence/query");
//            menuMaintain.setLeaf(true);
//            menus.add(menuMaintain);
//            count++;
//        }
        if (securityService.hasAuthorized("baseinfo.licence.drivingLicence", list, roles, user)) {
            AdminMenu menuMaintain = new AdminMenu();
            menuMaintain.setId(ObjectId.next());
            menuMaintain.setPid(dl.getId());
            menuMaintain.setText("驾驶证");
            menuMaintain.setUrl("/drivingLicence/query");
            menuMaintain.setLeaf(true);
            menus.add(menuMaintain);
            count++;
        }

        if (count > 0)
            menus.add(dl);
        count = 0;

        AdminMenu menuVehicleMaintain = new AdminMenu();
        menuVehicleMaintain.setId(ObjectId.next());
        menuVehicleMaintain.setText("账号管理");
        menuVehicleMaintain.setLeaf(false);

        if (securityService.hasAuthorized("baseinfo.role", list, roles, user)) {
            AdminMenu menuRole = new AdminMenu();
            menuRole.setId(ObjectId.next());
            menuRole.setPid(menuVehicleMaintain.getId());
            menuRole.setText("角色配置");
            menuRole.setUrl("/role/query");
            menuRole.setLeaf(true);
            menus.add(menuRole);
            count++;
        }
        if (securityService.hasAuthorized("baseinfo.user", list, roles, user)) {
            AdminMenu menuUser = new AdminMenu();
            menuUser.setId(ObjectId.next());
            menuUser.setPid(menuVehicleMaintain.getId());
            menuUser.setText("账号信息");
            menuUser.setUrl("/user/query");
            menuUser.setLeaf(true);
            menus.add(menuUser);
            count++;
        }

      /*  if (securityService.hasAuthorized("baseinfo.maintain")) {
            AdminMenu menuMaintain = new AdminMenu();
            menuMaintain.setId(ObjectId.next());
            menuMaintain.setPid(menuVehicleMaintain.getId());
            menuMaintain.setText("车辆保养");
            menuMaintain.setUrl("/maintain/query");
            menuMaintain.setLeaf(true);
            menus.add(menuMaintain);
            count++;
        }*/

        if (count > 0)
            menus.add(menuVehicleMaintain);
        count = 0;

        //
        AdminMenu menuMapAdmin = new AdminMenu();
        menuMapAdmin.setId(ObjectId.next());
        menuMapAdmin.setText("地图");
        menuMapAdmin.setLeaf(false);

        // if (securityService.hasAuthorized("baseinfo.maintain", list,roles,user)) {
        //     AdminMenu menuMaintain = new AdminMenu();
        //     menuMaintain.setId(ObjectId.next());
        //     menuMaintain.setPid(menuMapAdmin.getId());
        //     menuMaintain.setText("车辆保养");
        //     menuMaintain.setUrl("/maintain/query");
        //     menuMaintain.setLeaf(true);
        //     menus.add(menuMaintain);
        //     count++;
        // }
        // if (securityService.hasAuthorized("baseinfo.maintain", list,roles,user)) {
        //     AdminMenu menuMaintain = new AdminMenu();
        //     menuMaintain.setId(ObjectId.next());
        //     menuMaintain.setPid(menuMapAdmin.getId());
        //     menuMaintain.setText("保险信息");
        //     menuMaintain.setUrl("/maintain/query");
        //     menuMaintain.setLeaf(true);
        //     menus.add(menuMaintain);
        //     count++;
        // }

        //        if (securityService.hasAuthorized("baseinfo.circleArea")) {
        //            AdminMenu menuCircle = new AdminMenu();
        //            menuCircle.setId(ObjectId.next());
        //            menuCircle.setPid(menuMapAdmin.getId());
        //            menuCircle.setText("圆形区域");
        //            menuCircle.setUrl("/circleArea/circleArea.iframe");
        //            menuCircle.setLeaf(true);
        //            menus.add(menuCircle);
        //            count++;
        //        }
        //
        //        if (securityService.hasAuthorized("baseinfo.rectangleArea")) {
        //            AdminMenu menuRectangle = new AdminMenu();
        //            menuRectangle.setId(ObjectId.next());
        //            menuRectangle.setPid(menuMapAdmin.getId());
        //            menuRectangle.setText("矩形区域");
        //            menuRectangle.setUrl("rectangleArea/rectangleArea.iframe");
        //            menuRectangle.setLeaf(true);
        //            menus.add(menuRectangle);
        //            count++;
        //        }

        //        if (securityService.hasAuthorized("baseinfo.polygonArea")) {
        //            AdminMenu menuPolygon = new AdminMenu();
        //            menuPolygon.setId(ObjectId.next());
        //            menuPolygon.setPid(menuMapAdmin.getId());
        //            menuPolygon.setText("多边形区域");
        //            menuPolygon.setUrl("polygonArea/polygonArea.iframe");
        //            menuPolygon.setLeaf(true);
        //            menus.add(menuPolygon);
        //            count++;
        //        }
        //
        //        if (securityService.hasAuthorized("baseinfo.sectionArea")) {
        //            AdminMenu menuSection = new AdminMenu();
        //            menuSection.setId(ObjectId.next());
        //            menuSection.setPid(menuMapAdmin.getId());
        //            menuSection.setText("路段");
        //            menuSection.setUrl("sectionArea/sectionArea.iframe");
        //            menuSection.setLeaf(true);
        //            menus.add(menuSection);
        //            count++;
        //        }
        //
        //        if (securityService.hasAuthorized("baseinfo.routeArea")) {
        //            AdminMenu menuRoute = new AdminMenu();
        //            menuRoute.setId(ObjectId.next());
        //            menuRoute.setPid(menuMapAdmin.getId());
        //            menuRoute.setText("路线");
        //            menuRoute.setUrl("routeArea/routeArea.iframe");
        //            menuRoute.setLeaf(true);
        //            menus.add(menuRoute);
        //
        //            count++;
        //        }

      /*  if (securityService.hasAuthorized("baseinfo.poi")) {
            AdminMenu menuPoi = new AdminMenu();
            menuPoi.setId(ObjectId.next());
            menuPoi.setPid(menuMapAdmin.getId());
            menuPoi.setText("兴趣点");
            menuPoi.setUrl("poi/poi.iframe");
            menuPoi.setLeaf(true);
            menus.add(menuPoi);
            count++;
        }*/

        //        if (securityService.hasAuthorized("baseinfo.mapOption")) {
        //            AdminMenu menuMapOption = new AdminMenu();
        //            menuMapOption.setId(ObjectId.next());
        //            menuMapOption.setPid(menuMapAdmin.getId());
        //            menuMapOption.setText("地图设置");
        //            menuMapOption.setUrl("mapOption/mapOption.iframe");
        //            menuMapOption.setLeaf(true);
        //            menus.add(menuMapOption);
        //            count++;
        //        }
        //
        //        if (securityService.hasAuthorized("baseinfo.mapLayer")) {
        //            AdminMenu menuMapLayer = new AdminMenu();
        //            menuMapLayer.setId(ObjectId.next());
        //            menuMapLayer.setPid(menuMapAdmin.getId());
        //            menuMapLayer.setText("地图图层");
        //            menuMapLayer.setUrl("mapLayer/mapLayer.iframe");
        //            menuMapLayer.setLeaf(true);
        //            menus.add(menuMapLayer);
        //            count++;
        //        }

        //        if (securityService.hasAuthorized("baseinfo.marker")) {
        //            AdminMenu menuVehicleMarker = new AdminMenu();
        //            menuVehicleMarker.setId(ObjectId.next());
        //            menuVehicleMarker.setPid(menuMapAdmin.getId());
        //            menuVehicleMarker.setText("车辆图标");
        //            menuVehicleMarker.setUrl("marker/marker.iframe");
        //            menuVehicleMarker.setLeaf(true);
        //            menus.add(menuVehicleMarker);
        //            count++;
        //        }

        if (count > 0)
            menus.add(menuMapAdmin);
        count = 0;

        AdminMenu menuSystem = new AdminMenu();
        menuSystem.setId(ObjectId.next());
        menuSystem.setText("VID管理");
        menuSystem.setLeaf(false);

//      if (securityService.hasAuthorized("baseinfo.dictionary", list,roles,user)) {
//            AdminMenu menuDataDict = new AdminMenu();
//            menuDataDict.setId(ObjectId.next());
//            menuDataDict.setPid(menuSystem.getId());
//            menuDataDict.setText("数据字典");
//            menuDataDict.setUrl("dictionary/dictionary.iframe");
//            menuDataDict.setLeaf(true);
//            menus.add(menuDataDict);
//            count++;
//        }

        if (securityService.hasAuthorized("baseinfo.sysvid", list, roles, user)) {
            AdminMenu sys = new AdminMenu();
            sys.setId(ObjectId.next());
            sys.setPid(menuSystem.getId());
            sys.setText("VID信息");
            sys.setUrl("svid/list");
            sys.setLeaf(true);
            menus.add(sys);
            count++;
        }

        if (count > 0)
            menus.add(menuSystem);

        return menus;
    }
}
