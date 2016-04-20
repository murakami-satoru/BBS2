package BBS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.service.CountService;
import BBS.service.PostService;

@WebServlet(urlPatterns= { "/home","/updatePostsByBranch","/updatePostsByUser","/updateCommentsByBranch","/updatePostsByBranch" })
public class HomeServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		PostService postService = new PostService();
		CountService countService = new CountService();

		request.setAttribute("posts", postService.getPosts());
		request.setAttribute("categories", postService.getCategories());
		request.setAttribute("post_by_branch", countService.getCountPostByBranch());
		request.setAttribute("post_by_user", countService.getCountPostByUser());
		request.setAttribute("comment_by_branch", countService.getCountCommentByBranch());
		request.setAttribute("comment_by_user", countService.getCountCommentByUser());
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		String servletPath = request.getServletPath();
		int targetId = Integer.parseInt(request.getParameter("target_id"));

		if(servletPath.indexOf("Posts") != -1){

		}else if(servletPath.indexOf("Comments") != -1){

		}
	}
}
