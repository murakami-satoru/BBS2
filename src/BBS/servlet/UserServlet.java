package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Users;
import BBS.service.BranchesService;
import BBS.service.DepartmentsService;
import BBS.service.UserService;

@WebServlet(urlPatterns= { "/management","/regist" })
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String servletPath = (String) request.getServletPath();

		if(servletPath.equals("/management")){
			request.getRequestDispatcher("user/management.jsp").forward(request, response);
		}else if(servletPath.equals("/regist")){
			HttpSession session = request.getSession();
			session.setAttribute("branches", new BranchesService().getBranches());
			session.setAttribute("departments", new DepartmentsService().getDepartments());

			request.getRequestDispatcher("user/regist.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Users user = new Users();

		user.setLoginId(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("department")));

		UserService userService = new UserService();
		userService.regist(user);

		response.sendRedirect("home.jsp");

	}
}
