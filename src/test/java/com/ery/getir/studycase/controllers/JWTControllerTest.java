package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.dtos.JWTDto;
import com.ery.getir.studycase.repositories.CustomerRepository;
import com.ery.getir.studycase.requests.NewCustomerRequest;
import com.ery.getir.studycase.services.JwtTokenService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JWTControllerTest extends BaseApplicationTest {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldGetToken() throws Exception {
        JWTDto request = new JWTDto();
        request.setUsername("username@gmail.com");
        request.setPassword(TEST_PASSWORD);
        ResultActions resultActions = postRequest("/api/auth/token", request);
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void shouldCreateNewCustomer() throws Exception {
        NewCustomerRequest customerRequest = new NewCustomerRequest();
        customerRequest.setEmail("eray@gmail.com");
        customerRequest.setPassword(TEST_PASSWORD);
        customerRequest.setName("eray");
        customerRequest.setSurname("keskin");

        ResultActions resultActions = postRequest("/api/auth/register", customerRequest);
        resultActions.andExpect(status().isCreated());

        Optional<Customer> customer = customerRepository.findCustomerByEmail("eray@gmail.com");
        Assert.assertTrue(customer.isPresent());
    }
}
