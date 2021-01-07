package ru.itis.mongodb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);
        // Save
        Author author = Author.builder()
                .firstname("Ray")
                .lastname("Bradbury")
                .build();
        template.save(author, "author");

        // Update
        Query query = new Query();
        query.addCriteria(Criteria.where("firstname").is("Ray"));
        Update update = new Update();
        update.set("firstname", "Not ray");
        template.updateFirst(query, update, Author.class);

        // Delete
        template.findAndRemove(query, Author.class);
    }
}
