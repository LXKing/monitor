package mmp.gps.logic.service;


import mmp.gps.domain.multimedia.*;
import mmp.gps.logic.dao.IMultimediaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MultimediaService {

    @Autowired
    private IMultimediaDao multimediaDao;


    public QueryMultimediaResponse query(QueryMultimediaRequest request) throws Exception {
        QueryMultimediaResponse response = new QueryMultimediaResponse();
        int total = this.multimediaDao.queryPageCount(request.getDeviceNumber(), request.getStart(), request.getEnd());
        response.setTotal(total);
        if (total > 0) {
            List rows = this.multimediaDao.queryPageDetail(request.getDeviceNumber(), request.getStart(), request.getEnd(), (request.getPageIndex() - 1) * request.getPageSize(), request.getPageSize());
            Iterator var5 = rows.iterator();

            while (var5.hasNext()) {
                MultimediaInfoDto dto = (MultimediaInfoDto) var5.next();
                MultimediaInfo info = new MultimediaInfo();
                info.setId(dto.id);
                info.setGt(dto.gt);
                info.setMediaId(dto.mediaId);
                info.setMediaType(dto.mediaType);
                info.setFormatType(dto.formatType);
                info.setEventType(dto.eventType);
                info.setChannelId(dto.channelId);
                info.setLng(dto.lng);
                info.setLat(dto.lat);
                info.setSp(dto.sp);
                info.setD(dto.d);
                info.setA(dto.a);
                info.setS(dto.s);
                response.getRows().add(info);
            }
        }

        return response;
    }

    public ReadMultimediaResponse read(ReadMultimediaRequest request) throws Exception {
        ReadMultimediaResponse response = new ReadMultimediaResponse();
        MediaContentDto dto = this.multimediaDao.read(request.getId());
        response.setContent(dto.content);
        return response;
    }
}
