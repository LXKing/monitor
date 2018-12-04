package mmp.gps.monitor.area;


import mmp.gps.common.util.GisUtil;
import mmp.gps.common.enums.AreaKinds;
import mmp.gps.domain.area.CircleArea;
import mmp.gps.domain.locate.Latest;

import java.util.Date;

public class CircleAreaCatcher extends AreaCatcher {
    private CircleArea circleArea;

    public CircleArea getCircleArea() {
        return circleArea;
    }

    public void setCircleArea(CircleArea circleArea) {
        this.circleArea = circleArea;
    }

    @Override
    public boolean hasAlarm(Latest latest) {
        if (circleArea == null)
            return false;
        // 根据时间
        if ((circleArea.getFlag() & 0x01) == 0x1) {
            long now = new Date().getTime();
            if (circleArea.getStartTime() != null) {
                long start = circleArea.getStartTime().getTime();
                if (start > now)
                    return false;
            }
            if (circleArea.getEditTime() != null) {
                long end = circleArea.getEditTime().getTime();
                if (end < now)
                    return false;
            }
        }
        boolean result = false;
        // 限速
        if (((circleArea.getFlag() >> 1) & 0x01) == 0x1) {
            if (latest.getSp() > circleArea.getMaxSpeed()) {
                latest.setA(latest.getA() | 0x02);
                latest.setOvt((byte) AreaKinds.CircleArea.getIndex());
                latest.setOid(circleArea.getId());
                result = true;
            }
        }
        double lng1 = latest.getLng();
        double lat1 = latest.getLat();
        double lng2 = circleArea.getLng();
        double lat2 = circleArea.getLat();

        double distance = GisUtil.distance(lng1, lat1, lng2, lat2);
        boolean inside = distance <= circleArea.getRadius();
        // 进区域报警给平台
        if (((circleArea.getFlag() >> 3) & 0x01) == 0x1) {
            if (inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.CircleArea.getIndex());
                latest.setIid(circleArea.getId());
                latest.setIof((byte) 0);
                result = true;
            }
        }
        // 出区域报警给平台
        if (((circleArea.getFlag() >> 5) & 0x01) == 0x1) {
            if (!inside) {
                latest.setA(latest.getA() | (1 << 20));
                latest.setIot((byte) AreaKinds.CircleArea.getIndex());
                latest.setIid(circleArea.getId());
                latest.setIof((byte) 1);
                result = true;
            }
        }

        return result;
    }
}
