package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.entity.UserShopItemAllInfo;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("用户商店响应")
public class UserItemResponse extends RequestResult {
    private List<UserShopItemAllInfo> data;

    public UserItemResponse(boolean success, List<UserShopItemAllInfo> data) {
        super(success);
        this.data = data;
    }

    public List<UserShopItemAllInfo> getData() {
        return data;
    }

    public void setData(List<UserShopItemAllInfo> data) {
        this.data = data;
    }
}
