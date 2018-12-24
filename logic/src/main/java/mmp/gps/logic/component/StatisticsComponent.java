package mmp.gps.logic.component;

import mmp.gps.domain.statistics.CurrentOnlineOfflineStatisticsRequest;
import mmp.gps.domain.statistics.CurrentOnlineOfflineStatisticsResponse;
import mmp.gps.domain.statistics.HistoryOnlineOfflineStatisticsRequest;
import mmp.gps.domain.statistics.HistoryOnlineOfflineStatisticsResponse;
import mmp.gps.domain.statistics.HistoryOnlineTimeStatisticsRequest;
import mmp.gps.domain.statistics.HistoryOnlineTimeStatisticsResponse;
import mmp.gps.domain.statistics.MileageOilCountRequest;
import mmp.gps.domain.statistics.MileageOilCountResponse;
import mmp.gps.logic.portal.ServiceComponent;
import mmp.gps.logic.portal.ServiceMethod;
import mmp.gps.logic.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServiceComponent
public class StatisticsComponent {

    @Autowired
    private StatisticsService statisticsService;


    public StatisticsComponent() {
        ServiceComponents.regist(this);
    }

    @ServiceMethod(
            value = "statistics.history.onlineoffline",
            description = "设备历史上下线查询"
    )
    public HistoryOnlineOfflineStatisticsResponse historyOnlineOffline(HistoryOnlineOfflineStatisticsRequest request) throws Exception {
        return this.statisticsService.historyOnlineOffline(request);
    }

    @ServiceMethod(
            value = "statistics.current.onlineoffline",
            description = "设备当前上下线查询"
    )
    public CurrentOnlineOfflineStatisticsResponse currentOnlineOffline(CurrentOnlineOfflineStatisticsRequest request) throws Exception {
        return this.statisticsService.currentOnlineOffline(request);
    }

    @ServiceMethod(
            value = "statistics.history.onlinetime",
            description = "设备历史在线时长统计"
    )
    public HistoryOnlineTimeStatisticsResponse historyOnlineTime(HistoryOnlineTimeStatisticsRequest request) throws Exception {
        return this.statisticsService.historyOnlineTime(request);
    }

    @ServiceMethod(
            value = "statistics.count.mileageoil",
            description = "设备里程油耗统计"
    )
    public MileageOilCountResponse mileageOil(MileageOilCountRequest request) throws Exception {
        return this.statisticsService.countMileageOil(request);
    }
}
