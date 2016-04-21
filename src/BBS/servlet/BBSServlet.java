package BBS.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import BBS.service.CountService;
import BBS.service.PostService;
import BBS.validation.BBSForm;

public class BBSServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected Map<String, List<String>> validate(BBSForm form){

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<BBSForm>> validationResult = validator.validate(form);

		Map<String, List<String>> violationMessages = new HashMap<String, List<String>>();

		for(ConstraintViolation<BBSForm> violation : validationResult){

			String propertyPath = violation.getPropertyPath().toString();
			List<String> messages = violationMessages.get(propertyPath);
			if(messages == null){
				messages = new ArrayList<String>();
				violationMessages.put(propertyPath, messages);
			}
			messages.add(violation.getMessage());
		}
		return violationMessages;
	}

	protected void dispatcherHome(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		PostService postService = new PostService();
		CountService countService = new CountService();

		request.setAttribute("categories", postService.getCategories());
		request.setAttribute("post_by_branch", countService.getCountPostByBranch());
		request.setAttribute("post_by_user", countService.getCountPostByUser());
		request.setAttribute("comment_by_branch", countService.getCountCommentByBranch());
		request.setAttribute("comment_by_user", countService.getCountCommentByUser());
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected Date parseDate(String date){
		try {
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}
}
