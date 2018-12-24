package mmp.gps.domain.area;

import java.sql.Timestamp;
import java.util.Date;

public class RectangleAreaDto {
    public long id;
    public String companyId;
    public String name;
    public boolean deviceCatch;
    public int flag;
    public double ullat;
    public double ullng;
    public double brlat;
    public double brlng;
    public int maxSpeed;
    public short overspeedSeconds;
    public Date startTime;
    public Date endTime;
    public String remark;
    public Timestamp editTime;
}
