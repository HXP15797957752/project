package cn.jxau.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.jxau.entity.User;

public class CheckLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ����ת��
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// ����Ƿ��Ѿ���¼�������û�е�¼��ǿ����ת���Զ���¼����
		HttpSession session = req.getSession();
		if (session.getAttribute(User.SESSIONNAME) == null) {
			res.sendRedirect(req.getContextPath()+"/login");
			return;
		}
		// ������
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
