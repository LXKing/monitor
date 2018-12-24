package mmp.gps.logic.service;

import mmp.gps.domain.alarm.Alarm;
import mmp.gps.domain.alarm.AlarmDto;
import mmp.gps.domain.alarm.LoadAlarmRequest;
import mmp.gps.domain.alarm.LoadAlarmResponse;
import mmp.gps.logic.dao.IAlarmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private IAlarmDao alarmDao;


    public LoadAlarmResponse load(LoadAlarmRequest request) throws Exception {
        LoadAlarmResponse response = new LoadAlarmResponse();
        int total = this.alarmDao.loadPageCount(request.getNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.alarmDao.loadPageDetail(request.getNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                AlarmDto dto = (AlarmDto) var5.next();
                Alarm alarm = new Alarm();
                alarm.setA(dto.a);
                alarm.setD(dto.d);
                alarm.setLat(dto.lat);
                alarm.setLng(dto.lng);
                alarm.setS(dto.s);
                alarm.setSp(dto.sp);
                alarm.setGt(dto.gt);
                alarm.setSt(dto.st);
                alarm.setM((double) dto.m);
                alarm.setOil(dto.oil);
                alarm.setVss(dto.vss);
                alarm.setOvt(dto.ovt);
                alarm.setOid(dto.oid);
                alarm.setIot(dto.iot);
                alarm.setIid(dto.iid);
                alarm.setIof(dto.iof);
                alarm.setRid(dto.rid);
                alarm.setRt(dto.rt);
                alarm.setRf(dto.rf);
                alarm.setAid(dto.aid);
                alarm.setExs(dto.exs);
                alarm.setIos(dto.ios);
                alarm.setAd0(dto.ad0);
                alarm.setAd1(dto.ad1);
                alarm.setNet(dto.net);
                alarm.setSat(dto.sat);
                response.getAlarms().add(alarm);
            }
        }

        return response;
    }
}
