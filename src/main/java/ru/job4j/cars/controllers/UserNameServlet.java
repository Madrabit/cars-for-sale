package ru.job4j.cars.controllers;

import ru.job4j.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author madrabit on 13.08.2020
 * @version 1$
 * @since 0.1
 * Get User name/email for showing in site header.
 */
public class UserNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        resp.setContentType("text/plain;charset=UTF-8");
        System.out.println(user.getPassword() + user.getId() + user.getEmail());
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.print(user.getEmail());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
