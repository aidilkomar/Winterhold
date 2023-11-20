package com.indocyber.dto.book;

import com.indocyber.entity.Author;
import com.indocyber.entity.Category;

import java.time.LocalDate;

public class UpsertBookDto {

    private String code;

    private String title;

    private String categoryName;

    private Category category;

    private Integer authorId;

    private Author author;

    private Boolean isBorrowed;

    private String summary;

    private LocalDate releaseDate;

    private Integer totalPage;

    public UpsertBookDto() {
    }

    public UpsertBookDto(String code, String title, String categoryName, Category category, Integer authorId, Author author, Boolean isBorrowed, String summary, LocalDate releaseDate, Integer totalPage) {
        this.code = code;
        this.title = title;
        this.categoryName = categoryName;
        this.category = category;
        this.authorId = authorId;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.totalPage = totalPage;
    }
}
