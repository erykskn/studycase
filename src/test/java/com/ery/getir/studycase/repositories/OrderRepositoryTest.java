package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.collections.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mongodb.assertions.Assertions.assertNotNull;

public class OrderRepositoryTest extends BaseApplicationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenTheSaveOrderThenInsertDocument() {
        Customer customer = new Customer();
        customer.setEmail("example@gmail.com");
        customer.setName("example");

        Order order = new Order();
        order.setCustomer(customerRepository.save(customer));
        Order newItem = orderRepository.save(order);
        assertNotNull(newItem.getId());
    }
}
