package io.skylar.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet(name = "DownloadFileServlet", urlPatterns = "/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        downloadFile(response);
    }

    private void downloadFile(HttpServletResponse response) throws IOException {
        //获取文件
        String filePath = this.getServletContext().getRealPath("/res/img/图片一.jpg");
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        System.out.println("---------jinsheng.com---------fileName值="+fileName+",当前类=DownloadFileServlet.downloadFile()");

        //设置响应头并编码文件，名，使其能够显示为中文
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));

        //输出
        InputStream inputStream = new FileInputStream(filePath);
        // error PrintWriter writer = response.getWriter();
        OutputStream out = response.getOutputStream();
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(buffer)) != -1) {
                out.write(buffer);
            }
        }

        if (inputStream != null) {
            inputStream.close();
        }
    }
}
