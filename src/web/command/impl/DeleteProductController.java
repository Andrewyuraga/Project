package web.command.impl;

import org.apache.log4j.Logger;
import services.PartsService;
import services.impl.PartsServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteProductController
 * <p>
 * Created by Yuraga
 */

public class DeleteProductController implements Controller {
    private PartsService partsService = PartsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(DeleteProductController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String id = req.getParameter("delete");
            long partId = Long.parseLong(id);
            partsService.delete(partId);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=parts");
        } catch (Exception e) {
            logger.error(e);
            resp.setHeader("errorMsg", "Invalid work");
            req.setAttribute("errorMsg", "Invalid work");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);

        }
    }
}
