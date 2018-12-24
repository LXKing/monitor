package mmp.gps.domain.statistics;

import java.util.ArrayList;
import java.util.List;

public class VehicleOverspeedDto {
    public String number;
    public int times = 0;
    public long duration;

    public double LAT;
    public double LNG;


    public List<Object> OverP = new ArrayList<>();
}
