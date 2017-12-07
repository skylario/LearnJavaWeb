package io.skylar.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 记录用户的最近登录时间到cookie
 */
@WebServlet(name = "GreetServlet",urlPatterns = "/GreetServlet")
public class GreetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        //从cookie中读取上次登录时间
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; ++i) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("lastLogInTime")) {
                String s = cookie.getValue();
                long time = Long.parseLong(s);
                response.getWriter().print("上次登录时间：" + new Date(time).toLocaleString());
                break;
            }
        }

        //将当前时间以cookie的形式回写给客户机
        Cookie lastLogInTime = new Cookie("lastLogInTime", String.valueOf(System.currentTimeMillis()));
        lastLogInTime.setMaxAge(15*24*60*60);

        response.addCookie(lastLogInTime);

    }
}
