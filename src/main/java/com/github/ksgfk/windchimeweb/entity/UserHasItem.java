package com.github.ksgfk.windchimeweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserHasItem extends UserHasItemKey {
    @ApiModelProperty("稀有度")
    private String rare;

    @ApiModelProperty("主属性json")
    private String primary;

    @ApiModelProperty("副属性json，是个数组")
    private String minor;

    @ApiModelProperty("等级")
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRare() {
        return rare;
    }

    public void setRare(String rare) {
        this.rare = rare == null ? null : rare.trim();
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary == null ? null : primary.trim();
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor == null ? null : minor.trim();
    }
}