package ru.itis.mongodb.driver;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("library");
        MongoCollection<Document> collection = database.getCollection("books");
        // Save
        collection.insertOne(new Document("name", "Fahrenheit 451")
                .append("pages", 451)
                .append("author", "Ray Bradbury")
                .append("evaluation", 9.9));
        collection.insertOne(new Document("name", "Война и миръ")
                .append("pages", 2500)
                .append("author", "Лев Толстой")
                .append("evaluation", 9.6));
        // Update
        collection.updateOne(lt("evaluation", 9.7), new Document("$set", new Document("evaluation", 9.7)));
        // Delete
        collection.deleteMany(eq("author", "Лев Толстой"));
    }
}
