package com.edata.godp.dao.instruct;

import java.util.Date;
import java.util.List;

public interface IInstructDao {

    void create(InstructDto dto) throws Exception;

    int queryByNumberPageCount(String number, String userId, Date start, Date end);

    List<InstructDto> queryByNumberPageDetail(String number, String userId, Date start, Date end, int pageIndex, int
            pageSize) throws Exception;

    int queryByUserPageCount(String userId, Date start, Date end);

    List<InstructDto> queryByUserPageDetail(String userId, Date start, Date end, int pageIndex, int pageSize) throws
            Exception;

    InstructDto fetch(String id) throws Exception;
}
