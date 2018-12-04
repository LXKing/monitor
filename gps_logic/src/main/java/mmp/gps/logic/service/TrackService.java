package mmp.gps.logic.service;

import mmp.gps.domain.track.*;
import mmp.gps.logic.dao.ITrackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TrackService {

    @Autowired
    private ITrackDao trackDao;


    public CountTrackResponse count(CountTrackRequest request) {
        CountTrackResponse response = new CountTrackResponse();
        int total = this.trackDao.count(request.getNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        return response;
    }

    public LoadTrackResponse load(LoadTrackRequest request) {
        LoadTrackResponse response = new LoadTrackResponse();
        List list = this.trackDao.load(request.getNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
        if (list != null && list.size() > 0) {
            ArrayList tracks = new ArrayList(list.size());
            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                TrackDto dto = (TrackDto) var5.next();
                Track track = new Track();
                track.setA(dto.a);
                track.setD(dto.d);
                track.setLat(dto.lat);
                track.setLng(dto.lng);
                track.setS(dto.s);
                track.setSp(dto.sp);
                track.setGt(dto.gt);
                track.setSt(dto.st);
                track.setM(dto.m);
                track.setOil(dto.oil);
                track.setVss(dto.vss);
                track.setOvt(dto.ovt);
                track.setOid(dto.oid);
                track.setIot(dto.iot);
                track.setIid(dto.iid);
                track.setIof(dto.iof);
                track.setRid(dto.rid);
                track.setRt(dto.rt);
                track.setRf(dto.rf);
                track.setAid(dto.aid);
                track.setExs(dto.exs);
                track.setIos(dto.ios);
                track.setAd0(dto.ad0);
                track.setAd1(dto.ad1);
                track.setNet(dto.net);
                track.setSat(dto.sat);
                tracks.add(track);
            }

            response.setTracks(tracks);
            return response;
        } else {
            return response;
        }
    }
}
