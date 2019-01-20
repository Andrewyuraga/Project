package web.command.impl;

import org.apache.log4j.Logger;
import services.PartsService;
import services.impl.PartsServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by yslabko on 08/13/2017.
 */
public class ProductController implements Controller {
    private PartsService partsService = PartsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(ProductController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("parts", partsService.getAll());
            req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        } catch (SQLException e) {
            logger.error(e);
        }

    }
}

