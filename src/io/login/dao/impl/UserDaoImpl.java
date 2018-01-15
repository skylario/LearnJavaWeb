package io.login.dao.impl;

import io.login.domain.User;
import io.skylar.utils.XMLUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDaoImpl implements io.login.dao.UserDao {
    //将用户添加到xml数据库中
    @Override
    public void add(User user) {
        try {
            Document document = XMLUtils.getDocument();
            Element root = document.getRootElement();
            root.addElement("user")
                    .addAttribute("id", user.getId())
                    .addAttribute("username", user.getUserName())
                    .addAttribute("password", user.getPassword())
                    .addAttribute("nickname", user.getNickName())
                    .addAttribute("email", user.getEmail())

                    //Date若为null，记为空串
                    .addAttribute("birthday", user.getBirthday() == null ? "" : user.getBirthday().toLocaleString());
            XMLUtils.write2Xml(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //查找是否有该用户
    @Override
    public boolean find(String userName) {
        try {
            Document document = XMLUtils.getDocument();
            Node node =  document.selectSingleNode("//user[@username='" + userName + "']");
            if (node != null) {
                return true;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    //
    @Override
    public User find(String username, String password) {
        User user = null;
        try {
            Document document = XMLUtils.getDocument();
            //这里同时校验了密码
            //属性值需要用用引号括起来
            Element e = (Element) document.selectSingleNode("//user[@username='" + username + "' and @password='" + password + "']");

            if (e != null) {
                user = new User();
                user.setId(e.attribute("id").getValue());
                user.setUserName(e.attribute("username").getValue());
                user.setEmail(e.attribute("email").getValue());
                user.setNickName(e.attribute("nickname").getValue());
                String d = e.attribute("birthday").getValue();
                if (d.equals("")) {
                    user.setBirthday(null);
                } else {
                    Date date = new SimpleDateFormat("yyyy-mm-dd").parse(d);
                    user.setBirthday(date);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }
}
