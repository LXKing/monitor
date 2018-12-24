package mmp.gps.logic.component;

import mmp.gps.domain.instruct.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class InstructComponent {

    @Autowired
    private InstructService instructService;


    public InstructComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "instruct.send",
            description = "发送指令到设备"
    )
    public boolean resolve(SendInstructRequest request) throws Exception {
        this.instructService.send(request);
        return true;
    }

    @ServiceMethod(
            value = "instruct.query",
            description = "查询指令结果"
    )
    public QueryInstructResponse query(QueryInstructRequest request) throws Exception {
        return this.instructService.query(request);
    }

    @ServiceMethod(
            value = "instruct.fetch",
            description = "查询指令信息"
    )
    public FetchInstructResponse fetch(FetchInstructRequest request) throws Exception {
        return this.instructService.fetch(request);
    }
}
