package com.jl.mybean;

public class Concentrator {
    private Integer id;

    private String key;

    private Integer usersId;

    private Integer efenceId;

    private String mac;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getEfenceId() {
        return efenceId;
    }

    public void setEfenceId(Integer efenceId) {
        this.efenceId = efenceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }
}