package com.jl.mybean;

import java.math.BigDecimal;
import java.util.Date;

public class LocationInfo {
    private Integer id;

    private String equNum;

    private String equType;

    private BigDecimal lat;

    private BigDecimal lng;

    private BigDecimal hight;

    private Integer active;

    private String electricity;

    private Date createTime;

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

    public String getEquType() {
        return equType;
    }

    public void setEquType(String equType) {
        this.equType = equType == null ? null : equType.trim();
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getHight() {
        return hight;
    }

    public void setHight(BigDecimal hight) {
        this.hight = hight;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity == null ? null : electricity.trim();
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
}