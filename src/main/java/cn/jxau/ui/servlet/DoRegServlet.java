package cn.jxau.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.jxau.core.exception.ReTryException;
import cn.jxau.entity.User;
import cn.jxau.service.UserService;
import cn.jxau.service.impl.UserServiceImpl;

public class DoRegServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ������ύ�����ݣ��˺ţ������ȷ������
		String name=request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
				
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);			
		
		try {			
			//2 ����ҵ���߼���Ķ��󣬵���ҵ�񷽷�����
			UserService userService = new UserServiceImpl();
			userService.register(user);
			
			//3 TODO �ض��򵽵�¼ҳ�棬��ʱ��ʾ���
			//request.getRequestDispatcher("/WEB-INF/pages/success.jsp")
			//       .forward(request, response);
			response.sendRedirect(request.getContextPath()+"/login");
		} catch (ReTryException e) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/reg");
		}catch(Exception ex){
			throw new RuntimeException(ex);
			
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
