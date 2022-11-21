package edu.bbte.idde.vlim2099.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.vlim2099.backend.dao.DaoFactory;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarDao;
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

    boolean notNullParameters(UsedCar usedCar,
                      HttpServletResponse resp) throws IOException {
        if (usedCar.getPrice() == null || usedCar.getYearOfManufacture() == null
                || usedCar.getNumberOfKm() == null || usedCar.getEngineSize() == null
                || usedCar.getBrand() == null || usedCar.getModel() == null
                || usedCar.getHorsePower() == null || usedCar.getChassisNumber() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendError(400);
            resp.getWriter().println("Please fill all the properities of the car");
            return false;
        }
        return true;
    }

    boolean correctInput(UsedCar usedCar,
                          HttpServletResponse resp) throws IOException {

        boolean goodInput = true;
        if (notNullParameters(usedCar,resp)) {
            if (usedCar.getEngineSize() < 0.0) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("Engine size was not correct, lower than 0");
            }

            if (usedCar.getHorsePower() <= 0) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("The horse power was not correct, lower than 1");
            }

            if (usedCar.getNumberOfKm() < 0.0) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("The number of km was not correct, lower than 0");
            }

            if (usedCar.getYearOfManufacture() < 1886) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("The number of km was not correct, lower than 1886");
            }

            if (usedCar.getPrice() < 0) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("The price was not correct, it was lower than 0");
            }
            return goodInput;
        } else {
            return false;
        }
    }

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarServlet was initialized");
        usedCarDao = DaoFactory.getInstance().getUsedCarDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
    }

    //ha volt megadva id vissza adja az adott id-jű autót, ha létezik
    //ha nem volt megadva id paraméter akkor visszatéríti az összes autót
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
                    resp.sendError(404);
                } else {
                    resp.setHeader("Content-Type", "application/json");
                    objectMapper.writeValue(resp.getOutputStream(), usedCar);
                }
            } catch (NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
                resp.sendError(400);
            }
        }
    }

    //beszúr egy autót, ha megfelel a feltételeknek
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOGGER.info("Post method (UsedCarsServlet");
        resp.setHeader("Content-Type", "application/json");

        try {
            UsedCar usedCar = objectMapper.readValue(req.getInputStream(), UsedCar.class);

            if (correctInput(usedCar, resp)) {
                usedCarDao.createNewUsedCar(usedCar);
                resp.getWriter().println("The car was inserted");
            }

        } catch (IOException e) {
            LOGGER.info(String.valueOf(e));
            resp.getWriter().println(e);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendError(400);

            resp.getWriter().println("Invalid data");
        }
    }

    //kitöröl egy adott id paraméterű autot
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
                    resp.sendError(400);
                    resp.getWriter().println("The car wasn't found");
                } else {
                    usedCarDao.deleteUsedCar(id);
                    resp.setHeader("Content-Type", "application/json");
                    resp.getWriter().println("The car was deleted");
                }
            } catch (NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
                resp.sendError(400);
            }
        }
    }

    //frissiti az adott id paraméterű autot ha megfelelőek a paraméterei
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        LOGGER.info("Put method (UsedCarsServlet");
        resp.setHeader("Content-Type", "application/json");

        UsedCar usedCar = objectMapper.readValue(req.getInputStream(), UsedCar.class);

        if (correctInput(usedCar, resp)) {
            String idParameter = req.getParameter("id");
            if (idParameter == null) {
                resp.getWriter().println("The id parameter was null");
                resp.sendError(400);
            } else {
                try {
                    id = Long.parseLong(idParameter);
                    UsedCar idUsedCar = usedCarDao.findById(id);
                    if (idUsedCar == null) {
                        resp.getWriter().println("Not found a car with the given id");
                        resp.sendError(404);
                    } else {
                        usedCarDao.updateUsedCar(usedCar, id);
                        resp.getWriter().println("The car was updated");
                    }
                } catch (NumberFormatException exc) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().println("Invalid ID");
                    resp.sendError(400);
                }
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Bad parameters");
            resp.sendError(400);
        }
    }
}
