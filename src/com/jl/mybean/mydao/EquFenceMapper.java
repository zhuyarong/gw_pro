package com.jl.mybean.mydao;

import java.util.List;

import com.jl.mybean.EquFence;

public interface EquFenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EquFence record);

    int insertSelective(EquFence record);

    EquFence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquFence record);

    int updateByPrimaryKey(EquFence record);

	List<EquFence> selectByUserId(int userId);
}