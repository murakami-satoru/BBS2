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

	public List<Posts> getByCategory(String category){
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

	public List<Posts> getByDate(String date,boolean isFrom){
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
	public List<Posts> getByDate(String fromDate,String toDate){
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

	public List<Posts> getByBranch(int id){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectByBranch(connection, id);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Posts> getByUser(int id){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectByUser(connection, id);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Posts> getInCommentByBranch(int id){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectInCommentByBranch(connection, id);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Posts> getInCommentByUser(int id){
		Connection connection = DBUtil.getConnection();
		PostsDao postsDao = new PostsDao();
		List<Posts> posts = new ArrayList<Posts>();
		try{
			posts = postsDao.selectInCommentByUser(connection, id);
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
