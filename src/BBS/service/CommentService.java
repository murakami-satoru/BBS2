package BBS.service;

import java.sql.Connection;

import BBS.beans.Comments;
import BBS.dao.CommentsDao;
import BBS.utils.DBUtil;

public class CommentService {

	public void register(Comments commentsBean){
		Connection connection = DBUtil.getConnection();
		CommentsDao commentsDao = new CommentsDao();
		try{
			commentsDao.insert(connection, commentsBean);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}
}
