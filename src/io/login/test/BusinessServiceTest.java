package io.login.test;

import io.login.domain.User;
import io.login.exception.UserNameExistException;
import io.login.service.BusinessService;
import io.login.service.impl.BusinessServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class BusinessServiceTest {
    private User user;
    private BusinessService businessService;
    @Before
    public void init() {
        businessService = new BusinessServiceImpl();
    }

    public BusinessServiceTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static Collection initParams() {
        ArrayList<User> arrayList = new ArrayList();

        User user1 = new User();
        user1.setUserName("gggg");
        user1.setBirthday(new Date());
        user1.setEmail("bb@sina.com");
        user1.setId("3424234234");
        user1.setNickName("李子");
        user1.setPassword("123");

        User user2 = new User();
        user2.setUserName("gggg");
        user2.setBirthday(new Date());
        user2.setEmail("bb@sina.com");
        user2.setId("3424234234");
        user2.setNickName("李子");
        user2.setPassword("123");

        arrayList.add(user1);
        arrayList.add(user2);

        return arrayList;
    }
    @Test
    public void testLogIn(){
        businessService.logIn(user.getUserName(),user.getPassword());
    }
    @Test(expected = UserNameExistException.class)
    public void testRegister() throws UserNameExistException {
            businessService.register(user);
    }

}
