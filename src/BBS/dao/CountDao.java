package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Count;

public class CountDao {

	public List<Count> getCountPostByBranch(Connection connection){
		List<Count> count = new ArrayList<Count>();
		String sql = "select * from count_post_by_branch order by count desc";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			count = toCountList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return count;
	}
	public List<Count> getCountPostByUser(Connection connection){
		List<Count> count = new ArrayList<Count>();
		String sql = "select * from count_post_by_user order by count desc";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			count = toCountList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return count;
	}

	public List<Count> getCountCommentByBranch(Connection connection){
		List<Count> count = new ArrayList<Count>();
		String sql = "select * from count_comment_by_branch order by count desc";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			count = toCountList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return count;
	}
	public List<Count> getCountCommentByUser(Connection connection){
		List<Count> count = new ArrayList<Count>();
		String sql = "select * from count_comment_by_user order by count desc";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			count = toCountList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return count;
	}

	public List<Count> toCountList(ResultSet results,Connection connection){
		List<Count> count = new ArrayList<Count>();
		try{
			while(results.next()){
				Count countBean = new Count();

				countBean.setId(results.getInt("id"));
				countBean.setName(results.getString("name"));
				countBean.setCount(results.getInt("count"));

				count.add(countBean);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return count;
	}

}
