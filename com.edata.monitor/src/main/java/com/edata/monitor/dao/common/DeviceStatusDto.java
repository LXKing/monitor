package com.edata.monitor.dao.common;

import java.util.Date;

public class DeviceStatusDto {
	public String number;

	public boolean debugging;

	public boolean upgrading;

	public boolean matching;

	public boolean sleeping;

	public boolean repairing;

	public String preVer;

	public Date upgradeStart;

	public String curVer;

	public Date upgradeEnd;

	public String matchResult;

	public Date matchTime;

}
