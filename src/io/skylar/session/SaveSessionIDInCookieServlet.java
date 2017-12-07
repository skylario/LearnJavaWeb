package io.skylar.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SaveSessionIDInCookieServlet", urlPatterns = {"/SaveSessionIDInCookieServlet", "/buyServlet"})
public class SaveSessionIDInCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("bookId");

        if (id == null) {
            System.out.println("id=null");
        }
        HttpSession session = request.getSession();
        List<String> list = (List) session.getAttribute("shoppingList");
        if (list == null) {
            list = new ArrayList();
        }
        list.add(id);
        session.setAttribute("shoppingList", list);

        //将Session的ID写入Cookie，其它事情无需处理，将由浏览器带着Cookie访问服务器
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);

        //对url编码，如果浏览器禁用cookie则使用URL的方式，若不用于重定向，则使用encodeURL ()
        String url = response.encodeRedirectURL("/LearnJavaWeb/ListCartServlet");
        response.sendRedirect(url);
    }
}
