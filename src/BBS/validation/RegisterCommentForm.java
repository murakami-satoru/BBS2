package BBS.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterCommentForm implements BBSForm {
	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=500, message = "1～500" + OVER_SIZE_ERROR)
	private String _text;

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}
}
