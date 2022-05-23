package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.dtos.CustomerDto;
import com.ery.getir.studycase.dtos.JWTDto;
import com.ery.getir.studycase.requests.NewCustomerRequest;
import com.ery.getir.studycase.responses.JWTResponse;
import com.ery.getir.studycase.responses.SuccessResponse;
import com.ery.getir.studycase.services.AuthenticationService;
import com.ery.getir.studycase.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class JWTController {

    private final AuthenticationService authenticationService;
    private final CustomerService customerService;


    public JWTController(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @PostMapping("/token")
    public ResponseEntity<JWTResponse> token(@RequestBody @Valid JWTDto authenticationRequest) {
        return ResponseEntity.ok(authenticationService.token(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> create(@Valid @RequestBody NewCustomerRequest customerDto) {
        CustomerDto customer = customerService.createNewCustomer(customerDto);
        URI location = URI.create(String.format("/customer"+ "/%s", customer.getId()));
        SuccessResponse success = new SuccessResponse();
        success.setMessage("Customer is created");
        return ResponseEntity
                .created(location)
                .body(success);
    }

}
