package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Book;
import com.ery.getir.studycase.dtos.BookDto;
import com.ery.getir.studycase.repositories.BookRepository;
import com.ery.getir.studycase.requests.NewBookRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public BookDto createNewBook(NewBookRequest bookDto){
        return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
    }

}
