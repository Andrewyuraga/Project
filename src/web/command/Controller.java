package web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by AYuraga
 */
public interface Controller {
    String MAIN_PAGE = "/WEB-INF/view/layouts/Test.jspx";

    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
