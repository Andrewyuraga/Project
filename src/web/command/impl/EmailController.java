package web.command.impl;

import entities.User;
import org.apache.log4j.Logger;
import services.UserService;
import services.impl.UserServiceIplm;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yslabko on 08/13/2017.
 */
public class EmailController implements Controller {
    UserService userService = UserServiceIplm.getInstance();
    private Logger logger = Logger.getLogger(EmailController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            if (email == null || password == null) {
                resp.setHeader("errorMsg", "Invalid email or password");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            }
            User user = userService.getByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/frontController?command=orders");
            } else {
                resp.setHeader("errorMsg", "Invalid Email or Password");
                req.setAttribute("errorMsg", "Invalid Email or Password");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}