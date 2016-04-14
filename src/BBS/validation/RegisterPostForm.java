package BBS.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterPostForm implements BBSForm {

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=50, message = "1～50" + OVER_SIZE_ERROR)
	private String _title;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=1000, message = "1～1000" + OVER_SIZE_ERROR)
	private String _text;

	@NotNull(message = REQUIRED_ERROR)
	@Size(min = 1, max=10, message = "1～10" + OVER_SIZE_ERROR)
	private String _category;

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category;
	}
}
