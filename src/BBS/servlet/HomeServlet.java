package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.service.PostService;

@WebServlet(urlPatterns= { "/home" })
public class HomeServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getSession().setAttribute("posts", new PostService().getPosts());
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
