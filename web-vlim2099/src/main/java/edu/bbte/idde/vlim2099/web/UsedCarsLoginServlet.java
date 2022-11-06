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
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "UsedCarLoginServlet",
        initParams = {
                @WebInitParam(name = "username", value = "admin"),
                @WebInitParam(name = "password", value = "admin")},
        urlPatterns = {"/usedCarsLogin"})
public class UsedCarsLoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarsServlet.class);

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarLoginServlet was initialized");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Request arrived to UsedCarsLogin servlet");

        boolean isLoggedInVar = true;

        String usernameParameter = req.getParameter("username");
        String passwordParameter = req.getParameter("password");

        HttpSession session;
        session = req.getSession(false);
        if (session==null)
            session = req.getSession(true);


        LOGGER.info("eredeti adatok {}, {}", getInitParameter("username"), getInitParameter("password"));
        LOGGER.info("mostani adatok {}, {}", usernameParameter, passwordParameter);
        if (Objects.equals(getInitParameter("username"), usernameParameter) && Objects.equals(getInitParameter("password"), passwordParameter))
        {
            session.setAttribute("loggedIn", "true");
            resp.sendRedirect("/usedCars-web/usedCarsPage");
        }
        else{

            resp.sendRedirect("/usedCars-web/login.html");
        }

    }
}