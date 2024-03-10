package com.example.lab4;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @EJB
    private JournalRepository journalRepository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        journalRepository.save(Mark.builder()
                .presence(true)
                .knowledgeLevel(14)
                .build());
//        EntityManager entityManager = Persistence.createEntityManagerFactory("demo1").createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//
//            // Выполните здесь ваш запрос или проверку подключения
//            Mark mark = entityManager.find(Mark.class, 1L);
//            transaction.commit();
//            System.out.println("Подключение к базе данных успешно.");
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
//        } finally {
//            entityManager.close();
//        }
    }

}