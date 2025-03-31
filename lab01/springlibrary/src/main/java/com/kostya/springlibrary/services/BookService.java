package com.kostya.springlibrary.services;

import com.kostya.springlibrary.dto.BookDTO;
import com.kostya.springlibrary.entity.Book;
import com.kostya.springlibrary.entity.Author;
import com.kostya.springlibrary.entity.Publisher;
import com.kostya.springlibrary.repository.BookRepository;
import com.kostya.springlibrary.repository.AuthorRepository;
import com.kostya.springlibrary.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }


    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getPublisher().getId()))
                .collect(Collectors.toList());
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Автор не найден"));

        Publisher publisher = publisherRepository.findById(bookDTO.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Издатель не найден"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setPublisher(publisher);

        bookRepository.save(book);

        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getPublisher().getId());
    }


    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getPublisher().getId());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
