package io.skylar.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

public class WebUtils {

    public static <T> T request2RegisterFormBean(HttpServletRequest request, Class<T> beanClass) {
        T bean = null;
        try {
            bean = beanClass.newInstance();
            Enumeration<String> parameterNames = request.getParameterNames();

            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);

                System.out.println("name:"+name+"  value:"+value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  bean;
    }


    public static void copyBean(Object dest, Object orig) {
        ConvertUtils.register(new Converter() {

            //注意参数register的两个参数
            @Override
            public <T> T convert(Class<T> type, Object value) {
                if (value == null) {

                    return null;
                }
                if (value.toString().trim().equals("")) {
                    return null;
                }
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    return (T) simpleDateFormat.parse(String.valueOf(value));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }, Date.class);


        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
