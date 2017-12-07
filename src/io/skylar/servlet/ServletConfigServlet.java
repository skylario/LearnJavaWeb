package io.skylar.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 遍历并输出Servlet的初始化参数,包括注解配置和配置符配置
 */
@WebServlet(name = "ServletConfigServlet", urlPatterns = "/ServletConfigServlet",initParams = {@WebInitParam(name = "para1", value = "value1"), @WebInitParam(name = "para2", value = "2"), @WebInitParam(name = "para3", value = "value3")})
public class ServletConfigServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //遍历并输出Servlet的初始化参数
        for (Enumeration e = servletConfig.getInitParameterNames(); e.hasMoreElements();) {
            String name = (String) e.nextElement();
            String value = servletConfig.getInitParameter(name);
            System.out.println("name=" + name + ",value=" + value);
        }

    }
}
