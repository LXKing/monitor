package com.edata.monitor.dao.baseinfo;

import java.util.ArrayList;
import java.util.List;

public class SectionAreaInfoDto {
	public long id;
	public String name;
	public String remark;
	public List<SectionPointDto> points = new ArrayList<SectionPointDto>();
}
