package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Book;
import com.ery.getir.studycase.collections.Stock;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockRepositoryTest extends BaseApplicationTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeAll
    private void saveStock() {
        book = new Book();
        book.setName("olu canlar");
        Stock stock = new Stock();
        book = bookRepository.save(book);

        stock.setBook(book);
        stock.setQuantity(1);
        stockRepository.save(stock);
    }

    @Test
    public void whenCallFindByBookIdIdThenReturnStock() {
        Optional<Stock> stock = stockRepository.findByBook(book);
        Assert.assertNotNull(stock.get().getId());
    }

}
