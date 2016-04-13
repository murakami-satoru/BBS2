package BBS.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserForm implements MyForm {

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 6, max=20, message = "6～20" + OVER_SIZE_ERROR)
	@Pattern(regexp="[a-zA-Z0-9]*$" ,message="半角英数" + PATTERN_ERROR)
	private String _loginId;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 6, max=255, message = "6～255" + OVER_SIZE_ERROR)
	@Pattern(regexp="[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$" ,message="記号を含む半角" + PATTERN_ERROR)
	private String _password;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=10, message = "1～10" + OVER_SIZE_ERROR)
	private String _name;

	public void setLoginId(String loginId){
		_loginId = loginId;
	}

	public String getLoginId(){
		return _loginId;
	}

	public void setPassword(String password){
		_password = password;
	}

	public String getPassword(){
		return _password;
	}

	public void setName(String name){
		_name = name;
	}

	public String getName(){
		return _name;
	}
}
