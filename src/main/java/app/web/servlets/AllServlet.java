package app.web.servlets;

import app.domain.models.view.CarViewModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private final FileUtil fileUtil;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final String FILE_ALL_PATH = "G:\\JAVA EE\\01-javaee-intro\\src\\main\\webapp\\views\\all.html";

    @Inject
    public AllServlet(FileUtil fileUtil, CarService carService, ModelMapper modelMapper) {
        this.fileUtil = fileUtil;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(this.FILE_ALL_PATH);
        PrintWriter printWriter = resp.getWriter();
        StringBuilder sb = new StringBuilder(" ");
        List<CarViewModel> cars = this.carService.allCars().stream().map(carServiceModel -> this.modelMapper.map(carServiceModel, CarViewModel.class)).collect(Collectors.toList());
        for (CarViewModel car : cars) {
            sb.append("<li class =\"d-flex justify-content-around \">").append("\n")
                    .append("<div class=\"col-md-4 d-flex flex-column text-center mb-3\">").append("\n")
                    .append("<h2 class=\"text-white  text-center\">Brand:").append(car.getBrand()).append("</h2>").append("\n")
                    .append("<h4 class=\"text-white text-center\">Model:").append(car.getModel()).append("</h4>").append("\n")
                    .append("<h4 class=\"text-white text-center\">Year:").append(car.getYear()).append("</h4>").append("\n")
                    .append("<h4 class=\"text-white text-center\">Engine:").append(car.getEngine()).append("</h4>").append("\n")
                    .append("</div>").append("\n")
                    .append("</li>").append("\n");

        }

        html = html.replace("{{replace}}", sb.toString());

        printWriter.println(html);
    }
}
