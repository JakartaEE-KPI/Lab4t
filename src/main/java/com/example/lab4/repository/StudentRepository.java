package com.example.lab4.repository;

import com.example.lab4.entity.Student;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRepository {

    @PersistenceContext(unitName = "demo1")
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findBySubjectId(Long subjectId) {
        return entityManager.createQuery(
                        "select s from Student s join s.subjects ss " +
                                "where ss.id=:subjectId", Student.class)
                .setParameter("subjectId", subjectId)
                .getResultList();
    }

    public Student save(Student student) {
        return entityManager.merge(student);
    }
}
