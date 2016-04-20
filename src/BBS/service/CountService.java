package BBS.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Count;
import BBS.dao.CountDao;
import BBS.utils.DBUtil;

public class CountService {

	public List<Count> getCountCommentByBranch(){
		Connection connection = DBUtil.getConnection();
		CountDao postsDao = new CountDao();
		List<Count> posts = new ArrayList<Count>();
		try{
			posts = postsDao.getCountCommentByBranch(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Count> getCountCommentByUser(){
		Connection connection = DBUtil.getConnection();
		CountDao postsDao = new CountDao();
		List<Count> posts = new ArrayList<Count>();
		try{
			posts = postsDao.getCountCommentByUser(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Count> getCountPostByBranch(){
		Connection connection = DBUtil.getConnection();
		CountDao postsDao = new CountDao();
		List<Count> posts = new ArrayList<Count>();
		try{
			posts = postsDao.getCountPostByBranch(connection);
			DBUtil.commit(connection);
		}catch(RuntimeException | Error e){
			DBUtil.rollback(connection);
			throw e;
		} finally {
			DBUtil.close(connection);
		}

		return posts;
	}
	public List<Count> getCountPostByUser(){
		Connection connection = DBUtil.getConnection();
		CountDao postsDao = new CountDao();
		List<Count> posts = new ArrayList<Count>();
		try{
			posts = postsDao.getCountPostByUser(connection);
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
