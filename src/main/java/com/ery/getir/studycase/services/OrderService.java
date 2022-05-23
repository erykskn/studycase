package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Order;
import com.ery.getir.studycase.collections.OrderItem;
import com.ery.getir.studycase.collections.Stock;
import com.ery.getir.studycase.constant.OrderItemStatus;
import com.ery.getir.studycase.dtos.OrderDto;
import com.ery.getir.studycase.dtos.OrderItemDto;
import com.ery.getir.studycase.exceptions.InvalidOrderException;
import com.ery.getir.studycase.exceptions.OrderNotFoundException;
import com.ery.getir.studycase.exceptions.StockUpdateException;
import com.ery.getir.studycase.repositories.OrderRepository;
import com.ery.getir.studycase.requests.NewOrderRequest;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final StockService stockService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, StockService stockService, OrderItemService orderItemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.stockService = stockService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    public List<OrderDto> getAllOrdersByCustomerId(String customerId, Pageable pageable) {
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId, pageable);
        return orderList.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    public OrderDto createNewOrder(NewOrderRequest orderDto){
        List<OrderItem> orderItemIds = Arrays.asList();
        Order order = new Order();
        order.setCustomer(customerService.findById(orderDto.getCustomerId()).get());
        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItemDtoList();
        if(CollectionUtils.isEmpty(orderItemDtoList)){
            throw new InvalidOrderException("Order item cannot be empty.");
        }

        orderItemDtoList.stream().forEach(orderItemDto -> {
            Stock stock = stockService.getStockByBookId(orderItemDto.getBookDto().getId());
            orderItemDto.setOrderItemStatus(OrderItemStatus.NOT_ADDED);

            if(stock.getQuantity() > orderItemDto.getQuantity()){
                orderItemDto.setOrderItemStatus(OrderItemStatus.ADDED);
                stock.setQuantity(stock.getQuantity() - orderItemDto.getQuantity());
                try{
                    stockService.updateStock(stock);
                }catch (OptimisticLockingFailureException ex){
                    throw new StockUpdateException();
                }
            }
            orderItemIds.add(orderItemService.save(orderItemDto));
        });
        order.setOrderItemList(orderItemIds);

        return modelMapper.map( orderRepository.save(order), OrderDto.class);
    }

    public OrderDto getOrderById(String orderId){
        Optional<Order> order = orderRepository.findById(orderId);

        if(!order.isPresent()){
            throw new OrderNotFoundException();
        }

        return modelMapper.map(order.get(), OrderDto.class);
    }


    public List<OrderDto> findAllByCreatedAtBetween(Date startDate, Date endDate){
        List<Order> orderList = orderRepository.findAllByCreatedAtBetween(startDate,endDate);

        if(CollectionUtils.isEmpty(orderList)){
            return Arrays.asList();
        }

        List<OrderDto> collect = orderList.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return collect;
    }

}
