package BBS.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Comments;
import BBS.beans.Users;
import BBS.service.CommentService;
import BBS.service.PostService;
import BBS.validation.RegisterCommentForm;

@WebServlet(urlPatterns= { "/registerComment" })
public class CommentServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		if(request.getServletPath().indexOf("/registerComment") != -1){
			register(request, response);
		}
	}


	private void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Comments commentsBean = new Comments();
		HttpSession session = request.getSession();
		commentsBean.setText(request.getParameter("mainText"));
		commentsBean.setPostId(Integer.parseInt(request.getParameter("post_id")));
		Users userBean = (Users) session.getAttribute("loginUser");
		commentsBean.setUserId(userBean.getId());

		Map<String, List<String>> violationMessages = validate(toRegisterCommentForm(commentsBean));

		if(!violationMessages.isEmpty()){
			PostService postService = new PostService();
			request.setAttribute("violationMessages", violationMessages);
			request.setAttribute("isErrorPost",commentsBean.getPostId());
			request.setAttribute("inputComments", commentsBean);
			request.setAttribute("posts", postService.getPosts());
			request.setAttribute("categories", postService.getCategories());
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else{
			CommentService commentService = new CommentService();
			//本文のエンコーダーはバリデーションの後に行う。
			commentsBean.setText(lineSeparatorEncoder(commentsBean.getText()));
			commentService.register(commentsBean);
			response.sendRedirect("home");
		}
	}


	private RegisterCommentForm toRegisterCommentForm(Comments commentsBean){
		RegisterCommentForm form = new RegisterCommentForm();
		String text = commentsBean.getText();

		if(!text.isEmpty()){
			form.setText(text);
		}
		return form;
	}
}
