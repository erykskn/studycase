package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.requests.NewOrderRequest;
import com.ery.getir.studycase.services.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createNewCustomer(@RequestBody NewOrderRequest orderDto) {
        return new ResponseEntity<>(orderService.createNewOrder(orderDto), HttpStatus.OK);
    }

    @GetMapping("/orderById/{orderId}")
    public ResponseEntity<?> getAllOrdersByCustomerId(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/listOfOrderIntervalTwoDate/{startDate}/{endDate}")
    public ResponseEntity<?> listOfOrderIntervalTwoDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return new ResponseEntity<>(orderService.findAllByCreatedAtBetween(startDate, endDate), HttpStatus.OK);
    }
}
