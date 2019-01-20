package web.command.impl;

import entities.Parts;
import org.apache.log4j.Logger;
import services.PartsService;
import services.impl.PartsServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class PartsAddController
 * <p>
 * Created by Yuraga
 */

public class PartsAddController implements Controller {
    private PartsService partsService = PartsServiceImpl.getInstance();
    Logger logger = Logger.getLogger(PartsAddController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String producer = req.getParameter("producer");
            String category = req.getParameter("category");
            String img = req.getParameter("img");
            String name = req.getParameter("name");
            String chatacteristics = req.getParameter("chatacteristics");
            Double price = Double.parseDouble(req.getParameter("price"));

            Parts parts = new Parts(producer, category, img, name, chatacteristics, price);
            partsService.save(parts);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=parts");
        } catch (Exception e) {
            logger.error(e);
        }
    }

}