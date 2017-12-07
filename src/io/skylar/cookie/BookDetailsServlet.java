package io.skylar.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

@WebServlet(name = "BookDetailsServlet", urlPatterns = "/BookDetailsServlet")
public class BookDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String bookId = request.getParameter("id");

        //显示商品的信息
        Book book = (Book) DB.getMap().get(bookId);

        writer.append("<h3>详细信息</h3>")
                .append("书名:").append(book.getName())
                .append("<br>作者:").append(book.getAuthor())
                .append("<br>");

        //获取cookie
        Cookie browseHistory = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length && cookies != null; i++) {
            if (cookies[i].getName().equals("BrowseHistory")) {
                browseHistory = cookies[i];
                break;
            }
        }
        if (browseHistory == null) {
            browseHistory = new Cookie("BrowseHistory", bookId);
        } else {
            String newHistory = makeCookie(browseHistory.getValue(), bookId);
            browseHistory = new Cookie("BrowseHistory", newHistory);
        }
        browseHistory.setMaxAge(15 * 24 * 60 * 60);
        response.addCookie(browseHistory);
    }

    /**
     * 生成浏览历史的cookie的值
     *
     * @param browseHistory
     * @param bookId
     * @return
     */
    private String makeCookie(String browseHistory, String bookId) {
        String[] history = browseHistory.split("\\_");
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(history));

        if (linkedList.contains(bookId)) {
            linkedList.remove(bookId);
        }
        linkedList.addFirst(bookId);

        if (linkedList.size() > 3) {
            linkedList.removeLast();
        }
        StringBuilder newHistory = new StringBuilder();
        for (String s : linkedList) {
            newHistory.append(s).append("_");
        }

        //此处长度未减1曾导致错误
        newHistory.deleteCharAt(newHistory.length()-1);
        return newHistory.toString();
    }

}
