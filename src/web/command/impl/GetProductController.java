package web.command.impl;

import entities.Parts;
import org.apache.log4j.Logger;
import services.PartsService;
import services.impl.PartsServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class GetProductController
 * <p>
 * Created by Yuraga
 */

public class GetProductController implements Controller {
    PartsService partsService = PartsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(GetProductController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            List<Parts> parts = partsService.getAll();
            req.setAttribute("parts", parts);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            logger.error(e);
        }

    }
}
