package web.command.impl;

import entities.User;
import org.apache.log4j.Logger;
import services.UserService;
import services.impl.UserServiceIplm;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class UpdateAddress
 * <p>
 * Created by Yuraga
 */

public class UpdateAddress implements Controller {
    private UserService userService = UserServiceIplm.getInstance();
    private Logger logger = Logger.getLogger(UpdateAddress.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            long userId = user.getId();
            String address = req.getParameter("address");
            userService.update(userId, address);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=parts");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
