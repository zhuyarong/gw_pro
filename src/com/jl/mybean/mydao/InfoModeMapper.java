package com.jl.mybean.mydao;

import com.jl.mybean.InfoMode;

public interface InfoModeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoMode record);

    int insertSelective(InfoMode record);

    InfoMode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoMode record);

    int updateByPrimaryKey(InfoMode record);
}