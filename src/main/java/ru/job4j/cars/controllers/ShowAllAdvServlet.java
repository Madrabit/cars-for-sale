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
 * @author madrabit on 07.08.2020
 * @version 1$
 * @since 0.1
 * Load all Advertisment to main page.
 */
public class ShowAllAdvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Advertisement> advertisementList = AdvertisementDaoHiber.instOf().findAll(Advertisement.class);
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, advertisementList);
        String advAsString = writer.toString();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        out.print(advAsString);
        out.flush();
    }
}
