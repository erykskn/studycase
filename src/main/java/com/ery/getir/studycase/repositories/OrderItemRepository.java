package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.collections.OrderItem;
import com.ery.getir.studycase.dtos.OrderItemDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository  extends MongoRepository<OrderItem, String> {
}
