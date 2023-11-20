package com.indocyber.repository;

import com.indocyber.dto.author.AuthorGridDto;
import com.indocyber.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("""
            SELECT new com.indocyber.dto.author.AuthorGridDto(aut.id, CONCAT(aut.title, ' ', aut.firstName, ' ', aut.lastName), aut.birthDate, aut.deceasedDate, aut.education, aut.summary)
            FROM Author AS aut
            WHERE CONCAT(aut.firstName, aut.lastName) LIKE %:name%
            """)
    public List<AuthorGridDto> findAllAuthor(@Param("name") String name, Pageable pageable);

    @Query("""
			SELECT COUNT(aut.id)
			FROM Author AS aut
			""")
    public long count();
}
