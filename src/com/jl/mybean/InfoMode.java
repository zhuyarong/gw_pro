package com.jl.mybean;

import java.util.Date;

public class InfoMode {
    private Integer id;

    private Integer usersId;

    private String infomode;

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

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getInfomode() {
        return infomode;
    }

    public void setInfomode(String infomode) {
        this.infomode = infomode == null ? null : infomode.trim();
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