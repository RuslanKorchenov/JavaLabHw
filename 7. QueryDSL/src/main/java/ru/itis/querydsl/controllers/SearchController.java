package ru.itis.querydsl.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.querydsl.dto.AuthorDto;
import ru.itis.querydsl.models.Author;
import ru.itis.querydsl.models.Book;
import ru.itis.querydsl.repositories.AuthorsRepository;
import ru.itis.querydsl.repositories.BooksRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private  BooksRepository booksRepository;
    @GetMapping("/authors/search")
    public ResponseEntity<List<AuthorDto>> searchByPredicate(@QuerydslPredicate(root = Author.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(authorsRepository.findAll(predicate).spliterator(), true)
                        .map(author ->
                                AuthorDto.builder()
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .bookNames(author.getBooks().stream().map(book -> booksRepository.findById(book.get_id()).get().getName()).collect(Collectors.toList()))
                                        .build()).collect(Collectors.toList()));
    }


}