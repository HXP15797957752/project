package cn.jxau.dao;

import cn.jxau.core.dao.BaseDao;

public interface SubjectDao extends BaseDao {
	public int getGenerateId() throws Exception;
	public int getUserCount(int subjectId) throws Exception;
}
