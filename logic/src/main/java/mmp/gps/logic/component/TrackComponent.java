package mmp.gps.logic.component;

import mmp.gps.domain.track.CountTrackRequest;
import mmp.gps.domain.track.CountTrackResponse;
import mmp.gps.domain.track.LoadTrackRequest;
import mmp.gps.domain.track.LoadTrackResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class TrackComponent {

    @Autowired
    private TrackService trackService;


    public TrackComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "track.count",
            description = "统计时间段内轨迹记录数"
    )
    public CountTrackResponse count(CountTrackRequest request) throws Exception {
        return this.trackService.count(request);
    }

    @ServiceMethod(
            value = "track.load",
            description = "按页加载时间段内轨迹记录"
    )
    public LoadTrackResponse load(LoadTrackRequest request) throws Exception {
        return this.trackService.load(request);
    }
}
