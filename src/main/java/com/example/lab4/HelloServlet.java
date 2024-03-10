package com.example.lab4;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @EJB
    private JournalRepository journalRepository;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String textValue = request.getParameter("text");
        if (containsHtmlTags(textValue)) {
            displayErrorPage(response);
            return;
        }
        journalRepository.save(Mark.builder()
                .presence(true)
                .knowledgeLevel(14)
                .build());
    }

    public boolean containsHtmlTags(String text) {
        return text != null && !text.matches("^[^<>&]*$");
    }

    public void displayErrorPage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Помилка: Текст містить HTML-теги, що є небезпечними!</h1>");
        out.println("</body></html>");
        out.close();
    }

}