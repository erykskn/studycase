package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.repositories.CustomerRepository;
import com.ery.getir.studycase.requests.NewCustomerRequest;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CustomerControllerTest extends BaseApplicationTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    void shouldCreateNewCustomer() throws Exception {
        NewCustomerRequest customer = new NewCustomerRequest();
        customer.setName("eray");
        customer.setSurname("keskin");
        customer.setEmail("eray@gmail.com");
        customer.setPassword("deneme");
        ResultActions resultActions = postRequest("/api/customer/", customer);

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.email").value(customer.getEmail()));
    }
}
