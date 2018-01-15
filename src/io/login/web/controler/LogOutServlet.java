package io.login.web.controler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户注销登录
 */
@WebServlet(name = "LogOutServlet", urlPatterns = "/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //将user从cession中移除，先跳转到message页面，再定时跳转到index.jsp
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        //精髓！！！
        request.setAttribute("message", "注销成功，3秒后将跳转到登录页。 <meta http-equiv='refresh' content-type='3;url='" + getServletContext().getContextPath() + "/logInIndex.jsp' />");
        request.getRequestDispatcher(getServletContext().getContextPath()+"/WEB-INF/jsp/login/message.jsp").forward(request, response);
    }
}
