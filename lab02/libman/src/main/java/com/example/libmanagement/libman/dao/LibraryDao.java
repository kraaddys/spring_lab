package com.example.libmanagement.libman.dao;

import com.example.libmanagement.libman.entity.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class LibraryDao {

    @PersistenceContext
    private EntityManager em;

    public List<Library> findAll() {
        return em.createQuery("SELECT l FROM Library l", Library.class).getResultList();
    }

    public Library findById(Long id) {
        return em.find(Library.class, id);
    }

    public Library save(Library library) {
        if (library.getId() == null) {
            em.persist(library);
            return library;
        } else {
            return em.merge(library);
        }
    }

    public void delete(Long id) {
        Library library = em.find(Library.class, id);
        if (library != null) {
            em.remove(library);
        }
    }
}
