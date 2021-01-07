package ru.itis.mongodb.spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private String _id;
    private Map<String, String> author;
    private Double evaluation;
    private String name;
    private Integer pages;
}
