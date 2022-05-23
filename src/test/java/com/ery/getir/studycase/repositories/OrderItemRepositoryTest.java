package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Book;
import com.ery.getir.studycase.collections.OrderItem;
import com.ery.getir.studycase.collections.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mongodb.assertions.Assertions.assertNotNull;

public class OrderItemRepositoryTest extends BaseApplicationTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void whenTheSaveOrderItemThenInsertDocument() {
        Book book = new Book();
        book.setName("olu canlar");

        OrderItem orderItem = new OrderItem();
        orderItem.setBook(bookRepository.save(book));
        orderItem.setQuantity(12);
        OrderItem newItem = orderItemRepository.save(orderItem);
        assertNotNull(newItem.getId());
        assertNotNull(newItem.getQuantity());
    }

}
