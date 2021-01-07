package ru.itis.mongodb.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorsRepository extends MongoRepository<Author, String> {
    Author findByFirstname(String firstname);
}
