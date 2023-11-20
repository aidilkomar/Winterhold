package com.indocyber.repository;

import com.indocyber.dto.book.BookGridDto;
import com.indocyber.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query("""
            SELECT new com.indocyber.dto.book.BookGridDto(boo.code, boo.title, boo.categoryName, boo.authorId, boo.isBorrowed, boo.summary, boo.releaseDate, boo.totalPage)
            FROM Book AS boo""")
    List<BookGridDto> findAllBook(Pageable pagination);

    @Query("""
			SELECT COUNT(*)
			FROM Book AS boo
			""")
    public long count();
}
