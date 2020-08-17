package ru.job4j.cars.controllers;

import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author madrabit on 10.07.2020
 * @version 1$
 * @since 0.1
 * Sign up user.
 */
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        CommonDaoHiber.instOf().addUser(
                User.of(req.getParameter("email"),
                        req.getParameter("password"))
        );
        HttpSession sc = req.getSession();
        User user = new User();
        user.setEmail(req.getParameter("email"));
        sc.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
