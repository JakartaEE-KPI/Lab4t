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

@WebServlet(name = "UpdateMarkServlet", value = "/marks/update")
public class UpdateMarkServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (XssProtection.containsHtmlTags(req.getParameter("markId")) || XssProtection.containsHtmlTags(req.getParameter("mark"))) {
            XssProtection.displayErrorPage(resp);
            return;
        }
        Long markId = Long.valueOf(req.getParameter("markId"));
        Integer point = Integer.valueOf(req.getParameter("mark"));
        Boolean isPresent = req.getParameter("isPresent") != null;
        Mark mark = markRepository.findById(markId);

        if (mark != null) {
            mark.setPoint(point);
            mark.setPresence(isPresent);
            markRepository.save(mark);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
