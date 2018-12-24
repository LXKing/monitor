package mmp.gps.logic.component;

import mmp.gps.domain.flow.LoadFlowRequest;
import mmp.gps.domain.flow.LoadFlowResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.FlowService;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class FlowComponent {

    public FlowComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "flow.load",
            description = "加载时间段内OBD数据流"
    )
    public LoadFlowResponse load(LoadFlowRequest request) throws Exception {
        return FlowService.load(request);
    }
}
