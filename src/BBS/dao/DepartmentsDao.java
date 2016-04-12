package BBS.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Departments;

public class DepartmentsDao {

	public List<Departments> select(Connection connection){
		List<Departments> departments = new ArrayList<Departments>();
		String sql = "select * from departments order by id";
		try(ResultSet results = connection.prepareStatement(sql).executeQuery();){
			departments = toDepartmentsList(results);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return departments;
	}

	public List<Departments> toDepartmentsList(ResultSet results){
		List<Departments> departments = new ArrayList<Departments>();
		try{
			while(results.next()){
				Departments departmentsBean = new Departments();
				departmentsBean.setId(results.getInt("id"));
				departmentsBean.setName(results.getString("name"));

				departments.add(departmentsBean);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return departments;
	}

}
