package mmp.gps.logic.component;

import mmp.gps.domain.feature.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class FeatureComponent {

    @Autowired
    private FeatureService featureService;


    public FeatureComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "feature.create",
            description = "创建新的信令信息"
    )
    public void crate(CreateFeatureRequest request) throws Exception {
        this.featureService.create(request);
    }

    @ServiceMethod(
            value = "feature.update",
            description = "更新信令信息"
    )
    public void update(EditFeatureRequest request) throws Exception {
        this.featureService.update(request);
    }

    @ServiceMethod(
            value = "feature.fetch",
            description = "读取信令信息"
    )
    public FetchFeatureResponse fetch(FetchFeatureRequest request) throws Exception {
        return this.featureService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "feature.delete",
            description = "删除信令信息"
    )
    public boolean delete(DeleteFeatureRequest request) throws Exception {
        this.featureService.delete(request.getId());
        return true;
    }

    @ServiceMethod(
            value = "feature.list",
            description = "查询信令信息"
    )
    public ListFeatureResponse list(ListFeatureRequest request) throws Exception {
        return this.featureService.list(request.getKind());
    }

    @ServiceMethod(
            value = "feature.match",
            description = "匹配设备信令"
    )
    public MatchFeatureResponse match(MatchFeatureRequest request) throws Exception {
        System.out.println("request" + request);
        return this.featureService.match(request.getNumber(), request.getProtocol());
    }
}
