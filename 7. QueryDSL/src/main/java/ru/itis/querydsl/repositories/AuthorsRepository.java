package ru.itis.querydsl.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.querydsl.models.Author;

public interface AuthorsRepository extends MongoRepository<Author, ObjectId>, QuerydslPredicateExecutor<Author> {
    Author findByFirstName(String firstName);
}
