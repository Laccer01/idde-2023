package edu.bbte.idde.vlim2099.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter("/usedCarsPage")
public class SimpleFilter extends HttpFilter {

    Logger LOGGER = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            res.sendRedirect("/usedCars-web/login.html");
        } else if (session.getAttribute("loggedIn") == null || session.getAttribute("loggedIn") != "true") {
            LOGGER.info("minden okes {}", session.getAttribute("loggedIn"));
            res.sendRedirect("/usedCars-web/login.html");
        } else {
            chain.doFilter(req, res);
        }

        LOGGER.info("Method: {}, URL: {}, Status: {}", req.getMethod(), req.getRequestURI(), res.getStatus());
    }
}