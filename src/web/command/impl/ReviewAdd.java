package web.command.impl;

import dao.impl.ReviewsDaoImpl;
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

/**
 * Class ReviewAdd
 * <p>
 * Created by Yuraga
 */

public class ReviewAdd implements Controller {
    private ReviewsService reviewsService = ReviewsServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(ReviewAdd.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String userId = String.valueOf(user.getId());
            Long id = Long.parseLong(userId);
            String text = req.getParameter("text");

            reviewsService.createReview(id, text);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=reviews");
        } catch (IOException e) {
            resp.setHeader("errorMsg", "Invalid");
            req.setAttribute("errorMsg", "Invalid");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            logger.error(e);
        }
    }
}
