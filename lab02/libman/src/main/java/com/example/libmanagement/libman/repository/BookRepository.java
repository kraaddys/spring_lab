package com.example.libmanagement.libman.repository;

import com.example.libmanagement.libman.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
