package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dto.BookDTO;
import com.example.libmanagement.libman.entity.*;
import com.example.libmanagement.libman.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final PublisherRepository publisherRepo;
    private final CategoryRepository categoryRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo, PublisherRepository publisherRepo, CategoryRepository categoryRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.publisherRepo = publisherRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<BookDTO> getAll() {
        return bookRepo.findAll().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getId(),
                        book.getPublisher().getId(),
                        book.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO dto) {
        Author author = authorRepo.findById(dto.getAuthorId()).orElseThrow();
        Publisher publisher = publisherRepo.findById(dto.getPublisherId()).orElseThrow();
        Set<Category> categories = new HashSet<>(categoryRepo.findAllById(dto.getCategoryIds()));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        Book saved = bookRepo.save(book);

        return new BookDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor().getId(),
                saved.getPublisher().getId(),
                saved.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
        );
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book book = bookRepo.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        Set<Category> categories = new HashSet<>(categoryRepo.findAllById(dto.getCategoryIds()));
        book.setCategories(categories);
        Book updated = bookRepo.save(book);
        return new BookDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getAuthor().getId(),
                updated.getPublisher().getId(),
                updated.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
        );
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
}
