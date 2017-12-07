package io.skylar.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置浏览器的缓存
 */
@WebServlet(name = "SetCheServlet",urlPatterns = "/SetCheServlet")
public class SetCheServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long l = System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000;

        //error this.getServletContext()
        response.setHeader("expire", String.valueOf(l));

        String data = "this is data value writen in cache.";
        response.getWriter().write(data);
    }
}
