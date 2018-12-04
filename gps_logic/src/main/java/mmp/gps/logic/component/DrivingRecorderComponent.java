package mmp.gps.logic.component;


import mmp.gps.domain.drivingRecorder.*;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.DrivingRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class DrivingRecorderComponent {

    @Autowired
    private DrivingRecorderService drivingRecorderService;


    public DrivingRecorderComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "drivingRecorder.info",
            description = "查询行驶记录仪信息"
    )
    public QueryDrivingRecorderInfoResponse get(QueryDrivingRecorderInfoRequest request) throws Exception {
        return this.drivingRecorderService.fetch(request.getNumber());
    }

    @ServiceMethod(
            value = "drivingRecorder.accidentdoubtlog.query",
            description = "查询行驶记录仪事故疑点日志"
    )
    public QueryAccidentDoubtLogResponse query(QueryAccidentDoubtLogRequest request) throws Exception {
        return this.drivingRecorderService.queryAccidentDoubtLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.powerlog.query",
            description = "查询行驶记录仪外部供电日志"
    )
    public QueryPowerLogResponse query(QueryPowerLogRequest request) throws Exception {
        return this.drivingRecorderService.queryPowerLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.timeoutlog.query",
            description = "查询行驶记录仪超时驾驶日志"
    )
    public QueryTimeoutLogResponse query(QueryTimeoutLogRequest request) throws Exception {
        return this.drivingRecorderService.queryTimeoutLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.parameterlog.query",
            description = "查询行驶记录仪参数修改日志"
    )
    public QueryParameterLogResponse query(QueryParameterLogRequest request) throws Exception {
        return this.drivingRecorderService.queryParameterLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.loginlog.query",
            description = "查询行驶记录仪驾驶员登签日志"
    )
    public QueryLoginLogResponse query(QueryLoginLogRequest request) throws Exception {
        return this.drivingRecorderService.queryLoginLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.speedstatuslog.query",
            description = "查询行驶记录仪速度状态日志"
    )
    public QuerySpeedStatusLogResponse query(QuerySpeedStatusLogRequest request) throws Exception {
        return this.drivingRecorderService.querySpeedStatusLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.locatelog.query",
            description = "查询行驶记录仪位置记录"
    )
    public QueryLocateLogResponse query(QueryLocateLogRequest request) throws Exception {
        return this.drivingRecorderService.queryLocateLog(request);
    }

    @ServiceMethod(
            value = "drivingRecorder.speedlog.query",
            description = "查询行驶记录仪速度记录"
    )
    public QuerySpeedLogResponse query(QuerySpeedLogRequest request) throws Exception {
        return this.drivingRecorderService.querySpeedLog(request);
    }
}
