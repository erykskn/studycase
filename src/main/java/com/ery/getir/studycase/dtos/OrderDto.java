package com.ery.getir.studycase.dtos;

import com.ery.getir.studycase.base.BaseDto;
import com.ery.getir.studycase.constant.OrderStatus;

import java.util.List;

public class OrderDto extends BaseDto {
    private List<OrderItemDto> orderItemDtos;

    private CustomerDto customerDto;

    private OrderStatus orderStatus;

    public List<OrderItemDto> getOrderItemDtos() {
        return orderItemDtos;
    }

    public void setOrderItemDtos(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
