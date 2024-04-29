package com.goodReads.library.service.impl;

import com.goodReads.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookValidation {// internally it's dependent on ValidationService

    @Autowired
    ValidationService validationService;

    public void setValidationService(ValidationService validationService){
        this.validationService=validationService;
    }

    public boolean validateBook(Book book){
        return validationService.validate(book);
    }
}
