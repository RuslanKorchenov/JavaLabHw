package ru.itis.mongodb.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        AuthorsRepository authorsRepository = context.getBean(AuthorsRepository.class);
        BooksRepository booksRepository = context.getBean(BooksRepository.class);
        // Save
        Author ray = Author.builder()
                .firstname("Ray")
                .lastname("Bradbury")
                .build();
        Author lev = Author.builder()
                .firstname("Лев")
                .lastname("Толстой")
                .build();
        authorsRepository.save(lev);
        authorsRepository.save(ray);

        Book book = Book.builder()
                .name("Fahrenheit 451")
                .authors(Collections.singletonList(ray))
                .pages(451)
                .evaluation(9.9)
                .build();
        booksRepository.save(book);

        System.out.println(booksRepository.findAllByPagesGreaterThan(400));

        // Delete
        Author rayClone = authorsRepository.findByFirstname("Ray");
        authorsRepository.delete(rayClone);

        // Update
        Author fakelev = authorsRepository.findByFirstname("Лев");
        fakelev.setFirstname("Ну лев так лев");
        authorsRepository.save(fakelev);
    }
}
