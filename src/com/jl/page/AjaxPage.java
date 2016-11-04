package com.jl.page;

import java.util.List;

public class AjaxPage<E> extends Page<E> {





	public AjaxPage(List<E> list) {
		super(list);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849193414853297321L;

	public List<E> getCurruentPage(){
		return list;
	}

}
