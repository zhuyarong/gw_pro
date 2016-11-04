package com.jl.mybean;

public class ZTE_DATA {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}