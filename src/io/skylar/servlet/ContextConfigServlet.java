package io.skylar.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextConfigServlet",urlPatterns = "/ContextConfigServlet")
public class ContextConfigServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext= this.getServletContext();

        //Attribute和InitParameter有所区别
        String value6 = (String) servletContext.getAttribute("param6");

        if (value6 != null) {
            System.out.println("---------jinsheng.com---------value6值="+value6+",当前类=ContextConfigServlet.doGet()");

        }

        String value7 = servletContext.getInitParameter("param7");
        if (value7 != null) {
            System.out.println("---------jinsheng.com---------value7值="+value7+",当前类=ContextConfigServlet.doGet()");

        }

    }
}
