package mmp.gps.logic.service;

import mmp.gps.common.util.Data;
import mmp.gps.domain.device.Device;
import mmp.gps.domain.locate.LatestDto;
import mmp.gps.domain.locate.LatestInfo;
import mmp.gps.domain.locate.QueryLatestResponse;
import mmp.gps.domain.track.Track;
import mmp.gps.logic.cache.Devices;
import mmp.gps.logic.dao.ILocateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocateService {

    @Autowired
    private ILocateDao dao;


    public QueryLatestResponse queryLatests(List numbers) throws Exception {
        QueryLatestResponse response = new QueryLatestResponse();
        response.setLatests(new HashMap());
        Iterator var3 = numbers.iterator();

        while (var3.hasNext()) {
            String number = (String) var3.next();
            Device device = Devices.getCurrent().get(number);
            if (device != null) {
                Track track = device.getTrack();
                if (track != null) {
                    LatestInfo info = new LatestInfo();
                    info.a = track.getA();
                    info.d = track.getD();
                    info.val = track.getVal();
                    info.lat = track.getLat();
                    info.lng = track.getLng();
                    info.s = track.getS();
                    info.sp = track.getSp();
                    info.gt = track.getGt();
                    info.st = track.getSt();
                    info.o = track.getO();
                    info.m = track.getM();
                    info.oil = track.getOil();
                    info.vss = track.getVss();
                    info.ovt = track.getOvt();
                    info.oid = track.getOid();
                    info.iot = track.getIot();
                    info.iid = track.getIid();
                    info.iof = track.getIof();
                    info.rt = track.getRt();
                    info.rid = track.getRid();
                    info.rf = track.getRf();
                    info.oil = track.getOil();
                    info.vss = track.getVss();
                    info.ovt = track.getOvt();
                    info.oid = track.getOid();
                    info.iot = track.getIot();
                    info.iid = track.getIid();
                    info.iof = track.getIof();
                    info.rid = track.getRid();
                    info.rt = track.getRt();
                    info.rf = track.getRf();
                    info.aid = track.getAid();
                    info.exs = track.getExs();
                    info.ios = track.getIos();
                    info.ad0 = track.getAd0();
                    info.ad1 = track.getAd1();
                    info.net = track.getNet();
                    info.sat = track.getSat();
                    response.getLatests().put(number, info);
                }
            }
        }

        return response;
    }

    public Map loadLatest() throws Exception {
        List rows = this.dao.loadLatest();
        HashMap map = new HashMap();
        Iterator var3 = rows.iterator();

        while (var3.hasNext()) {
            LatestDto dto = (LatestDto) var3.next();
            map.put(dto.dn, dto);
        }

        return map;
    }

    public Data getmileage(String dn) throws Exception {
        Data data = new Data();
        ArrayList numbers = new ArrayList();
        numbers.add(dn);
        List rows = this.dao.queryLatests(numbers);
        if (rows.size() == 0) {
            data.setCode(1);
            data.setObj(Integer.valueOf(0));
        } else {
            data.setCode(1);
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                LatestDto dto = (LatestDto) var5.next();
                data.setObj(dto.getM());
            }
        }

        return data;
    }
}
