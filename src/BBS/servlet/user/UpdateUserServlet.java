package BBS.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BBS.beans.Users;
import BBS.service.UserService;

@WebServlet(urlPatterns= {"/updateUser"})
public class UpdateUserServlet extends UserServlet{
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Users usersBean = new Users();

		usersBean.setId(Integer.parseInt(request.getParameter("id")));
		usersBean.setLoginId(request.getParameter("login_id"));
		usersBean.setPassword(request.getParameter("password"));
		usersBean.setConfirmationPassword(request.getParameter("confirmation_password"));
		usersBean.setName(request.getParameter("name"));
		usersBean.setBranchId(Integer.parseInt(request.getParameter("branch")));
		usersBean.setDepartmentId(Integer.parseInt(request.getParameter("department")));
		usersBean.setUpdatedDate(request.getParameter("updated_date"));

		UserService userService = new UserService();
		List<String> messages = userService.checkPassword(usersBean);
		Map<String, List<String>> violationMessages = validate(toUserForm(usersBean));

		//バリデーションメッセージかパスワードの不一致があった場合は更新できない
		if(!violationMessages.isEmpty() || !messages.isEmpty()){
			request.setAttribute("user", usersBean);
			request.setAttribute("violationMessages", violationMessages);
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		}else{
			update(request, response, usersBean);
		}
	}

	protected void update(HttpServletRequest request,HttpServletResponse response,Users usersBean) throws IOException,ServletException{
		UserService userService = new UserService();
		if(userService.update(usersBean) == 0){
			List<String> messages = new ArrayList<String>();
			messages.add("["+usersBean.getLoginId()+"]");
			messages.add("このユーザーはすでに編集されています");
			messages.add("再度、管理画面から編集してください");
			request.setAttribute("errorMessages", messages);
		}

		request.setAttribute("users", userService.getUsers());
		request.getRequestDispatcher("managementUser.jsp").forward(request, response);

	}
}
