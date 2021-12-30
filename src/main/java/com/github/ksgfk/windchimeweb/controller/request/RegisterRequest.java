package com.github.ksgfk.windchimeweb.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RegisterRequest {
    @ApiModelProperty("账号名")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("QQ号")
    private String qq;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
