package mmp.gps.logic.component;

import mmp.gps.domain.fault.FaultQueryRequest;
import mmp.gps.domain.fault.FaultQueryResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.FaultService;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class FaultComponent {

    public FaultComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "fault.query",
            description = "查询时间段内故障记录"
    )
    public FaultQueryResponse load(FaultQueryRequest request) throws Exception {
        return FaultService.query(request);
    }
}
