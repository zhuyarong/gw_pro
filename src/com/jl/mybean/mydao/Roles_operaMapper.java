package com.jl.mybean.mydao;

import com.jl.mybean.Roles_opera;

public interface Roles_operaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles_opera record);

    int insertSelective(Roles_opera record);

    Roles_opera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles_opera record);

    int updateByPrimaryKey(Roles_opera record);
}