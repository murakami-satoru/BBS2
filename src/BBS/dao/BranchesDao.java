package BBS.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Branches;

public class BranchesDao {

	public List<Branches> select(Connection connection){
		List<Branches> branches = new ArrayList<Branches>();
		String sql = "select * from branches order by id";
		try(ResultSet results = connection.prepareStatement(sql).executeQuery();){
			branches = toBranchesList(results);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return branches;
	}

	public List<Branches> toBranchesList(ResultSet results){
		List<Branches> branches = new ArrayList<Branches>();
		try{
			while(results.next()){
				Branches branchesBean = new Branches();
				branchesBean.setId(results.getInt("id"));
				branchesBean.setName(results.getString("name"));

				branches.add(branchesBean);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return branches;
	}

}
