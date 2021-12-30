package com.github.ksgfk.windchimeweb.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class ShopRefreshTimeResponse extends RequestResult {
    @ApiModelProperty("下次刷新时间")
    private Date data;

    public ShopRefreshTimeResponse(boolean success, Date data) {
        super(success);
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
