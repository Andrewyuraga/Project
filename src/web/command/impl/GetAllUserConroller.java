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
import java.util.List;

/**
 * Class GetAllUserConroller
 * <p>
 * Created by Yuraga
 */

public class GetAllUserConroller implements Controller {
    UserService userService = UserServiceIplm.getInstance();
    private Logger logger = Logger.getLogger(GetAllUserConroller.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            List<User> users = userService.getAll();
            req.setAttribute("users", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
