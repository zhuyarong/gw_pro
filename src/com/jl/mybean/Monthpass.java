package com.jl.mybean;

import java.util.Date;

public class Monthpass {
    private Integer id;

    private Integer equinfoId;

    private String equNum;

    private Integer passCount;

    private Integer retrieveCount;

    private Long activityCount;

    private Integer month;

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

    public Integer getEquinfoId() {
        return equinfoId;
    }

    public void setEquinfoId(Integer equinfoId) {
        this.equinfoId = equinfoId;
    }

    public String getEquNum() {
        return equNum;
    }

    public void setEquNum(String equNum) {
        this.equNum = equNum == null ? null : equNum.trim();
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Integer getRetrieveCount() {
        return retrieveCount;
    }

    public void setRetrieveCount(Integer retrieveCount) {
        this.retrieveCount = retrieveCount;
    }

    public Long getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Long activityCount) {
        this.activityCount = activityCount;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
        this.version = version;
    }
}