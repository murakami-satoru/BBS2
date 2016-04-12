package BBS.service;

import java.util.List;

import BBS.beans.Departments;
import BBS.dao.DepartmentsDao;
import BBS.utils.DBUtil;

public class DepartmentsService {

	public List<Departments> getDepartments(){
		return new DepartmentsDao().select(DBUtil.getConnection());
	}

}
