package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dao.AuthorDao;
import com.example.libmanagement.libman.dto.AuthorDTO;
import com.example.libmanagement.libman.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public List<AuthorDTO> getAll() {
        return authorDao.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    public AuthorDTO create(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        Author saved = authorDao.save(author);
        return new AuthorDTO(saved.getId(), saved.getName());
    }

    public AuthorDTO update(Long id, AuthorDTO dto) {
        Author author = authorDao.findById(id);
        author.setName(dto.getName());
        Author updated = authorDao.save(author);
        return new AuthorDTO(updated.getId(), updated.getName());
    }

    public void delete(Long id) {
        authorDao.delete(id);
    }
}
