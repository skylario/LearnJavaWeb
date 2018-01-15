package io.login.test;

import io.login.dao.UserDao;
import io.login.dao.impl.UserDaoImpl;
import io.login.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UserDaoTest {
    @Test
    public void TestAdd() {
        UserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setUserName("test");
        user.setBirthday(new Date());
        user.setEmail("bb@sina.com");
        user.setId("3424234234");
        user.setNickName("李子");
        user.setPassword("123");
        user.setUserName("bbbb");

        userDao.add(user);
    }

    @Test
    public void TestFind() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find("zhang","1");

    }
    @Test
    public void TestFindByUserName() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.find("zhang"));
    }
}
