package BBS.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import BBS.validation.MyForm;

@WebServlet(urlPatterns= { "/login" })
public class VaildatorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected Map<String, List<String>> validate(HttpServletRequest request, MyForm form){

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<MyForm>> validationResult = validator.validate(form);

		Map<String, List<String>> violationProperties = new HashMap<String, List<String>>();

		for(ConstraintViolation<MyForm> violation : validationResult){

			String propertyPath = violation.getPropertyPath().toString();
			List<String> messages = violationProperties.get(propertyPath);
			if(messages == null){
				messages = new ArrayList<String>();
				violationProperties.put(propertyPath, messages);
			}
			messages.add(violation.getMessage());
		}
		return violationProperties;
	}
}
