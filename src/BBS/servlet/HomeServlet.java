package BBS.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.Posts;
import BBS.service.PostService;

@WebServlet(urlPatterns= { "/home","/selectPostsByBranch","/selectPostsByUser","/selectCommentsByBranch","/selectCommentsByUser" })
public class HomeServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		PostService postService = new PostService();
		request.setAttribute("posts", postService.getPosts());
		dispatcherHome(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{

		String servletPath = request.getServletPath();
		int targetId = Integer.parseInt(request.getParameter("target_id"));
		List<Posts> posts = null;

		PostService postService = new PostService();
		if(servletPath.indexOf("Posts") != -1){
			if(servletPath.indexOf("User") != -1){
				posts = postService.getByUser(targetId);
			}else if(servletPath.indexOf("Branch") != -1){
				posts = postService.getByBranch(targetId);
			}
		}else if(servletPath.indexOf("Comments") != -1){
			if(servletPath.indexOf("User") != -1){
				posts = postService.getInCommentByUser(targetId);
			}else if(servletPath.indexOf("Branch") != -1){
				posts = postService.getInCommentByBranch(targetId);
			}
		}

		request.setAttribute("posts", posts);
		dispatcherHome(request, response);
	}
}
