package mmp.gps.logic.component;

import mmp.gps.domain.exist.ExistResponse;
import mmp.gps.domain.faultcode.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.FaultCodeService;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class FaultCodeComponent {

    public FaultCodeComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "faultcode.query",
            description = "查询故障码库"
    )
    public QueryFaultCodeResponse query(QueryFaultCodeRequest request) throws Exception {
        return FaultCodeService.query(request);
    }

    @ServiceMethod(
            value = "faultcode.create",
            description = "创建新的故障码信息"
    )
    public FetchFaultCodeResponse crate(CreateFaultCodeRequest request) throws Exception {
        return FaultCodeService.create(request);
    }

    @ServiceMethod(
            value = "faultcode.update",
            description = "更新故障码信息"
    )
    public FetchFaultCodeResponse update(EditFaultCodeRequest request) throws Exception {
        return FaultCodeService.update(request);
    }

    @ServiceMethod(
            value = "faultcode.fetch",
            description = "读取故障码信息"
    )
    public FetchFaultCodeResponse fetch(FetchFaultCodeRequest request) throws Exception {
        return FaultCodeService.fetch(request.getId());
    }

    @ServiceMethod(
            value = "faultcode.delete",
            description = "删除故障码信息"
    )
    public boolean delete(DeleteFaultCodeRequest request) throws Exception {
        FaultCodeService.delete(request.getId());
        return true;
    }

    @ServiceMethod(
            value = "faultcode.exists",
            allowAnoumous = true,
            description = "检查是否已存在故障码信息"
    )
    public ExistResponse exists(ExistFaultCodeRequest request) throws Exception {
        return FaultCodeService.exists(request);
    }

    @ServiceMethod(
            value = "faultcode.parse",
            description = "解析故障码"
    )
    public ParseFaultCodeResponse parse(ParseFaultCodeRequest request) throws Exception {
        return FaultCodeService.parse(request);
    }

    @ServiceMethod(
            value = "faultcode.import",
            description = "导入故障码"
    )
    public boolean importRow(ImportFaultCodeRequest request) throws Exception {
        FaultCodeService.importRow(request);
        return true;
    }
}
