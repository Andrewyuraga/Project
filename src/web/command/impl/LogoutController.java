package web.command.impl;

import org.apache.log4j.Logger;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class LogoutController
 * <p>
 * Created by AYuraga
 */
public class LogoutController implements Controller {
    private Logger logger = Logger.getLogger(LogoutController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().invalidate();
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=email");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}

