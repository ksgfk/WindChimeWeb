package com.github.ksgfk.windchimeweb.entity;

import java.math.BigDecimal;

public class Goods extends GoodsKey {
    private BigDecimal price;

    private Integer onSale;

    private Integer count;

    private String specialUri;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSpecialUri() {
        return specialUri;
    }

    public void setSpecialUri(String specialUri) {
        this.specialUri = specialUri;
    }
}