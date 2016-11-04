package com.jl.mybean;

import java.util.Date;

public class EquInfo {
	private Integer id;

	private String equNum;

	private String nickname;

	private Integer equfenceId;

	private Integer concentratorId;

	private String concentratorMac;

	private String unit;

	private String sign;

	private Integer electricity;

	private Date expirytimestamp;

	private Short state;

	private Date createTime;

	private Date modifyTime;

	private Short deleted;

	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEquNum() {
		return equNum;
	}

	public void setEquNum(String equNum) {
		this.equNum = equNum == null ? null : equNum.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public Integer getEqufenceId() {
		return equfenceId;
	}

	public void setEqufenceId(Integer equfenceId) {
		this.equfenceId = equfenceId;
	}

	public Integer getConcentratorId() {
		return concentratorId;
	}

	public void setConcentratorId(Integer concentratorId) {
		this.concentratorId = concentratorId;
	}

	public String getConcentratorMac() {
		return concentratorMac;
	}

	public void setConcentratorMac(String concentratorMac) {
		this.concentratorMac = concentratorMac == null ? null : concentratorMac
				.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}

	public Integer getElectricity() {
		return electricity;
	}

	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}

	public Date getExpirytimestamp() {
		return expirytimestamp;
	}

	public void setExpirytimestamp(Date expirytimestamp) {
		this.expirytimestamp = expirytimestamp;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		
		if (null == createTime) {
			this.createTime = new Date();
		} else {
			this.createTime = createTime;
		}
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		if (null == modifyTime) {
			this.modifyTime = new Date();
		} else {
			this.modifyTime = modifyTime;
		}
		
	}

	public Short getDeleted() {
		return deleted;
	}

	public void setDeleted(Short deleted) {
		this.deleted = deleted;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		if (null == version) {
			this.version = 1;
		} else {
			this.version = version;
		}

	}
}