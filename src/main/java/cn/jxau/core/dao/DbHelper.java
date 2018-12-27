package cn.jxau.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DbHelper {
	//���ݿ����ӳ�
	private static DataSource dataSource = new ComboPooledDataSource();
	//�̲ۣ߳�����ά���߳��ڵ�Connection����
	private static ThreadLocal<Connection> connectionPool = new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws Exception{
		//���̲߳��û�ȡ�߳��ڵ����Ӷ���
		Connection con = connectionPool.get();
		//������Ӷ���Ϊnull����ʾ���߳��л�û�д������Ӷ���
		//������ӳ��л�ȡһ�����Ӷ��󣬲������̲߳�
		if(con==null){
			con = dataSource.getConnection();
			connectionPool.set(con);
		}
		
		return con;
	}
	
	public static void close() throws Exception{
		Connection con  = connectionPool.get();
		if(con!=null){
			con.close();
			connectionPool.remove();
		}
	}
	
	public static void beginTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.setAutoCommit(false);
		}
	}
	
	public static void commitTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.commit();
		}
	}
	
	public static void rollbackTrans() throws Exception{
		Connection con = connectionPool.get();
		if(con!=null){
			con.rollback();
		}
	}
	
	public static void closeAll(Connection con,PreparedStatement pst,ResultSet rs)throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(con!=null){
			con.close();
		}
	}
}
