package io.login.web.controler;

import io.login.domain.User;
import io.login.exception.UserNameExistException;
import io.login.service.BusinessService;
import io.login.service.impl.BusinessServiceImpl;
import io.login.web.model.RegisterFormBean;
import io.skylar.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "RegisterServlet",urlPatterns = "/servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        RegisterFormBean registerFormBean = WebUtils.request2RegisterFormBean(request, RegisterFormBean.class);
        String checkCodeServer = (String) request.getSession().getAttribute("identifyCode");
        registerFormBean.setCheckCodeServer(checkCodeServer);
        boolean isValidate = registerFormBean.validate();

        if (isValidate) {
            //У��ɹ�
            BusinessService businessService = new BusinessServiceImpl();
            User user = new User();
            WebUtils.copyBean(user, registerFormBean);
            //�����û���UUID
            user.setId(WebUtils.generateUUID());
            try {
                businessService.register(user);
            } catch (UserNameExistException e) {
                registerFormBean.getErrors().put("username", "���û����Ѿ�ע��");
                request.setAttribute("form", registerFormBean);
                request.getRequestDispatcher("/WEB-INF/jsp/login/register.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message","����������Ϊ֪����");
                request.getRequestDispatcher("/WEB-INF/jsp/login/message.jsp").forward(request,response);
                return;
            }
            request.getSession().setAttribute("user", user);
            request.setAttribute("message","��ϲ��ע��ɹ���");
            request.getRequestDispatcher("/WEB-INF/jsp/login/message.jsp").forward(request,response);

        } else {

            //У��ʧ��,
            // �轫registerFromBean����
            request.setAttribute("form", registerFormBean);
            request.setAttribute("errors", registerFormBean.getErrors());
            request.getRequestDispatcher("/WEB-INF/jsp/login/register.jsp").forward(request, response);
        }
    }


}
