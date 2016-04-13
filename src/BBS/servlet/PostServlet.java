package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Posts;
import BBS.beans.Users;
import BBS.service.PostService;

@WebServlet(urlPatterns= { "/registerPost" })
public class PostServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("registerPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Posts postsBean = new Posts();

		postsBean.setTitle(request.getParameter("title"));
		postsBean.setText(request.getParameter("mainText"));
		postsBean.setCategory(request.getParameter("category"));
		Users userBean = (Users) session.getAttribute("loginUser");
		postsBean.setUserId(userBean.getId());

		PostService postService = new PostService();
		postService.register(postsBean);
		response.sendRedirect("home");
	}
}
