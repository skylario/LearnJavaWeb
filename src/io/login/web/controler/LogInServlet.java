package io.login.web.controler;

import io.login.domain.User;
import io.login.service.BusinessService;
import io.login.service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogInServlet", urlPatterns = "/servlet/loginInServlet")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        BusinessService businessService = new BusinessServiceImpl();

        User user = businessService.logIn(userName, password);
        if (user == null) {
            //转发强求到message页面
            request.setAttribute("message", "登录失败");
            request.getRequestDispatcher("/WEB_INF/jsp/login/message.jsp").forward(request,response);
            return;
        }
        //重定向到网站首页
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(getServletContext().getContextPath() + "/WEB-INF/jsp/login/index.jsp");
    }
}
