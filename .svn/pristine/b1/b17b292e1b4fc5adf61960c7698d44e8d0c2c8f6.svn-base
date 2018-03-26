/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : monitordb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 21/03/2018 16:33:00
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '圆形区域' ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司资料' ROW_FORMAT = Dynamic;

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
  `SPEED` float unsigned NOT NULL COMMENT '速度',
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
-- Table structure for routesection
-- ----------------------------
DROP TABLE IF EXISTS `routesection`;
CREATE TABLE `routesection`  (
  `ROUTEID` int(11) UNSIGNED NOT NULL COMMENT '路线唯一编号',
  `SECTIONID` int(11) UNSIGNED NOT NULL COMMENT '路段唯一编号',
  PRIMARY KEY (`ROUTEID`, `SECTIONID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '路线路段表' ROW_FORMAT = Dynamic;

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
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'MMP',
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
-- Table structure for usermonitor
-- ----------------------------
DROP TABLE IF EXISTS `usermonitor`;
CREATE TABLE `usermonitor`  (
  `USERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一编号',
  `TARGETID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标唯一编号',
  PRIMARY KEY (`USERID`, `TARGETID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户监控范围' ROW_FORMAT = Dynamic;

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
-- Table structure for vehicleindriver
-- ----------------------------
DROP TABLE IF EXISTS `vehicleindriver`;
CREATE TABLE `vehicleindriver`  (
  `DRIVERID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机唯一编号',
  `VEHICLEID` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`DRIVERID`, `VEHICLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆与司机关系' ROW_FORMAT = Dynamic;

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

SET FOREIGN_KEY_CHECKS = 1;
