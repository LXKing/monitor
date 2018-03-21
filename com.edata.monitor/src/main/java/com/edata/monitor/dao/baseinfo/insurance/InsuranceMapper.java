package com.edata.monitor.dao.baseinfo.insurance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface InsuranceMapper {
    long countByExample(InsuranceExample example);

    int deleteByExample(InsuranceExample example);

    int insert(Insurance record);

    int insertSelective(Insurance record);

    List<Insurance> selectByExample(InsuranceExample example);

    int updateByExampleSelective(@Param("record") Insurance record, @Param("example") InsuranceExample example);

    int updateByExample(@Param("record") Insurance record, @Param("example") InsuranceExample example);
}