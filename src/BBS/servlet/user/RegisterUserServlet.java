package BBS.servlet.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.Users;
import BBS.service.UserService;

@WebServlet(urlPatterns= { "/registerUser"})
public class RegisterUserServlet extends UserServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("registerUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		registerUser(request, response);
	}

	//ユーザー新規登録
	private void registerUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Users usersBean = new Users();
		UserService userService =  new UserService();

		usersBean.setLoginId(request.getParameter("login_id"));
		usersBean.setPassword(request.getParameter("password"));
		usersBean.setConfirmationPassword(request.getParameter("confirmation_password"));
		usersBean.setName(request.getParameter("name"));
		usersBean.setBranchId(Integer.parseInt(request.getParameter("branch")));
		usersBean.setDepartmentId(Integer.parseInt(request.getParameter("department")));

		Map<String, List<String>> violationMessages = validate(toUserForm(usersBean));
		List<String> messages = userService.checkPassword(usersBean);

		if(!violationMessages.isEmpty() || !messages.isEmpty()){
			request.setAttribute("violationMessages", violationMessages);
			request.setAttribute("messages", messages);
			request.setAttribute("inputUsers", usersBean);
			request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		}else{
			userService.register(usersBean);
			request.setAttribute("users", userService.getUsers());
			request.getRequestDispatcher("managementUser.jsp").forward(request, response);
		}
	}
}
