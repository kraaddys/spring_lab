package com.example.libmanagement.libman.repository;

import com.example.libmanagement.libman.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
