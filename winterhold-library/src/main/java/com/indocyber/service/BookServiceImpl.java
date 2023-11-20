package com.indocyber.service;

import com.indocyber.dto.book.BookGridDto;
import com.indocyber.dto.book.UpsertBookDto;
import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.dto.customer.UpsertCustomerDto;
import com.indocyber.entity.Book;
import com.indocyber.entity.Customer;
import com.indocyber.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    private final int rowsInPage = 10;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookGridDto> getBookGrid(Integer page) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<BookGridDto> grid = bookRepository.findAllBook(pagination);
        return grid;
    }

    @Override
    public long getTotalPages() {
        double totalData = (double) (bookRepository.count());
        long totalPage = (long)(Math.ceil(totalData/rowsInPage));
        return totalPage;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public UpsertBookDto getUpdateBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book = bookOptional.get();
        UpsertBookDto bookDto = new UpsertBookDto(
                book.getCode(),
                book.getTitle(),
                book.getCategoryName(),
                book.getCategory(),
                book.getAuthorId(),
                book.getAuthor(),
                book.getBorrowed(),
                book.getSummary(),
                book.getReleaseDate(),
                book.getTotalPage()
        );
        return bookDto;
    }
}
