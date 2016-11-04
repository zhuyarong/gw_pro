package com.jl.mybean.mydao;

import com.jl.mybean.ZTE_DATA;

public interface ZTE_DATAMapper {
    int insert(ZTE_DATA record);

    int insertSelective(ZTE_DATA record);
}