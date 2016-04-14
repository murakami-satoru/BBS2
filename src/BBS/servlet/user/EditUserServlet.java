package BBS.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.service.UserService;

@WebServlet(urlPatterns= {"/editUser"})
public class EditUserServlet extends UserServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		session.setAttribute("user", new UserService().getUser(Integer.parseInt(request.getParameter("id"))));
		request.getRequestDispatcher("editUser.jsp").forward(request, response);

	}
}
