package ru.job4j.cars.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cars.dao.impl.AdvertisementDaoHiber;
import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.Advertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author madrabit on 10.08.2020
 * @version ${Id}$
 * @since 0.1
 */
public class CarDescriptionServlet extends HttpServlet {

    /**
     * Update Car status SOLD or NOT.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("ID");
        String completed = request.getParameter("completed");
        CommonDaoHiber.instOf().updateStatus(
                Integer.parseInt(id),
                Boolean.parseBoolean(completed)
        );
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    /**
     * Send Car description to view.
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        JsonNode node = objectMapper.readTree(request.getReader());
        Advertisement advertisement = null;
        if (node != null) {
            int id = node.get("id").asInt();
            if (id != 0) {
               advertisement = AdvertisementDaoHiber.instOf().findById(id);
            }
        }
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, advertisement);
        String advAsString = writer.toString();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(advAsString);
        out.flush();
    }

}
