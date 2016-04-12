package BBS.service;

import java.sql.Connection;

import BBS.beans.Users;
import BBS.dao.UsersDao;
import BBS.utils.CipherUtil;
import BBS.utils.DBUtil;

public class UserService {

	public void regist(Users user){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		try{
			user.setPassword( CipherUtil.encrypt(user.getPassword()));
			usersDao.insert(connection, user);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

}
