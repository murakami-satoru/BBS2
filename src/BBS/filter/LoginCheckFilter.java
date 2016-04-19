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

@WebFilter(urlPatterns={"/*"})
public class LoginCheckFilter implements Filter{
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String servletpath = ((HttpServletRequest) request).getServletPath();

		System.out.println(servletpath);

		//ログイン画面以外の画面でログイン情報がなければホーム画面に戻す。
		if(servletpath.indexOf("login") != 1 ){
			if(session.getAttribute("loginUser") == null){
				List<String> messages = new ArrayList<String>();
				messages.add("ログインしてください");
				session.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
