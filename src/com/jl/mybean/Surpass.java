package com.jl.mybean;

import java.math.BigDecimal;
import java.util.Date;

public class Surpass {
    private Integer id;

    private Integer usersId;

    private Integer equinfoId;

    private Integer equfenceId;

    private Integer concentratorId;

    private String equNum;

    private Date passTimestamp;

    private BigDecimal currlat;

    private BigDecimal currlng;

    private Integer isSend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getEquinfoId() {
        return equinfoId;
    }

    public void setEquinfoId(Integer equinfoId) {
        this.equinfoId = equinfoId;
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

    public String getEquNum() {
        return equNum;
    }

    public void setEquNum(String equNum) {
        this.equNum = equNum == null ? null : equNum.trim();
    }

    public Date getPassTimestamp() {
        return passTimestamp;
    }

    public void setPassTimestamp(Date passTimestamp) {
        this.passTimestamp = passTimestamp;
    }

    public BigDecimal getCurrlat() {
        return currlat;
    }

    public void setCurrlat(BigDecimal currlat) {
        this.currlat = currlat;
    }

    public BigDecimal getCurrlng() {
        return currlng;
    }

    public void setCurrlng(BigDecimal currlng) {
        this.currlng = currlng;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }
}