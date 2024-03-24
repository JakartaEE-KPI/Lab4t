package com.example.lab4.repository;

import com.example.lab4.entity.Journal;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JournalRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Journal findById(Long id) {
        return entityManager.find(Journal.class, id);
    }

    public Journal save(Journal journal) {
        return entityManager.merge(journal);
    }
}
