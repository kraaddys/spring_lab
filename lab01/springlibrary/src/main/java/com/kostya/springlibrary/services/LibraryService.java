package com.kostya.springlibrary.services;

import com.kostya.springlibrary.dto.LibraryDTO;
import com.kostya.springlibrary.entity.Library;
import com.kostya.springlibrary.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    // Получение всех библиотек
    public List<LibraryDTO> getAllLibraries() {
        return repository.findAll().stream()
                .map(library -> new LibraryDTO(library.getId(), library.getName(), library.getBookTitles()))
                .collect(Collectors.toList());
    }

    // Создание библиотеки
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = new Library();
        library.setName(libraryDTO.getName());
        library.setBookTitles(libraryDTO.getBookTitles()); // Устанавливаем список названий книг
        repository.save(library);
        return new LibraryDTO(library.getId(), library.getName(), library.getBookTitles());
    }

    // Обновление библиотеки
    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Library library = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Библиотека не найдена"));
        library.setName(libraryDTO.getName());
        library.setBookTitles(libraryDTO.getBookTitles()); // Обновляем список названий книг
        repository.save(library);
        return new LibraryDTO(library.getId(), library.getName(), library.getBookTitles());
    }

    // Удаление библиотеки
    public void deleteLibrary(Long id) {
        repository.deleteById(id);
    }
}
