package com.github.ksgfk.windchimeweb.controller.response;

public class UserIntertwinedFateCountRequest extends RequestResult {
    private Integer data;

    public UserIntertwinedFateCountRequest(boolean success, Integer data) {
        super(success);
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
