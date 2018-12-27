package votesystem;

import org.junit.Test;

import cn.jxau.entity.User;
import cn.jxau.service.UserService;
import cn.jxau.service.impl.UserServiceImpl;

public class UserServiceTest {
    @Test
    public void registerTest(){
        User user = new User();
        user.setName("hxp");
        user.setPwd("123456");
        user.setConfirmPwd("123456");
        UserService userService = new UserServiceImpl();
        try {
            userService.register(user);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
