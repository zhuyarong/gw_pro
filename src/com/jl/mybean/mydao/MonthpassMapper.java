package com.jl.mybean.mydao;

import com.jl.mybean.Monthpass;

public interface MonthpassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Monthpass record);

    int insertSelective(Monthpass record);

    Monthpass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Monthpass record);

    int updateByPrimaryKey(Monthpass record);
}