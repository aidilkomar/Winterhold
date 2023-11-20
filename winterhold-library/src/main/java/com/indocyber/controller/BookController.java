package com.indocyber.controller;

import com.indocyber.dto.book.BookGridDto;
import com.indocyber.dto.book.UpsertBookDto;
import com.indocyber.dto.customer.CustomerGridDto;
import com.indocyber.dto.customer.UpsertCustomerDto;
import com.indocyber.entity.Book;
import com.indocyber.entity.Category;
import com.indocyber.entity.Customer;
import com.indocyber.service.AuthorService;
import com.indocyber.service.BookService;
import com.indocyber.service.CategoryService;
import com.indocyber.service.CustomerService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    private CustomerService customerService;

    private CategoryService categoryService;

    private AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        // StringTrimmerEditor whitespace - leading and trailing
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true means trim to null

        //Preprocess every String form data
        // remove leading and trailing whitespace
        // If String only has whitespace, trim it to null
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("indexBook")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String name,
                        Model model){
        List<BookGridDto> bookGridDtoList = bookService.getBookGrid(page);
        long totalPages = bookService.getTotalPages();
        List<Book> books = bookService.findAllBooks();

        model.addAttribute("bookList", books);
        model.addAttribute("grid", bookGridDtoList);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);

        return "book/index-book";
    }

    @GetMapping("categoryForm")
    public String categoryForm(@RequestParam(value = "bookId", required = false) String id, Model model){
        if(id != null){
            UpsertBookDto dto = categoryService.getUpdateBook(id);
            model.addAttribute("book", dto);
            return "book/form-category";
        }

        return "book/form-category";
    }
}
