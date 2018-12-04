package mmp.gps.logic.dao;

import mmp.gps.domain.locate.LatestDto;

import java.util.List;

public interface ILocateDao {

    List<LatestDto> queryLatests(List<String> numbers) throws Exception;

    /**
     * 获取最近位置数据
     *
     * @throws Exception
     */
    List<LatestDto> loadLatest() throws Exception;
}
