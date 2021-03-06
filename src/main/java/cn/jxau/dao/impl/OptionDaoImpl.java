package cn.jxau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.jxau.core.dao.BaseDaoImpl;
import cn.jxau.core.dao.BaseQueryModel;
import cn.jxau.core.dao.DbHelper;
import cn.jxau.dao.OptionDao;
import cn.jxau.entity.Option;
import cn.jxau.entity.OptionQueryModel;


public class OptionDaoImpl extends BaseDaoImpl implements OptionDao {

	@Override
	public String getSelectAllSql() {		
		return "select * from t_option ";
	}

	@Override
	public String getSelectSql(BaseQueryModel queryModel) {
		OptionQueryModel qm = (OptionQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_option where 1=1 ");
		if(qm.getSubjectId()!=null){
			sb.append(" and subjectId="+qm.getSubjectId());
		}
		
		return sb.toString();
	}

	@Override
	public String getInsertSql(Object data) {	
		Option option = (Option)data;
		return "insert into t_option(content,idx,subjectId) values('"+option.getContent()+"',"+option.getIndex()+","+option.getSubjectId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		Option option = (Option)data;
		return "update t_option set content='"+option.getContent()+"',idx="+option.getIndex()+",subjectId="+option.getSubjectId()+" where id="+option.getId();
	}

	@Override
	public String getDeleteSql(int id) {		
		return "delete from t_option where id="+id;
	}

	@Override
	public Object rsToModel(ResultSet rs) throws Exception {
		Option option = new Option();
		option.setId(rs.getInt("id"));
		option.setContent(rs.getString("content"));
		option.setIndex(rs.getInt("idx"));
		option.setSubjectId(rs.getInt("subjectId"));
		
		return option;
	}	

	@Override
	public int deleteOptions(int subjectId) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = "delete from t_option where subjectId="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

}
