package com.jl.mybean.mydao;

import java.util.List;

import com.jl.mybean.Surpass;

public interface SurpassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Surpass record);

    int insertSelective(Surpass record);

    Surpass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Surpass record);

    int updateByPrimaryKey(Surpass record);

	List<Surpass> getSurpassesBySendflag(int is_send);
	
	int  updateSurpasses(List<Integer> ids, int is_send);
	
	List<Surpass> selectEqunumByissend(int is_send);
	
	Surpass selectByEqunum(String equ_num);
	/**
	 select equ_num,max(pass_timestamp) from surpass a  where is_send='0' group by equ_num; 

--select * from surpass where equ_num='1' and pass_timestamp = (select max(pass_timestamp) from surpass  where equ_num='1')
	 
	 */

	List<Surpass> selectEqunum();
}