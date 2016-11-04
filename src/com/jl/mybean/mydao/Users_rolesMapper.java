package com.jl.mybean.mydao;

import com.jl.mybean.Users_roles;

public interface Users_rolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users_roles record);

    int insertSelective(Users_roles record);

    Users_roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users_roles record);

    int updateByPrimaryKey(Users_roles record);
}