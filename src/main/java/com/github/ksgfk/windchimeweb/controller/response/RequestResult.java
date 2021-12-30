package com.github.ksgfk.windchimeweb.controller.response;

public class RequestResult {
    private boolean success;

    public RequestResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
