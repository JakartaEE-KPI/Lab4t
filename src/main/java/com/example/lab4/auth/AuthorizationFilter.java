package com.example.lab4.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.util.List;

@WebFilter(value = "/*", filterName = "authorizationFilter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorizationFilter implements Filter {

    List<String> teacherEndpoints = List.of(
            "/journal/home.jsp",
            "/journal/marks/add",
            "/journal/marks/update",
            "/journal/marks/delete",
            "/journal/transaction"
    );

    List<String> studentEndpoints = List.of(
            "/journal/home.jsp",
            "/journal/permission.jsp",
            "/journal/marks"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("authorizationFilter: " + ((HttpServletRequest) request).getRequestURI());
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.contains("/login") || requestURI.contains("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        String isLogin = String.valueOf(session.getAttribute("isLogin"));

        if (isLogin.equals("teacher") && (teacherEndpoints.contains(requestURI)
                || studentEndpoints.contains(requestURI))) {
            chain.doFilter(request, response);
            return;
        }
        if (isLogin.equals("student") && studentEndpoints.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        if (isLogin.equals("student") && teacherEndpoints.contains(requestURI)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/permission.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
