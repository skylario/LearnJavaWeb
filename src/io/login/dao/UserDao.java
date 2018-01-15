package io.login.dao;

import io.login.domain.User;

public interface UserDao {
    //将用户添加到xml数据库中
    void add(User user);

    //根据用户名查找是否有该用户，
    boolean find(String userName);

    //校验用户名和密码，返回用户信息
    User find(String username, String password);
}
