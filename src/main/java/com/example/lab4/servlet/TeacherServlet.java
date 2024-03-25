package com.example.lab4.servlet;

import com.example.lab4.entity.Mark;
import com.example.lab4.entity.Student;
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
import java.util.List;

@WebServlet(name = "teacherServlet", value = "/teacher")
public class TeacherServlet extends HttpServlet {

    @EJB
    private MarkRepository markRepository;

    @EJB
    private StudentRepository studentRepository;

    @EJB
    private SubjectRepository subjectRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (XssProtection.containsHtmlTags(req.getParameter("mark")) || XssProtection.containsHtmlTags(req.getParameter("studentId"))
                || XssProtection.containsHtmlTags(req.getParameter("subjectId"))) {
            XssProtection.displayErrorPage(resp);
            return;
        }
        Integer mark = Integer.valueOf(req.getParameter("mark"));
        Long studentId = Long.valueOf(req.getParameter("studentId"));
        Long subjectId = Long.valueOf(req.getParameter("subjectId"));
        Boolean isPresent = req.getParameter("isPresent") != null;
        Mark newMark = getNewMark(mark, studentId, subjectId, isPresent);
        markRepository.save(newMark);
    }

    private Mark getNewMark(Integer mark, Long studentId, Long subjectId, Boolean isPresent) {
        Mark newMark = new Mark();

        newMark.setPoint(mark);
        newMark.setStudent(studentRepository.findById(studentId));
        newMark.setSubject(subjectRepository.findById(subjectId));
        newMark.setPresence(isPresent);

        return newMark;
    }

}