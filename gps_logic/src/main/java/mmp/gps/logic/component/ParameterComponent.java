package mmp.gps.logic.component;


import mmp.gps.domain.parameter.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class ParameterComponent {

    @Autowired
    private ParameterService parameterService;


    public ParameterComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "parameter.create",
            description = "创建新的信令参数信息"
    )
    public void create(CreateParameterRequest request) throws Exception {
        this.parameterService.create(request);
    }

    @ServiceMethod(
            value = "parameter.update",
            description = "更新信令参数信息"
    )
    public void update(EditParameterRequest request) throws Exception {
        this.parameterService.update(request);
    }

    @ServiceMethod(
            value = "parameter.fetch",
            description = "读取信令参数信息"
    )
    public FetchParameterResponse fetch(FetchParameterRequest request) throws Exception {
        return this.parameterService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "parameter.delete",
            description = "删除信令参数信息"
    )
    public boolean delete(DeleteParameterRequest request) throws Exception {
        this.parameterService.delete(request.getId());
        return true;
    }

    @ServiceMethod(
            value = "parameter.list",
            description = "查询信令参数信息"
    )
    public ListParameterResponse list(ListParameterRequest request) throws Exception {
        return this.parameterService.list(request.getFeatureId());
    }

    @ServiceMethod(
            value = "parameter.move",
            description = "移动信令参数信息"
    )
    public boolean move(MoveParameterRequest request) throws Exception {
        this.parameterService.move(request.getId(), request.getPid());
        return true;
    }

    @ServiceMethod(
            value = "parameter.load",
            description = "加载信令参数列表"
    )
    public LoadParametersResponse load(LoadParametersRequest request) throws Exception {
        return this.parameterService.load(request.getPid(), request.getFeatureId());
    }
}
