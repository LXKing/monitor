-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.17-MariaDB - Homebrew
-- 服务器操作系统:                      osx10.10
-- HeidiSQL 版本:                  9.1.0.4938
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 monitordb 的数据库结构
CREATE DATABASE IF NOT EXISTS `monitordb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `monitordb`;


-- 导出  表 monitordb.alarm 结构
CREATE TABLE IF NOT EXISTS `alarm` (
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
  KEY `DEVICENUMBER_GPSTIME` (`DEVICENUMBER`,`GPSTIME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED COMMENT='报警表，用于记录设备所有报警，以及人工处理结果';

-- 正在导出表  monitordb.alarm 的数据：0 rows
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;


-- 导出  表 monitordb.areaindevice 结构
CREATE TABLE IF NOT EXISTS `areaindevice` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `AREAID` int(10) unsigned NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  `TIME` datetime NOT NULL COMMENT '绑定时间',
  PRIMARY KEY (`DEVICENUMBER`,`AREAID`,`AREATYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备';

-- 正在导出表  monitordb.areaindevice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `areaindevice` DISABLE KEYS */;
/*!40000 ALTER TABLE `areaindevice` ENABLE KEYS */;


-- 导出  表 monitordb.areaindevicelog 结构
CREATE TABLE IF NOT EXISTS `areaindevicelog` (
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
  KEY `ACKTIME` (`ACKTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='区域设备关系表，用于圆形区域、矩形区域、多边形区域、路线绑定到设备的操作日志，以便与设备同步';

-- 正在导出表  monitordb.areaindevicelog 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `areaindevicelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `areaindevicelog` ENABLE KEYS */;


-- 导出  表 monitordb.areainmaplayer 结构
CREATE TABLE IF NOT EXISTS `areainmaplayer` (
  `MAPLAYERID` char(24) NOT NULL COMMENT '地图图层id',
  `AREAID` int(10) unsigned NOT NULL COMMENT '区域唯一号',
  `AREATYPE` tinyint(4) NOT NULL COMMENT '区域类型',
  PRIMARY KEY (`MAPLAYERID`,`AREAID`,`AREATYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='地图图层覆盖物，用于圆形区域、矩形区域、多边形区域、路线、兴趣点绑定到地图图层';

-- 正在导出表  monitordb.areainmaplayer 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `areainmaplayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `areainmaplayer` ENABLE KEYS */;


-- 导出  表 monitordb.circle 结构
CREATE TABLE IF NOT EXISTS `circle` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='圆形区域';

-- 正在导出表  monitordb.circle 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `circle` DISABLE KEYS */;
/*!40000 ALTER TABLE `circle` ENABLE KEYS */;


-- 导出  表 monitordb.company 结构
CREATE TABLE IF NOT EXISTS `company` (
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

-- 正在导出表  monitordb.company 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`ID`, `FULLNAME`, `SHORTNAME`, `ORGANCODE`, `CORPORATION`, `ONDUTYPHONE`, `OFFICEADDRESS`, `REGISTADDRESS`, `PARENTVISIBLE`, `EDITTIME`, `REGISTDATE`) VALUES
	('558ffc6603c70e31a2a53a30', '中国兵器工业北方通用电子', '兵工北方通用', '', '', '', '', '', b'0', '2015-08-29 11:18:53', '2015-08-29');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- 导出  表 monitordb.companyauthorize 结构
CREATE TABLE IF NOT EXISTS `companyauthorize` (
  `COMPANYID` char(24) NOT NULL COMMENT '企业ID',
  `PERMISSIONID` varchar(100) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`,`PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='企业授权表';

-- 正在导出表  monitordb.companyauthorize 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `companyauthorize` DISABLE KEYS */;
/*!40000 ALTER TABLE `companyauthorize` ENABLE KEYS */;


-- 导出  表 monitordb.device 结构
CREATE TABLE IF NOT EXISTS `device` (
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
  UNIQUE KEY `DEVICENUMBER` (`DEVICENUMBER`),
  KEY `COMPANYID` (`COMPANYID`),
  KEY `SIMCARDNUMBER` (`SIMCARDID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='设备表';

-- 正在导出表  monitordb.device 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;


-- 导出  表 monitordb.dictionary 结构
CREATE TABLE IF NOT EXISTS `dictionary` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一编号',
  `KIND` int(11) NOT NULL COMMENT '类型',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  `PID` int(11) unsigned DEFAULT NULL COMMENT '父级编号',
  `CODE` varchar(20) DEFAULT NULL COMMENT '代码',
  `INDEX` varchar(20) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`ID`),
  KEY `KIND` (`KIND`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- 正在导出表  monitordb.dictionary 的数据：~97 rows (大约)
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
INSERT INTO `dictionary` (`ID`, `KIND`, `NAME`, `EDITTIME`, `PID`, `CODE`, `INDEX`) VALUES
	(1, 1, '蓝色', '2015-07-29 22:02:26', NULL, '1', '1'),
	(2, 1, '黄色', '2015-07-29 22:03:44', NULL, '2', '2'),
	(3, 1, '黑色', '2015-07-29 22:04:23', NULL, '3', '3'),
	(4, 1, '白色', '2015-07-29 22:04:44', NULL, '4', '4'),
	(5, 1, '其他', '2015-07-29 22:04:59', NULL, '9', '9'),
	(6, 3, '客车', '2015-07-30 10:50:45', NULL, '10', '10'),
	(7, 3, '大型客车', '2015-07-30 10:50:45', 6, '11', '11'),
	(8, 3, '中型客车', '2015-07-30 10:50:45', 6, '12', '12'),
	(9, 3, '小型客车', '2015-07-30 10:50:45', 6, '13', '13'),
	(10, 3, '轿车', '2015-07-30 10:50:45', 6, '14', '14'),
	(11, 3, '大型卧铺客车', '2015-07-30 10:50:45', 6, '15', '15'),
	(12, 3, '中型卧铺客车', '2015-07-30 10:50:45', 6, '16', '16'),
	(13, 3, '普通货车', '2015-07-30 10:50:45', NULL, '20', '20'),
	(14, 3, '大型普通货车', '2015-07-30 10:50:45', 13, '21', '21'),
	(16, 3, '中型普通货车', '2015-07-30 10:50:45', 13, '22', '22'),
	(17, 3, '小型普通货车', '2015-07-30 10:50:45', 13, '23', '23'),
	(18, 3, '专用运输车', '2015-07-30 10:50:45', NULL, '30', '30'),
	(19, 3, '集装箱车', '2015-07-30 10:50:45', 18, '31', '31'),
	(20, 3, '大件运输车', '2015-07-30 10:50:45', 18, '32', '32'),
	(21, 3, '保温冷藏车', '2015-07-30 10:50:45', 18, '33', '33'),
	(22, 3, '商品车运输专用车辆', '2015-07-30 10:50:45', 18, '34', '34'),
	(23, 3, '罐车', '2015-07-30 10:50:45', 18, '35', '35'),
	(24, 3, '牵引车', '2015-07-30 10:50:45', 18, '36', '36'),
	(25, 3, '挂车', '2015-07-30 10:50:45', 18, '37', '37'),
	(26, 3, '平板车', '2015-07-30 10:50:45', 18, '38', '38'),
	(27, 3, '其他专用车', '2015-07-30 10:50:45', 18, '39', '39'),
	(28, 3, '危险品运输车', '2015-07-30 10:50:45', NULL, '40', '40'),
	(29, 3, '农用车', '2015-07-30 10:50:45', NULL, '50', '50'),
	(30, 3, '拖拉车', '2015-07-30 10:50:45', NULL, '60', '60'),
	(31, 3, '轮式拖拉车', '2015-07-30 10:50:45', 30, '61', '61'),
	(32, 3, '手扶拖拉机', '2015-07-30 10:50:45', 30, '62', '62'),
	(33, 3, '履带拖拉机', '2015-07-30 10:50:45', 30, '63', '63'),
	(34, 3, '特种拖拉机', '2015-07-30 10:50:45', 30, '64', '64'),
	(35, 3, '其他车辆', '2015-07-30 10:50:45', NULL, '90', '90'),
	(36, 2, '白色', '2015-07-30 10:55:50', NULL, '', '白色'),
	(37, 2, '红色', '2015-07-30 10:55:51', NULL, '', '红色'),
	(38, 2, '绿色', '2015-07-30 10:55:52', NULL, '', '绿色'),
	(39, 2, '黑色', '2015-07-30 10:55:58', NULL, '', '黑色'),
	(40, 2, '蓝色', '2015-07-30 10:55:56', NULL, '', '蓝色'),
	(41, 4, '客车', '2015-07-30 11:49:10', NULL, '', '1'),
	(42, 4, '货车', '2015-07-30 11:49:24', NULL, '', '2'),
	(43, 4, '小轿车', '2015-07-30 11:49:39', NULL, '', '3'),
	(44, 4, '船', '2015-07-30 11:50:03', NULL, '', '4'),
	(45, 4, '摩托车', '2015-07-30 11:50:22', NULL, '', '5'),
	(46, 5, '北京市', '2015-07-30 11:51:55', NULL, '110000', '110000'),
	(47, 5, '天津市', '2015-07-30 11:52:14', NULL, '120000', '120000'),
	(48, 5, '河北省', '2015-07-30 11:52:39', NULL, '130000', '130000'),
	(49, 5, '山西省', '2015-07-30 11:52:58', NULL, '140000', '140000'),
	(50, 5, '内蒙古自治区', '2015-07-30 11:53:32', NULL, '150000', '150000'),
	(51, 5, '辽宁省', '2015-07-30 11:53:52', NULL, '210000', '210000'),
	(52, 5, '吉林省', '2015-07-30 11:54:07', NULL, '220000', '220000'),
	(53, 5, '黑龙江省', '2015-07-30 11:54:27', NULL, '230000', '230000'),
	(54, 5, '上海市', '2015-07-30 11:54:44', NULL, '310000', '310000'),
	(55, 5, '江苏省', '2015-07-30 11:55:02', NULL, '320000', '320000'),
	(56, 5, '浙江省', '2015-07-30 11:55:17', NULL, '330000', '330000'),
	(57, 5, '安徽省', '2015-07-30 11:55:38', NULL, '340000', '340000'),
	(58, 5, '福建省', '2015-07-30 11:55:56', NULL, '350000', '350000'),
	(59, 5, '江西省', '2015-07-30 11:56:13', NULL, '360000', '360000'),
	(60, 5, '山东省', '2015-07-30 11:56:30', NULL, '370000', '370000'),
	(61, 5, '河南省', '2015-07-30 11:56:47', NULL, '410000', '410000'),
	(62, 5, '湖北省', '2015-07-30 11:57:02', NULL, '420000', '420000'),
	(63, 5, '湖南省', '2015-07-30 11:57:20', NULL, '430000', '430000'),
	(64, 5, '广东省', '2015-07-30 11:57:36', NULL, '440000', '440000'),
	(65, 5, '广西壮族自治区', '2015-07-30 11:58:02', NULL, '450000', '450000'),
	(66, 5, '海南省', '2015-07-30 11:58:30', NULL, '460000', '460000'),
	(67, 5, '重庆市', '2015-07-30 11:58:47', NULL, '500000', '500000'),
	(68, 5, '四川省', '2015-07-30 11:59:12', NULL, '510000', '510000'),
	(69, 5, '贵州省', '2015-07-30 11:59:33', NULL, '520000', '520000'),
	(70, 5, '云南省', '2015-07-30 11:59:52', NULL, '530000', '530000'),
	(71, 5, '藏族自治区', '2015-07-30 12:00:18', NULL, '540000', '540000'),
	(72, 5, '陕西省', '2015-07-30 12:00:35', NULL, '610000', '610000'),
	(73, 5, '甘肃省', '2015-07-30 12:01:02', NULL, '620000', '620000'),
	(74, 5, '青海省', '2015-07-30 12:01:26', NULL, '630000', '630000'),
	(75, 5, '宁夏回族自治区', '2015-07-30 12:02:12', NULL, '640000', '640000'),
	(76, 5, '新疆维吾尔族自治区', '2015-07-30 12:02:44', NULL, '650000', '650000'),
	(77, 5, '台湾省', '2015-07-30 12:03:12', NULL, '710000', '710000'),
	(78, 5, '香港特别行政区', '2015-07-30 12:03:42', NULL, '720000', '720000'),
	(79, 5, '澳门特别行政区', '2015-07-30 12:04:06', NULL, '730000', '730000'),
	(80, 6, '12V', '2015-07-30 22:59:11', NULL, '', '1'),
	(81, 6, '24V', '2015-07-30 22:59:20', NULL, '', '2'),
	(82, 7, '呼入', '2015-07-31 11:08:06', NULL, '', '1'),
	(83, 7, '呼出', '2015-07-31 11:08:17', NULL, '', '2'),
	(84, 7, '呼入呼出', '2015-07-31 11:08:37', NULL, '', '3'),
	(85, 9, '联XXX', '2015-08-15 10:03:58', 0, '', '1'),
	(86, 9, '翰XXX', '2015-08-15 10:04:05', 0, '', '2'),
	(87, 9, '世XXX', '2015-08-15 10:04:12', 0, '', '3'),
	(88, 8, '星XXX', '2015-08-15 10:06:29', 0, '', '1'),
	(89, 8, 'XA-TY-101', '2015-07-31 15:39:22', 88, '', '11'),
	(90, 8, 'XA-BJ-101', '2015-07-31 15:41:11', 88, '', '12'),
	(91, 8, '翰XXX', '2015-08-15 10:08:15', 0, '', '2'),
	(92, 8, 'HS-BB-454', '2015-07-31 15:40:44', 91, '', '21'),
	(93, 10, '巡逻队', '2015-07-31 20:25:02', NULL, '', '1'),
	(94, 10, '后勤运输队', '2015-07-31 20:25:27', NULL, '', '2'),
	(95, 11, '日常保养', '2015-08-01 14:06:30', NULL, '', '1'),
	(96, 11, '专业保养', '2015-08-01 14:06:55', NULL, '', '2'),
	(97, 12, '加油站', '2015-08-05 18:28:58', NULL, '', '1'),
	(98, 12, '收费站', '2015-08-05 18:29:08', NULL, '', '2'),
	(99, 12, '停车场', '2015-08-05 18:29:20', NULL, '', '3'),
	(100, 13, '身份证', '2015-08-06 15:10:10', NULL, '', '1'),
	(101, 13, '驾驶证', '2015-08-06 15:10:19', NULL, '', '2'),
	(102, 13, '学生证', '2015-08-06 15:10:29', NULL, '', '3'),
	(103, 8, 'HS-BJ-454', '2015-08-15 10:31:05', 91, '', '22'),
	(104, 14, '通知客户', '2015-08-28 08:33:49', NULL, '', '1'),
	(105, 14, '没联系上', '2015-08-28 08:34:08', NULL, '', '2'),
	(106, 14, '设备故障', '2015-08-28 08:34:23', NULL, '', '3'),
	(107, 14, '修车', '2015-08-28 08:34:33', NULL, '', '4'),
	(108, 14, '不处理', '2015-08-28 08:34:42', NULL, '', '5'),
	(109, 14, '将来处理', '2015-08-28 08:34:57', NULL, '', '6'),
	(110, 14, '其他', '2015-08-28 08:35:06', NULL, '', '7'),
	(111, 13, '工作证', '2015-11-09 17:29:55', NULL, '', '4');
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;


-- 导出  表 monitordb.driver 结构
CREATE TABLE IF NOT EXISTS `driver` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='司机资料';

-- 正在导出表  monitordb.driver 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;


-- 导出  表 monitordb.eventmenu 结构
CREATE TABLE IF NOT EXISTS `eventmenu` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `EVENTID` tinyint(3) unsigned NOT NULL COMMENT '类型',
  `CONTENT` varchar(30) NOT NULL COMMENT '事件内容',
  PRIMARY KEY (`DEVICENUMBER`,`EVENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='事件菜单';

-- 正在导出表  monitordb.eventmenu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `eventmenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventmenu` ENABLE KEYS */;


-- 导出  表 monitordb.eventreport 结构
CREATE TABLE IF NOT EXISTS `eventreport` (
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
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='事件报告';

-- 正在导出表  monitordb.eventreport 的数据：0 rows
/*!40000 ALTER TABLE `eventreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventreport` ENABLE KEYS */;


-- 导出  表 monitordb.info 结构
CREATE TABLE IF NOT EXISTS `info` (
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
  KEY `COMPANYID` (`COMPANYID`),
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`),
  KEY `STARTTIME_ENDTIME` (`STARTTIME`,`ENDTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息表';

-- 正在导出表  monitordb.info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `info` DISABLE KEYS */;
/*!40000 ALTER TABLE `info` ENABLE KEYS */;


-- 导出  表 monitordb.infomenu 结构
CREATE TABLE IF NOT EXISTS `infomenu` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '类型',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息菜单';

-- 正在导出表  monitordb.infomenu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `infomenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `infomenu` ENABLE KEYS */;


-- 导出  表 monitordb.infomenuindevice 结构
CREATE TABLE IF NOT EXISTS `infomenuindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '信息类型',
  `NAME` varchar(30) NOT NULL COMMENT '信息名称',
  `ACTION` tinyint(3) unsigned NOT NULL COMMENT '操作',
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息菜单设置';

-- 正在导出表  monitordb.infomenuindevice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `infomenuindevice` DISABLE KEYS */;
/*!40000 ALTER TABLE `infomenuindevice` ENABLE KEYS */;


-- 导出  表 monitordb.infoservice 结构
CREATE TABLE IF NOT EXISTS `infoservice` (
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
  KEY `STARTTIME_ENDTIME` (`STARTTIME`,`ENDTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息服务';

-- 正在导出表  monitordb.infoservice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `infoservice` DISABLE KEYS */;
/*!40000 ALTER TABLE `infoservice` ENABLE KEYS */;


-- 导出  表 monitordb.infosubscribe 结构
CREATE TABLE IF NOT EXISTS `infosubscribe` (
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `INFOTYPE` tinyint(4) NOT NULL COMMENT '信息类型',
  PRIMARY KEY (`DEVICENUMBER`,`INFOTYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息订阅';

-- 正在导出表  monitordb.infosubscribe 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `infosubscribe` DISABLE KEYS */;
/*!40000 ALTER TABLE `infosubscribe` ENABLE KEYS */;


-- 导出  表 monitordb.log 结构
CREATE TABLE IF NOT EXISTS `log` (
  `TIME` datetime NOT NULL COMMENT '时间',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) NOT NULL COMMENT '用户编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户姓名',
  `OPERATION` varchar(50) NOT NULL COMMENT '操作',
  KEY `TIME_COMPANYID` (`TIME`,`COMPANYID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- 正在导出表  monitordb.log 的数据：54 rows
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` (`TIME`, `COMPANYID`, `USERID`, `USERNAME`, `OPERATION`) VALUES
	('2015-11-16 11:12:28', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开统计分析页面'),
	('2015-11-16 11:12:29', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开监控中心页面'),
	('2015-11-16 11:12:30', '558ffc6603c70e31a2a53a30', '563321b03dd6ef06eea077bc', '王生', '打开实时监控页面'),
	('2015-11-26 10:33:04', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-26 10:34:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-26 10:39:09', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-26 10:39:56', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-26 10:46:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2015-11-26 10:46:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2015-11-26 10:46:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2015-11-26 10:46:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面'),
	('2015-11-26 10:47:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-26 10:47:14', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2015-11-26 10:47:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2015-11-26 11:01:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2015-11-26 11:01:02', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2015-11-26 11:01:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2015-11-26 11:01:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车主管理页面'),
	('2015-11-26 11:01:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面'),
	('2015-11-26 11:01:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面'),
	('2015-11-26 11:01:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面'),
	('2015-11-26 11:01:18', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆保养页面'),
	('2015-11-26 11:01:19', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开数据字典页面'),
	('2015-11-30 18:14:35', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2015-11-30 18:14:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2015-11-30 18:14:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2015-11-30 18:14:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2015-11-30 18:14:42', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开企业管理页面'),
	('2016-01-12 14:04:39', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2016-01-12 14:04:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2016-01-12 14:04:45', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2016-01-12 14:05:05', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2016-01-12 14:05:08', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车队管理页面'),
	('2016-01-12 14:05:10', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开统计分析页面'),
	('2016-01-12 14:05:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开历史上线率统计页面'),
	('2016-01-12 14:05:13', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开当前在线率统计页面'),
	('2016-01-12 14:05:15', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开非区域超速统计页面'),
	('2016-01-12 14:05:16', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆区域超速统计页面'),
	('2016-01-12 14:05:17', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开路线偏离统计页面'),
	('2016-01-12 14:05:23', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆路段超速统计页面'),
	('2016-01-12 14:22:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2016-01-12 14:22:38', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面'),
	('2016-01-12 14:22:40', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面'),
	('2016-01-12 14:22:51', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开车辆管理页面'),
	('2016-01-22 13:00:34', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '用户录登:0:0:0:0:0:0:0:1'),
	('2016-01-22 13:00:41', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2016-01-22 13:00:44', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面'),
	('2016-01-22 13:18:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2016-01-22 13:18:48', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2016-01-22 13:39:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开监控中心页面'),
	('2016-01-22 13:39:12', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开实时监控页面'),
	('2016-01-22 13:41:27', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开基础信息页面'),
	('2016-01-22 13:41:29', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开角色管理页面'),
	('2016-01-22 13:41:50', '558ffc6603c70e31a2a53a30', '558ffc6603c70e31a2a53a30', '系统管理员', '打开用户管理页面');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;


-- 导出  表 monitordb.maintain 结构
CREATE TABLE IF NOT EXISTS `maintain` (
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
  KEY `VEHICLEID_DONEDATE` (`VEHICLEID`,`DONEDATE`),
  KEY `VEHICLEID_NEXTDATE` (`VEHICLEID`,`NEXTDATE`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养记录';

-- 正在导出表  monitordb.maintain 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `maintain` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintain` ENABLE KEYS */;


-- 导出  表 monitordb.maplayer 结构
CREATE TABLE IF NOT EXISTS `maplayer` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `NAME` varchar(20) NOT NULL COMMENT '名称',
  `VISIBLE` bit(1) NOT NULL COMMENT '可见否',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `USERID` (`USERID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地图图层';

-- 正在导出表  monitordb.maplayer 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `maplayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `maplayer` ENABLE KEYS */;


-- 导出  表 monitordb.motorcade 结构
CREATE TABLE IF NOT EXISTS `motorcade` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='车队';

-- 正在导出表  monitordb.motorcade 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `motorcade` DISABLE KEYS */;
/*!40000 ALTER TABLE `motorcade` ENABLE KEYS */;


-- 导出  表 monitordb.multimediaeventreport 结构
CREATE TABLE IF NOT EXISTS `multimediaeventreport` (
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
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='多媒体事件报告';

-- 正在导出表  monitordb.multimediaeventreport 的数据：0 rows
/*!40000 ALTER TABLE `multimediaeventreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `multimediaeventreport` ENABLE KEYS */;


-- 导出  表 monitordb.multimediaretrieval 结构
CREATE TABLE IF NOT EXISTS `multimediaretrieval` (
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

-- 正在导出表  monitordb.multimediaretrieval 的数据：0 rows
/*!40000 ALTER TABLE `multimediaretrieval` DISABLE KEYS */;
/*!40000 ALTER TABLE `multimediaretrieval` ENABLE KEYS */;


-- 导出  表 monitordb.owner 结构
CREATE TABLE IF NOT EXISTS `owner` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `OWNERNAME` varchar(30) NOT NULL COMMENT '姓名',
  `IDTYPE` varchar(30) NOT NULL COMMENT '证件类型',
  `IDNUMBER` varchar(30) NOT NULL COMMENT '证件编号',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车主资料';

-- 正在导出表  monitordb.owner 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;


-- 导出  表 monitordb.phonebook 结构
CREATE TABLE IF NOT EXISTS `phonebook` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司编号',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `PHONE` varchar(20) NOT NULL COMMENT '电话',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电话本';

-- 正在导出表  monitordb.phonebook 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `phonebook` DISABLE KEYS */;
/*!40000 ALTER TABLE `phonebook` ENABLE KEYS */;


-- 导出  表 monitordb.phoneindevice 结构
CREATE TABLE IF NOT EXISTS `phoneindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `FLAG` tinyint(4) NOT NULL COMMENT '通话标识',
  `PHONE` varchar(20) NOT NULL COMMENT '电话号码',
  `NAME` varchar(20) NOT NULL COMMENT '姓名',
  `ACTION` tinyint(4) NOT NULL COMMENT '操作类型',
  `STATUS` varchar(30) NOT NULL COMMENT '状态',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电话本设置';

-- 正在导出表  monitordb.phoneindevice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `phoneindevice` DISABLE KEYS */;
/*!40000 ALTER TABLE `phoneindevice` ENABLE KEYS */;


-- 导出  表 monitordb.poi 结构
CREATE TABLE IF NOT EXISTS `poi` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `TYPE` varchar(30) NOT NULL COMMENT '类别',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兴趣点';

-- 正在导出表  monitordb.poi 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `poi` DISABLE KEYS */;
/*!40000 ALTER TABLE `poi` ENABLE KEYS */;


-- 导出  表 monitordb.polygon 结构
CREATE TABLE IF NOT EXISTS `polygon` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='多边形区域';

-- 正在导出表  monitordb.polygon 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `polygon` DISABLE KEYS */;
/*!40000 ALTER TABLE `polygon` ENABLE KEYS */;


-- 导出  表 monitordb.polygonpoint 结构
CREATE TABLE IF NOT EXISTS `polygonpoint` (
  `PID` int(10) unsigned NOT NULL COMMENT '多边形编号',
  `LAT` decimal(9,6) NOT NULL COMMENT '纬度',
  `LNG` decimal(9,6) NOT NULL COMMENT '经度',
  KEY `PID_INDEX` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多边形顶点项';

-- 正在导出表  monitordb.polygonpoint 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `polygonpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `polygonpoint` ENABLE KEYS */;


-- 导出  表 monitordb.question 结构
CREATE TABLE IF NOT EXISTS `question` (
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
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

-- 正在导出表  monitordb.question 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;


-- 导出  表 monitordb.questionanswer 结构
CREATE TABLE IF NOT EXISTS `questionanswer` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `PID` char(24) NOT NULL COMMENT '父记录唯一编号',
  `ANSWERID` tinyint(3) unsigned NOT NULL COMMENT '答案编号',
  `CONTENT` varchar(50) NOT NULL COMMENT '答案内容',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `PID` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题答案';

-- 正在导出表  monitordb.questionanswer 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `questionanswer` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionanswer` ENABLE KEYS */;


-- 导出  表 monitordb.questionindevice 结构
CREATE TABLE IF NOT EXISTS `questionindevice` (
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
  KEY `DEVICENUMBER` (`DEVICENUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='提问下发';

-- 正在导出表  monitordb.questionindevice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `questionindevice` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionindevice` ENABLE KEYS */;


-- 导出  表 monitordb.rectangle 结构
CREATE TABLE IF NOT EXISTS `rectangle` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='矩形区域';

-- 正在导出表  monitordb.rectangle 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `rectangle` DISABLE KEYS */;
/*!40000 ALTER TABLE `rectangle` ENABLE KEYS */;


-- 导出  表 monitordb.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `ID` char(24) NOT NULL COMMENT '记录号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司id',
  `NAME` varchar(30) NOT NULL COMMENT '名称',
  `REMARK` varchar(50) NOT NULL COMMENT '备注',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `COMPANYID_NAME` (`COMPANYID`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- 正在导出表  monitordb.role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- 导出  表 monitordb.roleauthorize 结构
CREATE TABLE IF NOT EXISTS `roleauthorize` (
  `COMPANYID` char(24) NOT NULL COMMENT '企业ID',
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  `PERMISSIONID` varchar(100) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`COMPANYID`,`ROLEID`,`PERMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色授权表';

-- 正在导出表  monitordb.roleauthorize 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `roleauthorize` DISABLE KEYS */;
/*!40000 ALTER TABLE `roleauthorize` ENABLE KEYS */;


-- 导出  表 monitordb.roleinuser 结构
CREATE TABLE IF NOT EXISTS `roleinuser` (
  `USERID` char(24) NOT NULL COMMENT '用户ID',
  `ROLEID` char(24) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USERID`,`ROLEID`),
  KEY `FK_roleinuser_role` (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色表';

-- 正在导出表  monitordb.roleinuser 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `roleinuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `roleinuser` ENABLE KEYS */;


-- 导出  表 monitordb.route 结构
CREATE TABLE IF NOT EXISTS `route` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='路线区域';

-- 正在导出表  monitordb.route 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;


-- 导出  表 monitordb.routesection 结构
CREATE TABLE IF NOT EXISTS `routesection` (
  `ROUTEID` int(11) unsigned NOT NULL COMMENT '路线唯一编号',
  `SECTIONID` int(11) unsigned NOT NULL COMMENT '路段唯一编号',
  PRIMARY KEY (`ROUTEID`,`SECTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线路段表';

-- 正在导出表  monitordb.routesection 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `routesection` DISABLE KEYS */;
/*!40000 ALTER TABLE `routesection` ENABLE KEYS */;


-- 导出  表 monitordb.section 结构
CREATE TABLE IF NOT EXISTS `section` (
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
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='路段';

-- 正在导出表  monitordb.section 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;


-- 导出  表 monitordb.sectionpoint 结构
CREATE TABLE IF NOT EXISTS `sectionpoint` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '拐点id',
  `SECTIONID` int(10) unsigned NOT NULL COMMENT '路段id',
  `LAT` decimal(9,6) NOT NULL COMMENT '拐点纬度',
  `LNG` decimal(9,6) NOT NULL COMMENT '拐点经度',
  `INDEX` int(11) NOT NULL COMMENT '序号',
  PRIMARY KEY (`ID`),
  KEY `SECTIONID` (`SECTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='路线拐点';

-- 正在导出表  monitordb.sectionpoint 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sectionpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `sectionpoint` ENABLE KEYS */;


-- 导出  表 monitordb.simcard 结构
CREATE TABLE IF NOT EXISTS `simcard` (
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
  UNIQUE KEY `SIMCARDNUMBER` (`SIMCARDNUMBER`),
  UNIQUE KEY `PHONENUMBER` (`PHONENUMBER`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sim卡资料';

-- 正在导出表  monitordb.simcard 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `simcard` DISABLE KEYS */;
/*!40000 ALTER TABLE `simcard` ENABLE KEYS */;


-- 导出  表 monitordb.textmessage 结构
CREATE TABLE IF NOT EXISTS `textmessage` (
  `ID` char(24) NOT NULL COMMENT '记录唯一编号',
  `COMPANYID` char(24) NOT NULL COMMENT '公司唯一编号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `CONTENT` text NOT NULL COMMENT '文本内容',
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERNAME` varchar(20) NOT NULL COMMENT '用户名',
  `USERTIME` datetime NOT NULL COMMENT '记录时间',
  `EDITTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`ID`),
  KEY `COMPANYID` (`COMPANYID`),
  KEY `USERID_USERTIME` (`USERID`,`USERTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本信息';

-- 正在导出表  monitordb.textmessage 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `textmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `textmessage` ENABLE KEYS */;


-- 导出  表 monitordb.textmessageindevice 结构
CREATE TABLE IF NOT EXISTS `textmessageindevice` (
  `ID` char(36) NOT NULL COMMENT '记录唯一编号',
  `DEVICENUMBER` varchar(20) NOT NULL COMMENT '设备号',
  `FLAG` tinyint(3) unsigned NOT NULL COMMENT '标志',
  `CONTENT` text NOT NULL COMMENT '文本内容',
  `STATUS` varchar(50) NOT NULL COMMENT '状态',
  `SENDTIME` datetime NOT NULL COMMENT '绑定时间',
  `ACKTIME` datetime DEFAULT NULL COMMENT '命令正确应答时间',
  PRIMARY KEY (`ID`),
  KEY `DEVICENUMBER` (`DEVICENUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='文本信息下发';

-- 正在导出表  monitordb.textmessageindevice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `textmessageindevice` DISABLE KEYS */;
/*!40000 ALTER TABLE `textmessageindevice` ENABLE KEYS */;


-- 导出  表 monitordb.upgradereport 结构
CREATE TABLE IF NOT EXISTS `upgradereport` (
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
  KEY `DEVICENUMBER_TIME_READED` (`DEVICENUMBER`,`TIME`,`READED`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='升级结果通知';

-- 正在导出表  monitordb.upgradereport 的数据：0 rows
/*!40000 ALTER TABLE `upgradereport` DISABLE KEYS */;
/*!40000 ALTER TABLE `upgradereport` ENABLE KEYS */;


-- 导出  表 monitordb.user 结构
CREATE TABLE IF NOT EXISTS `user` (
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
  UNIQUE KEY `UN_ACCOUNT` (`ACCOUNT`),
  KEY `IX_PID` (`PID`),
  KEY `COMPANYID` (`COMPANYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- 正在导出表  monitordb.user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `UNID`, `COMPANYID`, `KIND`, `ACCOUNT`, `PASSWORD`, `NAME`, `EMAIL`, `PHONE`, `CONTACT`, `CREATETIME`, `ENABLE`, `EDITTIME`, `PID`, `SERVICESTARTDATE`, `SERVICEENDDATE`, `REMARK`, `QUESTION1`, `ANSWER1`, `QUESTION2`, `ANSWER2`) VALUES
	('558ffc6603c70e31a2a53a30', 'a64b9c76-1d9c-11e5-b3dd-34363bd42eba', '558ffc6603c70e31a2a53a30', 0, 'admin', '123456', '系统管理员', 'yangxp999@163.com', '13714196239', '杨生', '2014-10-10 17:43:14', b'1', '2015-06-28 22:00:53', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- 导出  表 monitordb.usermonitor 结构
CREATE TABLE IF NOT EXISTS `usermonitor` (
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `TARGETID` char(24) NOT NULL COMMENT '目标唯一编号',
  PRIMARY KEY (`USERID`,`TARGETID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户监控范围';

-- 正在导出表  monitordb.usermonitor 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `usermonitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `usermonitor` ENABLE KEYS */;


-- 导出  表 monitordb.useroptions 结构
CREATE TABLE IF NOT EXISTS `useroptions` (
  `USERID` char(24) NOT NULL COMMENT '用户唯一编号',
  `USERKEY` varchar(30) NOT NULL COMMENT '用户自定义键',
  `USERVALUE` text NOT NULL COMMENT '用户自定义设置',
  PRIMARY KEY (`USERID`,`USERKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户自定义设置';

-- 正在导出表  monitordb.useroptions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `useroptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `useroptions` ENABLE KEYS */;


-- 导出  表 monitordb.vehicle 结构
CREATE TABLE IF NOT EXISTS `vehicle` (
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
  UNIQUE KEY `PLATENUMBER` (`PLATENUMBER`),
  KEY `COMPANYID` (`COMPANYID`),
  KEY `MOTORCADEID` (`MOTORCADEID`),
  KEY `DEVICEID` (`DEVICEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆表';

-- 正在导出表  monitordb.vehicle 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;


-- 导出  表 monitordb.vehicleindriver 结构
CREATE TABLE IF NOT EXISTS `vehicleindriver` (
  `DRIVERID` char(24) NOT NULL COMMENT '司机唯一编号',
  `VEHICLEID` char(24) NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`DRIVERID`,`VEHICLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆与司机关系';

-- 正在导出表  monitordb.vehicleindriver 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `vehicleindriver` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicleindriver` ENABLE KEYS */;


-- 导出  表 monitordb.vehicleinowner 结构
CREATE TABLE IF NOT EXISTS `vehicleinowner` (
  `OWNERID` char(24) NOT NULL COMMENT '车主唯一编号',
  `VEHICLEID` char(24) NOT NULL COMMENT '车辆唯一编号',
  PRIMARY KEY (`OWNERID`,`VEHICLEID`),
  KEY `FK_vehicleinowner_vehilce` (`VEHICLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆所属车主';

-- 正在导出表  monitordb.vehicleinowner 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `vehicleinowner` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicleinowner` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
