package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.collections.Book;
import com.ery.getir.studycase.collections.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    Optional<Stock> findByBook(Book bookId);
}
