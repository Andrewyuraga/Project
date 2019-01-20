package web.command.impl;

import entities.Ord;
import org.apache.log4j.Logger;
import services.OrdService;
import services.impl.OrdServiceIpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class GetAllOrd
 * <p>
 * Created by Yuraga
 */

public class GetAllOrd implements Controller {
    private OrdService ordService = OrdServiceIpl.getInstance();
    private Logger logger = Logger.getLogger(GetAllOrd.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            List<Ord> ords = ordService.getAll();
            req.setAttribute("ords", ords);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
