package com.github.ksgfk.windchimeweb.controller.request;

public class StartWorkRequest extends UserToken {
    private int workObject;

    public int getWorkObject() {
        return workObject;
    }

    public void setWorkObject(int workObject) {
        this.workObject = workObject;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }

    private int workType;
}
