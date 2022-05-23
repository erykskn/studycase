package com.ery.getir.studycase.services;

import com.ery.getir.studycase.collections.Stock;
import com.ery.getir.studycase.dtos.StockDto;
import com.ery.getir.studycase.exceptions.QuantityNotAppropriateForUpdateStock;
import com.ery.getir.studycase.exceptions.StockNotFounByGivenBookIdException;
import com.ery.getir.studycase.exceptions.StockUpdateException;
import com.ery.getir.studycase.repositories.BookRepository;
import com.ery.getir.studycase.repositories.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final BookRepository bookRepository;
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    public StockService(BookRepository bookRepository, StockRepository stockRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }

    public StockDto updateStock(String bookId, int quantity) {
        if(quantity < 0){
            throw new QuantityNotAppropriateForUpdateStock();
        }
        Stock stockByBookId = getStockByBookId(bookId);
        stockByBookId.setQuantity(quantity);
        try {
            stockRepository.save(stockByBookId);
        }catch (OptimisticLockingFailureException ex){
            throw new StockUpdateException();
        }

        return modelMapper.map(stockByBookId , StockDto.class);
    }

    public Stock getStockByBookId(String bookId) {

        return stockRepository.findByBook(bookRepository.findById(bookId).get()).orElseThrow(StockNotFounByGivenBookIdException::new);
    }
}
