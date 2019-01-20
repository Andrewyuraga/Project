package web.command.impl;

import org.apache.log4j.Logger;
import services.OrdService;
import services.impl.OrdServiceIpl;
import web.command.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteOrdController
 * <p>
 * Created by Yuraga
 */

public class DeleteOrdController implements Controller {
    private OrdService ordService = OrdServiceIpl.getInstance();
    private Logger logger = Logger.getLogger(DeleteOrdController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("deleteOrd");
            long ordId = Long.parseLong(id);
            ordService.delete(ordId);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=orders");
        } catch (IOException e) {
            logger.error(e);
        }

    }
}
