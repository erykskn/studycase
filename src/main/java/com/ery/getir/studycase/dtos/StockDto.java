package com.ery.getir.studycase.dtos;

import com.ery.getir.studycase.base.BaseDto;
import com.ery.getir.studycase.collections.Book;

public class StockDto extends BaseDto {

    private BookDto bookDto;

    private Long version;

    private int quantity;

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
