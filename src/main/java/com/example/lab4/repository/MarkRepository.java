package com.example.lab4.repository;

import com.example.lab4.entity.Mark;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MarkRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Mark findById(Long id) {
        return entityManager.find(Mark.class, id);
    }

    public List<Mark> findByStudentId(Long studentId) {
        return entityManager.createQuery("select m from Mark m where m.student.id=:studentId", Mark.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    public List<Mark> findByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return entityManager.createQuery("""
                                select m from Mark m
                                where m.student.id=:studentId
                                    and m.subject.id=:subjectId""",
                        Mark.class)
                .setParameter("studentId", studentId)
                .setParameter("subjectId", subjectId)
                .getResultList();
    }

    public Mark save(Mark mark) {
        entityManager.persist(mark);
        return entityManager.merge(mark);
    }

    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Mark m where m.id=:id");
        query.setParameter("id", id).executeUpdate();
    }
}
