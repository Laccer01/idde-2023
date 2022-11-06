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
        boolean isZero = usedCarDao.findAllUsedCar().size() == 0;

        model.put("usedCars", usedCarDao.findAllUsedCar());
        model.put("userCarsSize", isZero);

        Template template = HandlebarsTemplateFactory.getTemplate("index");
        template.apply(model, resp.getWriter());

    }
}