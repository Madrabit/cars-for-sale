package ru.job4j.cars.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author madrabit on 05.08.2020
 * @version 1$
 * @since 0.1
 */
public class CreatAdvertServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CreatAdvertServlet.class.getName());

    /**
     * Load All cars to main page.
     * @param req
     * @param resp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }


    /**
     * Creates new Car, Advertisement.
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //For TEST json
//        Engine engine = Engine.of(1.6, 200, "gas");
//        CommonDaoHiber.instOf().create(
//                Advertisement.of(Car.of("bmw", "X5", engine, "Автомат", "Внедорожник",
//                        "Передний", "Blue", 2015, 100_000, 150_000, "some_url.jpg"),
//                        User.of("test_user", "test_password")));


        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        File file;
        String brand = "";
        String model = "";
        String fuel = "";
        int horsepower = 0;
        double displacement = 0;
        String transmission = "";
        String body = "";
        String drive = "";
        int year = 0;
        int mileage = 0;
        int price = 0;
        String image = "";
        try {
            List<FileItem> items = upload.parseRequest(req);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    if ("model".equals(item.getFieldName())) {
                        fieldNotEmpty(item, resp);
                        model = item.getString("UTF-8");
                    } else if ("displacement".equals(item.getFieldName())) {
                        fieldNotEmpty(item, resp);
                        displacement = Double.parseDouble(item.getString());
                    } else if ("transmission".equals(item.getFieldName())) {
                        transmission = item.getString("UTF-8");
                    } else if ("body".equals(item.getFieldName())) {
                        body = item.getString("UTF-8");
                    } else if ("brand".equals(item.getFieldName())) {
                        brand = item.getString("UTF-8");
                    } else if ("drive".equals(item.getFieldName())) {
                        drive = item.getString("UTF-8");
                    } else if ("year".equals(item.getFieldName())) {
                        year = Integer.parseInt(item.getString());
                    } else if ("mileage".equals(item.getFieldName())) {
                        mileage = Integer.parseInt(item.getString());
                    } else if ("price".equals(item.getFieldName())) {
                        price = Integer.parseInt(item.getString());
                    } else if ("fuel".equals(item.getFieldName())) {
                        fuel = item.getString("UTF-8");
                    } else if ("horsepower".equals(item.getFieldName())) {
                        horsepower = Integer.parseInt(item.getString());
                    }
                } else if (!item.isFormField() && item.getSize() > 0) {
                    File folder = new File("images");
                    if (!folder.exists()) {
                        folder.mkdir();
                    }

                    file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    image = item.getName();
                }
            }
        } catch (FileUploadException e) {
            LOG.error(e.getMessage(), e);
        }
        Engine engine = Engine.of(displacement, horsepower, fuel);
        User user = (User) req.getSession().getAttribute("user");
        String email = user.getEmail();
        User currentUser = CommonDaoHiber.instOf().findByEmail(email);
        Car car = Car.of(brand, model, engine, transmission, body, drive, year, mileage, price, image);
        Advertisement advertisement = Advertisement.of(car, currentUser);
        CommonDaoHiber.instOf().create(advertisement);
    }

    private void fieldNotEmpty(FileItem item, HttpServletResponse resp) throws IOException {
        if (item == null || item.getString().isEmpty())
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

}
