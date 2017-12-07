package io.skylar.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListCartServlet",urlPatterns = "/ListCartServlet")
public class ListCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.append("<h3>购物车的物品如下：</h3>")
                .append("<ul>");
        List<String> list = (List) request.getSession().getAttribute("shoppingList");

        Map<String,Book> map= DB.getMap();
        for (String s : list) {
            writer.append("<li>").append(map.get(s).getName()).append("</li>");
        }
        writer.append("</ul>");
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