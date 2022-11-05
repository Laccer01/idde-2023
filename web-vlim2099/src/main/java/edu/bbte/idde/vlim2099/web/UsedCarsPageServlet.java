package edu.bbte.idde.vlim2099.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Template;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.memory.UsedCarMemoryDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/usedCarsPage")
public class UsedCarsPageServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarsServlet.class);
    private UsedCarDao usedCarDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarPageServlet was initialized");
        usedCarDao = new UsedCarMemoryDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Request arrived to example servlet");

        Map<String, Object> model = new ConcurrentHashMap<>();
        boolean isLoggedInVar = true;

        model.put("usedCars", usedCarDao.findAllUsedCar());
        model.put("isLoggedIn", isLoggedInVar);

        Template template = HandlebarsTemplateFactory.getTemplate("index");
        template.apply(model, resp.getWriter());
    }
}