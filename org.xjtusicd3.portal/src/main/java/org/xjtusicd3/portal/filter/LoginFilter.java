package org.xjtusicd3.portal.filter;

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

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {			
			
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
			HttpSession session = request.getSession();
			Object object = session.getAttribute("nameOrEmail");
			
			//没有登录就不允许访问页面的的链接
			if (object==null&&(url.contains("index")||url.contains("incidentindex")||url.contains("problemindex")||url.contains("cfgindex")||url.contains("knowledgeindex")||url.contains("spiderindex")||url.contains("logindex")||url.contains("userindex")||url.contains("messageindex")||url.contains("rbacindex"))) {
				response.sendRedirect(request.getContextPath() + "/login.html");
				return;
			} 
			chain.doFilter(servletRequest, servletResponse);
			
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	

}
