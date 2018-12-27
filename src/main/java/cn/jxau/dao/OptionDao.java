package cn.jxau.dao;

import java.util.List;

import cn.jxau.core.dao.BaseDao;


public interface OptionDao extends BaseDao {
	//��������idɾ�����ڸ������ȫ��ѡ��
	public int deleteOptions(int subjectId) throws Exception;
}
