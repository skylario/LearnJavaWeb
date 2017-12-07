package io.skylar.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TokenFormSubmitServlet", urlPatterns = "/TokenFormSubmitServlet")
public class TokenFormSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!isToken(request)) {
            System.out.println("重复提交，拒绝请求");
            return;
        }
        System.out.println("第一次提交表单。");
        //需将令牌属性删除
        request.getSession().removeAttribute("token");

    }

    private boolean isToken(HttpServletRequest request) {
        String token = request.getParameter("token");

        if (null == token) {
            return false;
        }
        //判断token存在否，
        //注意，属性存储的是对象
        String realToken = (String) request.getSession(false).getAttribute("token");
        if (realToken == null) {
            return false;
        }
        if (realToken.equals(token)) {
            return true;
        } else {
            return false;
        }
    }
}
