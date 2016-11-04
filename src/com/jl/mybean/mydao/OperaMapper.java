package com.jl.mybean.mydao;

import com.jl.mybean.Opera;

public interface OperaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Opera record);

    int insertSelective(Opera record);

    Opera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Opera record);

    int updateByPrimaryKey(Opera record);
}