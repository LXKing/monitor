package com.edata.monitor.dao.baseinfo;

import java.util.ArrayList;
import java.util.List;

import com.edata.common.LatLng;

public class PolygonAreaInfoDto {
	public long id;
	public String name;
	public boolean deviceCatch;
	public String remark;
	public List<LatLng> points = new ArrayList<LatLng>();
}
