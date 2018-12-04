package mmp.gps.logic.dao;

import mmp.gps.domain.fault.FaultQueryDto;

import java.util.Date;
import java.util.List;

public interface IFaultDao {

    List<FaultQueryDto> load(String number, Date start, Date end) throws Exception;

}
