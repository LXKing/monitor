package mmp.gps.domain.area;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SectionAreaDto {
    public long id;
    public String companyId;
    public String name;
    public short width;
    public short flag;
    public int maxSeconds;
    public int minSeconds;
    public int maxSpeed;
    public short overspeedSeconds;
    public String remark;
    public Timestamp editTime;
    public List<SectionPointDto> points = new ArrayList<SectionPointDto>();
}
