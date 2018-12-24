package mmp.gps.logic.component;

import mmp.gps.domain.trip.LoadTripRequest;
import mmp.gps.domain.trip.LoadTripResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class TripComponent {

    @Autowired
    private TripService tripService;


    public TripComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "trip.load",
            description = "加载时间段内行程记录"
    )
    public LoadTripResponse load(LoadTripRequest request) throws Exception {
        return this.tripService.load(request);
    }
}
