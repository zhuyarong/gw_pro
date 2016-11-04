package com.jl.mybean.mydao;

import java.util.List;

import com.jl.mybean.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users findUserByname(String username);
    List<Users> selectByPuserid(Integer p_user_id);
}