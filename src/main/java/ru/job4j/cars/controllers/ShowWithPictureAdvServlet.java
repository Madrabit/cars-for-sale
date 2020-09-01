package ru.job4j.cars.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cars.dao.impl.AdvertisementDaoHiber;
import ru.job4j.cars.model.Advertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author madrabit on 28.08.2020
 * @version ${Id}$
 * @since 0.1
 */
public class ShowWithPictureAdvServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Advertisement> advertisementList = AdvertisementDaoHiber.instOf().findWithPicture(Advertisement.class);
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, advertisementList == null ? "" : advertisementList);
        String advAsString = writer.toString();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        out.print(advAsString);
        out.flush();
    }
}
