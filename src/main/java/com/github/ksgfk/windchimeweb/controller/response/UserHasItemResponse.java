package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.controller.UserController;

import java.util.List;

public class UserHasItemResponse extends RequestResult {
    private List<UserController.__USER_ITEM> data;

    public UserHasItemResponse(boolean success, List<UserController.__USER_ITEM> data) {
        super(success);
        this.data = data;
    }

    public List<UserController.__USER_ITEM> getData() {
        return data;
    }

    public void setData(List<UserController.__USER_ITEM> data) {
        this.data = data;
    }
}
