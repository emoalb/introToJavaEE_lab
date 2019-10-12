package app.web.servlets;

import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//Servlet means controller in the MVC world

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final FileUtil fileUtil;

    private final String FILE_ALL_PATH = "G:\\JAVA EE\\01-javaee-intro\\src\\main\\webapp\\views\\home.html";

    @Inject
    public HomeServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(this.FILE_ALL_PATH);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(html);
    }
}
