package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Posts;

public class PostsDao {

	public void insert(Connection connection,Posts posts){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into posts ("
				  + "title ,"
				  + "text ,"
				  + "category ,"
				  + "user_id ,"
				  + "created_date ,"
				  + "updated_date "
				  + " ) values ( "
				  + "? ,"	//title
				  + "? ,"	//text
				  + "? ,"	//category
				  + "? ,"	//post_id
				  + "CURRENT_TIMESTAMP ,"	//created_date
				  + "CURRENT_TIMESTAMP )"	//updated_date
				);

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, posts.getTitle());
			statement.setString(2, posts.getText());
			statement.setString(3, posts.getCategory());
			statement.setInt(4, posts.getUserId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public List<Posts> select(Connection connection){
		List<Posts> posts = new ArrayList<Posts>();
		String sql = "select * from view_posts order by id";
		try(ResultSet results = connection.prepareStatement(sql).executeQuery();){
			posts = toPostsList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return posts;
	}

	public List<Posts> toPostsList(ResultSet results,Connection connection){
		List<Posts> posts = new ArrayList<Posts>();
		CommentsDao commentsDao = new CommentsDao();
		try{
			while(results.next()){
				Posts postsBean = new Posts();
				postsBean.setId(results.getInt("id"));
				postsBean.setTitle(results.getString("title"));
				postsBean.setText(results.getString("text"));
				postsBean.setCategory(results.getString("category"));
				postsBean.setUserName(results.getString("user_name"));
				postsBean.setCreatedDate(results.getDate("created_date"));
				postsBean.setUpdatedDate(results.getDate("updated_date"));
				postsBean.setComments(commentsDao.select(connection, postsBean.getId()));

				posts.add(postsBean);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return posts;
	}

}
