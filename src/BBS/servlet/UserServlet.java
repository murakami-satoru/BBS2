package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Users;
import BBS.service.BranchesService;
import BBS.service.DepartmentsService;
import BBS.service.UserService;

@WebServlet(urlPatterns= { "/managementUser","/registerUser" })
public class UserServlet extends VaildatorServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String servletPath = (String) request.getServletPath();

		if(servletPath.equals("/managementUser")){
			request.getRequestDispatcher("managementUser.jsp").forward(request, response);

		}else if(servletPath.equals("/registerUser")){
			HttpSession session = request.getSession();
			session.setAttribute("branches", new BranchesService().getBranches());
			session.setAttribute("departments", new DepartmentsService().getDepartments());

			request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Users usersBean = new Users();

		usersBean.setLoginId(request.getParameter("login_id"));
		usersBean.setPassword(request.getParameter("password"));
		usersBean.setName(request.getParameter("name"));
		usersBean.setBranchId(Integer.parseInt(request.getParameter("branch")));
		usersBean.setDepartmentId(Integer.parseInt(request.getParameter("department")));
		new UserService().register(usersBean);

		request.getRequestDispatcher("managementUser.jsp").forward(request, response);

	}
}
