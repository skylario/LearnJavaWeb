package io.skylar.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "GetHeaderServlet",urlPatterns = "/GetHeaderServlet")
public class GetHeaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        for(Enumeration e = request.getHeaderNames(); e.hasMoreElements();){
            String name = (String) e.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "=" + value);
        }
    }
}
