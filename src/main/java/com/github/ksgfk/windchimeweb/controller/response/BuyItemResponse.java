package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.entity.UserHasItem;

public class BuyItemResponse extends RequestResult {
    private UserHasItem data;

    public BuyItemResponse(boolean success, UserHasItem data) {
        super(success);
        this.data = data;
    }

    public UserHasItem getData() {
        return data;
    }

    public void setData(UserHasItem data) {
        this.data = data;
    }
}
