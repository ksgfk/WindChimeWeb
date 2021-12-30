package com.github.ksgfk.windchimeweb.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BuyItemRequest extends UserToken {
    @ApiModelProperty("需要购买的商品id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
