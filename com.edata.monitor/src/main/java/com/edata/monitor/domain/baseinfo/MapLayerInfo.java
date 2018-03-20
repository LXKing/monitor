package com.edata.monitor.domain.baseinfo;

import java.util.ArrayList;
import java.util.List;

public class MapLayerInfo {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean visible;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private List<CircleAreaInfo> circleAreas = new ArrayList<CircleAreaInfo>();

	public List<CircleAreaInfo> getCircleAreas() {
		return circleAreas;
	}

	public void setCircleAreas(List<CircleAreaInfo> circleAreas) {
		this.circleAreas = circleAreas;
	}

	private List<RectangleAreaInfo> rectangleAreas = new ArrayList<RectangleAreaInfo>();

	public List<RectangleAreaInfo> getRectangleAreas() {
		return rectangleAreas;
	}

	public void setRectangleAreas(List<RectangleAreaInfo> rectangleAreas) {
		this.rectangleAreas = rectangleAreas;
	}

	private List<PolygonAreaInfo> polygonAreas = new ArrayList<PolygonAreaInfo>();

	public List<PolygonAreaInfo> getPolygonAreas() {
		return polygonAreas;
	}

	public void setPolygonAreas(List<PolygonAreaInfo> polygonAreas) {
		this.polygonAreas = polygonAreas;
	}

	private List<RouteAreaInfo> routeAreas = new ArrayList<RouteAreaInfo>();

	public List<RouteAreaInfo> getRouteAreas() {
		return routeAreas;
	}

	public void setRouteAreas(List<RouteAreaInfo> routeAreas) {
		this.routeAreas = routeAreas;
	}

	private List<PoiInfo> pois = new ArrayList<PoiInfo>();

	public List<PoiInfo> getPois() {
		return pois;
	}

	public void setPois(List<PoiInfo> pois) {
		this.pois = pois;
	}
}
