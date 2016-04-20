package BBS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Posts;
import BBS.beans.Users;

public class PostsDao {

	public void insert(Connection connection,Posts postsBean){
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
			statement.setString(1, postsBean.getTitle());
			statement.setString(2, postsBean.getText());
			statement.setString(3, postsBean.getCategory());
			statement.setInt(4, postsBean.getUserId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void delete(Connection connection,Posts postsBean){
		String sql = "delete from posts where id = ? ";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setInt(1, postsBean.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public List<String> getCategories(Connection connection){
		List<String> categories = new ArrayList<String>();
		//カテゴリー一覧表示のため一番上にブランクが来るように追加。
		categories.add("");
		String sql = "select distinct category from view_posts order by id  ";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			while(results.next()){
				categories.add(results.getString("category"));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return categories;
	}

	public List<Posts> selectAll(Connection connection){
		List<Posts> posts = new ArrayList<Posts>();
		String sql = "select * from view_posts order by id  ";
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			ResultSet results = statement.executeQuery();
			posts = toPostsList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return posts;
	}

	public List<Posts> selectCategory(Connection connection, String category){
		List<Posts> posts = new ArrayList<Posts>();
		String sql = "select * from view_posts where category = ? order by id";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, category);
			ResultSet results = statement.executeQuery();
			posts = toPostsList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return posts;
	}

	public List<Posts> selectDate(Connection connection, String date, boolean isFrom){
		List<Posts> posts = new ArrayList<Posts>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_posts where created_date ");
		if(isFrom){
			sql.append(" >= ? order by id ");
		}else{
			sql.append(" <= ? order by id ");
		}
		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, date);
			ResultSet results = statement.executeQuery();
			posts = toPostsList(results,connection);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return posts;
	}

	public List<Posts> selectDate(Connection connection, String fromDate, String toDate){
		List<Posts> posts = new ArrayList<Posts>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_posts where created_date between ? and ? ");

		try(PreparedStatement statement = connection.prepareStatement(sql.toString())){
			statement.setString(1, fromDate);
			statement.setString(2, toDate);
			ResultSet results = statement.executeQuery();
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
				Users user = new Users();
				postsBean.setUser(user);
				postsBean.setId(results.getInt("id"));
				postsBean.setTitle(results.getString("title"));
				postsBean.setText(results.getString("text"));
				postsBean.setCategory(results.getString("category"));
				postsBean.setUserName(results.getString("user_name"));
				postsBean.setBranchId(results.getInt("branch_id"));
				postsBean.setDepartmentId(results.getInt("department_id"));
				postsBean.setCreatedDate(results.getTimestamp("created_date"));
				postsBean.setUpdatedDate(results.getTimestamp("updated_date"));
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
