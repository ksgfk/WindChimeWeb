package com.github.ksgfk.windchimeweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("用户当前商店货物信息")
public class GoodsWithItemInfo {
    @ApiModelProperty("商品id")
    private Integer goodsId;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("折扣")
    private Integer discount;

    @ApiModelProperty("商品特殊uri")
    private String specialUri;

    @ApiModelProperty("商品对应物品id")
    private Integer itemId;

    @ApiModelProperty("物品名字")
    private String name;

    @ApiModelProperty("穿戴位置")
    private String position;

    @ApiModelProperty("物品uri")
    private String uriName;

    @ApiModelProperty("物品类型")
    private String type;

    @ApiModelProperty("物品描述")
    private String description;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName == null ? null : uriName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSpecialUri() {
        return specialUri;
    }

    public void setSpecialUri(String specialUri) {
        this.specialUri = specialUri;
    }
}
