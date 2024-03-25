package com.example.lab4.auth;

import com.example.lab4.utils.XssProtection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (XssProtection.containsHtmlTags(username) || XssProtection.containsHtmlTags(password)) {
            XssProtection.displayErrorPage(response);
        }
        if (username.equals("student") && password.equals("1234")
                || username.equals("teacher") && password.equals("4311")) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogin", username);
            session.setAttribute("userType", username);
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
