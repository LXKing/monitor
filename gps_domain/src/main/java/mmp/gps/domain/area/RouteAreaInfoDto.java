package mmp.gps.domain.area;

import java.util.ArrayList;
import java.util.List;

public class RouteAreaInfoDto {
    public long id;
    public String name;
    public boolean deviceCatch;
    public String remark;
    public List<SectionAreaInfoDto> sections = new ArrayList<SectionAreaInfoDto>();
}
