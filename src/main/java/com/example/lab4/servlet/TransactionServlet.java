package com.example.lab4.servlet;

import com.example.lab4.repository.MarkRepository;
import com.example.lab4.repository.StudentRepository;
import com.example.lab4.repository.SubjectRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TransactionServlet", value = "/transaction")
public class TransactionServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @EJB
    private StudentRepository studentRepository;

    @EJB
    private SubjectRepository subjectRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer markCount = Integer.valueOf(req.getParameter("markCount"));
        Long studentId = Long.valueOf(req.getParameter("studentId"));
        Long subjectId = Long.valueOf(req.getParameter("subjectId"));
        try {
            markRepository.testTransaction(
                    studentRepository.findById(studentId),
                    subjectRepository.findById(subjectId),
                    markCount
            );
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
