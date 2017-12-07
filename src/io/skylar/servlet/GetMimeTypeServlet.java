package io.skylar.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取文件Mime类型
 */
@WebServlet(name = "GetMimeTypeServlet",urlPatterns = "/GetMimeTypeServlet")
public class GetMimeTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = new String("picture.jpeg");
        System.out.println(this.getServletConfig().getServletContext().getMimeType(filename));
        response.setHeader("content-type","image/jpeg");
    }
}
