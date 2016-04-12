package BBS.service;

import java.util.List;

import BBS.beans.Branches;
import BBS.dao.BranchesDao;
import BBS.utils.DBUtil;

public class BranchesService {

	public List<Branches> getBranches(){
		return new BranchesDao().select(DBUtil.getConnection());
	}

}
