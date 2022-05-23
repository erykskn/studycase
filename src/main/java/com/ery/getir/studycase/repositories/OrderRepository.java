package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.collections.Customer;
import com.ery.getir.studycase.collections.Order;
import com.ery.getir.studycase.responses.MontlyStaticticReponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByCustomerId(String id, Pageable pageable);

    List<Order> findAllByCustomer(Customer customer);

    List<Order> findAllByCreatedAtBetween(Date startDate, Date endDate);

    @Query()
    MontlyStaticticReponse getCustomerMonthlyStatisticByCustomerId(String customerId);

}
