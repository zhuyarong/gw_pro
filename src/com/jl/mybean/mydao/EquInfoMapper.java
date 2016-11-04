package com.jl.mybean.mydao;

import com.jl.mybean.EquInfo;

public interface EquInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EquInfo record);

    int insertSelective(EquInfo record);

    EquInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquInfo record);

    int updateByPrimaryKey(EquInfo record);

	EquInfo selectByEqunum(String equNum);

	int updateByEqunumSelective(EquInfo equInfo);
}