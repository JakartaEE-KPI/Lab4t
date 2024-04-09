package com.example.lab4.servlet;

import com.example.lab4.entity.Mark;
import com.example.lab4.repository.MarkRepository;
import com.example.lab4.repository.StudentRepository;
import com.example.lab4.repository.SubjectRepository;
import com.example.lab4.utils.XssProtection;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteMarkServlet", value = "/delete/mark")
public class DeleteMarkServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if (XssProtection.containsHtmlTags(req.getParameter("markId"))) {
            XssProtection.displayErrorPage(resp);
            return;
        }
        Long markId = Long.valueOf(req.getParameter("markId"));

        markRepository.deleteById(markId);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }
}
