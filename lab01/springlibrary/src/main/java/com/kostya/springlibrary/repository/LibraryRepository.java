package com.kostya.springlibrary.repository;

import com.kostya.springlibrary.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {}
