package com.example.lab4.servlet;

import com.example.lab4.entity.Mark;
import com.example.lab4.repository.MarkRepository;
import com.example.lab4.repository.StudentRepository;
import com.example.lab4.repository.SubjectRepository;
import com.example.lab4.utils.XssProtection;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddMarkServlet", value = "/add/mark")
public class AddMarkServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @EJB
    private StudentRepository studentRepository;

    @EJB
    private SubjectRepository subjectRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("s");
        if (XssProtection.containsHtmlTags(req.getParameter("mark")) || XssProtection.containsHtmlTags(req.getParameter("studentId"))
                || XssProtection.containsHtmlTags(req.getParameter("subjectId"))) {
            XssProtection.displayErrorPage(resp);
            return;
        }
        Integer point = Integer.valueOf(req.getParameter("mark"));
        Long studentId = Long.valueOf(req.getParameter("studentId"));
        Long subjectId = Long.valueOf(req.getParameter("subjectId"));
        Boolean isPresent = req.getParameter("isPresent") != null;
        markRepository.save(Mark.builder()
                .point(point)
                .student(studentRepository.findById(studentId))
                .subject(subjectRepository.findById(subjectId))
                .presence(isPresent)
                .build());
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }
}