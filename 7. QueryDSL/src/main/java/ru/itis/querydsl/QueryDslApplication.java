package ru.itis.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.querydsl.models.Author;
import ru.itis.querydsl.models.Book;
import ru.itis.querydsl.repositories.AuthorsRepository;
import ru.itis.querydsl.repositories.BooksRepository;

import java.util.Collections;

@SpringBootApplication
public class QueryDslApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryDslApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        AuthorsRepository authorsRepository = context.getBean(AuthorsRepository.class);
        BooksRepository booksRepository = context.getBean(BooksRepository.class);
        // Save
        Author ray = Author.builder()
                .firstName("Ray")
                .lastName("Bradbury")
                .build();
        Author lev = Author.builder()
                .firstName("Лев")
                .lastName("Толстой")
                .build();

        authorsRepository.save(lev);
        authorsRepository.save(ray);

        Book book = Book.builder()
                .name("Fahrenheit 451")
                .authors(ray)
                .pages(451)
                .evaluation(9.9)
                .build();

        Book book1 = Book.builder()
                .name("Война и мир")
                .authors(lev)
                .pages(100000)
                .evaluation(10.0)
                .build();
        Book book2 = Book.builder()
                .name("Анна Каренина")
                .authors(lev)
                .pages(5000)
                .evaluation(9.6)
                .build();
        booksRepository.save(book);
        booksRepository.save(book1);
        booksRepository.save(book2);

    }

}
