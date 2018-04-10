/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.1.252
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : 192.168.1.252:3306
 Source Schema         : gpsdb2

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : 65001

 Date: 30/03/2018 11:26:57
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
-- Table structure for automobile
-- ----------------------------
DROP TABLE IF EXISTS `automobile`;
CREATE TABLE `automobile`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of automobile
-- ----------------------------
INSERT INTO `automobile` VALUES ('5a74182b3d769a7dcc878d5d', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5a7417fc3d769a7dcc878d5c', '京B37A94', '蓝色', '白色', '中型客车', '12V', '客车', 0, 0.0, 0, '广东省', '00.png', b'1', '', '2018-02-02 15:50:03', '2018-02-02 16:02:15', '2018-02-02', '2018-02-22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5aa741df7b03680b92edf012', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aa7419b7b03680b92edf011', '京B37A93', '蓝色', '白色', '大型客车', '12V', '客车', 0, 0.0, 0, '广东省', '00.png', b'1', '', '2018-03-13 11:13:35', '2018-03-19 15:04:40', NULL, NULL, '2018-03-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5aaf6182e6c6170930f59511', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aaf614be6c6170930f59510', 'gyyyyyyyyy', '黑色', '', '', '', '', 0, 0.0, 0, '', '00.png', b'1', '', '2018-03-19 15:06:42', '2018-03-19 15:06:42', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5ab22740e6c6171190c57f1f', '558ffc6603c70e31a2a53a30', '5ab21cb5e6c617119017815e', '5ab21cace6c617119017815d', '23324', '蓝色', '', '', '', '', 0, 0.0, 0, '', '00.png', b'1', '', '2018-03-21 17:34:56', '2018-03-21 17:34:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for automobile_automobile_owner
-- ----------------------------
DROP TABLE IF EXISTS `automobile_automobile_owner`;
CREATE TABLE `automobile_automobile_owner`  (
  `OWNERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车主唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`OWNERID`, `VEHICLEID`) USING BTREE,
  INDEX `FK_vehicleinowner_vehilce`(`VEHICLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆所属车主' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of automobile_automobile_owner
-- ----------------------------
INSERT INTO `automobile_automobile_owner` VALUES ('5a72acea3d769a75b6309e23', '5a72ae2a3d769a75b6309e68');
INSERT INTO `automobile_automobile_owner` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae2a3d769a75b6309e68');
INSERT INTO `automobile_automobile_owner` VALUES ('5a72ae2a3d769a75b6309e68', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5a7419293d769a7dcc878df4', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5aaf61b2e6c6170930f59513', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419293d769a7dcc878df4');
INSERT INTO `automobile_automobile_owner` VALUES ('5aaf61b2e6c6170930f59513', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5aaf61b2e6c6170930f59513', '5aaf6182e6c6170930f59511');

-- ----------------------------
-- Table structure for automobile_driveer
-- ----------------------------
DROP TABLE IF EXISTS `automobile_driveer`;
CREATE TABLE `automobile_driveer`  (
  `DRIVERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`DRIVERID`, `VEHICLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆与司机关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of automobile_driveer
-- ----------------------------
INSERT INTO `automobile_driveer` VALUES ('5a72acea3d769a75b6309e23', '5a72ae0c3d769a75b6309e67');
INSERT INTO `automobile_driveer` VALUES ('5a72ae0c3d769a75b6309e67', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_driveer` VALUES ('5a72ae0c3d769a75b6309e67', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_driveer` VALUES ('5a72ae0c3d769a75b6309e67', '5aaf6182e6c6170930f59511');
INSERT INTO `automobile_driveer` VALUES ('5a74182b3d769a7dcc878d5d', '5a72ae0c3d769a75b6309e67');
INSERT INTO `automobile_driveer` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419353d769a7dcc878df5');
INSERT INTO `automobile_driveer` VALUES ('5a7419353d769a7dcc878df5', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_driveer` VALUES ('5aaf61c6e6c6170930f59514', '5aaf6182e6c6170930f59511');

-- ----------------------------
-- Table structure for automobile_owner
-- ----------------------------
DROP TABLE IF EXISTS `automobile_owner`;
CREATE TABLE `automobile_owner`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `OWNERNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `IDTYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件编号',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `CONTACT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车主联系人\r\n',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册时间\r\n',
  `FAX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真\r\n',
  `BUSSINESSAGENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务代表\r\n',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车主资料' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of automobile_owner
-- ----------------------------
INSERT INTO `automobile_owner` VALUES ('5a7419293d769a7dcc878df4', '558ffc6603c70e31a2a53a30', '李生', '', '', '2018-03-21 16:43:55', '5555555', NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5aaf61b2e6c6170930f59513', '558ffc6603c70e31a2a53a30', 'eeeeeeeeeeee', '驾驶证', 'rrrrrr', '2018-03-21 16:50:37', '5555555', NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5ab21bf8e6c6171190178124', '558ffc6603c70e31a2a53a30', 'esdfsdf', '', '', '2018-03-21 16:46:48', NULL, NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5ab22152e6c61711901781ae', '558ffc6603c70e31a2a53a30', 'sdf', '', '', '2018-03-21 17:09:38', NULL, NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5ab457d8e6c617043ceb938b', '558ffc6603c70e31a2a53a30', 're', '', '', '2018-03-23 09:26:48', NULL, NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5abda9fc05a3310dc43da973', '558ffc6603c70e31a2a53a30', '而威尔', '', '', '2018-03-30 11:07:48', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for circle_region
-- ----------------------------
DROP TABLE IF EXISTS `circle_region`;
CREATE TABLE `circle_region`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圆形区域' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of circle_region
-- ----------------------------
INSERT INTO `circle_region` VALUES (1, '558ffc6603c70e31a2a53a30', 'test111', b'1', 16859, 37.891270, 85.164946, 561082, 0, 0, '2018-02-23 10:37:00', '2018-02-24 10:37:00', '', '2018-02-23 10:37:31');
INSERT INTO `circle_region` VALUES (2, '558ffc6603c70e31a2a53a30', 'dfhdfhd', b'1', 0, 37.657573, 95.100557, 577557, 0, 0, NULL, NULL, '', '2018-03-19 15:00:42');
INSERT INTO `circle_region` VALUES (3, '558ffc6603c70e31a2a53a30', 'fhdfhdf', b'1', 0, 41.054568, 86.489680, 910092, 0, 0, NULL, NULL, '', '2018-03-21 16:51:20');
INSERT INTO `circle_region` VALUES (4, '558ffc6603c70e31a2a53a30', 'sgdgs', b'1', 0, 37.803949, 85.753706, 65030, 0, 0, NULL, NULL, '', '2018-03-21 16:51:27');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司资料' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('558ffc6603c70e31a2a53a30', '中国兵器工业北方通用电子', '兵工北方通用', '', '', '', '', '', b'0', '2015-08-29 11:18:53', '2015-08-29', NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5a72ab073d769a75b6309dec', '深圳锐讯易通科技有限公司', '锐讯易通', 'retert', 'eter', 'ertert', 'erterte', 'rtret', b'1', '2018-03-19 15:11:16', '2018-02-01', NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5abc88ed05a3310acc2444dd', 'dvfsd', '444', 'fgdf', '', '', '', '', b'1', '2018-03-29 14:34:21', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for company_permission
-- ----------------------------
DROP TABLE IF EXISTS `company_permission`;
CREATE TABLE `company_permission`  (
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业ID',
  `PERMISSIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`, `PERMISSIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业授权表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of company_permission
-- ----------------------------
INSERT INTO `company_permission` VALUES ('558ffc6603c70e31a2a53a30', 'test');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.circleArea.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.authorize');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.company.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.device.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.dictionary.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.driver.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.maintain.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPois');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePoi');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.mapOption');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker.remove');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.marker.save');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.motorcade.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.owner.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.poi.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.authorize');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.role.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addSections');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeSection');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.routeArea.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.simcard.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.addMonitors');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.addRoles');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.removeMonitor');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.removeRole');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.user.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addDrivers');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addOwners');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.create');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.delete');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeDriver');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeOwner');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'baseinfo.vehicle.update');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.accidentDoubtLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.deviceEventReport');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.drivingRecorder');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.locateLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.loginLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimedia');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimediaEvent');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.multimediaRetrieval');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.parameterLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.powerLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.speedLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.speedStatusLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData.timeoutLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.locate');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.datalog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.instruct');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.locate.vehileinfo');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.replay');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'mmp');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyinfo');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'security.saveMyKey');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.areaIo');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.areaOverspeed');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.currentOnlineOffline');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.historyOnlineOffline');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.historyOnlineTime');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.mileageOil');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.operateLog');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.routeDeviation');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.sectionOverspeed');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.timeoutParking');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleAlarm');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleFatigueDriving');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'statistics.vehicleOverspeed');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'test');
INSERT INTO `company_permission` VALUES ('5abc88ed05a3310acc2444dd', 'baseinfo');
INSERT INTO `company_permission` VALUES ('5abc88ed05a3310acc2444dd', 'test');

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
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机资料' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('5a72ae0c3d769a75b6309e67', '558ffc6603c70e31a2a53a30', '杨先生', '男', '', '身份证', '511333290314302455', 'C01921', '', '2018-02-01 14:05:00', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5a7419353d769a7dcc878df5', '558ffc6603c70e31a2a53a30', '李先生', '男', '', '', '', '39021', '', '2018-02-02 15:54:29', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5aaf61c6e6c6170930f59514', '558ffc6603c70e31a2a53a30', 'rere', '女', 'ewr', '身份证', 'rewrwe', 'ewrewr', '', '2018-03-19 15:07:50', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('5a7417fc3d769a7dcc878d5c', '558ffc6603c70e31a2a53a30', '5a7417dd3d769a7dcc878d5b', '13311012700', '星XXX', '联XXX', '', 0, b'0', b'0', '', '2018-02-02 16:05:01', '2018-02-02', '2018-02-02', '2018-02-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES ('5aa7419b7b03680b92edf011', '558ffc6603c70e31a2a53a30', '5a72ac0c3d769a75b6309def', '10189415505', '星XXX', '联XXX', '', 0, b'0', b'0', '', '2018-03-13 16:07:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES ('5aaf614be6c6170930f59510', '558ffc6603c70e31a2a53a30', '5aaf6123e6c6170930f594dd', 'regtre', 'XA-TY-101', '翰XXX', '', 0, b'0', b'0', '', '2018-03-19 15:06:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES ('5ab21cace6c617119017815d', '558ffc6603c70e31a2a53a30', '', 'ddsfd', '', '', '', 0, b'0', b'0', '', '2018-03-21 16:49:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for equipment_region
-- ----------------------------
DROP TABLE IF EXISTS `equipment_region`;
CREATE TABLE `equipment_region`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `AREAID` int(10) UNSIGNED NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `TIME` datetime(0) NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`DEVICENUMBER`, `AREAID`, `AREATYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment_region
-- ----------------------------
INSERT INTO `equipment_region` VALUES ('10189415505', 1, 2, '2018-03-19 15:00:26');
INSERT INTO `equipment_region` VALUES ('10189415505', 1, 3, '2018-03-19 15:00:02');
INSERT INTO `equipment_region` VALUES ('10189415505', 1, 4, '2018-03-19 14:56:21');
INSERT INTO `equipment_region` VALUES ('10189415505', 2, 1, '2018-03-19 15:02:04');
INSERT INTO `equipment_region` VALUES ('10189415505', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `equipment_region` VALUES ('10189415505', 3, 4, '2018-03-19 15:52:39');
INSERT INTO `equipment_region` VALUES ('13311012699', 1, 1, '2018-02-23 10:37:41');
INSERT INTO `equipment_region` VALUES ('13311012700', 1, 2, '2018-03-19 15:00:26');
INSERT INTO `equipment_region` VALUES ('13311012700', 1, 3, '2018-03-19 15:00:02');
INSERT INTO `equipment_region` VALUES ('13311012700', 1, 4, '2018-03-19 14:56:21');
INSERT INTO `equipment_region` VALUES ('13311012700', 2, 1, '2018-03-19 15:02:04');
INSERT INTO `equipment_region` VALUES ('13311012700', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `equipment_region` VALUES ('13311012700', 3, 4, '2018-03-19 15:52:39');
INSERT INTO `equipment_region` VALUES ('regtre', 2, 3, '2018-03-19 15:36:11');
INSERT INTO `equipment_region` VALUES ('regtre', 3, 4, '2018-03-19 15:52:39');

-- ----------------------------
-- Table structure for equipment_region_log
-- ----------------------------
DROP TABLE IF EXISTS `equipment_region_log`;
CREATE TABLE `equipment_region_log`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备的操作日志，以便与设备同步' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment_region_log
-- ----------------------------
INSERT INTO `equipment_region_log` VALUES ('01f2e58b-610d-422f-b5c1-80dce1d2b4a6', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:47', NULL);
INSERT INTO `equipment_region_log` VALUES ('0c36b632-de08-4e34-9995-59856dd8acd0', '13311012700', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:42', NULL);
INSERT INTO `equipment_region_log` VALUES ('1440fd51-b3ed-4b1a-8779-57d8867ca332', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:29', NULL);
INSERT INTO `equipment_region_log` VALUES ('150d888b-03b7-4e3e-98e6-e2609dbe25ee', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('1560268f-0d59-483e-9a5b-210e7175f953', '10189415505', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('1bb3547c-5ade-4224-8bc2-5975f727a75f', 'regtre', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('3a1e9ffe-9486-46ae-89a9-25202d1a69af', '13311012700', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `equipment_region_log` VALUES ('3c6aff6a-2a74-49e1-835f-a12e2fd86552', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `equipment_region_log` VALUES ('40ab650f-08a5-4867-a5b3-8afa0fca1de8', '10189415505', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:04', NULL);
INSERT INTO `equipment_region_log` VALUES ('539cda10-fd44-4810-9a04-ed4142c7873b', '13311012700', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('61a9cb98-045b-49b7-931b-9354669deec3', '10189415505', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `equipment_region_log` VALUES ('66032486-22cd-45f5-8c9b-3c33b1a75b25', '10189415505', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('6a3a8ca1-9878-4020-937e-ef78e9823ea9', '10189415505', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `equipment_region_log` VALUES ('6fe8e695-a9ce-4223-bb89-2d684500c981', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('797f9ab0-1e83-4ec5-b911-393a9fe0944b', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('7c10f8a5-e0d4-4ca7-81cf-ae1f483e2192', '13311012700', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `equipment_region_log` VALUES ('96d05d27-2054-4f8f-9bfe-e651d30cf4ab', '10189415505', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `equipment_region_log` VALUES ('a15579d2-cbe7-4ca8-8534-f4d85e564c05', 'regtre', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('a99b21e4-f3d6-4bec-8da1-f1894584522f', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:16', NULL);
INSERT INTO `equipment_region_log` VALUES ('aee6ab70-1f1f-44d7-8a51-42293eb1e647', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:47', NULL);
INSERT INTO `equipment_region_log` VALUES ('cb07e646-7f57-4022-81f6-e020deacc268', '13311012700', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `equipment_region_log` VALUES ('d619d21a-776d-4830-9cc9-8b9a7453662c', '13311012700', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('e696227c-c546-41ad-90f7-6c2f6050315a', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `equipment_region_log` VALUES ('e7d5eb71-94ab-4875-9790-5d4a649d609d', '13311012699', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:41', NULL);
INSERT INTO `equipment_region_log` VALUES ('f110bfbc-63c5-4589-89bc-5d31645a3c93', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:27', NULL);
INSERT INTO `equipment_region_log` VALUES ('fbe64d7e-bf50-4f2e-993e-4c439ca89e90', '13311012700', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:04', NULL);

-- ----------------------------
-- Table structure for event_menu
-- ----------------------------
DROP TABLE IF EXISTS `event_menu`;
CREATE TABLE `event_menu`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `EVENTID` tinyint(3) UNSIGNED NOT NULL COMMENT '类型',
  `CONTENT` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件内容',
  PRIMARY KEY (`DEVICENUMBER`, `EVENTID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for event_report
-- ----------------------------
DROP TABLE IF EXISTS `event_report`;
CREATE TABLE `event_report`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for info_menu
-- ----------------------------
DROP TABLE IF EXISTS `info_menu`;
CREATE TABLE `info_menu`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) UNSIGNED NOT NULL COMMENT '类型',
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for info_menu_equipment
-- ----------------------------
DROP TABLE IF EXISTS `info_menu_equipment`;
CREATE TABLE `info_menu_equipment`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息菜单设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for info_service
-- ----------------------------
DROP TABLE IF EXISTS `info_service`;
CREATE TABLE `info_service`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息服务' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for info_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `info_subscribe`;
CREATE TABLE `info_subscribe`  (
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `INFOTYPE` tinyint(4) NOT NULL COMMENT '信息类型',
  PRIMARY KEY (`DEVICENUMBER`, `INFOTYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息订阅' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
INSERT INTO `log` VALUES ('2018-03-30 11:05:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-03-30 11:07:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:07:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:09:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-30 11:09:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:11:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:12:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:17:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:17:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:20:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:20:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:20:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:21:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:22:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:22:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:23:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '保养记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES ('5a72ad493d769a75b6309e3d', '558ffc6603c70e31a2a53a30', '5a72acea3d769a75b6309e23', '2018-02-15', '', '', 0, 0.00, '', '', 0, '558ffc6603c70e31a2a53a30', '系统管理员', '2018-02-01 14:01:45', '2018-02-01 14:01:45', NULL);
INSERT INTO `maintain` VALUES ('5aaf6108e6c6170930f594c3', '558ffc6603c70e31a2a53a30', '5aa741df7b03680b92edf012', '2018-03-06', '', '', 0, 0.00, '', '', 0, '5a72c8733d769a75b6309ff0', 'rayton02', '2018-03-19 15:04:40', '2018-03-19 15:04:40', '2018-03-06');
INSERT INTO `maintain` VALUES ('5ab21cfee6c6171190178161', '558ffc6603c70e31a2a53a30', '5aaf6182e6c6170930f59511', '2018-03-06', '', '', 0, 0.00, '', '', 0, '558ffc6603c70e31a2a53a30', '系统管理员', '2018-03-21 16:51:10', '2018-03-21 16:51:10', NULL);

-- ----------------------------
-- Table structure for map_layer
-- ----------------------------
DROP TABLE IF EXISTS `map_layer`;
CREATE TABLE `map_layer`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地图图层' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of map_layer
-- ----------------------------
INSERT INTO `map_layer` VALUES ('5aaf5e23e6c6170930f59413', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', '7777', b'1', '7777', '2018-03-19 14:52:19');

-- ----------------------------
-- Table structure for map_layer_cover
-- ----------------------------
DROP TABLE IF EXISTS `map_layer_cover`;
CREATE TABLE `map_layer_cover`  (
  `MAPLAYERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地图图层id',
  `AREAID` int(10) UNSIGNED NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  PRIMARY KEY (`MAPLAYERID`, `AREAID`, `AREATYPE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地图图层覆盖物，用于圆形区域、矩形区域、多边形区域、路线、兴趣点绑定到地图图层' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of map_layer_cover
-- ----------------------------
INSERT INTO `map_layer_cover` VALUES ('5aaf5e23e6c6170930f59413', 1, 1);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车队' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of motorcade
-- ----------------------------
INSERT INTO `motorcade` VALUES ('5a72abd83d769a75b6309dee', '558ffc6603c70e31a2a53a30', '后勤运输队', '测试车队', b'1', '', '', '', '2018-02-01 14:01:19', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5aaf618ee6c6170930f59512', '558ffc6603c70e31a2a53a30', '巡逻队', 'tyty', b'0', '', '', '', '2018-03-19 15:06:54', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ab21cb5e6c617119017815e', '558ffc6603c70e31a2a53a30', '后勤运输队', 'qweqwe', b'0', '', '', '', '2018-03-21 16:49:57', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ab22346e6c61711901781c9', '558ffc6603c70e31a2a53a30', '巡逻队', 'eerwer', b'0', '', '', '', '2018-03-21 17:17:58', NULL, NULL);

-- ----------------------------
-- Table structure for multimedia_event_report
-- ----------------------------
DROP TABLE IF EXISTS `multimedia_event_report`;
CREATE TABLE `multimedia_event_report`  (
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
-- Table structure for multimedia_retrieval
-- ----------------------------
DROP TABLE IF EXISTS `multimedia_retrieval`;
CREATE TABLE `multimedia_retrieval`  (
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
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permissionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限中文名',
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
-- Table structure for phone_equipment
-- ----------------------------
DROP TABLE IF EXISTS `phone_equipment`;
CREATE TABLE `phone_equipment`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电话本设置' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电话本' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for point_of_interest
-- ----------------------------
DROP TABLE IF EXISTS `point_of_interest`;
CREATE TABLE `point_of_interest`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '兴趣点' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of point_of_interest
-- ----------------------------
INSERT INTO `point_of_interest` VALUES (1, '558ffc6603c70e31a2a53a30', '加油站', '777777', 87.445769, 38.645371, '777777', '2018-03-19 14:53:47');
INSERT INTO `point_of_interest` VALUES (2, '558ffc6603c70e31a2a53a30', '', 'tgret', 86.857773, 40.129581, '', '2018-03-19 15:35:47');

-- ----------------------------
-- Table structure for polygon_region
-- ----------------------------
DROP TABLE IF EXISTS `polygon_region`;
CREATE TABLE `polygon_region`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多边形区域' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of polygon_region
-- ----------------------------
INSERT INTO `polygon_region` VALUES (1, '558ffc6603c70e31a2a53a30', 'etert', b'1', 0, 0, 0, '2018-03-06', '2018-03-06', '', '2018-03-19 14:59:55');
INSERT INTO `polygon_region` VALUES (2, '558ffc6603c70e31a2a53a30', 'yery', b'1', 0, 0, 0, NULL, NULL, '', '2018-03-19 15:36:06');

-- ----------------------------
-- Table structure for polygon_vertex
-- ----------------------------
DROP TABLE IF EXISTS `polygon_vertex`;
CREATE TABLE `polygon_vertex`  (
  `PID` int(10) UNSIGNED NOT NULL COMMENT '多边形编号',
  `LAT` decimal(9, 6) NOT NULL COMMENT '纬度',
  `LNG` decimal(9, 6) NOT NULL COMMENT '经度',
  INDEX `PID_INDEX`(`PID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多边形顶点项' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of polygon_vertex
-- ----------------------------
INSERT INTO `polygon_vertex` VALUES (1, 39.334675, 88.623796);
INSERT INTO `polygon_vertex` VALUES (1, 36.538442, 97.603658);
INSERT INTO `polygon_vertex` VALUES (1, 34.250162, 87.151708);
INSERT INTO `polygon_vertex` VALUES (1, 37.129410, 80.529488);
INSERT INTO `polygon_vertex` VALUES (2, 39.562756, 82.736172);
INSERT INTO `polygon_vertex` VALUES (2, 39.789985, 89.801321);
INSERT INTO `polygon_vertex` VALUES (2, 36.538009, 89.506997);
INSERT INTO `polygon_vertex` VALUES (2, 34.494052, 87.445936);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for question_answer
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `PID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父记录唯一编号',
  `ANSWERID` tinyint(3) UNSIGNED NOT NULL COMMENT '答案编号',
  `CONTENT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案内容',
  `EDITTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `PID`(`PID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题答案' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for question_equipment
-- ----------------------------
DROP TABLE IF EXISTS `question_equipment`;
CREATE TABLE `question_equipment`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提问下发' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for rectangle_region
-- ----------------------------
DROP TABLE IF EXISTS `rectangle_region`;
CREATE TABLE `rectangle_region`  (
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '矩形区域' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rectangle_region
-- ----------------------------
INSERT INTO `rectangle_region` VALUES (1, '558ffc6603c70e31a2a53a30', 'tdhtdghdt', b'1', 0, 36.359899, 89.212325, 36.121789, 94.364761, 0, 0, NULL, NULL, 'rdhdh', '2018-03-19 15:00:19');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('5a72ab073d769a75b6309ded', '5a72ab073d769a75b6309dec', '企业管理员', '', '2018-02-01 13:52:07');
INSERT INTO `role` VALUES ('5a72c8883d769a75b6309ff1', '558ffc6603c70e31a2a53a30', '所有权限', '', '2018-02-01 15:58:00');
INSERT INTO `role` VALUES ('5ab21c08e6c617119017813e', '558ffc6603c70e31a2a53a30', 'ewr', 'ewrew', '2018-03-21 16:47:04');
INSERT INTO `role` VALUES ('5abc88ed05a3310acc2444de', '5abc88ed05a3310acc2444dd', '企业管理员', '', '2018-03-29 14:34:21');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业ID',
  `ROLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `PERMISSIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`, `ROLEID`, `PERMISSIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色授权表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.circleArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.authorize');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.company.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.device.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.dictionary.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.driver.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.maintain.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addPois');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removePoi');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapLayer.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.mapOption');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker.remove');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.marker.save');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.motorcade.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.owner.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.poi.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.polygonArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.rectangleArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.addSections');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.removeSection');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.routeArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.sectionArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.simcard.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.addMonitors');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.addRoles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.removeMonitor');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.removeRole');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.user.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.addDrivers');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.addOwners');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.removeDriver');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.removeOwner');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'baseinfo.vehicle.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.accidentDoubtLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.deviceEventReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.drivingRecorder');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.locateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.loginLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimedia');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimediaEvent');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.multimediaRetrieval');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.parameterLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.powerLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.speedLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.speedStatusLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.deviceData.timeoutLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.datalog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.instruct');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.locate.vehileinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'center.replay');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'security.saveMyinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'security.saveMyKey');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.areaIo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.areaOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.currentOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.historyOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.historyOnlineTime');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.mileageOil');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.operateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.routeDeviation');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.sectionOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.timeoutParking');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleAlarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleFatigueDriving');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5a72c8883d769a75b6309ff1', 'statistics.vehicleOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'baseinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.areaIo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.areaOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.currentOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.historyOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.historyOnlineTime');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.mileageOil');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.operateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.routeDeviation');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.sectionOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.timeoutParking');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleAlarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleFatigueDriving');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'statistics.vehicleOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ab21c08e6c617119017813e', 'test');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.circleArea.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.authorize');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.company.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.device.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.dictionary.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.driver.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.maintain.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPois');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePoi');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapLayer.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.mapOption');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker.remove');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.marker.save');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.motorcade.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.owner.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.poi.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.polygonArea.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.rectangleArea.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.authorize');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.role.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addSections');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeSection');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.routeArea.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.sectionArea.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.simcard.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.addMonitors');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.addRoles');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.removeMonitor');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.removeRole');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.user.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addDrivers');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.addOwners');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.create');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.delete');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeDriver');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.removeOwner');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'baseinfo.vehicle.update');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.accidentDoubtLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.deviceEventReport');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.drivingRecorder');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.locateLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.loginLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimedia');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimediaEvent');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.multimediaRetrieval');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.parameterLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.powerLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.speedLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.speedStatusLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData.timeoutLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.datalog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.instruct');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.locate.vehileinfo');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.replay');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'mmp');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'security.saveMyinfo');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'security.saveMyKey');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.areaIo');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.areaOverspeed');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.currentOnlineOffline');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.historyOnlineOffline');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.historyOnlineTime');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.mileageOil');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.operateLog');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.routeDeviation');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.sectionOverspeed');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.timeoutParking');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleAlarm');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleFatigueDriving');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'statistics.vehicleOverspeed');
INSERT INTO `role_permission` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444dd', 'baseinfo');
INSERT INTO `role_permission` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444dd', 'test');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线区域' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, '558ffc6603c70e31a2a53a30', 'yytyr', b'1', 17, '2018-03-06', '2018-03-28', 'tytytytyty', '2018-03-19 14:56:14');
INSERT INTO `route` VALUES (2, '558ffc6603c70e31a2a53a30', 'fghfgh', b'1', 0, NULL, NULL, '', '2018-03-19 15:35:32');
INSERT INTO `route` VALUES (3, '558ffc6603c70e31a2a53a30', 'etdtr', b'1', 0, NULL, NULL, '', '2018-03-19 15:36:52');

-- ----------------------------
-- Table structure for route_inflection_point
-- ----------------------------
DROP TABLE IF EXISTS `route_inflection_point`;
CREATE TABLE `route_inflection_point`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '拐点id',
  `SECTIONID` int(10) UNSIGNED NOT NULL COMMENT '路段id',
  `LAT` decimal(9, 6) NOT NULL COMMENT '拐点纬度',
  `LNG` decimal(9, 6) NOT NULL COMMENT '拐点经度',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `SECTIONID`(`SECTIONID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线拐点' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of route_inflection_point
-- ----------------------------
INSERT INTO `route_inflection_point` VALUES (3, 17, 39.104950, 92.157241, 0);
INSERT INTO `route_inflection_point` VALUES (4, 17, 36.775055, 90.832617, 1);

-- ----------------------------
-- Table structure for route_section
-- ----------------------------
DROP TABLE IF EXISTS `route_section`;
CREATE TABLE `route_section`  (
  `ROUTEID` int(11) UNSIGNED NOT NULL COMMENT '路线唯一编号',
  `SECTIONID` int(11) UNSIGNED NOT NULL COMMENT '路段唯一编号',
  PRIMARY KEY (`ROUTEID`, `SECTIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线路段表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of route_section
-- ----------------------------
INSERT INTO `route_section` VALUES (1, 17);
INSERT INTO `route_section` VALUES (3, 17);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路段' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES (17, '558ffc6603c70e31a2a53a30', 'dhfghy', 0, 0, 0, 0, 0, 0, '', '2018-03-19 15:36:26');

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sim卡资料' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for text_message
-- ----------------------------
DROP TABLE IF EXISTS `text_message`;
CREATE TABLE `text_message`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文本信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for text_message_equipment
-- ----------------------------
DROP TABLE IF EXISTS `text_message_equipment`;
CREATE TABLE `text_message_equipment`  (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) UNSIGNED NOT NULL COMMENT '标志',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文本内容',
  `STATUS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `SENDTIME` datetime(0) NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime(0) NULL DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文本信息下发' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for update_report
-- ----------------------------
DROP TABLE IF EXISTS `update_report`;
CREATE TABLE `update_report`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

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
INSERT INTO `user` VALUES ('5abda9fc05a3310dc43da973', '86dffd6f-33c7-11e8-ab0d-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '温热', '888888', 'wew', '', '', '', '2018-03-30 11:07:48', b'1', '2018-03-30 11:07:48', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_monitor
-- ----------------------------
DROP TABLE IF EXISTS `user_monitor`;
CREATE TABLE `user_monitor`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `TARGETID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标唯一编号',
  PRIMARY KEY (`USERID`, `TARGETID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户监控范围' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_monitor
-- ----------------------------
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72ab073d769a75b6309dec');
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5a72abd83d769a75b6309dee');
INSERT INTO `user_monitor` VALUES ('5a72eb803d769a3c631b8b5a', '5a72abd83d769a75b6309dee');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72ab073d769a75b6309dec');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72abd83d769a75b6309dee');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5aaf618ee6c6170930f59512');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `ROLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USERID`, `ROLEID`) USING BTREE,
  INDEX `FK_roleinuser_role`(`ROLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309ded');
INSERT INTO `user_role` VALUES ('5a72c8733d769a75b6309ff0', '5a72c8883d769a75b6309ff1');
INSERT INTO `user_role` VALUES ('5a72eb803d769a3c631b8b5a', '5a72c8883d769a75b6309ff1');
INSERT INTO `user_role` VALUES ('5abc88ed05a3310acc2444dd', '5abc88ed05a3310acc2444de');

-- ----------------------------
-- Table structure for user_setting
-- ----------------------------
DROP TABLE IF EXISTS `user_setting`;
CREATE TABLE `user_setting`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `USERKEY` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户自定义键',
  `USERVALUE` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户自定义设置',
  PRIMARY KEY (`USERID`, `USERKEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户自定义设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_setting
-- ----------------------------
INSERT INTO `user_setting` VALUES ('5a72c8733d769a75b6309ff0', '1', '{\"lng\":119.97085441538619,\"zoom\":4,\"lat\":27.563785556517328}');

SET FOREIGN_KEY_CHECKS = 1;
