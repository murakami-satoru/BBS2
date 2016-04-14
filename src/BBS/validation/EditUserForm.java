package BBS.validation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditUserForm extends UserForm{

	@Size(min = 6, max=255, message = "6～255" + OVER_SIZE_ERROR)
	@Pattern(regexp="[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$" ,message="記号を含む半角" + PATTERN_ERROR)
	private String _confirmationPassword;

	@Size(min = 6, max=255, message = "6～255" + OVER_SIZE_ERROR)
	@Pattern(regexp="[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$" ,message="記号を含む半角" + PATTERN_ERROR)
	private String _password;

	public void setConfirmationPassword(String confirmationPassword){
		_confirmationPassword = confirmationPassword;
	}
	public String getConfirmationPassword(){
		return _confirmationPassword;
	}

	public void setPassword(String password){
		_password = password;
	}

	public String getPassword(){
		return _password;
	}
}
