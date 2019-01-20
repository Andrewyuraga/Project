package web.command.impl;

import org.apache.log4j.Logger;
import services.ReviewsService;
import services.impl.ReviewsServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteReviewController
 * <p>
 * Created by Yuraga
 */

public class DeleteReviewController implements Controller {
    private ReviewsService reviewsService = ReviewsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(DeleteReviewController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String reviewsId = req.getParameter("id");
            long id = Long.parseLong(reviewsId);
            reviewsService.delete(id);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=reviews");
        } catch (Exception e) {
            logger.error(e);
            resp.setHeader("errorMsg", "Invalid delete");
            req.setAttribute("errorMsg", "Invalid delete");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }

    }
}
