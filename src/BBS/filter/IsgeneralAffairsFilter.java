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

import BBS.beans.Users;

@WebFilter(urlPatterns={"/*"})
public class IsgeneralAffairsFilter implements Filter{
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String servletpath = ((HttpServletRequest) request).getServletPath();

		if(servletpath.indexOf("User") != -1 ){
			Users usersBean = (Users) session.getAttribute("loginUser");
			int departmentId = usersBean.getDepartmentId();
			int branchId = usersBean.getBranchId();
			//本社総務部以外がユーザー管理系の画面に遷移した場合にはじく
			if(departmentId != 3 && branchId != 1){
				List<String> messages = new ArrayList<String>();
				messages.add("本社総務部以外は操作できません");
				session.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("home.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
