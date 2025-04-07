package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dao.AuthorDao;
import com.example.libmanagement.libman.dao.BookDao;
import com.example.libmanagement.libman.dao.CategoryDao;
import com.example.libmanagement.libman.dao.PublisherDao;
import com.example.libmanagement.libman.dto.BookDTO;
import com.example.libmanagement.libman.entity.Author;
import com.example.libmanagement.libman.entity.Book;
import com.example.libmanagement.libman.entity.Category;
import com.example.libmanagement.libman.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final CategoryDao categoryDao;

    public BookService(BookDao bookDao, AuthorDao authorDao, PublisherDao publisherDao, CategoryDao categoryDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;
        this.categoryDao = categoryDao;
    }

    public List<BookDTO> getAll() {
        return bookDao.findAll().stream()
                .map(b -> new BookDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor().getId(),
                        b.getPublisher().getId(),
                        b.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
                ))
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO dto) {
        Author author = authorDao.findById(dto.getAuthorId());
        Publisher publisher = publisherDao.findById(dto.getPublisherId());
        Set<Category> categories = new HashSet<>(categoryDao.findAll().stream()
                .filter(c -> dto.getCategoryIds().contains(c.getId()))
                .collect(Collectors.toSet()));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        Book saved = bookDao.save(book);

        return new BookDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor().getId(),
                saved.getPublisher().getId(),
                saved.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
        );
    }

    public BookDTO update(Long id, BookDTO dto) {
        Book book = bookDao.findById(id);
        book.setTitle(dto.getTitle());

        Set<Category> categories = new HashSet<>(categoryDao.findAll().stream()
                .filter(c -> dto.getCategoryIds().contains(c.getId()))
                .collect(Collectors.toSet()));
        book.setCategories(categories);

        Book updated = bookDao.save(book);

        return new BookDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getAuthor().getId(),
                updated.getPublisher().getId(),
                updated.getCategories().stream().map(Category::getId).collect(Collectors.toSet())
        );
    }

    public void delete(Long id) {
        bookDao.delete(id);
    }
}
