package BBS.service;

import java.sql.Connection;

import BBS.beans.Users;
import BBS.dao.UsersDao;
import BBS.utils.CipherUtil;
import BBS.utils.DBUtil;

public class LoginService {

	public Users login(String loginId,String password){
		Connection connection = DBUtil.getConnection();
		UsersDao usersDao = new UsersDao();
		try{
			Users users = usersDao.getUsers(connection, loginId, CipherUtil.encrypt(password));

			DBUtil.commit(connection);
			return users;
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}

}
