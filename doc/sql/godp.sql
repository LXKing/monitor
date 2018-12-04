/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : godp

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-04 15:55:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm
-- ----------------------------
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
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
  KEY `IX_DEVICEID_GPSTIME` (`NUMBER`,`GPSTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='报警表';

-- ----------------------------
-- Records of alarm
-- ----------------------------

-- ----------------------------
-- Table structure for authorize
-- ----------------------------
DROP TABLE IF EXISTS `authorize`;
CREATE TABLE `authorize` (
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  `PERMID` char(24) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`ROLEID`,`PERMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权表';

-- ----------------------------
-- Records of authorize
-- ----------------------------
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f89980951362270fc954d');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8acc0951362270fc954e');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ad10951362270fc954f');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ad30951362270fc9550');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ad50951362270fc9551');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ad80951362270fc9552');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8adf0951362270fc9553');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ae10951362270fc9554');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8ae90951362270fc9555');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8aeb0951362270fc9556');
INSERT INTO `authorize` VALUES ('563c4b783dd6ef1eb5e83e10', '5a8f8aed0951362270fc9557');

-- ----------------------------
-- Table structure for datalog
-- ----------------------------
DROP TABLE IF EXISTS `datalog`;
CREATE TABLE `datalog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `RAW` text NOT NULL COMMENT '原始数据',
  KEY `IX_DEVICEID_TIME` (`NUMBER`,`TIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='数据日志表';

