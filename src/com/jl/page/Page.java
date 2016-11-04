package com.jl.page;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.CollectionUtils;

import com.jl.interfaces.Tinterface;

public class Page<E> implements Tinterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9001469637386781549L;
	private static final int PAGESIZE=8;
	 int pageSize=PAGESIZE;
	 int total=0;
	 int pages=0;
	 int curruentPageNum=1;
	 int prevNum=0;
	 int nextNum=0;

	 String pageId="";
	  List<E> list= new ArrayList<E>();
	

	public Page(List<E> t){
		
		if(!CollectionUtils.isEmpty(t)){
			list =t;
			this.total=t.size();
			
			this.curruentPageNum=1;
			this.nextNum=2;
			this.pages=total%pageSize==0?total/pageSize:total/pageSize+1;
			pageId =UUID.randomUUID().toString();
			
		}
	}
	
	
	
	public void save(){
		
	}
	
	public List<E> getPage(int num){
		List<E> pageList=new ArrayList<E>();
		if(num<1){
			num=1;
		}
		if(num>this.pages){
			num=this.pages;
		}
		if(!CollectionUtils.isEmpty(list)){
			pageList=(List<E>) list.subList((num-1)*pageSize, num*pageSize>total?total: num*pageSize);
		}
		return pageList;
	}
	
	public List<E> getCurruentPage(){
		List<E> pageList=new ArrayList<E>();
		if(!CollectionUtils.isEmpty(list)){
			pageList=(List<E>) list.subList((curruentPageNum-1)*pageSize, curruentPageNum*pageSize>total?total: curruentPageNum*pageSize);
		}
		return pageList;
	}
	
	public List<E> getPrevPage(){
		List<E> pageList=new ArrayList<E>();
		if(!CollectionUtils.isEmpty(list)&&curruentPageNum>1){
			pageList=(List<E>) list.subList((curruentPageNum-2)*pageSize, (curruentPageNum-1)*pageSize);
		}
		return pageList;
	}
	public  List<E> getNextPage(){
		List<E> pageList=new ArrayList<E>();
		if(!CollectionUtils.isEmpty(list)&&curruentPageNum*pageSize<total){
			pageList=(List<E>) list.subList(curruentPageNum*pageSize, (curruentPageNum+1)*pageSize<total?(curruentPageNum+1)*pageSize:total);
		}
		return pageList;
	}
	public  boolean hasNext(){
		if(curruentPageNum<pages){
			return true;
		}else{
			return false;
		}
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getCurruentPageNum() {
		return curruentPageNum;
	}
	public void setCurruentPageNum(int curruentPageNum) {
		this.prevNum =curruentPageNum-1>0?curruentPageNum-1:1;
		this.nextNum =curruentPageNum+1>pages?pages:curruentPageNum+1;
		this.curruentPageNum = curruentPageNum;
	}
	public int getPrevNum() {
		return prevNum;
	}
	public void setPrevNum(int prevNum) {
		this.prevNum = prevNum;
	}
	public int getNextNum() {
		return nextNum;
	}
	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}
	public static int getPagesize() {
		return PAGESIZE;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1){
			pageSize=1;
		}
		this.pageSize = pageSize;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public String getkey() {
	
		return pageId;
	}


}