package com.rayton.gps.dao.fault;

import java.util.Date;
import java.util.List;

public interface IFaultDao {

    List<FaultQueryDto> load(String number, Date start, Date end) throws Exception;

}
