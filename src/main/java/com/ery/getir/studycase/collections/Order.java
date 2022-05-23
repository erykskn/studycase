package com.ery.getir.studycase.collections;


import com.ery.getir.studycase.base.BaseDocument;
import com.ery.getir.studycase.constant.OrderStatus;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "order")
public class Order extends BaseDocument {

    @DocumentReference
    private List<OrderItem> orderItemList;

    @DocumentReference(collection = "customer")
    private Customer customer;

    private OrderStatus orderStatus = OrderStatus.ACTIVE;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
