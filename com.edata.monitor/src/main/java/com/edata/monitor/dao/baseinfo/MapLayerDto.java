package com.edata.monitor.dao.baseinfo;

import java.sql.Timestamp;
import java.util.List;

public class MapLayerDto {
	public String id;

	public String companyId;

	public String userId;

	public String name;

	public boolean visible;

	public String remark;

	public Timestamp editTime;

	public List<Long> circleAreas;

	public List<Long> rectangleAreas;

	public List<Long> polygonAreas;

	public List<Long> routeAreas;

	public List<Long> pois;
}
