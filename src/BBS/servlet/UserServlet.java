package BBS.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BBS.beans.Users;
import BBS.service.BranchService;
import BBS.service.DepartmentService;
import BBS.service.UserService;
import BBS.validation.RegisterUserForm;

@WebServlet(urlPatterns= { "/managementUser","/registerUser" })
public class UserServlet extends BBSServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String servletPath = (String) request.getServletPath();

		if(servletPath.equals("/managementUser")){
			request.getRequestDispatcher("managementUser.jsp").forward(request, response);

		}else if(servletPath.equals("/registerUser")){
			HttpSession session = request.getSession();
			session.setAttribute("branches", new BranchService().getBranches());
			session.setAttribute("departments", new DepartmentService().getDepartments());

			request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		Users usersBean = new Users();

		usersBean.setLoginId(request.getParameter("login_id"));
		usersBean.setPassword(request.getParameter("password"));
		usersBean.setName(request.getParameter("name"));
		usersBean.setBranchId(Integer.parseInt(request.getParameter("branch")));
		usersBean.setDepartmentId(Integer.parseInt(request.getParameter("department")));

		Map<String, List<String>> violationMessages = validate(toRegisterUserForm(usersBean));

		if(!violationMessages.isEmpty()){
			request.setAttribute("violationMessages", violationMessages);
			request.setAttribute("inputUsers", usersBean);
			request.getRequestDispatcher("registerUser.jsp").forward(request, response);
		}else{
			new UserService().register(usersBean);
			request.getRequestDispatcher("managementUser.jsp").forward(request, response);
		}
	}

	private RegisterUserForm toRegisterUserForm(Users usersBean){

		RegisterUserForm form = new RegisterUserForm();

		String loginId = usersBean.getLoginId();
		String password = usersBean.getPassword();
		String name = usersBean.getName();

		if(!loginId.isEmpty()){
			form.setLoginId(loginId);
		}
		if(!password.isEmpty()){
			form.setPassword(password);
		}
		if(!name.isEmpty()){
			form.setName(name);
		}

		return form;
	}
}
