package io.skylar.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * 使用ServletContext读取文件的几种方式
 */
@WebServlet(name = "ReadFileServlet", urlPatterns = {"/ReadFileServlet", "/ReadFileServlet1"})
public class ReadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        read1();

        read2();

        read3();
    }

    /**
     * 使用<code>ServletContext().getResourceAsStream</code>进行文件读取，传统的方式不可取，
     */
    private void read1() throws IOException {
        InputStream inputStream = this.getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties");
        if (inputStream != null) {
            Properties config = new Properties();
            config.load(inputStream);
            String database_url = config.getProperty("database_url");
            System.out.println("databaseurl= " + database_url);
        }
    }

    /**使用<code>FileInputStream(ServletContext().getRealPath)</code>读取文件
     *此方法可以获得文件的路径、名称
     */
    private void read2() throws IOException {
        String realPath = this.getServletContext().getRealPath("/WEB-INF/classes/config.properties");

        //加一
        String filename = realPath.substring(realPath.lastIndexOf('/'+1));
        System.out.println("---------jinsheng.com---------filename值=" + filename + ",当前类=ReadFileServlet.read2()");

        //文件输入流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        Properties config = new Properties();
        config.load(fileInputStream);

        String database_url = config.getProperty("database_url");
        System.out.println("---------jinsheng.com---------database_url值=" + database_url + ",当前类=ReadFileServlet.read2()");

    }

    private void read3() throws IOException {
        URL url = this.getServletContext().getResource("/WEB-INF/classes/config.properties");
        InputStream inputStream = url.openStream();
        //...
    }
}
