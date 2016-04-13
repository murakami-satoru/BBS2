package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Comments;
import BBS.beans.Users;
import BBS.service.CommentService;

@WebServlet(urlPatterns= { "/registerComment" })
public class CommentServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Comments commentsBean = new Comments();

		commentsBean.setText(request.getParameter("mainText"));
		commentsBean.setPostId(Integer.parseInt(request.getParameter("post_id")));
		Users userBean = (Users) session.getAttribute("loginUser");
		commentsBean.setUserId(userBean.getId());

		CommentService commentService = new CommentService();
		commentService.register(commentsBean);
		response.sendRedirect("home");
	}
}
