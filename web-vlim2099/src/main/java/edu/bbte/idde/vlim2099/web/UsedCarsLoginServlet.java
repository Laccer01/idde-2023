package edu.bbte.idde.vlim2099.web;

import com.github.jknack.handlebars.Template;
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

//bejelentkezéshez szükséges paraméterek
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Request arrived to UsedCarsLogin servlet");

        HttpSession session;
        session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
        }

        String usernameParameter = req.getParameter("username");
        String passwordParameter = req.getParameter("password");

        //ha a felhasználónév jelszó páros megegyezik akkor átirányit a weboldalra ahol az autok vannak felsorolva
        if (Objects.equals(getInitParameter("username"), usernameParameter)
                && Objects.equals(getInitParameter("password"), passwordParameter)) {
            session.setAttribute("loggedIn", "true");
            resp.sendRedirect("/usedCars-web/usedCarsPage");
        } else {
            //ellenkező esetben átírányit a loginre hibaüzenettel
            Map<String, Object> model = new ConcurrentHashMap<>();
            model.put("error", true);
            Template template = HandlebarsTemplateFactory.getTemplate("login");
            template.apply(model, resp.getWriter());
        }
    }
}