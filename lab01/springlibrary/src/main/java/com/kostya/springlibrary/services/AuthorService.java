package com.kostya.springlibrary.services;

import com.kostya.springlibrary.entity.Author;
import com.kostya.springlibrary.dto.AuthorDTO;
import com.kostya.springlibrary.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    // Получение всех авторов
    public List<AuthorDTO> getAllAuthors() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Author::getId))
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }


    // Создание автора
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());

        Author savedAuthor = repository.save(author);
        return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName());
    }

    // Обновление автора
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> optionalAuthor = repository.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(authorDTO.getName());

            Author updatedAuthor = repository.save(author);
            return new AuthorDTO(updatedAuthor.getId(), updatedAuthor.getName());
        } else {
            throw new RuntimeException("Автор с ID " + id + " не найден!");
        }
    }

    // Удаление автора
    public void deleteAuthor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Автор с ID " + id + " не найден!");
        }
    }
}
