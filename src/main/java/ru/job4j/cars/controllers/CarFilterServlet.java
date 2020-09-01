package ru.job4j.cars.controllers;

import com.fasterxml.jackson.databind.JsonNode;
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
 * @author madrabit on 31.08.2020
 * @version ${Id}$
 * @since 0.1
 */
public class CarFilterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        JsonNode node = objectMapper.readTree(request.getReader());
        List<Advertisement> advertisementList  = null;
        if (node != null ) {
            String brand = node.get("brand").asText();
            if (!brand.isEmpty()) {
                advertisementList = AdvertisementDaoHiber.instOf().findCarByBrand(brand);
            }
        }
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, advertisementList == null ? "" : advertisementList);
        String advAsString = writer.toString();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(advAsString);
        out.flush();
    }


}
