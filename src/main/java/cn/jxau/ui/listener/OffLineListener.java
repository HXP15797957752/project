package cn.jxau.ui.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.jxau.entity.User;
import cn.jxau.service.UserService;
import cn.jxau.service.impl.UserServiceImpl;

public class OffLineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		User user = (User)session.getAttribute(User.SESSIONNAME);
        //�޸�����״̬
		UserService userService = new UserServiceImpl();
		userService.online(user,false);
	}

}
