package com.jl.mybean.mydao;

import com.jl.mybean.LocationInfo;

public interface LocationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LocationInfo record);

    int insertSelective(LocationInfo record);

    LocationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LocationInfo record);

    int updateByPrimaryKey(LocationInfo record);
}