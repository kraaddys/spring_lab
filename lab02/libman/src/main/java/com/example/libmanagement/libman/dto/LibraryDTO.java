package com.example.libmanagement.libman.dto;

import java.util.List;

public class LibraryDTO {
    private Long id;
    private String name;
    private List<String> bookTitles;

    public LibraryDTO() {}

    public LibraryDTO(Long id, String name, List<String> bookTitles) {
        this.id = id;
        this.name = name;
        this.bookTitles = bookTitles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getBookTitles() { return bookTitles; }
    public void setBookTitles(List<String> bookTitles) { this.bookTitles = bookTitles; }
}
