package BBS.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.service.UserService;

@WebServlet(urlPatterns= { "/managementUser"})
public class ManagementUserServlet extends UserServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.setAttribute("users", new UserService().getUsers());
		request.getRequestDispatcher("managementUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		UserService userService = new UserService();

		userService.managementUser(userService.getUser(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("users", userService.getUsers());
		request.getRequestDispatcher("managementUser.jsp").forward(request, response);
	}
}
