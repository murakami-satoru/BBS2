package BBS.servlet;

import java.io.IOException;
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
		postsBean.setText(lineSeparatorEncoder(request.getParameter("mainText")));
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
			postService.register(postsBean);
			response.sendRedirect("home");
		}
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
