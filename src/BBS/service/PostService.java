package BBS.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Posts;
import BBS.dao.PostsDao;
import BBS.utils.DBUtil;

public class PostService {

	public void register(Posts postsBean){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		try{
			postsDao.insert(connection, postsBean);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}
	}
	public List<Posts> getPosts(){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.select(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}



}
