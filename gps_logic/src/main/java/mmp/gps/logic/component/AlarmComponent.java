package mmp.gps.logic.component;

import mmp.gps.domain.alarm.LoadAlarmRequest;
import mmp.gps.domain.alarm.LoadAlarmResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class AlarmComponent {

    @Autowired
    private AlarmService alarmService;


    public AlarmComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "alarm.load",
            description = "按页加载时间段内报警记录"
    )
    public LoadAlarmResponse load(LoadAlarmRequest request) throws Exception {
        return this.alarmService.load(request);
    }
}
