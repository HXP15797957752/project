package cn.jxau.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxau.entity.Subject;
import cn.jxau.service.SubjectService;
import cn.jxau.service.impl.SubjectServiceImpl;

public class VoteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1 
			int id = Integer.parseInt(request.getParameter("id"));
			//2 
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject= subjectService.getSubject(id);
			//ת����reg.jsp
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/pages/vote.jsp")
			       .forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
