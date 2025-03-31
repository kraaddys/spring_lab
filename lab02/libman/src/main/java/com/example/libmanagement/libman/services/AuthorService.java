package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dto.AuthorDTO;
import com.example.libmanagement.libman.entity.Author;
import com.example.libmanagement.libman.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<AuthorDTO> getAll() {
        return repository.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public AuthorDTO create(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        Author saved = repository.save(author);
        return new AuthorDTO(saved.getId(), saved.getName());
    }

    public AuthorDTO update(Long id, AuthorDTO dto) {
        Author author = repository.findById(id).orElseThrow();
        author.setName(dto.getName());
        Author updated = repository.save(author);
        return new AuthorDTO(updated.getId(), updated.getName());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
