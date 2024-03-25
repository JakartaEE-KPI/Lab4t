package com.example.lab4.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class XssProtection {

    public static boolean containsHtmlTags(String text) {
        return text != null && !text.matches("^[^<>&]*$");
    }

    public static void displayErrorPage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Warning: The text contains HTML tags that are dangerous!</h1>");
        out.println("</body></html>");
        out.close();
    }
}
