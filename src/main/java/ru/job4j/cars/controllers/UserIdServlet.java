package ru.job4j.cars.controllers;

import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author madrabit on 14.08.2020
 * @version ${Id}$
 * @since 0.1
 * Get user Id to compare with session user. And show or hidde status button.
 */
public class UserIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sc = request.getSession();
        User user = (User) sc.getAttribute("user");
        int id = CommonDaoHiber.instOf().findByEmail(user.getEmail()).getId();
        response.setContentType("text/plain;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(id);
    }
}
