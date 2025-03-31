package com.example.libmanagement.libman.dto;

import java.util.Set;

public class BookDTO {
    private Long id;
    private String title;
    private Long authorId;
    private Long publisherId;
    private Set<Long> categoryIds;

    public BookDTO() {}

    public BookDTO(Long id, String title, Long authorId, Long publisherId, Set<Long> categoryIds) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryIds = categoryIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getPublisherId() { return publisherId; }
    public void setPublisherId(Long publisherId) { this.publisherId = publisherId; }

    public Set<Long> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(Set<Long> categoryIds) { this.categoryIds = categoryIds; }
}
