package com.rayton.gps.dao.cache.area;

import com.rayton.gps.common.GisUtil;
import com.rayton.gps.common.LatLng;
import com.rayton.gps.dao.baseinfo.rectangleArea.RectangleArea;
import com.rayton.gps.dao.locate.Latest;
import com.rayton.gps.util.enums.AreaKinds;

import java.util.Date;

public class RectangleAreaCatcher extends AreaCatcher {
    private RectangleArea rectangleArea;

    public RectangleArea getRectangleArea() {
        return rectangleArea;
    }

    public void setRectangleArea(RectangleArea rectangleArea) {
        this.rectangleArea = rectangleArea;
    }

    @Override
    public boolean hasAlarm(Latest latest) {
        // 根据时间
        if ((rectangleArea.getFlag() & 0x01) == 0x1) {
            long now = new Date().getTime();
            if (rectangleArea.getStartTime() != null) {
                long start = rectangleArea.getStartTime().getTime();
                if (start > now)
                    return false;
            }
            if (rectangleArea.getEditTime() != null) {
                long end = rectangleArea.getEditTime().getTime();
                if (end < now)
                    return false;
            }
        }
        boolean result = false;
        // 限速
        if (((rectangleArea.getFlag() >> 1) & 0x01) == 0x1) {
            if (latest.getSp() > rectangleArea.getMaxSpeed()) {
                latest.setA(latest.getA() | 0x02);
                latest.setOvt((byte) AreaKinds.RectangleArea.getIndex());
                latest.setOid(rectangleArea.getId());
                result = true;
            }
        }
        LatLng wn = LatLng.of(rectangleArea.getUllat(), rectangleArea.getUllng());
        LatLng en = LatLng.of(rectangleArea.getUllat(), rectangleArea.getBrlng());
        LatLng es = LatLng.of(rectangleArea.getBrlat(), rectangleArea.getBrlng());
        LatLng ws = LatLng.of(rectangleArea.getBrlat(), rectangleArea.getUllng());

        LatLng pt = LatLng.of(latest.getLat(), latest.getLng());
        boolean inside = GisUtil.isInsidePolygon(pt, new LatLng[]{wn, en, es, ws});

        // 进区域报警给平台
        if (((rectangleArea.getFlag() >> 3) & 0x01) == 0x1) {
            if (inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.RectangleArea.getIndex());
                latest.setIid(rectangleArea.getId());
                latest.setIof((byte) 0);
                result = true;
            }
        }
        // 出区域报警给平台
        if (((rectangleArea.getFlag() >> 5) & 0x01) == 0x1) {
            if (!inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.RectangleArea.getIndex());
                latest.setIid(rectangleArea.getId());
                latest.setIof((byte) 1);
                result = true;
            }
        }

        return result;
    }
}
