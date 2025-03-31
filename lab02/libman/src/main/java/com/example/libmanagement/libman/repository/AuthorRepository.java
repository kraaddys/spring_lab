package com.example.libmanagement.libman.repository;

import com.example.libmanagement.libman.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
