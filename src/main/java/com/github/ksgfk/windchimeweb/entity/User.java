package com.github.ksgfk.windchimeweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class User {
    @ApiModelProperty("唯一id")
    private Integer id;

    @ApiModelProperty("账号")
    @JsonProperty("Account")
    private String account;

    @ApiModelProperty("密码")
    @JsonProperty("Password")
    private byte[] password;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("QQ")
    @JsonProperty("QQ")
    private String qq;

    @ApiModelProperty("工作次数")
    @JsonProperty("WorkCount")
    private Integer workCount;

    @JsonProperty("HeartCount")
    private Integer heartCount;

    @JsonProperty("Heart")
    private Integer heart;

    @JsonProperty("Courage")
    private Integer courage;

    @JsonProperty("Cautious")
    private Integer cautious;

    @JsonProperty("Discipline")
    private Integer discipline;

    @JsonProperty("Justice")
    private Integer justice;

    @JsonProperty("MoonCard")
    private Integer moonCard;

    @JsonProperty("Money")
    private Integer money;

    @JsonProperty("SavePebox")
    private Integer savePeBox;

    @JsonProperty("LatestLogin")
    private Date latestLogin;

    @ApiModelProperty("上次商店刷新时间")
    private Date lastRefreshShop;

//    @JsonProperty("IsTake")
//    private Boolean IsTake;
//    @JsonProperty("CardUse")
//    private Object CardUse;
//    @JsonProperty("AllCard")
//    private Object AllCard;
//    @JsonProperty("Inhibition")
//    private Object Inhibition;
//    @JsonProperty("Conversation")
//    private Object Conversation;
//    @JsonProperty("UseEGOCard")
//    private Object UseEGOCard;

    public Date getLastRefreshShop() {
        return lastRefreshShop;
    }

    public void setLastRefreshShop(Date lastRefreshShop) {
        this.lastRefreshShop = lastRefreshShop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    public Integer getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(Integer heartCount) {
        this.heartCount = heartCount;
    }

    public Integer getHeart() {
        return heart;
    }

    public void setHeart(Integer heart) {
        this.heart = heart;
    }

    public Integer getCourage() {
        return courage;
    }

    public void setCourage(Integer courage) {
        this.courage = courage;
    }

    public Integer getCautious() {
        return cautious;
    }

    public void setCautious(Integer cautious) {
        this.cautious = cautious;
    }

    public Integer getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Integer discipline) {
        this.discipline = discipline;
    }

    public Integer getJustice() {
        return justice;
    }

    public void setJustice(Integer justice) {
        this.justice = justice;
    }

    public Integer getMoonCard() {
        return moonCard;
    }

    public void setMoonCard(Integer moonCard) {
        this.moonCard = moonCard;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getSavePeBox() {
        return savePeBox;
    }

    public void setSavePeBox(Integer savePeBox) {
        this.savePeBox = savePeBox;
    }

    public Date getLatestLogin() {
        return latestLogin;
    }

    public void setLatestLogin(Date latestLogin) {
        this.latestLogin = latestLogin;
    }
}