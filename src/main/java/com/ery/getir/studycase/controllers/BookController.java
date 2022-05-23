package com.ery.getir.studycase.controllers;

import com.ery.getir.studycase.requests.NewBookRequest;
import com.ery.getir.studycase.services.BookService;
import com.ery.getir.studycase.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final StockService stockService;

    public BookController(BookService bookService, StockService stockService) {
        this.bookService = bookService;
        this.stockService = stockService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewBook(@RequestBody NewBookRequest bookDto) {
        return new ResponseEntity<>(bookService.createNewBook(bookDto), HttpStatus.OK);
    }

    @GetMapping("/updateStockBy/{bookId}/quantity/{quantity}")
    public ResponseEntity<?> updateBooksStock(@PathVariable String bookId, @PathVariable int quantity) {
        return new ResponseEntity<>(stockService.updateStock(bookId, quantity), HttpStatus.OK);
    }


}
