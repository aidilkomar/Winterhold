package com.indocyber.service;

import com.indocyber.dto.author.AuthorGridDto;
import com.indocyber.dto.author.UpsertAuthorDto;
import com.indocyber.entity.Author;

import java.util.List;

public interface AuthorService {
    UpsertAuthorDto getUpdateAuthor(Integer id);

    Integer saveAuthor(UpsertAuthorDto dto);

    Long getTotalPages();

    List<AuthorGridDto> getAuthorGrid(int page, String name);

    List<Author> findAllAuthor();

    void deleteAuthor(Integer id);
}
