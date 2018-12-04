package mmp.gps.logic.dao;

import mmp.gps.domain.flow.FlowDto;

import java.util.Date;
import java.util.List;

public interface IFlowDao {

    List<FlowDto> load(String number, Date start, Date end) throws Exception;

}
