package com.indocyber.service;

import com.indocyber.dto.book.BookGridDto;
import com.indocyber.dto.book.UpsertBookDto;
import com.indocyber.entity.Book;

import java.util.List;

public interface BookService {
    List<BookGridDto> getBookGrid(Integer page);

    long getTotalPages();

    List<Book> findAllBooks();

    UpsertBookDto getUpdateBook(String id);
}
