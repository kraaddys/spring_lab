package com.kostya.springlibrary.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> bookTitles;

    public Library() {}

    public Library(Long id, String name, List<String> bookTitles) {
        this.id = id;
        this.name = name;
        this.bookTitles = bookTitles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}
