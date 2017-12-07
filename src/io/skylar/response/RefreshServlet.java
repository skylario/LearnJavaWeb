package io.skylar.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RefreshServlet",urlPatterns = "/RefreshServlet")
public class RefreshServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
//        response.setHeader("Expires", "-1");
//        response.setHeader("Cache-Control","no-cache");
//        response.setHeader("Pragma","no-cache");
        PrintWriter writer = response.getWriter();
        writer.write("<meta http-equiv='refresh' content='3;url=/LearnJavaWeb/RefreshServlet'>");
        writer.write("Hello World!");

    }
}
