package mmp.gps.monitor.area;

import mmp.gps.common.util.GisUtil;
import mmp.gps.common.util.LatLng;
import mmp.gps.common.enums.AreaKinds;
import mmp.gps.domain.area.PolygonArea;
import mmp.gps.domain.locate.Latest;

import java.util.Date;

public class PolygonAreaCatcher extends AreaCatcher {
    private LatLng[] points = null;
    private PolygonArea polygonArea;

    public PolygonArea getPolygonArea() {
        return polygonArea;
    }

    public void setPolygonArea(PolygonArea polygonArea) {
        this.polygonArea = polygonArea;
        this.points = polygonArea.getPoints().toArray(new LatLng[0]);
    }

    @Override
    public boolean hasAlarm(Latest latest) {
        // 根据时间
        if ((polygonArea.getFlag() & 0x01) == 0x1) {
            long now = new Date().getTime();
            if (polygonArea.getStartTime() != null) {
                long start = polygonArea.getStartTime().getTime();

                if (start > now)
                    return false;
            }
            if (polygonArea.getEditTime() != null) {
                long end = polygonArea.getEditTime().getTime();
                if (end < now)
                    return false;
            }
        }
        boolean result = false;
        // 限速
        if (((polygonArea.getFlag() >> 1) & 0x01) == 0x1) {
            if (latest.getSp() > polygonArea.getMaxSpeed()) {
                latest.setA(latest.getA() | 0x02);
                latest.setOvt((byte) AreaKinds.CircleArea.getIndex());
                latest.setOid(polygonArea.getId());
                result = true;
            }
        }
        LatLng pt = LatLng.of(latest.getLat(), latest.getLng());

        boolean inside = GisUtil.isInsidePolygon(pt, points);
        // 进区域报警给平台
        if (((polygonArea.getFlag() >> 3) & 0x01) == 0x1) {
            if (inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.CircleArea.getIndex());
                latest.setIid(polygonArea.getId());
                latest.setIof((byte) 0);
                result = true;
            }
        }
        // 出区域报警给平台
        if (((polygonArea.getFlag() >> 5) & 0x01) == 0x1) {
            if (!inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.CircleArea.getIndex());
                latest.setIid(polygonArea.getId());
                latest.setIof((byte) 1);
                result = true;
            }
        }

        return result;
    }

}
