package edu.bbte.idde.vlim2099.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bbte.idde.vlim2099.backend.dao.DaoFactory;
import edu.bbte.idde.vlim2099.backend.dao.UsedCarOwnerDao;
import edu.bbte.idde.vlim2099.backend.model.UsedCarOwner;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/usedCarsOwner")
public class UsedCarsOwnerServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarsOwnerServlet.class);
    private UsedCarOwnerDao usedCarOwnerDao;
    private ObjectMapper objectMapper;

    boolean notNullParameters(UsedCarOwner usedCarOwner,
                      HttpServletResponse resp) throws IOException {
        if (usedCarOwner.getFirstName() == null || usedCarOwner.getLastName() == null
                || usedCarOwner.getBirthDay() == null || usedCarOwner.getGender() == null
                || usedCarOwner.getEmail() == null || usedCarOwner.getAddress() == null
                || usedCarOwner.getUsedCarId() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendError(400);
            resp.getWriter().println("Please fill all the properities of the car owner");
            return false;
        }
        return true;
    }

    boolean correctInput(UsedCarOwner usedCarOwner,
                          HttpServletResponse resp) throws IOException {

        boolean goodInput = true;
        if (notNullParameters(usedCarOwner,resp)) {

            if (!Objects.equals(usedCarOwner.getGender(), "man")
                    && !Objects.equals(usedCarOwner.getGender(), "woman")) {
                goodInput = false;
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(400);
                resp.getWriter().println("The gender was not correct, it has to be woman or man");
            }

            return goodInput;
        } else {
            return false;
        }
    }

    @Override
    public void init() throws ServletException {
        LOGGER.info("The UsedCarOwnerServlet was initialized");
        usedCarOwnerDao = DaoFactory.getInstance().getUsedCarOwnerDao();
        objectMapper = ObjectMapperFactory.getObjectMapper();
    }

    //ha volt megadva id vissza adja az adott id-jű autó tulajdonost, ha létezik
    //ha nem volt megadva id paraméter akkor visszatéríti az összes autó tulajdonost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Get method (UsedCarsOwnerServlet");

        String idParameter = req.getParameter("id");
        if (idParameter == null) {
            resp.setHeader("Content-Type", "application/json");
            objectMapper.writeValue(resp.getOutputStream(), usedCarOwnerDao.findAll());
        } else {
            try {
                Long id = Long.parseLong(idParameter);
                UsedCarOwner usedCarOwner = usedCarOwnerDao.findById(id);
                if (usedCarOwner == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println("The car wasn't found");
                    resp.sendError(404);
                } else {
                    resp.setHeader("Content-Type", "application/json");
                    objectMapper.writeValue(resp.getOutputStream(), usedCarOwner);
                }
            } catch (NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
                resp.sendError(400);
            }
        }
    }

    //beszúr egy auto tulajdonost, ha megfelel a feltételeknek
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LOGGER.info("Post method (UsedCarsOwnerServlet");
        resp.setHeader("Content-Type", "application/json");

        try {
            UsedCarOwner usedCarOwner = objectMapper.readValue(req.getInputStream(), UsedCarOwner.class);

            if (correctInput(usedCarOwner, resp)) {
                usedCarOwnerDao.create(usedCarOwner);
                resp.getWriter().println("The car owner was inserted");
            }

        } catch (IOException e) {
            LOGGER.info(String.valueOf(e));
            resp.getWriter().println(e);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendError(400);

            resp.getWriter().println("Invalid data");
        }
    }

    //kitöröl egy adott id paraméterű auto tulajdonost
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Delete method (UsedCarsOwnerServlet");

        String idParameter = req.getParameter("id");
        if (idParameter == null) {
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println("Invalid ID");
        } else {
            try {
                Long id = Long.parseLong(idParameter);
                UsedCarOwner usedCarOwner = usedCarOwnerDao.findById(id);
                if (usedCarOwner == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.sendError(400);
                    resp.getWriter().println("The car owner wasn't found");
                } else {
                    usedCarOwnerDao.delete(id);
                    resp.setHeader("Content-Type", "application/json");
                    resp.getWriter().println("The car owner was deleted");
                }
            } catch (NumberFormatException exc) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Invalid ID");
                resp.sendError(400);
            }
        }
    }

    //frissiti az adott id paraméterű auto tulajdonost ha megfelelőek a paraméterei
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        LOGGER.info("Put method (UsedCarsOwnerServlet");
        resp.setHeader("Content-Type", "application/json");

        UsedCarOwner usedCarOwner = objectMapper.readValue(req.getInputStream(), UsedCarOwner.class);

        if (correctInput(usedCarOwner, resp)) {
            String idParameter = req.getParameter("id");
            if (idParameter == null) {
                resp.getWriter().println("The id parameter was null");
                resp.sendError(400);
            } else {
                try {
                    id = Long.parseLong(idParameter);
                    UsedCarOwner idUsedCarOwner = usedCarOwnerDao.findById(id);
                    if (idUsedCarOwner == null) {
                        resp.getWriter().println("Not found a car owner with the given id");
                        resp.sendError(404);
                    } else {
                        usedCarOwnerDao.update(usedCarOwner, id);
                        resp.getWriter().println("The car owner was updated");
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
