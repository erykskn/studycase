package com.ery.getir.studycase.services;


import com.ery.getir.studycase.collections.OrderItem;
import com.ery.getir.studycase.dtos.OrderItemDto;
import com.ery.getir.studycase.repositories.OrderItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public OrderItemService(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }

    public OrderItemDto createNewOrderItem(OrderItemDto orderItemDto){
        OrderItem orderItem = orderItemRepository.save(modelMapper.map(orderItemDto, OrderItem.class));
        return modelMapper.map(orderItem, OrderItemDto.class);
    }
    public OrderItem save(OrderItemDto orderItemDto){
        return orderItemRepository.save(modelMapper.map(orderItemDto, OrderItem.class));
    }
}
