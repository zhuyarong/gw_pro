package com.jl.webBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jl.interfaces.Tinterface;

public class WebUser implements Cloneable,Tinterface
{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -76864997973414231L;


	public String username;//用户名

	public String password;//密码
	
	public int age;

	public Date regDate;//注册时间

	public Date lastlogintime;

	public String lastloginIp;

	public String fathername;

	public String roleid;//1管理员，2业务员，3客户


	public String sex;

	public String phone;
	
	public String email;
	public List<String> phones;

	public List<String> emails;

	public String addr;

	public String addr1;

	public String postion;

	public String mark;
	

	
	public ArrayList<WebUser> users;


	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}






	public ArrayList<WebUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<WebUser> users) {
		this.users = users;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getLastloginIp() {
		return lastloginIp;
	}

	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	
	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

	

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}



	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getkey() {
	
		return this.username;
	}


}
