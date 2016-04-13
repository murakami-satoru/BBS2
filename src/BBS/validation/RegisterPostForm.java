package BBS.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterPostForm implements MyForm {

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=50, message = "1～50" + OVER_SIZE_ERROR)
	private String _title;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=1000, message = "1～1000" + OVER_SIZE_ERROR)
	private String _text;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=10, message = "1～10" + OVER_SIZE_ERROR)
	private String _category;

	public String gettitle() {
		return _title;
	}

	public void settitle(String title) {
		_title = title;
	}

	public String gettext() {
		return _text;
	}

	public void settext(String text) {
		_text = text;
	}

	public String getcategory() {
		return _category;
	}

	public void setcategory(String category) {
		_category = category;
	}
}
