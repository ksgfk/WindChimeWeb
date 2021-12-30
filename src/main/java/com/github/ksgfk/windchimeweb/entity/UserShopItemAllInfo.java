package com.github.ksgfk.windchimeweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("用户独特商店的商品信息")
public class UserShopItemAllInfo {
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

    @ApiModelProperty("物品稀有度")
    private String rare;

    @ApiModelProperty("是否已经购买")
    private boolean hasBuy;

    @ApiModelProperty("套装")
    private String group;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

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

    public String getSpecialUri() {
        return specialUri;
    }

    public void setSpecialUri(String specialUri) {
        this.specialUri = specialUri;
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
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUriName() {
        return uriName;
    }

    public void setUriName(String uriName) {
        this.uriName = uriName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRare() {
        return rare;
    }

    public void setRare(String rare) {
        this.rare = rare;
    }

    public boolean hasBuy() {
        return hasBuy;
    }

    public void setHasBuy(boolean hasBuy) {
        this.hasBuy = hasBuy;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
