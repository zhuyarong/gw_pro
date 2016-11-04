package com.jl.mybean.mydao;

import com.jl.mybean.SirenInfo;

public interface SirenInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SirenInfo record);

    int insertSelective(SirenInfo record);

    SirenInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SirenInfo record);

    int updateByPrimaryKey(SirenInfo record);
}