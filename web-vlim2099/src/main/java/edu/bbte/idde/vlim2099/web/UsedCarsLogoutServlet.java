package edu.bbte.idde.vlim2099.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Template;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.memory.UsedCarMemoryDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/usedCarsLogout")
public class UsedCarsLogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarsServlet.class);

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarLogoutServlet was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Request arrived to UsedCarsLogout servlet");

        HttpSession session;
        session = req.getSession(false);
        if (session==null)
            session = req.getSession(true);

        session.invalidate();
        resp.sendRedirect("/usedCars-web/login.html");
    }
}