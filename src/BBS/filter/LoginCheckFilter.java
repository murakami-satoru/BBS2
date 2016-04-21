package BBS.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/home","/managementUser","/editUser","/registerPost","/registerUser","*.jsp"})
public class LoginCheckFilter implements Filter{
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String servletPath = ((HttpServletRequest) request).getServletPath();

		//ログインをせずに他画面が呼ばれればログイン画面に戻す。
		if(session.getAttribute("loginUser") == null && servletPath.indexOf("login") == -1){
			List<String> messages = new ArrayList<String>();
			messages.add("ログインしてください");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
