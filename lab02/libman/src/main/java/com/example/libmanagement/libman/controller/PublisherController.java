package com.example.libmanagement.libman.controller;

import com.example.libmanagement.libman.dto.PublisherDTO;
import com.example.libmanagement.libman.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping
    public List<PublisherDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> create(@RequestBody PublisherDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> update(@PathVariable Long id, @RequestBody PublisherDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
