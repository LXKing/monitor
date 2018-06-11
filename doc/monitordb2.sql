/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.252
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : 192.168.1.252:3306
 Source Schema         : monitordb2

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : 65001

 Date: 06/06/2018 09:18:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for MOTO2C
-- ----------------------------
DROP TABLE IF EXISTS `MOTO2C`;
CREATE TABLE `MOTO2C`  (
  `MOTORCADEID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CARID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`MOTORCADEID`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `SPEED` float unsigned NOT NULL COMMENT '速度',
  `ANGLE` smallint(6) UNSIGNED NOT NULL COMMENT '方向',
  `ALARMS` int(11) UNSIGNED NOT NULL COMMENT '报警',
  `STATUS` int(11) UNSIGNED NOT NULL COMMENT '状态',
  `MILEAGE` double unsigned NOT NULL COMMENT '里程',
  `OILMASS` float unsigned NOT NULL COMMENT '油量',
  `VSS` float unsigned NOT NULL COMMENT '车速',
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
-- Records of alarm
-- ----------------------------
INSERT INTO `alarm` VALUES ('5b077fd38743d61830d3bd3b', '120190994308', '2018-05-25 09:37:36', '2018-05-25 11:15:31', 112.559573, 37.735160, 0, 4.1, 93, 2, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, '2018-05-25 11:14:44');

-- ----------------------------
-- Table structure for automobile
-- ----------------------------
DROP TABLE IF EXISTS `automobile`;
CREATE TABLE `automobile`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司',
  `MOTORCADEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属车队',
  `DEVICEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号唯一编号',
  `PLATENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号码',
  `PLATECOLOR` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌颜色',
  `VEHICLECOLOR` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `VEHICLETYPE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `VEHICLEVOLTAGE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆电压',
  `CARRYTYPE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '载运类型',
  `INITIALMILEAGE` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '初始里程',
  `OILWEAR` decimal(10, 1) NULL DEFAULT NULL COMMENT '百公里油耗',
  `USEFULLIFE` int(11) NULL DEFAULT NULL COMMENT '使用年限',
  `ADMINAREA` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行政区域',
  `MARKER` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆图标',
  `ROTATE` bit(1) NULL DEFAULT NULL COMMENT '旋转车辆图标否',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATETIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
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
  `FUELTYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `PLATENUMBER`(`PLATENUMBER`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `MOTORCADEID`(`MOTORCADEID`) USING BTREE,
  INDEX `DEVICEID`(`DEVICEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of automobile
-- ----------------------------
INSERT INTO `automobile` VALUES ('5a74182b3d769a7dcc878d5d', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5a7417fc3d769a7dcc878d5c', '808-1', '蓝色', '白色', '中型客车', '12V', '客车', 0, 22.0, 0, '广东省', '00.png', b'1', '444', '2018-02-02 15:50:03', '2018-05-24 19:17:23', '2018-02-02', '2018-02-22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5aa741df7b03680b92edf012', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aa7419b7b03680b92edf011', '京B37A93', '蓝色', '黑色', '其他车辆', '12V', '小轿车', 0, 8.0, 0, '山西省', '00.png', b'1', '', '2018-03-13 11:13:35', '2018-05-24 19:15:42', '2018-05-08', '2018-05-30', '2018-03-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '22222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5ae43e64af775217a0b6fd31', '5ae43d9baf775217a0b6fcf3', '5ae43dd7af775217a0b6fd2e', '5ae43e2caf775217a0b6fd30', '子公司车', '蓝色', '白色', '农用车', '12V', '客车', 22, 222.0, 22, '韶关市', '', b'1', '222', '2018-04-28 17:27:22', '2018-04-28 17:27:22', '2018-04-03', '2018-04-24', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5ae834cfaf775215949b0cfc', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5add91e0431ab70cd4cc6ca4', '868120187322661', '蓝色', '白色', '农用车', '12V', '客车', 2322, 32323.0, 0, '浈江区', '00.png', b'1', 'ok', '2018-05-01 17:35:11', '2018-05-28 17:26:16', '2018-05-21', '2018-05-28', '2018-05-04', NULL, '2', '2', '2', '2', '2', '2', '222222222', '2', '2', '2018-05-22 00:00:00', '2', '2', '2', '2', '2', '2018-05-21 00:00:00', '2', '2', '2', NULL, '2');
INSERT INTO `automobile` VALUES ('5ae83510af775215949b0cfd', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5aebb3acaf77521014caab59', '晋A7993U*', '蓝色', '白色', '农用车', '12V', '小轿车', 21321, 312312.0, 0, '山西省', '00.png', b'1', 'ok', '2018-05-01 17:36:16', '2018-05-24 19:16:35', '2018-05-15', '2018-05-13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '23232', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5ae9754aaf7752140c24e62c', '558ffc6603c70e31a2a53a30', '5ae97524af7752140c24e62b', '5add91ca431ab70cd4cc6ca2', '积极', '蓝色', '白色', '危险品运输车', '12V', '客车', 33, 3.0, 0, '天津市', '00.png', b'0', 'ok', '2018-05-02 16:22:34', '2018-05-24 19:16:25', '2018-05-09', '2018-05-07', '2018-05-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3233', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5af0196d7b036828a39464da', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5add940a431ab70cd4cc6cc5', '晋AT3214', '蓝色', '白色', '危险品运输车', '12V', '小轿车', 23, 322.0, 0, '山西省', '00.png', b'1', '', '2018-05-07 17:16:29', '2018-05-24 19:16:12', '2018-05-07', '2018-05-07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '23232323', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `automobile` VALUES ('5af971297b036831eeeb65cb', '558ffc6603c70e31a2a53a30', '5a72abd83d769a75b6309dee', '5af970e27b036831eeeb65ca', '晋A7993U', '蓝色', '黑色', '客车', '12V', '小轿车', 0, 23.0, 0, '山西省', '00.png', b'1', '', '2018-05-14 19:21:13', '2018-05-24 19:16:48', '2018-05-14', '2019-05-14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '23232', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `automobile_automobile_owner` VALUES ('5add924c431ab70cd4cc6ca7', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5add9254431ab70cd4cc6ca8', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5add927d431ab70cd4cc6cad', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_automobile_owner` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419293d769a7dcc878df4');
INSERT INTO `automobile_automobile_owner` VALUES ('5aaf61b2e6c6170930f59513', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add924c431ab70cd4cc6ca7', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add9254431ab70cd4cc6ca8', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add925b431ab70cd4cc6ca9', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add9263431ab70cd4cc6caa', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add9269431ab70cd4cc6cab', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add927d431ab70cd4cc6cad', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_automobile_owner` VALUES ('5add924c431ab70cd4cc6ca7', '5ae834cfaf775215949b0cfc');
INSERT INTO `automobile_automobile_owner` VALUES ('5add924c431ab70cd4cc6ca7', '5ae83510af775215949b0cfd');
INSERT INTO `automobile_automobile_owner` VALUES ('5ae14d3d0d8a3b0408e6759a', '5ae83510af775215949b0cfd');

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
INSERT INTO `automobile_driveer` VALUES ('5a74182b3d769a7dcc878d5d', '5a7419353d769a7dcc878df5');
INSERT INTO `automobile_driveer` VALUES ('5a7419353d769a7dcc878df5', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_driveer` VALUES ('5a7419353d769a7dcc878df5', '5ae834cfaf775215949b0cfc');
INSERT INTO `automobile_driveer` VALUES ('5a7419353d769a7dcc878df5', '5ae83510af775215949b0cfd');
INSERT INTO `automobile_driveer` VALUES ('5a7419353d769a7dcc878df5', '5af0196d7b036828a39464da');
INSERT INTO `automobile_driveer` VALUES ('5add9324431ab70cd4cc6cbd', '5af0196d7b036828a39464da');
INSERT INTO `automobile_driveer` VALUES ('5add9324431ab70cd4cc6cbd', '5af971297b036831eeeb65cb');
INSERT INTO `automobile_driveer` VALUES ('5ae83319af775215949b0cf8', '5ae83510af775215949b0cfd');
INSERT INTO `automobile_driveer` VALUES ('5ae8332aaf775215949b0cf9', '5a74182b3d769a7dcc878d5d');
INSERT INTO `automobile_driveer` VALUES ('5ae8332aaf775215949b0cf9', '5aa741df7b03680b92edf012');
INSERT INTO `automobile_driveer` VALUES ('5ae8332aaf775215949b0cf9', '5ae834cfaf775215949b0cfc');
INSERT INTO `automobile_driveer` VALUES ('5ae8332aaf775215949b0cf9', '5ae83510af775215949b0cfd');
INSERT INTO `automobile_driveer` VALUES ('5ae8332aaf775215949b0cf9', '5ae9754aaf7752140c24e62c');

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
INSERT INTO `automobile_owner` VALUES ('5add924c431ab70cd4cc6ca7', '558ffc6603c70e31a2a53a30', '车主', '身份证', '4404', '2018-05-04 10:45:22', NULL, NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5ae14d3d0d8a3b0408e6759a', '558ffc6603c70e31a2a53a30', 'ok', '学生证', '123456', '2018-05-04 09:25:48', NULL, NULL, NULL, NULL);
INSERT INTO `automobile_owner` VALUES ('5ae96c9eaf775213e02d87d6', '558ffc6603c70e31a2a53a30', '测试', '身份证', '999999', '2018-05-04 09:26:31', NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圆形区域' ROW_FORMAT = Compact;

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
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FAX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司资料' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('558ffc6603c70e31a2a53a30', '深圳锐讯易通科技有限公司', '锐讯易通', '', '', '', '', '', b'0', '2018-04-04 15:33:47', '2015-08-29', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5a72ab073d769a75b6309dec', '深圳锐讯易通科技有限公司', '锐讯易通', '666', 'mmp', '999', '555', '777', b'0', '2018-05-29 11:05:01', '2018-02-01', NULL, 'WW', NULL, '3443', 'WW');
INSERT INTO `company` VALUES ('5adf0d6f431ab7133404e508', '23', '555', '23', '323', '23', '23', '23', b'1', '2018-04-24 18:57:19', '2018-04-02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `company` VALUES ('5ae43d9baf775217a0b6fcf3', '子公司', '子公司', '子公司', '子公司', '子公司', '子公司', '子公司', b'1', '2018-04-28 17:24:02', '2018-04-18', NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `company_permission` VALUES ('5a72ab073d769a75b6309dec', 'center.deviceData');
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
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.company');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.company.authorize');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.company.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.company.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.company.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.device');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.device.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.device.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.device.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.licence');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.drivingLicence');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.VehicleLicence');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.VehicleRegister');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addPois');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removePoi');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.mapOption');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.marker');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.marker.remove');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.marker.save');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.poi');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.role');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.role.authorize');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.role.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.role.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.role.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.addSections');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.addVehicles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.removeSection');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.addMonitors');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.addRoles');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.removeMonitor');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.removeRole');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.user.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.addDrivers');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.addOwners');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.create');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.delete');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.removeDriver');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.removeOwner');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.update');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.alarm');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.alarm.processAll');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.alarm.processOnce');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.accidentDoubtLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.deviceEventReport');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.drivingRecorder');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.locateLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.loginLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimedia');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimediaEvent');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimediaRetrieval');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.parameterLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.powerLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.speedLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.speedStatusLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.deviceData.timeoutLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.locate');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.locate.datalog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.locate.instruct');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.locate.vehileinfo');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'center.replay');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'security.saveMyinfo');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'security.saveMyKey');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.areaIo');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.areaOverspeed');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.currentOnlineOffline');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.historyOnlineOffline');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.historyOnlineTime');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.mileageOil');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.operateLog');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.routeDeviation');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.sectionOverspeed');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.timeoutParking');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.vehicleAlarm');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.vehicleFatigueDriving');
INSERT INTO `company_permission` VALUES ('5ae43d9baf775217a0b6fcf3', 'statistics.vehicleOverspeed');

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
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 1, '蓝色', '2015-07-29 22:02:26', NULL, '1', '1');
INSERT INTO `dictionary` VALUES (2, 1, '黄色', '2015-07-29 22:03:44', NULL, '2', '2');
INSERT INTO `dictionary` VALUES (3, 1, '黑色', '2018-05-03 13:55:51', NULL, '3', '3');
INSERT INTO `dictionary` VALUES (4, 1, '白色', '2018-05-03 15:06:49', NULL, '8', '4');
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
INSERT INTO `dictionary` VALUES (29, 3, '农用车', '2018-05-03 14:23:30', NULL, '50', '50');
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
INSERT INTO `dictionary` VALUES (96, 11, '专业保养', '2018-05-03 13:56:03', NULL, '999', '2');
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
INSERT INTO `dictionary` VALUES (115, 5, '海淀区', '2018-04-24 09:28:44', 46, '11', '1');
INSERT INTO `dictionary` VALUES (116, 5, '韶关市', '2018-04-24 09:29:02', 64, '1', '1');
INSERT INTO `dictionary` VALUES (117, 5, '浈江区', '2018-04-24 09:29:23', 116, '1', '1');
INSERT INTO `dictionary` VALUES (118, 15, '康凯斯', '2018-04-28 10:30:56', NULL, '0x02', '2');
INSERT INTO `dictionary` VALUES (119, 15, '808', '2018-04-28 10:31:02', NULL, '0x01', '1');
INSERT INTO `dictionary` VALUES (134, 1, '1', '2018-05-03 16:54:22', 5, '1', '1');
INSERT INTO `dictionary` VALUES (135, 1, '111', '2018-05-03 16:54:31', 134, '111', '111');
INSERT INTO `dictionary` VALUES (136, 1, '2', '2018-05-03 16:54:38', 5, '2', '2');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `ID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司唯一编号',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `SEX` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `IDTYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件号',
  `DRIVINGLICENSENUMBER` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾驶证号',
  `REMARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
  `DRIVINGLICENSEEXPIRYDATE` date NULL DEFAULT NULL COMMENT '驾驶证有效日期',
  `PHOTO` blob NULL COMMENT '相片',
  `REGISTRATIONDATE` datetime(0) NULL DEFAULT NULL COMMENT '注册时间\r\n',
  `PERMITCODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机准驾证号\r\n',
  `EMERGENCYCONTACTA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人1\r\n',
  `EMERGENCYCONTACTB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人2\r\n',
  `OFFICEDATE` datetime(0) NULL DEFAULT NULL,
  `POSITION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DRIVERAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '司机资料' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('5a7419353d769a7dcc878df5', '558ffc6603c70e31a2a53a30', '司机', '男', '66666', '工作证', '99999', '99999', 'ok', NULL, '2018-05-08', NULL, NULL, NULL, '232', '32323', '2018-05-01 00:00:00', '323', '23', NULL);
INSERT INTO `driver` VALUES ('5add9324431ab70cd4cc6cbd', '558ffc6603c70e31a2a53a30', '测试8', '女', '8888', '学生证', '885585', '88888', '8888', NULL, '2018-05-29', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5ae83319af775215949b0cf8', '558ffc6603c70e31a2a53a30', '测试', '女', '测试', '驾驶证', '8888', '测试', '测试', NULL, '2018-05-24', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driver` VALUES ('5ae8332aaf775215949b0cf9', '558ffc6603c70e31a2a53a30', '不知道', '男', '不知道', '身份证', '999', '不知道', '不知道', NULL, '2018-05-31', NULL, NULL, NULL, 'の', 'の嗯嗯', '2018-05-02 00:00:00', '333', '33', NULL);
INSERT INTO `driver` VALUES ('5b0bbcf5ee8c3a15f021ffd1', '558ffc6603c70e31a2a53a30', 'ee', '男', 'ee', '身份证', 'ee', 'ee', 'ee', '2018-05-28 16:25:29', '2018-05-23', NULL, NULL, NULL, 'ee', 'ee', '2018-04-30 00:00:00', 'ee', 'ee', '深圳锐讯易通科技有限公司');

