package com.example.lab4.servlet;

import com.example.lab4.entity.Mark;
import com.example.lab4.repository.MarkRepository;
import com.example.lab4.utils.XssProtection;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MarkServlet", value = "/marks")
public class MarkServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (XssProtection.containsHtmlTags(request.getParameter("studentId")) || XssProtection.containsHtmlTags(request.getParameter("subjectId"))) {
            XssProtection.displayErrorPage(response);
            return;
        }
        Long studentId = Long.valueOf(request.getParameter("studentId"));
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        List<Mark> marks = markRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        request.setAttribute("marks", marks);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mark.jsp");
        dispatcher.forward(request, response);
    }

}
