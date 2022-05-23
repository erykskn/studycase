package com.ery.getir.studycase.dtos;

import com.ery.getir.studycase.base.BaseDto;
import com.ery.getir.studycase.constant.OrderItemStatus;

import java.math.BigDecimal;

public class OrderItemDto extends BaseDto {

    private BookDto bookDto;
    private int quantity;
    private OrderItemStatus orderItemStatus;
    private BigDecimal price;

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItemStatus getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(OrderItemStatus orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
