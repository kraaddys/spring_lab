package com.example.libmanagement.libman.dao;

import com.example.libmanagement.libman.entity.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager em;

    public List<Publisher> findAll() {
        return em.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }

    public Publisher findById(Long id) {
        return em.find(Publisher.class, id);
    }

    public Publisher save(Publisher publisher) {
        if (publisher.getId() == null) {
            em.persist(publisher);
            return publisher;
        } else {
            return em.merge(publisher);
        }
    }

    public void delete(Long id) {
        Publisher publisher = em.find(Publisher.class, id);
        if (publisher != null) {
            em.remove(publisher);
        }
    }
}