-- ----------------------------
-- Table structure for driver_d_l
-- ----------------------------
DROP TABLE IF EXISTS `driver_d_l`;
CREATE TABLE `driver_d_l`  (
  `driver_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `d_l_id` int(25) NOT NULL,
  PRIMARY KEY (`driver_id`, `d_l_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of driver_d_l
-- ----------------------------
INSERT INTO `driver_d_l` VALUES ('5a7419353d769a7dcc878df5', 112);
INSERT INTO `driver_d_l` VALUES ('5add9324431ab70cd4cc6cbd', 112);
INSERT INTO `driver_d_l` VALUES ('5add9324431ab70cd4cc6cbd', 113);

-- ----------------------------
-- Table structure for driver_v_l
-- ----------------------------
DROP TABLE IF EXISTS `driver_v_l`;
CREATE TABLE `driver_v_l`  (
  `driver_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vehicle_licence_id` int(25) NOT NULL,
  PRIMARY KEY (`driver_id`, `vehicle_licence_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for driver_v_r
-- ----------------------------
DROP TABLE IF EXISTS `driver_v_r`;
CREATE TABLE `driver_v_r`  (
  `driver_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `v_r_id` int(25) NOT NULL,
  PRIMARY KEY (`driver_id`, `v_r_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of driver_v_r
-- ----------------------------
INSERT INTO `driver_v_r` VALUES ('5a7419353d769a7dcc878df5', 1);

-- ----------------------------
-- Table structure for driving_lisence
-- ----------------------------
DROP TABLE IF EXISTS `driving_lisence`;
CREATE TABLE `driving_lisence`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `company_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机名',
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `nationality` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国籍',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `birthdate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `date_of_first_issue` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初次领证日期',
  `car_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准驾车型',
  `valid_period` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效期限',
  `doc_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案编号',
  `record` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '驾驶证' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driving_lisence
-- ----------------------------
INSERT INTO `driving_lisence` VALUES (111, NULL, NULL, NULL, NULL, 'dddd', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `driving_lisence` VALUES (112, '558ffc6603c70e31a2a53a30', '大佬', '男', '北京', '北京', '2018-04-17', '2018-04-17', '大卡车', '2018-04-02', '888', '北京');
INSERT INTO `driving_lisence` VALUES (113, '558ffc6603c70e31a2a53a30', '渣渣', '女', '你猜', '你猜', '2018-04-24', '2018-04-24', '无', '2018-04-02', '你猜', 'ok');

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
  `Protocol` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协议',
  `ProtocolName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REPAIR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修',
  `EDITUSER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `DEVICENUMBER`(`DEVICENUMBER`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE,
  INDEX `SIMCARDNUMBER`(`SIMCARDID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('5a7417fc3d769a7dcc878d5c', '558ffc6603c70e31a2a53a30', '5add90dd431ab70cd4cc6c90', '13311012700', 'XA-BJ-101', '联XXX', '999', 0, b'0', b'0', '588', '2018-05-23 19:24:17', '2018-02-02', '2018-02-02', '2018-02-02', NULL, NULL, NULL, NULL, NULL, NULL, '123213', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5aa7419b7b03680b92edf011', '558ffc6603c70e31a2a53a30', '5a72ac0c3d769a75b6309def', '10189415505', '星XXX', '联XXX', '33', 99, b'0', b'0', '3', '2018-05-28 11:12:05', '2018-05-07', '2018-05-01', '2018-05-15', NULL, NULL, NULL, '2018-05-01', '2018-05-01', '2018-05-16 00:00:00', '33333333333', '3', '2018-05-14 00:00:00', '0x01', '808', '222', 'rayton02');
INSERT INTO `equipment` VALUES ('5add91ca431ab70cd4cc6ca2', '558ffc6603c70e31a2a53a30', '5add911d431ab70cd4cc6c93', '120190994309', '翰XXX', '世XXX', '324234324', 0, b'1', b'1', '23423', '2018-05-23 19:23:56', '2018-05-16', '2018-05-15', '2018-05-01', NULL, NULL, NULL, NULL, NULL, NULL, '57275753', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5add91e0431ab70cd4cc6ca4', '558ffc6603c70e31a2a53a30', '5add90f3431ab70cd4cc6c91', '120187322661', 'XA-TY-101', '联XXX', '342343242', 0, b'1', b'1', '2324', '2018-05-23 19:23:37', '2018-04-03', '2018-05-08', '2018-05-01', NULL, NULL, NULL, NULL, NULL, NULL, '23232323', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5add940a431ab70cd4cc6cc5', '558ffc6603c70e31a2a53a30', '5add9106431ab70cd4cc6c92', '120190994308', '翰XXX', '翰XXX', '324324', 0, b'0', b'1', '2343', '2018-05-23 19:23:47', '2018-05-15', '2018-05-08', '2018-05-08', NULL, NULL, NULL, NULL, NULL, NULL, '23454524', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5add941b431ab70cd4cc6cc7', '558ffc6603c70e31a2a53a30', '5a7417dd3d769a7dcc878d5b', '234324', '翰XXX', '联XXX', '324324324', 0, b'1', b'1', '234', '2018-05-23 19:24:23', '2018-05-08', '2018-04-30', '2018-05-01', NULL, NULL, NULL, NULL, NULL, NULL, '12312', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5ae43e2caf775217a0b6fd30', '5ae43d9baf775217a0b6fcf3', '5ae43dffaf775217a0b6fd2f', '9999999', '翰XXX', '联XXX', '1111111', 111, b'1', b'1', '1111', '2018-04-28 17:26:26', '2018-04-17', '2018-04-10', '2018-04-10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `equipment` VALUES ('5ae97456af7752140c24e5f0', '558ffc6603c70e31a2a53a30', '5ae972c0af7752140c24e5ef', '894815631', '翰XXX', '联XXX', '72578857875758', 100, b'1', b'0', '', '2018-05-23 19:24:28', '2018-05-15', '2018-05-07', '2018-05-07', NULL, NULL, NULL, NULL, NULL, NULL, '123312', NULL, NULL, '0x01', '808', NULL, NULL);
INSERT INTO `equipment` VALUES ('5aebb3acaf77521014caab59', '558ffc6603c70e31a2a53a30', '5ac43e5c7136fa10e89cea90', '120187322620', 'HS-BB-454', '联XXX', '222', 5, b'0', b'0', '6', '2018-05-23 19:23:30', '2018-05-08', '2018-05-01', '2018-05-01', NULL, NULL, NULL, NULL, NULL, NULL, '3333333', NULL, NULL, '0x02', '康凯斯', NULL, NULL);
INSERT INTO `equipment` VALUES ('5af970e27b036831eeeb65ca', '558ffc6603c70e31a2a53a30', '5af970337b036831eeeb65ac', '120191007647', '星XXX', '联XXX', '868120191007647', 0, b'0', b'0', '', '2018-05-24 19:18:30', '2018-05-14', '2018-05-14', '2018-05-14', NULL, NULL, NULL, NULL, NULL, NULL, '东方饭店', NULL, NULL, '0x02', '康凯斯', NULL, NULL);

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
INSERT INTO `equipment_region_log` VALUES ('0a9fb68e-3fd6-4ce3-9fe0-4b0c1fc118fb', '10189415505', 2, 1, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:25', NULL);
INSERT INTO `equipment_region_log` VALUES ('0c36b632-de08-4e34-9995-59856dd8acd0', '13311012700', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:42', NULL);
INSERT INTO `equipment_region_log` VALUES ('1440fd51-b3ed-4b1a-8779-57d8867ca332', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:29', NULL);
INSERT INTO `equipment_region_log` VALUES ('150d888b-03b7-4e3e-98e6-e2609dbe25ee', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('1560268f-0d59-483e-9a5b-210e7175f953', '10189415505', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('1bb3547c-5ade-4224-8bc2-5975f727a75f', 'regtre', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('30f78b76-7e20-4d4b-80a4-0c7be256dcfa', '13311012700', 1, 3, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:42', NULL);
INSERT INTO `equipment_region_log` VALUES ('32f1407a-12a7-48e1-be91-6b90afc0a988', '10189415505', 3, 4, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:55', NULL);
INSERT INTO `equipment_region_log` VALUES ('3a1e9ffe-9486-46ae-89a9-25202d1a69af', '13311012700', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `equipment_region_log` VALUES ('3c6aff6a-2a74-49e1-835f-a12e2fd86552', '13311012700', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `equipment_region_log` VALUES ('3cf847f2-3260-40dc-b22c-8f79c272dc11', 'ddsfd', 1, 2, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:38:36', NULL);
INSERT INTO `equipment_region_log` VALUES ('3e5dd1e9-2962-42b1-b82b-b8327659fed5', '13311012700', 2, 1, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:25', NULL);
INSERT INTO `equipment_region_log` VALUES ('40ab650f-08a5-4867-a5b3-8afa0fca1de8', '10189415505', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:04', NULL);
INSERT INTO `equipment_region_log` VALUES ('418cf7c6-b733-4042-b701-7f2cfb0c67b5', 'regtre', 2, 1, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:25', NULL);
INSERT INTO `equipment_region_log` VALUES ('46655b8b-badf-4964-9dea-c91dcda13ea0', '13311012700', 2, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:01', NULL);
INSERT INTO `equipment_region_log` VALUES ('48ef06ee-4227-4729-9df8-74ec0c544f15', '13311012700', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:14', NULL);
INSERT INTO `equipment_region_log` VALUES ('4a9711cc-c6a7-4640-a4ba-60275ec10082', '13311012700', 2, 3, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:44', NULL);
INSERT INTO `equipment_region_log` VALUES ('4ad70f3e-6752-493b-b82d-0a09ff459fb3', 'regtre', 2, 3, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:44', NULL);
INSERT INTO `equipment_region_log` VALUES ('4b816a84-2a13-43c0-8e6d-d9ac6b10b929', '13311012700', 1, 4, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:10:00', NULL);
INSERT INTO `equipment_region_log` VALUES ('4e0286a8-f4c2-41ca-8718-ef35a05a723c', 'regtre', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:13', NULL);
INSERT INTO `equipment_region_log` VALUES ('539cda10-fd44-4810-9a04-ed4142c7873b', '13311012700', 3, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:52:39', NULL);
INSERT INTO `equipment_region_log` VALUES ('5bb8fcf5-46d9-438a-a275-7cf553e873ac', 'ddsfd', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:38:29', NULL);
INSERT INTO `equipment_region_log` VALUES ('5faa11b1-e2e9-4ca2-bf54-ebbeea7a3c7d', '13311012700', 1, 2, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:38:34', NULL);
INSERT INTO `equipment_region_log` VALUES ('61a9cb98-045b-49b7-931b-9354669deec3', '10189415505', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `equipment_region_log` VALUES ('62219e81-dcd5-495b-97b4-5d5fb2c35b59', '10189415505', 2, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:03', NULL);
INSERT INTO `equipment_region_log` VALUES ('66032486-22cd-45f5-8c9b-3c33b1a75b25', '10189415505', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('66a2687a-6602-40c1-9c76-c6755342ff93', '10189415505', 1, 3, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:42', NULL);
INSERT INTO `equipment_region_log` VALUES ('6a3a8ca1-9878-4020-937e-ef78e9823ea9', '10189415505', 1, 2, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:26', NULL);
INSERT INTO `equipment_region_log` VALUES ('6fe8e695-a9ce-4223-bb89-2d684500c981', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('797f9ab0-1e83-4ec5-b911-393a9fe0944b', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:15', NULL);
INSERT INTO `equipment_region_log` VALUES ('7b408eff-d2ad-4059-b507-66d2f11c14ef', '10189415505', 1, 2, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:38:35', NULL);
INSERT INTO `equipment_region_log` VALUES ('7c10f8a5-e0d4-4ca7-81cf-ae1f483e2192', '13311012700', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `equipment_region_log` VALUES ('838545bc-99ba-4fa5-ac65-efa1bcc1ef7b', '10189415505', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:13', NULL);
INSERT INTO `equipment_region_log` VALUES ('8643cc2c-a893-4847-b10f-59ccf0efc2f7', 'ddsfd', 2, 1, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:25', NULL);
INSERT INTO `equipment_region_log` VALUES ('96d05d27-2054-4f8f-9bfe-e651d30cf4ab', '10189415505', 1, 4, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:56:21', NULL);
INSERT INTO `equipment_region_log` VALUES ('9a659cc8-5369-4f0e-8b3f-235d989598d0', '13311012699', 1, 1, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:31', NULL);
INSERT INTO `equipment_region_log` VALUES ('a15579d2-cbe7-4ca8-8534-f4d85e564c05', 'regtre', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('a99b21e4-f3d6-4bec-8da1-f1894584522f', '13311012700', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:02:16', NULL);
INSERT INTO `equipment_region_log` VALUES ('aee6ab70-1f1f-44d7-8a51-42293eb1e647', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:47', NULL);
INSERT INTO `equipment_region_log` VALUES ('b30de33e-b14d-45a0-abb3-7e042aa019a3', 'regtre', 3, 4, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:55', NULL);
INSERT INTO `equipment_region_log` VALUES ('cb07e646-7f57-4022-81f6-e020deacc268', '13311012700', 1, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:00:02', NULL);
INSERT INTO `equipment_region_log` VALUES ('d1428210-b3f5-4584-84ff-44863d5e1e7b', 'ddsfd', 2, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-30 14:39:13', NULL);
INSERT INTO `equipment_region_log` VALUES ('d619d21a-776d-4830-9cc9-8b9a7453662c', '13311012700', 2, 3, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 15:36:11', NULL);
INSERT INTO `equipment_region_log` VALUES ('d78b458e-e73f-44c5-aa62-4353234c6f30', '10189415505', 1, 4, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:10:00', NULL);
INSERT INTO `equipment_region_log` VALUES ('e696227c-c546-41ad-90f7-6c2f6050315a', '10189415505', 1, 1, 1, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:36', NULL);
INSERT INTO `equipment_region_log` VALUES ('e7d5eb71-94ab-4875-9790-5d4a649d609d', '13311012699', 1, 1, 1, '772cf348-073a-11e8-89e3-00163e32414e', 'xin', '2018-02-23 10:37:41', NULL);
INSERT INTO `equipment_region_log` VALUES ('f110bfbc-63c5-4589-89bc-5d31645a3c93', '10189415505', 1, 1, 3, '92ff3411-0725-11e8-89e3-00163e32414e', 'rayton02', '2018-03-19 14:55:27', NULL);
INSERT INTO `equipment_region_log` VALUES ('f3ad4218-5b2f-4f06-9d59-4f3691d9102d', '10189415505', 2, 3, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:44', NULL);
INSERT INTO `equipment_region_log` VALUES ('fa68795a-68e4-4ada-b73a-0a8a6827f3b4', '13311012700', 3, 4, 3, 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '系统管理员', '2018-04-03 14:09:55', NULL);
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
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `companyId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lng` decimal(65, 30) NULL DEFAULT NULL,
  `alt` decimal(65, 30) NULL DEFAULT NULL,
  `showName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `share` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1043 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of icon
-- ----------------------------
INSERT INTO `icon` VALUES (1, 'car', '00.png', '2018-04-23 18:47:30', '558ffc6603c70e31a2a53a30', NULL, NULL, NULL, NULL);

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
INSERT INTO `log` VALUES ('2018-03-30 11:27:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:27:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:27:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:27:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:27:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-03-30 11:28:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 11:28:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 11:28:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:00:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:01:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:01:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:01:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:01:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-03-30 14:02:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:32:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:32:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:32:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:33:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:34:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:34:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:35:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:35:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:35:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:35:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:36:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:36:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:37:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-03-30 14:37:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:37:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:38:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:38:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '矩形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '矩形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '矩形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '矩形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:38:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-30 14:38:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域解除车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:38:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 14:38:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-03-30 14:39:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '圆形区域绑定车辆');
INSERT INTO `log` VALUES ('2018-03-30 14:39:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:39:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:40:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:40:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:40:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-03-30 14:40:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-03-30 15:28:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 15:29:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 15:29:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-03-30 15:47:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-03-30 15:47:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-03-30 16:10:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:41:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 10:41:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-02 10:41:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-02 10:41:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-02 10:41:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 10:42:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:43:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:46:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:46:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:47:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:50:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:50:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 10:50:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:51:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 10:51:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:10:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:13:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:16:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:19:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:22:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:26:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:32:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 11:32:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 13:38:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 13:46:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 13:46:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 13:48:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 13:53:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 13:53:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 13:53:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 13:53:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 13:54:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 14:07:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 14:07:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 14:07:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 14:08:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 14:08:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 14:11:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 14:23:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 16:01:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-02 16:42:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:45:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 16:50:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:55:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 16:55:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:10:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:11:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:18:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:19:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:22:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:27:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-02 17:27:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:27:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:28:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:29:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:36:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:36:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-02 17:36:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 08:41:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 08:41:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 08:58:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 08:59:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:00:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:05:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:08:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:11:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:12:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:17:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:17:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:18:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:22:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:24:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:25:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:25:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:26:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:28:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:31:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:33:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:34:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:35:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:35:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 09:53:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 09:53:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 09:54:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 11:02:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:02:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 11:05:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 11:07:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:07:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除用户资料');
INSERT INTO `log` VALUES ('2018-04-03 11:07:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除用户资料');
INSERT INTO `log` VALUES ('2018-04-03 11:07:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除用户资料');
INSERT INTO `log` VALUES ('2018-04-03 11:07:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:07:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:08:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-03 11:08:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-03 11:08:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-03 11:08:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:08:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-03 11:08:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-03 11:08:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:08:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-03 11:08:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:09:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-03 11:09:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-03 11:09:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-03 11:09:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-03 11:10:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 11:52:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 11:53:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:04:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:04:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:04:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:04:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 14:04:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 14:08:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-04-03 14:08:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:08:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除车辆保养记录');
INSERT INTO `log` VALUES ('2018-04-03 14:09:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除圆形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除圆形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除圆形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除圆形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除矩形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除多边形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除多边形区域');
INSERT INTO `log` VALUES ('2018-04-03 14:09:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除路段');
INSERT INTO `log` VALUES ('2018-04-03 14:09:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除路线');
INSERT INTO `log` VALUES ('2018-04-03 14:09:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除路线');
INSERT INTO `log` VALUES ('2018-04-03 14:09:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除路线');
INSERT INTO `log` VALUES ('2018-04-03 14:09:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:09:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除兴趣点');
INSERT INTO `log` VALUES ('2018-04-03 14:09:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除兴趣点');
INSERT INTO `log` VALUES ('2018-04-03 14:09:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-04-03 14:10:03', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:10:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-04-03 14:10:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-04-03 14:10:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除角色资料');
INSERT INTO `log` VALUES ('2018-04-03 14:11:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-03 14:11:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 14:25:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:25:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:25:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:25:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:25:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-03 14:25:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:25:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:26:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-03 14:28:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-03 14:36:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:36:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 14:36:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-04-03 14:54:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:55:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:55:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:55:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:55:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:55:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 14:56:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:18:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:18:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:19:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:23:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:23:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:24:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:24:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:24:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:24:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:24:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:26:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-03 15:31:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:32:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:44:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:44:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:44:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:46:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:52:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:55:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:56:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:56:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:56:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:57:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:58:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:58:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 15:59:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:04:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:05:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:07:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:08:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:09:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:15:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:18:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:19:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:20:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:20:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:20:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:26:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:26:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-03 16:34:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:03:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:03:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:03:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:22:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:29:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:38:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:44:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:44:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 09:44:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:19:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:20:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:20:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:22:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:22:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:24:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:25:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:25:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:25:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:25:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:25:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:26:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:26:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:26:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:27:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:28:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:29:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:29:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:29:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:30:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:31:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:32:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:32:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:32:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:33:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:33:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:35:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:37:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:38:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:38:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:38:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:38:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:38:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:39:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:40:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:40:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:40:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:40:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:40:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:42:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:43:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:51:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:52:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:52:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:52:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:53:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 10:53:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:54:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-04 10:54:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-04-04 10:54:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:53:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:55:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-04 10:55:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:55:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 10:56:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-04 10:56:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:56:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:56:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:57:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:58:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:58:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:58:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:00:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 11:00:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 11:00:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 10:59:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:05:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:14:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:32:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:39:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:42:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 11:59:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 11:59:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 11:59:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 12:56:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 13:43:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 13:54:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 13:54:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-04 13:54:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 13:54:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-04 13:54:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-04 13:55:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 13:55:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 13:55:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:00:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:00:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:01:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:02:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:02:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:02:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:02:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:03:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:03:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:04:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:04:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:05:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:07:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:07:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:08:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:08:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 14:08:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:08:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:09:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:09:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 14:10:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:10:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 14:10:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-04-04 14:10:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-04-04 14:11:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-04 14:13:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-04 14:13:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:13:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 14:15:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:17:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:17:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:18:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:19:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:20:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:22:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:32:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:34:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:34:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:35:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:35:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:35:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-04 14:37:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:37:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-04 14:39:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-04 14:47:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:51:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:51:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:51:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:51:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:52:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:53:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:54:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:54:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:54:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:54:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:55:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 14:54:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 14:57:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:00:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:03:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:04:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:05:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:11:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:17:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:20:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:23:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:23:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:24:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:25:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:29:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:30:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:32:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:32:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:32:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:32:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:38:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:40:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:41:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:41:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:41:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:41:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:41:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-04 15:45:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:45:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:46:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:46:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:46:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:46:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:46:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的角色');
INSERT INTO `log` VALUES ('2018-04-04 15:50:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-04 15:50:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除角色');
INSERT INTO `log` VALUES ('2018-04-04 15:51:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:51:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:51:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:51:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:51:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:54:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的角色');
INSERT INTO `log` VALUES ('2018-04-04 15:55:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:55:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:56:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:56:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:56:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:56:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-04 15:57:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:55', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:57:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:58:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 15:58:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 15:58:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-04 15:58:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:00:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:28', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:01:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-04-04 16:02:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-04-04 16:02:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-04-04 16:02:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:02:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:03:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-04-04 16:03:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:03:57', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:03:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户解除角色');
INSERT INTO `log` VALUES ('2018-04-04 16:04:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:04:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除角色资料');
INSERT INTO `log` VALUES ('2018-04-04 16:06:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:07:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:07:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:07:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:08:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:09:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:10:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:10:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:10:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:10:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:12:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:12:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:12:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:12:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:17:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:24:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:31:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:33:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:34:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:36:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:38:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:38:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:38:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:39:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:40:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:42:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:43:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:44:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:44:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:44:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:44:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:44:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开矩形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开多边形区域管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路段管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开兴趣点管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图设置页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开地图图层管理页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆图标页面');
INSERT INTO `log` VALUES ('2018-04-04 16:45:32', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-04-04 16:46:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:49:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:55:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 16:56:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:00:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:00:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:00:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:00:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:01:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:01:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:03:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:03:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:04:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:05:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:05:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:06:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:06:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:06:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:06:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:06:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:08:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:08:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:09:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:33:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:35:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:35:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:35:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:35:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-04 17:46:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-04 17:46:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-04 17:46:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:52:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 07:52:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:52:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:52:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:52:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:53:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改角色资料');
INSERT INTO `log` VALUES ('2018-04-08 07:53:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 07:53:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:54:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:21', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:55:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除角色资料');
INSERT INTO `log` VALUES ('2018-04-08 07:56:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 07:56:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:09:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:09:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:09:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 08:09:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 08:21:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:43:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:43:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:44:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:45:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:49:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:53:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:54:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:54:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 08:54:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:00:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:02:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:04:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:05:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:05:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:06:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:07:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:09:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:10:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:12:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:12:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 09:12:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 09:13:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:14:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 09:14:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:14:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:16:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 09:18:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 10:17:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 10:17:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 10:17:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 10:17:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:16:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:30:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:30:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:30:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:30:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:31:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:32:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 11:33:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 11:33:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:33:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:33:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 11:34:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:42:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 13:43:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:43:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 13:45:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:47:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:47:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:47:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:49:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:49:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 13:49:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:49:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:51:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:51:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:51:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:52:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 13:52:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:52:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 13:53:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 14:22:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 16:23:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 16:23:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 16:23:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-08 16:23:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-08 16:23:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-08 16:24:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 17:16:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 17:53:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-08 17:53:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-08 17:55:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-08 19:10:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-09 10:21:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-09 12:45:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-09 18:32:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-09 18:33:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-09 18:34:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-09 18:37:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-10 11:36:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-10 17:31:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-12 15:59:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-16 15:22:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-16 15:24:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-16 15:25:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-16 15:26:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-16 15:28:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:21:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:22:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 14:22:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-18 14:22:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-18 14:22:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-18 14:22:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-18 14:37:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:38:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 14:40:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:42:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:42:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 14:43:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开报警查询页面');
INSERT INTO `log` VALUES ('2018-04-18 14:44:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-18 14:45:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:45:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 14:46:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:46:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 14:46:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:46:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 14:48:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 14:48:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:12:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:12:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:14:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:14:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:14:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:14:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:14:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:15:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:15:46', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:16:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-18 15:16:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-18 15:17:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:17:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的角色');
INSERT INTO `log` VALUES ('2018-04-18 15:18:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '角色授权');
INSERT INTO `log` VALUES ('2018-04-18 15:18:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-18 15:18:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:18:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:19:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:20:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:21:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:21:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:21:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:27:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '查询车辆资料');
INSERT INTO `log` VALUES ('2018-04-18 15:27:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '查询车辆资料');
INSERT INTO `log` VALUES ('2018-04-18 15:27:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开轨迹回放页面');
INSERT INTO `log` VALUES ('2018-04-18 15:28:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开报警查询页面');
INSERT INTO `log` VALUES ('2018-04-18 15:28:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-18 15:28:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-18 15:45:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-18 15:45:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:45:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-18 15:45:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-23 09:26:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-23 09:29:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-23 09:31:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-23 10:47:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-23 14:07:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-23 15:52:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-23 15:52:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-23 15:52:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-23 15:52:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:52:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:53:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:53:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:53:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:54:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:54:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:54:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:55:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:56:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:56:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-23 15:56:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:56:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:56:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:56:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:57:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:57:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:57:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:57:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 15:58:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:58:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:59:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-23 15:59:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 15:59:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-23 16:00:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-23 16:00:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:01:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:01:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:02:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:03:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:03:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:03:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:04:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开圆形区域页面');
INSERT INTO `log` VALUES ('2018-04-23 16:05:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-23 16:05:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-23 16:05:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-23 16:05:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-23 16:06:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 16:06:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 16:06:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 16:06:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 16:06:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 16:07:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-23 18:59:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-23 18:59:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-23 18:59:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-23 18:59:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-23 19:01:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-23 19:02:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-23 19:30:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-23 19:33:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-23 19:34:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-24 09:07:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-24 09:28:00', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-24 09:28:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-24 09:28:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-24 09:28:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面');
INSERT INTO `log` VALUES ('2018-04-24 09:28:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改字典项');
INSERT INTO `log` VALUES ('2018-04-24 09:28:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-04-24 09:29:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-04-24 10:14:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-24 10:14:11', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-24 10:14:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-24 10:14:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-24 10:14:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-24 10:15:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-24 10:16:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-24 10:57:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-24 10:58:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-24 10:58:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-24 10:58:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-24 10:58:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-24 10:58:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-24 11:43:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-24 11:44:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-24 13:49:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 13:50:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 13:52:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 13:52:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 13:53:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 13:53:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-24 17:18:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-24 17:36:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-24 18:12:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-24 18:13:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:17:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:21:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:28:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:30:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:31:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:32:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:49:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:52:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 18:56:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 19:03:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-24 19:04:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-25 08:40:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-25 09:09:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:09:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 09:18:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:19:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:22:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:24:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:24:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 09:24:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:31:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 09:46:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 10:41:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 10:42:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 10:44:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-25 10:45:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-25 10:45:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 10:48:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 13:35:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 13:37:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 13:37:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 14:15:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:15:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:17:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:20:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:22:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:55:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:55:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:55:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:56:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:56:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:57:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:57:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:57:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:57:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 14:57:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 14:57:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-25 15:00:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 15:14:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-25 15:14:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-25 15:30:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 15:30:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 15:35:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 15:37:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 15:37:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-25 16:37:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-25 16:54:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-04-25 16:55:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-04-25 16:55:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-25 16:55:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-25 16:57:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 16:58:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 17:05:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 17:06:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-25 17:06:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-25 17:06:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-25 17:08:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-25 17:08:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-25 17:08:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-25 17:08:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-25 18:57:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的角色');
INSERT INTO `log` VALUES ('2018-04-25 18:58:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:00:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:00:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:00:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:02:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:04:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-25 19:05:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改角色资料');
INSERT INTO `log` VALUES ('2018-04-25 19:06:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除角色资料');
INSERT INTO `log` VALUES ('2018-04-26 10:20:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的用户');
INSERT INTO `log` VALUES ('2018-04-26 10:22:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-04-26 10:22:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-04-26 10:22:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除用户资料');
INSERT INTO `log` VALUES ('2018-04-26 10:23:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除角色');
INSERT INTO `log` VALUES ('2018-04-26 10:23:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-26 10:23:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-26 10:25:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-26 10:29:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户解除监控对象');
INSERT INTO `log` VALUES ('2018-04-26 10:29:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-04-26 11:14:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:15:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:15:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:15:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-04-26 11:15:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-04-26 11:17:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-04-26 11:18:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-04-26 11:23:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:26:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:35:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:44:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-04-26 11:48:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-04-26 11:53:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-04-26 14:36:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-26 14:43:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-26 14:45:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-04-26 14:46:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-04-26 14:49:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-04-26 14:49:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-26 15:53:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 15:53:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 15:53:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:53:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:53:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:53:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-26 15:54:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 15:54:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-26 15:57:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 15:57:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 15:57:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:57:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-26 15:59:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-26 15:59:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-26 15:59:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 15:59:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:00:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:49', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:02:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-26 16:03:24', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-26 16:03:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:03:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:25', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:30', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:31', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:05:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改企业资料');
INSERT INTO `log` VALUES ('2018-04-26 16:05:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-26 16:06:22', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:36', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开驾驶员管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开SIM卡管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开终端管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:06:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:12:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面');
INSERT INTO `log` VALUES ('2018-04-26 16:40:06', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:44:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:46:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:51:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:54:01', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:54:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:54:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-26 16:56:58', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-26 17:36:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车队');
INSERT INTO `log` VALUES ('2018-04-26 17:38:54', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建新的车队');
INSERT INTO `log` VALUES ('2018-04-26 17:39:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '更新车队资料');
INSERT INTO `log` VALUES ('2018-04-26 17:39:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除车队资料');
INSERT INTO `log` VALUES ('2018-04-27 09:07:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-27 09:31:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-27 09:31:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-27 09:31:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-27 10:05:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-27 10:09:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:09:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:09:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:11:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:11:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:13:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 10:13:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 11:28:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-04-27 11:29:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-04-27 11:29:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-27 11:30:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-27 11:30:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '更新车队资料');
INSERT INTO `log` VALUES ('2018-04-27 11:39:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-27 11:39:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-04-27 11:40:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 11:40:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:43:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-04-27 13:43:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:43:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:49:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:50:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:50:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-04-27 13:51:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:52:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:56:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 13:59:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:00:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:01:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:01:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:02:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:02:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:02:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:03:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-27 14:03:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 16:00:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-27 16:00:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-27 17:19:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 17:19:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 17:19:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 17:25:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-27 17:26:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 17:26:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-27 17:26:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-28 13:43:33', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 13:43:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 13:43:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 13:51:26', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-28 13:51:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:19:59', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 14:20:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-28 14:20:47', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:25:53', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:26:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:26:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:29:37', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-28 14:38:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:38:20', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:38:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:39:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:40:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:40:07', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:40:52', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:42:43', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 14:44:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 15:32:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-28 17:22:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 17:23:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-04-28 17:23:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-04-28 17:23:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-28 17:24:40', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的车队');
INSERT INTO `log` VALUES ('2018-04-28 17:25:20', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-04-28 17:26:04', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-04-28 17:27:00', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-28 17:46:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-04-28 17:51:16', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的用户');
INSERT INTO `log` VALUES ('2018-04-28 17:51:20', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-04-28 17:55:01', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '创建新的角色');
INSERT INTO `log` VALUES ('2018-04-28 17:55:08', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '角色授权');
INSERT INTO `log` VALUES ('2018-04-28 17:55:19', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-04-28 17:56:24', '5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '999', '修改用户资料');
INSERT INTO `log` VALUES ('2018-04-28 17:59:49', '5ae43d9baf775217a0b6fcf3', '5ae44414af775203b8502abd', '渣渣辉', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-04-30 12:33:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-04-30 13:23:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-30 13:24:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-30 13:24:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-04-30 14:17:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-30 14:17:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '企业授权');
INSERT INTO `log` VALUES ('2018-04-30 14:18:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-30 14:40:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-04-30 14:40:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-04-30 14:40:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆解除车主');
INSERT INTO `log` VALUES ('2018-04-30 18:50:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-30 18:51:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-04-30 18:52:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-05-01 17:24:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建企业资料');
INSERT INTO `log` VALUES ('2018-05-01 17:24:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的角色');
INSERT INTO `log` VALUES ('2018-05-01 17:24:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-05-01 17:24:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的用户');
INSERT INTO `log` VALUES ('2018-05-01 17:24:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户分配监控对象');
INSERT INTO `log` VALUES ('2018-05-01 17:25:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '用户绑定角色');
INSERT INTO `log` VALUES ('2018-05-01 17:25:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:25:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:25:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:26:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:26:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:27:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车主资料');
INSERT INTO `log` VALUES ('2018-05-01 17:27:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:27:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:28:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:28:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-01 17:28:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:29:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-05-01 17:29:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-01 17:29:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-01 17:30:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:30:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:32:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-05-01 17:32:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '更新车队资料');
INSERT INTO `log` VALUES ('2018-05-01 17:32:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-05-01 17:33:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:33:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:33:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:33:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:33:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆资料');
INSERT INTO `log` VALUES ('2018-05-01 17:35:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:36:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:36:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:36:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:36:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:36:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 17:44:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-05-01 17:44:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-05-01 17:44:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定车主');
INSERT INTO `log` VALUES ('2018-05-01 18:04:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-01 18:04:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-01 18:05:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-01 18:05:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-01 18:06:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-01 18:09:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '驾驶员绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:10:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:10:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:10:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:10:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:12:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:12:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主解除车辆');
INSERT INTO `log` VALUES ('2018-05-01 18:13:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车主绑定车辆');
INSERT INTO `log` VALUES ('2018-05-02 15:09:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 15:09:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 15:12:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 15:12:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 15:12:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 15:27:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 15:27:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 15:40:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-02 15:45:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车主资料');
INSERT INTO `log` VALUES ('2018-05-02 15:53:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 15:53:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 15:54:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 15:54:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:11:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-05-02 16:18:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:20:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:21:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:21:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:21:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:21:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-02 16:21:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-05-02 16:22:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-05-02 16:59:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-02 18:52:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:09:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:11:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:12:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:13:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:13:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:13:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:14:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-02 19:14:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车辆保养记录');
INSERT INTO `log` VALUES ('2018-05-03 13:39:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 13:40:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 13:43:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 13:55:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 13:55:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 13:56:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:23:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:23:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:23:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:23:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:51:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:51:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:51:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:52:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:52:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:54:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:55:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:56:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:57:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:57:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 14:57:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 15:06:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:50:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:50:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:50:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:50:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:50:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:51:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:51:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:51:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:51:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:51:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:54:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:54:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-03 16:54:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的字典项');
INSERT INTO `log` VALUES ('2018-05-04 09:12:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:12:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:13:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:13:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:13:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:21:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 09:23:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除企业资料');
INSERT INTO `log` VALUES ('2018-05-04 09:23:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除角色资料');
INSERT INTO `log` VALUES ('2018-05-04 09:23:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除用户资料');
INSERT INTO `log` VALUES ('2018-05-04 09:24:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-04 09:25:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-05-04 09:25:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-05-04 09:26:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-05-04 09:26:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-04 09:26:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:39:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-04 10:40:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-04 10:41:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-04 10:41:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-04 10:45:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改车主资料');
INSERT INTO `log` VALUES ('2018-05-04 10:46:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:46:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:46:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:46:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:47:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:47:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:47:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:48:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:48:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:48:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:48:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:49:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:49:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:50:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-04 10:51:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 10:52:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 10:52:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 10:53:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 10:53:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-04 10:54:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '更新车队资料');
INSERT INTO `log` VALUES ('2018-05-04 11:00:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-05-04 16:41:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-05-04 16:41:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-05-07 17:13:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-07 17:15:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-07 17:15:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-07 17:15:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-07 17:16:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-05-07 17:17:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-07 19:32:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:32:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:34:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:37:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:47:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:57:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 19:58:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 23:04:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 23:04:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 23:05:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-07 23:06:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 08:13:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 08:20:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 08:38:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 08:48:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 09:25:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 10:05:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 10:19:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 10:44:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 11:20:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 11:26:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 14:19:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 14:54:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 14:56:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 14:56:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 14:58:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:02:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:04:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:05:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:05:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:06:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:06:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:22:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 15:26:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:13:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:18:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:21:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:24:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改角色资料');
INSERT INTO `log` VALUES ('2018-05-08 16:40:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:51:54', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 16:53:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 17:25:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 17:40:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 18:57:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:01:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:02:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:02:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:03:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:03:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:03:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:03:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:04:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 19:06:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 22:59:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 23:00:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 23:02:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 23:07:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 23:07:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-08 23:16:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 01:34:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:37:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:46:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:47:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:54:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:55:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 08:58:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 09:07:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 09:15:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 09:19:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 10:18:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 12:28:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 14:56:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 15:40:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 15:51:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 16:06:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 16:08:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 16:09:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 16:18:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 16:49:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 17:36:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 17:37:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 18:11:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 18:27:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 18:45:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-09 18:48:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 08:35:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 08:56:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 09:01:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 09:07:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 09:24:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 10:01:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-10 10:05:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 10:14:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 10:23:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-10 10:23:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-10 10:24:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除终端资料');
INSERT INTO `log` VALUES ('2018-05-10 11:01:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:14:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:16:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:17:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:21:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:42:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 14:53:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 15:46:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 17:15:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 17:22:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 17:28:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 17:34:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 17:54:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 19:15:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 19:18:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 19:33:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 19:35:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 19:35:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 19:35:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 19:35:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 19:36:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-10 20:02:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 23:44:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-10 23:45:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 01:50:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 08:52:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 09:06:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 09:06:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 09:06:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 09:08:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 14:10:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 14:47:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 16:54:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 16:58:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 16:58:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:58:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 16:59:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-11 17:11:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 17:12:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 17:29:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 17:47:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 18:03:47', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 19:35:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 19:46:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-11 23:38:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 08:25:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 09:49:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 09:49:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 09:50:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 09:51:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 10:03:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 10:07:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 10:25:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 10:45:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 11:31:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-12 11:37:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 13:06:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 14:54:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 17:40:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-12 20:11:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 12:14:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 22:52:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 23:13:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 23:14:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 23:16:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-13 23:56:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 01:25:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 08:49:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 08:51:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 09:18:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 09:36:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 09:36:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 09:41:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 10:17:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 10:29:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 10:40:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 11:19:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 11:52:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 11:56:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 11:56:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 12:15:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 12:20:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 13:50:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:06:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:23:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:26:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:27:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:56:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 14:56:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:03:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:03:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:19:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:21:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-14 15:21:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:21:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-14 15:22:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-14 15:24:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:25:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-14 15:25:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:29:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-14 15:31:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 15:39:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:16:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:47:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:47:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:49:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:50:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:51:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 16:52:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 17:06:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-14 17:36:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 18:19:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 18:20:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 19:05:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 19:17:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的sim卡');
INSERT INTO `log` VALUES ('2018-05-14 19:20:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的终端资料');
INSERT INTO `log` VALUES ('2018-05-14 19:21:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车辆');
INSERT INTO `log` VALUES ('2018-05-14 19:21:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 19:21:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 19:22:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 19:39:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:02:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:02:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:02:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:03:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:06:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:06:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:11:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:12:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:12:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:16:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-14 20:17:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 08:33:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 08:42:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 08:54:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:05:50', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:10:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:10:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:11:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:11:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:12:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:14:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:49:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:54:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:55:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 09:56:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 10:00:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 10:03:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 10:04:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 10:06:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 10:15:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 11:34:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 13:36:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 14:50:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 17:20:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 18:36:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 19:22:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-15 19:23:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 02:15:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:45:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:51:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:51:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:51:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:53:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 08:57:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:04:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:26:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:26:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:27:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:28:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:29:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 09:35:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 10:20:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 10:37:20', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 10:45:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-16 10:48:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 11:47:28', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 11:47:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-16 13:59:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 14:17:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 16:23:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 16:55:44', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-16 17:47:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 07:55:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 09:44:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 10:19:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 10:27:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 10:35:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 12:54:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 12:55:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 12:57:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:02:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:12:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:36:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:42:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:42:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:47:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 13:55:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 14:34:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 14:56:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 16:58:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 16:58:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-17 17:45:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 17:46:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-17 18:52:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 18:56:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 19:04:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 19:11:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-17 19:30:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 14:25:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 14:26:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 14:29:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 14:38:12', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 14:38:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 17:09:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 17:55:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 18:19:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-18 21:35:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-19 11:45:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-19 14:43:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-19 17:08:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-19 17:09:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-19 17:09:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-19 17:09:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-19 17:09:57', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-20 21:51:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 08:23:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 09:31:55', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 09:55:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 15:15:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-21 15:48:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 15:54:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-21 16:10:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 16:25:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 18:42:19', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-21 22:21:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-21 22:21:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-21 22:24:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-22 11:56:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-22 17:19:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-22 20:10:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-23 08:36:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-23 16:20:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-23 19:23:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:23:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:23:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:23:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:23:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:24:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:24:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:24:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 19:24:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-23 20:40:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 08:52:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 09:00:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:04:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:04:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:16:23', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:31:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:32:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:32:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:38:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:40:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:41:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:41:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 09:56:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 10:02:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 10:04:08', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 10:07:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 15:57:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 15:59:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:30:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:32:03', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:32:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:46:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:49:09', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:51:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 16:51:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:51:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 16:59:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:00:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:04:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:04:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:08:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:08:35', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:10:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:11:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:35:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 17:39:14', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 18:37:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '指令下发');
INSERT INTO `log` VALUES ('2018-05-24 19:12:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 19:14:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-24 19:14:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-24 19:16:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 19:18:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-24 19:21:39', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 19:29:02', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-05-24 19:29:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-05-24 19:29:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '车辆绑定驾驶员');
INSERT INTO `log` VALUES ('2018-05-24 21:04:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 22:16:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 23:13:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 23:16:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-24 23:22:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 09:10:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 09:35:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 10:29:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 11:58:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 11:59:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 12:01:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 12:12:21', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:36:36', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:40:56', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:48:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:49:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:51:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 13:53:04', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 15:49:31', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:08:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:10:41', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:20:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:20:59', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:21:05', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:21:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:22:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:23:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:24:46', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:31:18', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:31:51', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:36:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:39:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:39:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:40:13', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:40:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 16:40:43', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 17:03:26', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-25 17:07:40', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-28 10:33:16', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-28 10:45:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-28 10:48:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-28 11:12:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改终端资料');
INSERT INTO `log` VALUES ('2018-05-28 11:37:29', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 11:41:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 11:43:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 11:44:07', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 11:47:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 11:49:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改用户资料');
INSERT INTO `log` VALUES ('2018-05-28 12:03:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的用户');
INSERT INTO `log` VALUES ('2018-05-28 13:46:32', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-05-28 13:48:34', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-05-28 13:48:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-05-28 13:50:00', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-05-28 13:50:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的车队');
INSERT INTO `log` VALUES ('2018-05-28 13:50:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除车队资料');
INSERT INTO `log` VALUES ('2018-05-28 14:00:27', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-28 14:00:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改sim卡');
INSERT INTO `log` VALUES ('2018-05-28 16:05:48', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:06:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:08:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:22:11', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:23:15', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:24:22', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:25:25', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '创建新的驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:27:06', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-28 16:27:10', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '删除驾驶员');
INSERT INTO `log` VALUES ('2018-05-29 09:49:37', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '更新车队资料');
INSERT INTO `log` VALUES ('2018-05-29 10:39:38', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:41:49', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:45:17', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:49:53', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:51:58', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:55:30', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 10:59:33', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 11:03:45', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 11:03:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 11:05:01', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '修改企业资料');
INSERT INTO `log` VALUES ('2018-05-29 14:39:52', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '角色授权');
INSERT INTO `log` VALUES ('2018-05-30 15:05:42', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');
INSERT INTO `log` VALUES ('2018-05-30 16:50:24', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', 'rayton02', '打开统计分析页面');

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
INSERT INTO `maintain` VALUES ('5ab21cfee6c6171190178161', '558ffc6603c70e31a2a53a30', '5aaf6182e6c6170930f59511', '2018-03-06', '', '', 0, 0.00, '', '', 0, '558ffc6603c70e31a2a53a30', '系统管理员', '2018-03-21 16:51:10', '2018-03-21 16:51:10', NULL);
INSERT INTO `maintain` VALUES ('5ae99852af7752156418f356', '558ffc6603c70e31a2a53a30', '5ae834cfaf775215949b0cfc', '2018-05-01', '日常保养', '5', 2, 2.00, '2', '2', 2, '5a72c8733d769a75b6309ff0', 'rayton02', '2018-05-02 19:13:55', '2018-05-02 19:13:55', '2018-05-02');

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
INSERT INTO `map_layer` VALUES ('5aaf5e23e6c6170930f59413', '558ffc6603c70e31a2a53a30', '5a72c8733d769a75b6309ff0', '7777', b'1', '7777', '2018-04-18 15:29:14');

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
  `COMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车队' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of motorcade
-- ----------------------------
INSERT INTO `motorcade` VALUES ('5a72abd83d769a75b6309dee', '558ffc6603c70e31a2a53a30', '后勤运输队', '测试车队', b'1', 'www', 'ww', '', '2018-05-29 09:49:41', '2018-05-08 00:00:00', '', NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ae43dd7af775217a0b6fd2e', '5ae43d9baf775217a0b6fcf3', '巡逻队', '子公司车队', b'1', '子公司', '子公司', '子公司', '2018-04-28 17:25:02', NULL, NULL, NULL, NULL);
INSERT INTO `motorcade` VALUES ('5ae97524af7752140c24e62b', '558ffc6603c70e31a2a53a30', '巡逻队', '车队', b'1', 'ok', '72485857', 'ok', '2018-05-04 10:54:16', NULL, NULL, NULL, NULL);

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
  `SPEED` float unsigned NOT NULL COMMENT '速度',
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
INSERT INTO `permission` VALUES ('baseinfo.driver.addDriverLicence', '司机家驾驶证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.addVehicleLicence', '司机加行驶证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.addVehicleReg', '司机加登记证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.addVehicles', '驾驶员绑定车辆', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.create', '创建新的驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.delete', '删除驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.removeDriverLicence', '司机移除驾驶证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.removeVehicle', '驾驶员解除车辆', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.removeVehicleLicence', '司机移除行驶证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.removeVehicleReg', '司机移除登记证', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.driver.update', '修改驾驶员', 'baseinfo.driver');
INSERT INTO `permission` VALUES ('baseinfo.licence ', '证件管理', 'baseinfo');
INSERT INTO `permission` VALUES ('baseinfo.licence.drivingLicence', '驾驶证', 'baseinfo.licence ');
INSERT INTO `permission` VALUES ('baseinfo.licence.VehicleLicence', '行驶证', 'baseinfo.licence ');
INSERT INTO `permission` VALUES ('baseinfo.licence.VehicleRegister', '登记证', 'baseinfo.licence ');
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
INSERT INTO `permission` VALUES ('baseinfo.sysvid', '系统vid', 'baseinfo');
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '兴趣点' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多边形区域' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '矩形区域' ROW_FORMAT = Compact;

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
INSERT INTO `role` VALUES ('5ac482e67136fa35940d516d', '558ffc6603c70e31a2a53a30', '测试', '测试', '2018-04-04 15:46:22');
INSERT INTO `role` VALUES ('5ad6f141431ab70ec8e56b72', '558ffc6603c70e31a2a53a30', '所有', '', '2018-05-08 16:24:01');
INSERT INTO `role` VALUES ('5adf0eba431ab7133404e509', '5adf0d6f431ab7133404e508', '企业管理员', '', '2018-04-24 19:02:40');
INSERT INTO `role` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', '企业管理员', '', '2018-04-28 17:24:02');
INSERT INTO `role` VALUES ('5ae444f5af77521690951105', '5ae43d9baf775217a0b6fcf3', 'all', '', '2018-04-28 17:55:23');

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
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.alarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.accidentDoubtLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.deviceEventReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.drivingRecorder');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.locateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.loginLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.multimedia');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.multimediaEvent');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.multimediaRetrieval');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.parameterLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.powerLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.speedLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.speedStatusLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.deviceData.timeoutLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.locate');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.locate.datalog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.locate.instruct');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.locate.vehileinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ac482e67136fa35940d516d', 'center.replay');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.circleArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.company');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.company.authorize');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.company.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.company.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.company.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.device');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.device.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.device.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.device.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.dictionary');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.dictionary.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.dictionary.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.dictionary.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.addDriverLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.addVehicleLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.addVehicleReg');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.removeDriverLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.removeVehicleLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.removeVehicleReg');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.driver.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.licence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.licence.drivingLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.licence.VehicleLicence');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.licence.VehicleRegister');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.maintain');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.maintain.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.maintain.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.maintain.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.addPois');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.removePoi');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapLayer.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.mapOption');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.marker');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.marker.remove');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.marker.save');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.motorcade');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.motorcade.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.motorcade.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.motorcade.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.owner.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.poi');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.poi.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.poi.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.poi.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.polygonArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.rectangleArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.role');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.role.authorize');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.role.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.role.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.role.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.addSections');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.addVehicles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.removeSection');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.routeArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.sectionArea');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.sectionArea.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.sectionArea.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.sectionArea.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.simcard');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.simcard.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.simcard.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.simcard.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.sysvid');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.addMonitors');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.addRoles');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.removeMonitor');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.removeRole');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.user.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.addDrivers');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.addOwners');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.create');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.delete');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.removeDriver');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.removeOwner');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'baseinfo.vehicle.update');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.alarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.accidentDoubtLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.deviceEventReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.drivingRecorder');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.locateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.loginLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.multimedia');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.multimediaEvent');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.multimediaRetrieval');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.parameterLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.powerLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.speedLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.speedStatusLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.deviceData.timeoutLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.locate');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.locate.datalog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.locate.instruct');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.locate.vehileinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'center.replay');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'security.saveMyinfo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'security.saveMyKey');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.areaIo');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.areaOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.currentOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.historyOnlineOffline');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.historyOnlineTime');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.mileageOil');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.operateLog');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.routeDeviation');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.sectionOverspeed');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.timeoutParking');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.vehicleAlarm');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.vehicleFatigueDriving');
INSERT INTO `role_permission` VALUES ('558ffc6603c70e31a2a53a30', '5ad6f141431ab70ec8e56b72', 'statistics.vehicleOverspeed');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('5a72ab073d769a75b6309dec', '5a72ab073d769a75b6309dec', 'center.deviceData');
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
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.circleArea.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.company');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.company.authorize');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.company.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.company.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.company.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.device');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.device.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.device.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.device.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.dictionary.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.driver.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.licence');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.drivingLicence');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.VehicleLicence');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.licence.VehicleRegister');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.maintain.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addCircleAreas');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addPois');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addPolygonAreas');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addRectangleAreas');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.addRouteAreas');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeCircleArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removePoi');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removePolygonArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeRectangleArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.removeRouteArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapLayer.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.mapOption');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.marker');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.marker.remove');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.marker.save');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.motorcade.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.owner.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.poi');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.poi.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.polygonArea.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.rectangleArea.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.role');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.role.authorize');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.role.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.role.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.role.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.addSections');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.addVehicles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.removeSection');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.removeVehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.routeArea.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.sectionArea.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.simcard.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.addMonitors');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.addRoles');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.removeMonitor');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.removeRole');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.user.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.addDrivers');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.addOwners');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.create');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.delete');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.removeDriver');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.removeOwner');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'baseinfo.vehicle.update');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.alarm');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.alarm.processAll');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.alarm.processOnce');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.accidentDoubtLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.deviceEventReport');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.deviceUpgradeResultReport');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.drivingRecorder');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.locateLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.loginLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimedia');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimediaEvent');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.multimediaRetrieval');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.parameterLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.powerLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.speedLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.speedStatusLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.deviceData.timeoutLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.locate');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.locate.datalog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.locate.instruct');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.locate.vehileinfo');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'center.replay');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'security.saveMyinfo');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'security.saveMyKey');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.areaIo');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.areaOverspeed');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.currentOnlineOffline');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.historyOnlineOffline');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.historyOnlineTime');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.mileageOil');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.operateLog');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.routeDeviation');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.sectionOverspeed');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.timeoutParking');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.vehicleAlarm');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.vehicleFatigueDriving');
INSERT INTO `role_permission` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3', 'statistics.vehicleOverspeed');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线区域' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线拐点' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路段' ROW_FORMAT = Compact;

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
INSERT INTO `simcard` VALUES ('5a72ac0c3d769a75b6309def', '558ffc6603c70e31a2a53a30', '18011012699', '18011012699', '呼入呼出', b'0', '联通', 0.00, '联通', '2018-02-01', '联通', '2018-05-04 10:46:34', '2018-05-14', '2018-02-01', '2018-05-16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5a7417dd3d769a7dcc878d5b', '558ffc6603c70e31a2a53a30', '18011012700', '18011012700', '呼入呼出', b'0', '联通', 0.00, '联通', '2018-02-02', '联通', '2018-05-04 10:46:49', '2018-05-01', '2018-02-02', '2018-05-15', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ac43e5c7136fa10e89cea90', '558ffc6603c70e31a2a53a30', '120187322661', '120187322661', '呼入呼出', b'1', '联通', 0.00, '联通', '2018-04-04', '联通', '2018-05-04 10:46:22', '2018-05-08', '2018-05-31', '2018-05-15', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add90dd431ab70cd4cc6c90', '558ffc6603c70e31a2a53a30', '11111111111', '11111111', '呼入', b'1', '联通', 0.00, '333', '2018-04-23', '联通', '2018-05-28 14:00:49', '2018-04-12', '2018-04-10', '2018-04-18', NULL, '3', '2018-05-01 00:00:00', '2018-05-08 00:00:00', NULL);
INSERT INTO `simcard` VALUES ('5add90f3431ab70cd4cc6c91', '558ffc6603c70e31a2a53a30', '2222222', '222222222', '呼入', b'1', '联通', 0.00, '联通', '2018-04-23', '联通', '2018-05-04 10:47:18', '2018-04-26', '2018-04-04', '2018-04-24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add9106431ab70cd4cc6c92', '558ffc6603c70e31a2a53a30', '120190994308', '2333333', '呼入', b'1', '移动', 0.00, '23333333', '2018-04-23', '移动', '2018-05-07 17:17:35', '2018-04-19', '2018-04-03', '2018-04-17', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add911d431ab70cd4cc6c93', '558ffc6603c70e31a2a53a30', '23333', '22222222', '呼入', b'1', '联通', 0.00, '联通', '2018-04-23', '联通', '2018-05-04 10:47:30', '2018-04-26', '2018-04-11', '2018-04-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add9143431ab70cd4cc6c95', '558ffc6603c70e31a2a53a30', '8844848256', '88888852626', '呼入', b'1', '移动', -1.00, '移动', '2018-04-23', '移动', '2018-05-04 10:48:35', '2018-05-03', '2018-04-17', '2018-04-24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add9157431ab70cd4cc6c96', '558ffc6603c70e31a2a53a30', '999995985895', '5898256156', '呼入', b'0', '移动', 0.00, '移动', '2018-04-23', '入', '2018-05-04 10:49:13', '2018-05-03', '2018-04-02', '2018-04-10', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add9175431ab70cd4cc6c99', '558ffc6603c70e31a2a53a30', '4324324324', '324234324', '呼出', b'1', '移动', 0.00, '移动', '2018-04-23', '移动', '2018-05-04 10:48:52', '2018-05-22', '2018-05-04', '2018-05-04', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add917c431ab70cd4cc6c9a', '558ffc6603c70e31a2a53a30', '234234324', '234324324', '呼出', b'1', '移动', 0.00, '移动', '2018-04-23', '移动', '2018-05-04 10:48:18', '2018-05-15', '2018-05-15', '2018-05-15', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5add919e431ab70cd4cc6c9e', '558ffc6603c70e31a2a53a30', '5656151561', '8498265489156', '呼出', b'1', '电信', 0.00, '电信', '2018-04-23', '电信', '2018-05-04 10:49:49', '2018-05-23', '2018-05-09', '2018-06-07', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ae43dffaf775217a0b6fd2f', '5ae43d9baf775217a0b6fcf3', '1111', '111111', '呼入', b'1', '1', 11.00, '11111111', '2018-04-28', '11', '2018-04-28 17:25:42', '2018-04-03', '2018-04-03', '2020-04-10', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ae8337baf775215949b0cfa', '558ffc6603c70e31a2a53a30', '58925616', '7245752', '呼出', b'1', '电信', 121.00, '电信', '2018-05-01', '电信', '2018-05-04 10:50:06', '2018-05-16', '2018-05-09', '2018-05-23', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5ae972c0af7752140c24e5ef', '558ffc6603c70e31a2a53a30', '212121212', '88888888', '呼入', b'1', '联通', 1212.00, '联通', '2018-05-02', '联通', '2018-05-04 10:47:07', '2018-05-15', '2018-05-16', '2018-05-08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `simcard` VALUES ('5af970337b036831eeeb65ac', '558ffc6603c70e31a2a53a30', '868120191007647', '', '呼入呼出', b'0', '', 14.00, '', '2018-05-14', '', '2018-05-14 19:17:07', '2018-05-14', '2018-05-14', '2019-05-14', NULL, NULL, NULL, NULL, NULL);

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
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATEDATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `ID` int(99) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `DEVICEID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '终端id',
  `CARID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '车辆id',
  `DRIVERID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '司机',
  `SIMID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CARGROUPID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CARLICECEID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `CARREGID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `DRIVERLICENCEID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `COMPANYID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `USERID` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `COMID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sysvid
-- ----------------------------
INSERT INTO `sysvid` VALUES ('111', '2018-05-29 00:00:00', '2018-05-08 00:00:00', '2018-05-01 00:00:00', '1', '1', NULL, 2, '5aa7419b7b03680b92edf011,5a7417fc3d769a7dcc878d5c,5add941b431ab70cd4cc6cc7,5ae97456af7752140c24e5f0,5af970e27b036831eeeb65ca,5add91ca431ab70cd4cc6ca2,5add940a431ab70cd4cc6cc5,5add91e0431ab70cd4cc6ca4,5aebb3acaf77521014caab59', '5ae83510af775215949b0cfd,5af0196d7b036828a39464da,5ae9754aaf7752140c24e62c,5af971297b036831eeeb65cb,5aa741df7b03680b92edf012,5ae834cfaf775215949b0cfc,5a74182b3d769a7dcc878d5d', '5a7419353d769a7dcc878df5,5add9324431ab70cd4cc6cbd,5b0bbcf5ee8c3a15f021ffd1,5ae8332aaf775215949b0cf9,5ae83319af775215949b0cf8', '5add90dd431ab70cd4cc6c90,5ac43e5c7136fa10e89cea90,5add9106431ab70cd4cc6c92,5a72ac0c3d769a75b6309def,5a7417dd3d769a7dcc878d5b,5ae972c0af7752140c24e5ef,5add90f3431ab70cd4cc6c91,5add911d431ab70cd4cc6c93,5add917c431ab70cd4cc6c9a,5add9175431ab70cd4cc6c99,5add9157431ab70cd4cc6c96,5add9143431ab70cd4cc6c95,5af970337b036831eeeb65ac,5ae8337baf775215949b0cfa,5add919e431ab70cd4cc6c9e', '5a72abd83d769a75b6309dee,5ae97524af7752140c24e62b', '1', '1', '113,112', '5ae43d9baf775217a0b6fcf3,5a72ab073d769a75b6309dec', '5b0b7faeee8c3a1a2cd640db,5a72c8733d769a75b6309ff0', '558ffc6603c70e31a2a53a30');
INSERT INTO `sysvid` VALUES ('222', '2018-05-15 00:00:00', '2018-05-15 00:00:00', '2018-05-08 00:00:00', '2222', '22', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '558ffc6603c70e31a2a53a30');

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
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `EMAIL` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `CONTACT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `CREATETIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ENABLE` bit(1) NULL DEFAULT NULL COMMENT '启用',
  `EDITTIME` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '时间戳',
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
  `COMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UN_ACCOUNT`(`ACCOUNT`) USING BTREE,
  INDEX `IX_PID`(`PID`) USING BTREE,
  INDEX `COMPANYID`(`COMPANYID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('558ffc6603c70e31a2a53a30', 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '558ffc6603c70e31a2a53a30', 0, 'admin', 'a66abb5684c45962d887564f08346e8d', '系统管理员', 'yangxp999@163.com', '13714196239', 'lin', '2014-10-10 17:43:14', b'1', '2018-04-08 07:43:18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '8888', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72ab073d769a75b6309dec', '09790727-0714-11e8-89e3-00163e32414e', '5a72ab073d769a75b6309dec', 1, 'rayton', '888888', 'rayton', '333', '222', NULL, '2018-02-01 13:52:07', b'1', '2018-05-29 11:05:01', '558ffc6603c70e31a2a53a30', '2018-03-29', '2018-05-25', '888', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72ae2a3d769a75b6309e68', 'e858c6ae-0715-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 3, 'rayton01', '888888', 'rayton01', '', '', '', '2018-02-01 14:05:30', b'1', '2018-03-21 16:41:16', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a72c8733d769a75b6309ff0', '92ff3411-0725-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 2, 'rayton02', 'dc00255dabbf77e271bc30eb39b2a2f6', 'rayton02', '123@123.com', '120', 'ray', '2018-02-01 15:57:39', b'1', NULL, NULL, '2018-01-30', '2018-09-02', 'ok', NULL, NULL, NULL, NULL, 'eee', '222', '222', NULL, '2018-05-15 00:00:00', 'ss', NULL);
INSERT INTO `user` VALUES ('5a7417fc3d769a7dcc878d5c', '91bd9da3-07ed-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 4, '18011012700', '888888', 'T0001', '155', '999', '999', '2018-02-02 15:49:16', b'1', NULL, NULL, '2018-02-02', '2018-03-10', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5a7419293d769a7dcc878df4', '44ce10ae-07ee-11e8-89e3-00163e32414e', '558ffc6603c70e31a2a53a30', 3, 'spr', '888888', 'spr', '', '', '', '2018-02-02 15:54:17', b'1', '2018-03-21 16:41:19', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, '88', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aa7419b7b03680b92edf011', '5bc016f5-266c-11e8-b49f-00163e32131c', '558ffc6603c70e31a2a53a30', 4, '10189431635', '888888', '测试', '', '333', '333', '2018-03-13 11:12:27', b'1', NULL, NULL, '2018-05-29', '2020-05-24', '33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aaf61b2e6c6170930f59513', '30627e1f-2b44-11e8-bda6-72b63e1098cc', '558ffc6603c70e31a2a53a30', 3, 'eeeeeeeeee', '888888', 'eeeeeeeeee', '', 'rrrrrrrrrrr', 'rrrrrrrrrr', '2018-03-19 15:07:30', b'1', '2018-03-21 16:41:22', NULL, '2018-01-17', '2018-03-30', 'rrrrr', NULL, NULL, NULL, NULL, NULL, NULL, '888', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab21bf8e6c6171190178124', '64b78b6c-2ce4-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 3, 'fsf', '888888', 'fssfsf', '', '', '', '2018-03-21 16:46:48', b'0', '2018-03-21 16:46:48', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab22152e6c61711901781ae', '955ed2f5-2ce7-11e8-94dc-3df92df1332a', '558ffc6603c70e31a2a53a30', 3, 'dsfsdfdf', '888888', 'dsfdfd', '', 'sdfsfdsf', '', '2018-03-21 17:09:39', b'0', '2018-03-21 17:09:39', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ab457d8e6c617043ceb938b', '417e2251-2e39-11e8-8aa8-d2872c46dd83', '558ffc6603c70e31a2a53a30', 3, 'dsf', '888888', 'wewe', '', '', '', '2018-03-23 09:26:48', b'0', '2018-03-23 09:26:48', NULL, NULL, NULL, 'fsdf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5abda9fc05a3310dc43da973', '86dffd6f-33c7-11e8-ab0d-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '温热', '888888', 'wew', '', '', '', '2018-03-30 11:07:48', b'1', '2018-03-30 11:07:48', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add91ca431ab70cd4cc6ca2', 'f35c9ea9-46cb-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 4, '213123', '47789bf7745498bfac14152a642bf405', '21321321', '4324', '324234', '23423432', '2018-04-23 15:57:21', b'1', NULL, NULL, '2018-05-25', '2018-05-30', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add91e0431ab70cd4cc6ca4', '002eb07a-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 4, '2132133123', 'fb38cb9098aa8e0a54cb15d0074521aa', '3213', '32432', '234234', '23432', '2018-04-23 15:57:42', b'1', NULL, NULL, '2018-04-04', '2018-07-26', '234324', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add924c431ab70cd4cc6ca7', '40ecb32d-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, 'we', '069905a7c53bf44e9492e8e08fe6db26', 'we', 'we@123.com', '120', 'ray', '2018-04-23 15:59:31', b'1', NULL, NULL, '2018-05-01', '2018-05-31', 'ok', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9254431ab70cd4cc6ca8', '458abc2b-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '123213', 'c25526ee25d9b9d071104e741b548326', '12323231', '', '', '', '2018-04-23 15:59:38', b'0', '2018-04-23 15:59:38', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add925b431ab70cd4cc6ca9', '49aad131-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '213213123', '13dae1a760a9ff1c20cab16f0be90a61', '123213213', '', '', '', '2018-04-23 15:59:45', b'0', '2018-04-23 15:59:45', NULL, NULL, NULL, '123213123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9263431ab70cd4cc6caa', '4e26bfdf-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '21312312', 'f1b96c02efc933c2a97634b2e7fab7e3', '21312313', '', '', '', '2018-04-23 15:59:53', b'0', '2018-04-23 15:59:53', NULL, NULL, NULL, '123123213', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9269431ab70cd4cc6cab', '51e35827-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '3213213', '38dbb3ff4836b0a046809736f2b9fc20', '213213', '', '', '', '2018-04-23 15:59:59', b'0', '2018-04-23 15:59:59', NULL, NULL, NULL, '3213213', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9277431ab70cd4cc6cac', '5a85210d-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '213123123213', '78e6e0f25ff49334ece5cd42b42d796d', '123123123', '', '', '', '2018-04-23 16:00:14', b'0', '2018-04-23 16:00:14', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add927d431ab70cd4cc6cad', '5e03574c-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '321321', '4fc4639a3e6e0a07f1307b80015d3ddf', '321321312', '', '', '', '2018-04-23 16:00:19', b'0', '2018-04-23 16:00:19', NULL, NULL, NULL, '312312', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9286431ab70cd4cc6cae', '631b2a85-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '321312', '32518e0037b74546da18f70ace15713f', '3213123123', '', '', '', '2018-04-23 16:00:28', b'0', '2018-04-23 16:00:28', NULL, NULL, NULL, '2131', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add928c431ab70cd4cc6caf', '669d156f-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '4324324', '3a5235d50a0b24e633d30f5fe3676061', '32423', '', '', '', '2018-04-23 16:00:34', b'0', '2018-04-23 16:00:34', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9292431ab70cd4cc6cb0', '6a6adb38-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '23432432', '57ecdfbfc6c5e69ad8a1c007eba6339f', '432432423', '', '', '', '2018-04-23 16:00:40', b'0', '2018-04-23 16:00:40', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add9299431ab70cd4cc6cb1', '6e65ae08-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '324234234', 'f157a132c00deadbb33c7f844efe701a', '2343424', '', '', '', '2018-04-23 16:00:47', b'0', '2018-04-23 16:00:47', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add92a0431ab70cd4cc6cb2', '72e95dff-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '2343243234234', '58b3a9413da9590fcdc841ddc0181c5f', '32444442', '', '', '', '2018-04-23 16:00:54', b'0', '2018-04-23 16:00:54', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add92a7431ab70cd4cc6cb3', '77271a60-46cc-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '4234324', '46cc1cd5c231ee0099de3a1a10644e55', '342234324234', '', '', '', '2018-04-23 16:01:02', b'0', '2018-04-23 16:01:02', NULL, NULL, NULL, '324324', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add940a431ab70cd4cc6cc5', '4a901eb0-46cd-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 4, '4234234', '1b42e3c1b3f20fab2efc03931f36ac02', '34234234', '2343', '23432', '242', '2018-04-23 16:06:56', b'1', NULL, NULL, '2018-05-17', '2018-05-22', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5add941b431ab70cd4cc6cc7', '54b558cb-46cd-11e8-af06-000c29be51fb', '558ffc6603c70e31a2a53a30', 4, '2342344324', '9f4c538358f1ef24c1ca77d0b5bfa2e2', '324234', '', '32423423', '32432', '2018-04-23 16:07:13', b'1', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae1464e0d8a3b17ace0252e', '4f2d1c01-4901-11e8-97df-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, 'ewrwer', '83897bab777f4e7e35b49d940dd8c9d9', 'wer', 'wer', 'ewr', 'wer', '2018-04-26 11:24:20', b'1', '2018-04-26 11:24:20', '', '2018-04-16', '2018-04-16', 'ewr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae146f10d8a3b147c3ce091', 'b068eaeb-4901-11e8-97df-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, 'ewr', '8f59436adc170d88c51b5e30ac2de6ae', 'ewr', 'wew', 'ewr', 'werwe', '2018-04-26 11:27:03', b'1', NULL, '', '2018-04-24', '2018-04-03', 'ewrew', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae1491b0d8a3b147c3ce10f', 'facb9b12-4902-11e8-97df-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, '12', 'abe29e179e816cf8c676c6baf84e9178', '21', '12', '12', '21', '2018-04-26 11:36:18', b'1', '2018-04-26 11:36:18', '', '2018-04-24', '2018-04-16', '巍峨', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae14d3d0d8a3b0408e6759a', '70f1a4c6-4905-11e8-97df-000c29be51fb', '558ffc6603c70e31a2a53a30', 3, 'ok', '7a7b2c4104bb1a18dd5afedc9b3f084f', 'ok', 'ok@134.com', '112', 'ok', '2018-04-26 11:53:55', b'1', NULL, '', '2018-05-01', '2019-04-30', 'ok', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae43d9baf775217a0b6fcf3', 'e391c640-4ac5-11e8-97df-000c29be51fb', '5ae43d9baf775217a0b6fcf3', 1, '999', '9ccc1e65c2e317bbf9fd0e31059ca207', '999', '子公司', '子公司', NULL, '2018-04-28 17:24:02', b'1', '2018-04-28 17:24:02', '558ffc6603c70e31a2a53a30', '2018-04-26', '2020-04-16', '子公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae43e2caf775217a0b6fd30', '39982be8-4ac6-11e8-97df-000c29be51fb', '5ae43d9baf775217a0b6fcf3', 4, '999999999999', '45f8a28b3d9d98d10e49a7e648d627a0', '121323123', '312312', '123123', '3213', '2018-04-28 17:26:26', b'1', '2018-04-28 17:26:26', '', '2018-04-25', '2021-04-08', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae44414af775203b8502abd', 'bed1c9b5-4ac9-11e8-97df-000c29be51fb', '5ae43d9baf775217a0b6fcf3', 2, 'zzh', '29e94a5390fe1648199bb4d0e2ef44a6', '渣渣辉', '渣渣辉', '渣渣辉', '渣渣辉', '2018-04-28 17:51:38', b'1', NULL, NULL, '2018-04-19', '2021-04-15', '渣渣辉', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae96c9eaf775213e02d87d6', 'cc1ddb3c-4ddc-11e8-92b1-54ee75765425', '558ffc6603c70e31a2a53a30', 3, '1231232131', '841e3c4dc02c0b17c845e50198fae858', '213', '052@238.com', '213123', 'ok', '2018-05-02 15:45:34', b'0', NULL, '', '2018-05-02', '2018-06-05', 'ok', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5ae97456af7752140c24e5f0', '660380a6-4de1-11e8-92b1-54ee75765425', '558ffc6603c70e31a2a53a30', 4, '48748994', 'a8a93fffd8286445133bc2dec8cfc531', '464151', '42524', '57885', '475522', '2018-05-02 16:18:30', b'1', NULL, '', '2018-05-24', '2018-05-15', '等等', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5aebb3acaf77521014caab59', '52d1c115-4f38-11e8-bfd9-54ee75765425', '558ffc6603c70e31a2a53a30', 4, '222222', '726248fed7074674851a2957c38eaa0d', '2222', '2222', '22', '222', '2018-05-04 09:13:16', b'0', NULL, '', '2018-05-31', '2018-05-21', '222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5af970e27b036831eeeb65ca', 'be9a3654-5768-11e8-8429-00163e32131c', '558ffc6603c70e31a2a53a30', 4, '868120191007647', 'f910d24d6008849f99102df09e1223ac', '868120191007647', '', '868120191007647', '868120191007647', '2018-05-14 19:20:02', b'1', NULL, '', '2018-05-14', '2019-05-14', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('5b0b7faeee8c3a1a2cd640db', '283fe035-622c-11e8-92a2-000c29be51fb', '558ffc6603c70e31a2a53a30', 2, 'w', 'c0a8d584709704ea4da1b201a95d3654', 'w', 'w', 'w', 'w', '2018-05-28 12:04:02', b'1', '2018-05-28 12:04:02', NULL, '2018-05-23', '2018-05-13', 'w', NULL, NULL, NULL, NULL, 'w', 'w', 'w', NULL, '2018-05-02 00:00:00', 'w', '深圳锐讯易通科技有限公司');

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
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5aaf618ee6c6170930f59512');
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5ab21cb5e6c617119017815e');
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5ab22346e6c61711901781c9');
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5ae43d9baf775217a0b6fcf3');
INSERT INTO `user_monitor` VALUES ('5a72c8733d769a75b6309ff0', '5ae8324aaf775215949b0cf5');
INSERT INTO `user_monitor` VALUES ('5a72eb803d769a3c631b8b5a', '5a72abd83d769a75b6309dee');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72ab073d769a75b6309dec');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5a72abd83d769a75b6309dee');
INSERT INTO `user_monitor` VALUES ('5aaf61d7e6c6170930f59515', '5aaf618ee6c6170930f59512');
INSERT INTO `user_monitor` VALUES ('5ab2206be6c61711901781ad', '5a72ab073d769a75b6309dec');
INSERT INTO `user_monitor` VALUES ('5ab2206be6c61711901781ad', '5abc88ed05a3310acc2444dd');
INSERT INTO `user_monitor` VALUES ('5ae44414af775203b8502abd', '5ae43dd7af775217a0b6fd2e');

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
INSERT INTO `user_role` VALUES ('5a72eb803d769a3c631b8b5a', '5a72c8883d769a75b6309ff1');
INSERT INTO `user_role` VALUES ('5ab2206be6c61711901781ad', '5ab21c08e6c617119017813e');
INSERT INTO `user_role` VALUES ('5a72c8733d769a75b6309ff0', '5ac482e67136fa35940d516d');
INSERT INTO `user_role` VALUES ('5a72c8733d769a75b6309ff0', '5ad6f141431ab70ec8e56b72');
INSERT INTO `user_role` VALUES ('5adf0d6f431ab7133404e508', '5adf0eba431ab7133404e509');
INSERT INTO `user_role` VALUES ('5ae43d9baf775217a0b6fcf3', '5ae43d9baf775217a0b6fcf3');
INSERT INTO `user_role` VALUES ('5ae44414af775203b8502abd', '5ae444f5af77521690951105');
INSERT INTO `user_role` VALUES ('5a72c8733d769a75b6309ff0', '5ae83251af775215949b0cf6');

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
INSERT INTO `user_setting` VALUES ('5a72c8733d769a75b6309ff0', '2', '{\"show\":\"inline-block\"}');

-- ----------------------------
-- Table structure for vehicle_license
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_license`;
CREATE TABLE `vehicle_license`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_no` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `vehicle_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有人',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `use_character` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用性质',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌型号',
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆识别代号',
  `engine_no` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发动机号码',
  `register_date` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `issue_date` datetime(0) NULL DEFAULT NULL COMMENT '发证日期',
  `document_no` varchar(88) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案编号',
  `approved_passengers_capacity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核定载人数',
  `total_mass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总质量',
  `curb_weight` varchar(99) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '整备质量',
  `approved_load` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核定载质量',
  `overall_size` varchar(88) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外廓尺寸',
  `traction_mass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准牵引总质量',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `inspection_record` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验记录',
  `company_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_license
-- ----------------------------
INSERT INTO `vehicle_license` VALUES (1, '999', '大车', '我', '秘密', '无', '999', '无', '999', '2018-04-03 00:00:00', '2018-04-09 00:00:00', '无', '99', '999', '无', '99', '99', '99', '无', '无', '558ffc6603c70e31a2a53a30');

-- ----------------------------
-- Table structure for vehicle_register
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_register`;
CREATE TABLE `vehicle_register`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `owner_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有人',
  `register_office` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记机关',
  `register_date` datetime(0) NULL DEFAULT NULL COMMENT '登记日期',
  `register_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记编号',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆类型',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆品牌',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆型号',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `identify_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '识别代号',
  `import_or_not` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否国产',
  `engine_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发动机号',
  `engine_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发动机型号',
  `fuel_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '燃料种类',
  `output_volume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排量',
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造厂',
  `steering_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转向形式',
  `track` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮距',
  `number_of_wheel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮胎数',
  `tyre_size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮胎规格',
  `number_of_spring_leaf` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钢板弹簧片数',
  `wheelbase` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轴距',
  `number_of_axles` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轴数',
  `all_size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外廓尺寸',
  `internal_size` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货箱内部尺寸',
  `gross_mass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总质量',
  `rated_loading_weight` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核定载质量',
  `rated_passenger_capacity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核定载客',
  `total_traction_weight` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准牵引总质量',
  `passenger_capacity_of_cab` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驾驶室载客',
  `purpose_of_use` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用性质',
  `means_of_acquisition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获得方式',
  `ex_factory_date` datetime(0) NULL DEFAULT NULL COMMENT '出厂日期',
  `company_id` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_register
-- ----------------------------
INSERT INTO `vehicle_register` VALUES (1, '12312', '3123', '2018-05-09 00:00:00', '1232321', '313', '你猜', '312', '白', '3213', 'false', '123', '3123', '123', '3', '3123', '你猜', '213', '12', '123', '12321312', '123', '312', '123', '123', '23', '12312', '123', '12312', '123', '你猜', '你猜', NULL, '558ffc6603c70e31a2a53a30');

-- ----------------------------
-- Table structure for viewport
-- ----------------------------
DROP TABLE IF EXISTS `viewport`;
CREATE TABLE `viewport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lng` decimal(65, 30) NULL DEFAULT NULL,
  `alt` decimal(65, 30) NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of viewport
-- ----------------------------
INSERT INTO `viewport` VALUES (1, 104.000000000000000000000000000000, 40.000000000000000000000000000000, '5', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (2, 111.000000000000000000000000000000, 39.000000000000000000000000000000, '7', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (3, 113.000000000000000000000000000000, 38.000000000000000000000000000000, '12', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (4, 113.000000000000000000000000000000, 38.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (5, 113.000000000000000000000000000000, 38.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (6, 113.000000000000000000000000000000, 38.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (7, 113.000000000000000000000000000000, 38.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (8, 113.000000000000000000000000000000, 23.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (9, 113.000000000000000000000000000000, 23.000000000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (10, 105.000000000000000000000000000000, 39.000000000000000000000000000000, '5', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (11, 112.572787000000000000000000000000, 37.917322000000000000000000000000, '11', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (12, 113.453211291250000000000000000000, 23.176126226567000000000000000000, '12', '558ffc6603c70e31a2a53a30');
INSERT INTO `viewport` VALUES (13, 113.446216000000000000000000000000, 23.185115000000000000000000000000, '14', '558ffc6603c70e31a2a53a30');

SET FOREIGN_KEY_CHECKS = 1;
