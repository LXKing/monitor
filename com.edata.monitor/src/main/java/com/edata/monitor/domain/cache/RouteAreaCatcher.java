package com.edata.monitor.domain.cache;

import java.util.Date;
import java.util.List;

import com.edata.common.GisUtil;
import com.edata.common.LatLng;
import com.edata.common.Tuple;
import com.edata.monitor.domain.baseinfo.RouteArea;
import com.edata.monitor.domain.baseinfo.SectionArea;
import com.edata.monitor.domain.baseinfo.SectionPoint;
import com.edata.monitor.domain.locate.Latest;
import com.edata.monitor.util.kind.AreaKinds;

public class RouteAreaCatcher extends AreaCatcher {
	private RouteArea routeArea;

	public RouteArea getRouteArea() {
		return routeArea;
	}

	public void setRouteArea(RouteArea routeArea) {
		this.routeArea = routeArea;
	}

	private List<SectionArea> sections;

	public List<SectionArea> getSections() {
		return sections;
	}

	public void setSections(List<SectionArea> sections) {
		this.sections = sections;
	}

	@Override
	public boolean hasAlarm(Latest latest) {
		if (routeArea == null)
			return false;
		// 根据时间
		if ((routeArea.getFlag() & 0x01) == 0x1) {
			long now = new Date().getTime();
			if (routeArea.getStartTime() != null) {
				long start = routeArea.getStartTime().getTime();
				if (start > now)
					return false;
			}
			if (routeArea.getEditTime() != null) {
				long end = routeArea.getEditTime().getTime();
				if (end < now)
					return false;
			}
		}
		boolean result = false;

		Tuple<Boolean, Boolean> checkResult = checkSections(latest);
		if (checkResult != null) {
			// 进区域报警给平台
			if (((routeArea.getFlag() >> 3) & 0x01) == 0x1) {
				if (checkResult.e) {
					latest.setA(latest.getA() | (1 << 20));
					latest.setIot((byte) AreaKinds.RouteArea.getIndex());
					latest.setIid(routeArea.getId());
					latest.setIof((byte) 0);
					result = true;
				}
			}
			// 出区域报警给平台
			if (((routeArea.getFlag() >> 5) & 0x01) == 0x1) {
				if (!checkResult.e) {
					latest.setA(latest.getA() | (1 << 20));
					latest.setIot((byte) AreaKinds.RouteArea.getIndex());
					latest.setIid(routeArea.getId());
					latest.setIof((byte) 1);
					result = true;
				}
			}

			if (checkResult.t)
				result = true;
		}

		return result;
	}

	/**
	 * 路段检查，是否在路段内，是否有路段报警
	 */
	private Tuple<Boolean, Boolean> checkSections(Latest latest) {
		boolean inside = false;
		boolean hasAlarm = false;
		if (sections == null)
			return null;
		for (SectionArea section : sections) {
			// 是否在路段内
			LatLng p = LatLng.of(latest.getLat(), latest.getLng());
			double distance = minDistance(p, section.getPoints());
			if (distance <= section.getWidth() / 2) {
				inside = true;
			}

			// 限速
			if (((section.getFlag() >> 1) & 0x01) == 0x1) {
				if (latest.getSp() > section.getMaxSpeed()) {
					latest.setA(latest.getA() | 0x02);
					latest.setOvt((byte) AreaKinds.RouteArea.getIndex());
					latest.setOid(section.getId());
					hasAlarm = true;
				}
			}
		}

		return Tuple.of(inside, hasAlarm);
	}

	public static double minDistance(LatLng p, List<SectionPoint> points) {
		double min = -99999;
		for (int x = 1; x < points.size(); x++) {
			SectionPoint p1 = points.get(x - 1);
			SectionPoint p2 = points.get(x);

			double distance = GisUtil.pointToLine(p1.getLng(), p1.getLat(), p2.getLng(), p2.getLat(), p.lng, p.lat);
			if (min == -99999)
				min = distance;
			else if (distance <= min)
				min = distance;
		}

		return min;
	}

}
