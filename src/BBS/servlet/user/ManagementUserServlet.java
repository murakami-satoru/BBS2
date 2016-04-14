package BBS.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.service.UserService;

@WebServlet(urlPatterns= { "/managementUser"})
public class ManagementUserServlet extends UserServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		session.setAttribute("users", new UserService().getUsers());
		request.getRequestDispatcher("managementUser.jsp").forward(request, response);

	}
}
