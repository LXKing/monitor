package mmp.gps.domain.area;


import mmp.gps.common.util.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PolygonAreaInfoDto {
    public long id;
    public String name;
    public boolean deviceCatch;
    public String remark;
    public List<LatLng> points = new ArrayList<LatLng>();
}
