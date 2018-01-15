package io.login.service;

import io.login.domain.User;
import io.login.exception.UserNameExistException;

public interface BusinessService {
    /**
     * 用户注册，
     * @param user
     * @throws UserNameExistException 用户名存在时抛出异常
     */
    void register(User user) throws UserNameExistException;

    /**
     * 用户登录，返回用户的登录信息
     * @param userName
     * @param password
     * @return
     */
    User logIn(String userName, String password);
}
