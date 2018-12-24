package mmp.gps.logic.service;


import mmp.gps.common.util.DaoFactory;
import mmp.gps.domain.flow.Flow;
import mmp.gps.domain.flow.FlowDto;
import mmp.gps.domain.flow.LoadFlowRequest;
import mmp.gps.domain.flow.LoadFlowResponse;
import mmp.gps.logic.dao.IFlowDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlowService {

    public static LoadFlowResponse load(LoadFlowRequest request) throws Exception {
        LoadFlowResponse response = new LoadFlowResponse();
        IFlowDao dao = (IFlowDao) DaoFactory.getAdo(IFlowDao.class);
        List list = dao.load(request.getNumber(), request.getStart(), request.getEnd());
        if (list != null && list.size() > 0) {
            ArrayList flows = new ArrayList(list.size());
            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                FlowDto dto = (FlowDto) var5.next();
                Flow flow = new Flow();
                flow.setNumber(dto.number);
                flow.setTime(dto.time);
                flow.setVss(dto.vss);
                flow.setMileage(dto.mileage);
                flow.setBv(dto.bv);
                flow.setRpm(dto.rpm);
                flow.setEct(dto.ect);
                flow.setRuntm(dto.runtm);
                flow.setEot(dto.eot);
                flow.setIfc(dto.ifc);
                flow.setOt(dto.ot);
                flow.setIat(dto.iat);
                flow.setEt(dto.et);
                flow.setMaf(dto.maf);
                flow.setAp(dto.ap);
                flow.setLoadpct(dto.loadpct);
                flow.setMap(dto.map);
                flow.setLftp(dto.lftp);
                flow.setAltp(dto.altp);
                flow.setRrtp(dto.rrtp);
                flow.setRftp(dto.rftp);
                flow.setMord(dto.mord);
                flow.setIaa(dto.iaa);
                flow.setLongftb1(dto.longftb1);
                flow.setShrtftb1(dto.shrtftb1);
                flow.setShrtftb1s1(dto.shrtftb1s1);
                flow.setShrtftb1s2(dto.shrtftb1s2);
                flow.setFaults(dto.faults);
                flow.setTp(dto.tp);
                flow.setTpalb(dto.tpalb);
                flow.setTpalc(dto.tpalc);
                flow.setFrp(dto.frp);
                flow.setFuelsys1(dto.fuelsys1);
                flow.setFuelsys2(dto.fuelsys2);
                flow.setO2sb1s1(dto.o2sb1s1);
                flow.setO2sb1s2(dto.o2sb1s2);
                flow.setPpsd(dto.ppsd);
                flow.setPpse(dto.ppse);
                flow.setVaptb1s1(dto.vaptb1s1);
                flow.setVaptb1s2(dto.vaptb1s2);
                flows.add(flow);
            }

            response.setFlows(flows);
            return response;
        } else {
            return response;
        }
    }
}
