package com.example.lab4.repository;

import com.example.lab4.entity.Subject;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Stateless
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Subject findById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public List<Subject> findByJournalId(Long journalId) {
        return entityManager.createQuery("select s from Subject s where s.journal.id=:journalId", Subject.class)
                .setParameter("journalId", journalId)
                .getResultList();
    }

    public Subject save(Subject subject) {
        return entityManager.merge(subject);
    }

    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
