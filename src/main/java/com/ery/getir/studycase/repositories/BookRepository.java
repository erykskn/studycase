package com.ery.getir.studycase.repositories;

import com.ery.getir.studycase.collections.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
