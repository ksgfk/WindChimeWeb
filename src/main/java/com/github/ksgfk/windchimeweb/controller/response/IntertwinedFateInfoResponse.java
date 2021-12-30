package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.entity.IntertwinedFateGoods;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "纠缠之缘响应列表")
public class IntertwinedFateInfoResponse extends RequestResult {
    private List<IntertwinedFateGoods> data;

    public IntertwinedFateInfoResponse(boolean success, List<IntertwinedFateGoods> data) {
        super(success);
        this.data = data;
    }

    public List<IntertwinedFateGoods> getData() {
        return data;
    }

    public void setData(List<IntertwinedFateGoods> data) {
        this.data = data;
    }
}
