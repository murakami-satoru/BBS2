package BBS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Posts;
import BBS.beans.Users;
import BBS.service.PostService;
import BBS.validation.RegisterPostForm;

@WebServlet(urlPatterns= { "/registerPost","/deletePost","/searchCategory","/searchDate" })
public class PostServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.setAttribute("categories", new PostService().getCategories());
		request.getRequestDispatcher("registerPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		if(request.getServletPath().indexOf("/registerPost") != -1){
			register(request, response);
		}else if(request.getServletPath().indexOf("/deletePost") != -1){
			delete(request, response);
		}else if(request.getServletPath().indexOf("/searchCategory") != -1){
			searchCategory(request, response);
		}else if(request.getServletPath().indexOf("/searchDate") != -1){
			searchDate(request, response);
		}
	}

	private void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Posts postsBean = new Posts();

		postsBean.setTitle(request.getParameter("title"));
		postsBean.setText(request.getParameter("mainText"));
		postsBean.setCategory(request.getParameter("category"));
		Users userBean = (Users) session.getAttribute("loginUser");
		postsBean.setUserId(userBean.getId());

		Map<String, List<String>> violationMessages = validate(toRegisterPostForm(postsBean));

		if(!violationMessages.isEmpty()){
			request.setAttribute("violationMessages", violationMessages);
			request.setAttribute("inputPosts", postsBean);
			request.getRequestDispatcher("registerPost.jsp").forward(request, response);
		}else{
			PostService postService = new PostService();
			//本文のエンコーダーはバリデーションの後に行う。
			postsBean.setText(lineSeparatorEncoder(postsBean.getText()));
			postService.register(postsBean);
			response.sendRedirect("home");
		}
	}
	private void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Posts postsBean = new Posts();

		postsBean.setId(Integer.parseInt(request.getParameter("post_id")));
		PostService postService = new PostService();
		postService.delete(postsBean);
		response.sendRedirect("home");
	}

	private void searchCategory(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		PostService postService = new PostService();
		String category = request.getParameter("category");
		request.setAttribute("posts", postService.selectCategory(category));
		request.setAttribute("categories", postService.getCategories());
		request.setAttribute("inputCategory", category);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	private void searchDate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		PostService postService = new PostService();

		String fromDate = request.getParameter("from_date");
		String toDate = request.getParameter("to_date");
		List<Posts> posts = new ArrayList<Posts>();

		String timeFormatFromDate = fromDate + " 00:00:00";
		String timeFormattoDate = toDate + " 23:59:59";

		if(!toDate.isEmpty() && !fromDate.isEmpty()){
			posts = postService.selectDate(timeFormatFromDate,timeFormattoDate);
		}else if(!toDate.isEmpty()){
			posts = postService.selectDate(timeFormattoDate,false);
		}else if(!fromDate.isEmpty()){
			posts = postService.selectDate(timeFormatFromDate, true);
		}

		request.setAttribute("posts", posts);
		request.setAttribute("inputFromDate", fromDate);
		request.setAttribute("inputToDate", toDate);
		request.setAttribute("categories", postService.getCategories());
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private RegisterPostForm toRegisterPostForm(Posts postsBean){

		RegisterPostForm form = new RegisterPostForm();

		String title = postsBean.getTitle();
		String text = postsBean.getText();
		String category = postsBean.getCategory();

		if(!title.isEmpty()){
			form.setTitle(title);
		}
		if(!text.isEmpty()){
			form.setText(text);
		}
		if(!category.isEmpty()){
			form.setCategory(category);
		}
		return form;
	}
}
