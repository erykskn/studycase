package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.mongodb.assertions.Assertions.assertNotNull;


public class BookRepositoryTest extends BaseApplicationTest {

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void whenTheSaveBookThenInsertDocument() {
        Book book = new Book();
        book.setName("olu canlar");
        book.setPrice(BigDecimal.valueOf(14.12));
        book.setOnSale(true);
        Book newBook = bookRepository.save(book);
        assertNotNull(newBook.getId());
        assertNotNull(newBook.getPrice());
    }

}
