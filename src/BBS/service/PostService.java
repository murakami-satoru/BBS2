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
	public void delete(Posts postsBean){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		try{
			postsDao.delete(connection, postsBean);
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
			posts = postsDao.selectAll(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<String> getCategories(){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<String> categories = new ArrayList<String>();
		try{
			categories = postsDao.getCategories(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return categories;
	}

	public List<Posts> selectCategory(String category){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectCategory(connection,category);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}

	public List<Posts> selectDate(String date,boolean isFrom){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectDate(connection,date, isFrom);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Posts> selectDate(String fromDate,String toDate){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectDate(connection, fromDate, toDate);
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
