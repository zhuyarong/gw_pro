package com.jl.mybean.mydao;

import com.jl.mybean.Concentrator;

public interface ConcentratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Concentrator record);

    int insertSelective(Concentrator record);

    Concentrator selectByPrimaryKey(Integer id);
    
    Concentrator selectByMac(String mac);

    int updateByPrimaryKeySelective(Concentrator record);
    int updateByMac(Concentrator record);

    int updateByPrimaryKey(Concentrator record);
}