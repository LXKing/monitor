package com.rayton.gps.dao.baseinfo.insurance;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InsuranceMapper {
    long countByExample(InsuranceExample example);

    int deleteByExample(InsuranceExample example);

    int insert(Insurance record);

    int insertSelective(Insurance record);

    List<Insurance> selectByExample(InsuranceExample example);

    int updateByExampleSelective(@Param("record") Insurance record, @Param("example") InsuranceExample example);

    int updateByExample(@Param("record") Insurance record, @Param("example") InsuranceExample example);
}