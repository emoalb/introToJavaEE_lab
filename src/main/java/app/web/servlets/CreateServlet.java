package app.web.servlets;

import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarServiceModel;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final CarService carService;

    private final String FILE_ALL_PATH ="G:\\JAVA EE\\01-javaee-intro\\src\\main\\webapp\\views\\create.html";

    @Inject
    public CreateServlet(FileUtil fileUtil, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(this.FILE_ALL_PATH);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarCreateBindingModel carCreateBindingModel = new CarCreateBindingModel();
        carCreateBindingModel.setBrand(req.getParameter("brand"));
        carCreateBindingModel.setModel(req.getParameter("model"));
        carCreateBindingModel.setYear(Integer.parseInt(req.getParameter("year")));
        carCreateBindingModel.setEngine(req.getParameter("engine"));
        this.carService.createCar(this.modelMapper.map(carCreateBindingModel, CarServiceModel.class));
        resp.sendRedirect("/all");
    }
}
