package mmp.gps.logic.service;


import mmp.gps.common.util.DaoFactory;
import mmp.gps.domain.fault.FaultQuery;
import mmp.gps.domain.fault.FaultQueryDto;
import mmp.gps.domain.fault.FaultQueryRequest;
import mmp.gps.domain.fault.FaultQueryResponse;
import mmp.gps.logic.dao.IFaultDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FaultService {

    public static FaultQueryResponse query(FaultQueryRequest request) throws Exception {
        FaultQueryResponse response = new FaultQueryResponse();
        IFaultDao dao = (IFaultDao) DaoFactory.getAdo(IFaultDao.class);
        List list = dao.load(request.getNumber(), request.getStart(), request.getEnd());
        if (list != null && list.size() > 0) {
            ArrayList faults = new ArrayList(list.size());
            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                FaultQueryDto dto = (FaultQueryDto) var5.next();
                FaultQuery fault = new FaultQuery();
                fault.setNumber(dto.number);
                fault.setTime(dto.time);
                fault.setSystemId(dto.systemId);
                fault.setLevel(dto.level);
                fault.setDescriptionC(dto.descriptionC);
                fault.setDescriptionE(dto.descriptionE);
                fault.setSolution(dto.solution);
                fault.setBrand(dto.brand);
                fault.setSensors(dto.sensors);
                faults.add(fault);
            }

            response.setFaults(faults);
            return response;
        } else {
            return response;
        }
    }
}
