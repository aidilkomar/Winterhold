package com.indocyber.service;

import com.indocyber.dto.book.UpsertBookDto;

public interface CategoryService {
    UpsertBookDto getUpdateBook(String id);
}
