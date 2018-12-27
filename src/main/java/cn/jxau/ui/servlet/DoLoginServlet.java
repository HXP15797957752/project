package cn.jxau.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.jxau.core.exception.ReTryException;
import cn.jxau.entity.User;
import cn.jxau.service.UserService;
import cn.jxau.service.impl.UserServiceImpl;

public class DoLoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ������ύ�����ݣ��û���������ͼ�ס��
		String name=request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String remember=request.getParameter("remember");
		
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		
		HttpSession session = request.getSession();
		try{
			//2 ����ҵ���߼�����󣬵���ҵ�񷽷���ɵ�¼
			UserService userService = new UserServiceImpl();
			user = userService.login(user);
			//3 �ν�JSP
			//3.1 ��session�м�¼�û�����
			session.setAttribute(User.SESSIONNAME, user);
			//3.2 ����Ƿ�ѡ�˼�ס�ң��ǣ����û���ż�¼��cookie��
			if(remember!=null){
				Cookie cookie=new Cookie(User.SESSIONNAME,user.getId().toString());
				cookie.setMaxAge(10*24*60*60);
				response.addCookie(cookie);
			}
			//3.3 �ض���ͶƱ��Ŀ�б�ҳ��
			response.sendRedirect(request.getContextPath()+"/list");
		}
		catch(ReTryException re){
			//���ݻ���			
			session.setAttribute("user", user);
			session.setAttribute("message", re.getMessage());
			response.sendRedirect(request.getContextPath()+"/login");
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
