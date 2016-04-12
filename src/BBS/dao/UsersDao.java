package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Users;

public class UsersDao {

	public Users getUsers(Connection connection, String loginId, String password){
		String sql = "select * from users where login_id = ? and password = ?";
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

	public void insert(Connection connection,Users users){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into users ("
				  + "login_id ,"
				  + "password ,"
				  + "name ,"
				  + "branch_id ,"
				  + "department_id"
				  + " ) values ( "
				  + "? ,"	//login_id
				  + "? ,"	//password
				  + "? ,"	//name
				  + "? ,"	//branch_id
				  + "? )"	//department_id
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

	public List<Users> selectAll(Connection connection){
		List<Users> users = new ArrayList<Users>();
		String sql = "select * from users order by id";

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
				usersBean.setId(results.getInt("id"));
				usersBean.setLoginId(results.getString("login_id"));
				usersBean.setPassword(results.getString("password"));
				usersBean.setName(results.getString("name"));
				usersBean.setBranchId(results.getInt("branch_id"));
				usersBean.setDepartmentId(results.getInt("department_id"));
				usersBean.setIsLocked(results.getInt("is_locked"));

				users.add(usersBean);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return users;
	}
}
