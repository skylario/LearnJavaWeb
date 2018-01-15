package io.skylar.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * 防止用户重复提交表单，生成令牌，写入form隐藏域,提交时与session中的token对比，防止重复提交。
 */
@WebServlet(name = "TokenFormServlet",urlPatterns = "/TokenFormServlet")
public class TokenFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String token = TokenProcessor.getInstance().generateToken();

        //将令牌添加到session和form表单
        request.getSession().setAttribute("token",token);
        writer.append("<form method=\"POST\" action=\"/LearnJavaWeb/TokenFormSubmitServlet\">")
                .append("<input name='token' type='hidden' value='" + token + "'/>")
                .append("<input name='username' type='text'/>")
                .append("<input value='提交' type='submit'/>")
                .append("</form>");

        request.login("aa","aa");
    }


}
class TokenProcessor{
    //1. 把构造方法私有
    //2. 静态创建该类的对象
    //3. 定义一个方法返回上面的对象

    private  static final TokenProcessor instance = new TokenProcessor();
    private TokenProcessor() {
    }
    public static TokenProcessor getInstance(){
        return instance;
    }
    public String generateToken() {
        //生成消息
        String message =""+ System.currentTimeMillis() + new Random().nextInt(99999);

        //生成消息摘要
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] md5 = messageDigest.digest(message.getBytes());

            //防止生成乱码，故再采用base64编码
            Base64.Encoder encoder = Base64.getEncoder();
            return String.valueOf(encoder.encode(md5));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}