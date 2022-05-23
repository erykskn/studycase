package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.requests.NewCustomerRequest;
import com.ery.getir.studycase.services.CustomerService;
import com.ery.getir.studycase.services.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<?> createNewCustomer(@RequestBody NewCustomerRequest customerDto) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDto), HttpStatus.OK);
    }

    @GetMapping("/getAllOrdersByCustomerId/{customerId}")
    public ResponseEntity<?> getAllOrdersByCustomerId(@PathVariable String customerId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(orderService.getAllOrdersByCustomerId(customerId, pageable), HttpStatus.OK);
    }

}
