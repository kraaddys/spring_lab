package com.kostya.springlibrary.controller;

import com.kostya.springlibrary.dto.LibraryDTO;
import com.kostya.springlibrary.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @GetMapping
    public List<LibraryDTO> getAllLibraries() {
        return service.getAllLibraries();
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryDTO libraryDTO) {
        LibraryDTO createdLibrary = service.createLibrary(libraryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO) {
        LibraryDTO updatedLibrary = service.updateLibrary(id, libraryDTO);
        return ResponseEntity.ok(updatedLibrary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        service.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}
