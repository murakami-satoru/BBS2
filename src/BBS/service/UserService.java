package BBS.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Users;
import BBS.dao.UsersDao;
import BBS.utils.BBSUtil;
import BBS.utils.DBUtil;

public class UserService {

	public void register(Users usersBean){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		try{
			usersBean.setPassword( BBSUtil.encrypt(usersBean.getPassword()));
			usersDao.insert(connection, usersBean);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}
	public int update(Users usersBean){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		int updateLine;
		try{
			updateLine = usersDao.update(connection, usersBean);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return updateLine;
	}

	public void managementUser(Users usersBean){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		try{
			usersDao.managementUser(connection, usersBean);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

	public Users login(String loginId,String password){
		Connection connection = DBUtil.getConnection();
		try{
			return new UsersDao().login(connection, loginId, BBSUtil.encrypt(password));
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

	public Users getUser(int id){
		Connection connection = DBUtil.getConnection();
		try{
			return new UsersDao().getUser(connection,id);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

	public List<Users> getUsers(){
		Connection connection = DBUtil.getConnection();
		try{
			return new UsersDao().selectAll(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

	public List<String> checkPassword(Users usersBean){
		List<String> messages = new ArrayList<String>();
		//入力された確認用パスワードとパスワードが一致しなければエラーメッセージを出力する。
		if(!usersBean.getPassword().equals(usersBean.getConfirmationPassword())){
			messages.add("パスワードが一致しません。");
		}
		return messages;
	}
}
