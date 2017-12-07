package io.skylar.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "ShoppingIndexServlet", urlPatterns = "/ShoppingIndexServlet")
public class ShoppingIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        Map<String, Book> map = DB.getMap();

//        request.getSession();
        writer.append("<h3>本站有如下书籍:</h3><br/>")
                .append("<ul>");
        for (Map.Entry<String, Book> entry : map.entrySet()) {
            writer.append("<li>")
                    .append(entry.getValue().getName())
                    .append("<a href='").append("/LearnJavaWeb/buyServlet?bookId=")
                    .append(entry.getValue().getId()).append("'>加入购物车</a>");
        }
        writer.append("</ul>");
    }

}
