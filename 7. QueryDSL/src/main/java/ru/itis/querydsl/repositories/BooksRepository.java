package ru.itis.querydsl.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.querydsl.models.Book;

import java.util.List;

public interface BooksRepository extends MongoRepository<Book, ObjectId> {
//
//    @RestResource(path = "byPagesGreater", rel = "pagesGreater")
//    @Query(value = "{pages: {$gt: ?0}}")
//    List<Book> findAllByPagesGreaterThan(@Param("pages") int pages);
    List<Book> findAllByAuthorsEquals(ObjectId id);
}
