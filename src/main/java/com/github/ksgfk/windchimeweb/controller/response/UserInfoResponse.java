package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.entity.User;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "用户登录信息响应")
public class UserInfoResponse extends RequestResult {
    private User data;

    public UserInfoResponse(boolean success, User data) {
        super(success);
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
