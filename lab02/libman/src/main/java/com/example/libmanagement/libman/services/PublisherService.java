package com.example.libmanagement.libman.services;

import com.example.libmanagement.libman.dao.PublisherDao;
import com.example.libmanagement.libman.dto.PublisherDTO;
import com.example.libmanagement.libman.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherDao publisherDao;

    public PublisherService(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    public List<PublisherDTO> getAll() {
        return publisherDao.findAll().stream()
                .map(p -> new PublisherDTO(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }

    public PublisherDTO create(PublisherDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        Publisher saved = publisherDao.save(publisher);
        return new PublisherDTO(saved.getId(), saved.getName());
    }

    public PublisherDTO update(Long id, PublisherDTO dto) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(dto.getName());
        Publisher updated = publisherDao.save(publisher);
        return new PublisherDTO(updated.getId(), updated.getName());
    }

    public void delete(Long id) {
        publisherDao.delete(id);
    }
}
