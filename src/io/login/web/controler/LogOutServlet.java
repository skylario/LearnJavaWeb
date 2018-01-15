package io.login.web.controler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * �û�ע����¼
 */
@WebServlet(name = "LogOutServlet", urlPatterns = "/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //��user��cession���Ƴ�������ת��messageҳ�棬�ٶ�ʱ��ת��index.jsp
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }
        //���裡����
        request.setAttribute("message", "ע���ɹ���3�����ת����¼ҳ�� <meta http-equiv='refresh' content-type='3;url='" + getServletContext().getContextPath() + "/logInIndex.jsp' />");
        request.getRequestDispatcher(getServletContext().getContextPath()+"/WEB-INF/jsp/login/message.jsp").forward(request, response);
    }
}
