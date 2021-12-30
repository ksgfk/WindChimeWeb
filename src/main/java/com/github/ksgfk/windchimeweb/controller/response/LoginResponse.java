package com.github.ksgfk.windchimeweb.controller.response;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel(description = "登录响应")
public class LoginResponse extends RequestResult {
    private UUID data;

    public LoginResponse(boolean success, UUID data) {
        super(success);
        this.data = data;
    }

    public UUID getData() {
        return data;
    }

    public void setData(UUID data) {
        this.data = data;
    }
}
