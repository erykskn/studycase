package com.ery.getir.studycase.collections;

import com.ery.getir.studycase.base.BaseDocument;
import com.ery.getir.studycase.constant.OrderItemStatus;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(collection = "orderItem")
public class OrderItem extends BaseDocument {

    @DocumentReference(collection = "book")
    private Book book;
    private int quantity;
    private OrderItemStatus orderItemStatus;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
