package BBS.beans;

public class Users {

	private int _id;
	private String _loginId;
	private String _password;
	private String _confirmationPassword;
	private String _name;
	private int _branchId;
	private String _branchName;
	private int _departmentId;
	private String _departmentName;
	private int _isLocked;
	private String _updatedDate;

	//User.idのセッター・ゲッター
	public void setId(int id){
		_id = id;
	}
	public int getId(){
		return _id;
	}

	//User.login_idのセッター・ゲッター
	public void setLoginId(String loginId){
		_loginId = loginId;
	}
	public String getLoginId(){
		return _loginId;
	}

	//User.passwordのセッター・ゲッター
	public void setPassword(String password){
		_password = password;
	}
	public String getPassword(){
		return _password;
	}
	//確認用パスワードのセッター・ゲッター
	public void setConfirmationPassword(String confirmationPassword){
		_confirmationPassword = confirmationPassword;
	}
	public String getConfirmationPassword(){
		return _confirmationPassword;
	}

	//User.nameのセッター・ゲッター
	public void setName(String name){
		_name = name;
	}
	public String getName(){
		return _name;
	}

	//User.branch_idのセッター・ゲッター
	public void setBranchId(int branchId){
		_branchId = branchId;
	}
	public int getBranchId(){
		return _branchId;
	}

	//User.branch_idに紐づくBranches.nameのセッター・ゲッター
	public void setBranchName(String branchName){
		_branchName = branchName;
	}
	public String getBranchName(){
		return _branchName;
	}

	//User.department_idのセッター・ゲッター
	public void setDepartmentId(int departmentId){
		_departmentId = departmentId;
	}
	public int getDepartmentId(){
		return _departmentId;
	}

	//User.department_idに紐づくDepartments.nameのセッター・ゲッター
	public void setDepartmentName(String departmentName){
		_departmentName = departmentName;
	}
	public String getDepartmentName(){
		return _departmentName;
	}

	//User.is_lockedのセッター・ゲッター
	public void setIsLocked(int isLocked){
		_isLocked = isLocked;
	}
	public int getIsLocked(){
		return _isLocked;
	}

	//Users.updated_dateのセッター・ゲッター
	public void setUpdatedDate(String updatedDate){
		_updatedDate = updatedDate;
	}
	public String getUpdatedDate(){
		return _updatedDate;
	}
}
