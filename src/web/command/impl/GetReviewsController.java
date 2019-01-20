package web.command.impl;

import entities.Reviews;
import entities.User;
import org.apache.log4j.Logger;
import services.ReviewsService;
import services.impl.ReviewsServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class GetReviewsController
 * <p>
 * Created by Yuraga
 */

public class GetReviewsController implements Controller {
    private ReviewsService reviewsService = ReviewsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(GetReviewsController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<Reviews> reviews = reviewsService.getAll();
            req.setAttribute("reviews", reviews);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
