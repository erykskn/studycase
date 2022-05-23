package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerRepositoryTest extends BaseApplicationTest {

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void whenCallFindByCustomerByEmalThenReturnCustomer() {
        Customer customer = getCustomer();
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        Assert.assertEquals(customer.getName(), customerByEmail.get().getName());
    }

}
