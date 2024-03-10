package com.example.lab4;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class JournalRepository {

//    private EntityManager entityManager = Persistence
//            .createEntityManagerFactory("demo1")
//            .createEntityManager();

    @PersistenceContext(unitName = "demo1")
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Mark findById(Long id) {
        return entityManager.find(Mark.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Mark mark) {
        entityManager.persist(mark);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean testRollback() {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//
//            // Выполните здесь ваш запрос или проверку подключения
////            entityManager.persist(new Mark(null,true, 11));
//            transaction.commit();
//            System.out.println("uhooo");
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
//        } finally {
//            entityManager.close();
//        }
//        entityManager.persist(new Mark(null,true, 11));
        entityManager.persist(new Mark(null, true, 11));
        return true;
////        throw new RuntimeException();
    }

    public boolean isHibernateConnectedToDatabase() {
        entityManager.createNativeQuery("SELECT table_name \n" +
                "FROM information_schema.tables \n" +
                "WHERE table_schema = 'public'").getSingleResult();
        return true;
    }
}

