package mmp.gps.domain.area;

import java.sql.Timestamp;
import java.util.Date;

public class RouteAreaDto {
    public long id;
    public String companyId;
    public String name;
    public boolean deviceCatch;
    public int flag;
    public Date startTime;
    public Date endTime;
    public String remark;
    public Timestamp editTime;
}
