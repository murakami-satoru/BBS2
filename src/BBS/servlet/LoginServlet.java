package BBS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Users;
import BBS.service.UserService;

@WebServlet(urlPatterns= { "/login" })
public class LoginServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		UserService userService = new UserService();
		Users usersBean = userService.login(loginId, password);

		HttpSession session = request.getSession();
		if(usersBean != null){
			session.setAttribute("loginUser", usersBean);
			response.sendRedirect("home");
		}else{
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		}
	}

}
