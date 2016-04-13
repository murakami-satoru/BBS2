package BBS.service;

import java.sql.Connection;

import BBS.beans.Users;
import BBS.dao.UsersDao;
import BBS.utils.CipherUtil;
import BBS.utils.DBUtil;

public class UserService {

	public void register(Users usersBean){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		try{
			usersBean.setPassword( CipherUtil.encrypt(usersBean.getPassword()));
			usersDao.insert(connection, usersBean);
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
//			return new UsersDao().getUsers(connection, loginId, password);
			return new UsersDao().getUsers(connection, loginId, CipherUtil.encrypt(password));
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

}
