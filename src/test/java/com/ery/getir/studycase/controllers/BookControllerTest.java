package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.base.BaseApplicationTest;
import com.ery.getir.studycase.collections.Book;
import com.ery.getir.studycase.collections.Stock;
import com.ery.getir.studycase.dtos.BookDto;
import com.ery.getir.studycase.repositories.BookRepository;
import com.ery.getir.studycase.repositories.StockRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BookControllerTest extends BaseApplicationTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void shouldCreateNewBook() throws Exception {
        BookDto newBookRequest = new BookDto();
        newBookRequest.setName("dogunun limanlar");
        newBookRequest.setPrice(BigDecimal.valueOf(123.45));
        Book book = bookRepository.save(modelMapper.map(newBookRequest, Book.class));

        ResultActions resultActions = postRequest("/api/book/", newBookRequest);

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value(book.getName()))
                .andExpect(jsonPath("$.price").value(BigDecimal.valueOf(123.45)));
    }

    @Test
    void shouldUpdateStokByBookIdAndQuantity() throws Exception {

        BookDto newBookRequest = new BookDto();
        newBookRequest.setName("dogunun limanlar");
        newBookRequest.setPrice(BigDecimal.valueOf(123.45));
        Book book = bookRepository.save(modelMapper.map(newBookRequest, Book.class));
        Stock stock = new Stock();
        stock.setQuantity(10000);
        stock.setBook(book);
        stockRepository.save(stock);

        ResultActions resultActions = getRequest("/api/book/updateStockBy/" + book.getId() + "/quantity/" + 10);

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.quantity").value(10));
    }
}
