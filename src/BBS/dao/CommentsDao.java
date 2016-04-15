package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Comments;

public class CommentsDao {

	public void insert(Connection connection,Comments commentsBean){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into comments ("
				  + "text ,"
				  + "user_id ,"
				  + "post_id ,"
				  + "created_date ,"
				  + "updated_date "
				  + " ) values ( "
				  + "? ,"	//text
				  + "? ,"	//post_id
				  + "? ,"	//user_id
				  + "CURRENT_TIMESTAMP ,"	//created_date
				  + "CURRENT_TIMESTAMP )"	//updated_date
				);

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, commentsBean.getText());
			statement.setInt(2, commentsBean.getUserId());
			statement.setInt(3, commentsBean.getPostId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void delete(Connection connection,Comments commentsBean){
		String sql = "delete from posts where id = ?";

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setInt(1, commentsBean.getId());
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public List<Comments> select(Connection connection,int postId){
		List<Comments> commentsBeans = new ArrayList<Comments>();
		String sql = "select * from view_comments where post_id = ? order by id";

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setInt(1, postId);
			commentsBeans = toCommentsList(statement.executeQuery());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return commentsBeans;
	}

	public List<Comments> toCommentsList(ResultSet results){
		List<Comments> comments = new ArrayList<Comments>();
		try{
			while(results.next()){
				Comments commentsBean = new Comments();
				commentsBean.setId(results.getInt("id"));
				commentsBean.setText(results.getString("text"));
				commentsBean.setUserName(results.getString("user_name"));
				commentsBean.setCreatedDate(results.getTimestamp("created_date"));
				commentsBean.setUpdatedDate(results.getTimestamp("updated_date"));

				comments.add(commentsBean);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return comments;
	}

}
