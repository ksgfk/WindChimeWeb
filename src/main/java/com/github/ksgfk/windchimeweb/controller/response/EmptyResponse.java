package com.github.ksgfk.windchimeweb.controller.response;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "只有是否成功，没有data")
public class EmptyResponse extends RequestResult {
    public EmptyResponse(boolean success) {
        super(success);
    }
}
