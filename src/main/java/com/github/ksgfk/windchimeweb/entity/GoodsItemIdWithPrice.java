package com.github.ksgfk.windchimeweb.entity;

import java.math.BigDecimal;

public class GoodsItemIdWithPrice {
    private Integer itemId;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
