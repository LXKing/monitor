package com.rayton.gps.service;

import com.rayton.gps.dao.locate.TrackDto;
import com.rayton.gps.godp.IGodpDao;
import com.rayton.gps.replay.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplayService {
    @Autowired
    private IGodpDao godpDao;

    public int countTracks(String number, Date start, Date end) throws Exception {
        return godpDao.queryTrackPageCount(number, start, end);
    }

    public List<Track> loadTracks(String number, Date start, Date end, int pageIndex, int pageSize) throws Exception {
        List<Track> tracks = new ArrayList<Track>();

        List<TrackDto> list = godpDao.queryTrackPageDetail(number, start, end, pageIndex, pageSize);
        if (list == null || list.size() <= 0)
            return tracks;

        for (TrackDto dto : list) {
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

        return tracks;
    }

}
