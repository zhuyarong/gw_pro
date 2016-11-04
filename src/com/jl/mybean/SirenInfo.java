package com.jl.mybean;

import java.util.Date;

public class SirenInfo {
    private Integer id;

    private Integer usersId;

    private Integer equfenceId;

    private Date sendtimestamp;

    private String message;

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

    public Integer getEqufenceId() {
        return equfenceId;
    }

    public void setEqufenceId(Integer equfenceId) {
        this.equfenceId = equfenceId;
    }

    public Date getSendtimestamp() {
        return sendtimestamp;
    }

    public void setSendtimestamp(Date sendtimestamp) {
        this.sendtimestamp = sendtimestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}