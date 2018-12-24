package mmp.gps.logic.component;

import mmp.gps.domain.locate.QueryLatestRequest;
import mmp.gps.domain.locate.QueryLatestResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.LocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class LocateComponent {

    @Autowired
    private LocateService locateService;


    public LocateComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "locate.latests",
            description = "查询设备最后位置信息"
    )
    public QueryLatestResponse latests(QueryLatestRequest request) throws Exception {
        return this.locateService.queryLatests(request.getNumbers());
    }
}
