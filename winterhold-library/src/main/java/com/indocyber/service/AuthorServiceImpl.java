package com.indocyber.service;

import com.indocyber.dto.author.AuthorGridDto;
import com.indocyber.dto.author.UpsertAuthorDto;
import com.indocyber.entity.Author;
import com.indocyber.repository.AuthorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    private final int rowsInPage = 10;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public UpsertAuthorDto getUpdateAuthor(Integer id) {
        Optional<Author> authorRepositoryById = authorRepository.findById(id);
        Author author = authorRepositoryById.get();
        UpsertAuthorDto authorDto = new UpsertAuthorDto(
                author.getId(),
                author.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthDate(),
                author.getDeceasedDate(),
                author.getEducation(),
                author.getSummary()
        );
        return authorDto;
    }

    @Override
    public Integer saveAuthor(UpsertAuthorDto dto) {
        if(dto.getId() != null){
            Optional<Author> repository = authorRepository.findById(dto.getId());
            Author author = repository.get();
            author.setTitle(dto.getTitle());
            author.setFirstName(dto.getFirstName());
            author.setLastName(dto.getLastName());
            author.setBirthDate(dto.getBirthDate());
            author.setDeceasedDate(dto.getDeceasedDate());
            author.setEducation(dto.getEducation());
            author.setSummary(dto.getSummary());
            Author respond = authorRepository.save(author);
            return respond.getId();
        } else {
        Author author = new Author(
                dto.getTitle(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getDeceasedDate(),
                dto.getEducation(),
                dto.getSummary()
        );
        Author respond = authorRepository.save(author);
        return respond.getId();
        }
    }

    @Override
    public Long getTotalPages() {
        double totalData = (double) (authorRepository.count());
        long totalPage = (long)(Math.ceil(totalData/rowsInPage));
        return totalPage;
    }

    @Override
    public List<AuthorGridDto> getAuthorGrid(int page, String name) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<AuthorGridDto> grid = authorRepository.findAllAuthor(name, pagination);
        return grid;
    }

    @Override
    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }
}
