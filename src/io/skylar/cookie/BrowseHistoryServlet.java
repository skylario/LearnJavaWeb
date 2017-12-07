package io.skylar.cookie;

import sun.plugin2.message.CookieOpMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 记录用户的浏览历史
 */
@WebServlet(name = "BrowseHistoryServlet",urlPatterns = "/BrowseHistoryServlet")
public class BrowseHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");

        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        PrintWriter writer = response.getWriter();
        //显示商品列表
        Map<String, Book> bookMap = DB.getMap();
        writer.print("<h3>商品列表如下：<h3><br>");
        writer.print("<ul>");
        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
            writer.append("<li>")
                    .append("<a href=\"").append("/LearnJavaWeb/BookDetailsServlet?id=").append(entry.getKey()).append("\">").append(entry.getValue().getName()).append("</a>").append("     author:").append(entry.getValue().getAuthor())
                    .append("</li><br/>");
        }
        writer.print("</ul>");

        //显示浏览历史
        writer.print("<h4>浏览历史:</h4>");
        writer.print("<ul>");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length && i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("BrowseHistory")) {
                String[] bookIds =cookie.getValue().split("\\_");
                for (int j = 0; j < bookIds.length && bookIds != null; j++) {
                    Book book = (Book) DB.getMap().get(bookIds[j]);
                    writer.append("<li>").append(book.getName()).append("</li>");
                }
                break;
            }
        }
        writer.print("</ul>");

    }


}

class Book {
    private String id;
    private String name;
    private String author;

    public Book() {
        super();
    }

    public Book(String id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

class DB {
    private static Map<String, Book> db = new HashMap<>();

    static {
        db.put("1", new Book("1", "java编程思想", "Eckel"));
        db.put("2", new Book("2", "jdbc开发", "老黎"));
        db.put("3", new Book("3", "struts2开发", "老张"));
        db.put("4", new Book("4", "spring开发", "老黎"));
        db.put("5", new Book("5", "hibernate开发", "老张"));
    }

    static Map getMap() {
        return db;
    }
}