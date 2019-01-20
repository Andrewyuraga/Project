package web.command.impl;

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

/**
 * Class CreateOrdConroller
 *
 * Created by Yuraga
 */

public class CreateOrdConroller implements Controller {
    private Logger logger = Logger.getLogger(CreateOrdConroller.class);
    private OrdService ordService = OrdServiceIpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            long userId = user.getId();
            String id = req.getParameter("id");
            Long parts_id = Long.parseLong(id);
            String parts_price = req.getParameter("price");
            Double price = Double.parseDouble(parts_price);
            String quantity_parts = req.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_parts);
            ordService.createOrder(userId, parts_id, quantity, price);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=orders");
        } catch (Exception e) {
            logger.error(e);
            resp.setHeader("errorMsg", "Invalid");
            req.setAttribute("errorMsg", "Invalid");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
