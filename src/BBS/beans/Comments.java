package BBS.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comments {

	private int _id;
	private String _text;
	private int _userId;
	private String _userName;
	private int _postId;
	private Date _createdDate;
	private Date _updatedDate;
	private SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy年MM月dd日 E曜日 hh:mm:ss");

	//Comments.idのセッター・ゲッター
	public void setId(int id){
		_id = id;
	}
	public int getId(){
		return _id;
	}

	//Comments.textのセッター・ゲッター
	public void setText(String text){
		_text = text;
	}
	public String getText(){
		return _text;
	}

	//Comments.user_idのセッター・ゲッター
	public void setUserId(int userId){
		_userId = userId;
	}
	public int getUserId(){
		return _userId;
	}

	//Comments.post_idのセッター・ゲッター
	public void setPostId(int postId){
		_postId = postId;
	}
	public int getPostId(){
		return _postId;
	}

	//Comments.created_dateのセッター・ゲッター
	public void setCreatedDate(Date createdDate){
		_createdDate = createdDate;
	}
	public Date getCreatedDate(){
		return _createdDate;
	}
	public String getCreatedDateString(){
		return _dateFormat.format(_createdDate);
	}

	//user_idに紐づくUsers.nameのセッター・ゲッター
	public void setUserName(String userName){
		_userName = userName;
	}
	public String getUserName(){
		return _userName;
	}

	//Comments.updated_dateのセッター・ゲッター
	public void setUpdatedDate(Date updatedDate){
		_updatedDate = updatedDate;
	}
	public Date getUpdatedDate(){
		return _updatedDate;
	}

}
