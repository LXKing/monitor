package mmp.gps.logic.component;

import mmp.gps.domain.multimedia.QueryMultimediaRequest;
import mmp.gps.domain.multimedia.QueryMultimediaResponse;
import mmp.gps.domain.multimedia.ReadMultimediaRequest;
import mmp.gps.domain.multimedia.ReadMultimediaResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class MultimediaComponent {

    @Autowired
    private MultimediaService multimediaService;


    public MultimediaComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "multimedia.query",
            description = "查询多媒体信息"
    )
    public QueryMultimediaResponse query(QueryMultimediaRequest request) throws Exception {
        return this.multimediaService.query(request);
    }

    @ServiceMethod(
            value = "multimedia.read",
            description = "读取多媒数据"
    )
    public ReadMultimediaResponse read(ReadMultimediaRequest request) throws Exception {
        return this.multimediaService.read(request);
    }
}
