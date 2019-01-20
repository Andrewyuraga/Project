package web.command.impl;

import services.PartsService;
import services.impl.PartsServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class ProductOilController
 * <p>
 * Created by Yuraga
 */

public class ProductOilController implements Controller {
    private PartsService partsService = PartsServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("parts", partsService.getByCategory("Motor oil"));
            req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
