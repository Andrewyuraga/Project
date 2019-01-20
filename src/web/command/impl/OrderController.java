package web.command.impl;

import entities.Ord;
import entities.User;
import org.apache.log4j.Logger;
import services.OrdService;
import services.impl.OrdServiceIpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class OrderController
 * <p>
 * Created AYuraga
 */
public class OrderController implements Controller {
    private OrdService ordService = OrdServiceIpl.getInstance();
    private Logger logger = Logger.getLogger(OrderController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<Ord> orders = ordService.getByUserId(user.getId());

            req.setAttribute("orders", orders);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
