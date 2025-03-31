package com.example.libmanagement.libman.repository;

import com.example.libmanagement.libman.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
