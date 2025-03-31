package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dto.PublisherDTO;
import com.example.libmanagement.libman.entity.Publisher;
import com.example.libmanagement.libman.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<PublisherDTO> getAll() {
        return repository.findAll().stream()
                .map(p -> new PublisherDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }

    public PublisherDTO create(PublisherDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        Publisher saved = repository.save(publisher);
        return new PublisherDTO(saved.getId(), saved.getName());
    }

    public PublisherDTO update(Long id, PublisherDTO dto) {
        Publisher publisher = repository.findById(id).orElseThrow();
        publisher.setName(dto.getName());
        Publisher updated = repository.save(publisher);
        return new PublisherDTO(updated.getId(), updated.getName());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
