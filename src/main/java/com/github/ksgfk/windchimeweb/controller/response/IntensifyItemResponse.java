package com.github.ksgfk.windchimeweb.controller.response;

import com.github.ksgfk.windchimeweb.controller.UserController;

public class IntensifyItemResponse extends RequestResult {
    public IntensifyItemResponse(boolean success, UserController.__NEW_PROP data) {
        super(success);
        this.data = data;
    }

    private UserController.__NEW_PROP data;

    public IntensifyItemResponse(boolean success) {
        super(success);
    }

    public UserController.__NEW_PROP getData() {
        return data;
    }

    public void setData(UserController.__NEW_PROP data) {
        this.data = data;
    }
}
