package com.example.lab4.auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

@WebFilter(value = "/*", filterName = "authenticationFilter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("authenticationFilter: " + ((HttpServletRequest) request).getRequestURI());
        HttpSession session = httpRequest.getSession(false);
        boolean isLogin = false;

        if (session != null) {
            isLogin = session.getAttribute("isLogin") != null;
        }

        if (httpRequest.getRequestURI().contains("/login") && !isLogin) {
            chain.doFilter(request, response);
            return;
        }

        if (httpRequest.getRequestURI().contains("/login") && isLogin) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }

        if (isLogin) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/journal/login.jsp");
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
