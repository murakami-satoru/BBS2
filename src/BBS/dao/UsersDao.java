package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Users;
import BBS.utils.BBSUtil;

public class UsersDao {

	public Users login(Connection connection, String loginId, String password){
		//アカウント停止していたらログインできない。
		String sql = "select * from view_users where is_locked = 0 and login_id = ? and password = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, loginId);
			statement.setString(2, password);

			ResultSet results = statement.executeQuery();
			List<Users> users = toUsersList(results);
			if(users.isEmpty()){
				return null;
			}else if(2 <= users.size()){
				throw new IllegalStateException("2 <= user.size()");
			}else{
				return users.get(0);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Users getUser(Connection connection, int id){
		String sql = "select * from view_users where user_id = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			List<Users> users = toUsersList(results);
			return users.get(0);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insert(Connection connection,Users users){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into users ("
				  + "login_id ,"
				  + "password ,"
				  + "name ,"
				  + "branch_id ,"
				  + "department_id ,"
				  + "created_date ,"
				  + "updates_date ,"
				  + " ) values ( "
				  + "? ,"	//login_id
				  + "? ,"	//password
				  + "? ,"	//name
				  + "? ,"	//branch_id
				  + "? ,"	//department_id
				  + "CURRENT_TIMESTAMP ,"	//created_date
				  + "CURRENT_TIMESTAMP )"	//updated_date)"
				);

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, users.getLoginId());
			statement.setString(2, users.getPassword());
			statement.setString(3, users.getName());
			statement.setInt(4, users.getBranchId());
			statement.setInt(5, users.getDepartmentId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public int update(Connection connection,Users users){
		StringBuilder sql = new StringBuilder();
		sql.append("update users set "
				 + "  login_id = ? "
				 + ", name = ? "
				 + ", branch_id = ? "
				 + ", department_id = ? "
				 );
		//passwordが入力されていれば更新
		boolean isPassword = users.getPassword().equals("");
		if(!isPassword){
			sql.append(", password = ? ");
		}
		sql.append(", updated_date = CURRENT_TIMESTAMP ");
		sql.append(" where id = ? and updated_date = ?");
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, users.getLoginId());
			statement.setString(2, users.getName());
			statement.setInt(3, users.getBranchId());
			statement.setInt(4, users.getDepartmentId());
			int parameterIndex = 5;
			//passwordが入力されていれば更新
			if(!isPassword){
				//パスワードの暗号化
				users.setPassword( BBSUtil.encrypt(users.getPassword()));
				statement.setString(parameterIndex++, users.getPassword());
			}
			statement.setInt(parameterIndex++, users.getId());
			statement.setString(parameterIndex++,users.getUpdatedDate());
			return statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return 0;
	}

	public void managementUser(Connection connection,Users users){
		StringBuilder sql = new StringBuilder();
		sql.append("update users set is_locked = ? ");
		sql.append(" where id = ? ");
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){

			//isLockedを見て停止か復活かを分ける
			if(users.getIsLocked() == 0){
				//停止させる「１」を入れる
				statement.setInt(1, 1);
			}else{
				//復活させる「０」を入れる
				statement.setInt(1, 0);
			}

			statement.setInt(2, users.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public List<Users> selectAll(Connection connection){
		List<Users> users = new ArrayList<Users>();
		String sql = "select * from view_users order by user_id";

		try(ResultSet result = connection.prepareStatement(sql).executeQuery();){
			users = toUsersList(result);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return users;
	}

	public List<Users> toUsersList(ResultSet results){
		List<Users> users = new ArrayList<Users>();
		try{
			while(results.next()){
				Users usersBean = new Users();
				usersBean.setId(results.getInt("user_id"));
				usersBean.setLoginId(results.getString("login_id"));
				usersBean.setPassword(results.getString("password"));
				usersBean.setName(results.getString("user_name"));
				usersBean.setBranchId(results.getInt("branch_id"));
				usersBean.setBranchName(results.getString("branch_name"));
				usersBean.setDepartmentId(results.getInt("department_id"));
				usersBean.setDepartmentName(results.getString("department_name"));
				usersBean.setIsLocked(results.getInt("is_locked"));
				usersBean.setUpdatedDate(results.getString("updated_date"));
				users.add(usersBean);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return users;
	}
}
