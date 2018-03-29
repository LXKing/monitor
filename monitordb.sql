/*
 Navicat MySQL Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : monitordb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 29/03/2018 16:41:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm
-- ----------------------------
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `GPSTIME` datetime(0) NOT NULL COMMENT 'GPS时间',
  `SERVERTIME` datetime(0) NOT NULL COMMENT '服务器时间',
  `LNG` decimal(9, 6) NOT NULL COMMENT '经度',
  `LAT` decimal(9, 6) NOT NULL COMMENT '纬度',
  `ALT` smallint(6) UNSIGNED NOT NULL COMMENT '海拔',
  `SPEED` float UNSIGNED NOT NULL COMMENT '速度',
  `ANGLE` smallint(6) UNSIGNED NOT NULL COMMENT '方向',
  `ALARMS` int(11) UNSIGNED NOT NULL COMMENT '报警',
  `STATUS` int(11) UNSIGNED NOT NULL COMMENT '状态',
  `MILEAGE` double UNSIGNED NOT NULL COMMENT '里程',
  `OILMASS` float UNSIGNED NOT NULL COMMENT '油量',
  `VSS` float UNSIGNED NOT NULL COMMENT '车速',
  `OVAREATYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '超速区域类型',
  `OVAREAID` int(10) UNSIGNED NOT NULL COMMENT '超速区域id',
  `IOAREATYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '进出区域类型',
  `IOAREAID` int(10) UNSIGNED NOT NULL COMMENT '进出区域id',
  `IOAREAFLAG` tinyint(4) NOT NULL COMMENT '进出区域方向',
  `ROUTEID` int(10) UNSIGNED NOT NULL COMMENT '路段id',
  `ROUTESECONDS` smallint(5) UNSIGNED NOT NULL COMMENT '路段行驶时间',
  `ROUTEFLAG` tinyint(4) NOT NULL COMMENT '路段结果',
  `ALARMID` smallint(5) UNSIGNED NOT NULL COMMENT '报警确人ID',
  `EXSTATUS` int(10) UNSIGNED NOT NULL COMMENT '扩展车辆信号状态位',
  `IOSTATUS` smallint(5) UNSIGNED NOT NULL COMMENT 'IO状态位',
  `AD0` smallint(5) UNSIGNED NOT NULL COMMENT '模拟量AD0',
  `AD1` smallint(5) UNSIGNED NOT NULL COMMENT '模拟量AD1',
  `NETWORK` tinyint(3) UNSIGNED NOT NULL COMMENT '网络强度',
  `SATELLITES` tinyint(3) UNSIGNED NOT NULL COMMENT '卫星数量',
  `FROM` tinyint(4) NOT NULL COMMENT '来源,0:终端,1:平台',
  `VALID` bit(1) NOT NULL COMMENT '是否有效',
  `ISHANDLED` bit(1) NOT NULL COMMENT '处理否',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `USERCONFIRM` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户确认警类型',
  `USERMETHOD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理方式',
  `USERREMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER_GPSTIME`(`DEVICENUMBER`, `GPSTIME`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报警表，用于记录设备所有报警，以及人工处理结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for areaindevice
-- ----------------------------
DROP TABLE IF EXISTS `areaindevice`;
CREATE TABLE `areaindevice`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `AREAID` int(10) UNSIGNED NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `TIME` datetime(0) NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`DEVICENUMBER`, `AREAID`, `AREATYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of areaindevice
-- ----------------------------
INSERT INTO `areaindevice` VALUES ('10189415505', 1, 2, '2018-03-19 15:00:26');
INSERT INTO `areaindevice` VALUES ('10189415505', 1, 3, '2018-03-19 15:00:02');
INSERT INTO `areaindevice` VALUES ('10189415505', 1, 4, '2018-03-19 14:56:21');
INSERT INTO `areaindevice` VALUES ('10189415505', 2, 1, '2018-03-19 15:02:04');
INSERT INTO `areaindevice` VALUES ('10189415505', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `areaindevice` VALUES ('10189415505', 3, 4, '2018-03-19 15:52:39');
INSERT INTO `areaindevice` VALUES ('13311012699', 1, 1, '2018-02-23 10:37:41');
INSERT INTO `areaindevice` VALUES ('13311012700', 1, 2, '2018-03-19 15:00:26');
INSERT INTO `areaindevice` VALUES ('13311012700', 1, 3, '2018-03-19 15:00:02');
INSERT INTO `areaindevice` VALUES ('13311012700', 1, 4, '2018-03-19 14:56:21');
INSERT INTO `areaindevice` VALUES ('13311012700', 2, 1, '2018-03-19 15:02:04');
INSERT INTO `areaindevice` VALUES ('13311012700', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `areaindevice` VALUES ('13311012700', 3, 4, '2018-03-19 15:52:39');
INSERT INTO `areaindevice` VALUES ('regtre', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `areaindevice` VALUES ('regtre', 3, 4, '2018-03-19 15:52:39');

-- ----------------------------
-- Table structure for areaindevicelog
-- ----------------------------
DROP TABLE IF EXISTS `areaindevicelog`;
CREATE TABLE `areaindevicelog`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `AREAID` int(10) UNSIGNED NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `ACTION` tinyint(4) NOT NULL COMMENT '操作类型',
  `UNID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户全局唯一编号',
  `USER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `SENDTIME` datetime(0) NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ACKTIME`(`ACKTIME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备的操作日志，以便与设备同步' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of areaindevicelog
-- ----------------------------
INSERT INTO `areaindevicelog` VALUES ('01f2e58b-610d-422f-b5c1-80dce1d2b4a6', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:47', NULL);
INSERT INTO `areaindevicelog` VALUES ('0c36b632-de08-4e34-9995-59856dd8acd0', '13311012700', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:42', NULL);
INSERT INTO `areaindevicelog` VALUES ('1440fd51-b3ed-4b1a-8779-57d8867ca332', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:29', NULL);
INSERT INTO `areaindevicelog` VALUES ('150d888b-03b7-4e3e-98e6-e2609dbe25ee', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `areaindevicelog` VALUES ('1560268f-0d59-483e-9a5b-210e7175f953', '10189415505', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `areaindevicelog` VALUES ('1bb3547c-5ade-4224-8bc2-5975f727a75f', 'regtre', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `areaindevicelog` VALUES ('3a1e9ffe-9486-46ae-89a9-25202d1a69af', '13311012700', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `areaindevicelog` VALUES ('3c6aff6a-2a74-49e1-835f-a12e2fd86552', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `areaindevicelog` VALUES ('40ab650f-08a5-4867-a5b3-8afa0fca1de8', '10189415505', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:04', NULL);
INSERT INTO `areaindevicelog` VALUES ('539cda10-fd44-4810-9a04-ed4142c7873b', '13311012700', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `areaindevicelog` VALUES ('61a9cb98-045b-49b7-931b-9354669deec3', '10189415505', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `areaindevicelog` VALUES ('66032486-22cd-45f5-8c9b-3c33b1a75b25', '10189415505', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `areaindevicelog` VALUES ('6a3a8ca1-9878-4020-937e-ef78e9823ea9', '10189415505', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `areaindevicelog` VALUES ('6fe8e695-a9ce-4223-bb89-2d684500c981', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `areaindevicelog` VALUES ('797f9ab0-1e83-4ec5-b911-393a9fe0944b', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:15', NULL);
INSERT INTO `areaindevicelog` VALUES ('7c10f8a5-e0d4-4ca7-81cf-ae1f483e2192', '13311012700', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `areaindevicelog` VALUES ('96d05d27-2054-4f8f-9bfe-e651d30cf4ab', '10189415505', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `areaindevicelog` VALUES ('a15579d2-cbe7-4ca8-8534-f4d85e564c05', 'regtre', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `areaindevicelog` VALUES ('a99b21e4-f3d6-4bec-8da1-f1894584522f', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:16', NULL);
INSERT INTO `areaindevicelog` VALUES ('aee6ab70-1f1f-44d7-8a51-42293eb1e647', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:47', NULL);
INSERT INTO `areaindevicelog` VALUES ('cb07e646-7f57-4022-81f6-e020deacc268', '13311012700', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `areaindevicelog` VALUES ('d619d21a-776d-4830-9cc9-8b9a7453662c', '13311012700', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `areaindevicelog` VALUES ('e696227c-c546-41ad-90f7-6c2f6050315a', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `areaindevicelog` VALUES ('e7d5eb71-94ab-4875-9790-5d4a649d609d', '13311012699', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:41', NULL);
INSERT INTO `areaindevicelog` VALUES ('f110bfbc-63c5-4589-89bc-5d31645a3c93', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:27', NULL);
INSERT INTO `areaindevicelog` VALUES ('fbe64d7e-bf50-4f2e-993e-4c439ca89e90', '13311012700', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:04', NULL);

-- ----------------------------
-- Table structure for areainmaplayer
-- ----------------------------
DROP TABLE IF EXISTS `areainmaplayer`;
CREATE TABLE `areainmaplayer`  (
  `MAPLAYERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地图图层id',
  `AREAID` int(10) UNSIGNED NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  PRIMARY KEY (`MAPLAYERID`, `AREAID`, `AREATYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地图图层覆盖物，用于圆形区域、矩形区域、多边形区域、路线、兴趣点绑定到地图图层' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of areainmaplayer
-- ----------------------------
INSERT INTO `areainmaplayer` VALUES ('5aaf5e23e6c6170930f59413', 1, 1);

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) UNSIGNED NOT NULL COMMENT '属性',
  `LAT` decimal(9, 6) NOT NULL COMMENT '中心点纬度',
  `LNG` decimal(9, 6) NOT NULL COMMENT '中心点经度',
  `RADIUS` int(10) UNSIGNED NOT NULL COMMENT '半径',
  `MAXSPEED` smallint(5) UNSIGNED NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) UNSIGNED NOT NULL COMMENT '超速持续时间',
  `STARTTIME` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圆形区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES (1, '558ffc6603c70e31a2a53a30', 'test111', b'1', 16859, 37.891270, 85.164946, 561082, 0, 0, '2018-02-23 10:37:00', '2018-02-24 10:37:00', '', '2018-02-23 10:37:31');
INSERT INTO `circle` VALUES (2, '558ffc6603c70e31a2a53a30', 'dfhdfhd', b'1', 0, 37.657573, 95.100557, 577557, 0, 0, NULL, NULL, '', '2018-03-19 15:00:42');
INSERT INTO `circle` VALUES (3, '558ffc6603c70e31a2a53a30', 'fhdfhdf', b'1', 0, 41.054568, 86.489680, 910092, 0, 0, NULL, NULL, '', '2018-03-21 16:51:20');
INSERT INTO `circle` VALUES (4, '558ffc6603c70e31a2a53a30', 'sgdgs', b'1', 0, 37.803949, 85.753706, 65030, 0, 0, NULL, NULL, '', '2018-03-21 16:51:27');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一编号',
  `FULLNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全称',
  `SHORTNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `ORGANCODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织机构编号',
  `CORPORATION` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '法人代表',
  `ONDUTYPHONE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '24小时值班电话',
  `OFFICEADDRESS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '办公地址',
  `REGISTADDRESS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册地址',
  `PARENTVISIBLE` bit(1) NOT NULL COMMENT '上级可见否',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `REGISTDATE` date NULL DEFAULT NULL COMMENT '注册日期',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商业代表',
  `ZIPCODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `PID` int(11) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('558ffc6603c70e31a2a53a30', '中国兵器工业北方通用电子', '兵工北方通用', '', '', '', '', '', b'0', '2015-08-29 11:18:53', '2015-08-29', NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5a72ab073d769a75b6309dec', '深圳锐讯易通科技有限公司', '锐讯易通', 'retert', 'eter', 'ertert', 'erterte', 'rtret', b'1', '2018-03-19 15:11:16', '2018-02-01', NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5abc88ed05a3310acc2444dd', 'dvfsd', '444', 'fgdf', '', '', '', '', b'1', '2018-03-29 14:34:21', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for companyauthorize
-- ----------------------------
DROP TABLE IF EXISTS `companyauthorize`;
CREATE TABLE `companyauthorize`  (
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业ID',
  `PERMISSIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`, `PERMISSIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of companyauthorize
-- ----------------------------
INSERT INTO `companyauthorize` VALUES ('558ffc6603c70e31a2a53a30', 'test');
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
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'mmp');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyinfo');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyKey');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.areaIo');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.areaOverspeed');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.currentOnlineOffline');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.historyOnlineOffline');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.historyOnlineTime');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.mileageOil');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.operateLog');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.routeDeviation');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.sectionOverspeed');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.timeoutParking');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleAlarm');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleFatigueDriving');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleOverspeed');
INSERT INTO `companyauthorize` VALUES ('5a72ab073d769a75b6309dec', 'test');
INSERT INTO `companyauthorize` VALUES ('5abc88ed05a3310acc2444dd', 'baseinfo');
INSERT INTO `companyauthorize` VALUES ('5abc88ed05a3310acc2444dd', 'test');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `SIMCARDID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sim卡号唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `MODEL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号(终端型号)',
  `FACTORYNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '厂家(终端厂商)',
  `FACTORYNUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出厂号',
  `CAMERAS` tinyint(4) NOT NULL COMMENT '摄像头路数',
  `HASMICROPHONE` bit(1) NOT NULL COMMENT '有无麦克风',
  `HASNAVIGATION` bit(1) NOT NULL COMMENT '有无导航屏',
  `SENSORS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '传感器列表',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `WARRANTY` date NULL DEFAULT NULL COMMENT '保修期',
  `PURCHASEDATE` date NULL DEFAULT NULL COMMENT '购买日期',
  `INSTALLDATE` date NULL DEFAULT NULL COMMENT '安装日期',
  `AUTHCODE` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '鉴权码',
  `REGISTTIME` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端SN\r\n',
  `INSTOCKDATE` date NULL DEFAULT NULL COMMENT '终端入库时间\r\n',
  `OUTSTOCKDATE` date NULL DEFAULT NULL COMMENT '终端出库时间\r\n',
  `ACTIVATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '终端激活时间\r\n',
  `IMEI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端IMEI\r\n',
  `LIFECYCLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端使用寿命\r\n',
  `LIFEEXPIRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '终端寿命到期时间\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `SIMCARDNUMBER`(`SIMCARDID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('5a7417fc3d769a7dcc878d5c', '558ffc6603c70e31a2a53a30', '5a7417dd3d769a7dcc878d5b', '13311012700', '星XXX', '联XXX', '', 0, b'0', b'0', '', '2018-02-02 16:05:01', '2018-02-02', '2018-02-02', '2018-02-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device` VALUES ('5aa7419b7b03680b92edf011', '558ffc6603c70e31a2a53a30', '5a72ac0c3d769a75b6309def', '10189415505', '星XXX', '联XXX', '', 0, b'0', b'0', '', '2018-03-13 16:07:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device` VALUES ('5aaf614be6c6170930f59510', '558ffc6603c70e31a2a53a30', '5aaf6123e6c6170930f594dd', 'regtre', 'XA-TY-101', '翰XXX', '', 0, b'0', b'0', '', '2018-03-19 15:06:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device` VALUES ('5ab21cace6c617119017815d', '558ffc6603c70e31a2a53a30', '', 'ddsfd', '', '', '', 0, b'0', b'0', '', '2018-03-21 16:49:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `KIND` int(11) NOT NULL COMMENT '类型',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `PID` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '父级编号',
  `CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码',
  `INDEX` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `KIND`(`KIND`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 1, '蓝色', '2015-07-29 22:02:26', NULL, '1', '1');
INSERT INTO `dictionary` VALUES (2, 1, '黄色', '2015-07-29 22:03:44', NULL, '2', '2');
INSERT INTO `dictionary` VALUES (3, 1, '黑色', '2015-07-29 22:04:23', NULL, '3', '3');
INSERT INTO `dictionary` VALUES (4, 1, '白色', '2015-07-29 22:04:44', NULL, '4', '4');
INSERT INTO `dictionary` VALUES (5, 1, '其他', '2015-07-29 22:04:59', NULL, '9', '9');
INSERT INTO `dictionary` VALUES (6, 3, '客车', '2015-07-30 10:50:45', NULL, '10', '10');
INSERT INTO `dictionary` VALUES (7, 3, '大型客车', '2015-07-30 10:50:45', 6, '11', '11');
INSERT INTO `dictionary` VALUES (8, 3, '中型客车', '2015-07-30 10:50:45', 6, '12', '12');
INSERT INTO `dictionary` VALUES (9, 3, '小型客车', '2015-07-30 10:50:45', 6, '13', '13');
INSERT INTO `dictionary` VALUES (10, 3, '轿车', '2015-07-30 10:50:45', 6, '14', '14');
INSERT INTO `dictionary` VALUES (11, 3, '大型卧铺客车', '2015-07-30 10:50:45', 6, '15', '15');
INSERT INTO `dictionary` VALUES (12, 3, '中型卧铺客车', '2015-07-30 10:50:45', 6, '16', '16');
INSERT INTO `dictionary` VALUES (13, 3, '普通货车', '2015-07-30 10:50:45', NULL, '20', '20');
INSERT INTO `dictionary` VALUES (14, 3, '大型普通货车', '2015-07-30 10:50:45', 13, '21', '21');
INSERT INTO `dictionary` VALUES (16, 3, '中型普通货车', '2015-07-30 10:50:45', 13, '22', '22');
INSERT INTO `dictionary` VALUES (17, 3, '小型普通货车', '2015-07-30 10:50:45', 13, '23', '23');
INSERT INTO `dictionary` VALUES (18, 3, '专用运输车', '2015-07-30 10:50:45', NULL, '30', '30');
INSERT INTO `dictionary` VALUES (19, 3, '集装箱车', '2015-07-30 10:50:45', 18, '31', '31');
INSERT INTO `dictionary` VALUES (20, 3, '大件运输车', '2015-07-30 10:50:45', 18, '32', '32');
INSERT INTO `dictionary` VALUES (21, 3, '保温冷藏车', '2015-07-30 10:50:45', 18, '33', '33');
INSERT INTO `dictionary` VALUES (22, 3, '商品车运输专用车辆', '2015-07-30 10:50:45', 18, '34', '34');
INSERT INTO `dictionary` VALUES (23, 3, '罐车', '2015-07-30 10:50:45', 18, '35', '35');
INSERT INTO `dictionary` VALUES (24, 3, '牵引车', '2015-07-30 10:50:45', 18, '36', '36');
INSERT INTO `dictionary` VALUES (25, 3, '挂车', '2015-07-30 10:50:45', 18, '37', '37');
INSERT INTO `dictionary` VALUES (26, 3, '平板车', '2015-07-30 10:50:45', 18, '38', '38');
INSERT INTO `dictionary` VALUES (27, 3, '其他专用车', '2015-07-30 10:50:45', 18, '39', '39');
INSERT INTO `dictionary` VALUES (28, 3, '危险品运输车', '2015-07-30 10:50:45', NULL, '40', '40');
INSERT INTO `dictionary` VALUES (29, 3, '农用车', '2015-07-30 10:50:45', NULL, '50', '50');
INSERT INTO `dictionary` VALUES (30, 3, '拖拉车', '2015-07-30 10:50:45', NULL, '60', '60');
INSERT INTO `dictionary` VALUES (31, 3, '轮式拖拉车', '2015-07-30 10:50:45', 30, '61', '61');
INSERT INTO `dictionary` VALUES (32, 3, '手扶拖拉机', '2015-07-30 10:50:45', 30, '62', '62');
INSERT INTO `dictionary` VALUES (33, 3, '履带拖拉机', '2015-07-30 10:50:45', 30, '63', '63');
INSERT INTO `dictionary` VALUES (34, 3, '特种拖拉机', '2015-07-30 10:50:45', 30, '64', '64');
INSERT INTO `dictionary` VALUES (35, 3, '其他车辆', '2015-07-30 10:50:45', NULL, '90', '90');
INSERT INTO `dictionary` VALUES (36, 2, '白色', '2015-07-30 10:55:50', NULL, '', '白色');
INSERT INTO `dictionary` VALUES (37, 2, '红色', '2015-07-30 10:55:51', NULL, '', '红色');
INSERT INTO `dictionary` VALUES (38, 2, '绿色', '2015-07-30 10:55:52', NULL, '', '绿色');
INSERT INTO `dictionary` VALUES (39, 2, '黑色', '2015-07-30 10:55:58', NULL, '', '黑色');
INSERT INTO `dictionary` VALUES (40, 2, '蓝色', '2015-07-30 10:55:56', NULL, '', '蓝色');
INSERT INTO `dictionary` VALUES (41, 4, '客车', '2015-07-30 11:49:10', NULL, '', '1');
INSERT INTO `dictionary` VALUES (42, 4, '货车', '2015-07-30 11:49:24', NULL, '', '2');
INSERT INTO `dictionary` VALUES (43, 4, '小轿车', '2015-07-30 11:49:39', NULL, '', '3');
INSERT INTO `dictionary` VALUES (44, 4, '船', '2015-07-30 11:50:03', NULL, '', '4');
INSERT INTO `dictionary` VALUES (45, 4, '摩托车', '2015-07-30 11:50:22', NULL, '', '5');
INSERT INTO `dictionary` VALUES (46, 5, '北京市', '2015-07-30 11:51:55', NULL, '110000', '110000');
INSERT INTO `dictionary` VALUES (47, 5, '天津市', '2015-07-30 11:52:14', NULL, '120000', '120000');
INSERT INTO `dictionary` VALUES (48, 5, '河北省', '2015-07-30 11:52:39', NULL, '130000', '130000');
INSERT INTO `dictionary` VALUES (49, 5, '山西省', '2015-07-30 11:52:58', NULL, '140000', '140000');
INSERT INTO `dictionary` VALUES (50, 5, '内蒙古自治区', '2015-07-30 11:53:32', NULL, '150000', '150000');
INSERT INTO `dictionary` VALUES (51, 5, '辽宁省', '2015-07-30 11:53:52', NULL, '210000', '210000');
INSERT INTO `dictionary` VALUES (52, 5, '吉林省', '2015-07-30 11:54:07', NULL, '220000', '220000');
INSERT INTO `dictionary` VALUES (53, 5, '黑龙江省', '2015-07-30 11:54:27', NULL, '230000', '230000');
INSERT INTO `dictionary` VALUES (54, 5, '上海市', '2015-07-30 11:54:44', NULL, '310000', '310000');
INSERT INTO `dictionary` VALUES (55, 5, '江苏省', '2015-07-30 11:55:02', NULL, '320000', '320000');
INSERT INTO `dictionary` VALUES (56, 5, '浙江省', '2015-07-30 11:55:17', NULL, '330000', '330000');
INSERT INTO `dictionary` VALUES (57, 5, '安徽省', '2015-07-30 11:55:38', NULL, '340000', '340000');
INSERT INTO `dictionary` VALUES (58, 5, '福建省', '2015-07-30 11:55:56', NULL, '350000', '350000');
INSERT INTO `dictionary` VALUES (59, 5, '江西省', '2015-07-30 11:56:13', NULL, '360000', '360000');
INSERT INTO `dictionary` VALUES (60, 5, '山东省', '2015-07-30 11:56:30', NULL, '370000', '370000');
INSERT INTO `dictionary` VALUES (61, 5, '河南省', '2015-07-30 11:56:47', NULL, '410000', '410000');
INSERT INTO `dictionary` VALUES (62, 5, '湖北省', '2015-07-30 11:57:02', NULL, '420000', '420000');
INSERT INTO `dictionary` VALUES (63, 5, '湖南省', '2015-07-30 11:57:20', NULL, '430000', '430000');
INSERT INTO `dictionary` VALUES (64, 5, '广东省', '2015-07-30 11:57:36', NULL, '440000', '440000');
INSERT INTO `dictionary` VALUES (65, 5, '广西壮族自治区', '2015-07-30 11:58:02', NULL, '450000', '450000');
INSERT INTO `dictionary` VALUES (66, 5, '海南省', '2015-07-30 11:58:30', NULL, '460000', '460000');
INSERT INTO `dictionary` VALUES (67, 5, '重庆市', '2015-07-30 11:58:47', NULL, '500000', '500000');
INSERT INTO `dictionary` VALUES (68, 5, '四川省', '2015-07-30 11:59:12', NULL, '510000', '510000');
INSERT INTO `dictionary` VALUES (69, 5, '贵州省', '2015-07-30 11:59:33', NULL, '520000', '520000');
INSERT INTO `dictionary` VALUES (70, 5, '云南省', '2015-07-30 11:59:52', NULL, '530000', '530000');
INSERT INTO `dictionary` VALUES (71, 5, '藏族自治区', '2015-07-30 12:00:18', NULL, '540000', '540000');
INSERT INTO `dictionary` VALUES (72, 5, '陕西省', '2015-07-30 12:00:35', NULL, '610000', '610000');
INSERT INTO `dictionary` VALUES (73, 5, '甘肃省', '2015-07-30 12:01:02', NULL, '620000', '620000');
INSERT INTO `dictionary` VALUES (74, 5, '青海省', '2015-07-30 12:01:26', NULL, '630000', '630000');
INSERT INTO `dictionary` VALUES (75, 5, '宁夏回族自治区', '2015-07-30 12:02:12', NULL, '640000', '640000');
INSERT INTO `dictionary` VALUES (76, 5, '新疆维吾尔族自治区', '2015-07-30 12:02:44', NULL, '650000', '650000');
INSERT INTO `dictionary` VALUES (77, 5, '台湾省', '2015-07-30 12:03:12', NULL, '710000', '710000');
INSERT INTO `dictionary` VALUES (78, 5, '香港特别行政区', '2015-07-30 12:03:42', NULL, '720000', '720000');
INSERT INTO `dictionary` VALUES (79, 5, '澳门特别行政区', '2015-07-30 12:04:06', NULL, '730000', '730000');
INSERT INTO `dictionary` VALUES (80, 6, '12V', '2015-07-30 22:59:11', NULL, '', '1');
INSERT INTO `dictionary` VALUES (81, 6, '24V', '2015-07-30 22:59:20', NULL, '', '2');
INSERT INTO `dictionary` VALUES (82, 7, '呼入', '2015-07-31 11:08:06', NULL, '', '1');
INSERT INTO `dictionary` VALUES (83, 7, '呼出', '2015-07-31 11:08:17', NULL, '', '2');
INSERT INTO `dictionary` VALUES (84, 7, '呼入呼出', '2015-07-31 11:08:37', NULL, '', '3');
INSERT INTO `dictionary` VALUES (85, 9, '联XXX', '2015-08-15 10:03:58', 0, '', '1');
INSERT INTO `dictionary` VALUES (86, 9, '翰XXX', '2015-08-15 10:04:05', 0, '', '2');
INSERT INTO `dictionary` VALUES (87, 9, '世XXX', '2015-08-15 10:04:12', 0, '', '3');
INSERT INTO `dictionary` VALUES (88, 8, '星XXX', '2015-08-15 10:06:29', 0, '', '1');
INSERT INTO `dictionary` VALUES (89, 8, 'XA-TY-101', '2015-07-31 15:39:22', 88, '', '11');
INSERT INTO `dictionary` VALUES (90, 8, 'XA-BJ-101', '2015-07-31 15:41:11', 88, '', '12');
INSERT INTO `dictionary` VALUES (91, 8, '翰XXX', '2015-08-15 10:08:15', 0, '', '2');
INSERT INTO `dictionary` VALUES (92, 8, 'HS-BB-454', '2015-07-31 15:40:44', 91, '', '21');
INSERT INTO `dictionary` VALUES (93, 10, '巡逻队', '2015-07-31 20:25:02', NULL, '', '1');
INSERT INTO `dictionary` VALUES (94, 10, '后勤运输队', '2015-07-31 20:25:27', NULL, '', '2');
INSERT INTO `dictionary` VALUES (95, 11, '日常保养', '2015-08-01 14:06:30', NULL, '', '1');
INSERT INTO `dictionary` VALUES (96, 11, '专业保养', '2015-08-01 14:06:55', NULL, '', '2');
INSERT INTO `dictionary` VALUES (97, 12, '加油站', '2015-08-05 18:28:58', NULL, '', '1');
INSERT INTO `dictionary` VALUES (98, 12, '收费站', '2015-08-05 18:29:08', NULL, '', '2');
INSERT INTO `dictionary` VALUES (99, 12, '停车场', '2015-08-05 18:29:20', NULL, '', '3');
INSERT INTO `dictionary` VALUES (100, 13, '身份证', '2015-08-06 15:10:10', NULL, '', '1');
INSERT INTO `dictionary` VALUES (101, 13, '驾驶证', '2015-08-06 15:10:19', NULL, '', '2');
INSERT INTO `dictionary` VALUES (102, 13, '学生证', '2015-08-06 15:10:29', NULL, '', '3');
INSERT INTO `dictionary` VALUES (103, 8, 'HS-BJ-454', '2015-08-15 10:31:05', 91, '', '22');
INSERT INTO `dictionary` VALUES (104, 14, '通知客户', '2015-08-28 08:33:49', NULL, '', '1');
INSERT INTO `dictionary` VALUES (105, 14, '没联系上', '2015-08-28 08:34:08', NULL, '', '2');
INSERT INTO `dictionary` VALUES (106, 14, '设备故障', '2015-08-28 08:34:23', NULL, '', '3');
INSERT INTO `dictionary` VALUES (107, 14, '修车', '2015-08-28 08:34:33', NULL, '', '4');
INSERT INTO `dictionary` VALUES (108, 14, '不处理', '2015-08-28 08:34:42', NULL, '', '5');
INSERT INTO `dictionary` VALUES (109, 14, '将来处理', '2015-08-28 08:34:57', NULL, '', '6');
INSERT INTO `dictionary` VALUES (110, 14, '其他', '2015-08-28 08:35:06', NULL, '', '7');
INSERT INTO `dictionary` VALUES (111, 13, '工作证', '2015-11-09 17:29:55', NULL, '', '4');
INSERT INTO `dictionary` VALUES (112, 14, '777', '2018-03-19 14:50:35', NULL, '777', '777');
INSERT INTO `dictionary` VALUES (113, 14, '777777', '2018-03-19 14:51:04', NULL, '7777', '7777');
INSERT INTO `dictionary` VALUES (114, 14, '555', '2018-03-19 14:51:15', 112, '555', '45555');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `SEX` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `IDTYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件号',
  `DRIVINGLICENSENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾驶证号',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `DRIVINGLICENSEEXPIRYDATE` date NULL DEFAULT NULL COMMENT '驾驶证有效日期',
  `PHOTO` blob NULL COMMENT '相片',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册时间\r\n',
  `PERMITCODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机准驾证号\r\n',
  `EMERGENCYCONTACTA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人1\r\n',
  `EMERGENCYCONTACTB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人2\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('5a72ae0c3d769a75b6309e67', '558ffc6603c70e31a2a53a30', '杨先生', '男', '', '身份证', '511333290314302455', 'C01921', '', '2018-02-01 14:05:00', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5a7419353d769a7dcc878df5', '558ffc6603c70e31a2a53a30', '李先生', '男', '', '', '', '39021', '', '2018-02-02 15:54:29', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5aaf61c6e6c6170930f59514', '558ffc6603c70e31a2a53a30', 'rere', '女', 'ewr', '身份证', 'rewrwe', 'ewrewr', '', '2018-03-19 15:07:50', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for eventmenu
-- ----------------------------
DROP TABLE IF EXISTS `eventmenu`;
CREATE TABLE `eventmenu`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `EVENTID` tinyint(3) UNSIGNED NOT NULL COMMENT '类型',
  `CONTENT` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件内容',
  PRIMARY KEY (`DEVICENUMBER`, `EVENTID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for eventreport
-- ----------------------------
DROP TABLE IF EXISTS `eventreport`;
CREATE TABLE `eventreport`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TIME` datetime(0) NOT NULL COMMENT '时间',
  `EVENTID` tinyint(4) NOT NULL COMMENT '事件id',
  `CONTENT` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件内容',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER_TIME_READED`(`DEVICENUMBER`, `TIME`, `READED`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件报告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '信息类型',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信息内容',
  `STARTTIME` datetime(0) NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime(0) NOT NULL COMMENT '结束时间',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `USERID_USERTIME`(`USERID`, `USERTIME`) USING BTREE,
  INDEX `STARTTIME_ENDTIME`(`STARTTIME`, `ENDTIME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infomenu
-- ----------------------------
DROP TABLE IF EXISTS `infomenu`;
CREATE TABLE `infomenu`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '类型',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infomenuindevice
-- ----------------------------
DROP TABLE IF EXISTS `infomenuindevice`;
CREATE TABLE `infomenuindevice`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '信息类型',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信息名称',
  `ACTION` tinyint(3) UNSIGNED NOT NULL COMMENT '操作',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `SENDTIME` datetime(0) NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息菜单设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infoservice
-- ----------------------------
DROP TABLE IF EXISTS `infoservice`;
CREATE TABLE `infoservice`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '信息类型',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信息内容',
  `STARTTIME` datetime(0) NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime(0) NOT NULL COMMENT '结束时间',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `SENDTIME` datetime(0) NOT NULL COMMENT '发送时间',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `STARTTIME_ENDTIME`(`STARTTIME`, `ENDTIME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息服务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for infosubscribe
-- ----------------------------
DROP TABLE IF EXISTS `infosubscribe`;
CREATE TABLE `infosubscribe`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `INFOTYPE` tinyint(4) NOT NULL COMMENT '信息类型',
  PRIMARY KEY (`DEVICENUMBER`, `INFOTYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息订阅' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for insurance
-- ----------------------------
DROP TABLE IF EXISTS `insurance`;
CREATE TABLE `insurance`  (
  `INSURANCECOMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆保险公司',
  `POLICYNO` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险单号\r\n',
  `POLICYEXPIRENOTIFYDATE` datetime(0) NULL DEFAULT NULL COMMENT '保险到期提醒时间\r\n',
  `INSURANCECOMPANYCONTACT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险公司联系人\r\n',
  `PREMIUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保费金额\r\n',
  `POLICYPIC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险单照片\r\n',
  `INSURANCECOMPANYTEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险公司电话\r\n',
  `PERIODVALIDITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险有效期\r\n'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for investor
-- ----------------------------
DROP TABLE IF EXISTS `investor`;
CREATE TABLE `investor`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `INVESTOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客商名称\r\n',
  `INVESTORNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客商姓名\r\n',
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'E-Mail\r\n',
  `FAX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真\r\n',
  `ADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客商地址\r\n',
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客商电话\r\n',
  `ZIPCODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编\r\n',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册日期\r\n',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表\r\n',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `TIME` datetime(0) NOT NULL COMMENT '时间',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `OPERATION` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作',
  INDEX `TIME_COMPANYID`(`TIME`, `COMPANYID`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户操作日志表' ROW_FORMAT = Dynamic;

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
INSERT INTO `log` VALUES ('2018-03-19 14:15:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-19 14:16:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-19 14:16:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-19 14:16:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:16:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:16:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:17:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:17:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:20:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:20:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:21:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:21:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:21:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:21:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:21:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:22:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:22:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:28:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:31:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:45:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:46:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:46:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:46:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:46:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:48:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:48:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:48:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:48:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:49:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-19 14:50:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开历史上线率统计页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开当前在线率统计页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-03-19 14:50:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-03-19 14:51:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-03-19 14:51:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-03-19 14:51:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-03-19 14:51:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '上传车辆图标');
INSERT INTO `log` VALUES ('2018-03-19 14:51:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:51:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-03-19 14:51:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆图标');
INSERT INTO `log` VALUES ('2018-03-19 14:51:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '上传车辆图标');
INSERT INTO `log` VALUES ('2018-03-19 14:52:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:52:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建地图图层');
INSERT INTO `log` VALUES ('2018-03-19 14:52:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '添加圆形区域');
INSERT INTO `log` VALUES ('2018-03-19 14:52:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-03-19 14:53:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:53:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的兴趣点');
INSERT INTO `log` VALUES ('2018-03-19 14:55:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 14:55:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-19 14:55:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-19 14:55:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 14:55:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:55:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:56:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路线');
INSERT INTO `log` VALUES ('2018-03-19 14:56:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 14:56:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:56:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:57:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 14:58:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:58:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-03-19 14:59:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-03-19 14:59:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的多边形区域');
INSERT INTO `log` VALUES ('2018-03-19 15:00:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '多边形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:00:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的矩形区域');
INSERT INTO `log` VALUES ('2018-03-19 15:00:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '矩形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:00:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建圆形区域');
INSERT INTO `log` VALUES ('2018-03-19 15:00:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:00:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:00:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 15:02:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:02:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:02:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 15:02:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:02:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:02:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:02:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-19 15:02:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:03:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:04:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-19 15:04:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆保养记录');
INSERT INTO `log` VALUES ('2018-03-19 15:04:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:04:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-19 15:04:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-03-19 15:05:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:05:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-03-19 15:06:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-03-19 15:06:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:06:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-03-19 15:06:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:06:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:06:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-19 15:06:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-03-19 15:07:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:07:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-03-19 15:07:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:07:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-03-19 15:07:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:08:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的用户');
INSERT INTO `log` VALUES ('2018-03-19 15:08:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-03-19 15:09:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-03-19 15:09:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-03-19 15:09:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:09:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:10:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:10:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:10:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-19 15:10:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-03-19 15:10:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:10:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:10:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:11:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:11:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-03-19 15:11:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-03-19 15:12:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:12:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:17:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:17:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:17:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:17:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:18:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:18:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:18:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:19:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:19:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:19:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:19:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:20:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:21:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:21:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:22:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:31:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-19 15:31:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:31:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:32:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:32:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:34:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:34:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:34:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路段');
INSERT INTO `log` VALUES ('2018-03-19 15:35:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定路段');
INSERT INTO `log` VALUES ('2018-03-19 15:35:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路线');
INSERT INTO `log` VALUES ('2018-03-19 15:35:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的兴趣点');
INSERT INTO `log` VALUES ('2018-03-19 15:35:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:35:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:36:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的多边形区域');
INSERT INTO `log` VALUES ('2018-03-19 15:36:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '多边形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:36:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:36:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:36:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:36:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改路段');
INSERT INTO `log` VALUES ('2018-03-19 15:36:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:36:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的路线');
INSERT INTO `log` VALUES ('2018-03-19 15:36:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:37:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:39:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:39:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:40:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:40:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:40:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:40:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:40:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:51:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:51:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:52:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定路段');
INSERT INTO `log` VALUES ('2018-03-19 15:52:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:52:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:52:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定路段');
INSERT INTO `log` VALUES ('2018-03-19 15:52:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 15:52:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:52:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:52:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:53:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:53:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:56:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:56:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:56:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定路段');
INSERT INTO `log` VALUES ('2018-03-19 15:57:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线解除路段');
INSERT INTO `log` VALUES ('2018-03-19 15:57:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:57:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:57:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:57:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '路线绑定路段');
INSERT INTO `log` VALUES ('2018-03-19 15:57:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:57:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:57:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:58:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 15:58:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:58:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:59:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 15:59:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 16:00:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 16:00:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 16:01:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 16:02:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 16:02:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 16:04:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 16:04:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 16:04:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-03-19 16:04:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-19 16:04:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-19 16:04:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:42:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-21 16:42:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 16:42:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:43:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:43:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-03-21 16:46:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:46:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的角色');
INSERT INTO `log` VALUES ('2018-03-21 16:47:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-03-21 16:47:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除角色');
INSERT INTO `log` VALUES ('2018-03-21 16:47:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-21 16:47:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-21 16:47:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-21 16:47:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-21 16:47:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:47:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:48:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-03-21 16:48:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-03-21 16:48:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-03-21 16:48:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-03-21 16:49:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:49:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-03-21 16:49:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:49:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-03-21 16:49:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:49:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车队');
INSERT INTO `log` VALUES ('2018-03-21 16:50:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:50:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-21 16:50:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-21 16:50:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-21 16:51:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆保养记录');
INSERT INTO `log` VALUES ('2018-03-21 16:51:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-21 16:51:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建圆形区域');
INSERT INTO `log` VALUES ('2018-03-21 16:51:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建圆形区域');
INSERT INTO `log` VALUES ('2018-03-21 16:51:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-03-21 16:51:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 16:51:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 16:51:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:02:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:03:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:03:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:03:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:03:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:04:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:04:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:05:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 17:05:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:05:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 17:05:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:05:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的用户');
INSERT INTO `log` VALUES ('2018-03-21 17:08:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:09:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-03-21 17:12:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:16:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:16:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:16:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 17:16:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:16:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-03-21 17:16:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:17:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:17:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车队');
INSERT INTO `log` VALUES ('2018-03-21 17:18:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:18:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-21 17:22:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-21 17:24:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-21 17:34:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 17:34:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:34:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-21 17:34:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:34:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-03-21 17:35:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-21 17:36:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-21 17:36:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-22 08:45:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-22 09:02:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-22 10:02:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-22 10:10:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-23 09:18:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-23 09:19:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:19:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:19:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:19:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:23:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:24:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:25:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:25:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:26:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:26:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:26:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:26:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:26:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-03-23 09:32:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:32:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:33:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-23 09:34:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-23 09:34:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:34:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:35:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:39:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:39:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:39:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:39:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:39:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:40:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-23 09:40:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:40:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-23 09:45:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:45:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:45:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:58:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:58:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 09:58:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 09:58:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 10:18:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 10:18:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-23 10:28:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-23 10:31:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-23 10:31:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 15:57:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 15:59:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:04:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:07:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:09:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:10:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:13:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:13:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:16:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:20:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:21:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:21:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:24:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1');
INSERT INTO `log` VALUES ('2018-03-26 16:52:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:52:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:52:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:53:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 16:54:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 17:01:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-26 17:27:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-26 17:27:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-27 09:00:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开历史在线率统计页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 09:01:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-27 10:42:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除角色');
INSERT INTO `log` VALUES ('2018-03-27 10:46:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 10:46:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 10:46:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 10:46:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-27 17:25:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-27 17:25:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-27 17:25:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-27 17:25:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:24:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:24:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:44:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:44:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:51:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:51:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:55:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:55:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:56:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-03-28 09:59:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 09:59:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:05:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:05:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:05:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:06:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:07:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:10:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:11:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:11:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:12:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:13:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:14:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:15:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:17:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:17:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 14:18:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 14:18:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-28 14:18:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-28 14:18:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:45:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:45:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:48:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-03-28 14:57:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:58:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 14:58:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:00:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:00:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:07:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:15:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:15:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:20:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:23:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:24:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:25:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:25:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:25:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:25:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:26:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:27:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:27:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:28:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:28:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:28:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:28:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:28:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:29:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:29:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:30:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:30:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:30:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:30:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:30:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:34:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:36:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:40:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:41:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:41:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:41:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:44:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:47:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:47:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:48:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:48:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:49:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:49:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:51:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:52:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:52:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:52:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:52:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:56:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:56:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:57:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:57:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:57:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 15:57:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 15:58:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:00:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:02:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:02:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:02:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:02:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-28 16:02:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:06:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:06:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:07:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:10:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:11:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:11:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:11:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:11:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:12:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:14:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:15:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:16:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:17:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:17:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:17:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:23:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:23:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:24:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:24:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:26:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:26:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:26:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:26:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:27:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:27:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:27:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:28:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:30:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:30:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:30:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:30:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:31:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:31:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:32:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:33:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:33:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:33:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:33:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:33:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:35:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:35:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:35:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:35:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:35:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:36:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-28 16:42:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:43:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:48:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:48:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:50:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:50:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:51:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:51:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:51:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:51:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:53:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:53:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:54:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:54:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:56:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:56:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:56:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:56:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:57:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:57:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:58:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:58:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:58:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:58:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:58:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 16:59:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 16:59:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 17:00:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 17:00:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-28 17:03:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 17:03:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-28 17:03:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 17:03:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-28 17:10:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-28 17:10:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 08:40:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-29 08:40:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 08:40:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-29 08:40:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 08:40:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 08:41:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:08:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 09:08:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:12:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 09:13:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:29:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 09:29:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:29:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:29:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-29 09:30:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-29 11:22:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 11:22:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 11:25:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-03-29 11:36:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 11:36:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 11:55:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:05:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:05:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-29 14:06:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:06:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:06:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:06:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:06:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-03-29 14:11:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:11:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:14:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:14:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:15:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:15:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:24:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:24:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:25:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:25:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:25:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:25:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:26:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:26:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:26:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:29:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:29:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:30:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 14:30:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:34:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建企业资料');
INSERT INTO `log` VALUES ('2018-03-29 14:39:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-03-29 14:40:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:40:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-29 14:40:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-03-29 15:05:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:05:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:05:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:05:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:05:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:06:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-03-29 15:06:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:06:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:49:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:49:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:49:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:50:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:50:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:50:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:52:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:52:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 15:52:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 15:54:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 16:06:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 16:06:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-29 16:40:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-29 16:40:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆id',
  `DONEDATE` date NOT NULL COMMENT '保养日期',
  `TYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保养类型',
  `CONTENT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保养内容',
  `MILEAGE` int(11) NOT NULL COMMENT '保养里程',
  `FEE` decimal(10, 2) NOT NULL COMMENT '保养费用',
  `GARAGE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保养单位',
  `AGENT` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '经办人',
  `NEXTMILEAGE` int(11) NOT NULL COMMENT '下次保养里程(下次里程保养数)',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员ID',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员姓名',
  `USERTIME` datetime(0) NOT NULL COMMENT '操作时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `NEXTDATE` date NULL DEFAULT NULL COMMENT '下次保养日期(下次里程保养提醒时间)',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `VEHICLEID_DONEDATE`(`VEHICLEID`, `DONEDATE`) USING BTREE,
  INDEX `VEHICLEID_NEXTDATE`(`VEHICLEID`, `NEXTDATE`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '保养记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES ('5a72ad493d769a75b6309e3d', '558ffc6603c70e31a2a53a30', '5a72acea3d769a75b6309e23', '2018-02-15', '', '', 0, 0.00, '', '', 0, '558ffc6603c70e31a2a53a30', '系统管理员', '2018-02-01 14:01:45', '2018-02-01 14:01:45', NULL);
INSERT INTO `maintain` VALUES ('5aaf6108e6c6170930f594c3', '558ffc6603c70e31a2a53a30', '5aa741df7b03680b92edf012', '2018-03-06', '', '', 0, 0.00, '', '', 0, '5a72c8733d769a75b6309ff0', 'rayton02', '2018-03-19 15:04:40', '2018-03-19 15:04:40', '2018-03-06');
INSERT INTO `maintain` VALUES ('5ab21cfee6c6171190178161', '558ffc6603c70e31a2a53a30', '5aaf6182e6c6170930f59511', '2018-03-06', '', '', 0, 0.00, '', '', 0, '558ffc6603c70e31a2a53a30', '系统管理员', '2018-03-21 16:51:10', '2018-03-21 16:51:10', NULL);

-- ----------------------------
-- Table structure for maplayer
-- ----------------------------
DROP TABLE IF EXISTS `maplayer`;
CREATE TABLE `maplayer`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `VISIBLE` bit(1) NOT NULL COMMENT '可见否',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USERID`(`USERID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地图图层' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maplayer
-- ----------------------------
INSERT INTO `maplayer` VALUES ('5aaf5e23e6c6170930f59413', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', '7777', b'1', '7777', '2018-03-19 14:52:19');

-- ----------------------------
-- Table structure for motorcade
-- ----------------------------
DROP TABLE IF EXISTS `motorcade`;
CREATE TABLE `motorcade`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录ID',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司ID',
  `TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车队类型',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `PARENTVISIBLE` bit(1) NOT NULL COMMENT '上级可见否',
  `LINKMAN` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车队联系人',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车队联系电话',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `REGISTATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车队' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of motorcade
-- ----------------------------
INSERT INTO `motorcade` VALUES ('5a72abd83d769a75b6309dee', '558ffc6603c70e31a2a53a30', '后勤运输队', '测试车队', b'1', '', '', '', '2018-02-01 14:01:19', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5aaf618ee6c6170930f59512', '558ffc6603c70e31a2a53a30', '巡逻队', 'tyty', b'0', '', '', '', '2018-03-19 15:06:54', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ab21cb5e6c617119017815e', '558ffc6603c70e31a2a53a30', '后勤运输队', 'qweqwe', b'0', '', '', '', '2018-03-21 16:49:57', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ab22346e6c61711901781c9', '558ffc6603c70e31a2a53a30', '巡逻队', 'eerwer', b'0', '', '', '', '2018-03-21 17:17:58', NULL, NULL);

-- ----------------------------
-- Table structure for multimediaeventreport
-- ----------------------------
DROP TABLE IF EXISTS `multimediaeventreport`;
CREATE TABLE `multimediaeventreport`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TIME` datetime(0) NOT NULL COMMENT '报告时间',
  `MEDIAID` int(10) UNSIGNED NOT NULL COMMENT '多媒体ＩＤ',
  `MEDIATYPE` tinyint(4) NOT NULL COMMENT '多媒体类型',
  `FORMATTYPE` tinyint(4) NOT NULL COMMENT '格式类型',
  `EVENTTYPE` tinyint(4) NOT NULL COMMENT '事件类型',
  `CHANNELID` tinyint(4) NOT NULL COMMENT '通道ＩＤ',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER_TIME_READED`(`DEVICENUMBER`, `TIME`, `READED`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多媒体事件报告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for multimediaretrieval
-- ----------------------------
DROP TABLE IF EXISTS `multimediaretrieval`;
CREATE TABLE `multimediaretrieval`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TIME` datetime(0) NOT NULL COMMENT '时间',
  `MEDIAID` int(10) UNSIGNED NOT NULL COMMENT '多媒体ＩＤ',
  `MEDIATYPE` tinyint(4) NOT NULL COMMENT '多媒体类型',
  `EVENTTYPE` tinyint(4) NOT NULL COMMENT '事件类型',
  `CHANNELID` tinyint(4) NOT NULL COMMENT '通道ＩＤ',
  `LNG` decimal(9, 6) NOT NULL COMMENT '经度',
  `LAT` decimal(9, 6) NOT NULL COMMENT '纬度',
  `ALT` smallint(6) UNSIGNED NOT NULL COMMENT '海拔',
  `SPEED` float UNSIGNED NOT NULL COMMENT '速度',
  `ANGLE` smallint(6) UNSIGNED NOT NULL COMMENT '方向',
  `ALARMS` int(11) UNSIGNED NOT NULL COMMENT '报警',
  `STATUS` int(11) UNSIGNED NOT NULL COMMENT '状态',
  PRIMARY KEY (`DEVICENUMBER`, `TIME`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多媒体检索' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `OWNERNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `IDTYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件编号',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `CONTACT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车主联系人\r\n',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册时间\r\n',
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车主手机号\r\n',
  `FAX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真\r\n',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车主资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('5a7419293d769a7dcc878df4', '558ffc6603c70e31a2a53a30', '李生', '', '', '2018-03-21 16:43:55', '5555555', NULL, NULL, NULL, NULL);
INSERT INTO `owner` VALUES ('5aaf61b2e6c6170930f59513', '558ffc6603c70e31a2a53a30', 'eeeeeeeeeeee', '驾驶证', 'rrrrrr', '2018-03-21 16:50:37', '5555555', NULL, NULL, NULL, NULL);
INSERT INTO `owner` VALUES ('5ab21bf8e6c6171190178124', '558ffc6603c70e31a2a53a30', 'esdfsdf', '', '', '2018-03-21 16:46:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `owner` VALUES ('5ab22152e6c61711901781ae', '558ffc6603c70e31a2a53a30', 'sdf', '', '', '2018-03-21 17:09:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `owner` VALUES ('5ab457d8e6c617043ceb938b', '558ffc6603c70e31a2a53a30', 're', '', '', '2018-03-23 09:26:48', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permissionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限中文名',
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('baseinfo', '基础信息', '');
INSERT INTO `permission` VALUES ('baseinfo.circleArea', '圆形区域', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.circleArea.addVehicles', '圆形区域绑定车辆', 'baseinfo.circleArea');
INSERT INTO `permission` VALUES ('baseinfo.circleArea.create', '创建圆形区域', 'baseinfo.circleArea');
INSERT INTO `permission` VALUES ('baseinfo.circleArea.delete', '删除圆形区域', 'baseinfo.circleArea');
INSERT INTO `permission` VALUES ('baseinfo.circleArea.removeVehicle', '圆形区域解除车辆', 'baseinfo.circleArea');
INSERT INTO `permission` VALUES ('baseinfo.circleArea.update', '修改圆形区域', 'baseinfo.circleArea');
INSERT INTO `permission` VALUES ('baseinfo.company', '企业管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.company.authorize', '企业授权', 'baseinfo.company');
INSERT INTO `permission` VALUES ('baseinfo.company.create', '创建企业资料', 'baseinfo.company');
INSERT INTO `permission` VALUES ('baseinfo.company.delete', '删除企业资料', 'baseinfo.company');
INSERT INTO `permission` VALUES ('baseinfo.company.update', '修改企业资料', 'baseinfo.company');
INSERT INTO `permission` VALUES ('baseinfo.device', '终端管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.device.create', '创建新的终端资料', 'baseinfo.device');
INSERT INTO `permission` VALUES ('baseinfo.device.delete', '删除终端资料', 'baseinfo.device');
INSERT INTO `permission` VALUES ('baseinfo.device.update', '修改终端资料', 'baseinfo.device');
INSERT INTO `permission` VALUES ('baseinfo.dictionary', '数据字典', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.dictionary.create', '创建新的字典项', 'baseinfo.dictionary');
INSERT INTO `permission` VALUES ('baseinfo.dictionary.delete', '删除字典项', 'baseinfo.dictionary');
INSERT INTO `permission` VALUES ('baseinfo.dictionary.update', '修改字典项', 'baseinfo.dictionary');
INSERT INTO `permission` VALUES ('baseinfo.driver', '驾驶员管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.driver.addVehicles', '驾驶员绑定车辆', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.create', '创建新的驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.delete', '删除驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.removeVehicle', '驾驶员解除车辆', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.update', '修改驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.maintain', '车辆保养', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.maintain.create', '创建新的车辆保养记录', 'baseinfo.maintain');
INSERT INTO `permission` VALUES ('baseinfo.maintain.delete', '删除车辆保养记录', 'baseinfo.maintain');
INSERT INTO `permission` VALUES ('baseinfo.maintain.update', '修改车辆保养记录', 'baseinfo.maintain');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer', '地图图层管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.addCircleAreas', '添加圆形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.addPois', '添加兴趣点', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.addPolygonAreas', '添加多边形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.addRectangleAreas', '添加矩形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.addRouteAreas', '添加路线', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.create', '创建地图图层', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.delete', '删除地图图层', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.removeCircleArea', '移除圆形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.removePoi', '移除兴趣点', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.removePolygonArea', '移除多边形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.removeRectangleArea', '移除矩形区域', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.removeRouteArea', '移除路线', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapLayer.update', '修改地图图层', 'baseinfo.mapLayer');
INSERT INTO `permission` VALUES ('baseinfo.mapOption', '地图设置', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.marker', '车辆图标', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.marker.remove', '删除车辆图标', 'baseinfo.marker');
INSERT INTO `permission` VALUES ('baseinfo.marker.save', '上传车辆图标', 'baseinfo.marker');
INSERT INTO `permission` VALUES ('baseinfo.motorcade', '车队管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.motorcade.create', '创建新的车队', 'baseinfo.motorcade');
INSERT INTO `permission` VALUES ('baseinfo.motorcade.delete', '删除车队资料', 'baseinfo.motorcade');
INSERT INTO `permission` VALUES ('baseinfo.motorcade.update', '更新车队资料', 'baseinfo.motorcade');
INSERT INTO `permission` VALUES ('baseinfo.owner', '车主管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.owner.addVehicles', '车主绑定车辆', 'baseinfo.owner');
INSERT INTO `permission` VALUES ('baseinfo.owner.create', '创建新的车主资料', 'baseinfo.owner');
INSERT INTO `permission` VALUES ('baseinfo.owner.delete', '删除车主资料', 'baseinfo.owner');
INSERT INTO `permission` VALUES ('baseinfo.owner.removeVehicle', '车主解除车辆', 'baseinfo.owner');
INSERT INTO `permission` VALUES ('baseinfo.owner.update', '修改车主资料', 'baseinfo.owner');
INSERT INTO `permission` VALUES ('baseinfo.poi', '兴趣点', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.poi.create', '创建新的兴趣点', 'baseinfo.poi');
INSERT INTO `permission` VALUES ('baseinfo.poi.delete', '删除兴趣点', 'baseinfo.poi');
INSERT INTO `permission` VALUES ('baseinfo.poi.update', '更新兴趣点', 'baseinfo.poi');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea', '多边形区域', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea.addVehicles', '多边形区域绑定车辆', 'baseinfo.polygonArea');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea.create', '创建新的多边形区域', 'baseinfo.polygonArea');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea.delete', '删除多边形区域', 'baseinfo.polygonArea');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea.removeVehicle', '多边形区域解除车辆', 'baseinfo.polygonArea');
INSERT INTO `permission` VALUES ('baseinfo.polygonArea.update', '修改多边形区域', 'baseinfo.polygonArea');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea', '矩形区域', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea.addVehicles', '矩形区域绑定车辆', 'baseinfo.rectangleArea');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea.create', '创建新的矩形区域', 'baseinfo.rectangleArea');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea.delete', '删除矩形区域', 'baseinfo.rectangleArea');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea.removeVehicle', '矩形区域解除车辆', 'baseinfo.rectangleArea');
INSERT INTO `permission` VALUES ('baseinfo.rectangleArea.update', '修改矩形区域', 'baseinfo.rectangleArea');
INSERT INTO `permission` VALUES ('baseinfo.role', '角色管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.role.authorize', '角色授权', 'baseinfo.role');
INSERT INTO `permission` VALUES ('baseinfo.role.create', '创建新的角色', 'baseinfo.role');
INSERT INTO `permission` VALUES ('baseinfo.role.delete', '删除角色资料', 'baseinfo.role');
INSERT INTO `permission` VALUES ('baseinfo.role.update', '修改角色资料', 'baseinfo.role');
INSERT INTO `permission` VALUES ('baseinfo.routeArea', '路线', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.addSections', '路线绑定路段', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.addVehicles', '路线绑定车辆', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.create', '创建新的路线', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.delete', '删除路线', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.removeSection', '路线解除路段', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.removeVehicle', '路线解除车辆', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.routeArea.update', '修改路线', 'baseinfo.routeArea');
INSERT INTO `permission` VALUES ('baseinfo.sectionArea', '路段', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.sectionArea.create', '创建新的路段', 'baseinfo.sectionArea');
INSERT INTO `permission` VALUES ('baseinfo.sectionArea.delete', '删除路段', 'baseinfo.sectionArea');
INSERT INTO `permission` VALUES ('baseinfo.sectionArea.update', '修改路段', 'baseinfo.sectionArea');
INSERT INTO `permission` VALUES ('baseinfo.simcard', 'SIM卡管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.simcard.create', '创建新的sim卡', 'baseinfo.simcard');
INSERT INTO `permission` VALUES ('baseinfo.simcard.delete', '删除sim卡', 'baseinfo.simcard');
INSERT INTO `permission` VALUES ('baseinfo.simcard.update', '修改sim卡', 'baseinfo.simcard');
INSERT INTO `permission` VALUES ('baseinfo.user', '用户管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.user.addMonitors', '用户分配监控对象', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.addRoles', '用户绑定角色', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.create', '创建新的用户', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.delete', '删除用户资料', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.removeMonitor', '用户解除监控对象', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.removeRole', '用户解除角色', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.user.update', '修改用户资料', 'baseinfo.user');
INSERT INTO `permission` VALUES ('baseinfo.vehicle', '车辆管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.addDrivers', '车辆绑定驾驶员', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.addOwners', '车辆绑定车主', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.create', '创建新的车辆', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.delete', '删除车辆资料', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.removeDriver', '车辆解除驾驶员', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.removeOwner', '车辆解除车主', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('baseinfo.vehicle.update', '修改车辆资料', 'baseinfo.vehicle');
INSERT INTO `permission` VALUES ('center', '监控中心', '');
INSERT INTO `permission` VALUES ('center.alarm', '报警查询', 'center');
INSERT INTO `permission` VALUES ('center.alarm.processAll', '处理全部报警', 'center.alarm');
INSERT INTO `permission` VALUES ('center.alarm.processOnce', '单次处理报警', 'center.alarm');
INSERT INTO `permission` VALUES ('center.deviceData', '数据查询', 'center');
INSERT INTO `permission` VALUES ('center.deviceData.accidentDoubtLog', '事故疑点日志', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.deviceEventReport', '事件报告', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.deviceUpgradeResultReport', '升级结果报告', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.drivingRecorder', '行驶记录仪信息', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.locateLog', '位置记录', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.loginLog', '驾驶员登签日志', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.multimedia', '多媒体数据', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.multimediaEvent', '多媒体事件', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.multimediaRetrieval', '多媒体数据检索', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.parameterLog', '参数修改日志', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.powerLog', '外部供电日志', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.speedLog', '速度记录', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.speedStatusLog', '速度状态', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.deviceData.timeoutLog', '超时驾驶日志', 'center.deviceData');
INSERT INTO `permission` VALUES ('center.locate', '实时监控', 'center');
INSERT INTO `permission` VALUES ('center.locate.datalog', '数据调试', 'center.locate');
INSERT INTO `permission` VALUES ('center.locate.instruct', '指令下发', 'center.locate');
INSERT INTO `permission` VALUES ('center.locate.vehileinfo', '车辆资料', 'center.locate');
INSERT INTO `permission` VALUES ('center.replay', '轨迹回放', 'center');
INSERT INTO `permission` VALUES ('security.saveMyinfo', '修改用户信息', 'security');
INSERT INTO `permission` VALUES ('security.saveMyKey', '修改用户密码', 'security');
INSERT INTO `permission` VALUES ('statistics', '统计分析', '');
INSERT INTO `permission` VALUES ('statistics.areaIo', '进出区域统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.areaOverspeed', '车辆区域超速', 'statistics');
INSERT INTO `permission` VALUES ('statistics.currentOnlineOffline', '当前在线率统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.historyOnlineOffline', '历史上线率统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.historyOnlineTime', '历史在线率统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.mileageOil', '行驶里程及油耗', 'statistics');
INSERT INTO `permission` VALUES ('statistics.operateLog', '操作日志', 'statistics');
INSERT INTO `permission` VALUES ('statistics.routeDeviation', '路线偏离统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.sectionOverspeed', '车辆路段超速', 'statistics');
INSERT INTO `permission` VALUES ('statistics.timeoutParking', '车辆停车超时', 'statistics');
INSERT INTO `permission` VALUES ('statistics.vehicleAlarm', '车辆警情统计', 'statistics');
INSERT INTO `permission` VALUES ('statistics.vehicleFatigueDriving', '车辆疲劳驾驶', 'statistics');
INSERT INTO `permission` VALUES ('statistics.vehicleOverspeed', '非区域超速统计', 'statistics');

-- ----------------------------
-- Table structure for phonebook
-- ----------------------------
DROP TABLE IF EXISTS `phonebook`;
CREATE TABLE `phonebook`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电话本' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for phoneindevice
-- ----------------------------
DROP TABLE IF EXISTS `phoneindevice`;
CREATE TABLE `phoneindevice`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `FLAG` tinyint(4) NOT NULL COMMENT '通话标识',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `ACTION` tinyint(4) NOT NULL COMMENT '操作类型',
  `STATUS` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电话本设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poi
-- ----------------------------
DROP TABLE IF EXISTS `poi`;
CREATE TABLE `poi`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `TYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `LNG` decimal(9, 6) NOT NULL COMMENT '经度',
  `LAT` decimal(9, 6) NOT NULL COMMENT '纬度',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '兴趣点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poi
-- ----------------------------
INSERT INTO `poi` VALUES (1, '558ffc6603c70e31a2a53a30', '加油站', '777777', 87.445769, 38.645371, '777777', '2018-03-19 14:53:47');
INSERT INTO `poi` VALUES (2, '558ffc6603c70e31a2a53a30', '', 'tgret', 86.857773, 40.129581, '', '2018-03-19 15:35:47');

-- ----------------------------
-- Table structure for polygon
-- ----------------------------
DROP TABLE IF EXISTS `polygon`;
CREATE TABLE `polygon`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) UNSIGNED NOT NULL COMMENT '属性',
  `MAXSPEED` smallint(5) UNSIGNED NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) UNSIGNED NOT NULL COMMENT '超速持续时间',
  `STARTTIME` date NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date NULL DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多边形区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of polygon
-- ----------------------------
INSERT INTO `polygon` VALUES (1, '558ffc6603c70e31a2a53a30', 'etert', b'1', 0, 0, 0, '2018-03-06', '2018-03-06', '', '2018-03-19 14:59:55');
INSERT INTO `polygon` VALUES (2, '558ffc6603c70e31a2a53a30', 'yery', b'1', 0, 0, 0, NULL, NULL, '', '2018-03-19 15:36:06');

-- ----------------------------
-- Table structure for polygonpoint
-- ----------------------------
DROP TABLE IF EXISTS `polygonpoint`;
CREATE TABLE `polygonpoint`  (
  `PID` int(10) UNSIGNED NOT NULL COMMENT '多边形编号',
  `LAT` decimal(9, 6) NOT NULL COMMENT '纬度',
  `LNG` decimal(9, 6) NOT NULL COMMENT '经度',
  INDEX `PID_INDEX`(`PID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多边形顶点项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of polygonpoint
-- ----------------------------
INSERT INTO `polygonpoint` VALUES (1, 39.334675, 88.623796);
INSERT INTO `polygonpoint` VALUES (1, 36.538442, 97.603658);
INSERT INTO `polygonpoint` VALUES (1, 34.250162, 87.151708);
INSERT INTO `polygonpoint` VALUES (1, 37.129410, 80.529488);
INSERT INTO `polygonpoint` VALUES (2, 39.562756, 82.736172);
INSERT INTO `polygonpoint` VALUES (2, 39.789985, 89.801321);
INSERT INTO `polygonpoint` VALUES (2, 36.538009, 89.506997);
INSERT INTO `polygonpoint` VALUES (2, 34.494052, 87.445936);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '标志',
  `ASK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `ANSWERID` tinyint(3) UNSIGNED NOT NULL COMMENT '答案id',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USERID_USERTIME`(`USERID`, `USERTIME`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for questionanswer
-- ----------------------------
DROP TABLE IF EXISTS `questionanswer`;
CREATE TABLE `questionanswer`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `PID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父记录唯一编号',
  `ANSWERID` tinyint(3) UNSIGNED NOT NULL COMMENT '答案编号',
  `CONTENT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案内容',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `PID`(`PID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题答案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for questionindevice
-- ----------------------------
DROP TABLE IF EXISTS `questionindevice`;
CREATE TABLE `questionindevice`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '标志',
  `ASK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题',
  `ACTION` tinyint(3) UNSIGNED NOT NULL COMMENT '操作',
  `ANSWERID` tinyint(3) UNSIGNED NOT NULL,
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `SENDTIME` datetime(0) NOT NULL COMMENT '绑定时间',
  `ACKID` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '命令正确应答id',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提问下发' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rectangle
-- ----------------------------
DROP TABLE IF EXISTS `rectangle`;
CREATE TABLE `rectangle`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) UNSIGNED NOT NULL COMMENT '属性',
  `ULLAT` decimal(9, 6) NOT NULL COMMENT '左上点纬度',
  `ULLNG` decimal(9, 6) NOT NULL COMMENT '左上点经度',
  `BRLAT` decimal(9, 6) NOT NULL COMMENT '右下点纬度',
  `BRLNG` decimal(9, 6) NOT NULL COMMENT '右下点经度',
  `MAXSPEED` smallint(5) UNSIGNED NOT NULL COMMENT '最高速度',
  `OVERSPEEDSECONDS` tinyint(3) UNSIGNED NOT NULL COMMENT '超速持续时间',
  `STARTTIME` date NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date NULL DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '矩形区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rectangle
-- ----------------------------
INSERT INTO `rectangle` VALUES (1, '558ffc6603c70e31a2a53a30', 'tdhtdghdt', b'1', 0, 36.359899, 89.212325, 36.121789, 94.364761, 0, 0, NULL, NULL, 'rdhdh', '2018-03-19 15:00:19');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司id',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `COMPANYID_NAME`(`COMPANYID`, `NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('5a72ab073d769a75b6309ded', '5a72ab073d769a75b6309dec', '企业管理员', '', '2018-02-01 13:52:07');
INSERT INTO `role` VALUES ('5a72c8883d769a75b6309ff1', '558ffc6603c70e31a2a53a30', '所有权限', '', '2018-02-01 15:58:00');
INSERT INTO `role` VALUES ('5ab21c08e6c617119017813e', '558ffc6603c70e31a2a53a30', 'ewr', 'ewrew', '2018-03-21 16:47:04');
INSERT INTO `role` VALUES ('5abc88ed05a3310acc2444de', '5abc88ed05a3310acc2444dd', '企业管理员', '', '2018-03-29 14:34:21');

-- ----------------------------
-- Table structure for roleauthorize
-- ----------------------------
DROP TABLE IF EXISTS `roleauthorize`;
CREATE TABLE `roleauthorize`  (
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业ID',
  `ROLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `PERMISSIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`, `ROLEID`, `PERMISSIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色授权表' ROW_FORMAT = Dynamic;

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
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'baseinfo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.areaIo');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.areaOverspeed');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.currentOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.historyOnlineOffline');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.historyOnlineTime');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.mileageOil');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.operateLog');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.routeDeviation');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.sectionOverspeed');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.timeoutParking');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleAlarm');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleFatigueDriving');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleOverspeed');
INSERT INTO `roleauthorize` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'test');
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
INSERT INTO `roleauthorize` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'mmp');
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
INSERT INTO `roleauthorize` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444dd', 'baseinfo');
INSERT INTO `roleauthorize` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444dd', 'test');

-- ----------------------------
-- Table structure for roleinuser
-- ----------------------------
DROP TABLE IF EXISTS `roleinuser`;
CREATE TABLE `roleinuser`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `ROLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USERID`, `ROLEID`) USING BTREE,
  INDEX `FK_roleinuser_role`(`ROLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roleinuser
-- ----------------------------
INSERT INTO `roleinuser` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309ded');
INSERT INTO `roleinuser` VALUES ('5a72c8733d769a75b6309ff0', '5a72c8883d769a75b6309ff1');
INSERT INTO `roleinuser` VALUES ('5a72eb803d769a3c631b8b5a', '5a72c8883d769a75b6309ff1');
INSERT INTO `roleinuser` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444de');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域名称',
  `DEVICECATCH` bit(1) NOT NULL COMMENT '终端计算否',
  `FLAG` smallint(5) UNSIGNED NOT NULL COMMENT '属性',
  `STARTTIME` date NULL DEFAULT NULL COMMENT '开始时间',
  `ENDTIME` date NULL DEFAULT NULL COMMENT '结束时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, '558ffc6603c70e31a2a53a30', 'yytyr', b'1', 17, '2018-03-06', '2018-03-28', 'tytytytyty', '2018-03-19 14:56:14');
INSERT INTO `route` VALUES (2, '558ffc6603c70e31a2a53a30', 'fghfgh', b'1', 0, NULL, NULL, '', '2018-03-19 15:35:32');
INSERT INTO `route` VALUES (3, '558ffc6603c70e31a2a53a30', 'etdtr', b'1', 0, NULL, NULL, '', '2018-03-19 15:36:52');

-- ----------------------------
-- Table structure for routesection
-- ----------------------------
DROP TABLE IF EXISTS `routesection`;
CREATE TABLE `routesection`  (
  `ROUTEID` int(11) UNSIGNED NOT NULL COMMENT '路线唯一编号',
  `SECTIONID` int(11) UNSIGNED NOT NULL COMMENT '路段唯一编号',
  PRIMARY KEY (`ROUTEID`, `SECTIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线路段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of routesection
-- ----------------------------
INSERT INTO `routesection` VALUES (1, 17);
INSERT INTO `routesection` VALUES (3, 17);

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '路段id',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业唯一编号',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路段名称',
  `WIDTH` tinyint(3) UNSIGNED NOT NULL COMMENT '路段宽度',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '路段属性',
  `MAXSECONDS` smallint(5) UNSIGNED NOT NULL COMMENT '路段行驶过长阈值',
  `MINSECONDS` smallint(5) UNSIGNED NOT NULL COMMENT '路段行驶不足阈值',
  `MAXSPEED` smallint(5) UNSIGNED NOT NULL COMMENT '路段最高速度',
  `OVERSPEEDSECONDS` tinyint(3) UNSIGNED NOT NULL COMMENT '路段超速持续时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES (17, '558ffc6603c70e31a2a53a30', 'dhfghy', 0, 0, 0, 0, 0, 0, '', '2018-03-19 15:36:26');

-- ----------------------------
-- Table structure for sectionpoint
-- ----------------------------
DROP TABLE IF EXISTS `sectionpoint`;
CREATE TABLE `sectionpoint`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '拐点id',
  `SECTIONID` int(10) UNSIGNED NOT NULL COMMENT '路段id',
  `LAT` decimal(9, 6) NOT NULL COMMENT '拐点纬度',
  `LNG` decimal(9, 6) NOT NULL COMMENT '拐点经度',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `SECTIONID`(`SECTIONID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线拐点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sectionpoint
-- ----------------------------
INSERT INTO `sectionpoint` VALUES (3, 17, 39.104950, 92.157241, 0);
INSERT INTO `sectionpoint` VALUES (4, 17, 36.775055, 90.832617, 1);

-- ----------------------------
-- Table structure for simcard
-- ----------------------------
DROP TABLE IF EXISTS `simcard`;
CREATE TABLE `simcard`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一编号（SIM号ID，呵呵\r\n）',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `PHONENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `SIMCARDNUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sim卡号',
  `SPEECHTYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '语音类型',
  `OPENSMS` bit(1) NOT NULL COMMENT '是否开通短信',
  `CARRIEROPERATOR` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运营商',
  `PREPAYMENT` decimal(10, 2) NOT NULL COMMENT '预交费',
  `FLOWSET` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '流量套餐',
  `CREATETIME` date NOT NULL COMMENT '创建时间',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `OPENDATE` date NULL DEFAULT NULL COMMENT '开通日期(SIM卡激活时间)',
  `PURCHASEDATE` date NULL DEFAULT NULL COMMENT '购买日期',
  `EXPIREDATE` date NULL DEFAULT NULL COMMENT '到期日期',
  `ACCOUNTNAME` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SIM卡开户名\r\n',
  `MONTHLYRENT` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SIM卡月租\r\n',
  `INSTOCKDATE` datetime(0) NULL DEFAULT NULL COMMENT 'SIM卡入库时间\r\n',
  `OUTSTOCKDATE` datetime(0) NULL DEFAULT NULL COMMENT 'SIM卡出库时间\r\n',
  `EXPIRENOTIFYDATE` datetime(0) NULL DEFAULT NULL COMMENT 'SIM卡费到期前提示时间\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `SIMCARDNUMBER`(`SIMCARDNUMBER`) USING BTREE,
  UNIQUE INDEX `PHONENUMBER`(`PHONENUMBER`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sim卡资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of simcard
-- ----------------------------
INSERT INTO `simcard` VALUES ('5a72ac0c3d769a75b6309def', '558ffc6603c70e31a2a53a30', '18011012699', '18011012699', '呼入呼出', b'1', '', 0.00, '', '2018-02-01', '', '2018-02-02 16:06:00', NULL, '2018-02-01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5a7417dd3d769a7dcc878d5b', '558ffc6603c70e31a2a53a30', '18011012700', '18011012700', '呼入呼出', b'1', '', 0.00, '', '2018-02-02', '', '2018-02-02 16:05:54', NULL, '2018-02-02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5aaf6123e6c6170930f594dd', '558ffc6603c70e31a2a53a30', '444444444', '4444444444', '呼入呼出', b'1', '', 0.00, '', '2018-03-19', '', '2018-03-19 15:06:22', '2018-03-29', '2018-02-28', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ab21c90e6c617119017815c', '558ffc6603c70e31a2a53a30', 'eerwe', 'wrewr', '', b'0', '', 0.00, '', '2018-03-21', '', '2018-03-21 16:49:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ab22308e6c61711901781c8', '558ffc6603c70e31a2a53a30', 'dxsd', 'sdsdsd', '', b'0', '', 0.00, '', '2018-03-21', '', '2018-03-21 17:16:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `STAFFNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职员姓名\r\n',
  `DEPARTMENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门\r\n',
  `HIREDATE` datetime(0) NULL DEFAULT NULL COMMENT '入职时间\r\n',
  `RESIDENTADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍地址\r\n',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表\r\n',
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职员手机\r\n',
  `POSITION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位\r\n',
  `IDNUM` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号\r\n',
  `NOWADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现住址\r\n',
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `COMPANYID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sysvid
-- ----------------------------
DROP TABLE IF EXISTS `sysvid`;
CREATE TABLE `sysvid`  (
  `SYSVID` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '\r\n系统VID\r\n',
  `SYSVIDACTIVATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '\r\n系统VID激活日期',
  `EXPIREDATE` datetime(0) NULL DEFAULT NULL COMMENT '系统VID到期时间',
  `EXPIRENOTIFYDATE` datetime(0) NULL DEFAULT NULL COMMENT '系统VID到期提醒时间\r\n',
  `BUSSINESSAGENT` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表',
  PRIMARY KEY (`SYSVID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for textmessage
-- ----------------------------
DROP TABLE IF EXISTS `textmessage`;
CREATE TABLE `textmessage`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '标志',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文本内容',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `USERID_USERTIME`(`USERID`, `USERTIME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for textmessageindevice
-- ----------------------------
DROP TABLE IF EXISTS `textmessageindevice`;
CREATE TABLE `textmessageindevice`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '标志',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文本内容',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `SENDTIME` datetime(0) NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文本信息下发' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for upgradereport
-- ----------------------------
DROP TABLE IF EXISTS `upgradereport`;
CREATE TABLE `upgradereport`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `TIME` datetime(0) NOT NULL COMMENT '时间',
  `TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '升级类型',
  `RESULT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '升级结果',
  `READED` bit(1) NOT NULL COMMENT '已阅读否',
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `USERTIME` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER_TIME_READED`(`DEVICENUMBER`, `TIME`, `READED`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '升级结果通知' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `UNID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户guid',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司ID 所属集团\r\n',
  `KIND` tinyint(4) NOT NULL COMMENT '类别，1公司，2公司用户，3车主，4设备',
  `ACCOUNT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `EMAIL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `CONTACT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `CREATETIME` datetime(0) NOT NULL COMMENT '创建时间',
  `ENABLE` bit(1) NOT NULL COMMENT '启用',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `PID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父ID',
  `SERVICESTARTDATE` date NULL DEFAULT NULL COMMENT '服务开始日期（用户名到期时间\r\n）',
  `SERVICEENDDATE` date NULL DEFAULT NULL COMMENT '服务结束日期',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `QUESTION1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题1',
  `ANSWER1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案1',
  `QUESTION2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题2',
  `ANSWER2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案2',
  `TEL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `FAX` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `ZIPCODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `REGISTERDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `SERVICEENDNOTIFYDATE` datetime(0) NULL DEFAULT NULL COMMENT '用户名到期提醒时间',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代表',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UN_ACCOUNT`(`ACCOUNT`) USING BTREE,
  INDEX `IX_PID`(`PID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('558ffc6603c70e31a2a53a30', 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '558ffc6603c70e31a2a53a30', 0, 'admin', '123456', '系统管理员', 'yangxp999@163.com', '13714196239', '杨生', '2014-10-10 17:43:14', b'1', '2018-03-21 16:41:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '8888', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72ab073d769a75b6309dec', '09790727-0714-11e8-89e3-00163e32414e', '5a72ab073d769a75b6309dec', 1, 'rayton', '888888', 'rayton', '', 'ert', 'tertr', '2018-02-01 13:52:07', b'1', '2018-03-21 16:41:15', '558ffc6603c70e31a2a53a30', '2018-03-29', '2018-03-30', 'tert', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72ae2a3d769a75b6309e68', 'e858c6ae-0715-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 3, 'rayton01', '888888', 'rayton01', '', '', '', '2018-02-01 14:05:30', b'1', '2018-03-21 16:41:16', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72c8733d769a75b6309ff0', '92ff3411-0725-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 2, 'rayton02', '888888', 'rayton02', '', '', '', '2018-02-01 15:57:39', b'1', '2018-03-21 16:41:17', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72eb803d769a3c631b8b5a', '772cf348-073a-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 2, 'xin', '888888', 'xin', '', '', '', '2018-02-01 18:27:12', b'1', '2018-03-21 16:41:18', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a7417fc3d769a7dcc878d5c', '91bd9da3-07ed-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 4, '18011012700', '888888', 'T0001', '', '', '', '2018-02-02 15:49:16', b'1', '2018-03-21 16:41:19', NULL, '2018-02-02', '2018-03-10', '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a7419293d769a7dcc878df4', '44ce10ae-07ee-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 3, 'spr', '888888', 'spr', '', '', '', '2018-02-02 15:54:17', b'1', '2018-03-21 16:41:19', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aa7419b7b03680b92edf011', '5bc016f5-266c-11e8-b49f-00163e32131c', '558ffc6603c70e31a2a53a30', 4, '10189431635', '888888', '测试', '', '', '', '2018-03-13 11:12:27', b'1', '2018-03-21 16:41:20', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '888', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aaf614be6c6170930f59510', 'f3439b03-2b43-11e8-bda6-72b63e1098cc', '558ffc6603c70e31a2a53a30', 4, 'retr', '888888', 'eter', '', '', '', '2018-03-19 15:05:47', b'1', '2018-03-21 16:41:21', NULL, '2018-03-07', '2018-03-22', '', NULL, NULL, NULL, NULL, NULL, NULL, '888', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aaf61b2e6c6170930f59513', '30627e1f-2b44-11e8-bda6-72b63e1098cc', '558ffc6603c70e31a2a53a30', 3, 'eeeeeeeeee', '888888', 'eeeeeeeeee', '', 'rrrrrrrrrrr', 'rrrrrrrrrr', '2018-03-19 15:07:30', b'1', '2018-03-21 16:41:22', NULL, '2018-01-17', '2018-03-30', 'rrrrr', NULL, NULL, NULL, NULL, NULL, NULL, '888', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aaf61d7e6c6170930f59515', '464a31a4-2b44-11e8-bda6-72b63e1098cc', '558ffc6603c70e31a2a53a30', 2, 'werew', '888888', 'rewr', 'rewrwer@111.v', '', '', '2018-03-19 15:08:07', b'1', '2018-03-21 16:50:42', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '888', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab21bf8e6c6171190178124', '64b78b6c-2ce4-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 3, 'fsf', '888888', 'fssfsf', '', '', '', '2018-03-21 16:46:48', b'0', '2018-03-21 16:46:48', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab21cace6c617119017815d', 'cfa31688-2ce4-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 4, 'dsfsd', '888888', 'fsf', '', '', '', '2018-03-21 16:49:48', b'1', '2018-03-21 16:49:48', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab2206be6c61711901781ad', '0b8826fd-2ce7-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 2, 'sdf', '888888', 'tret', '', '', '', '2018-03-21 17:05:47', b'1', '2018-03-21 17:05:47', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab22152e6c61711901781ae', '955ed2f5-2ce7-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 3, 'dsfsdfdf', '888888', 'dsfdfd', '', 'sdfsfdsf', '', '2018-03-21 17:09:39', b'0', '2018-03-21 17:09:39', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab457d8e6c617043ceb938b', '417e2251-2e39-11e8-8aa8-d2872c46dd83', '558ffc6603c70e31a2a53a30', 3, 'dsf', '888888', 'wewe', '', '', '', '2018-03-23 09:26:48', b'0', '2018-03-23 09:26:48', NULL, NULL, NULL, 'fsdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5abc88ed05a3310acc2444dd', '36dc6609-331b-11e8-854a-91bbf8a85fe3', '5abc88ed05a3310acc2444dd', 1, 'dfdsf', '888888', 'dfdsf', 'fdf', '', '', '2018-03-29 14:34:21', b'1', '2018-03-29 14:34:21', '558ffc6603c70e31a2a53a30', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for usermonitor
-- ----------------------------
DROP TABLE IF EXISTS `usermonitor`;
CREATE TABLE `usermonitor`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `TARGETID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标唯一编号',
  PRIMARY KEY (`USERID`, `TARGETID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户监控范围' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usermonitor
-- ----------------------------
INSERT INTO `usermonitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72ab073d769a75b6309dec');
INSERT INTO `usermonitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72abd83d769a75b6309dee');
INSERT INTO `usermonitor` VALUES ('5a72eb803d769a3c631b8b5a', '5a72abd83d769a75b6309dee');
INSERT INTO `usermonitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72ab073d769a75b6309dec');
INSERT INTO `usermonitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72abd83d769a75b6309dee');
INSERT INTO `usermonitor` VALUES ('5aaf61d7e6c6170930f59515', '5aaf618ee6c6170930f59512');

-- ----------------------------
-- Table structure for useroptions
-- ----------------------------
DROP TABLE IF EXISTS `useroptions`;
CREATE TABLE `useroptions`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `USERKEY` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户自定义键',
  `USERVALUE` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户自定义设置',
  PRIMARY KEY (`USERID`, `USERKEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户自定义设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of useroptions
-- ----------------------------
INSERT INTO `useroptions` VALUES ('5a72c8733d769a75b6309ff0', '1', '{\"lng\":119.97085441538619,\"zoom\":4,\"lat\":27.563785556517328}');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `MOTORCADEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属车队',
  `DEVICEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号唯一编号',
  `PLATENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号码',
  `PLATECOLOR` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌颜色',
  `VEHICLECOLOR` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆颜色',
  `VEHICLETYPE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆类型',
  `VEHICLEVOLTAGE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆电压',
  `CARRYTYPE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '载运类型',
  `INITIALMILEAGE` int(10) UNSIGNED NOT NULL COMMENT '初始里程',
  `OILWEAR` decimal(10, 1) NOT NULL COMMENT '百公里油耗',
  `USEFULLIFE` int(11) NOT NULL COMMENT '使用年限',
  `ADMINAREA` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属行政区域',
  `MARKER` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆图标',
  `ROTATE` bit(1) NOT NULL COMMENT '旋转车辆图标否',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `CREATETIME` datetime(0) NOT NULL COMMENT '创建时间',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `INSTALLDATE` date NULL DEFAULT NULL COMMENT '安装日期',
  `ANNUALSURVEYDATE` date NULL DEFAULT NULL COMMENT '年检日期',
  `NEXTMAINTAINDATE` date NULL DEFAULT NULL COMMENT '下次保养日期',
  `FACTORYPLATEMODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂牌型号',
  `AXLENUM` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车轴数\r\n',
  `MANUFACTURER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汽车厂家\r\n',
  `TRAILERNUM` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '挂车号\r\n',
  `VIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车架号\r\n',
  `BUYMONEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购车金额\r\n',
  `SERVICEPROVIDER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汽车服务商\r\n',
  `LICENSEPLATESELFNUM` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自编号\r\n',
  `ENGINENUM` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发动机号\r\n',
  `CARRIAGELONG` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车厢长度\r\n',
  `BUYDATE` datetime(0) NULL DEFAULT NULL COMMENT '购车时间\r\n',
  `PROVIDERCONTACT` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务商联系人\r\n',
  `DANGERPLATENUM` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '危货牌号\r\n',
  `VEHICULARWEIGHT` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车载重\r\n',
  `INVOICEAMOUNT` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票金额\r\n',
  `PROVIDERTEL` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务商电话\r\n',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '上户时间\r\n',
  `BUYTAX` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆购置税\r\n',
  `LICENSEEXAMINATIONPERIOD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行车证审验期\r\n',
  `VEHICLEPIC` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆照片\r\n',
  `VINPIC` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车架号照片\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `PLATENUMBER`(`PLATENUMBER`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `MOTORCADEID`(`MOTORCADEID`) USING BTREE,
  INDEX `DEVICEID`(`DEVICEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('5a74182b3d769a7dcc878d5d', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5a7417fc3d769a7dcc878d5c', '京B37A94', '蓝色', '白色', '中型客车', '12V', '客车', 0, 0.0, 0, '广东省', '00.png', b'1', '', '2018-02-02 15:50:03', '2018-02-02 16:02:15', '2018-02-02', '2018-02-22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `vehicle` VALUES ('5aa741df7b03680b92edf012', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aa7419b7b03680b92edf011', '京B37A93', '蓝色', '白色', '大型客车', '12V', '客车', 0, 0.0, 0, '广东省', '00.png', b'1', '', '2018-03-13 11:13:35', '2018-03-19 15:04:40', NULL, NULL, '2018-03-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `vehicle` VALUES ('5aaf6182e6c6170930f59511', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aaf614be6c6170930f59510', 'gyyyyyyyyy', '黑色', '', '', '', '', 0, 0.0, 0, '', '00.png', b'1', '', '2018-03-19 15:06:42', '2018-03-19 15:06:42', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `vehicle` VALUES ('5ab22740e6c6171190c57f1f', '558ffc6603c70e31a2a53a30', '5ab21cb5e6c617119017815e', '5ab21cace6c617119017815d', '23324', '蓝色', '', '', '', '', 0, 0.0, 0, '', '00.png', b'1', '', '2018-03-21 17:34:56', '2018-03-21 17:34:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for vehicleindriver
-- ----------------------------
DROP TABLE IF EXISTS `vehicleindriver`;
CREATE TABLE `vehicleindriver`  (
  `DRIVERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`DRIVERID`, `VEHICLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆与司机关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicleindriver
-- ----------------------------
INSERT INTO `vehicleindriver` VALUES ('5a72acea3d769a75b6309e23', '5a72ae0c3d769a75b6309e67');
INSERT INTO `vehicleindriver` VALUES ('5a72ae0c3d769a75b6309e67', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleindriver` VALUES ('5a72ae0c3d769a75b6309e67', '5aa741df7b03680b92edf012');
INSERT INTO `vehicleindriver` VALUES ('5a72ae0c3d769a75b6309e67', '5aaf6182e6c6170930f59511');
INSERT INTO `vehicleindriver` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae0c3d769a75b6309e67');
INSERT INTO `vehicleindriver` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419353d769a7dcc878df5');
INSERT INTO `vehicleindriver` VALUES ('5a7419353d769a7dcc878df5', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleindriver` VALUES ('5aaf61c6e6c6170930f59514', '5aaf6182e6c6170930f59511');

-- ----------------------------
-- Table structure for vehicleinowner
-- ----------------------------
DROP TABLE IF EXISTS `vehicleinowner`;
CREATE TABLE `vehicleinowner`  (
  `OWNERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车主唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`OWNERID`, `VEHICLEID`) USING BTREE,
  INDEX `FK_vehicleinowner_vehilce`(`VEHICLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆所属车主' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicleinowner
-- ----------------------------
INSERT INTO `vehicleinowner` VALUES ('5a72acea3d769a75b6309e23', '5a72ae2a3d769a75b6309e68');
INSERT INTO `vehicleinowner` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae2a3d769a75b6309e68');
INSERT INTO `vehicleinowner` VALUES ('5a72ae2a3d769a75b6309e68', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleinowner` VALUES ('5a7419293d769a7dcc878df4', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleinowner` VALUES ('5aaf61b2e6c6170930f59513', '5a74182b3d769a7dcc878d5d');
INSERT INTO `vehicleinowner` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419293d769a7dcc878df4');
INSERT INTO `vehicleinowner` VALUES ('5aaf61b2e6c6170930f59513', '5aa741df7b03680b92edf012');
INSERT INTO `vehicleinowner` VALUES ('5aaf61b2e6c6170930f59513', '5aaf6182e6c6170930f59511');

SET FOREIGN_KEY_CHECKS = 1;
