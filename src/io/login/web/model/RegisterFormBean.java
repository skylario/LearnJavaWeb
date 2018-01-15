package io.login.web.model;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterFormBean {

    private String userName;
    private String password;
    private String password2;
    private String checkCode;
    private String checkCodeServer;

    private String nickName;
    private String birthday;
    private String email;

    public String getCheckCodeServer() {
        return checkCodeServer;
    }

    public void setCheckCodeServer(String checkCodeServer) {
        this.checkCodeServer = checkCodeServer;
    }

    public Map getErrors() {
        return errors;
    }

    public void setErrors(Map errors) {
        this.errors = errors;
    }

    private Map errors;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validate() {
        boolean isOK = true;
        errors = new HashMap();

        if (userName == null || userName.trim().equals("")) {
            isOK = false;
            errors.put("username", "用户名不能为空");
        } else {
            if (!userName.matches("[a-zA-Z]+")) {
                errors.put("username", "用户名只能用英文大小写拼写");
                isOK = false;
            }
        }

        //密码
        if (password == null || password.trim().equals("")||password2 == null || password2.trim().equals("")) {
            errors.put("password", "密码不能为空");
            isOK = false;
        } else {
            if (!password.matches("[a-zA-Z1-9.,;'\\[\\]]+")) {
                errors.put("password", "密码只能包含英文大小写、数字和， .;[]");
                isOK = false;
            }
            if (password.equals(password2)==false){
                errors.put("password", "密码不一致");
                isOK = false;
            }
        }

        //昵称
        if (nickName == null || nickName.trim().equals("")) {
            errors.put("nickName", "用户名不能为空");
            isOK = false;
        } else {
            if (!userName.matches("[a-zA-Z]+")) {
                errors.put("nickName", "用户名只能用英文大小写拼写");
                isOK = false;
            }
        }

        //邮箱
        if (email == null || email.trim().equals("")) {
            errors.put("email", "邮箱不能为空");
            isOK = false;
        } else {
            if (!email.matches("\\w+@(\\w+\\.)*\\w+")) {
                errors.put("email", "请填写正确的邮箱");
                isOK = false;
            }
        }

        //日期
        if (birthday != null) {
            try {
                DateLocaleConverter dateLocaleConverter = new DateLocaleConverter();
                dateLocaleConverter.convert(birthday, "yyyy-MM-dd");
            } catch (Exception ex) {
                errors.put("birthday", "日期的格式不对");
                isOK = false;
            }
        }

        //校验码
        if (checkCode != null && checkCode.equals(checkCodeServer)) {

        }else {
            errors.put("checkCode", "校验码错误");
            isOK = false;
        }
        System.out.println("checkCode:" + checkCode);
        System.out.println("checkCodeServer:" + checkCodeServer);

        return isOK;
    }
}
