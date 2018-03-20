package com.edata.godp.dao.track;

import java.util.Date;
import java.util.List;

/**
 * 轨迹数据访问接口
 *
 * @author 生
 */
public interface ITrackDao {
    /**
     * 统计时间段内轨迹记录数
     */
    int count(String number, Date start, Date end);

    /**
     * 按页加载时间段内轨变记录
     */
    List<TrackDto> load(String number, Date start, Date end, int pageIndex, int pageSize);

}
