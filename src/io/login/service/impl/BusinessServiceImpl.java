package io.login.service.impl;


import io.login.dao.UserDao;
import io.login.dao.impl.UserDaoImpl;
import io.login.domain.User;
import io.login.exception.UserNameExistException;
import io.skylar.utils.ServiceUtils;

/**
 * service层，为web层提供服务
 */
public class BusinessServiceImpl implements io.login.service.BusinessService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 用户注册，
     * @param user
     * @throws UserNameExistException 用户名存在时抛出异常
     */
    @Override
    public void register(User user) throws UserNameExistException {
        String username = user.getUserName();
        if (userDao.find(username)) {
            throw new UserNameExistException();
        }
        //需要对密码进行加密
        user.setPassword(ServiceUtils.mod5(user.getPassword()).toString());

        userDao.add(user);
    }

    /**
     * 用户登录，返回用户的登录信息
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User logIn(String userName, String password) {

        return userDao.find(userName, ServiceUtils.mod5(password).toString());
    }
}
