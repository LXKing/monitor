/*
Navicat MySQL Data Transfer

Source Server         : 60.205.226.132
Source Server Version : 50614
Source Host           : 60.205.226.132:3306
Source Database       : monitordb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-03-16 11:20:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `alarm`
-- ----------------------------
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `GPSTIME` datetime NOT NULL COMMENT 'GPS时间',
  `SERVERTIME` datetime NOT NULL COMMENT '服务器时间',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `ALT` smallint(6) unsigned NOT NULL COMMENT '海拔',
  `SPEED` float unsigned NOT NULL COMMENT '速度',
  `ANGLE` smallint(6) unsigned NOT NULL COMMENT '方向',
  `ALARMS` int(11) unsigned NOT NULL COMMENT '报警',
  `STATUS` int(11) unsigned NOT NULL COMMENT '状态',
  `MILEAGE` double unsigned NOT NULL COMMENT '里程',
  `OILMASS` float unsigned NOT NULL COMMENT '油量',
  `VSS` float unsigned NOT NULL COMMENT '车速',
  `OVAREATYPE` tinyint(3) unsigned NOT NULL COMMENT '超速区域类型',
  `OVAREAID` int(10) unsigned NOT NULL COMMENT '超速区域id',
  `IOAREATYPE` tinyint(3) unsigned NOT NULL COMMENT '进出区域类型',
  `IOAREAID` int(10) unsigned NOT NULL COMMENT '进出区域id',
  `IOAREAFLAG` tinyint(4) NOT NULL COMMENT '进出区域方向',
  `ROUTEID` int(10) unsigned NOT NULL COMMENT '路段id',
  `ROUTESECONDS` smallint(5) unsigned NOT NULL COMMENT '路段行驶时间',
  `ROUTEFLAG` tinyint(4) NOT NULL COMMENT '路段结果',
  `ALARMID` smallint(5) unsigned NOT NULL COMMENT '报警确人ID',
  `EXSTATUS` int(10) unsigned NOT NULL COMMENT '扩展车辆信号状态位',
  `IOSTATUS` smallint(5) unsigned NOT NULL COMMENT 'IO状态位',
  `AD0` smallint(5) unsigned NOT NULL COMMENT '模拟量AD0',
  `AD1` smallint(5) unsigned NOT NULL COMMENT '模拟量AD1',
  `NETWORK` tinyint(3) unsigned NOT NULL COMMENT '网络强度',
  `SATELLITES` tinyint(3) unsigned NOT NULL COMMENT '卫星数量',
  `FROM` tinyint(4) NOT NULL COMMENT '来源,0:终端,1:平台',
  `VALID` bit(1) NOT NULL COMMENT '是否有效',
  `ISHANDLED` bit(1) NOT NULL COMMENT '处理否',
  `USERID` char(24) DEFAULT NULL COMMENT '用户id',
  `USERNAME` varchar(20) DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime DEFAULT NULL COMMENT '处理时间',
  `USERCONFIRM` int(10) unsigned DEFAULT NULL COMMENT '用户确认警类型',
  `USERMETHOD` varchar(30) DEFAULT NULL COMMENT '处理方式',
  `USERREMARK` varchar(50) DEFAULT NULL COMMENT '处理内容',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER_GPSTIME` (`DEVICENUMBER`,`GPSTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='报警表，用于记录设备所有报警，以及人工处理结果';

-- ----------------------------
-- Records of alarm
-- ----------------------------

-- ----------------------------
-- Table structure for `areaindevice`
-- ----------------------------
DROP TABLE IF EXISTS `areaindevice`;
CREATE TABLE `areaindevice` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `AREAID` int(10) unsigned NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `TIME` datetime NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`DEVICENUMBER`,`AREAID`,`AREATYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备';

-- ----------------------------
-- Records of areaindevice
-- ----------------------------
INSERT INTO `areaindevice` VALUES ('13311012699', '1', '1', '2018-02-23 10:37:41');
INSERT INTO `areaindevice` VALUES ('13311012700', '1', '1', '2018-02-23 10:37:41');

-- ----------------------------
-- Table structure for `areaindevicelog`
-- ----------------------------
DROP TABLE IF EXISTS `areaindevicelog`;
CREATE TABLE `areaindevicelog` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `AREAID` int(10) unsigned NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `ACTION` tinyint(4) NOT NULL COMMENT '操作类型',
  `UNID` char(36) NOT NULL COMMENT '用户全局唯一编号',
  `USER` varchar(20) NOT NULL COMMENT '用户姓名',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `ACKTIME` (`ACKTIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备的操作日志，以便与设备同步';

-- ----------------------------
-- Records of areaindevicelog
-- ----------------------------
INSERT INTO `areaindevicelog` VALUES ('0c36b632-de08-4e34-9995-59856dd8acd0', '13311012700', '1', '1', '1', '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:42', null);
INSERT INTO `areaindevicelog` VALUES ('e7d5eb71-94ab-4875-9790-5d4a649d609d', '13311012699', '1', '1', '1', '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:41', null);

-- ----------------------------
-- Table structure for `areainmaplayer`
-- ----------------------------
DROP TABLE IF EXISTS `areainmaplayer`;
CREATE TABLE `areainmaplayer` (
  `MAPLAYERID` char(24) NOT NULL COMMENT '地图图层id',
  `AREAID` int(10) unsigned NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  PRIMARY KEY (`MAPLAYERID`,`AREAID`,`AREATYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地图图层覆盖物，用于圆形区域、矩形区域、多边形区域、路线、兴趣点绑定到地图图层';

-- ----------------------------
-- Records of areainmaplayer
-- ----------------------------

-- ----------------------------
-- Table structure for `circle`
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) unsigned NOT NULL COMMENT '属性',
  `LAT` decimal(9,6) NOT NULL COMMENT '中心点纬度',
  `LNG` decimal(9,6) NOT NULL COMMENT '中心点经度',
  `RADIUS` int(10) unsigned NOT NULL COMMENT '半径',
  `MAXSPEED` smallint(5) unsigned NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) unsigned NOT NULL COMMENT '超速持续时间',
  `STARTTIME` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='圆形区域';

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '558ffc6603c70e31a2a53a30', 'test111', '', '16859', '37.891270', '85.164946', '561082', '0', '0', '2018-02-23 10:37:00', '2018-02-24 10:37:00', '', '2018-02-23 10:37:31');

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID` char(24) NOT NULL COMMENT '唯一编号',
  `FULLNAME` varchar(50) NOT NULL COMMENT '全称',
  `SHORTNAME` varchar(30) NOT NULL COMMENT '简称',
  `ORGANCODE` varchar(20) NOT NULL COMMENT '组织机构编号',
  `CORPORATION` varchar(20) NOT NULL COMMENT '法人代表',
  `ONDUTYPHONE` varchar(15) NOT NULL COMMENT '24小时值班电话',
  `OFFICEADDRESS` varchar(50) NOT NULL COMMENT '办公地址',
  `REGISTADDRESS` varchar(50) NOT NULL COMMENT '注册地址',
  `PARENTVISIBLE` bit(1) NOT NULL COMMENT '上级可见否',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `REGISTDATE` date DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司资料';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('558ffc6603c70e31a2a53a30', '中国兵器工业北方通用电子', '兵工北方通用', '', '', '', '', '', '', '2015-08-29 11:18:53', '2015-08-29');
INSERT INTO `company` VALUES ('5a72ab073d769a75b6309dec', '深圳锐讯易通科技有限公司', '锐讯易通', '', '', '', '', '', '', '2018-02-01 13:52:07', '2018-02-01');

-- ----------------------------
-- Table structure for `companyauthorize`
-- ----------------------------
DROP TABLE IF EXISTS `companyauthorize`;
CREATE TABLE `companyauthorize` (
  `COMPANYID` char(24) NOT NULL COMMENT '企业ID',
  `PERMISSIONID` varchar(100) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`,`PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业授权表';

-- ----------------------------
-- Records of companyauthorize
-- ----------------------------
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.authorize');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPois');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePoi');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapOption');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker.remove');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker.save');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.authorize');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addSections');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addVehicles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeSection');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.addMonitors');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.addRoles');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.removeMonitor');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.removeRole');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addDrivers');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addOwners');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.create');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.delete');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeDriver');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeOwner');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.update');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.accidentDoubtLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.deviceEventReport');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.drivingRecorder');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.locateLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.loginLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimedia');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimediaEvent');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimediaRetrieval');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.parameterLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.powerLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.speedLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.speedStatusLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.timeoutLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.locate');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.datalog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.instruct');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.vehileinfo');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'center.replay');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyinfo');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyKey');

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `SIMCARDID` char(24) NOT NULL COMMENT 'sim卡号唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `MODEL` varchar(20) NOT NULL COMMENT '型号',
  `FACTORYNAME` varchar(20) NOT NULL COMMENT '厂家',
  `FACTORYNUMBER` varchar(20) NOT NULL COMMENT '出厂号',
  `CAMERAS` tinyint(4) NOT NULL COMMENT '摄像头路数',
  `HASMICROPHONE` bit(1) NOT NULL COMMENT '有无麦克风',
  `HASNAVIGATION` bit(1) NOT NULL COMMENT '有无导航屏',
  `SENSORS` varchar(50) NOT NULL COMMENT '传感器列表',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `WARRANTY` date DEFAULT NULL COMMENT '保修期',
  `PURCHASEDATE` date DEFAULT NULL COMMENT '购买日期',
  `INSTALLDATE` date DEFAULT NULL COMMENT '安装日期',
  `AUTHCODE` char(24) DEFAULT NULL COMMENT '鉴权码',
  `REGISTTIME` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DEVICENUMBER` (`DEVICENUMBER`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE,
  KEY `SIMCARDNUMBER` (`SIMCARDID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('5a7417fc3d769a7dcc878d5c', '558ffc6603c70e31a2a53a30', '5a7417dd3d769a7dcc878d5b', '13311012700', '星XXX', '联XXX', '', '0', '', '', '', '2018-02-02 16:05:01', '2018-02-02', '2018-02-02', '2018-02-02', null, null);
INSERT INTO `device` VALUES ('5aa7419b7b03680b92edf011', '558ffc6603c70e31a2a53a30', '5a72ac0c3d769a75b6309def', '10189415505', '星XXX', '联XXX', '', '0', '', '', '', '2018-03-13 16:07:10', null, null, null, null, null);

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `KIND` int(11) NOT NULL COMMENT '类型',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `PID` int(11) unsigned DEFAULT NULL COMMENT '父级编号',
  `CODE` varchar(20) DEFAULT NULL COMMENT '代码',
  `INDEX` varchar(20) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`ID`),
  KEY `KIND` (`KIND`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', '1', '蓝色', '2015-07-29 22:02:26', null, '1', '1');
INSERT INTO `dictionary` VALUES ('2', '1', '黄色', '2015-07-29 22:03:44', null, '2', '2');
INSERT INTO `dictionary` VALUES ('3', '1', '黑色', '2015-07-29 22:04:23', null, '3', '3');
INSERT INTO `dictionary` VALUES ('4', '1', '白色', '2015-07-29 22:04:44', null, '4', '4');
INSERT INTO `dictionary` VALUES ('5', '1', '其他', '2015-07-29 22:04:59', null, '9', '9');
INSERT INTO `dictionary` VALUES ('6', '3', '客车', '2015-07-30 10:50:45', null, '10', '10');
INSERT INTO `dictionary` VALUES ('7', '3', '大型客车', '2015-07-30 10:50:45', '6', '11', '11');
INSERT INTO `dictionary` VALUES ('8', '3', '中型客车', '2015-07-30 10:50:45', '6', '12', '12');
INSERT INTO `dictionary` VALUES ('9', '3', '小型客车', '2015-07-30 10:50:45', '6', '13', '13');
INSERT INTO `dictionary` VALUES ('10', '3', '轿车', '2015-07-30 10:50:45', '6', '14', '14');
INSERT INTO `dictionary` VALUES ('11', '3', '大型卧铺客车', '2015-07-30 10:50:45', '6', '15', '15');
INSERT INTO `dictionary` VALUES ('12', '3', '中型卧铺客车', '2015-07-30 10:50:45', '6', '16', '16');
INSERT INTO `dictionary` VALUES ('13', '3', '普通货车', '2015-07-30 10:50:45', null, '20', '20');
INSERT INTO `dictionary` VALUES ('14', '3', '大型普通货车', '2015-07-30 10:50:45', '13', '21', '21');
INSERT INTO `dictionary` VALUES ('16', '3', '中型普通货车', '2015-07-30 10:50:45', '13', '22', '22');
INSERT INTO `dictionary` VALUES ('17', '3', '小型普通货车', '2015-07-30 10:50:45', '13', '23', '23');
INSERT INTO `dictionary` VALUES ('18', '3', '专用运输车', '2015-07-30 10:50:45', null, '30', '30');
INSERT INTO `dictionary` VALUES ('19', '3', '集装箱车', '2015-07-30 10:50:45', '18', '31', '31');
INSERT INTO `dictionary` VALUES ('20', '3', '大件运输车', '2015-07-30 10:50:45', '18', '32', '32');
INSERT INTO `dictionary` VALUES ('21', '3', '保温冷藏车', '2015-07-30 10:50:45', '18', '33', '33');
INSERT INTO `dictionary` VALUES ('22', '3', '商品车运输专用车辆', '2015-07-30 10:50:45', '18', '34', '34');
INSERT INTO `dictionary` VALUES ('23', '3', '罐车', '2015-07-30 10:50:45', '18', '35', '35');
INSERT INTO `dictionary` VALUES ('24', '3', '牵引车', '2015-07-30 10:50:45', '18', '36', '36');
INSERT INTO `dictionary` VALUES ('25', '3', '挂车', '2015-07-30 10:50:45', '18', '37', '37');
INSERT INTO `dictionary` VALUES ('26', '3', '平板车', '2015-07-30 10:50:45', '18', '38', '38');
INSERT INTO `dictionary` VALUES ('27', '3', '其他专用车', '2015-07-30 10:50:45', '18', '39', '39');
INSERT INTO `dictionary` VALUES ('28', '3', '危险品运输车', '2015-07-30 10:50:45', null, '40', '40');
INSERT INTO `dictionary` VALUES ('29', '3', '农用车', '2015-07-30 10:50:45', null, '50', '50');
INSERT INTO `dictionary` VALUES ('30', '3', '拖拉车', '2015-07-30 10:50:45', null, '60', '60');
INSERT INTO `dictionary` VALUES ('31', '3', '轮式拖拉车', '2015-07-30 10:50:45', '30', '61', '61');
INSERT INTO `dictionary` VALUES ('32', '3', '手扶拖拉机', '2015-07-30 10:50:45', '30', '62', '62');
INSERT INTO `dictionary` VALUES ('33', '3', '履带拖拉机', '2015-07-30 10:50:45', '30', '63', '63');
INSERT INTO `dictionary` VALUES ('34', '3', '特种拖拉机', '2015-07-30 10:50:45', '30', '64', '64');
INSERT INTO `dictionary` VALUES ('35', '3', '其他车辆', '2015-07-30 10:50:45', null, '90', '90');
INSERT INTO `dictionary` VALUES ('36', '2', '白色', '2015-07-30 10:55:50', null, '', '白色');
INSERT INTO `dictionary` VALUES ('37', '2', '红色', '2015-07-30 10:55:51', null, '', '红色');
INSERT INTO `dictionary` VALUES ('38', '2', '绿色', '2015-07-30 10:55:52', null, '', '绿色');
INSERT INTO `dictionary` VALUES ('39', '2', '黑色', '2015-07-30 10:55:58', null, '', '黑色');
INSERT INTO `dictionary` VALUES ('40', '2', '蓝色', '2015-07-30 10:55:56', null, '', '蓝色');
INSERT INTO `dictionary` VALUES ('41', '4', '客车', '2015-07-30 11:49:10', null, '', '1');
INSERT INTO `dictionary` VALUES ('42', '4', '货车', '2015-07-30 11:49:24', null, '', '2');
INSERT INTO `dictionary` VALUES ('43', '4', '小轿车', '2015-07-30 11:49:39', null, '', '3');
INSERT INTO `dictionary` VALUES ('44', '4', '船', '2015-07-30 11:50:03', null, '', '4');
INSERT INTO `dictionary` VALUES ('45', '4', '摩托车', '2015-07-30 11:50:22', null, '', '5');
INSERT INTO `dictionary` VALUES ('46', '5', '北京市', '2015-07-30 11:51:55', null, '110000', '110000');
INSERT INTO `dictionary` VALUES ('47', '5', '天津市', '2015-07-30 11:52:14', null, '120000', '120000');
INSERT INTO `dictionary` VALUES ('48', '5', '河北省', '2015-07-30 11:52:39', null, '130000', '130000');
INSERT INTO `dictionary` VALUES ('49', '5', '山西省', '2015-07-30 11:52:58', null, '140000', '140000');
INSERT INTO `dictionary` VALUES ('50', '5', '内蒙古自治区', '2015-07-30 11:53:32', null, '150000', '150000');
INSERT INTO `dictionary` VALUES ('51', '5', '辽宁省', '2015-07-30 11:53:52', null, '210000', '210000');
INSERT INTO `dictionary` VALUES ('52', '5', '吉林省', '2015-07-30 11:54:07', null, '220000', '220000');
INSERT INTO `dictionary` VALUES ('53', '5', '黑龙江省', '2015-07-30 11:54:27', null, '230000', '230000');
INSERT INTO `dictionary` VALUES ('54', '5', '上海市', '2015-07-30 11:54:44', null, '310000', '310000');
INSERT INTO `dictionary` VALUES ('55', '5', '江苏省', '2015-07-30 11:55:02', null, '320000', '320000');
INSERT INTO `dictionary` VALUES ('56', '5', '浙江省', '2015-07-30 11:55:17', null, '330000', '330000');
INSERT INTO `dictionary` VALUES ('57', '5', '安徽省', '2015-07-30 11:55:38', null, '340000', '340000');
INSERT INTO `dictionary` VALUES ('58', '5', '福建省', '2015-07-30 11:55:56', null, '350000', '350000');
INSERT INTO `dictionary` VALUES ('59', '5', '江西省', '2015-07-30 11:56:13', null, '360000', '360000');
INSERT INTO `dictionary` VALUES ('60', '5', '山东省', '2015-07-30 11:56:30', null, '370000', '370000');
INSERT INTO `dictionary` VALUES ('61', '5', '河南省', '2015-07-30 11:56:47', null, '410000', '410000');
INSERT INTO `dictionary` VALUES ('62', '5', '湖北省', '2015-07-30 11:57:02', null, '420000', '420000');
INSERT INTO `dictionary` VALUES ('63', '5', '湖南省', '2015-07-30 11:57:20', null, '430000', '430000');
INSERT INTO `dictionary` VALUES ('64', '5', '广东省', '2015-07-30 11:57:36', null, '440000', '440000');
INSERT INTO `dictionary` VALUES ('65', '5', '广西壮族自治区', '2015-07-30 11:58:02', null, '450000', '450000');
INSERT INTO `dictionary` VALUES ('66', '5', '海南省', '2015-07-30 11:58:30', null, '460000', '460000');
INSERT INTO `dictionary` VALUES ('67', '5', '重庆市', '2015-07-30 11:58:47', null, '500000', '500000');
INSERT INTO `dictionary` VALUES ('68', '5', '四川省', '2015-07-30 11:59:12', null, '510000', '510000');
INSERT INTO `dictionary` VALUES ('69', '5', '贵州省', '2015-07-30 11:59:33', null, '520000', '520000');
INSERT INTO `dictionary` VALUES ('70', '5', '云南省', '2015-07-30 11:59:52', null, '530000', '530000');
INSERT INTO `dictionary` VALUES ('71', '5', '藏族自治区', '2015-07-30 12:00:18', null, '540000', '540000');
INSERT INTO `dictionary` VALUES ('72', '5', '陕西省', '2015-07-30 12:00:35', null, '610000', '610000');
INSERT INTO `dictionary` VALUES ('73', '5', '甘肃省', '2015-07-30 12:01:02', null, '620000', '620000');
INSERT INTO `dictionary` VALUES ('74', '5', '青海省', '2015-07-30 12:01:26', null, '630000', '630000');
INSERT INTO `dictionary` VALUES ('75', '5', '宁夏回族自治区', '2015-07-30 12:02:12', null, '640000', '640000');
INSERT INTO `dictionary` VALUES ('76', '5', '新疆维吾尔族自治区', '2015-07-30 12:02:44', null, '650000', '650000');
INSERT INTO `dictionary` VALUES ('77', '5', '台湾省', '2015-07-30 12:03:12', null, '710000', '710000');
INSERT INTO `dictionary` VALUES ('78', '5', '香港特别行政区', '2015-07-30 12:03:42', null, '720000', '720000');
INSERT INTO `dictionary` VALUES ('79', '5', '澳门特别行政区', '2015-07-30 12:04:06', null, '730000', '730000');
INSERT INTO `dictionary` VALUES ('80', '6', '12V', '2015-07-30 22:59:11', null, '', '1');
INSERT INTO `dictionary` VALUES ('81', '6', '24V', '2015-07-30 22:59:20', null, '', '2');
INSERT INTO `dictionary` VALUES ('82', '7', '呼入', '2015-07-31 11:08:06', null, '', '1');
INSERT INTO `dictionary` VALUES ('83', '7', '呼出', '2015-07-31 11:08:17', null, '', '2');
INSERT INTO `dictionary` VALUES ('84', '7', '呼入呼出', '2015-07-31 11:08:37', null, '', '3');
INSERT INTO `dictionary` VALUES ('85', '9', '联XXX', '2015-08-15 10:03:58', '0', '', '1');
INSERT INTO `dictionary` VALUES ('86', '9', '翰XXX', '2015-08-15 10:04:05', '0', '', '2');
INSERT INTO `dictionary` VALUES ('87', '9', '世XXX', '2015-08-15 10:04:12', '0', '', '3');
INSERT INTO `dictionary` VALUES ('88', '8', '星XXX', '2015-08-15 10:06:29', '0', '', '1');
INSERT INTO `dictionary` VALUES ('89', '8', 'XA-TY-101', '2015-07-31 15:39:22', '88', '', '11');
INSERT INTO `dictionary` VALUES ('90', '8', 'XA-BJ-101', '2015-07-31 15:41:11', '88', '', '12');
INSERT INTO `dictionary` VALUES ('91', '8', '翰XXX', '2015-08-15 10:08:15', '0', '', '2');
INSERT INTO `dictionary` VALUES ('92', '8', 'HS-BB-454', '2015-07-31 15:40:44', '91', '', '21');
INSERT INTO `dictionary` VALUES ('93', '10', '巡逻队', '2015-07-31 20:25:02', null, '', '1');
INSERT INTO `dictionary` VALUES ('94', '10', '后勤运输队', '2015-07-31 20:25:27', null, '', '2');
INSERT INTO `dictionary` VALUES ('95', '11', '日常保养', '2015-08-01 14:06:30', null, '', '1');
INSERT INTO `dictionary` VALUES ('96', '11', '专业保养', '2015-08-01 14:06:55', null, '', '2');
INSERT INTO `dictionary` VALUES ('97', '12', '加油站', '2015-08-05 18:28:58', null, '', '1');
INSERT INTO `dictionary` VALUES ('98', '12', '收费站', '2015-08-05 18:29:08', null, '', '2');
INSERT INTO `dictionary` VALUES ('99', '12', '停车场', '2015-08-05 18:29:20', null, '', '3');
INSERT INTO `dictionary` VALUES ('100', '13', '身份证', '2015-08-06 15:10:10', null, '', '1');
INSERT INTO `dictionary` VALUES ('101', '13', '驾驶证', '2015-08-06 15:10:19', null, '', '2');
INSERT INTO `dictionary` VALUES ('102', '13', '学生证', '2015-08-06 15:10:29', null, '', '3');
INSERT INTO `dictionary` VALUES ('103', '8', 'HS-BJ-454', '2015-08-15 10:31:05', '91', '', '22');
INSERT INTO `dictionary` VALUES ('104', '14', '通知客户', '2015-08-28 08:33:49', null, '', '1');
INSERT INTO `dictionary` VALUES ('105', '14', '没联系上', '2015-08-28 08:34:08', null, '', '2');
INSERT INTO `dictionary` VALUES ('106', '14', '设备故障', '2015-08-28 08:34:23', null, '', '3');
INSERT INTO `dictionary` VALUES ('107', '14', '修车', '2015-08-28 08:34:33', null, '', '4');
INSERT INTO `dictionary` VALUES ('108', '14', '不处理', '2015-08-28 08:34:42', null, '', '5');
INSERT INTO `dictionary` VALUES ('109', '14', '将来处理', '2015-08-28 08:34:57', null, '', '6');
INSERT INTO `dictionary` VALUES ('110', '14', '其他', '2015-08-28 08:35:06', null, '', '7');
INSERT INTO `dictionary` VALUES ('111', '13', '工作证', '2015-11-09 17:29:55', null, '', '4');

-- ----------------------------
-- Table structure for `driver`
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `SEX` char(2) NOT NULL COMMENT '性别',
  `PHONE` varchar(20) NOT NULL COMMENT '电话',
  `IDTYPE` varchar(30) NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(20) NOT NULL COMMENT '证件号',
  `DRIVINGLICENSENUMBER` varchar(20) NOT NULL COMMENT '驾驶证号',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `DRIVINGLICENSEEXPIRYDATE` date DEFAULT NULL COMMENT '驾驶证有效日期',
  `PHOTO` blob COMMENT '相片',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='司机资料';

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('5a72ae0c3d769a75b6309e67', '558ffc6603c70e31a2a53a30', '杨先生', '男', '', '身份证', '511333290314302455', 'C01921', '', '2018-02-01 14:05:00', null, null);
INSERT INTO `driver` VALUES ('5a7419353d769a7dcc878df5', '558ffc6603c70e31a2a53a30', '李先生', '男', '', '', '', '39021', '', '2018-02-02 15:54:29', null, null);

-- ----------------------------
-- Table structure for `eventmenu`
-- ----------------------------
DROP TABLE IF EXISTS `eventmenu`;
CREATE TABLE `eventmenu` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `EVENTID` tinyint(3) unsigned NOT NULL COMMENT '类型',
  `CONTENT` varchar(30) NOT NULL COMMENT '事件内容',
  PRIMARY KEY (`DEVICENUMBER`,`EVENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件菜单';

-- ----------------------------
-- Records of eventmenu
-- ----------------------------

-- ----------------------------
-- Table structure for `eventreport`
-- ----------------------------
DROP TABLE IF EXISTS `eventreport`;
CREATE TABLE `eventreport` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `EVENTID` tinyint(4) NOT NULL COMMENT '事件id',
  `CONTENT` varchar(30) NOT NULL COMMENT '事件内容',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='事件报告';

-- ----------------------------
-- Records of eventreport
-- ----------------------------

-- ----------------------------
-- Table structure for `info`
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '信息类型',
  `CONTENT` text NOT NULL COMMENT '信息内容',
  `STARTTIME` datetime NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL COMMENT '结束时间',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `USERTIME` datetime NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE,
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`) USING BTREE,
  KEY `STARTTIME_ENDTIME` (`STARTTIME`,`ENDTIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息表';

-- ----------------------------
-- Records of info
-- ----------------------------

-- ----------------------------
-- Table structure for `infomenu`
-- ----------------------------
DROP TABLE IF EXISTS `infomenu`;
CREATE TABLE `infomenu` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '类型',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息菜单';

-- ----------------------------
-- Records of infomenu
-- ----------------------------

-- ----------------------------
-- Table structure for `infomenuindevice`
-- ----------------------------
DROP TABLE IF EXISTS `infomenuindevice`;
CREATE TABLE `infomenuindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '信息类型',
  `NAME` varchar(30) NOT NULL COMMENT '信息名称',
  `ACTION` tinyint(3) unsigned NOT NULL COMMENT '操作',
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息菜单设置';

-- ----------------------------
-- Records of infomenuindevice
-- ----------------------------

-- ----------------------------
-- Table structure for `infoservice`
-- ----------------------------
DROP TABLE IF EXISTS `infoservice`;
CREATE TABLE `infoservice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '信息类型',
  `CONTENT` text NOT NULL COMMENT '信息内容',
  `STARTTIME` datetime NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL COMMENT '结束时间',
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '发送时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `STARTTIME_ENDTIME` (`STARTTIME`,`ENDTIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息服务';

-- ----------------------------
-- Records of infoservice
-- ----------------------------

-- ----------------------------
-- Table structure for `infosubscribe`
-- ----------------------------
DROP TABLE IF EXISTS `infosubscribe`;
CREATE TABLE `infosubscribe` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `INFOTYPE` tinyint(4) NOT NULL COMMENT '信息类型',
  PRIMARY KEY (`DEVICENUMBER`,`INFOTYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息订阅';

-- ----------------------------
-- Records of infosubscribe
-- ----------------------------

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `TIME` datetime NOT NULL COMMENT '时间',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) NOT NULL COMMENT '用户编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户姓名',
  `OPERATION` varchar(50) NOT NULL COMMENT '操作',
  KEY `TIME_COMPANYID` (`TIME`,`COMPANYID`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('2015-11-16 11:12:28', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开统计分析页面');
INSERT INTO `log` VALUES ('2015-11-16 11:12:29', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开监控中心页面');
INSERT INTO `log` VALUES ('2015-11-16 11:12:30', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开实时监控页面');
INSERT INTO `log` VALUES ('2015-11-26 10:33:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2015-11-26 10:34:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');





INSERT INTO `log` VALUES ('2018-02-01 16:04:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:05:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:08:51', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:09:04', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:05', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:09', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:13', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:14', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:15', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:16', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:18', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:09:19', '5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'rayton', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:10:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:10:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:11:21', '558ffc6603c70e31a2a53a30', '5a72ac863d769a75b6309e09', '13311012699', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:11:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:11:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:11:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:11:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:12:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:12:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:12:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:12:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:12:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-02-01 16:13:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:36', '558ffc6603c70e31a2a53a30', '5a72ae2a3d769a75b6309e68', 'rayton01', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:13:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:13:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:14:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:14:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:15:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:15:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:15:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:15:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:16:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:18:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:18:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开历史上线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开历史在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开操作日志页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开进出区域统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:19:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线偏离统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:20:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆停车超时统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:20:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆区域超速统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开历史上线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开历史在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开当前在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开行驶里程及油耗统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆警情统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线偏离统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:22:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开进出区域统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:24:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:24:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:24:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:24:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:25:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:26:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:26:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:27:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:28:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:28:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:28:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:31:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:31:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:34:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:34:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:35:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:35:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:37:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:37:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开行驶里程及油耗统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:38:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开当前在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:39:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:42:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:42:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:43:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:43:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:43:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:43:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:44:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:44:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:46:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 16:46:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:46:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:52:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:52:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 16:57:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:57:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-01 16:57:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 16:57:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 16:57:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:11:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:11:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:11:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:12:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:12:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 17:12:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:12:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:13:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:13:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:16:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-01 17:16:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:17:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:17:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:17:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:17:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:18:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:18:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-02-01 17:19:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:19:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:20:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-01 17:20:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-02-01 17:20:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:20:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:21:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 17:21:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:22:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:22:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:22:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:23:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:23:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:27:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:28:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 17:28:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:28:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:28:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:30:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-02-01 17:31:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:31:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 17:31:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:31:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 17:32:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:32:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 17:34:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-01 17:35:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-01 17:35:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:35:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-02-01 17:36:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 17:36:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 17:36:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-02-01 17:36:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:36:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-02-01 17:37:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-02-01 17:37:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-01 17:37:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:37:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:38:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:38:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:38:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:38:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:38:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:39:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:40:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-01 17:41:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 17:41:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 18:10:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:10:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:13:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:13:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:14:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:14:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:15:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:15:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:21:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:21:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:23:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:23:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:25:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:25:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:25:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-01 18:25:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-02-01 18:26:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-01 18:27:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的用户');
INSERT INTO `log` VALUES ('2018-02-01 18:27:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-01 18:27:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-01 18:28:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-01 18:28:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:28:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:29:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:29:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-01 18:30:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-01 18:30:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 09:00:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 09:00:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 09:00:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 09:00:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 09:00:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 09:00:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 09:01:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 09:01:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 09:22:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 09:22:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:38:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 10:38:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:38:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:38:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:38:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 10:39:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:39:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:40:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据查询页面');
INSERT INTO `log` VALUES ('2018-02-02 10:40:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '查询车辆资料');
INSERT INTO `log` VALUES ('2018-02-02 10:40:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-02-02 10:41:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:41:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:42:06', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 10:42:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:42:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:43:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:43:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:43:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:43:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:44:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 10:44:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:44:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 10:44:38', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:44:38', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 10:45:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-02-02 10:45:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 10:45:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 11:02:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-02-02 11:21:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:106.113.122.111');
INSERT INTO `log` VALUES ('2018-02-02 11:21:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 11:21:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 11:25:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 11:25:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 11:25:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 11:25:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 11:27:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 11:27:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 11:27:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 11:30:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 11:30:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-02 11:30:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 11:30:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:18:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:23:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-02 14:39:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:39:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:39:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:56:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:56:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:56:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:57:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:57:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:57:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:57:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:57:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:57:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:58:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 14:58:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:58:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 14:59:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 14:59:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:59:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 14:59:24', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 14:59:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:00:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:00:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:00:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:00:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:00:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:01:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:01:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:02:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:02:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:02:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:02:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:02:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:03:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-02-02 15:03:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:03:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:03:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:03:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:04:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:27:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:27:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:27:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:27:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:34:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 15:34:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:34:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:06', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:11', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:40:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:46:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:48:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:48:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-02-02 15:49:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-02-02 15:49:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:50:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-02-02 15:50:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:50:14', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:51', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-02-02 15:51:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:51:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:52:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:52:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:52:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:36', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:39', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:53:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:02', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-02-02 15:54:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-02-02 15:54:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:54:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-02 15:54:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-02-02 15:54:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-02-02 15:55:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-02-02 15:55:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:55:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:06', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-02 15:56:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:28', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:56:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-02-02 15:57:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-02 15:57:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:57:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:58:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:58:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:58:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-02 15:59:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据查询页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 15:59:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:00:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-02 16:00:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-02 16:00:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:00:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:00:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:01:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-02 16:01:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:01:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '查询车辆资料');
INSERT INTO `log` VALUES ('2018-02-02 16:01:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:01:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-02-02 16:02:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:38', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:39', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-02-02 16:02:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:02:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:07', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-02 16:03:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:03:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:03:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-02-02 16:04:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-02-02 16:04:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:04:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-02-02 16:05:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改车主资料');
INSERT INTO `log` VALUES ('2018-02-02 16:05:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:05:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:28', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:05:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '修改sim卡');
INSERT INTO `log` VALUES ('2018-02-02 16:06:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '修改sim卡');
INSERT INTO `log` VALUES ('2018-02-02 16:06:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:06:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:06:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:06:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-02-02 16:06:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-02-02 16:06:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:06:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:07:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:07:20', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:07:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:08:47', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:08:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:09:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:09:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:11:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:11:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:11:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:11:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:12:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-02-02 16:12:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:12:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:12:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-02 16:12:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:12:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:12:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:12:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:16:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:20:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:20:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:20:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:23:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:23:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:24:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 16:24:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:24:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:26:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:28:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:28:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 16:28:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:28:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:29:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:30:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:30:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 16:30:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 16:33:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:29:38', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:29:39', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:36', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:37:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-02 17:43:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:43:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:43:39', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.210');
INSERT INTO `log` VALUES ('2018-02-02 17:43:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:43:43', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:45:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:45:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:45:51', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:45:51', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:46:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:47:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:47:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:47:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:47:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:48:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:49:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-02 17:49:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-02 17:49:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开数据查询页面');
INSERT INTO `log` VALUES ('2018-02-05 09:35:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-05 09:35:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:35:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:36:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 09:37:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 09:37:18', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:37:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:37:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 09:37:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:37:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:37:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:37:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:38:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:38:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:38:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:38:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:38:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:39:11', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:39:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:45:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 09:49:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 09:49:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:22', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 09:50:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:50:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:53:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:53:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 09:53:35', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 09:53:35', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 10:36:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 10:36:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 10:36:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 10:37:28', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 10:37:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 10:37:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 11:01:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:43', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-05 11:01:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-02-05 11:27:36', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 11:27:36', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 11:30:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 11:30:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 11:30:47', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 14:27:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 14:40:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 14:40:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-05 14:40:51', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-05 17:40:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-05 17:40:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 17:40:37', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 17:42:07', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 18:18:24', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-05 18:18:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 18:18:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-05 18:19:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-05 18:19:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 08:51:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-06 08:51:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 08:51:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 08:52:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '指令下发');
INSERT INTO `log` VALUES ('2018-02-06 08:53:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-06 08:53:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-06 08:53:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-06 09:31:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-06 09:31:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-06 09:31:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:31:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:31:22', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-06 09:31:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-06 09:32:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:32:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:32:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:32:19', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:33:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-06 09:33:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:33:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:34:14', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:34:14', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:36:33', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:36:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:39:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:192.168.1.107');
INSERT INTO `log` VALUES ('2018-02-06 09:39:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:39:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:40:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:40:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:40:24', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:40:24', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:45:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:45:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:46:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:46:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 09:46:07', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 09:46:07', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 10:16:22', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开数据查询页面');
INSERT INTO `log` VALUES ('2018-02-06 10:16:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 10:16:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 10:24:21', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '数据调试');
INSERT INTO `log` VALUES ('2018-02-06 10:24:23', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '数据调试');
INSERT INTO `log` VALUES ('2018-02-06 10:51:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 10:51:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 10:56:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:05:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:05:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:14:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:14:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:16:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:17:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:14.145.145.100');
INSERT INTO `log` VALUES ('2018-02-06 11:17:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:17:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 11:17:09', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:17:14', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:51', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:19:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:20:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 11:20:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:20:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 11:20:45', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:21:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:23:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:24:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-06 11:24:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开数据查询页面');
INSERT INTO `log` VALUES ('2018-02-06 11:24:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开多媒体数据页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开参数修改日志页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:11', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开位置记录页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:30', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开速度记录页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开事件报告页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开升级结果报告页面');
INSERT INTO `log` VALUES ('2018-02-06 11:25:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '查询车辆资料');
INSERT INTO `log` VALUES ('2018-02-06 14:20:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-06 14:21:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-02-06 14:21:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '指令下发');
INSERT INTO `log` VALUES ('2018-02-23 10:31:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-23 10:33:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-23 10:34:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-23 10:34:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:43', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:46', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:36:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:03', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:05', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:06', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:32', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '创建圆形区域');
INSERT INTO `log` VALUES ('2018-02-23 10:37:43', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-02-23 10:37:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:37:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:38:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-02-23 10:38:01', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-02-23 10:39:06', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-23 10:39:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-23 10:39:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开历史上线率统计页面');
INSERT INTO `log` VALUES ('2018-02-23 10:39:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开历史在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-23 11:04:07', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开当前在线率统计页面');
INSERT INTO `log` VALUES ('2018-02-23 11:04:14', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开行驶里程及油耗统计页面');
INSERT INTO `log` VALUES ('2018-02-23 11:04:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆警情统计页面');
INSERT INTO `log` VALUES ('2018-02-23 16:20:04', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:127.0.0.1');
INSERT INTO `log` VALUES ('2018-02-23 16:24:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.247');
INSERT INTO `log` VALUES ('2018-02-23 16:36:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.247');
INSERT INTO `log` VALUES ('2018-02-23 16:37:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-23 16:37:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-23 16:54:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-23 16:54:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-23 16:56:31', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-23 16:56:34', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-23 16:56:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-23 16:56:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-23 16:57:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-23 16:57:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:00:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.247');
INSERT INTO `log` VALUES ('2018-02-24 10:00:11', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-24 10:00:24', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:00:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:00:27', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:02:40', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:02:41', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:03:10', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:03:12', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:03:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:07:44', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-02-24 10:07:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:07:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-02-24 10:07:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-02-24 15:13:38', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-24 15:13:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 15:13:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 15:44:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 15:44:13', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 15:44:25', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '数据调试');
INSERT INTO `log` VALUES ('2018-02-24 15:44:26', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '数据调试');
INSERT INTO `log` VALUES ('2018-02-24 15:45:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:42', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 15:45:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-24 16:15:54', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-24 16:15:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-24 16:15:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 16:15:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 16:25:47', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-02-24 16:25:59', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.64.247');
INSERT INTO `log` VALUES ('2018-02-24 16:46:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-02-24 16:46:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.64.247');



INSERT INTO `log` VALUES ('2018-03-13 11:14:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:14:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.66.24');
INSERT INTO `log` VALUES ('2018-03-13 11:15:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:15:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:16:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:17:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:27:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 11:27:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:27:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:28:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:28:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:28:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:28:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:29:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.66.24');
INSERT INTO `log` VALUES ('2018-03-13 11:29:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:29:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:30:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:30:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:31:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:31:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:34:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:35:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-03-13 11:39:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:39:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:42:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:42:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 11:43:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 13:26:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 13:39:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 14:04:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 14:04:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:31:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:31:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:31:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:31:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:32:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 15:32:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-13 15:33:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:33:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:33:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 15:33:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:33:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:34:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:34:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:37:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:37:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:219.137.66.24');
INSERT INTO `log` VALUES ('2018-03-13 15:48:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '用户录登:219.137.66.24');
INSERT INTO `log` VALUES ('2018-03-13 15:48:52', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:53', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:56', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:48:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:55:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:55:17', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:57:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 15:57:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 15:57:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:03:47', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:03:48', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:04:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:04:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:29', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-13 16:06:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:02', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:02', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-03-13 16:07:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-03-13 16:07:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:49', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:50', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:07:58', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:08:00', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:10:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:12:15', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:12:16', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:24:55', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-13 16:24:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-13 16:24:57', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-13 16:25:08', '558ffc6603c70e31a2a53a30', '5a72eb803d769a3c631b8b5a', 'xin', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-03-14 10:48:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:219.137.66.24');
INSERT INTO `log` VALUES ('2018-03-14 10:48:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-14 10:48:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-14 10:49:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-03-14 14:06:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-14 16:05:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.89');
INSERT INTO `log` VALUES ('2018-03-14 16:06:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-14 16:06:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-14 16:06:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-14 16:06:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:24:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:24:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:24:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:24:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:24:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-14 16:25:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-15 15:52:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.89');
INSERT INTO `log` VALUES ('2018-03-15 15:52:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-15 15:52:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-16 09:42:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.89');
INSERT INTO `log` VALUES ('2018-03-16 09:42:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-16 09:42:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-16 09:57:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:14.145.145.89');
INSERT INTO `log` VALUES ('2018-03-16 09:57:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-16 09:57:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-16 10:53:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:14.145.145.89');
INSERT INTO `log` VALUES ('2018-03-16 10:53:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-16 10:53:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-16 10:54:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-03-16 10:55:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');

-- ----------------------------
-- Table structure for `maintain`
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `VEHICLEID` char(24) NOT NULL COMMENT '车辆id',
  `DONEDATE` date NOT NULL COMMENT '保养日期',
  `TYPE` varchar(30) NOT NULL COMMENT '保养类型',
  `CONTENT` varchar(50) NOT NULL COMMENT '保养内容',
  `MILEAGE` int(11) NOT NULL COMMENT '保养里程',
  `FEE` decimal(10,2) NOT NULL COMMENT '保养费用',
  `GARAGE` varchar(30) NOT NULL COMMENT '保养单位',
  `AGENT` varchar(30) NOT NULL COMMENT '经办人',
  `NEXTMILEAGE` int(11) NOT NULL COMMENT '下次保养里程',
  `USERID` char(24) NOT NULL COMMENT '操作员ID',
  `USERNAME` varchar(20) NOT NULL COMMENT '操作员姓名',
  `USERTIME` datetime NOT NULL COMMENT '操作时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `NEXTDATE` date DEFAULT NULL COMMENT '下次保养日期',
  PRIMARY KEY (`ID`),
  KEY `VEHICLEID_DONEDATE` (`VEHICLEID`,`DONEDATE`) USING BTREE,
  KEY `VEHICLEID_NEXTDATE` (`VEHICLEID`,`NEXTDATE`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养记录';

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES ('5a72ad493d769a75b6309e3d', '558ffc6603c70e31a2a53a30', '5a72acea3d769a75b6309e23', '2018-02-15', '', '', '0', '0.00', '', '', '0', '558ffc6603c70e31a2a53a30', '系统管理员', '2018-02-01 14:01:45', '2018-02-01 14:01:45', null);

-- ----------------------------
-- Table structure for `maplayer`
-- ----------------------------
DROP TABLE IF EXISTS `maplayer`;
CREATE TABLE `maplayer` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `VISIBLE` bit(1) NOT NULL COMMENT '可见否',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `USERID` (`USERID`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地图图层';

-- ----------------------------
-- Records of maplayer
-- ----------------------------

-- ----------------------------
-- Table structure for `motorcade`
-- ----------------------------
DROP TABLE IF EXISTS `motorcade`;
CREATE TABLE `motorcade` (
  `ID` char(24) NOT NULL COMMENT '记录ID',
  `COMPANYID` char(24) NOT NULL COMMENT '公司ID',
  `TYPE` varchar(20) NOT NULL COMMENT '车队类型',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `PARENTVISIBLE` bit(1) NOT NULL COMMENT '上级可见否',
  `LINKMAN` varchar(20) NOT NULL COMMENT '车队联系人',
  `PHONE` varchar(20) NOT NULL COMMENT '车队联系电话',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车队';

-- ----------------------------
-- Records of motorcade
-- ----------------------------
INSERT INTO `motorcade` VALUES ('5a72abd83d769a75b6309dee', '558ffc6603c70e31a2a53a30', '后勤运输队', '测试车队', '', '', '', '', '2018-02-01 14:01:19');

-- ----------------------------
-- Table structure for `multimediaeventreport`
-- ----------------------------
DROP TABLE IF EXISTS `multimediaeventreport`;
CREATE TABLE `multimediaeventreport` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '报告时间',
  `MEDIAID` int(10) unsigned NOT NULL COMMENT '多媒体ＩＤ',
  `MEDIATYPE` tinyint(4) NOT NULL COMMENT '多媒体类型',
  `FORMATTYPE` tinyint(4) NOT NULL COMMENT '格式类型',
  `EVENTTYPE` tinyint(4) NOT NULL COMMENT '事件类型',
  `CHANNELID` tinyint(4) NOT NULL COMMENT '通道ＩＤ',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='多媒体事件报告';

-- ----------------------------
-- Records of multimediaeventreport
-- ----------------------------

-- ----------------------------
-- Table structure for `multimediaretrieval`
-- ----------------------------
DROP TABLE IF EXISTS `multimediaretrieval`;
CREATE TABLE `multimediaretrieval` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `MEDIAID` int(10) unsigned NOT NULL COMMENT '多媒体ＩＤ',
  `MEDIATYPE` tinyint(4) NOT NULL COMMENT '多媒体类型',
  `EVENTTYPE` tinyint(4) NOT NULL COMMENT '事件类型',
  `CHANNELID` tinyint(4) NOT NULL COMMENT '通道ＩＤ',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `ALT` smallint(6) unsigned NOT NULL COMMENT '海拔',
  `SPEED` float unsigned NOT NULL COMMENT '速度',
  `ANGLE` smallint(6) unsigned NOT NULL COMMENT '方向',
  `ALARMS` int(11) unsigned NOT NULL COMMENT '报警',
  `STATUS` int(11) unsigned NOT NULL COMMENT '状态',
  PRIMARY KEY (`DEVICENUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='多媒体检索';

-- ----------------------------
-- Records of multimediaretrieval
-- ----------------------------

-- ----------------------------
-- Table structure for `owner`
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `OWNERNAME` varchar(30) NOT NULL COMMENT '姓名',
  `IDTYPE` varchar(30) NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(30) NOT NULL COMMENT '证件编号',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车主资料';

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('5a7419293d769a7dcc878df4', '558ffc6603c70e31a2a53a30', '李生', '', '', '2018-02-02 15:54:17');

-- ----------------------------
-- Table structure for `phonebook`
-- ----------------------------
DROP TABLE IF EXISTS `phonebook`;
CREATE TABLE `phonebook` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司编号',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `PHONE` varchar(20) NOT NULL COMMENT '电话',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电话本';

-- ----------------------------
-- Records of phonebook
-- ----------------------------

-- ----------------------------
-- Table structure for `phoneindevice`
-- ----------------------------
DROP TABLE IF EXISTS `phoneindevice`;
CREATE TABLE `phoneindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `FLAG` tinyint(4) NOT NULL COMMENT '通话标识',
  `PHONE` varchar(20) NOT NULL COMMENT '电话号码',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `ACTION` tinyint(4) NOT NULL COMMENT '操作类型',
  `STATUS` varchar(30) NOT NULL COMMENT '状态',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电话本设置';

-- ----------------------------
-- Records of phoneindevice
-- ----------------------------

-- ----------------------------
-- Table structure for `poi`
-- ----------------------------
DROP TABLE IF EXISTS `poi`;
CREATE TABLE `poi` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `TYPE` varchar(30) NOT NULL COMMENT '类别',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兴趣点';

-- ----------------------------
-- Records of poi
-- ----------------------------

-- ----------------------------
-- Table structure for `polygon`
-- ----------------------------
DROP TABLE IF EXISTS `polygon`;
CREATE TABLE `polygon` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) unsigned NOT NULL COMMENT '属性',
  `MAXSPEED` smallint(5) unsigned NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) unsigned NOT NULL COMMENT '超速持续时间',
  `STARTTIME` date DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多边形区域';

-- ----------------------------
-- Records of polygon
-- ----------------------------

-- ----------------------------
-- Table structure for `polygonpoint`
-- ----------------------------
DROP TABLE IF EXISTS `polygonpoint`;
CREATE TABLE `polygonpoint` (
  `PID` int(10) unsigned NOT NULL COMMENT '多边形编号',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  KEY `PID_INDEX` (`PID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多边形顶点项';

-- ----------------------------
-- Records of polygonpoint
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `ASK` varchar(50) NOT NULL COMMENT '问题',
  `ANSWERID` tinyint(3) unsigned NOT NULL COMMENT '答案id',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `USERTIME` datetime NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for `questionanswer`
-- ----------------------------
DROP TABLE IF EXISTS `questionanswer`;
CREATE TABLE `questionanswer` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `PID` char(24) NOT NULL COMMENT '父记录唯一编号',
  `ANSWERID` tinyint(3) unsigned NOT NULL COMMENT '答案编号',
  `CONTENT` varchar(50) NOT NULL COMMENT '答案内容',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `PID` (`PID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题答案';

-- ----------------------------
-- Records of questionanswer
-- ----------------------------

-- ----------------------------
-- Table structure for `questionindevice`
-- ----------------------------
DROP TABLE IF EXISTS `questionindevice`;
CREATE TABLE `questionindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `ASK` varchar(50) NOT NULL COMMENT '问题',
  `ACTION` tinyint(3) unsigned NOT NULL COMMENT '操作',
  `ANSWERID` tinyint(3) unsigned NOT NULL,
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKID` tinyint(3) unsigned DEFAULT NULL COMMENT '命令正确应答id',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提问下发';

-- ----------------------------
-- Records of questionindevice
-- ----------------------------

-- ----------------------------
-- Table structure for `rectangle`
-- ----------------------------
DROP TABLE IF EXISTS `rectangle`;
CREATE TABLE `rectangle` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) unsigned NOT NULL COMMENT '属性',
  `ULLAT` decimal(9,6) NOT NULL COMMENT '左上点纬度',
  `ULLNG` decimal(9,6) NOT NULL COMMENT '左上点经度',
  `BRLAT` decimal(9,6) NOT NULL COMMENT '右下点纬度',
  `BRLNG` decimal(9,6) NOT NULL COMMENT '右下点经度',
  `MAXSPEED` smallint(5) unsigned NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) unsigned NOT NULL COMMENT '超速持续时间',
  `STARTTIME` date DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='矩形区域';

-- ----------------------------
-- Records of rectangle
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司id',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `COMPANYID_NAME` (`COMPANYID`,`NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('5a72ab073d769a75b6309ded', '5a72ab073d769a75b6309dec', '企业管理员', '', '2018-02-01 13:52:07');
INSERT INTO `role` VALUES ('5a72c8883d769a75b6309ff1', '558ffc6603c70e31a2a53a30', '所有权限', '', '2018-02-01 15:58:00');

-- ----------------------------
-- Table structure for `roleauthorize`
-- ----------------------------
DROP TABLE IF EXISTS `roleauthorize`;
CREATE TABLE `roleauthorize` (
  `COMPANYID` char(24) NOT NULL COMMENT '企业ID',
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  `PERMISSIONID` varchar(100) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`,`ROLEID`,`PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- ----------------------------
-- Records of roleauthorize
-- ----------------------------
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.authorize');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addPois');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removePoi');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapOption');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker.remove');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker.save');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.role');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.role.authorize');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.role.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.role.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.role.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.addSections');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.removeSection');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.addMonitors');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.addRoles');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.removeMonitor');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.removeRole');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.addDrivers');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.addOwners');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.create');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.delete');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.removeDriver');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.removeOwner');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.update');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm.processAll');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm.processOnce');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.accidentDoubtLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.deviceEventReport');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.drivingRecorder');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.locateLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.loginLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimedia');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimediaEvent');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimediaRetrieval');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.parameterLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.powerLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.speedLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.speedStatusLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.timeoutLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.datalog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.instruct');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.vehileinfo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.replay');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'security.saveMyinfo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'security.saveMyKey');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.areaIo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.areaOverspeed');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.currentOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.historyOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.historyOnlineTime');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.mileageOil');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.operateLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.routeDeviation');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.sectionOverspeed');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.timeoutParking');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleAlarm');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleFatigueDriving');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleOverspeed');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.authorize');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPois');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePoi');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapOption');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker.remove');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker.save');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.authorize');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addSections');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addVehicles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeSection');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.addMonitors');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.addRoles');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.removeMonitor');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.removeRole');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addDrivers');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addOwners');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.create');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.delete');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeDriver');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeOwner');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.update');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.accidentDoubtLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.deviceEventReport');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.drivingRecorder');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.locateLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.loginLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimedia');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimediaEvent');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimediaRetrieval');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.parameterLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.powerLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.speedLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.speedStatusLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.timeoutLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.datalog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.instruct');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.vehileinfo');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.replay');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'security.saveMyinfo');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'security.saveMyKey');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.areaIo');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.areaOverspeed');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.currentOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.historyOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.historyOnlineTime');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.mileageOil');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.operateLog');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.routeDeviation');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.sectionOverspeed');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.timeoutParking');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleAlarm');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleFatigueDriving');
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleOverspeed');

-- ----------------------------
-- Table structure for `roleinuser`
-- ----------------------------
DROP TABLE IF EXISTS `roleinuser`;
CREATE TABLE `roleinuser` (
  `USERID` char(24) NOT NULL COMMENT '用户ID',
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USERID`,`ROLEID`),
  KEY `FK_roleinuser_role` (`ROLEID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of roleinuser
-- ----------------------------
INSERT INTO `roleinuser` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309ded');
INSERT INTO `roleinuser` VALUES ('5a72c8733d769a75b6309ff0', '5a72c8883d769a75b6309ff1');
INSERT INTO `roleinuser` VALUES ('5a72eb803d769a3c631b8b5a', '5a72c8883d769a75b6309ff1');

-- ----------------------------
-- Table structure for `route`
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) unsigned NOT NULL COMMENT '属性',
  `STARTTIME` date DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线区域';

-- ----------------------------
-- Records of route
-- ----------------------------

-- ----------------------------
-- Table structure for `routesection`
-- ----------------------------
DROP TABLE IF EXISTS `routesection`;
CREATE TABLE `routesection` (
  `ROUTEID` int(11) unsigned NOT NULL COMMENT '路线唯一编号',
  `SECTIONID` int(11) unsigned NOT NULL COMMENT '路段唯一编号',
  PRIMARY KEY (`ROUTEID`,`SECTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线路段表';

-- ----------------------------
-- Records of routesection
-- ----------------------------

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '路段id',
  `COMPANYID` char(24) NOT NULL COMMENT '企业唯一编号',
  `NAME` varchar(30) NOT NULL COMMENT '路段名称',
  `WIDTH` tinyint(3) unsigned NOT NULL COMMENT '路段宽度',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '路段属性',
  `MAXSECONDS` smallint(5) unsigned NOT NULL COMMENT '路段行驶过长阈值',
  `MINSECONDS` smallint(5) unsigned NOT NULL COMMENT '路段行驶不足阈值',
  `MAXSPEED` smallint(5) unsigned NOT NULL COMMENT '路段最高速度',
  `OVERSPEEDSECONDS` tinyint(3) unsigned NOT NULL COMMENT '路段超速持续时间',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路段';

-- ----------------------------
-- Records of section
-- ----------------------------

-- ----------------------------
-- Table structure for `sectionpoint`
-- ----------------------------
DROP TABLE IF EXISTS `sectionpoint`;
CREATE TABLE `sectionpoint` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '拐点id',
  `SECTIONID` int(10) unsigned NOT NULL COMMENT '路段id',
  `LAT` decimal(9,6) NOT NULL COMMENT '拐点纬度',
  `LNG` decimal(9,6) NOT NULL COMMENT '拐点经度',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  PRIMARY KEY (`ID`),
  KEY `SECTIONID` (`SECTIONID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线拐点';

-- ----------------------------
-- Records of sectionpoint
-- ----------------------------

-- ----------------------------
-- Table structure for `simcard`
-- ----------------------------
DROP TABLE IF EXISTS `simcard`;
CREATE TABLE `simcard` (
  `ID` char(24) NOT NULL COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `PHONENUMBER` varchar(20) NOT NULL COMMENT '电话号码',
  `SIMCARDNUMBER` varchar(20) NOT NULL COMMENT 'sim卡号',
  `SPEECHTYPE` varchar(10) NOT NULL COMMENT '语音类型',
  `OPENSMS` bit(1) NOT NULL COMMENT '是否开通短信',
  `CARRIEROPERATOR` varchar(20) NOT NULL COMMENT '运营商',
  `PREPAYMENT` decimal(10,2) NOT NULL COMMENT '预交费',
  `FLOWSET` varchar(20) NOT NULL COMMENT '流量套餐',
  `CREATETIME` date NOT NULL COMMENT '创建时间',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `OPENDATE` date DEFAULT NULL COMMENT '开通日期',
  `PURCHASEDATE` date DEFAULT NULL COMMENT '购买日期',
  `EXPIREDATE` date DEFAULT NULL COMMENT '到期日期',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SIMCARDNUMBER` (`SIMCARDNUMBER`) USING BTREE,
  UNIQUE KEY `PHONENUMBER` (`PHONENUMBER`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sim卡资料';

-- ----------------------------
-- Records of simcard
-- ----------------------------
INSERT INTO `simcard` VALUES ('5a72ac0c3d769a75b6309def', '558ffc6603c70e31a2a53a30', '18011012699', '18011012699', '呼入呼出', '', '', '0.00', '', '2018-02-01', '', '2018-02-02 16:06:00', null, '2018-02-01', null);
INSERT INTO `simcard` VALUES ('5a7417dd3d769a7dcc878d5b', '558ffc6603c70e31a2a53a30', '18011012700', '18011012700', '呼入呼出', '', '', '0.00', '', '2018-02-02', '', '2018-02-02 16:05:54', null, '2018-02-02', null);

-- ----------------------------
-- Table structure for `textmessage`
-- ----------------------------
DROP TABLE IF EXISTS `textmessage`;
CREATE TABLE `textmessage` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `CONTENT` text NOT NULL COMMENT '文本内容',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `USERTIME` datetime NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`) USING BTREE,
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本信息';

-- ----------------------------
-- Records of textmessage
-- ----------------------------

-- ----------------------------
-- Table structure for `textmessageindevice`
-- ----------------------------
DROP TABLE IF EXISTS `textmessageindevice`;
CREATE TABLE `textmessageindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `CONTENT` text NOT NULL COMMENT '文本内容',
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本信息下发';

-- ----------------------------
-- Records of textmessageindevice
-- ----------------------------

-- ----------------------------
-- Table structure for `upgradereport`
-- ----------------------------
DROP TABLE IF EXISTS `upgradereport`;
CREATE TABLE `upgradereport` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `TYPE` varchar(20) NOT NULL COMMENT '升级类型',
  `RESULT` varchar(20) NOT NULL COMMENT '升级结果',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='升级结果通知';

-- ----------------------------
-- Records of upgradereport
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` char(24) NOT NULL COMMENT '用户ID',
  `UNID` char(36) NOT NULL COMMENT '用户guid',
  `COMPANYID` char(24) NOT NULL COMMENT '公司ID',
  `KIND` tinyint(4) NOT NULL COMMENT '类别，1公司，2公司用户，3车主，4设备',
  `ACCOUNT` varchar(20) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(20) NOT NULL COMMENT '密码',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `EMAIL` varchar(20) NOT NULL COMMENT '邮箱',
  `PHONE` varchar(20) NOT NULL COMMENT '电话',
  `CONTACT` varchar(20) NOT NULL COMMENT '联系人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `ENABLE` bit(1) NOT NULL COMMENT '启用',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `PID` char(24) DEFAULT NULL COMMENT '父ID',
  `SERVICESTARTDATE` date DEFAULT NULL COMMENT '服务开始日期',
  `SERVICEENDDATE` date DEFAULT NULL COMMENT '服务结束日期',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `QUESTION1` varchar(20) DEFAULT NULL COMMENT '问题1',
  `ANSWER1` varchar(20) DEFAULT NULL COMMENT '答案1',
  `QUESTION2` varchar(20) DEFAULT NULL COMMENT '问题2',
  `ANSWER2` varchar(20) DEFAULT NULL COMMENT '答案2',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UN_ACCOUNT` (`ACCOUNT`) USING BTREE,
  KEY `IX_PID` (`PID`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('558ffc6603c70e31a2a53a30', 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '558ffc6603c70e31a2a53a30', '0', 'admin', '123456', '系统管理员', 'yangxp999@163.com', '13714196239', '杨生', '2014-10-10 17:43:14', '', '2015-06-28 22:00:53', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('5a72ab073d769a75b6309dec', '09790727-0714-11e8-89e3-00163e32414e', '5a72ab073d769a75b6309dec', '1', 'rayton', '888888', 'rayton', '', '', '', '2018-02-01 13:52:07', '', '2018-02-01 13:52:07', '558ffc6603c70e31a2a53a30', null, null, '', null, null, null, null);
INSERT INTO `user` VALUES ('5a72ae2a3d769a75b6309e68', 'e858c6ae-0715-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', '3', 'rayton01', '888888', 'rayton01', '', '', '', '2018-02-01 14:05:30', '', '2018-02-01 16:13:25', null, null, null, '', null, null, null, null);
INSERT INTO `user` VALUES ('5a72c8733d769a75b6309ff0', '92ff3411-0725-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', '2', 'rayton02', '888888', 'rayton02', '', '', '', '2018-02-01 15:57:39', '', '2018-02-01 15:57:39', null, null, null, '', null, null, null, null);
INSERT INTO `user` VALUES ('5a72eb803d769a3c631b8b5a', '772cf348-073a-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', '2', 'xin', '888888', 'xin', '', '', '', '2018-02-01 18:27:12', '', '2018-02-01 18:27:12', null, null, null, '', null, null, null, null);
INSERT INTO `user` VALUES ('5a7417fc3d769a7dcc878d5c', '91bd9da3-07ed-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', '4', '18011012700', '888888', 'T0001', '', '', '', '2018-02-02 15:49:16', '', '2018-02-02 15:55:43', null, '2018-02-02', '2018-03-10', '', null, null, null, null);
INSERT INTO `user` VALUES ('5a7419293d769a7dcc878df4', '44ce10ae-07ee-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', '3', 'spr', '888888', 'spr', '', '', '', '2018-02-02 15:54:17', '', '2018-02-02 16:05:04', null, null, null, '', null, null, null, null);
INSERT INTO `user` VALUES ('5aa7419b7b03680b92edf011', '5bc016f5-266c-11e8-b49f-00163e32131c', '558ffc6603c70e31a2a53a30', '4', '10189431635', '888888', '测试', '', '', '', '2018-03-13 11:12:27', '', '2018-03-13 11:12:27', null, null, null, '', null, null, null, null);

-- ----------------------------
-- Table structure for `usermonitor`
-- ----------------------------
DROP TABLE IF EXISTS `usermonitor`;
CREATE TABLE `usermonitor` (
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `TARGETID` char(24) NOT NULL COMMENT '目标唯一编号',
  PRIMARY KEY (`USERID`,`TARGETID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户监控范围';

-- ----------------------------
-- Records of usermonitor
-- ----------------------------
INSERT INTO `usermonitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72ab073d769a75b6309dec');
INSERT INTO `usermonitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72abd83d769a75b6309dee');
INSERT INTO `usermonitor` VALUES ('5a72eb803d769a3c631b8b5a', '5a72abd83d769a75b6309dee');

-- ----------------------------
-- Table structure for `useroptions`
-- ----------------------------
DROP TABLE IF EXISTS `useroptions`;
CREATE TABLE `useroptions` (
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERKEY` varchar(30) NOT NULL COMMENT '用户自定义键',
  `USERVALUE` text NOT NULL COMMENT '用户自定义设置',
  PRIMARY KEY (`USERID`,`USERKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户自定义设置';

-- ----------------------------
-- Records of useroptions
-- ----------------------------

-- ----------------------------
-- Table structure for `vehicle`
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `ID` char(24) NOT NULL COMMENT '唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '所属公司',
  `MOTORCADEID` char(24) NOT NULL COMMENT '所属车队',
  `DEVICEID` char(24) NOT NULL COMMENT '设备号唯一编号',
  `PLATENUMBER` varchar(20) NOT NULL COMMENT '车牌号码',
  `PLATECOLOR` varchar(5) NOT NULL COMMENT '车牌颜色',
  `VEHICLECOLOR` varchar(5) NOT NULL COMMENT '车辆颜色',
  `VEHICLETYPE` varchar(15) NOT NULL COMMENT '车辆类型',
  `VEHICLEVOLTAGE` varchar(10) NOT NULL COMMENT '车辆电压',
  `CARRYTYPE` varchar(15) NOT NULL COMMENT '载运类型',
  `INITIALMILEAGE` int(10) unsigned NOT NULL COMMENT '初始里程',
  `OILWEAR` decimal(10,1) NOT NULL COMMENT '百公里油耗',
  `USEFULLIFE` int(11) NOT NULL COMMENT '使用年限',
  `ADMINAREA` varchar(15) NOT NULL COMMENT '所属行政区域',
  `MARKER` varchar(15) NOT NULL COMMENT '车辆图标',
  `ROTATE` bit(1) NOT NULL COMMENT '旋转车辆图标否',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `INSTALLDATE` date DEFAULT NULL COMMENT '安装日期',
  `ANNUALSURVEYDATE` date DEFAULT NULL COMMENT '年检日期',
  `NEXTMAINTAINDATE` date DEFAULT NULL COMMENT '下次保养日期',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PLATENUMBER` (`PLATENUMBER`) USING BTREE,
  KEY `COMPANYID` (`COMPANYID`) USING BTREE,
  KEY `MOTORCADEID` (`MOTORCADEID`) USING BTREE,
  KEY `DEVICEID` (`DEVICEID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆表';

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('5a74182b3d769a7dcc878d5d', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5a7417fc3d769a7dcc878d5c', '京B37A94', '蓝色', '白色', '中型客车', '12V', '客车', '0', '0.0', '0', '广东省', '00.png', '', '', '2018-02-02 15:50:03', '2018-02-02 16:02:15', '2018-02-02', '2018-02-22', null);
INSERT INTO `vehicle` VALUES ('5aa741df7b03680b92edf012', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aa7419b7b03680b92edf011', '京B37A93', '蓝色', '白色', '大型客车', '12V', '客车', '0', '0.0', '0', '广东省', '00.png', '', '', '2018-03-13 11:13:35', '2018-03-13 11:13:35', null, null, null);

-- ----------------------------
-- Table structure for `vehicleindriver`
-- ----------------------------
DROP TABLE IF EXISTS `vehicleindriver`;
CREATE TABLE `vehicleindriver` (
  `DRIVERID` char(24) NOT NULL COMMENT '司机唯一编号',
  `VEHICLEID` char(24) NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`DRIVERID`,`VEHICLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆与司机关系';

-- ----------------------------
-- Records of vehicleindriver
-- ----------------------------
INSERT INTO `vehicleindriver` VALUES ('5a72acea3d769a75b6309e23', '5a72ae0c3d769a75b6309e67');
INSERT INTO `vehicleindriver` VALUES ('5a72ae0c3d769a75b6309e67', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleindriver` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae0c3d769a75b6309e67');
INSERT INTO `vehicleindriver` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419353d769a7dcc878df5');
INSERT INTO `vehicleindriver` VALUES ('5a7419353d769a7dcc878df5', '5a74182b3d769a7dcc878d5d');

-- ----------------------------
-- Table structure for `vehicleinowner`
-- ----------------------------
DROP TABLE IF EXISTS `vehicleinowner`;
CREATE TABLE `vehicleinowner` (
  `OWNERID` char(24) NOT NULL COMMENT '车主唯一编号',
  `VEHICLEID` char(24) NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`OWNERID`,`VEHICLEID`),
  KEY `FK_vehicleinowner_vehilce` (`VEHICLEID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆所属车主';

-- ----------------------------
-- Records of vehicleinowner
-- ----------------------------
INSERT INTO `vehicleinowner` VALUES ('5a72acea3d769a75b6309e23', '5a72ae2a3d769a75b6309e68');
INSERT INTO `vehicleinowner` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae2a3d769a75b6309e68');
INSERT INTO `vehicleinowner` VALUES ('5a72ae2a3d769a75b6309e68', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleinowner` VALUES ('5a7419293d769a7dcc878df4', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleinowner` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419293d769a7dcc878df4');
