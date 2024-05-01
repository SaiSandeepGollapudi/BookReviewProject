package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BookCascadeSampleImpl {
    @Autowired
    BookRepository bookRepository;

    public void testCascade(Integer id){

        bookRepository.deleteById(id);

    }

}
