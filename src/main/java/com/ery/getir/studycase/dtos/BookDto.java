package com.ery.getir.studycase.dtos;

import com.ery.getir.studycase.base.BaseDto;

import java.math.BigDecimal;

public class BookDto extends BaseDto {

    private String name;

    private StockDto stock;

    private boolean onSale;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StockDto getStock() {
        return stock;
    }

    public void setStock(StockDto stock) {
        this.stock = stock;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
