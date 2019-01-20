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
 * Class RegistrationUserController
 * <p>
 * Created by Yuraga
 */

public class RegistrationUserController implements Controller {
    private UserService userService = UserServiceIplm.getInstance();
    private Logger logger = Logger.getLogger(RegistrationUserController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String address = req.getParameter("address");
            User user = new User(name, email, password, address);
            userService.save(user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=email");
        } catch (Exception e) {
            logger.error(e);
            resp.setHeader("errorMsg", "Invalid data");
            req.setAttribute("errorMsg", "Invalid data");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        }
    }
}