-- ----------------------------
-- Records of datalog
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `CREATETIME` datetime NOT NULL COMMENT '注册时间',
  `PROTOCOLKIND` int(11) NOT NULL COMMENT '协议类型',
  `DEBUGGING` bit(1) NOT NULL DEFAULT b'0' COMMENT '调试中',
  `UPGRADING` bit(1) NOT NULL DEFAULT b'0' COMMENT '升级中',
  `MATCHING` bit(1) NOT NULL DEFAULT b'0' COMMENT '全车匹配中',
  `SLEEPING` bit(1) NOT NULL DEFAULT b'0' COMMENT '休眠中',
  `REPAIRING` bit(1) NOT NULL DEFAULT b'0' COMMENT '维修中',
  `PREVER` varchar(10) DEFAULT NULL COMMENT '前一版本',
  `UPGRADESTART` datetime DEFAULT NULL COMMENT '升级开始时间',
  `CURVER` varchar(10) DEFAULT NULL COMMENT '当前版本',
  `UPGRADEEND` datetime DEFAULT NULL COMMENT '升级完成时间',
  `MATCHRESULT` text COMMENT '全车匹配结果',
  `MATCHTIME` datetime DEFAULT NULL COMMENT '匹配完成时间',
  `PROVINCEID` smallint(5) unsigned DEFAULT NULL COMMENT '省域id',
  `CITYID` smallint(5) unsigned DEFAULT NULL COMMENT '县域id',
  `FACTORYID` varchar(10) DEFAULT NULL COMMENT '制造商id',
  `MODEL` varchar(20) DEFAULT NULL COMMENT '型号',
  `DEVICEID` varchar(10) DEFAULT NULL COMMENT '设备id',
  `VEHICLEPLATECOLOR` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌颜色',
  `VEHICLEID` varchar(20) DEFAULT NULL COMMENT '车辆标识',
  PRIMARY KEY (`NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for deviceinuser
-- ----------------------------
DROP TABLE IF EXISTS `deviceinuser`;
CREATE TABLE `deviceinuser` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `USERID` char(24) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`NUMBER`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备表';

-- ----------------------------
-- Records of deviceinuser
-- ----------------------------

-- ----------------------------
-- Table structure for draccidentlog
-- ----------------------------
DROP TABLE IF EXISTS `draccidentlog`;
CREATE TABLE `draccidentlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `LICENSENUMBER` varchar(20) NOT NULL COMMENT '驾驶证号',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `ALT` smallint(6) unsigned NOT NULL COMMENT '海拔',
  `CONTENT` binary(200) NOT NULL COMMENT '每隔0.2S的速度与状态',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪事故疑点日志';

-- ----------------------------
-- Records of draccidentlog
-- ----------------------------

-- ----------------------------
-- Table structure for drivingrecorder
-- ----------------------------
DROP TABLE IF EXISTS `drivingrecorder`;
CREATE TABLE `drivingrecorder` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `REVISION` varchar(10) DEFAULT NULL COMMENT '版本号',
  `LICENSE` varchar(18) DEFAULT NULL COMMENT '驾证号',
  `INITIALMILEAGE` double DEFAULT NULL COMMENT '初始里程',
  `TOTALMILEAGE` double DEFAULT NULL COMMENT '累计里程',
  `PULSEFACTOR` smallint(5) unsigned DEFAULT NULL COMMENT '脉冲系数',
  `VEHICLEIDCODE` varchar(20) DEFAULT NULL COMMENT '车辆识别代号',
  `PLATENUMBER` varchar(15) DEFAULT NULL COMMENT '车牌号',
  `PLATETYPE` varchar(15) DEFAULT NULL COMMENT '车牌类别',
  `CCCCODE` varchar(10) DEFAULT NULL COMMENT 'ccc认证号',
  `MODEL` varchar(20) DEFAULT NULL COMMENT '型号',
  `PRODUCTIONDATE` varchar(15) DEFAULT NULL COMMENT '生产日期',
  `SERIALNUMBER` int(10) unsigned DEFAULT NULL COMMENT '序列号',
  `D0` varchar(10) DEFAULT NULL COMMENT 'D0名称',
  `D1` varchar(10) DEFAULT NULL COMMENT 'D1名称',
  `D2` varchar(10) DEFAULT NULL COMMENT 'D2名称',
  `D3` varchar(10) DEFAULT NULL COMMENT 'D3名称',
  `D4` varchar(10) DEFAULT NULL COMMENT 'D4名称',
  `D5` varchar(10) DEFAULT NULL COMMENT 'D5名称',
  `D6` varchar(10) DEFAULT NULL COMMENT 'D6名称',
  `D7` varchar(10) DEFAULT NULL COMMENT 'D7名称',
  PRIMARY KEY (`NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行驶记录仪';

-- ----------------------------
-- Records of drivingrecorder
-- ----------------------------

-- ----------------------------
-- Table structure for drlocatelog
-- ----------------------------
DROP TABLE IF EXISTS `drlocatelog`;
CREATE TABLE `drlocatelog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `ALT` smallint(5) unsigned NOT NULL COMMENT '海拔',
  `SPEED` tinyint(3) unsigned NOT NULL COMMENT '速度',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪位置记录';

-- ----------------------------
-- Records of drlocatelog
-- ----------------------------

-- ----------------------------
-- Table structure for drloginlog
-- ----------------------------
DROP TABLE IF EXISTS `drloginlog`;
CREATE TABLE `drloginlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `LICENSENUMBER` varchar(20) NOT NULL COMMENT '驾驶证号',
  `EVENT` tinyint(4) NOT NULL COMMENT '事件(1登录，2退出)',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='驶驶员登签日志';

-- ----------------------------
-- Records of drloginlog
-- ----------------------------

-- ----------------------------
-- Table structure for drparameterlog
-- ----------------------------
DROP TABLE IF EXISTS `drparameterlog`;
CREATE TABLE `drparameterlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `EVENT` tinyint(4) unsigned NOT NULL COMMENT '事件(命令字)',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪参数修改日志';

-- ----------------------------
-- Records of drparameterlog
-- ----------------------------

-- ----------------------------
-- Table structure for drpowerlog
-- ----------------------------
DROP TABLE IF EXISTS `drpowerlog`;
CREATE TABLE `drpowerlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `EVENT` tinyint(4) NOT NULL COMMENT '事件(1通电，2断电)',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪供电日志';

-- ----------------------------
-- Records of drpowerlog
-- ----------------------------

-- ----------------------------
-- Table structure for drspeedlog
-- ----------------------------
DROP TABLE IF EXISTS `drspeedlog`;
CREATE TABLE `drspeedlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `CONTENT` binary(120) NOT NULL COMMENT '每分钟60秒的速度与状态值',
  PRIMARY KEY (`NUMBER`,`TIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶速度记录';

-- ----------------------------
-- Records of drspeedlog
-- ----------------------------

-- ----------------------------
-- Table structure for drspeedstatuslog
-- ----------------------------
DROP TABLE IF EXISTS `drspeedstatuslog`;
CREATE TABLE `drspeedstatuslog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `STARTTIME` datetime NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL COMMENT '结束时间',
  `STATE` tinyint(4) NOT NULL COMMENT '状态:01H 表示正常,02H 表示异常',
  `CONTENT` varbinary(120) NOT NULL COMMENT '记录速度, 参考速度',
  PRIMARY KEY (`NUMBER`,`STARTTIME`,`ENDTIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪速度状态日志';

-- ----------------------------
-- Records of drspeedstatuslog
-- ----------------------------

-- ----------------------------
-- Table structure for drtimeoutlog
-- ----------------------------
DROP TABLE IF EXISTS `drtimeoutlog`;
CREATE TABLE `drtimeoutlog` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `STARTTIME` datetime NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL COMMENT '结束时间',
  `LICENSENUMBER` varchar(20) NOT NULL COMMENT '加驶证',
  `STARTLNG` decimal(9,6) NOT NULL COMMENT '开始经度',
  `STARTLAT` decimal(9,6) NOT NULL COMMENT '开始纬度',
  `STARTALT` smallint(5) unsigned NOT NULL COMMENT '开始海拔',
  `ENDLNG` decimal(9,6) NOT NULL COMMENT '结束经度',
  `ENDLAT` decimal(9,6) NOT NULL COMMENT '结束纬度',
  `ENDALT` smallint(5) unsigned NOT NULL COMMENT '结束海拔',
  PRIMARY KEY (`NUMBER`,`STARTTIME`,`ENDTIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行驶记录仪超时驾驶日志';

-- ----------------------------
-- Records of drtimeoutlog
-- ----------------------------

-- ----------------------------
-- Table structure for fault
-- ----------------------------
DROP TABLE IF EXISTS `fault`;
CREATE TABLE `fault` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `GPSTIME` datetime NOT NULL COMMENT '时间',
  `SYSID` tinyint(4) unsigned NOT NULL COMMENT '系统ID',
  `MODEID` tinyint(4) unsigned NOT NULL COMMENT '故障码模式',
  `CODE1` tinyint(4) unsigned NOT NULL COMMENT '故障字节1',
  `CODE2` tinyint(4) unsigned NOT NULL COMMENT '故障字节2',
  `CODE3` tinyint(4) unsigned NOT NULL COMMENT '故障字节3',
  KEY `IX_DEVICEID_GPSTIME` (`NUMBER`,`GPSTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='故障表';

-- ----------------------------
-- Records of fault
-- ----------------------------

-- ----------------------------
-- Table structure for faultcode
-- ----------------------------
DROP TABLE IF EXISTS `faultcode`;
CREATE TABLE `faultcode` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `LEVEL` tinyint(3) unsigned NOT NULL COMMENT '等级',
  `MODEID` tinyint(3) unsigned NOT NULL COMMENT '模式ID',
  `CODE1` tinyint(3) unsigned NOT NULL COMMENT '第一个字节',
  `CODE2` tinyint(3) unsigned NOT NULL COMMENT '第二个字节',
  `CODE3` tinyint(3) unsigned NOT NULL COMMENT '第三个字节',
  `PCODE` varchar(5) NOT NULL COMMENT '编号',
  `BRAND` varchar(50) NOT NULL,
  `CONTENTC` varchar(200) NOT NULL COMMENT '内容',
  `CONTENTE` varchar(200) NOT NULL,
  `SOLUTION` varchar(300) NOT NULL COMMENT '解决方案',
  `CLEAR` tinyint(3) NOT NULL COMMENT '清除条件',
  `NOTIFYOWNER` bit(1) NOT NULL COMMENT '通知车主否',
  `NOTIFYADVISOR` bit(1) NOT NULL COMMENT '通知技师否',
  `CATEGORYID` tinyint(3) NOT NULL COMMENT '分类ID',
  `SENSORS` varchar(50) NOT NULL,
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `kind_code1_code2_code3` (`MODEID`,`CODE1`,`CODE2`,`CODE3`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='故障码表';

-- ----------------------------
-- Records of faultcode
-- ----------------------------

-- ----------------------------
-- Table structure for feature
-- ----------------------------
DROP TABLE IF EXISTS `feature`;
CREATE TABLE `feature` (
  `ID` char(24) NOT NULL COMMENT '记录ID',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  `KIND` int(11) NOT NULL COMMENT '协议类型',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `COMMAND` varchar(50) NOT NULL COMMENT '命令',
  `PARAMS` int(11) NOT NULL COMMENT '参数个数',
  `ENABLED` bit(1) NOT NULL COMMENT '启用否',
  `TWICECONFIRM` bit(1) NOT NULL COMMENT '两次确认否',
  `PASSWORDCONFIRM` bit(1) NOT NULL COMMENT '密码确认否',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`),
  KEY `IX_KIND` (`KIND`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能表';

-- ----------------------------
-- Records of feature
-- ----------------------------
INSERT INTO `feature` VALUES ('5589ec3303c70e2678e39f3c', '1', '1', '位置信息查询', '8201', '0', '', '\0', '\0', '2015-06-24 07:30:59', '点名，设备收后将立即上传当前位置数据');
INSERT INTO `feature` VALUES ('5589ec6303c70e2678e39f3d', '2', '1', '摄像头立即拍摄', '8801', '10', '', '\0', '\0', '2015-11-13 16:19:17', '摄像头立即拍摄');
INSERT INTO `feature` VALUES ('558ff76103c70e316a452c5b', '8', '1', '事件设置', '8301', '2', '', '\0', '\0', '2015-06-28 21:38:36', '设置终端向服务器可发送的事件');
INSERT INTO `feature` VALUES ('5591fa9c03c70e360cb98cec', '3', '1', '设置终端参数', '8103', '1', '', '\0', '\0', '2015-06-30 10:11:06', '设置终端的各项运行时参数');
INSERT INTO `feature` VALUES ('5592918c03c70e36b152cf48', '4', '1', '查询所有参数', '8104', '0', '', '\0', '\0', '2015-06-30 20:56:07', '查询终端指定的参数信息');
INSERT INTO `feature` VALUES ('559291b903c70e36b152cf49', '5', '1', '查询指定参数', '8106', '1', '', '\0', '\0', '2015-06-30 20:56:46', '查询终端指定的参数信息');
INSERT INTO `feature` VALUES ('55929ee603c70e36b152cf99', '6', '1', '终端控制', '8105', '1', '', '\0', '\0', '2015-07-03 10:29:03', '');
INSERT INTO `feature` VALUES ('5592a46b03c70e36b152cfb2', '7', '1', '查询终端属性', '8107', '0', '', '\0', '\0', '2015-06-30 22:15:07', '');
INSERT INTO `feature` VALUES ('559483431d19b602cb089d25', '9', '1', '位置跟踪', '8202', '2', '', '\0', '\0', '2015-07-02 08:19:22', '临时位置跟踪控制');
INSERT INTO `feature` VALUES ('559484281d19b602cb089d28', '10', '1', '人工确认报警', '8203', '2', '\0', '\0', '\0', '2015-10-30 10:24:39', '');
INSERT INTO `feature` VALUES ('5594857e1d19b602cb089d32', '11', '1', '文本信息下发', '8300', '2', '', '\0', '\0', '2015-07-02 08:31:47', '');
INSERT INTO `feature` VALUES ('559488501d19b602cb089d3a', '12', '1', '提问下发', '8302', '3', '\0', '\0', '\0', '2015-10-30 10:25:08', '');
INSERT INTO `feature` VALUES ('55948a611d19b602cb089d44', '13', '1', '信息点播菜单设置', '8303', '2', '\0', '\0', '\0', '2015-10-30 10:24:49', '');
INSERT INTO `feature` VALUES ('5595f0e71d19b60586c59081', '14', '1', '无线升级', '8105', '12', '', '\0', '\0', '2015-07-03 10:25:50', '');
INSERT INTO `feature` VALUES ('5595f5e51d19b60586c5908f', '15', '1', '连接服务器', '8105', '10', '', '\0', '\0', '2015-07-03 11:00:14', '');
INSERT INTO `feature` VALUES ('559663ce1d19b60636d3afea', '16', '1', '电话回拨', '8400', '2', '', '\0', '\0', '2015-07-03 18:29:37', '');
INSERT INTO `feature` VALUES ('559664931d19b60636d3afef', '17', '1', '设置电话本', '8401', '2', '', '\0', '\0', '2015-07-03 18:35:21', '');
INSERT INTO `feature` VALUES ('5596663a1d19b60636d3affd', '18', '1', '车辆控制', '8500', '1', '', '\0', '\0', '2015-07-03 18:39:20', '');
INSERT INTO `feature` VALUES ('559667351d19b60636d3b000', '19', '1', '司机身份', '8702', '0', '', '\0', '\0', '2015-07-03 18:43:01', '');
INSERT INTO `feature` VALUES ('559686071d19b60636d3b001', '20', '1', '检索多媒体', '8802', '5', '', '\0', '\0', '2015-07-03 21:00:16', '');
INSERT INTO `feature` VALUES ('559688471d19b60636d3b014', '21', '1', '上传时段多媒体', '8803', '6', '', '\0', '\0', '2015-07-04 10:17:57', '');
INSERT INTO `feature` VALUES ('559740b31d19b60636d3b02a', '22', '1', '录音', '8804', '4', '', '\0', '\0', '2015-07-04 10:13:56', '');
INSERT INTO `feature` VALUES ('559742451d19b60636d3b037', '23', '1', '上传单条多媒体', '8805', '2', '', '\0', '\0', '2015-07-04 10:18:26', '');
INSERT INTO `feature` VALUES ('5603642934795768f3c4481e', '24', '1', '设置圆形区域', '8600', '2', '\0', '\0', '\0', '2015-10-23 11:48:27', '');
INSERT INTO `feature` VALUES ('5603671234795768f3c4482e', '25', '1', '删除圆形区域', '8601', '1', '\0', '\0', '\0', '2015-10-23 11:48:20', '');
INSERT INTO `feature` VALUES ('5608b5723479576e72812c6b', '26', '1', '设置矩形区域', '8602', '2', '\0', '\0', '\0', '2015-10-23 11:48:13', '');
INSERT INTO `feature` VALUES ('5608dbdd3479576e72812c7c', '27', '1', '删除矩形区域', '8603', '1', '\0', '\0', '\0', '2015-10-23 11:48:07', '');
INSERT INTO `feature` VALUES ('5609e7dc3479576f67bdf828', '28', '1', '设置多边形区域', '8604', '7', '\0', '\0', '\0', '2015-10-23 11:48:02', '');
INSERT INTO `feature` VALUES ('5609ea353479576f67bdf833', '29', '1', '删除多边形区域', '8605', '1', '\0', '\0', '\0', '2015-10-23 11:47:57', '');
INSERT INTO `feature` VALUES ('5609eec034795770116c4746', '30', '1', '设置路线', '8606', '5', '\0', '\0', '\0', '2015-10-23 11:47:52', '');
INSERT INTO `feature` VALUES ('5609f19434795770116c4757', '31', '1', '删除路线', '8607', '1', '\0', '\0', '\0', '2015-10-23 11:47:48', '');
INSERT INTO `feature` VALUES ('5629f7c97a056430c58ef3d2', '32', '1', '记录仪信息查询', '8700', '1', '', '\0', '\0', '2015-11-13 16:30:28', '');
INSERT INTO `feature` VALUES ('562d9ef97a056430c566c50a', '34', '1', '记录仪数据采集', '8700', '4', '', '\0', '\0', '2015-10-26 14:10:01', '');
INSERT INTO `feature` VALUES ('562da2c97a056430c566c51e', '36', '1', '记录仪设置车辆信息', '8701', '4', '', '\0', '\0', '2015-10-26 14:40:21', '');
INSERT INTO `feature` VALUES ('562dc2957a056430c566c522', '37', '1', '记录仪设置安装日期', '8701', '2', '', '\0', '\0', '2015-10-26 14:40:32', '');
INSERT INTO `feature` VALUES ('562dc3977a056430c566c524', '38', '1', '记录仪设置状态量', '8701', '9', '', '\0', '\0', '2015-10-26 14:40:37', '');
INSERT INTO `feature` VALUES ('562dc60e7a056430c566c533', '39', '1', '记录仪设置时间', '8701', '2', '', '\0', '\0', '2015-10-26 14:40:42', '');
INSERT INTO `feature` VALUES ('562dc6d57a056430c566c537', '40', '1', '记录仪设置脉冲系数', '8701', '2', '', '\0', '\0', '2015-10-29 15:39:05', '');
INSERT INTO `feature` VALUES ('562dc7c47a056430c566c53c', '41', '1', '记录仪设置起始里程', '8701', '2', '', '\0', '\0', '2015-10-26 14:40:52', '');
INSERT INTO `feature` VALUES ('5ad54c407b0368144cff2841', '1', '2', '版本查询', 'VERSION#', '0', '', '\0', '\0', '2018-04-17 09:22:08', '版本查询');
INSERT INTO `feature` VALUES ('5ad709507b03680cd84d9c4d', '2', '2', '查询参数设置', 'PARAM#', '0', '', '\0', '\0', '2018-04-18 17:01:12', '查询参数设置');
INSERT INTO `feature` VALUES ('5ad709727b03680cd84d9c4e', '3', '2', '精简参数查询', 'SCXSZ#', '0', '', '\0', '\0', '2018-04-18 17:01:38', '');
INSERT INTO `feature` VALUES ('5ad7098f7b03680cd84d9c4f', '4', '2', '查询 GPRS 参数', 'GPRSSET#', '0', '', '\0', '\0', '2018-04-18 17:02:07', '');
INSERT INTO `feature` VALUES ('5ad7098f7b03680cd84d9c50', '5', '2', '查询状态', 'STATUS#', '0', '', '\0', '\0', '2018-04-18 18:43:23', '');
INSERT INTO `feature` VALUES ('5ad721647b03680cd84d9c51', '6', '2', '经纬度位置查询指令', 'WHERE#', '0', '', '\0', '\0', '2018-04-18 18:44:09', '');
INSERT INTO `feature` VALUES ('5ad721a17b03680cd84d9c52', '7', '2', '位置链接查询指令', 'URL#', '0', '', '\0', '\0', '2018-04-18 18:44:49', '');
INSERT INTO `feature` VALUES ('5ad721b67b03680cd84d9c53', '10', '2', '地址查询-123', '123#', '0', '', '\0', '\0', '2018-04-18 19:25:28', '');
INSERT INTO `feature` VALUES ('5ad721e97b03680cd84d9c54', '9', '2', '地址查询-POSITION', 'POSITION#', '0', '', '\0', '\0', '2018-04-18 19:25:23', '');
INSERT INTO `feature` VALUES ('5ad721ff7b03680cd84d9c55', '8', '2', '地址查询-DW', 'DW#', '0', '', '\0', '\0', '2018-04-18 19:25:16', '');
INSERT INTO `feature` VALUES ('5ad722177b03680cd84d9c56', '11', '2', '围栏状态查询', 'FENCE#', '0', '', '\0', '\0', '2018-04-18 18:46:47', '');
INSERT INTO `feature` VALUES ('5ad722297b03680cd84d9c57', '12', '2', '位移状态查询', 'MOVING#', '0', '', '\0', '\0', '2018-04-18 18:47:05', '');
INSERT INTO `feature` VALUES ('5ad7223d7b03680cd84d9c58', '13', '2', 'APN自适应状态查询', 'ASETAPN#', '0', '', '\0', '\0', '2018-04-18 19:26:07', '');
INSERT INTO `feature` VALUES ('5aebc31b7b03680cd84d9c59', '14', '2', 'GPS 数据定时发送间隔', 'TIMER', '3', '', '\0', '\0', '2018-05-10 09:53:22', '');
INSERT INTO `feature` VALUES ('5aebca877b03680cd84d9c5c', '15', '2', '重启指令', 'RESET#', '0', '', '\0', '\0', '2018-05-04 10:50:47', '');
INSERT INTO `feature` VALUES ('5aebcacd7b03680cd84d9c5d', '16', '2', 'GPRS 重连次数', 'LINK', '2', '', '\0', '\0', '2018-05-04 10:55:20', '');
INSERT INTO `feature` VALUES ('5b060e277b03680cd87195d8', '17', '2', '超速提醒设置', 'SPEED', '1', '', '\0', '\0', '2018-05-24 09:09:22', '');

-- ----------------------------
-- Table structure for firmware
-- ----------------------------
DROP TABLE IF EXISTS `firmware`;
CREATE TABLE `firmware` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `FACTORYID` tinyint(3) unsigned NOT NULL COMMENT '车厂ID',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `VERSION` varchar(10) NOT NULL COMMENT '版本',
  `UPLOADTIME` datetime DEFAULT NULL COMMENT '上传时间',
  `FILESIZE` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小',
  `CONTENT` mediumblob COMMENT '内容',
  `CHECKCODE` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '校验码',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '说明',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FACTORYID` (`FACTORYID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='固件库';

-- ----------------------------
-- Records of firmware
-- ----------------------------

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `GPSTIME` datetime NOT NULL COMMENT '时间',
  `VSS` float(4,1) unsigned NOT NULL COMMENT '车速',
  `MILEAGE` int(10) unsigned NOT NULL COMMENT '里程',
  `BV` float(3,1) unsigned NOT NULL COMMENT '电池电压',
  `RPM` smallint(6) unsigned NOT NULL COMMENT '发动机转速',
  `ECT` tinyint(4) NOT NULL COMMENT '发动机冷却液温度',
  `RUNTM` smallint(5) unsigned NOT NULL COMMENT '发动机运行时间',
  `EOT` tinyint(4) NOT NULL COMMENT '发动机机油温度',
  `IFC` float(4,1) NOT NULL COMMENT '瞬时油耗',
  `OT` float(5,2) NOT NULL COMMENT '油箱油量',
  `IAT` tinyint(4) NOT NULL COMMENT '进气温度',
  `ET` tinyint(4) NOT NULL COMMENT '环境温度',
  `MAF` smallint(6) NOT NULL COMMENT '空气流量',
  `AP` tinyint(3) unsigned NOT NULL COMMENT '大气压力',
  `LOADPCT` float(5,2) NOT NULL COMMENT '计算负荷',
  `MAP` tinyint(3) unsigned NOT NULL COMMENT '进气歧管绝对压力',
  `LFTP` smallint(6) NOT NULL COMMENT '左前轮胎压力',
  `ALTP` smallint(6) NOT NULL COMMENT '左后轮胎压力',
  `RRTP` smallint(6) NOT NULL COMMENT '右后轮胎压力',
  `RFTP` smallint(6) NOT NULL COMMENT '右前轮胎压力',
  `MORD` int(11) unsigned NOT NULL COMMENT '亮灯里程',
  `IAA` float(4,1) NOT NULL COMMENT '点火提前角',
  `LONGFTB1` float(5,2) NOT NULL COMMENT '长期燃油修正B1',
  `SHRTFTB1` float(5,2) NOT NULL COMMENT '短期燃油修正B1',
  `SHRTFTB1S1` float(5,2) NOT NULL COMMENT '短期燃油修正B1S1',
  `SHRTFTB1S2` float(5,2) NOT NULL COMMENT '短期燃油修正B1S2',
  `FAULTS` tinyint(4) NOT NULL COMMENT '故障数量',
  `TP` float(5,2) NOT NULL COMMENT '节气门开度',
  `TPALB` float(5,2) NOT NULL COMMENT '节气门绝对位置B',
  `TPALC` float(5,2) NOT NULL COMMENT '节气门绝对位置C',
  `FRP` tinyint(3) unsigned NOT NULL COMMENT '燃油压力',
  `FUELSYS1` varchar(10) NOT NULL COMMENT '燃油状态1',
  `FUELSYS2` varchar(10) NOT NULL COMMENT '燃油状态2',
  `O2SB1S1` float(3,1) NOT NULL COMMENT '氧传感器电压B1S1',
  `O2SB1S2` float(3,1) NOT NULL COMMENT '氧传感器电压B1S2',
  `PPSD` float(5,2) NOT NULL COMMENT '油门踏板位置D',
  `PPSE` float(5,2) NOT NULL COMMENT '油门踏板位置E',
  `VAPTB1S1` tinyint(4) NOT NULL COMMENT '蒸发汽温度B1S1',
  `VAPTB1S2` tinyint(4) NOT NULL COMMENT '蒸发汽温度B1S2',
  KEY `IX_DEVICEID_GPSTIME` (`NUMBER`,`GPSTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='关键数据流表';

-- ----------------------------
-- Records of flow
-- ----------------------------

-- ----------------------------
-- Table structure for instruct
-- ----------------------------
DROP TABLE IF EXISTS `instruct`;
CREATE TABLE `instruct` (
  `ID` char(36) NOT NULL COMMENT '记录ID',
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `SENDTIME` datetime NOT NULL COMMENT '发送时间',
  `SENDERID` char(36) NOT NULL COMMENT '发送人ID',
  `USER` varchar(20) NOT NULL COMMENT '发送人姓名',
  `COMMAND` varchar(10) NOT NULL COMMENT '命令',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `PARAMETER` text NOT NULL COMMENT '参数',
  `STATUS` tinyint(4) NOT NULL COMMENT '状态',
  `REPLYTIME` datetime DEFAULT NULL COMMENT '应答时间',
  `RESULT` text COMMENT '结果',
  PRIMARY KEY (`ID`),
  KEY `IX_SENDERID_DEVICEID_SENDTIME` (`SENDERID`,`NUMBER`,`SENDTIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指令状态表';

-- ----------------------------
-- Records of instruct
-- ----------------------------

-- ----------------------------
-- Table structure for latest
-- ----------------------------
DROP TABLE IF EXISTS `latest`;
CREATE TABLE `latest` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `GPSTIME` datetime NOT NULL COMMENT 'GPS时间',
  `SERVERTIME` datetime NOT NULL COMMENT '服务器时间',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `ALT` float unsigned NOT NULL COMMENT '海拔',
  `SPEED` smallint(6) unsigned NOT NULL COMMENT '速度',
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
  `ROUTEFLAG` tinyint(4) NOT NULL COMMENT '路段行驶结果',
  `ALARMID` smallint(5) unsigned NOT NULL COMMENT '报警确人ID',
  `EXSTATUS` int(10) unsigned NOT NULL COMMENT '扩展车辆信号状态位',
  `IOSTATUS` smallint(5) unsigned NOT NULL COMMENT 'IO状态位',
  `AD0` smallint(5) unsigned NOT NULL COMMENT '模拟量AD0',
  `AD1` smallint(5) unsigned NOT NULL COMMENT '模拟量AD1',
  `NETWORK` tinyint(3) unsigned NOT NULL COMMENT '网络强度',
  `SATELLITES` tinyint(3) unsigned NOT NULL COMMENT '卫星数量',
  PRIMARY KEY (`NUMBER`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='最后位置表';

-- ----------------------------
-- Records of latest
-- ----------------------------

-- ----------------------------
-- Table structure for multimedia
-- ----------------------------
DROP TABLE IF EXISTS `multimedia`;
CREATE TABLE `multimedia` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `MEDIAID` int(10) unsigned NOT NULL COMMENT '媒体id',
  `MEDIATYPE` tinyint(4) NOT NULL COMMENT '媒体类型',
  `FORMATTYPE` tinyint(4) NOT NULL COMMENT '格式类型',
  `EVENTTYPE` tinyint(4) NOT NULL COMMENT '事件类型',
  `CHANNELID` tinyint(4) NOT NULL COMMENT '通道id',
  `LNG` decimal(9,6) unsigned NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) unsigned NOT NULL COMMENT '纬度',
  `SPEED` float unsigned NOT NULL COMMENT '速度',
  `ANGLE` smallint(5) unsigned NOT NULL COMMENT '方向',
  `ALARMS` int(11) unsigned NOT NULL COMMENT '报警',
  `STATUS` int(11) unsigned NOT NULL COMMENT '状态',
  `CONTENT` mediumblob COMMENT '内容',
  PRIMARY KEY (`ID`),
  KEY `NUMBER_TIME` (`NUMBER`,`TIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用于存放多媒体数据';

-- ----------------------------
-- Records of multimedia
-- ----------------------------

-- ----------------------------
-- Table structure for onoffline
-- ----------------------------
DROP TABLE IF EXISTS `onoffline`;
CREATE TABLE `onoffline` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TIME` datetime NOT NULL COMMENT '时间',
  `ISON` bit(1) NOT NULL COMMENT '上线：1，离线：0',
  `ONTIME` int(11) NOT NULL COMMENT '在线时长：秒',
  `OFFTIME` int(11) NOT NULL COMMENT '离线时长：秒',
  KEY `NUMBER_TIME` (`NUMBER`,`TIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在线离线日志表';

-- ----------------------------
-- Records of onoffline
-- ----------------------------

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter` (
  `ID` char(24) NOT NULL COMMENT '记录ID',
  `PID` char(24) NOT NULL COMMENT '父ID',
  `FEATUREID` char(24) NOT NULL COMMENT '功能ID',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `LABEL` varchar(50) NOT NULL COMMENT '标签',
  `TYPE` int(11) NOT NULL COMMENT '类型',
  `SELECTVALUE` varchar(50) NOT NULL COMMENT '单选值',
  `DICTIONARYKEY` varchar(50) NOT NULL COMMENT '字典键',
  `SWITCHBIT` int(11) NOT NULL COMMENT '开关位',
  `ROWS` int(11) NOT NULL COMMENT '行数',
  `COLUMNS` int(11) NOT NULL COMMENT '列数',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `DEFAULTVALUE` varchar(50) DEFAULT NULL COMMENT '默认值',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`),
  KEY `IX_PID` (`PID`) USING BTREE,
  KEY `IX_FEATUREID` (`FEATUREID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能参数表';

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES ('5589ecb503c70e2678e39f3e', '', '5589ec6303c70e2678e39f3d', '1', 'channelId', '通道ID', '2', '', '', '0', '0', '0', '2015-11-13 16:07:09', '1', '摄像头路数,从1开始');
INSERT INTO `parameter` VALUES ('5589ecef03c70e2678e39f3f', '', '5589ec6303c70e2678e39f3d', '2', 'actionFlag', '拍摄命令', '1', '', '', '0', '0', '0', '2015-07-16 09:49:46', '1', '0表示停止拍摄；-1表示录像；其他表示拍照张数');
INSERT INTO `parameter` VALUES ('5589ed1d03c70e2678e39f40', '', '5589ec6303c70e2678e39f3d', '3', 'seconds', '拍照间隔/录像时间', '1', '', '', '0', '0', '0', '2015-07-15 11:13:01', '0', '单位：秒，0表示按最小间隔拍照或一直录像');
INSERT INTO `parameter` VALUES ('5589ed4403c70e2678e39f41', '', '5589ec6303c70e2678e39f3d', '4', 'saveFlag', '保存类型', '2', '', '', '0', '0', '0', '2015-07-15 11:12:40', '', '保存类型');
INSERT INTO `parameter` VALUES ('5589ed6a03c70e2678e39f42', '5589ed4403c70e2678e39f41', '5589ec6303c70e2678e39f3d', '1', '', '时实上传', '1', '0', '', '0', '0', '0', '2015-07-15 21:32:57', '', '时实上传');
INSERT INTO `parameter` VALUES ('5589ed7f03c70e2678e39f43', '5589ed4403c70e2678e39f41', '5589ec6303c70e2678e39f3d', '2', '', '本地保存', '1', '1', '', '0', '0', '0', '2015-07-15 21:33:01', '', '本地保存');
INSERT INTO `parameter` VALUES ('5589eec203c70e2678e39f44', '', '5589ec6303c70e2678e39f3d', '5', 'resolution', '分辨率', '2', '', '', '0', '0', '0', '2015-07-15 11:15:03', '', '分辨率');
INSERT INTO `parameter` VALUES ('5589eede03c70e2678e39f45', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '1', '', '320*240', '1', '1', '', '0', '0', '0', '2015-07-15 21:33:07', '', '320*240');
INSERT INTO `parameter` VALUES ('5589eefc03c70e2678e39f46', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '2', '', '640*480', '1', '2', '', '0', '0', '0', '2015-07-15 21:33:11', '', '640*480');
INSERT INTO `parameter` VALUES ('5589ef1303c70e2678e39f47', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '3', '', '800*600', '1', '3', '', '0', '0', '0', '2015-07-15 21:33:14', '', '800*600');
INSERT INTO `parameter` VALUES ('5589ef2803c70e2678e39f48', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '4', '', '1024*768', '1', '4', '', '0', '0', '0', '2015-07-15 21:33:19', '', '1024*768');
INSERT INTO `parameter` VALUES ('5589ef4a03c70e2678e39f49', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '5', '', '176*144[Qcif]', '1', '5', '', '0', '0', '0', '2015-07-15 21:33:23', '', '176*144[Qcif]');
INSERT INTO `parameter` VALUES ('5589ef6203c70e2678e39f4a', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '6', '', '352*288[Cif]', '1', '6', '', '0', '0', '0', '2015-07-15 21:33:26', '', '352*288[Cif]');
INSERT INTO `parameter` VALUES ('5589ef9203c70e2678e39f4b', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '7', '', '704*288[HALF D1]', '1', '7', '', '0', '0', '0', '2015-07-15 21:33:30', '', '704*288[HALF D1]');
INSERT INTO `parameter` VALUES ('5589efa903c70e2678e39f4c', '5589eec203c70e2678e39f44', '5589ec6303c70e2678e39f3d', '8', '', '704*576[D1]', '1', '8', '', '0', '0', '0', '2015-07-15 21:33:34', '', '704*576[D1]');
INSERT INTO `parameter` VALUES ('5589efd503c70e2678e39f4d', '', '5589ec6303c70e2678e39f3d', '6', 'quality', '图像质量', '1', '', '', '0', '0', '0', '2015-07-15 11:17:10', '5', '图像 / 视频质量， 1-10,1 最好');
INSERT INTO `parameter` VALUES ('5589effc03c70e2678e39f4e', '', '5589ec6303c70e2678e39f3d', '7', 'brightness', '亮度', '1', '', '', '0', '0', '0', '2015-07-15 11:17:22', '127', '亮度， 0-255');
INSERT INTO `parameter` VALUES ('5589f02203c70e2678e39f4f', '', '5589ec6303c70e2678e39f3d', '8', 'contrast', '对比度', '1', '', '', '0', '0', '0', '2015-07-15 11:17:39', '65', '对比度， 0-127');
INSERT INTO `parameter` VALUES ('5589f04603c70e2678e39f50', '', '5589ec6303c70e2678e39f3d', '9', 'saturation', '饱和度', '1', '', '', '0', '0', '0', '2015-07-15 11:17:54', '65', '饱和度， 0-127');
INSERT INTO `parameter` VALUES ('5589f06103c70e2678e39f51', '', '5589ec6303c70e2678e39f3d', '10', 'chroma', '色度', '1', '', '', '0', '0', '0', '2015-07-15 11:18:12', '127', '色度， 0-255');
INSERT INTO `parameter` VALUES ('558ff78303c70e316a452c5c', '', '558ff76103c70e316a452c5b', '1', 'type', '类型', '2', '', '', '0', '0', '0', '2015-07-15 17:54:13', '', '');
INSERT INTO `parameter` VALUES ('558ff7ca03c70e316a452c5d', '558ff78303c70e316a452c5c', '558ff76103c70e316a452c5b', '0', '', '删除终端所有事件', '1', '0', '', '0', '0', '0', '2015-07-15 17:54:18', '', '');
INSERT INTO `parameter` VALUES ('558ff7df03c70e316a452c5e', '558ff78303c70e316a452c5c', '558ff76103c70e316a452c5b', '1', '', '更新事件', '1', '1', '', '0', '0', '0', '2015-07-15 17:54:22', '', '');
INSERT INTO `parameter` VALUES ('558ff7f003c70e316a452c5f', '558ff78303c70e316a452c5c', '558ff76103c70e316a452c5b', '2', '', '追加事件', '1', '2', '', '0', '0', '0', '2015-07-15 17:54:26', '', '');
INSERT INTO `parameter` VALUES ('558ff82f03c70e316a452c60', '558ff78303c70e316a452c5c', '558ff76103c70e316a452c5b', '3', '', '修改事件', '1', '3', '', '0', '0', '0', '2015-07-15 17:54:30', '', '');
INSERT INTO `parameter` VALUES ('558ff8a103c70e316a452c61', '558ff78303c70e316a452c5c', '558ff76103c70e316a452c5b', '4', '', '删除终端指定事件', '1', '4', '', '0', '0', '0', '2015-07-15 17:54:34', '', '');
INSERT INTO `parameter` VALUES ('558ff8dc03c70e316a452c62', '', '558ff76103c70e316a452c5b', '2', 'events', '事件项列表', '5', '', '', '0', '0', '0', '2015-07-15 17:54:46', '', '');
INSERT INTO `parameter` VALUES ('558ff9a703c70e316a452c63', '558ff8dc03c70e316a452c62', '558ff76103c70e316a452c5b', '1', '', '事件项', '6', '', '', '0', '0', '0', '2015-07-15 17:54:51', '', '');
INSERT INTO `parameter` VALUES ('558ff9c803c70e316a452c64', '558ff9a703c70e316a452c63', '558ff76103c70e316a452c5b', '1', 'id', '事件编号', '1', '', '', '0', '0', '0', '2015-07-15 18:09:54', '', '');
INSERT INTO `parameter` VALUES ('558ff9da03c70e316a452c65', '558ff9a703c70e316a452c63', '558ff76103c70e316a452c5b', '2', 'content', '事件内容', '1', '', '', '0', '0', '500', '2015-07-15 18:10:04', '', '');
INSERT INTO `parameter` VALUES ('5591faba03c70e360cb98ced', '', '5591fa9c03c70e360cb98cec', '1', 'parameters', '参数项列表', '3', '', '', '0', '0', '0', '2015-07-15 11:29:25', '', '');
INSERT INTO `parameter` VALUES ('5591fbb703c70e360cb98cf3', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '6', '', 'SMS消息应答超时时间', '1', '', '0006', '0', '0', '0', '2015-07-15 19:39:18', '20', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('5591fbcd03c70e360cb98cf4', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '7', '', 'SMS消息重传次数', '1', '', '0007', '0', '0', '0', '2015-07-15 19:39:22', '3', '');
INSERT INTO `parameter` VALUES ('5591fc2703c70e360cb98cf5', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '16', '', '主服务器APN,无线通信拨号访问点', '1', '', '0010', '0', '0', '0', '2015-07-15 19:39:30', '', '若网络制式为 CDMA,则该处为 PPP 拨号号码');
INSERT INTO `parameter` VALUES ('5591fc7403c70e360cb98cf6', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '17', '', '主服务器无线通信拨号用户名', '1', '', '0011', '0', '0', '0', '2015-07-15 19:39:35', '', '');
INSERT INTO `parameter` VALUES ('5591fcad03c70e360cb98cf8', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '19', '', '主服务器地址,IP或域名', '1', '', '0013', '0', '0', '0', '2015-07-15 19:40:01', '', '');
INSERT INTO `parameter` VALUES ('559202f703c70e3645045400', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '26', '', 'IC卡认证主服务器IP地址或域名', '1', '', '001A', '0', '0', '0', '2015-07-15 19:40:42', '', '');
INSERT INTO `parameter` VALUES ('5592033303c70e3645045401', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '27', '', 'IC卡认证主服务器TCP端口', '1', '', '001B', '0', '0', '0', '2015-07-15 19:40:49', '', '');
INSERT INTO `parameter` VALUES ('5592036e03c70e3645045403', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '29', '', 'IC卡认证备份服务器IP地址或域名', '1', '', '001D', '0', '0', '0', '2015-07-15 19:41:06', '', '');
INSERT INTO `parameter` VALUES ('5592039703c70e3645045404', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '32', '', '位置汇报策略', '2', '', '0020', '0', '0', '0', '2015-07-15 19:41:19', '', '');
INSERT INTO `parameter` VALUES ('559203b203c70e3645045405', '5592039703c70e3645045404', '5591fa9c03c70e360cb98cec', '1', '', '定时汇报', '1', '0', '', '0', '0', '0', '2015-07-15 19:41:23', '', '');
INSERT INTO `parameter` VALUES ('559203c103c70e3645045406', '5592039703c70e3645045404', '5591fa9c03c70e360cb98cec', '2', '', '定距汇报', '1', '1', '', '0', '0', '0', '2015-07-15 19:41:26', '', '');
INSERT INTO `parameter` VALUES ('559203d203c70e3645045407', '5592039703c70e3645045404', '5591fa9c03c70e360cb98cec', '3', '', '定时和定距汇报', '1', '3', '', '0', '0', '0', '2015-07-15 19:41:30', '', '');
INSERT INTO `parameter` VALUES ('5592043803c70e3645045408', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '33', '', '位置汇报方案', '2', '', '0021', '0', '0', '0', '2015-07-15 19:41:33', '', '');
INSERT INTO `parameter` VALUES ('5592044c03c70e3645045409', '5592043803c70e3645045408', '5591fa9c03c70e360cb98cec', '1', '', '根据 ACC 状态', '1', '0', '', '0', '0', '0', '2015-07-15 19:41:37', '', '');
INSERT INTO `parameter` VALUES ('5592049703c70e364504540a', '5592043803c70e3645045408', '5591fa9c03c70e360cb98cec', '2', '', '根据登录状态和 ACC 状态', '1', '2', '', '0', '0', '0', '2015-07-15 19:41:40', '', '');
INSERT INTO `parameter` VALUES ('5592055b03c70e364504540d', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '40', '', '紧急报警时汇报时间间隔', '1', '', '0028', '0', '0', '0', '2015-07-15 19:41:52', '1', '单位为秒(s),>0');
INSERT INTO `parameter` VALUES ('559205f103c70e364504540f', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '44', '', '缺省距离汇报间隔', '1', '', '002C', '0', '0', '0', '2015-07-15 19:42:03', '100', '单位为米(m),>0');
INSERT INTO `parameter` VALUES ('5592077603c70e3645045415', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '64', '', '监控平台电话号码', '1', '', '0040', '0', '0', '0', '2015-07-15 19:42:29', '', '');
INSERT INTO `parameter` VALUES ('5592079903c70e3645045416', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '65', '', '复位电话号码', '1', '', '0041', '0', '0', '0', '2015-07-15 19:42:33', '', '可采用此电话号码拨打终端电话让终端复位');
INSERT INTO `parameter` VALUES ('559207c003c70e3645045417', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '66', '', '恢复出厂设置电话号码', '1', '', '0042', '0', '0', '0', '2015-07-15 19:42:37', '', '可采用此电话号码拨打终端电话让终端恢复出厂设置');
INSERT INTO `parameter` VALUES ('559207fb03c70e3645045419', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '68', '', '接收终端SMS文本报警号码', '1', '', '0044', '0', '0', '0', '2015-07-15 19:42:45', '', '');
INSERT INTO `parameter` VALUES ('559208fb03c70e3645045420', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '69', '', '终端电话接听策略', '2', '', '0045', '0', '0', '0', '2015-07-15 19:42:50', '', '');
INSERT INTO `parameter` VALUES ('5592091a03c70e3645045422', '559208fb03c70e3645045420', '5591fa9c03c70e360cb98cec', '0', '', '自动接听', '1', '0', '', '0', '0', '0', '2015-07-15 19:42:54', '', '');
INSERT INTO `parameter` VALUES ('5592094803c70e3645045423', '559208fb03c70e3645045420', '5591fa9c03c70e360cb98cec', '1', '', 'ACC:ON自动接听,OFF手动接听', '1', '1', '', '0', '0', '0', '2015-07-15 19:42:58', '', '');
INSERT INTO `parameter` VALUES ('55920a1103c70e3645045427', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '72', '', '监听电话号码', '1', '', '0048', '0', '0', '0', '2015-07-15 19:43:10', '', '');
INSERT INTO `parameter` VALUES ('55920a3c03c70e3645045428', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '73', '', '监管平台特权短信号码', '1', '', '0049', '0', '0', '0', '2015-07-15 19:43:14', '', '');
INSERT INTO `parameter` VALUES ('55920a7d03c70e3645045429', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '80', '', '报警屏蔽字', '4', '', '0050', '0', '0', '0', '2015-07-15 19:43:19', '', '与位置信息汇报消息中的报警标志相对应,相应位为 1 则相应报警被屏蔽');
INSERT INTO `parameter` VALUES ('55920b2f03c70e364504542e', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '4', '', 'GNSS模块发生故障', '1', '', '', '4', '0', '0', '2015-07-15 19:43:39', '', '');
INSERT INTO `parameter` VALUES ('55920b4903c70e3645045430', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '6', '', 'GNSS天线短路', '1', '', '', '6', '0', '0', '2015-07-15 19:43:47', '', '');
INSERT INTO `parameter` VALUES ('55920b5803c70e3645045431', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '7', '', '终端主电源欠压', '1', '', '', '7', '0', '0', '2015-07-15 19:43:50', '', '');
INSERT INTO `parameter` VALUES ('55920b9503c70e3645045435', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '11', '', '摄像头故障', '1', '', '', '11', '0', '0', '2015-07-15 19:44:08', '', '');
INSERT INTO `parameter` VALUES ('55920bb303c70e3645045437', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '13', '', '超速预警', '1', '', '', '13', '0', '0', '2015-07-15 19:44:15', '', '');
INSERT INTO `parameter` VALUES ('55920c0003c70e364504543b', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '20', '', '进出区域', '1', '', '', '20', '0', '0', '2015-07-15 19:44:32', '', '');
INSERT INTO `parameter` VALUES ('55920c4c03c70e3645045440', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '25', '', '车辆油量异常', '1', '', '', '25', '0', '0', '2015-07-15 19:44:56', '', '');
INSERT INTO `parameter` VALUES ('55920c9903c70e3645045443', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '28', '', '车辆非法位移', '1', '', '', '28', '0', '0', '2015-07-15 19:45:07', '', '');
INSERT INTO `parameter` VALUES ('55920cb803c70e3645045445', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '30', '', '侧翻预警', '1', '', '', '30', '0', '0', '2015-07-15 19:45:16', '', '');
INSERT INTO `parameter` VALUES ('55920cca03c70e3645045446', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '31', '', '非法开门报警', '1', '', '', '31', '0', '0', '2015-07-15 19:45:20', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e89', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '4', '', 'UDP消息应答超时时间', '1', '', '0004', '0', '0', '0', '2015-07-15 19:39:09', '20', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e8a', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '5', '', 'UDP消息重传次数', '1', '', '0005', '0', '0', '0', '2015-07-15 19:39:13', '3', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e8f', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '18', '', '主服务器无线通信拨号密码', '1', '', '0012', '0', '0', '0', '2015-07-15 19:39:39', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e91', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '20', '', '备份服务器APN,无线通信拨号访问点', '1', '', '0014', '0', '0', '0', '2015-07-15 19:39:51', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e92', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '21', '', '备份服务器无线通信拨号用户名', '1', '', '0015', '0', '0', '0', '2015-07-15 19:39:57', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e93', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '22', '', '备份服务器无线通信拨号密码', '1', '', '0016', '0', '0', '0', '2015-07-15 19:40:06', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e94', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '23', '', '备份服务器地址,IP或域名', '1', '', '0017', '0', '0', '0', '2015-07-15 19:40:18', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e95', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '24', '', '服务器TCP端口', '1', '', '0018', '0', '0', '0', '2015-07-15 19:40:26', '7808', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e96', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '25', '', '服务器UDP端口', '1', '', '0019', '0', '0', '0', '2015-07-15 19:40:33', '6808', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e99', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '28', '', 'IC卡认证主服务器UDP端口', '1', '', '001C', '0', '0', '0', '2015-07-15 19:40:58', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e9d', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '34', '', '驾驶员未登录汇报时间间隔', '1', '', '0022', '0', '0', '0', '2015-07-15 19:41:44', '30', '单位为秒(s),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0e9e', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '39', '', '休眠时汇报时间间隔', '1', '', '0027', '0', '0', '0', '2015-07-15 19:41:47', '180', '单位为秒(s),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea0', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '41', '', '缺省时间汇报间隔', '1', '', '0029', '0', '0', '0', '2015-07-15 19:41:57', '30', '单位为秒(s),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea2', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '45', '', '驾驶员未登录汇报距离间隔', '1', '', '002D', '0', '0', '0', '2015-07-15 19:42:06', '100', '单位为米(m),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea3', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '46', '', '休眠时汇报距离间隔', '1', '', '002E', '0', '0', '0', '2015-07-15 19:42:10', '500', '单位为米(m),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea4', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '47', '', '紧急报警时汇报距离间隔', '1', '', '002F', '0', '0', '0', '2015-07-15 19:42:14', '50', '单位为米(m),>0');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea5', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '48', '', '拐点补传角度', '1', '', '0030', '0', '0', '0', '2015-07-15 19:42:19', '15', '<180');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ea6', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '49', '', '电子围栏半径(非法位移阈值)', '1', '', '0031', '0', '0', '0', '2015-07-15 19:42:23', '100', '单位为米');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0eaa', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '67', '', '监控平台SMS电话号码', '1', '', '0043', '0', '0', '0', '2015-07-15 19:42:41', '', '');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0ead', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '70', '', '每次最长通话时间', '1', '', '0046', '0', '0', '0', '2015-07-15 19:43:03', '0', '单位为秒(s),0 为不允许通话,FFFFFFFF 为 不限制');
INSERT INTO `parameter` VALUES ('559233ab03c70e36affe0eae', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '71', '', '当月最长通话时间', '1', '', '0047', '0', '0', '0', '2015-07-15 19:43:07', '0', '单位为秒(s),0 为不允许通话,FFFFFFFF 为 不限制');
INSERT INTO `parameter` VALUES ('5592346c03c70e36affe0eb4', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '1', '', '终端心跳发送间隔', '1', '', '0001', '0', '0', '0', '2015-07-15 19:38:28', '120', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('5592346f03c70e36affe0eb6', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '2', '', 'TCP消息应答超时时间', '1', '', '0002', '0', '0', '0', '2015-07-15 19:39:01', '20', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('5592347103c70e36affe0eb7', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '3', '', 'TCP消息重传次数', '1', '', '0003', '0', '0', '0', '2015-07-15 19:39:05', '3', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8d7', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '0', '', '紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 19:43:23', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8d8', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '1', '', '超速报警', '1', '', '', '1', '0', '0', '2015-07-15 19:43:27', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8d9', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '2', '', '疲劳驾驶', '1', '', '', '2', '0', '0', '2015-07-15 19:43:30', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8da', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '3', '', '危险预警', '1', '', '', '3', '0', '0', '2015-07-15 19:43:34', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8dc', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '5', '', 'GNSS天线未接或被剪断', '1', '', '', '5', '0', '0', '2015-07-15 19:43:43', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8df', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '8', '', '终端主电源掉电', '1', '', '', '8', '0', '0', '2015-07-15 19:43:54', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e0', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '9', '', '终端LCD或显示器故障', '1', '', '', '9', '0', '0', '2015-07-15 19:43:58', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e1', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '10', '', 'TTS模块故障', '1', '', '', '10', '0', '0', '2015-07-15 19:44:05', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e3', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '12', '', '道路运输证 IC 卡模块故障', '1', '', '', '12', '0', '0', '2015-07-15 19:44:12', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e5', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '14', '', '疲劳驾驶预警', '1', '', '', '14', '0', '0', '2015-07-15 19:44:19', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e6', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '18', '', '当天累计驾驶超时', '1', '1', '', '18', '0', '0', '2015-07-15 19:44:23', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e7', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '19', '', '超时停车', '1', '', '', '19', '0', '0', '2015-07-15 19:44:27', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8e9', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '21', '', '进出路线', '1', '', '', '21', '0', '0', '2015-07-15 19:44:35', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8ea', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '22', '', '路段行驶时间不足/过长', '1', '', '', '22', '0', '0', '2015-07-15 19:44:39', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8eb', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '23', '', '路线偏离报警', '1', '', '', '23', '0', '0', '2015-07-15 19:44:43', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8ec', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '24', '', '车辆VSS故障', '1', '', '', '24', '0', '0', '2015-07-15 19:44:50', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8ee', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '26', '', '车辆被盗', '1', '', '', '26', '0', '0', '2015-07-15 19:45:00', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8ef', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '27', '', '车辆非法点火', '1', '', '', '27', '0', '0', '2015-07-15 19:45:03', '', '');
INSERT INTO `parameter` VALUES ('5592366703c70e36b1e9c8f1', '55920a7d03c70e3645045429', '5591fa9c03c70e360cb98cec', '29', '', '碰撞预警', '1', '', '', '29', '0', '0', '2015-07-15 19:45:12', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c5c', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '81', '', '报警发送文本SMS开关', '4', '', '0051', '0', '0', '0', '2015-07-15 19:45:30', '', '与位置信息汇报消息中的报警标志相对应, 相应位为 1 则相应报警时发送文本SMS');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c5d', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '0', '', '紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 19:45:35', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c5e', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '1', '', '超速报警', '1', '', '', '1', '0', '0', '2015-07-15 19:45:40', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c5f', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '2', '', '疲劳驾驶', '1', '', '', '2', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c60', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '3', '', '危险预警', '1', '', '', '3', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c61', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '4', '', 'GNSS模块发生故障', '1', '', '', '4', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c62', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '5', '', 'GNSS天线未接或被剪断', '1', '', '', '5', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ab03c70e36b1133c63', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '6', '', 'GNSS天线短路', '1', '', '', '6', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c64', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '7', '', '终端主电源欠压', '1', '', '', '7', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c65', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '8', '', '终端主电源掉电', '1', '', '', '8', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c66', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '9', '', '终端LCD或显示器故障', '1', '', '', '9', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c67', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '10', '', 'TTS模块故障', '1', '', '', '10', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c68', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '11', '', '摄像头故障', '1', '', '', '11', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c69', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '12', '', '道路运输证 IC 卡模块故障', '1', '', '', '12', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6a', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '13', '', '超速预警', '1', '', '', '13', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6b', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '14', '', '疲劳驾驶预警', '1', '', '', '14', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6c', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '18', '', '当天累计驾驶超时', '1', '1', '', '18', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6d', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '19', '', '超时停车', '1', '', '', '19', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6e', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '20', '', '进出区域', '1', '', '', '20', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c6f', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '21', '', '进出路线', '1', '', '', '21', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c70', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '22', '', '路段行驶时间不足/过长', '1', '', '', '22', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c71', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '23', '', '路线偏离报警', '1', '', '', '23', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c72', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '24', '', '车辆 VSS 故障', '1', '', '', '24', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c73', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '25', '', '车辆油量异常', '1', '', '', '25', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c74', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '26', '', '车辆被盗', '1', '', '', '26', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c75', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '27', '', '车辆非法点火', '1', '', '', '27', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c76', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '28', '', '车辆非法位移', '1', '', '', '28', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c77', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '29', '', '碰撞预警', '1', '', '', '29', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c78', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '30', '', '侧翻预警', '1', '', '', '30', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237ac03c70e36b1133c79', '559237ab03c70e36b1133c5c', '5591fa9c03c70e360cb98cec', '31', '', '非法开门报警', '1', '', '', '31', '0', '0', '2015-07-15 21:21:33', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7a', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '82', '', '报警拍摄开关', '4', '', '0052', '0', '0', '0', '2015-07-15 21:23:07', '', '与位置信息汇报消息中的报警标志相对应,相应位为 1 则相应报警时摄像头拍摄');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7b', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '0', '', '紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7c', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '1', '', '超速报警', '1', '', '', '1', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7d', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '2', '', '疲劳驾驶', '1', '', '', '2', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7e', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '3', '', '危险预警', '1', '', '', '3', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c7f', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '4', '', 'GNSS模块发生故障', '1', '', '', '4', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c80', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '5', '', 'GNSS天线未接或被剪断', '1', '', '', '5', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c81', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '6', '', 'GNSS天线短路', '1', '', '', '6', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c82', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '7', '', '终端主电源欠压', '1', '', '', '7', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c83', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '8', '', '终端主电源掉电', '1', '', '', '8', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c84', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '9', '', '终端LCD或显示器故障', '1', '', '', '9', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c85', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '10', '', 'TTS模块故障', '1', '', '', '10', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c86', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '11', '', '摄像头故障', '1', '', '', '11', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c87', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '12', '', '道路运输证 IC 卡模块故障', '1', '', '', '12', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c88', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '13', '', '超速预警', '1', '', '', '13', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c89', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '14', '', '疲劳驾驶预警', '1', '', '', '14', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8a', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '18', '', '当天累计驾驶超时', '1', '1', '', '18', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8b', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '19', '', '超时停车', '1', '', '', '19', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8c', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '20', '', '进出区域', '1', '', '', '20', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8d', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '21', '', '进出路线', '1', '', '', '21', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8e', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '22', '', '路段行驶时间不足/过长', '1', '', '', '22', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c8f', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '23', '', '路线偏离报警', '1', '', '', '23', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c90', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '24', '', '车辆 VSS 故障', '1', '', '', '24', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c91', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '25', '', '车辆油量异常', '1', '', '', '25', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c92', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '26', '', '车辆被盗', '1', '', '', '26', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c93', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '27', '', '车辆非法点火', '1', '', '', '27', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c94', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '28', '', '车辆非法位移', '1', '', '', '28', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c95', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '29', '', '碰撞预警', '1', '', '', '29', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c96', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '30', '', '侧翻预警', '1', '', '', '30', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('559237f003c70e36b1133c97', '559237f003c70e36b1133c7a', '5591fa9c03c70e360cb98cec', '31', '', '非法开门报警', '1', '', '', '31', '0', '0', '2015-07-15 21:22:53', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c98', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '83', '', '报警拍摄存储标志', '4', '', '0053', '0', '0', '0', '2015-07-15 21:23:48', '', '与位置信息汇报消息中的报警标志相对应,相应 位为 1 则对相应报警时拍的照片进行存储,否则实时上传');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c99', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '0', '', '紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9a', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '1', '', '超速报警', '1', '', '', '1', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9b', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '2', '', '疲劳驾驶', '1', '', '', '2', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9c', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '3', '', '危险预警', '1', '', '', '3', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9d', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '4', '', 'GNSS模块发生故障', '1', '', '', '4', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9e', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '5', '', 'GNSS天线未接或被剪断', '1', '', '', '5', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133c9f', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '6', '', 'GNSS天线短路', '1', '', '', '6', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca0', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '7', '', '终端主电源欠压', '1', '', '', '7', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca1', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '8', '', '终端主电源掉电', '1', '', '', '8', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca2', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '9', '', '终端LCD或显示器故障', '1', '', '', '9', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca3', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '10', '', 'TTS模块故障', '1', '', '', '10', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca4', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '11', '', '摄像头故障', '1', '', '', '11', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca5', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '12', '', '道路运输证 IC 卡模块故障', '1', '', '', '12', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca6', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '13', '', '超速预警', '1', '', '', '13', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca7', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '14', '', '疲劳驾驶预警', '1', '', '', '14', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca8', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '18', '', '当天累计驾驶超时', '1', '1', '', '18', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133ca9', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '19', '', '超时停车', '1', '', '', '19', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133caa', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '20', '', '进出区域', '1', '', '', '20', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cab', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '21', '', '进出路线', '1', '', '', '21', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cac', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '22', '', '路段行驶时间不足/过长', '1', '', '', '22', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cad', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '23', '', '路线偏离报警', '1', '', '', '23', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cae', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '24', '', '车辆 VSS 故障', '1', '', '', '24', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133caf', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '25', '', '车辆油量异常', '1', '', '', '25', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cb0', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '26', '', '车辆被盗', '1', '', '', '26', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cb1', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '27', '', '车辆非法点火', '1', '', '', '27', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cb2', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '28', '', '车辆非法位移', '1', '', '', '28', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387b03c70e36b1133cb3', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '29', '', '碰撞预警', '1', '', '', '29', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387c03c70e36b1133cb4', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '30', '', '侧翻预警', '1', '', '', '30', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('5592387c03c70e36b1133cb5', '5592387b03c70e36b1133c98', '5591fa9c03c70e360cb98cec', '31', '', '非法开门报警', '1', '', '', '31', '0', '0', '2015-07-15 21:23:37', '', '');
INSERT INTO `parameter` VALUES ('559238c003c70e36b1133cb6', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '84', '', '关键标志', '4', '', '0054', '0', '0', '0', '2015-07-15 21:24:34', '', '与位置信息汇报消息中的报警标志相对应,相应位为 1 则 对相应报警为关键报警');
INSERT INTO `parameter` VALUES ('559238c003c70e36b1133cb7', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '0', '', '紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c003c70e36b1133cb8', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '1', '', '超速报警', '1', '', '', '1', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c003c70e36b1133cb9', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '2', '', '疲劳驾驶', '1', '', '', '2', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c003c70e36b1133cba', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '3', '', '危险预警', '1', '', '', '3', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cbb', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '4', '', 'GNSS模块发生故障', '1', '', '', '4', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cbc', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '5', '', 'GNSS天线未接或被剪断', '1', '', '', '5', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cbd', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '6', '', 'GNSS天线短路', '1', '', '', '6', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cbe', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '7', '', '终端主电源欠压', '1', '', '', '7', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cbf', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '8', '', '终端主电源掉电', '1', '', '', '8', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc0', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '9', '', '终端LCD或显示器故障', '1', '', '', '9', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc1', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '10', '', 'TTS模块故障', '1', '', '', '10', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc2', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '11', '', '摄像头故障', '1', '', '', '11', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc3', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '12', '', '道路运输证 IC 卡模块故障', '1', '', '', '12', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc4', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '13', '', '超速预警', '1', '', '', '13', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc5', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '14', '', '疲劳驾驶预警', '1', '', '', '14', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc6', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '18', '', '当天累计驾驶超时', '1', '1', '', '18', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc7', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '19', '', '超时停车', '1', '', '', '19', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc8', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '20', '', '进出区域', '1', '', '', '20', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cc9', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '21', '', '进出路线', '1', '', '', '21', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cca', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '22', '', '路段行驶时间不足/过长', '1', '', '', '22', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133ccb', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '23', '', '路线偏离报警', '1', '', '', '23', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133ccc', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '24', '', '车辆 VSS 故障', '1', '', '', '24', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133ccd', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '25', '', '车辆油量异常', '1', '', '', '25', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cce', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '26', '', '车辆被盗', '1', '', '', '26', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133ccf', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '27', '', '车辆非法点火', '1', '', '', '27', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cd0', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '28', '', '车辆非法位移', '1', '', '', '28', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cd1', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '29', '', '碰撞预警', '1', '', '', '29', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cd2', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '30', '', '侧翻预警', '1', '', '', '30', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('559238c103c70e36b1133cd3', '559238c003c70e36b1133cb6', '5591fa9c03c70e360cb98cec', '31', '', '非法开门报警', '1', '', '', '31', '0', '0', '2015-07-15 21:24:22', '', '');
INSERT INTO `parameter` VALUES ('5592390d03c70e36b1133cd4', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '85', '', '最高速度', '1', '', '0055', '0', '0', '0', '2015-07-15 21:24:44', '120', '单位为公里每小时(km/h)');
INSERT INTO `parameter` VALUES ('5592393d03c70e36b1133cd5', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '86', '', '超速持续时间', '1', '', '0056', '0', '0', '0', '2015-07-15 21:24:49', '5', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('559239a303c70e36b1133cd6', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '87', '', '连续驾驶时间门限', '1', '', '0057', '0', '0', '0', '2015-07-15 21:24:53', '', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('559239c303c70e36b1133cd7', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '88', '', '当天累计驾驶时间门限', '1', '', '0058', '0', '0', '0', '2015-07-15 21:24:57', '', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('559239ec03c70e36b1133cd8', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '89', '', '最小休息时间', '1', '', '0059', '0', '0', '0', '2015-07-15 21:25:01', '600', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('55923a0a03c70e36b1133cd9', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '90', '', '最长停车时间', '1', '', '005A', '0', '0', '0', '2015-07-15 21:25:05', '', '单位为秒(s)');
INSERT INTO `parameter` VALUES ('55923a8103c70e36b1133cda', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '91', '', '超速报警预警差值', '1', '', '005B', '0', '0', '0', '2015-07-15 21:25:09', '0.5', '单位为 Km/h');
INSERT INTO `parameter` VALUES ('55923ac603c70e36b1133cdb', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '92', '', '疲劳驾驶预警差值', '1', '', '005C', '0', '0', '0', '2015-07-15 21:25:14', '1800', '单位为秒(s),>0');
INSERT INTO `parameter` VALUES ('55923b0f03c70e36b1133cdc', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '93', '', '碰撞报警参数设置', '6', '', '005D', '0', '0', '0', '2015-07-15 21:25:28', '', '');
INSERT INTO `parameter` VALUES ('55923b7d03c70e36b1133cdd', '55923b0f03c70e36b1133cdc', '5591fa9c03c70e360cb98cec', '1', 'time', '碰撞时间', '1', '', '', '0', '0', '0', '2015-07-15 21:26:59', '250', '单位(ms)');
INSERT INTO `parameter` VALUES ('55923be303c70e36b1133cde', '55923b0f03c70e36b1133cdc', '5591fa9c03c70e360cb98cec', '2', 'acceleration', '碰撞加速度', '1', '', '', '0', '0', '0', '2015-07-15 21:26:48', '10', '设置范围在:0-79 之间,默认为 10');
INSERT INTO `parameter` VALUES ('55923c0c03c70e36b1133cdf', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '94', '', '侧翻报警参数设置', '1', '', '005E', '0', '0', '0', '2015-07-15 21:27:06', '30', '侧翻角度,单位 1 度,默认为 30 度');
INSERT INTO `parameter` VALUES ('55923c5703c70e36b1133ce0', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '100', '', '定时拍照控制', '6', '', '0064', '0', '0', '0', '2015-07-15 21:27:16', '', '');
INSERT INTO `parameter` VALUES ('55923cbb03c70e36b1133ce1', '55923c5703c70e36b1133ce0', '5591fa9c03c70e360cb98cec', '1', 'switchs', '定时拍照控制位开关', '4', '', '', '0', '0', '0', '2015-07-16 12:23:12', '', '');
INSERT INTO `parameter` VALUES ('55923ce303c70e36b1133ce2', '55923c5703c70e36b1133ce0', '5591fa9c03c70e360cb98cec', '2', 'interval', '定时时间间隔', '1', '', '', '0', '0', '0', '2015-07-15 15:00:04', '0', '0~32767');
INSERT INTO `parameter` VALUES ('55923d0b03c70e36b1133ce3', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '0', '', '摄像通道1定时拍照开关标志', '1', '', '', '0', '0', '0', '2015-07-15 21:27:23', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('55923d2303c70e36b1133ce4', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '1', '', '摄像通道2定时拍照开关标志', '1', '', '', '1', '0', '0', '2015-07-15 21:27:27', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('55923d3003c70e36b1133ce5', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '2', '', '摄像通道3定时拍照开关标志', '1', '', '', '2', '0', '0', '2015-07-15 21:27:31', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('55923d5703c70e36b1133ce6', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '3', '', '摄像通道4定时拍照开关标志', '1', '', '', '3', '0', '0', '2015-07-15 21:27:35', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('55923d7903c70e36b1133ce7', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '4', '', '摄像通道5定时拍照开关标志', '1', '', '', '4', '0', '0', '2015-07-15 21:27:39', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('55923daa03c70e36b1133ce8', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '8', '', '摄像通道1定时拍照存储标志', '1', '', '', '8', '0', '0', '2015-07-15 21:27:42', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('55923daf03c70e36b1133ce9', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '9', '', '摄像通道2定时拍照存储标志', '1', '', '', '9', '0', '0', '2015-07-15 21:27:47', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('55923de603c70e36b1133cea', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '10', '', '摄像通道3定时拍照存储标志', '1', '', '', '10', '0', '0', '2015-07-15 21:27:52', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('55923df603c70e36b1133ceb', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '11', '', '摄像通道4定时拍照存储标志', '1', '', '', '11', '0', '0', '2015-07-15 21:27:56', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('55923e0503c70e36b1133cec', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '12', '', '摄像通道5定时拍照存储标志', '1', '', '', '12', '0', '0', '2015-07-15 21:28:01', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('559240ac03c70e36b152cf0d', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '101', '', '定距拍照控制', '6', '', '0065', '0', '0', '0', '2015-07-15 21:28:16', '', '');
INSERT INTO `parameter` VALUES ('559240ac03c70e36b152cf0f', '559240ac03c70e36b152cf0d', '5591fa9c03c70e360cb98cec', '2', 'interval', '定距距离间隔', '1', '', '', '0', '0', '0', '2015-07-16 15:54:40', '0', '0~32767');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf11', '559240ac03c70e36b152cf0d', '5591fa9c03c70e360cb98cec', '1', 'switchs', '定距拍照控制位开关', '4', '', '', '0', '0', '0', '2015-07-16 12:23:19', '', '');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf12', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '0', '', '摄像通道1定距拍照开关标志', '1', '', '', '0', '0', '0', '2015-07-15 21:28:21', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf13', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '1', '', '摄像通道2定距拍照开关标志', '1', '', '', '1', '0', '0', '2015-07-15 21:28:26', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf14', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '2', '', '摄像通道3定距拍照开关标志', '1', '', '', '2', '0', '0', '2015-07-15 21:28:29', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf15', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '3', '', '摄像通道4定距拍照开关标志', '1', '', '', '3', '0', '0', '2015-07-15 21:28:33', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf16', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '4', '', '摄像通道5定距拍照开关标志', '1', '', '', '4', '0', '0', '2015-07-15 21:28:37', '', '0:不允许; 1:允许');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf17', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '8', '', '摄像通道1定距拍照存储标志', '1', '', '', '8', '0', '0', '2015-07-15 21:28:42', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf18', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '9', '', '摄像通道2定距拍照存储标志', '1', '', '', '9', '0', '0', '2015-07-15 21:28:45', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf19', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '10', '', '摄像通道3定距拍照存储标志', '1', '', '', '10', '0', '0', '2015-07-15 21:28:49', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf1a', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '11', '', '摄像通道4定距拍照存储标志', '1', '', '', '11', '0', '0', '2015-07-15 21:28:54', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('559240cd03c70e36b152cf1b', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '12', '', '摄像通道5定距拍照存储标志', '1', '', '', '12', '0', '0', '2015-07-15 21:28:57', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('5592423003c70e36b152cf1e', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '112', '', '图像/视频质量', '1', '', '0070', '0', '0', '0', '2015-07-15 21:29:12', '1', '1-10,1 最好');
INSERT INTO `parameter` VALUES ('5592426203c70e36b152cf1f', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '113', '', '亮度', '1', '', '0071', '0', '0', '0', '2015-07-15 21:29:16', '127', '0-255');
INSERT INTO `parameter` VALUES ('5592429503c70e36b152cf20', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '114', '', '对比度', '1', '', '0072', '0', '0', '0', '2015-07-15 21:29:19', '65', '0-127');
INSERT INTO `parameter` VALUES ('559242a003c70e36b152cf21', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '115', '', '饱和度', '1', '', '0073', '0', '0', '0', '2015-07-15 21:29:23', '65', '0-127');
INSERT INTO `parameter` VALUES ('559242b203c70e36b152cf22', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '116', '', '色度', '1', '', '0074', '0', '0', '0', '2015-07-15 21:29:27', '127', '0-255');
INSERT INTO `parameter` VALUES ('5592431903c70e36b152cf23', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '128', '', '车辆里程表读数', '1', '', '0080', '0', '0', '0', '2015-07-15 21:29:31', '', '单位：km');
INSERT INTO `parameter` VALUES ('5592433903c70e36b152cf24', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '129', '', '车辆所在的省域 ID', '1', '', '0081', '0', '0', '0', '2015-07-15 21:29:36', '', '');
INSERT INTO `parameter` VALUES ('5592434703c70e36b152cf25', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '130', '', '车辆所在的市域ID', '1', '', '0082', '0', '0', '0', '2015-07-15 21:29:42', '', '');
INSERT INTO `parameter` VALUES ('5592435803c70e36b152cf26', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '131', '', '机动车号牌', '1', '', '0083', '0', '0', '0', '2015-07-15 21:29:47', '', '公安交通管理部门颁发的机动车号牌');
INSERT INTO `parameter` VALUES ('5592438903c70e36b152cf27', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '132', '', '车牌颜色', '2', '', '0084', '0', '0', '0', '2015-07-15 21:29:52', '', '按照 JT/T415-2006 的 5.4.12');
INSERT INTO `parameter` VALUES ('559248b803c70e36b152cf28', '5592438903c70e36b152cf27', '5591fa9c03c70e360cb98cec', '1', '', '蓝色', '1', '1', '', '0', '0', '0', '2015-07-15 21:29:57', '', '');
INSERT INTO `parameter` VALUES ('559248c803c70e36b152cf29', '5592438903c70e36b152cf27', '5591fa9c03c70e360cb98cec', '2', '', '黄色', '1', '2', '', '0', '0', '0', '2015-07-15 21:30:00', '', '');
INSERT INTO `parameter` VALUES ('559248cb03c70e36b152cf2a', '5592438903c70e36b152cf27', '5591fa9c03c70e360cb98cec', '3', '', '黑色', '1', '3', '', '0', '0', '0', '2015-07-15 21:30:04', '', '');
INSERT INTO `parameter` VALUES ('559248d903c70e36b152cf2b', '5592438903c70e36b152cf27', '5591fa9c03c70e360cb98cec', '4', '', '白色', '1', '4', '', '0', '0', '0', '2015-07-15 21:30:08', '', '');
INSERT INTO `parameter` VALUES ('559248ee03c70e36b152cf2c', '5592438903c70e36b152cf27', '5591fa9c03c70e360cb98cec', '9', '', '其他', '1', '9', '', '0', '0', '0', '2015-07-15 21:30:12', '', '');
INSERT INTO `parameter` VALUES ('5592494203c70e36b152cf2d', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '144', '', 'GNSS定位模式', '4', '', '0090', '0', '0', '0', '2015-07-15 21:30:19', '', '');
INSERT INTO `parameter` VALUES ('5592496103c70e36b152cf2e', '5592494203c70e36b152cf2d', '5591fa9c03c70e360cb98cec', '0', '', 'GPS定位', '1', '', '', '0', '0', '0', '2015-07-15 21:30:24', '', '');
INSERT INTO `parameter` VALUES ('5592496603c70e36b152cf2f', '5592494203c70e36b152cf2d', '5591fa9c03c70e360cb98cec', '1', '', '北斗定位', '1', '', '', '1', '0', '0', '2015-07-15 21:30:28', '', '');
INSERT INTO `parameter` VALUES ('5592498703c70e36b152cf30', '5592494203c70e36b152cf2d', '5591fa9c03c70e360cb98cec', '2', '', 'GLONASS定位', '1', '', '', '2', '0', '0', '2015-07-15 21:30:31', '', '');
INSERT INTO `parameter` VALUES ('5592499f03c70e36b152cf31', '5592494203c70e36b152cf2d', '5591fa9c03c70e360cb98cec', '3', '', 'Galileo定位', '1', '', '', '3', '0', '0', '2015-07-15 21:30:35', '', '');
INSERT INTO `parameter` VALUES ('55924a0003c70e36b152cf32', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '145', '', 'GNSS波特率', '2', '', '0091', '0', '0', '0', '2015-07-15 21:30:38', '', '');
INSERT INTO `parameter` VALUES ('55924a2403c70e36b152cf33', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '0', '', '4800', '1', '0', '', '0', '0', '0', '2015-07-15 21:30:43', '', '');
INSERT INTO `parameter` VALUES ('55924a2903c70e36b152cf34', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '1', '', '9600', '1', '1', '', '0', '0', '0', '2015-07-15 21:30:47', '', '');
INSERT INTO `parameter` VALUES ('55924a4303c70e36b152cf35', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '2', '', '19200', '1', '2', '', '0', '0', '0', '2015-07-15 21:30:51', '', '');
INSERT INTO `parameter` VALUES ('55924a8703c70e36b152cf36', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '3', '', '38400', '1', '3', '', '0', '0', '0', '2015-07-15 21:30:55', '', '');
INSERT INTO `parameter` VALUES ('55924ab203c70e36b152cf37', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '4', '', '57600', '1', '4', '', '0', '0', '0', '2015-07-15 21:30:59', '', '');
INSERT INTO `parameter` VALUES ('55924ac403c70e36b152cf38', '55924a0003c70e36b152cf32', '5591fa9c03c70e360cb98cec', '5', '', '115200', '1', '5', '', '0', '0', '0', '2015-07-15 21:31:03', '', '');
INSERT INTO `parameter` VALUES ('55928e8f03c70e36b152cf39', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '146', '', 'GNSS模块详细定位数据输出频率', '2', '', '0092', '0', '0', '0', '2015-07-15 21:31:09', '', '');
INSERT INTO `parameter` VALUES ('55928ead03c70e36b152cf3a', '55928e8f03c70e36b152cf39', '5591fa9c03c70e360cb98cec', '0', '', '500ms', '1', '0', '', '0', '0', '0', '2015-07-15 21:31:13', '', '');
INSERT INTO `parameter` VALUES ('55928eb403c70e36b152cf3b', '55928e8f03c70e36b152cf39', '5591fa9c03c70e360cb98cec', '1', '', '1000ms', '1', '1', '', '0', '0', '0', '2015-07-15 21:31:18', '', '');
INSERT INTO `parameter` VALUES ('55928ecb03c70e36b152cf3c', '55928e8f03c70e36b152cf39', '5591fa9c03c70e360cb98cec', '2', '', '2000ms', '1', '2', '', '0', '0', '0', '2015-07-15 21:31:22', '', '');
INSERT INTO `parameter` VALUES ('55928edd03c70e36b152cf3d', '55928e8f03c70e36b152cf39', '5591fa9c03c70e360cb98cec', '3', '', '3000ms', '1', '3', '', '0', '0', '0', '2015-07-15 21:31:26', '', '');
INSERT INTO `parameter` VALUES ('55928ef403c70e36b152cf3e', '55928e8f03c70e36b152cf39', '5591fa9c03c70e360cb98cec', '4', '', '4000ms', '1', '4', '', '0', '0', '0', '2015-07-15 21:31:30', '', '');
INSERT INTO `parameter` VALUES ('55928f3e03c70e36b152cf3f', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '147', '', 'GNSS模块详细定位数据采集频率', '1', '', '0093', '0', '0', '0', '2015-07-15 21:31:38', '1', '单位为秒,默认为 1');
INSERT INTO `parameter` VALUES ('55928fcc03c70e36b152cf40', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '148', '', 'GNSS模块详细定位数据上传方式', '2', '', '0094', '0', '0', '0', '2015-07-15 21:31:45', '1', '单位为秒,默认为 1');
INSERT INTO `parameter` VALUES ('55928ffc03c70e36b152cf41', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '0', '', '本地存储,不上传', '1', '0', '', '0', '0', '0', '2015-07-15 21:31:49', '', '');
INSERT INTO `parameter` VALUES ('5592900103c70e36b152cf42', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '1', '', '按时间间隔上传', '1', '1', '', '0', '0', '0', '2015-07-15 21:31:53', '', '');
INSERT INTO `parameter` VALUES ('5592901303c70e36b152cf43', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '2', '', '按距离间隔上传', '1', '2', '', '0', '0', '0', '2015-07-15 21:31:57', '', '');
INSERT INTO `parameter` VALUES ('5592902503c70e36b152cf44', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '11', '', '按累计时间上传,达到传输时间后自动停止上传', '1', '11', '', '0', '0', '0', '2015-07-15 21:32:01', '', '');
INSERT INTO `parameter` VALUES ('5592905e03c70e36b152cf45', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '12', '', '按累计距离上传,达到距离后自动停止上传', '1', '12', '', '0', '0', '0', '2015-07-15 21:32:06', '', '');
INSERT INTO `parameter` VALUES ('5592907603c70e36b152cf46', '55928fcc03c70e36b152cf40', '5591fa9c03c70e360cb98cec', '13', '', '按累计条数上传,达到上传条数后自动停止上传', '1', '13', '', '0', '0', '0', '2015-07-15 21:32:12', '', '');
INSERT INTO `parameter` VALUES ('5592911c03c70e36b152cf47', '5591faba03c70e360cb98ced', '5591fa9c03c70e360cb98cec', '149', '', 'GNSS 模块详细定位数据上传设置', '1', '', '0095', '0', '0', '0', '2015-07-15 21:32:19', '', '');
INSERT INTO `parameter` VALUES ('5592920e03c70e36b152cf4a', '', '559291b903c70e36b152cf49', '1', 'keys', '参数列表', '5', '', '', '0', '0', '0', '2015-07-15 19:31:18', '', '');
INSERT INTO `parameter` VALUES ('5592922803c70e36b152cf4b', '5592920e03c70e36b152cf4a', '559291b903c70e36b152cf49', '1', '', '参数项', '2', '', '', '0', '0', '0', '2015-07-15 19:31:23', '', '');
INSERT INTO `parameter` VALUES ('5592925103c70e36b152cf4c', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '1', '', '终端心跳发送间隔', '1', '0001', '', '0', '0', '0', '2015-07-15 19:31:31', '', '');
INSERT INTO `parameter` VALUES ('5592925403c70e36b152cf4d', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '2', '', 'TCP消息应答超时时间', '1', '0002', '', '0', '0', '0', '2015-07-15 19:31:36', '', '');
INSERT INTO `parameter` VALUES ('5592926703c70e36b152cf4e', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '3', '', 'TCP消息重传次数', '1', '0003', '', '0', '0', '0', '2015-07-15 19:31:40', '', '');
INSERT INTO `parameter` VALUES ('5592958403c70e36b152cf4f', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '4', '', 'UDP消息应答超时时间', '1', '0004', '', '0', '0', '0', '2015-07-15 19:31:44', '', '');
INSERT INTO `parameter` VALUES ('559295a503c70e36b152cf50', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '5', '', 'UDP消息重传次数', '1', '0005', '', '0', '0', '0', '2015-07-15 19:31:49', '', '');
INSERT INTO `parameter` VALUES ('559295bc03c70e36b152cf51', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '6', '', 'SMS消息应答超时时间', '1', '0006', '', '0', '0', '0', '2015-07-15 19:31:55', '', '');
INSERT INTO `parameter` VALUES ('559295db03c70e36b152cf52', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '7', '', 'SMS消息重传次数', '1', '0007', '', '0', '0', '0', '2015-07-15 19:32:00', '', '');
INSERT INTO `parameter` VALUES ('559295ff03c70e36b152cf53', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '16', '', '主服务器 APN,无线通信拨号访问点', '1', '0010', '', '0', '0', '0', '2015-07-15 19:32:04', '', '');
INSERT INTO `parameter` VALUES ('5592962503c70e36b152cf54', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '17', '', '主服务器无线通信拨号用户名', '1', '0011', '', '0', '0', '0', '2015-07-15 19:32:08', '', '');
INSERT INTO `parameter` VALUES ('5592963b03c70e36b152cf55', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '18', '', '主服务器无线通信拨号密码', '1', '0012', '', '0', '0', '0', '2015-07-15 19:32:12', '', '');
INSERT INTO `parameter` VALUES ('5592964c03c70e36b152cf56', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '19', '', '主服务器地址,IP 或域名', '1', '0013', '', '0', '0', '0', '2015-07-15 19:32:15', '', '');
INSERT INTO `parameter` VALUES ('5592965e03c70e36b152cf57', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '20', '', '备份服务器 APN,无线通信拨号访问点', '1', '0014', '', '0', '0', '0', '2015-07-15 19:32:19', '', '');
INSERT INTO `parameter` VALUES ('5592967203c70e36b152cf58', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '21', '', '备份服务器无线通信拨号用户名', '1', '0015', '', '0', '0', '0', '2015-07-15 19:32:24', '', '');
INSERT INTO `parameter` VALUES ('5592968603c70e36b152cf59', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '22', '', '备份服务器无线通信拨号密码', '1', '0016', '', '0', '0', '0', '2015-07-15 19:32:28', '', '');
INSERT INTO `parameter` VALUES ('5592969d03c70e36b152cf5a', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '23', '', '备份服务器地址,IP 或域名', '1', '0017', '', '0', '0', '0', '2015-07-15 19:32:32', '', '');
INSERT INTO `parameter` VALUES ('559296af03c70e36b152cf5b', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '24', '', '服务器TCP端口', '1', '0018', '', '0', '0', '0', '2015-07-15 19:32:35', '', '');
INSERT INTO `parameter` VALUES ('559296ce03c70e36b152cf5c', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '25', '', '服务器UDP端口', '1', '0019', '', '0', '0', '0', '2015-07-15 19:32:40', '', '');
INSERT INTO `parameter` VALUES ('559296e503c70e36b152cf5d', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '26', '', 'IC卡认证主服务器IP地址或域名', '1', '001A', '', '0', '0', '0', '2015-07-15 19:32:43', '', '');
INSERT INTO `parameter` VALUES ('5592970e03c70e36b152cf5e', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '27', '', 'IC卡认证主服务器TCP端口', '1', '001B', '', '0', '0', '0', '2015-07-15 19:32:48', '', '');
INSERT INTO `parameter` VALUES ('5592972c03c70e36b152cf5f', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '28', '', 'IC卡认证主服务器UDP端口', '1', '001C', '', '0', '0', '0', '2015-07-15 19:32:52', '', '');
INSERT INTO `parameter` VALUES ('5592975d03c70e36b152cf60', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '29', '', 'IC卡认证备份服务器IP地址或域名', '1', '001D', '', '0', '0', '0', '2015-07-15 19:32:56', '', '');
INSERT INTO `parameter` VALUES ('5592978a03c70e36b152cf61', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '32', '', '位置汇报策略', '1', '0020', '', '0', '0', '0', '2015-07-15 19:32:59', '', '');
INSERT INTO `parameter` VALUES ('5592978d03c70e36b152cf62', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '33', '', '位置汇报方案', '1', '0021', '', '0', '0', '0', '2015-07-15 19:33:03', '', '');
INSERT INTO `parameter` VALUES ('559297aa03c70e36b152cf63', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '34', '', '驾驶员未登录汇报时间间隔', '1', '0022', '', '0', '0', '0', '2015-07-15 19:33:08', '', '');
INSERT INTO `parameter` VALUES ('5592983903c70e36b152cf64', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '39', '', '休眠时汇报时间间隔', '1', '0027', '', '0', '0', '0', '2015-07-15 19:33:12', '', '');
INSERT INTO `parameter` VALUES ('559298d403c70e36b152cf65', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '40', '', '紧急报警时汇报时间间隔', '1', '0028', '', '0', '0', '0', '2015-07-15 19:33:15', '', '');
INSERT INTO `parameter` VALUES ('559298fb03c70e36b152cf66', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '41', '', '缺省时间汇报间隔', '1', '0029', '', '0', '0', '0', '2015-07-15 19:33:18', '', '');
INSERT INTO `parameter` VALUES ('5592990903c70e36b152cf67', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '44', '', '缺省距离汇报间隔', '1', '002C', '', '0', '0', '0', '2015-07-15 19:33:22', '', '');
INSERT INTO `parameter` VALUES ('5592994603c70e36b152cf68', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '45', '', '驾驶员未登录汇报距离间隔', '1', '002D', '', '0', '0', '0', '2015-07-15 19:33:26', '', '');
INSERT INTO `parameter` VALUES ('5592995b03c70e36b152cf69', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '46', '', '休眠时汇报距离间隔', '1', '002E', '', '0', '0', '0', '2015-07-15 19:33:32', '', '');
INSERT INTO `parameter` VALUES ('5592997403c70e36b152cf6a', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '47', '', '紧急报警时汇报距离间隔', '1', '002F', '', '0', '0', '0', '2015-07-15 19:33:35', '', '');
INSERT INTO `parameter` VALUES ('5592999c03c70e36b152cf6b', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '48', '', '拐点补传角度', '1', '0030', '', '0', '0', '0', '2015-07-15 19:33:39', '', '');
INSERT INTO `parameter` VALUES ('559299ac03c70e36b152cf6c', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '49', '', '电子围栏半径', '1', '0031', '', '0', '0', '0', '2015-07-15 19:33:42', '', '');
INSERT INTO `parameter` VALUES ('559299d003c70e36b152cf6d', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '64', '', '监控平台电话号码', '1', '0040', '', '0', '0', '0', '2015-07-15 19:33:46', '', '');
INSERT INTO `parameter` VALUES ('559299fe03c70e36b152cf6e', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '65', '', '复位电话号码', '1', '0041', '', '0', '0', '0', '2015-07-15 19:33:50', '', '');
INSERT INTO `parameter` VALUES ('55929a0b03c70e36b152cf6f', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '66', '', '恢复出厂设置电话号码', '1', '0042', '', '0', '0', '0', '2015-07-15 19:33:55', '', '');
INSERT INTO `parameter` VALUES ('55929a2503c70e36b152cf70', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '67', '', '监控平台SMS电话号码', '1', '0043', '', '0', '0', '0', '2015-07-15 19:33:59', '', '');
INSERT INTO `parameter` VALUES ('55929a3d03c70e36b152cf71', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '68', '', '接收终端SMS文本报警号码', '1', '0044', '', '0', '0', '0', '2015-07-15 19:34:06', '', '');
INSERT INTO `parameter` VALUES ('55929a5603c70e36b152cf72', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '69', '', '终端电话接听策略', '1', '0045', '', '0', '0', '0', '2015-07-15 19:34:02', '', '');
INSERT INTO `parameter` VALUES ('55929a8c03c70e36b152cf73', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '70', '', '每次最长通话时间', '1', '0046', '', '0', '0', '0', '2015-07-15 19:34:09', '', '');
INSERT INTO `parameter` VALUES ('55929aa403c70e36b152cf74', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '71', '', '当月最长通话时间', '1', '0047', '', '0', '0', '0', '2015-07-15 19:34:13', '', '');
INSERT INTO `parameter` VALUES ('55929ab103c70e36b152cf75', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '72', '', '监听电话号码', '1', '0048', '', '0', '0', '0', '2015-07-15 19:34:17', '', '');
INSERT INTO `parameter` VALUES ('55929acd03c70e36b152cf76', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '73', '', '监管平台特权短信号码', '1', '0049', '', '0', '0', '0', '2015-07-15 19:34:22', '', '');
INSERT INTO `parameter` VALUES ('55929adf03c70e36b152cf77', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '80', '', '报警屏蔽字', '1', '0050', '', '0', '0', '0', '2015-07-15 19:34:25', '', '');
INSERT INTO `parameter` VALUES ('55929b0c03c70e36b152cf78', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '81', '', '报警发送文本SMS开关', '1', '0051', '', '0', '0', '0', '2015-07-15 19:34:30', '', '');
INSERT INTO `parameter` VALUES ('55929b2503c70e36b152cf79', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '82', '', '报警拍摄开关', '1', '0052', '', '0', '0', '0', '2015-07-15 19:34:34', '', '');
INSERT INTO `parameter` VALUES ('55929b3e03c70e36b152cf7a', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '83', '', '报警拍摄存储标志', '1', '0053', '', '0', '0', '0', '2015-07-15 19:34:38', '', '');
INSERT INTO `parameter` VALUES ('55929b5603c70e36b152cf7b', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '84', '', '关键标志', '1', '0054', '', '0', '0', '0', '2015-07-15 19:34:43', '', '');
INSERT INTO `parameter` VALUES ('55929b7303c70e36b152cf7c', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '85', '', '最高速度', '1', '0055', '', '0', '0', '0', '2015-07-15 19:34:46', '', '');
INSERT INTO `parameter` VALUES ('55929b8a03c70e36b152cf7d', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '86', '', '超速持续时间', '1', '0056', '', '0', '0', '0', '2015-07-15 19:34:50', '', '');
INSERT INTO `parameter` VALUES ('55929b9d03c70e36b152cf7e', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '87', '', '连续驾驶时间门限', '1', '0057', '', '0', '0', '0', '2015-07-15 19:34:54', '', '');
INSERT INTO `parameter` VALUES ('55929bb203c70e36b152cf7f', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '88', '', '当天累计驾驶时间门限', '1', '0058', '', '0', '0', '0', '2015-07-15 19:34:58', '', '');
INSERT INTO `parameter` VALUES ('55929bdc03c70e36b152cf80', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '89', '', '最小休息时间', '1', '0059', '', '0', '0', '0', '2015-07-15 19:35:02', '', '');
INSERT INTO `parameter` VALUES ('55929bed03c70e36b152cf81', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '90', '', '最长停车时间', '1', '005A', '', '0', '0', '0', '2015-07-15 19:35:05', '', '');
INSERT INTO `parameter` VALUES ('55929c0403c70e36b152cf82', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '91', '', '超速报警预警差值', '1', '005B', '', '0', '0', '0', '2015-07-15 19:35:09', '', '');
INSERT INTO `parameter` VALUES ('55929c1a03c70e36b152cf83', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '92', '', '疲劳驾驶预警差值', '1', '005C', '', '0', '0', '0', '2015-07-15 19:35:13', '', '');
INSERT INTO `parameter` VALUES ('55929c2e03c70e36b152cf84', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '93', '', '碰撞报警参数设置', '1', '005D', '', '0', '0', '0', '2015-07-15 19:35:17', '', '');
INSERT INTO `parameter` VALUES ('55929c4603c70e36b152cf85', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '94', '', '侧翻报警参数设置', '1', '005E', '', '0', '0', '0', '2015-07-15 19:35:20', '', '');
INSERT INTO `parameter` VALUES ('55929c6e03c70e36b152cf86', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '100', '', '定时拍照控制', '1', '0064', '', '0', '0', '0', '2015-07-15 19:35:24', '', '');
INSERT INTO `parameter` VALUES ('55929c9a03c70e36b152cf87', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '101', '', '定距拍照控制', '1', '0065', '', '0', '0', '0', '2015-07-15 19:35:28', '', '');
INSERT INTO `parameter` VALUES ('55929ca703c70e36b152cf88', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '112', '', '图像/视频质量', '1', '0070', '', '0', '0', '0', '2015-07-15 19:35:34', '', '');
INSERT INTO `parameter` VALUES ('55929cd503c70e36b152cf89', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '113', '', '亮度', '1', '0071', '', '0', '0', '0', '2015-07-15 19:35:38', '', '');
INSERT INTO `parameter` VALUES ('55929cef03c70e36b152cf8a', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '114', '', '对比度', '1', '0072', '', '0', '0', '0', '2015-07-15 19:35:42', '', '');
INSERT INTO `parameter` VALUES ('55929d0203c70e36b152cf8b', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '115', '', '饱和度', '1', '0073', '', '0', '0', '0', '2015-07-15 19:37:03', '', '');
INSERT INTO `parameter` VALUES ('55929d1603c70e36b152cf8c', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '116', '', '色度', '1', '0074', '', '0', '0', '0', '2015-07-15 19:36:59', '', '');
INSERT INTO `parameter` VALUES ('55929d5003c70e36b152cf8d', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '128', '', '车辆里程表读数', '1', '0080', '', '0', '0', '0', '2015-07-15 19:36:40', '', '');
INSERT INTO `parameter` VALUES ('55929d7303c70e36b152cf8e', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '129', '', '车辆所在的省域ID', '1', '0081', '', '0', '0', '0', '2015-07-15 19:36:36', '', '');
INSERT INTO `parameter` VALUES ('55929d8503c70e36b152cf8f', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '130', '', '车辆所在的市域ID', '1', '0082', '', '0', '0', '0', '2015-07-15 19:36:26', '', '');
INSERT INTO `parameter` VALUES ('55929d9d03c70e36b152cf90', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '131', '', '机动车号牌', '1', '0083', '', '0', '0', '0', '2015-07-15 19:36:19', '', '');
INSERT INTO `parameter` VALUES ('55929db203c70e36b152cf91', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '132', '', '车牌颜色', '1', '0084', '', '0', '0', '0', '2015-07-15 19:36:15', '', '');
INSERT INTO `parameter` VALUES ('55929dc703c70e36b152cf92', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '144', '', 'GNSS定位模式', '1', '0090', '', '0', '0', '0', '2015-07-15 19:36:11', '', '');
INSERT INTO `parameter` VALUES ('55929dff03c70e36b152cf93', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '145', '', 'GNSS波特率', '1', '0091', '', '0', '0', '0', '2015-07-15 19:36:08', '', '');
INSERT INTO `parameter` VALUES ('55929e1b03c70e36b152cf94', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '146', '', 'GNSS模块详细定位数据输出频率', '1', '0092', '', '0', '0', '0', '2015-07-15 19:36:04', '', '');
INSERT INTO `parameter` VALUES ('55929e3303c70e36b152cf95', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '147', '', 'GNSS模块详细定位数据采集频率', '1', '0093', '', '0', '0', '0', '2015-07-15 19:36:01', '', '');
INSERT INTO `parameter` VALUES ('55929e5803c70e36b152cf96', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '148', '', 'GNSS模块详细定位数据上传方式', '1', '0094', '', '0', '0', '0', '2015-07-15 19:35:57', '', '');
INSERT INTO `parameter` VALUES ('55929e6803c70e36b152cf97', '5592922803c70e36b152cf4b', '559291b903c70e36b152cf49', '149', '', 'GNSS 模块详细定位数据上传设置', '1', '0095', '', '0', '0', '0', '2015-07-15 19:35:53', '', '');
INSERT INTO `parameter` VALUES ('55929fb803c70e36b152cf9a', '', '55929ee603c70e36b152cf99', '1', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-07-15 19:30:23', '', '');
INSERT INTO `parameter` VALUES ('55929fe403c70e36b152cf9d', '55929fb803c70e36b152cf9a', '55929ee603c70e36b152cf99', '3', '', '终端关机', '1', '3', '', '0', '0', '0', '2015-07-15 19:30:28', '', '');
INSERT INTO `parameter` VALUES ('55929ff503c70e36b152cf9e', '55929fb803c70e36b152cf9a', '55929ee603c70e36b152cf99', '4', '', '终端复位', '1', '4', '', '0', '0', '0', '2015-07-15 19:30:33', '', '');
INSERT INTO `parameter` VALUES ('5592a00803c70e36b152cf9f', '55929fb803c70e36b152cf9a', '55929ee603c70e36b152cf99', '5', '', '恢复出厂设置', '1', '5', '', '0', '0', '0', '2015-07-15 19:30:38', '', '');
INSERT INTO `parameter` VALUES ('5592a02103c70e36b152cfa0', '55929fb803c70e36b152cf9a', '55929ee603c70e36b152cf99', '6', '', '关闭数据通信', '1', '6', '', '0', '0', '0', '2015-07-15 19:30:42', '', '');
INSERT INTO `parameter` VALUES ('5592a03903c70e36b152cfa1', '55929fb803c70e36b152cf9a', '55929ee603c70e36b152cf99', '7', '', '关闭所有无线通信', '1', '6', '', '0', '0', '0', '2015-07-15 19:30:47', '', '');
INSERT INTO `parameter` VALUES ('5594836c1d19b602cb089d26', '', '559483431d19b602cb089d25', '1', 'interval', '时间间隔', '1', '', '', '0', '0', '0', '2015-10-30 10:26:00', '0', '单位为秒(s),0 则停止跟踪。停止跟踪无需带后继字段');
INSERT INTO `parameter` VALUES ('5594838a1d19b602cb089d27', '', '559483431d19b602cb089d25', '2', 'seconds', '位置跟踪有效期', '1', '', '', '0', '0', '0', '2015-10-30 10:26:19', '300', '单位为秒(s),终端在接收到位置跟踪控制消息后, 在有效期截止时间之前,依据消息中的时间间隔发送位置汇报');
INSERT INTO `parameter` VALUES ('559484431d19b602cb089d29', '', '559484281d19b602cb089d28', '1', 'alarmId', '报警消息流水号', '1', '', '', '0', '0', '0', '2015-07-15 17:52:09', '0', '需人工确认的报警消息流水号,0 表示该报警类型所有 消息');
INSERT INTO `parameter` VALUES ('5594845e1d19b602cb089d2a', '', '559484281d19b602cb089d28', '2', 'type', '人工确认报警类型', '4', '', '', '0', '0', '0', '2015-07-15 17:52:19', '', '');
INSERT INTO `parameter` VALUES ('559484961d19b602cb089d2b', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '0', '', '确认紧急报警', '1', '', '', '0', '0', '0', '2015-07-15 17:52:31', '', '');
INSERT INTO `parameter` VALUES ('559484af1d19b602cb089d2c', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '3', '', '确认危险预警', '1', '', '', '3', '0', '0', '2015-07-15 17:52:35', '', '');
INSERT INTO `parameter` VALUES ('559484b21d19b602cb089d2d', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '20', '', '确认进出区域报警', '1', '', '', '20', '0', '0', '2015-07-15 17:52:39', '', '');
INSERT INTO `parameter` VALUES ('559484c81d19b602cb089d2e', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '21', '', '确认进出路线报警', '1', '', '', '21', '0', '0', '2015-07-15 17:52:43', '', '');
INSERT INTO `parameter` VALUES ('559484dd1d19b602cb089d2f', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '22', '', '确认路段行驶时间不足/过程报警', '1', '', '', '22', '0', '0', '2015-07-15 17:52:46', '', '');
INSERT INTO `parameter` VALUES ('559484ee1d19b602cb089d30', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '27', '', '确认车辆非法点火报警', '1', '', '', '27', '0', '0', '2015-07-15 17:52:51', '', '');
INSERT INTO `parameter` VALUES ('559485081d19b602cb089d31', '5594845e1d19b602cb089d2a', '559484281d19b602cb089d28', '28', '', '确认车辆非法位移报警', '1', '', '', '28', '0', '0', '2015-07-15 17:52:56', '', '');
INSERT INTO `parameter` VALUES ('559485a61d19b602cb089d33', '', '5594857e1d19b602cb089d32', '1', 'flag', '标志', '4', '', '', '0', '0', '0', '2015-07-15 17:50:37', '', '');
INSERT INTO `parameter` VALUES ('559485ae1d19b602cb089d34', '559485a61d19b602cb089d33', '5594857e1d19b602cb089d32', '0', '', '紧急', '1', '', '', '0', '0', '0', '2015-07-15 17:50:41', '', '');
INSERT INTO `parameter` VALUES ('559485b81d19b602cb089d35', '559485a61d19b602cb089d33', '5594857e1d19b602cb089d32', '2', '', '终端显示器显示', '1', '', '', '2', '0', '0', '2015-07-15 17:50:45', '', '');
INSERT INTO `parameter` VALUES ('559485c11d19b602cb089d36', '559485a61d19b602cb089d33', '5594857e1d19b602cb089d32', '3', '', '终端TTS播读', '1', '', '', '3', '0', '0', '2015-07-15 17:50:48', '', '');
INSERT INTO `parameter` VALUES ('559485d01d19b602cb089d37', '559485a61d19b602cb089d33', '5594857e1d19b602cb089d32', '4', '', '广告屏显示', '1', '', '', '4', '0', '0', '2015-07-15 17:50:51', '', '');
INSERT INTO `parameter` VALUES ('559486131d19b602cb089d38', '559485a61d19b602cb089d33', '5594857e1d19b602cb089d32', '5', '', 'CAN故障码信息/中心导航信息', '1', '', '', '5', '0', '0', '2015-07-15 17:50:55', '', '');
INSERT INTO `parameter` VALUES ('559486731d19b602cb089d39', '', '5594857e1d19b602cb089d32', '2', 'message', '文本信息', '1', '', '', '0', '8', '130', '2015-07-15 17:51:09', '', '');
INSERT INTO `parameter` VALUES ('559488621d19b602cb089d3b', '', '559488501d19b602cb089d3a', '1', 'flag', '标志', '4', '', '', '0', '0', '0', '2015-07-15 17:48:55', '', '');
INSERT INTO `parameter` VALUES ('559488771d19b602cb089d3c', '559488621d19b602cb089d3b', '559488501d19b602cb089d3a', '0', '', '紧急', '1', '', '', '0', '0', '0', '2015-07-15 17:48:59', '', '');
INSERT INTO `parameter` VALUES ('5594887b1d19b602cb089d3d', '559488621d19b602cb089d3b', '559488501d19b602cb089d3a', '3', '', '终端TTS播读', '1', '', '', '3', '0', '0', '2015-07-15 17:49:02', '', '');
INSERT INTO `parameter` VALUES ('559488991d19b602cb089d3e', '559488621d19b602cb089d3b', '559488501d19b602cb089d3a', '4', '', '广告屏显示', '1', '', '', '4', '0', '0', '2015-07-15 17:49:07', '', '');
INSERT INTO `parameter` VALUES ('559488d81d19b602cb089d3f', '', '559488501d19b602cb089d3a', '2', 'question', '问题', '1', '', '', '0', '0', '500', '2015-07-15 17:49:22', '', '');
INSERT INTO `parameter` VALUES ('559488f61d19b602cb089d40', '', '559488501d19b602cb089d3a', '3', 'answers', '候选答案列表', '5', '', '', '0', '0', '0', '2015-07-15 17:49:35', '', '');
INSERT INTO `parameter` VALUES ('559489271d19b602cb089d41', '559488f61d19b602cb089d40', '559488501d19b602cb089d3a', '1', '', '候选答案项', '6', '', '', '0', '0', '0', '2015-07-15 17:49:40', '', '');
INSERT INTO `parameter` VALUES ('559489481d19b602cb089d42', '559489271d19b602cb089d41', '559488501d19b602cb089d3a', '1', 'id', '答案ID', '1', '', '', '0', '0', '0', '2015-07-15 17:49:52', '', '');
INSERT INTO `parameter` VALUES ('559489521d19b602cb089d43', '559489271d19b602cb089d41', '559488501d19b602cb089d3a', '2', 'content', '答案内容', '1', '', '', '0', '0', '0', '2015-07-15 17:50:06', '', '');
INSERT INTO `parameter` VALUES ('55948a711d19b602cb089d45', '', '55948a611d19b602cb089d44', '1', 'type', '设置类型', '2', '', '', '0', '0', '0', '2015-07-15 17:47:08', '', '');
INSERT INTO `parameter` VALUES ('55948a871d19b602cb089d46', '55948a711d19b602cb089d45', '55948a611d19b602cb089d44', '0', '', '删除终端全部信息项', '1', '0', '', '0', '0', '0', '2015-07-15 17:47:12', '', '');
INSERT INTO `parameter` VALUES ('55948a8a1d19b602cb089d47', '55948a711d19b602cb089d45', '55948a611d19b602cb089d44', '1', '', '更新菜单', '1', '1', '', '0', '0', '0', '2015-07-15 17:47:16', '', '');
INSERT INTO `parameter` VALUES ('55948aa11d19b602cb089d48', '55948a711d19b602cb089d45', '55948a611d19b602cb089d44', '2', '', '追加菜单', '1', '2', '', '0', '0', '0', '2015-07-15 17:47:20', '', '');
INSERT INTO `parameter` VALUES ('55948ab11d19b602cb089d49', '55948a711d19b602cb089d45', '55948a611d19b602cb089d44', '3', '', '修改菜单', '1', '3', '', '0', '0', '0', '2015-07-15 17:47:24', '', '');
INSERT INTO `parameter` VALUES ('55948ae11d19b602cb089d4a', '', '55948a611d19b602cb089d44', '2', 'menus', '信息项列表', '5', '', '', '0', '0', '0', '2015-07-15 17:47:37', '', '');
INSERT INTO `parameter` VALUES ('55948af01d19b602cb089d4b', '55948ae11d19b602cb089d4a', '55948a611d19b602cb089d44', '1', '', '信息项', '6', '', '', '0', '0', '0', '2015-07-15 17:47:48', '', '');
INSERT INTO `parameter` VALUES ('55948b331d19b602cb089d4c', '55948af01d19b602cb089d4b', '55948a611d19b602cb089d44', '1', 'id', '编号', '1', '', '', '0', '0', '0', '2015-07-15 17:48:04', '', '信息类型，若终端已有同类型的信息项,则被覆盖');
INSERT INTO `parameter` VALUES ('55948b361d19b602cb089d4d', '55948af01d19b602cb089d4b', '55948a611d19b602cb089d44', '2', 'content', '名称', '1', '', '', '0', '0', '0', '2015-07-15 17:48:20', '', '信息名称');
INSERT INTO `parameter` VALUES ('5595f11a1d19b60586c59082', '', '5595f0e71d19b60586c59081', '1', 'command', '终端控制命令', '2', '', '', '0', '0', '0', '2015-07-15 17:43:47', '', '');
INSERT INTO `parameter` VALUES ('5595f12a1d19b60586c59083', '5595f11a1d19b60586c59082', '5595f0e71d19b60586c59081', '1', '', '无线升级', '1', '1', '', '0', '0', '0', '2015-07-15 17:43:51', '', '');
INSERT INTO `parameter` VALUES ('5595f1661d19b60586c59084', '', '5595f0e71d19b60586c59081', '2', 'url', 'URL地址', '1', '', '', '0', '0', '500', '2015-07-15 17:44:23', '', '完整 URL 地址');
INSERT INTO `parameter` VALUES ('5595f16c1d19b60586c59085', '', '5595f0e71d19b60586c59081', '3', 'apn', '拨号点名称', '1', '', '', '0', '0', '0', '2015-07-15 17:44:31', '', '一般为服务器 APN,无线通信拨号访问点,若网络制式为 CDMA,则该值 为 PPP 连接拨号号码');
INSERT INTO `parameter` VALUES ('5595f1961d19b60586c59086', '', '5595f0e71d19b60586c59081', '4', 'account', '拨号用户名', '1', '', '', '0', '0', '0', '2015-07-15 17:44:40', '', '服务器无线通信拨号用户名');
INSERT INTO `parameter` VALUES ('5595f1b21d19b60586c59087', '', '5595f0e71d19b60586c59081', '5', 'password', '拨号密码', '1', '', '', '0', '0', '0', '2015-07-15 17:44:55', '', '服务器无线通信拨号密码');
INSERT INTO `parameter` VALUES ('5595f1cf1d19b60586c59088', '', '5595f0e71d19b60586c59081', '6', 'address', '地址', '1', '', '', '0', '0', '300', '2015-07-15 17:45:05', '', '服务器地址,IP 或域名');
INSERT INTO `parameter` VALUES ('5595f2021d19b60586c59089', '', '5595f0e71d19b60586c59081', '7', 'tcpPort', 'TCP端口', '1', '', '', '0', '0', '0', '2015-07-15 17:45:14', '', '服务器 TCP 端口');
INSERT INTO `parameter` VALUES ('5595f2201d19b60586c5908a', '', '5595f0e71d19b60586c59081', '8', 'udpPort', 'UDP端口', '1', '', '', '0', '0', '0', '2015-07-15 17:45:27', '', '服务器 UDP 端口');
INSERT INTO `parameter` VALUES ('5595f2411d19b60586c5908b', '', '5595f0e71d19b60586c59081', '9', 'factoryId', '制造商ID', '1', '', '', '0', '0', '0', '2015-07-15 17:45:43', '', '终端制造商编码');
INSERT INTO `parameter` VALUES ('5595f25d1d19b60586c5908c', '', '5595f0e71d19b60586c59081', '10', 'hardwareVer', '硬件版本', '1', '', '', '0', '0', '0', '2015-07-15 17:45:59', '', '终端的硬件版本号,由制造商自定');
INSERT INTO `parameter` VALUES ('5595f2831d19b60586c5908d', '', '5595f0e71d19b60586c59081', '11', 'firmwareVer', '固件版本', '1', '', '', '0', '0', '0', '2015-07-15 17:46:13', '', '终端的固件版本号,由制造商自定');
INSERT INTO `parameter` VALUES ('5595f2ae1d19b60586c5908e', '', '5595f0e71d19b60586c59081', '12', 'timeout', '连接到指定服务器时限', '1', '', '', '0', '0', '0', '2015-07-15 17:46:23', '', '单位:分(min),值非 0 表示在终端接收到升级或连接指定服务器指令 后的有效期截止前,终端应连回原地址。若值为 0,则表示一直连接指 定服务器');
INSERT INTO `parameter` VALUES ('5595f5f81d19b60586c59090', '', '5595f5e51d19b60586c5908f', '1', 'command', '终端控制命令', '2', '', '', '0', '0', '0', '2015-07-15 17:41:35', '', '');
INSERT INTO `parameter` VALUES ('5595f6091d19b60586c59091', '5595f5f81d19b60586c59090', '5595f5e51d19b60586c5908f', '2', '', '控制终端连接指定服务器', '1', '2', '', '0', '0', '0', '2015-07-15 17:40:59', '', '');
INSERT INTO `parameter` VALUES ('5595f61f1d19b60586c59092', '', '5595f5e51d19b60586c5908f', '2', 'flag', '连接控制', '2', '', '', '0', '0', '0', '2015-07-15 17:41:41', '', '');
INSERT INTO `parameter` VALUES ('5595f6811d19b60586c59093', '5595f61f1d19b60586c59092', '5595f5e51d19b60586c5908f', '0', '', '切换到指定监管平台服务器,连接到该服务器后即进入应急状态', '1', '0', '', '0', '0', '0', '2015-07-15 17:41:47', '', '');
INSERT INTO `parameter` VALUES ('5595f6971d19b60586c59094', '5595f61f1d19b60586c59092', '5595f5e51d19b60586c5908f', '1', '', '切换回原缺省监控平台服务器,并恢复正常状态', '1', '1', '', '0', '0', '0', '2015-07-15 17:41:51', '', '');
INSERT INTO `parameter` VALUES ('5595f6bb1d19b60586c59095', '', '5595f5e51d19b60586c5908f', '3', 'authenticationCode', '监管平台鉴权码', '1', '', '', '0', '0', '350', '2015-07-15 17:42:06', '', '监管平台下发的鉴权码,仅用于终端连接到监管平台之后的鉴权,终端 连接回原监控平台还用原鉴权码');
INSERT INTO `parameter` VALUES ('5595f6be1d19b60586c59096', '', '5595f5e51d19b60586c5908f', '4', 'apn', '拨号点名称', '1', '', '', '0', '0', '0', '2015-07-15 17:42:20', '', '一般为服务器 APN,无线通信拨号访问点,若网络制式为 CDMA,则该值 为 PPP 连接拨号号码');
INSERT INTO `parameter` VALUES ('5595fa1e1d19b60586c59097', '', '5595f5e51d19b60586c5908f', '5', 'account', '拨号用户名', '1', '', '', '0', '0', '0', '2015-07-15 17:42:36', '', '服务器无线通信拨号用户名');
INSERT INTO `parameter` VALUES ('5595fa4a1d19b60586c59098', '', '5595f5e51d19b60586c5908f', '6', 'password', '拨号密码', '1', '', '', '0', '0', '0', '2015-07-15 17:42:49', '', '服务器无线通信拨号密码');
INSERT INTO `parameter` VALUES ('5595fa611d19b60586c59099', '', '5595f5e51d19b60586c5908f', '7', 'address', '地址', '1', '', '', '0', '0', '0', '2015-07-15 17:42:58', '', '服务器地址,IP 或域名');
INSERT INTO `parameter` VALUES ('5595fa861d19b60586c5909a', '', '5595f5e51d19b60586c5908f', '8', 'tcpPort', 'TCP 端口', '1', '', '', '0', '0', '0', '2015-07-15 17:43:15', '', '服务器 TCP 端口');
INSERT INTO `parameter` VALUES ('5595faa41d19b60586c5909b', '', '5595f5e51d19b60586c5908f', '9', 'udpPort', 'UDP 端口', '1', '', '', '0', '0', '0', '2015-07-15 17:43:24', '', '服务器 UDP 端口');
INSERT INTO `parameter` VALUES ('5595fabe1d19b60586c5909c', '', '5595f5e51d19b60586c5908f', '10', 'timeout', '连接到指定服务器时限', '1', '', '', '0', '0', '0', '2015-07-15 17:43:36', '', '单位:分(min),值非 0 表示在终端接收到升级或连接指定服务器指令 后的有效期截止前,终端应连回原地址。若值为 0,则表示一直连接指 定服务器');
INSERT INTO `parameter` VALUES ('559663df1d19b60636d3afeb', '', '559663ce1d19b60636d3afea', '1', 'flag', '标志', '2', '', '', '0', '0', '0', '2015-07-15 17:39:42', '', '');
INSERT INTO `parameter` VALUES ('559663ec1d19b60636d3afec', '559663df1d19b60636d3afeb', '559663ce1d19b60636d3afea', '0', '', '普通通话', '1', '0', '', '0', '0', '0', '2015-07-15 17:39:47', '', '');
INSERT INTO `parameter` VALUES ('559663ee1d19b60636d3afed', '559663df1d19b60636d3afeb', '559663ce1d19b60636d3afea', '1', '', '监听', '1', '1', '', '0', '0', '0', '2015-07-15 17:39:51', '', '');
INSERT INTO `parameter` VALUES ('559664111d19b60636d3afee', '', '559663ce1d19b60636d3afea', '2', 'phone', '电话号码', '1', '', '', '0', '0', '0', '2015-07-15 17:40:03', '', '最长为 20 字');
INSERT INTO `parameter` VALUES ('559664a71d19b60636d3aff0', '', '559664931d19b60636d3afef', '1', 'type', '类型', '2', '', '', '0', '0', '0', '2015-07-15 16:11:00', '', '');
INSERT INTO `parameter` VALUES ('559664b41d19b60636d3aff1', '559664a71d19b60636d3aff0', '559664931d19b60636d3afef', '0', '', '全部删除', '1', '0', '', '0', '0', '0', '2015-07-15 17:38:17', '', '');
INSERT INTO `parameter` VALUES ('559664b81d19b60636d3aff2', '559664a71d19b60636d3aff0', '559664931d19b60636d3afef', '1', '', '更新电话本', '1', '1', '', '0', '0', '0', '2015-07-15 17:38:20', '', '');
INSERT INTO `parameter` VALUES ('559664d51d19b60636d3aff3', '559664a71d19b60636d3aff0', '559664931d19b60636d3afef', '2', '', '追加电话本', '1', '2', '', '0', '0', '0', '2015-07-15 17:38:23', '', '');
INSERT INTO `parameter` VALUES ('5596652a1d19b60636d3aff4', '559664a71d19b60636d3aff0', '559664931d19b60636d3afef', '3', '', '修改电话本', '1', '2', '', '0', '0', '0', '2015-07-15 17:38:27', '', '');
INSERT INTO `parameter` VALUES ('559665691d19b60636d3aff5', '', '559664931d19b60636d3afef', '2', 'list', '联系人列表', '5', '', '', '0', '0', '0', '2015-07-15 16:11:16', '', '');
INSERT INTO `parameter` VALUES ('559665881d19b60636d3aff6', '559665691d19b60636d3aff5', '559664931d19b60636d3afef', '1', '', '联系人项', '6', '', '', '0', '0', '0', '2015-07-15 17:38:43', '', '');
INSERT INTO `parameter` VALUES ('559665971d19b60636d3aff7', '559665881d19b60636d3aff6', '559664931d19b60636d3afef', '1', 'flag', '标志', '2', '', '', '0', '0', '0', '2015-07-15 16:12:02', '', '');
INSERT INTO `parameter` VALUES ('559665a71d19b60636d3aff8', '559665971d19b60636d3aff7', '559664931d19b60636d3afef', '1', '', '呼入', '1', '1', '', '0', '0', '0', '2015-07-15 17:38:36', '', '');
INSERT INTO `parameter` VALUES ('559665aa1d19b60636d3aff9', '559665971d19b60636d3aff7', '559664931d19b60636d3afef', '2', '', '呼出', '1', '2', '', '0', '0', '0', '2015-07-15 17:38:47', '', '');
INSERT INTO `parameter` VALUES ('559665bc1d19b60636d3affa', '559665971d19b60636d3aff7', '559664931d19b60636d3afef', '3', '', '呼入/呼出', '1', '3', '', '0', '0', '0', '2015-07-15 17:38:51', '', '');
INSERT INTO `parameter` VALUES ('559665de1d19b60636d3affb', '559665881d19b60636d3aff6', '559664931d19b60636d3afef', '2', 'phone', '电话号码', '1', '', '', '0', '0', '0', '2015-07-15 16:12:20', '', '');
INSERT INTO `parameter` VALUES ('559665e31d19b60636d3affc', '559665881d19b60636d3aff6', '559664931d19b60636d3afef', '3', 'name', '联系人', '1', '', '', '0', '0', '0', '2015-07-15 16:12:39', '', '');
INSERT INTO `parameter` VALUES ('559666581d19b60636d3affe', '', '5596663a1d19b60636d3affd', '0', 'flag', '控制标志', '4', '', '', '0', '0', '0', '2015-07-15 17:38:01', '', '');
INSERT INTO `parameter` VALUES ('559666d61d19b60636d3afff', '559666581d19b60636d3affe', '5596663a1d19b60636d3affd', '0', '', '车门加锁/车门解锁', '1', '', '', '0', '0', '0', '2015-07-15 17:38:09', '', '');
INSERT INTO `parameter` VALUES ('559686171d19b60636d3b002', '', '559686071d19b60636d3b001', '1', 'mediaType', '多媒体类型', '2', '', '', '0', '0', '0', '2015-07-15 17:35:57', '', '');
INSERT INTO `parameter` VALUES ('559686251d19b60636d3b003', '559686171d19b60636d3b002', '559686071d19b60636d3b001', '0', '', '图像', '1', '0', '', '0', '0', '0', '2015-07-15 17:36:01', '', '');
INSERT INTO `parameter` VALUES ('559686271d19b60636d3b004', '559686171d19b60636d3b002', '559686071d19b60636d3b001', '1', '', '音频', '1', '1', '', '0', '0', '0', '2015-07-15 17:36:05', '', '');
INSERT INTO `parameter` VALUES ('559686351d19b60636d3b005', '559686171d19b60636d3b002', '559686071d19b60636d3b001', '2', '', '视频', '1', '2', '', '0', '0', '0', '2015-07-15 17:36:08', '', '');
INSERT INTO `parameter` VALUES ('559686691d19b60636d3b006', '', '559686071d19b60636d3b001', '2', 'channelId', '通道ID', '2', '', '', '0', '0', '0', '2015-07-15 17:36:18', '', '');
INSERT INTO `parameter` VALUES ('5596868f1d19b60636d3b007', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '1', '', '1路摄像头', '1', '1', '', '0', '0', '0', '2015-07-15 17:36:26', '', '');
INSERT INTO `parameter` VALUES ('5596869c1d19b60636d3b008', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '2', '', '2路摄像头', '1', '2', '', '0', '0', '0', '2015-07-15 17:36:30', '', '');
INSERT INTO `parameter` VALUES ('559686a91d19b60636d3b009', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '3', '', '3路摄像头', '1', '3', '', '0', '0', '0', '2015-07-15 17:36:34', '', '');
INSERT INTO `parameter` VALUES ('559686b21d19b60636d3b00a', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '4', '', '4路摄像头', '1', '4', '', '0', '0', '0', '2015-07-15 17:36:39', '', '');
INSERT INTO `parameter` VALUES ('559686bb1d19b60636d3b00b', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '5', '', '5路摄像头', '1', '5', '', '0', '0', '0', '2015-07-15 17:36:42', '', '');
INSERT INTO `parameter` VALUES ('559686df1d19b60636d3b00c', '', '559686071d19b60636d3b001', '3', 'eventType', '事件项编码', '2', '', '', '0', '0', '0', '2015-07-15 17:36:52', '', '');
INSERT INTO `parameter` VALUES ('559686ea1d19b60636d3b00d', '559686df1d19b60636d3b00c', '559686071d19b60636d3b001', '0', '', '平台下发指令', '1', '0', '', '0', '0', '0', '2015-07-15 17:36:56', '', '');
INSERT INTO `parameter` VALUES ('559686ed1d19b60636d3b00e', '559686df1d19b60636d3b00c', '559686071d19b60636d3b001', '1', '', '定时动作', '1', '1', '', '0', '0', '0', '2015-07-15 17:37:01', '', '');
INSERT INTO `parameter` VALUES ('559687011d19b60636d3b00f', '559686df1d19b60636d3b00c', '559686071d19b60636d3b001', '2', '', '抢劫报警触发', '1', '2', '', '0', '0', '0', '2015-07-15 17:37:04', '', '');
INSERT INTO `parameter` VALUES ('559687141d19b60636d3b010', '559686df1d19b60636d3b00c', '559686071d19b60636d3b001', '3', '', '碰撞侧翻报警触发', '1', '3', '', '0', '0', '0', '2015-07-15 17:37:08', '', '');
INSERT INTO `parameter` VALUES ('5596875d1d19b60636d3b011', '', '559686071d19b60636d3b001', '4', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-07-15 17:37:21', '', 'yyyy-MM-dd hh:mm:ss');
INSERT INTO `parameter` VALUES ('559687601d19b60636d3b012', '', '559686071d19b60636d3b001', '5', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-07-15 17:37:32', '', 'yyyy-MM-dd hh:mm:ss');
INSERT INTO `parameter` VALUES ('559687a51d19b60636d3b013', '559686691d19b60636d3b006', '559686071d19b60636d3b001', '0', '', '所有摄像头', '1', '0', '', '0', '0', '0', '2015-07-15 17:36:23', '', '');
INSERT INTO `parameter` VALUES ('559688601d19b60636d3b015', '', '559688471d19b60636d3b014', '1', 'mediaType', '多媒体类型', '2', '', '', '0', '0', '0', '2015-07-15 17:32:55', '', '');
INSERT INTO `parameter` VALUES ('5596886c1d19b60636d3b016', '559688601d19b60636d3b015', '559688471d19b60636d3b014', '0', '', '图像', '1', '0', '', '0', '0', '0', '2015-07-15 17:32:59', '', '');
INSERT INTO `parameter` VALUES ('5596886e1d19b60636d3b017', '559688601d19b60636d3b015', '559688471d19b60636d3b014', '1', '', '音频', '1', '1', '', '0', '0', '0', '2015-07-15 17:33:03', '', '');
INSERT INTO `parameter` VALUES ('5596887d1d19b60636d3b018', '559688601d19b60636d3b015', '559688471d19b60636d3b014', '2', '', '视频', '1', '2', '', '0', '0', '0', '2015-07-15 17:33:06', '', '');
INSERT INTO `parameter` VALUES ('559688a31d19b60636d3b019', '', '559688471d19b60636d3b014', '2', 'channelId', '通道ID', '2', '', '', '0', '0', '0', '2015-07-15 17:33:18', '', '');
INSERT INTO `parameter` VALUES ('559688b41d19b60636d3b01a', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '0', '', '所有通道', '1', '0', '', '0', '0', '0', '2015-07-15 17:33:24', '', '');
INSERT INTO `parameter` VALUES ('559688b71d19b60636d3b01b', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '1', '', '1路摄像头', '1', '1', '', '0', '0', '0', '2015-07-15 17:33:27', '', '');
INSERT INTO `parameter` VALUES ('559688c81d19b60636d3b01c', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '2', '', '2路摄像头', '1', '2', '', '0', '0', '0', '2015-07-15 17:33:32', '', '');
INSERT INTO `parameter` VALUES ('559688d11d19b60636d3b01d', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '3', '', '3路摄像头', '1', '3', '', '0', '0', '0', '2015-07-15 17:33:36', '', '');
INSERT INTO `parameter` VALUES ('559688db1d19b60636d3b01e', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '4', '', '4路摄像头', '1', '4', '', '0', '0', '0', '2015-07-15 17:33:40', '', '');
INSERT INTO `parameter` VALUES ('559688e21d19b60636d3b01f', '559688a31d19b60636d3b019', '559688471d19b60636d3b014', '5', '', '5路摄像头', '1', '5', '', '0', '0', '0', '2015-07-15 17:33:44', '', '');
INSERT INTO `parameter` VALUES ('559689021d19b60636d3b020', '', '559688471d19b60636d3b014', '3', 'eventType', '事件项编码', '2', '', '', '0', '0', '0', '2015-07-15 17:34:00', '', '');
INSERT INTO `parameter` VALUES ('559689221d19b60636d3b021', '559689021d19b60636d3b020', '559688471d19b60636d3b014', '0', '', '平台下发指令', '1', '0', '', '0', '0', '0', '2015-07-15 17:34:05', '', '');
INSERT INTO `parameter` VALUES ('559689251d19b60636d3b022', '559689021d19b60636d3b020', '559688471d19b60636d3b014', '1', '', '定时动作', '1', '1', '', '0', '0', '0', '2015-07-15 17:34:08', '', '');
INSERT INTO `parameter` VALUES ('559689351d19b60636d3b023', '559689021d19b60636d3b020', '559688471d19b60636d3b014', '2', '', '抢劫报警触发', '1', '2', '', '0', '0', '0', '2015-07-15 17:34:13', '', '');
INSERT INTO `parameter` VALUES ('559689481d19b60636d3b024', '559689021d19b60636d3b020', '559688471d19b60636d3b014', '3', '', '碰撞侧翻报警触发', '1', '3', '', '0', '0', '0', '2015-07-15 17:34:17', '', '');
INSERT INTO `parameter` VALUES ('559689791d19b60636d3b025', '', '559688471d19b60636d3b014', '4', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-07-15 17:34:37', '', 'yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('5596897c1d19b60636d3b026', '', '559688471d19b60636d3b014', '5', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-07-15 17:35:01', '', 'yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('559689951d19b60636d3b027', '', '559688471d19b60636d3b014', '7', 'action', '删除标志', '2', '', '', '0', '0', '0', '2015-07-15 17:35:22', '', '');
INSERT INTO `parameter` VALUES ('559689a21d19b60636d3b028', '559689951d19b60636d3b027', '559688471d19b60636d3b014', '0', '', '保留', '1', '0', '', '0', '0', '0', '2015-07-15 17:35:26', '', '');
INSERT INTO `parameter` VALUES ('559689a61d19b60636d3b029', '559689951d19b60636d3b027', '559688471d19b60636d3b014', '1', '', '删除', '1', '1', '', '0', '0', '0', '2015-07-15 17:35:30', '', '');
INSERT INTO `parameter` VALUES ('559740cd1d19b60636d3b02b', '', '559740b31d19b60636d3b02a', '1', 'action', '录音命令', '2', '', '', '0', '0', '0', '2015-07-15 17:26:52', '', '');
INSERT INTO `parameter` VALUES ('559740d71d19b60636d3b02c', '559740cd1d19b60636d3b02b', '559740b31d19b60636d3b02a', '0', '', '停止录音', '1', '0', '', '0', '0', '0', '2015-07-15 17:31:26', '', '');
INSERT INTO `parameter` VALUES ('559740d91d19b60636d3b02d', '559740cd1d19b60636d3b02b', '559740b31d19b60636d3b02a', '1', '', '开始录音', '1', '1', '', '0', '0', '0', '2015-07-15 17:31:32', '', '');
INSERT INTO `parameter` VALUES ('559741001d19b60636d3b02e', '', '559740b31d19b60636d3b02a', '2', 'seconds', '录音时间', '1', '', '', '0', '0', '0', '2015-07-15 17:27:23', '0', '单位为秒(s),0 表示一直录音');
INSERT INTO `parameter` VALUES ('559741041d19b60636d3b02f', '', '559740b31d19b60636d3b02a', '3', 'saveFlag', '保存标志', '2', '', '', '0', '0', '0', '2015-07-15 17:27:39', '0', '单位为秒(s),0 表示一直录音');
INSERT INTO `parameter` VALUES ('5597412d1d19b60636d3b030', '559741041d19b60636d3b02f', '559740b31d19b60636d3b02a', '0', '', '实时上传', '1', '0', '', '0', '0', '0', '2015-07-15 17:31:39', '', '');
INSERT INTO `parameter` VALUES ('559741301d19b60636d3b031', '559741041d19b60636d3b02f', '559740b31d19b60636d3b02a', '1', '', '保存', '1', '1', '', '0', '0', '0', '2015-07-15 17:31:42', '', '');
INSERT INTO `parameter` VALUES ('559741641d19b60636d3b032', '', '559740b31d19b60636d3b02a', '4', 'rate', '音频采样率', '2', '', '', '0', '0', '0', '2015-07-15 17:27:55', '', '');
INSERT INTO `parameter` VALUES ('5597416c1d19b60636d3b033', '559741641d19b60636d3b032', '559740b31d19b60636d3b02a', '0', '', '8K', '1', '0', '', '0', '0', '0', '2015-07-15 17:31:49', '', '');
INSERT INTO `parameter` VALUES ('5597416e1d19b60636d3b034', '559741641d19b60636d3b032', '559740b31d19b60636d3b02a', '1', '', '11K', '1', '1', '', '0', '0', '0', '2015-07-15 17:31:52', '', '');
INSERT INTO `parameter` VALUES ('5597417e1d19b60636d3b035', '559741641d19b60636d3b032', '559740b31d19b60636d3b02a', '2', '', '23K', '1', '2', '', '0', '0', '0', '2015-07-15 17:31:56', '', '');
INSERT INTO `parameter` VALUES ('5597418e1d19b60636d3b036', '559741641d19b60636d3b032', '559740b31d19b60636d3b02a', '3', '', '32K', '1', '3', '', '0', '0', '0', '2015-07-15 17:32:01', '', '');
INSERT INTO `parameter` VALUES ('559742661d19b60636d3b038', '', '559742451d19b60636d3b037', '1', 'mediaId', '多媒体ID', '1', '', '', '0', '0', '0', '2015-07-15 17:29:05', '', '');
INSERT INTO `parameter` VALUES ('559742721d19b60636d3b039', '', '559742451d19b60636d3b037', '2', 'action', '删除标志', '2', '', '', '0', '0', '0', '2015-07-15 17:29:22', '', '');
INSERT INTO `parameter` VALUES ('5597427b1d19b60636d3b03a', '559742721d19b60636d3b039', '559742451d19b60636d3b037', '0', '', '保留', '1', '0', '', '0', '0', '0', '2015-07-15 17:31:04', '', '');
INSERT INTO `parameter` VALUES ('5597427e1d19b60636d3b03b', '559742721d19b60636d3b039', '559742451d19b60636d3b037', '1', '', '删除', '1', '1', '', '0', '0', '0', '2015-07-15 17:31:09', '', '');
INSERT INTO `parameter` VALUES ('55974f3a1d19b60636d3b03c', '5589ecb503c70e2678e39f3e', '5589ec6303c70e2678e39f3d', '1', '', '1路摄像头', '1', '1', '', '0', '0', '0', '2015-07-15 21:32:32', '', '');
INSERT INTO `parameter` VALUES ('55974f3d1d19b60636d3b03d', '5589ecb503c70e2678e39f3e', '5589ec6303c70e2678e39f3d', '2', '', '2路摄像头', '1', '2', '', '0', '0', '0', '2015-07-15 21:32:36', '', '');
INSERT INTO `parameter` VALUES ('55974f441d19b60636d3b03e', '5589ecb503c70e2678e39f3e', '5589ec6303c70e2678e39f3d', '3', '', '3路摄像头', '1', '3', '', '0', '0', '0', '2015-07-15 21:32:40', '', '');
INSERT INTO `parameter` VALUES ('55974f4d1d19b60636d3b03f', '5589ecb503c70e2678e39f3e', '5589ec6303c70e2678e39f3d', '4', '', '4路摄像头', '1', '4', '', '0', '0', '0', '2015-07-15 21:32:44', '', '');
INSERT INTO `parameter` VALUES ('55974f551d19b60636d3b040', '5589ecb503c70e2678e39f3e', '5589ec6303c70e2678e39f3d', '5', '', '5路摄像头', '1', '5', '', '0', '0', '0', '2015-07-15 21:32:48', '', '');
INSERT INTO `parameter` VALUES ('55988dcc1d19b6087f468586', '55923cbb03c70e36b1133ce1', '5591fa9c03c70e360cb98cec', '13', '', '单位：√分 □秒', '1', '', '', '16', '0', '0', '2015-07-15 21:28:05', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('55988e3b1d19b6087f468587', '559240cd03c70e36b152cf11', '5591fa9c03c70e360cb98cec', '16', '', '单位：√公里 □米', '1', '', '', '16', '0', '0', '2015-07-15 21:29:04', '', '0:存储; 1:上传');
INSERT INTO `parameter` VALUES ('560364ce34795768f3c4481f', '', '5603642934795768f3c4481e', '1', 'action', '属性', '2', '', '', '0', '0', '0', '2015-09-24 10:54:59', '', '');
INSERT INTO `parameter` VALUES ('560364f034795768f3c44820', '560364ce34795768f3c4481f', '5603642934795768f3c4481e', '0', '', '更新区域', '1', '0', '', '0', '0', '0', '2015-09-24 10:50:24', '', '');
INSERT INTO `parameter` VALUES ('5603650834795768f3c44821', '560364ce34795768f3c4481f', '5603642934795768f3c4481e', '1', '', '追加区域', '1', '1', '', '0', '0', '0', '2015-09-24 10:50:48', '', '');
INSERT INTO `parameter` VALUES ('5603651e34795768f3c44822', '560364ce34795768f3c4481f', '5603642934795768f3c4481e', '2', '', '修改区域', '1', '2', '', '0', '0', '0', '2015-09-24 10:51:10', '', '');
INSERT INTO `parameter` VALUES ('5603656134795768f3c44823', '', '5603642934795768f3c4481e', '2', 'areas', '区域列表', '5', '', '', '0', '0', '0', '2015-09-24 10:52:17', '', '');
INSERT INTO `parameter` VALUES ('5603658734795768f3c44824', '5603656134795768f3c44823', '5603642934795768f3c4481e', '1', '', '区域项', '6', '', '', '0', '0', '0', '2015-09-24 10:52:55', '', '');
INSERT INTO `parameter` VALUES ('560365c734795768f3c44825', '5603658734795768f3c44824', '5603642934795768f3c4481e', '1', 'areaId', '区域ID', '1', '', '', '0', '0', '0', '2015-09-24 10:53:59', '', '');
INSERT INTO `parameter` VALUES ('560365cb34795768f3c44826', '5603658734795768f3c44824', '5603642934795768f3c4481e', '2', 'flag', '区域属性', '1', '', '', '0', '0', '0', '2015-09-24 10:54:51', '', '');
INSERT INTO `parameter` VALUES ('5603660834795768f3c44827', '5603658734795768f3c44824', '5603642934795768f3c4481e', '3', 'lat', '中心点纬度', '1', '', '', '0', '0', '0', '2015-09-24 10:55:27', '', '');
INSERT INTO `parameter` VALUES ('5603662234795768f3c44828', '5603658734795768f3c44824', '5603642934795768f3c4481e', '4', 'lng', '中心点经度', '1', '', '', '0', '0', '0', '2015-09-24 10:55:42', '', '');
INSERT INTO `parameter` VALUES ('5603663a34795768f3c44829', '5603658734795768f3c44824', '5603642934795768f3c4481e', '5', 'radius', '半径', '1', '', '', '0', '0', '0', '2015-09-24 10:56:19', '', '');
INSERT INTO `parameter` VALUES ('5603666b34795768f3c4482a', '5603658734795768f3c44824', '5603642934795768f3c4481e', '6', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-09-24 10:56:57', '', '');
INSERT INTO `parameter` VALUES ('5603667c34795768f3c4482b', '5603658734795768f3c44824', '5603642934795768f3c4481e', '7', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-09-24 10:57:17', '', '');
INSERT INTO `parameter` VALUES ('5603669b34795768f3c4482c', '5603658734795768f3c44824', '5603642934795768f3c4481e', '8', 'maxSpeed', '最高速度', '1', '', '', '0', '0', '0', '2015-09-24 10:57:43', '', '');
INSERT INTO `parameter` VALUES ('560366aa34795768f3c4482d', '5603658734795768f3c44824', '5603642934795768f3c4481e', '9', 'overspeedDuration', '超速持续时间', '1', '', '', '0', '0', '0', '2015-09-24 10:58:42', '', '');
INSERT INTO `parameter` VALUES ('5603673f34795768f3c4482f', '', '5603671234795768f3c4482e', '0', 'list', '区域ID列表', '5', '', '', '0', '0', '0', '2015-09-24 11:00:15', '', '');
INSERT INTO `parameter` VALUES ('5603676034795768f3c44830', '5603673f34795768f3c4482f', '5603671234795768f3c4482e', '1', '', '区域ID', '1', '', '', '0', '0', '0', '2015-09-24 11:00:48', '', '');
INSERT INTO `parameter` VALUES ('5608b59b3479576e72812c6c', '', '5608b5723479576e72812c6b', '1', 'action', '属性', '2', '', '', '0', '0', '0', '2015-09-28 11:35:55', '', '');
INSERT INTO `parameter` VALUES ('5608b5ae3479576e72812c6d', '5608b59b3479576e72812c6c', '5608b5723479576e72812c6b', '0', '', '更新区域', '1', '0', '', '0', '0', '0', '2015-09-28 11:36:14', '', '');
INSERT INTO `parameter` VALUES ('5608b5b33479576e72812c6e', '5608b59b3479576e72812c6c', '5608b5723479576e72812c6b', '1', '', '追加区域', '1', '1', '', '0', '0', '0', '2015-09-28 11:36:45', '', '');
INSERT INTO `parameter` VALUES ('5608b5d03479576e72812c6f', '5608b59b3479576e72812c6c', '5608b5723479576e72812c6b', '2', '', '修改区域', '1', '2', '', '0', '0', '0', '2015-09-28 11:37:02', '', '');
INSERT INTO `parameter` VALUES ('5608b6123479576e72812c70', '', '5608b5723479576e72812c6b', '2', 'areas', '区域列表', '5', '', '', '0', '0', '0', '2015-09-28 11:37:54', '', '');
INSERT INTO `parameter` VALUES ('5608b63a3479576e72812c71', '5608b6123479576e72812c70', '5608b5723479576e72812c6b', '1', '', '区域项', '6', '', '', '0', '0', '0', '2015-09-28 11:38:34', '', '');
INSERT INTO `parameter` VALUES ('5608b6573479576e72812c72', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '1', 'areaId', '区域ID', '1', '', '', '0', '0', '0', '2015-09-28 11:39:03', '', '');
INSERT INTO `parameter` VALUES ('5608b65a3479576e72812c73', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '2', 'flag', '区域属性', '1', '', '', '0', '0', '0', '2015-09-28 11:39:33', '', '');
INSERT INTO `parameter` VALUES ('5608b67b3479576e72812c74', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '3', 'ulLat', '左上点纬度', '1', '', '', '0', '0', '0', '2015-09-28 11:41:07', '', '');
INSERT INTO `parameter` VALUES ('5608b6d73479576e72812c75', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '4', 'ulLng', '左上点经度', '1', '', '', '0', '0', '0', '2015-09-28 11:41:29', '', '');
INSERT INTO `parameter` VALUES ('5608db103479576e72812c76', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '5', 'brLat', '右下点纬度', '1', '', '', '0', '0', '0', '2015-09-28 14:31:55', '', '');
INSERT INTO `parameter` VALUES ('5608db343479576e72812c77', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '6', 'brLng', '右下点经度', '1', '', '', '0', '0', '0', '2015-09-28 14:32:02', '', '');
INSERT INTO `parameter` VALUES ('5608db583479576e72812c78', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '7', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-09-28 14:17:10', '', '');
INSERT INTO `parameter` VALUES ('5608db693479576e72812c79', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '8', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-09-28 14:17:37', '', '');
INSERT INTO `parameter` VALUES ('5608db863479576e72812c7a', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '9', 'maxSpeed', '最高速度', '1', '', '', '0', '0', '0', '2015-09-28 14:18:11', '', '');
INSERT INTO `parameter` VALUES ('5608dba73479576e72812c7b', '5608b63a3479576e72812c71', '5608b5723479576e72812c6b', '10', 'overspeedSeconds', '超速持续时间', '1', '', '', '0', '0', '0', '2015-09-28 14:18:43', '', '');
INSERT INTO `parameter` VALUES ('5608dc023479576e72812c7d', '', '5608dbdd3479576e72812c7c', '1', 'list', '区域ID列表', '5', '', '', '0', '0', '0', '2015-09-28 14:19:46', '', '');
INSERT INTO `parameter` VALUES ('5608dc1e3479576e72812c7e', '5608dc023479576e72812c7d', '5608dbdd3479576e72812c7c', '1', '', '区域ID', '1', '', '', '0', '0', '0', '2015-09-28 14:20:14', '', '');
INSERT INTO `parameter` VALUES ('5609e7fc3479576f67bdf829', '', '5609e7dc3479576f67bdf828', '1', 'areaId', '区域ID', '1', '', '', '0', '0', '0', '2015-09-29 09:23:08', '', '');
INSERT INTO `parameter` VALUES ('5609e8003479576f67bdf82a', '', '5609e7dc3479576f67bdf828', '2', 'flag', '区域属性', '1', '', '', '0', '0', '0', '2015-09-29 09:23:26', '', '');
INSERT INTO `parameter` VALUES ('5609e8103479576f67bdf82b', '', '5609e7dc3479576f67bdf828', '3', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-09-29 09:23:52', '', '');
INSERT INTO `parameter` VALUES ('5609e82b3479576f67bdf82c', '', '5609e7dc3479576f67bdf828', '4', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-09-29 09:24:08', '', '');
INSERT INTO `parameter` VALUES ('5609e83b3479576f67bdf82d', '', '5609e7dc3479576f67bdf828', '5', 'maxSpeed', '最高速度', '1', '', '', '0', '0', '0', '2015-09-29 09:24:27', '', '');
INSERT INTO `parameter` VALUES ('5609e8713479576f67bdf82e', '', '5609e7dc3479576f67bdf828', '6', 'overspeedSeconds', '超速持续时间', '1', '', '', '0', '0', '0', '2015-09-29 09:25:19', '', '');
INSERT INTO `parameter` VALUES ('5609e9593479576f67bdf82f', '', '5609e7dc3479576f67bdf828', '7', 'points', '顶点列表', '5', '', '', '0', '0', '0', '2015-09-29 09:29:59', '', '');
INSERT INTO `parameter` VALUES ('5609e9f33479576f67bdf830', '5609e9593479576f67bdf82f', '5609e7dc3479576f67bdf828', '1', '', '顶点项', '6', '', '', '0', '0', '0', '2015-09-29 09:31:46', '', '');
INSERT INTO `parameter` VALUES ('5609ea0e3479576f67bdf831', '5609e9f33479576f67bdf830', '5609e7dc3479576f67bdf828', '1', 'lat', '顶点纬度', '1', '', '', '0', '0', '0', '2015-09-29 09:31:58', '', '');
INSERT INTO `parameter` VALUES ('5609ea103479576f67bdf832', '5609e9f33479576f67bdf830', '5609e7dc3479576f67bdf828', '2', 'lng', '顶点经度', '1', '', '', '0', '0', '0', '2015-09-29 09:32:14', '', '');
INSERT INTO `parameter` VALUES ('5609ea5a3479576f67bdf834', '', '5609ea353479576f67bdf833', '1', 'list', '区域ID列表', '5', '', '', '0', '0', '0', '2015-09-29 09:33:20', '', '');
INSERT INTO `parameter` VALUES ('5609ea6a3479576f67bdf835', '5609ea5a3479576f67bdf834', '5609ea353479576f67bdf833', '0', '', '区域ID', '1', '', '', '0', '0', '0', '2015-09-29 09:33:40', '', '');
INSERT INTO `parameter` VALUES ('5609ef0f34795770116c4747', '', '5609eec034795770116c4746', '1', 'areaId', '路线ID', '1', '', '', '0', '0', '0', '2015-09-29 09:53:19', '', '');
INSERT INTO `parameter` VALUES ('5609ef1834795770116c4748', '', '5609eec034795770116c4746', '2', 'flag', '路线属性', '1', '', '', '0', '0', '0', '2015-09-29 09:53:42', '', '');
INSERT INTO `parameter` VALUES ('5609ef2834795770116c4749', '', '5609eec034795770116c4746', '3', 'startTime', '起始时间', '1', '', '', '0', '0', '0', '2015-09-29 09:53:57', '', '');
INSERT INTO `parameter` VALUES ('5609ef4034795770116c474a', '', '5609eec034795770116c4746', '4', 'endTime', '结束时间', '1', '', '', '0', '0', '0', '2015-09-29 09:54:21', '', '');
INSERT INTO `parameter` VALUES ('5609ef4f34795770116c474b', '', '5609eec034795770116c4746', '5', 'sections', '路段列表', '5', '', '', '0', '0', '0', '2015-09-29 09:55:01', '', '');
INSERT INTO `parameter` VALUES ('5609ef8f34795770116c474c', '5609ef4f34795770116c474b', '5609eec034795770116c4746', '1', '', '路段项', '6', '', '', '0', '0', '0', '2015-09-29 09:55:27', '', '');
INSERT INTO `parameter` VALUES ('5609efb034795770116c474d', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '0', 'pointId', '拐点ID', '1', '', '', '0', '0', '0', '2015-09-29 09:56:00', '', '');
INSERT INTO `parameter` VALUES ('5609efb234795770116c474e', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '1', 'sectionId', '路段ID', '1', '', '', '0', '0', '0', '2015-09-29 09:56:34', '', '');
INSERT INTO `parameter` VALUES ('5609efd634795770116c474f', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '2', 'lat', '拐点纬度', '1', '', '', '0', '0', '0', '2015-09-29 09:56:59', '', '');
INSERT INTO `parameter` VALUES ('5609efee34795770116c4750', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '3', 'lng', '拐点经度', '1', '', '', '0', '0', '0', '2015-09-29 09:57:15', '', '');
INSERT INTO `parameter` VALUES ('5609effd34795770116c4751', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '4', 'width', '路段宽度', '1', '', '', '0', '0', '0', '2015-09-29 09:57:33', '', '');
INSERT INTO `parameter` VALUES ('5609f01134795770116c4752', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '5', 'flag', '路段属性', '1', '', '', '0', '0', '0', '2015-09-29 09:57:54', '', '');
INSERT INTO `parameter` VALUES ('5609f03534795770116c4753', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '6', 'maxTime', '路段行驶过长阈值', '1', '', '', '0', '0', '0', '2015-09-29 09:59:21', '', '');
INSERT INTO `parameter` VALUES ('5609f07c34795770116c4754', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '7', 'minTime', '路段行驶不足阈值', '1', '', '', '0', '0', '0', '2015-09-29 09:59:39', '', '');
INSERT INTO `parameter` VALUES ('5609f09334795770116c4755', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '8', 'maxSpeed', '路段最高速度', '1', '', '', '0', '0', '0', '2015-09-29 09:59:58', '', '');
INSERT INTO `parameter` VALUES ('5609f0a034795770116c4756', '5609ef8f34795770116c474c', '5609eec034795770116c4746', '9', 'overspeedSeconds', '路段超速持续时间', '1', '', '', '0', '0', '0', '2015-09-29 10:00:30', '', '');
INSERT INTO `parameter` VALUES ('5609f1c034795770116c4758', '', '5609f19434795770116c4757', '1', 'list', '路线列表', '5', '', '', '0', '0', '0', '2015-09-29 10:04:48', '', '');
INSERT INTO `parameter` VALUES ('5609f1d934795770116c4759', '5609f1c034795770116c4758', '5609f19434795770116c4757', '1', '', '路线ID', '1', '', '', '0', '0', '0', '2015-09-29 10:05:13', '', '');
INSERT INTO `parameter` VALUES ('5629fabf7a056430c58ef3d5', '', '5629f7c97a056430c58ef3d2', '1', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-23 17:15:43', '', '');
INSERT INTO `parameter` VALUES ('5629facd7a056430c58ef3d6', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '1', '', '执行标准版本', '1', '00', '', '0', '0', '0', '2015-10-26 11:26:31', '', '');
INSERT INTO `parameter` VALUES ('562d9d6d7a056430c566c502', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '2', '', '当前驾驶人信息', '1', '01', '', '0', '0', '0', '2015-10-26 11:27:53', '', '');
INSERT INTO `parameter` VALUES ('562d9d907a056430c566c503', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '3', '', '实时时间', '1', '02', '', '0', '0', '0', '2015-10-26 11:27:48', '', '');
INSERT INTO `parameter` VALUES ('562d9dbc7a056430c566c504', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '4', '', '累计行驶里程', '1', '03', '', '0', '0', '0', '2015-10-26 11:28:18', '', '');
INSERT INTO `parameter` VALUES ('562d9dd97a056430c566c505', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '5', '', '脉冲系数', '1', '04', '', '0', '0', '0', '2015-10-26 11:29:00', '', '');
INSERT INTO `parameter` VALUES ('562d9dff7a056430c566c506', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '6', '', '车辆信息', '1', '05', '', '0', '0', '0', '2015-10-26 11:29:20', '', '');
INSERT INTO `parameter` VALUES ('562d9e127a056430c566c507', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '7', '', '状态信号配置信息', '1', '06', '', '0', '0', '0', '2015-10-26 11:29:48', '', '');
INSERT INTO `parameter` VALUES ('562d9e2f7a056430c566c508', '5629fabf7a056430c58ef3d5', '5629f7c97a056430c58ef3d2', '8', '', '唯一性编号', '1', '07', '', '0', '0', '0', '2015-10-26 11:30:11', '', '');
INSERT INTO `parameter` VALUES ('562d9f367a056430c566c50b', '', '562d9ef97a056430c566c50a', '1', 'start', '开始时间', '1', '', '', '0', '0', '0', '2015-10-26 11:37:51', '', '时间格式：yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('562d9f427a056430c566c50c', '', '562d9ef97a056430c566c50a', '2', 'end', '结束时间', '1', '', '', '0', '0', '0', '2015-10-26 11:37:46', '', '时间格式：yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('562d9fbf7a056430c566c50d', '', '562d9ef97a056430c566c50a', '3', 'blocks', '数据行数', '1', '', '', '0', '0', '0', '2015-10-26 11:36:31', '10', '最大单位数据块个数');
INSERT INTO `parameter` VALUES ('562da0617a056430c566c50e', '', '562d9ef97a056430c566c50a', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 11:39:13', '', '');
INSERT INTO `parameter` VALUES ('562da07c7a056430c566c50f', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '0', '', '行驶速度记录', '1', '08', '', '0', '0', '0', '2015-10-26 11:39:40', '', '');
INSERT INTO `parameter` VALUES ('562da0807a056430c566c510', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '1', '', '位置信息记录', '1', '09', '', '0', '0', '0', '2015-10-26 11:40:03', '', '');
INSERT INTO `parameter` VALUES ('562da0967a056430c566c511', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '2', '', '事故疑点记录', '1', '10', '', '0', '0', '0', '2015-10-26 11:40:35', '', '');
INSERT INTO `parameter` VALUES ('562da0b77a056430c566c512', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '3', '', '超时驾驶记录', '1', '11', '', '0', '0', '0', '2015-10-26 11:40:55', '', '');
INSERT INTO `parameter` VALUES ('562da0ca7a056430c566c513', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '4', '', '驾驶人登退记录', '1', '12', '', '0', '0', '0', '2015-10-26 11:41:31', '', '');
INSERT INTO `parameter` VALUES ('562da1077a056430c566c514', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '5', '', '外部供电记录', '1', '13', '', '0', '0', '0', '2015-10-26 14:37:53', '', '');
INSERT INTO `parameter` VALUES ('562da1127a056430c566c515', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '6', '', '参数修改记录', '1', '14', '', '0', '0', '0', '2015-10-26 11:42:30', '', '');
INSERT INTO `parameter` VALUES ('562da13c7a056430c566c516', '562da0617a056430c566c50e', '562d9ef97a056430c566c50a', '7', '', '速度状态日志', '1', '15', '', '0', '0', '0', '2015-10-26 11:43:00', '', '');
INSERT INTO `parameter` VALUES ('562dc1f27a056430c566c51f', '', '562da2c97a056430c566c51e', '1', 'vehicleIdcode', '车辆识别代号', '1', '', '', '0', '0', '0', '2015-10-26 14:10:20', '', '');
INSERT INTO `parameter` VALUES ('562dc20a7a056430c566c520', '', '562da2c97a056430c566c51e', '2', 'plateNumber', '机动车号牌号码', '1', '', '', '0', '0', '0', '2015-10-26 14:10:25', '', '');
INSERT INTO `parameter` VALUES ('562dc2247a056430c566c521', '', '562da2c97a056430c566c51e', '3', 'category', '机动车号牌分类', '1', '', '', '0', '0', '0', '2015-10-26 14:10:30', '', '');
INSERT INTO `parameter` VALUES ('562dc3277a056430c566c523', '', '562dc2957a056430c566c522', '1', 'time', '安装日期', '1', '', '', '0', '0', '0', '2015-10-26 14:21:43', '', '日期格式：yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('562dc3f67a056430c566c525', '', '562da2c97a056430c566c51e', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:11:02', '', '');
INSERT INTO `parameter` VALUES ('562dc4207a056430c566c526', '562dc3f67a056430c566c525', '562da2c97a056430c566c51e', '0', '', '设置车辆信息', '1', '82', '', '0', '0', '0', '2015-10-26 14:11:44', '', '');
INSERT INTO `parameter` VALUES ('562dc43a7a056430c566c527', '', '562dc2957a056430c566c522', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:12:10', '', '');
INSERT INTO `parameter` VALUES ('562dc4577a056430c566c528', '562dc43a7a056430c566c527', '562dc2957a056430c566c522', '0', '', '设置安装日期', '1', '83', '', '0', '0', '0', '2015-10-26 14:12:39', '', '');
INSERT INTO `parameter` VALUES ('562dc47d7a056430c566c529', '', '562dc3977a056430c566c524', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:13:17', '', '');
INSERT INTO `parameter` VALUES ('562dc48d7a056430c566c52a', '562dc47d7a056430c566c529', '562dc3977a056430c566c524', '0', '', '设置状态量配置信息', '1', '84', '', '0', '0', '0', '2015-10-26 14:13:33', '', '');
INSERT INTO `parameter` VALUES ('562dc4e97a056430c566c52b', '', '562dc3977a056430c566c524', '0', 'name0', 'D0的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:15:05', '', '');
INSERT INTO `parameter` VALUES ('562dc4ec7a056430c566c52c', '', '562dc3977a056430c566c524', '1', 'name1', 'D1的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:15:19', '', '');
INSERT INTO `parameter` VALUES ('562dc4fa7a056430c566c52d', '', '562dc3977a056430c566c524', '2', 'name2', 'D2的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:15:32', '', '');
INSERT INTO `parameter` VALUES ('562dc50e7a056430c566c52e', '', '562dc3977a056430c566c524', '3', 'name3', 'D3的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:15:52', '', '');
INSERT INTO `parameter` VALUES ('562dc51a7a056430c566c52f', '', '562dc3977a056430c566c524', '4', 'name4', 'D4的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:16:03', '', '');
INSERT INTO `parameter` VALUES ('562dc58d7a056430c566c530', '', '562dc3977a056430c566c524', '5', 'name5', 'D5的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:17:58', '', '');
INSERT INTO `parameter` VALUES ('562dc5987a056430c566c531', '', '562dc3977a056430c566c524', '6', 'name6', 'D6的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:18:10', '', '');
INSERT INTO `parameter` VALUES ('562dc5a47a056430c566c532', '', '562dc3977a056430c566c524', '7', 'name7', 'D7的状态信号名称', '1', '', '', '0', '0', '0', '2015-10-26 14:18:23', '', '');
INSERT INTO `parameter` VALUES ('562dc6217a056430c566c534', '', '562dc60e7a056430c566c533', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:20:17', '', '');
INSERT INTO `parameter` VALUES ('562dc63e7a056430c566c535', '562dc6217a056430c566c534', '562dc60e7a056430c566c533', '0', '', '设置时间', '1', 'c2', '', '0', '0', '0', '2015-10-26 14:20:46', '', '');
INSERT INTO `parameter` VALUES ('562dc6667a056430c566c536', '', '562dc60e7a056430c566c533', '1', 'time', '时间', '1', '', '', '0', '0', '0', '2015-10-26 14:21:26', '', '时间格式：yyyy-MM-dd HH:mm:ss');
INSERT INTO `parameter` VALUES ('562dc6ee7a056430c566c538', '', '562dc6d57a056430c566c537', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:23:42', '', '');
INSERT INTO `parameter` VALUES ('562dc7127a056430c566c539', '562dc6ee7a056430c566c538', '562dc6d57a056430c566c537', '0', '', '设置脉冲系统', '1', 'c3', '', '0', '0', '0', '2015-10-26 14:24:18', '', '');
INSERT INTO `parameter` VALUES ('562dc76d7a056430c566c53b', '', '562dc6d57a056430c566c537', '1', 'pulse', '脉冲系数', '1', '', '', '0', '0', '0', '2015-10-29 15:39:12', '', '');
INSERT INTO `parameter` VALUES ('562dc7d47a056430c566c53d', '', '562dc7c47a056430c566c53c', '0', 'command', '命令字', '2', '', '', '0', '0', '0', '2015-10-26 14:27:32', '', '');
INSERT INTO `parameter` VALUES ('562dc7e77a056430c566c53e', '562dc7d47a056430c566c53d', '562dc7c47a056430c566c53c', '0', '', '设置起始里程', '1', 'c4', '', '0', '0', '0', '2015-10-26 14:27:51', '', '');
INSERT INTO `parameter` VALUES ('562dc8897a056430c566c53f', '', '562dc7c47a056430c566c53c', '0', 'mileage', '初始里程', '1', '', '', '0', '0', '0', '2015-10-26 15:39:05', '', '单位：千米');
INSERT INTO `parameter` VALUES ('5aebc3737b03680cd84d9c5a', '', '5aebc31b7b03680cd84d9c59', '1', 'T1', 'ACC ON 状态下上传间隔；默认值：10', '1', '', '', '0', '0', '0', '2018-05-04 10:20:35', '10', '');
INSERT INTO `parameter` VALUES ('5aebc39b7b03680cd84d9c5b', '', '5aebc31b7b03680cd84d9c59', '2', 'T2', 'ACC OFF 状态下上传间隔', '1', '', '', '0', '0', '0', '2018-05-04 10:21:15', '10', '');
INSERT INTO `parameter` VALUES ('5aebcb5e7b03680cd84d9c5e', '', '5aebcacd7b03680cd84d9c5d', '1', 'N', '重拨次数', '1', '', '', '0', '0', '0', '2018-05-04 10:54:22', '3', '');
INSERT INTO `parameter` VALUES ('5aebcb987b03680cd84d9c5f', '', '5aebcacd7b03680cd84d9c5d', '2', '#', '查询设置的重拨次数', '1', '', '', '0', '0', '0', '2018-05-04 10:55:20', '', '');
INSERT INTO `parameter` VALUES ('5af3a6127b03680cd87195d5', '', '5aebc31b7b03680cd84d9c59', '0', '#', ' 查询当前设置的T1、T2参数', '6', '', '', '0', '0', '0', '2018-05-10 10:02:41', '0', '');
INSERT INTO `parameter` VALUES ('5af3a8807b03680cd87195d7', '5af3a6127b03680cd87195d5', '5aebc31b7b03680cd84d9c59', '0', 'T1', 'ACC ON 状态下上传间隔；默认值：10', '1', '', '', '0', '0', '0', '2018-05-10 10:03:44', '', '');
INSERT INTO `parameter` VALUES ('5b060f407b03680cd87195d9', '', '5b060e277b03680cd87195d8', '0', 'SPEED', '超速提醒', '3', 'SPEED', 'SPEED', '0', '0', '0', '2018-05-24 09:41:45', '', '');
INSERT INTO `parameter` VALUES ('5b060f827b03680cd87195da', '5b060f407b03680cd87195d9', '5b060e277b03680cd87195d8', '0', 'select', '查询超速参数', '2', '', 'select', '0', '0', '0', '2018-05-24 13:47:52', '', '');
INSERT INTO `parameter` VALUES ('5b060f8e7b03680cd87195db', '5b060f407b03680cd87195d9', '5b060e277b03680cd87195d8', '0', 'set', '设置超速参数', '6', '', 'set', '0', '0', '0', '2018-05-24 09:32:54', '', '');
INSERT INTO `parameter` VALUES ('5b0611947b03680cd87195dd', '5b060f8e7b03680cd87195db', '5b060e277b03680cd87195d8', '0', 'A', '超速报警是否开启', '1', '', '', '0', '0', '0', '2018-05-24 09:15:55', 'OFF', '');
INSERT INTO `parameter` VALUES ('5b0611a97b03680cd87195de', '5b060f8e7b03680cd87195db', '5b060e277b03680cd87195d8', '0', 'B', '时间范围', '1', '', '', '0', '0', '0', '2018-05-24 17:07:42', '20', '秒');
INSERT INTO `parameter` VALUES ('5b0611fa7b03680cd87195df', '5b060f8e7b03680cd87195db', '5b060e277b03680cd87195d8', '0', 'C', '超速门限范围', '1', '', '', '0', '0', '0', '2018-05-24 17:07:51', '100', 'km/h');
INSERT INTO `parameter` VALUES ('5b06122f7b03680cd87195e0', '5b060f8e7b03680cd87195db', '5b060e277b03680cd87195d8', '0', 'M', '报警上报方式', '1', '', '', '0', '0', '0', '2018-05-24 09:15:27', '1', '0 仅GPRS，1 SMS+GPRS');
INSERT INTO `parameter` VALUES ('5b06178f7b03680cd87195e1', '5b060f827b03680cd87195da', '5b060e277b03680cd87195d8', '0', '#', '查询超速参数', '1', '#', '#', '0', '0', '0', '2018-05-24 09:40:49', '', '');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IX_NAME` (`NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IX_NAME` (`NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('56331dd73dd6ef06ee816d69', '监控平台', '用于监控运营平台', '2015-11-13 16:06:42');
INSERT INTO `role` VALUES ('563c4b783dd6ef1eb5e83e10', 'admin', '系统管理员', '2015-11-12 09:58:24');
INSERT INTO `role` VALUES ('5a718d5129a50f3148bf9ba9', '监控管理员', '用于车队监控管理员使用', '2018-04-08 16:17:00');

-- ----------------------------
-- Table structure for roleinuser
-- ----------------------------
DROP TABLE IF EXISTS `roleinuser`;
CREATE TABLE `roleinuser` (
  `USERID` char(24) NOT NULL COMMENT '用户ID',
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USERID`,`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of roleinuser
-- ----------------------------

-- ----------------------------
-- Table structure for track
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
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
  KEY `IX_DEVICEID_GPSTIME` (`NUMBER`,`GPSTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='轨迹表';

-- ----------------------------
-- Records of track
-- ----------------------------

-- ----------------------------
-- Table structure for trip
-- ----------------------------
DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip` (
  `NUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `STARTTIME` datetime NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime NOT NULL COMMENT '结束时间',
  `MILEAGE` int(11) unsigned NOT NULL COMMENT '里程',
  `MAXSPEED` tinyint(3) unsigned NOT NULL COMMENT '最高速度',
  `AVGSPEED` tinyint(3) unsigned NOT NULL COMMENT '平均速度',
  `OVERSPEED` tinyint(3) unsigned NOT NULL COMMENT '超速次数',
  `OVERSPEEDTIME` smallint(6) NOT NULL COMMENT '超速时长',
  `BREAKS` tinyint(3) unsigned NOT NULL COMMENT '急刹车次数',
  `SPEEDUP` tinyint(3) unsigned NOT NULL COMMENT '急加速次数',
  `MAXECT` tinyint(4) NOT NULL COMMENT '最高水温',
  `MAXRPM` smallint(6) NOT NULL COMMENT '最高转速',
  `AVGBV` float(3,1) NOT NULL COMMENT '平均电压',
  `TOTALOIL` float(5,2) NOT NULL COMMENT '总油耗',
  `AVGOIL` float(5,2) NOT NULL COMMENT '平均油耗',
  `FATIGUETIME` smallint(5) unsigned NOT NULL COMMENT '疲劳驾驶时长',
  `IDLETIME` smallint(5) unsigned NOT NULL COMMENT '怠速时长',
  KEY `IX_DEVICEID_STARTTIME` (`NUMBER`,`STARTTIME`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='行程报告表';

-- ----------------------------
-- Records of trip
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` char(24) NOT NULL COMMENT '用户ID',
  `ACCOUNT` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(50) NOT NULL COMMENT '密码',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `PUSHURL` varchar(100) NOT NULL COMMENT '推送接口',
  `EMAIL` varchar(50) NOT NULL COMMENT '邮箱',
  `PHONE` varchar(50) NOT NULL COMMENT '电话',
  `CONTACT` varchar(50) NOT NULL COMMENT '联系人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `ENABLED` bit(1) NOT NULL COMMENT '启用',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `SERVICESTARTDATE` date DEFAULT NULL COMMENT '服务开始日期',
  `SERVICEENDDATE` date DEFAULT NULL COMMENT '服务结束日期',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `QUESTION1` varchar(50) DEFAULT NULL COMMENT '问题1',
  `ANSWER1` varchar(50) DEFAULT NULL COMMENT '答案1',
  `QUESTION2` varchar(50) DEFAULT NULL COMMENT '问题2',
  `ANSWER2` varchar(50) DEFAULT NULL COMMENT '答案2',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UN_ACCOUNT` (`ACCOUNT`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5629a3f87a056430ae68996c', 'admin', '123456', '管理员', '', '', '', '', '2015-01-01 00:00:00', '', '2015-10-30 15:31:54', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('56331f403dd6ef06ee816d6a', 'rayton', '123456', '锐讯易通', 'http://60.205.226.132:8082/locate/realtime', '3333@163.com', '', '', '2015-10-30 15:41:52', '', '2018-05-22 10:58:33', null, null, '', null, null, null, null);
