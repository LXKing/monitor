package com.rayton.gps.dao.baseinfo.investor;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorMapper {
    long countByExample(InvestorExample example);

    int deleteByExample(InvestorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Investor record);

    int insertSelective(Investor record);

    List<Investor> selectByExample(InvestorExample example);

    Investor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Investor record, @Param("example") InvestorExample example);

    int updateByExample(@Param("record") Investor record, @Param("example") InvestorExample example);

    int updateByPrimaryKeySelective(Investor record);

    int updateByPrimaryKey(Investor record);
}