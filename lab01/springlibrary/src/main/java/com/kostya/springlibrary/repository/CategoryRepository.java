package com.kostya.springlibrary.repository;

import com.kostya.springlibrary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
