package edu.bbte.idde.vlim2099.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
import edu.bbte.idde.vlim2099.backend.dao.memory.UsedCarMemoryDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/usedCars")
public class UsedCarsServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarsServlet.class);
    private UsedCarDao usedCarDao;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarServlet was initialized");
        usedCarDao = new UsedCarMemoryDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Get method (UsedCarsServlet");
        
        String idParameter = req.getParameter("id");
        if (idParameter == null) {
            resp.setHeader("Content-Type", "application/json");
            objectMapper.writeValue(resp.getOutputStream(), usedCarDao.findAllUsedCar());
        } else {
            try {
                Long id = Long.parseLong(idParameter);
                UsedCar usedCar = usedCarDao.findById(id);
                if (usedCar == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("The car wasn't found");
                } else {
                    resp.setHeader("Content-Type", "application/json");
                    objectMapper.writeValue(resp.getOutputStream(), usedCar);
                }
            } catch(NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean goodInput = true;

        LOGGER.info("Post method (UsedCarsServlet");
        resp.setHeader("Content-Type", "application/json");

        try
        {
            UsedCar usedCar = objectMapper.readValue(req.getInputStream(), UsedCar.class);

            if (usedCar.getBrand() == "" || usedCar.getBrand()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("No brand name");
            }

            if (usedCar.getModel() == "" || usedCar.getModel()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("No model name");
            }

            if (usedCar.getEngineSize() < 0 || usedCar.getEngineSize()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Engine size was not correct, lower than 0");
            }

            if (usedCar.getHorsePower() <= 0 || usedCar.getHorsePower()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("The horse power was not correct, lower than 1");
            }

            if (usedCar.getNumberOfKm() < 0 || usedCar.getNumberOfKm()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("The number of km was not correct, lower than 0");
            }

            if (usedCar.getYearOfManufacture() < 1886 || usedCar.getYearOfManufacture()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("The number of km was not correct, lower than 1886");
            }

            if (usedCar.getChassisNumber() == "" || usedCar.getChassisNumber()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("The chassis number was not correct, it was empty");
            }

            if (usedCar.getPrice() < 0 || usedCar.getPrice()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("The price was not correct, it was lower than 0");
            }

            if (goodInput)
            {
                usedCarDao.createNewUsedCar(usedCar);
            }

        }
        catch(NumberFormatException exc) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid number");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Delete method (UsedCarsServlet");

        String idParameter = req.getParameter("id");
        if (idParameter == null) {
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println("Invalid ID");
        } else {
            try {
                Long id = Long.parseLong(idParameter);
                UsedCar usedCar = usedCarDao.findById(id);
                if (usedCar == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("The car wasn't found");
                } else {
                    usedCarDao.deleteUsedCar(id);
                    resp.setHeader("Content-Type", "application/json");
                    resp.getWriter().println("The car was deleted");
                }
            } catch(NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean goodInput = true;
        Long id = null;
        LOGGER.info("Put method (UsedCarsServlet");
        resp.setHeader("Content-Type", "application/json");

        UsedCar usedCar = objectMapper.readValue(req.getInputStream(), UsedCar.class);

        String idParameter = req.getParameter("id");

        if (usedCar.getBrand() == "" || usedCar.getBrand()==null)
            {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("No brand name");
        }

        if (usedCar.getModel() == "" || usedCar.getModel()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("No model name");
        }

        if (usedCar.getEngineSize() < 0 || usedCar.getEngineSize()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Engine size was not correct, lower than 0");
        }

        if (usedCar.getHorsePower() <= 0 || usedCar.getHorsePower()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The horse power was not correct, lower than 1");
        }

        if (usedCar.getNumberOfKm() < 0 || usedCar.getNumberOfKm()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The number of km was not correct, lower than 0");
        }

        if (usedCar.getYearOfManufacture() < 1886 || usedCar.getYearOfManufacture()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The number of km was not correct, lower than 1886");
        }

        if (usedCar.getChassisNumber() == "" || usedCar.getChassisNumber()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The chassis number was not correct, it was empty");
        }

        if (usedCar.getPrice() < 0 || usedCar.getPrice()==null)
        {
            goodInput = false;
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("The price was not correct, it was lower than 0");
        }

        if (idParameter == null) {
            goodInput = false;
            resp.getWriter().println("The id parameter was null");
        }
        else
        {
            try{
                id = Long.parseLong(idParameter);
                UsedCar idUsedCar = usedCarDao.findById(id);
                if (idUsedCar == null)
                {
                    goodInput = false;
                    resp.getWriter().println("Not found a car with the given id");
                }
            }

            catch(NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
            }
        }

        if (goodInput)
        {
            usedCarDao.updateUsedCar(usedCar, id);
        }
    }
}
