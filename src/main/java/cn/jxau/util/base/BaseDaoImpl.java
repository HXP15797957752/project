package cn.jxau.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.jxau.util.dao.DbHelper;

/**
 * 数据访问的父类，实现父接口中的增删改查的方法
 * 
 * @author Ma Wenhai
 *
 */
public abstract class BaseDaoImpl implements BaseDao {

    @Override
    public int insert(Object data) throws Exception {
        // JDBC
        // 1 创建数据库连接对象Connection
        Connection con = DbHelper.getConnection();
        // 2 编写sql语句，创建命令对象PreparedStatement
        String sql = getInsertSql(data);
        PreparedStatement pst = con.prepareStatement(sql);

        // 3 执行命令：executeUpdate ,executeQuery
        int rows = pst.executeUpdate();
        
        // 4 处理执行的结果：ResultSet结果集
        // 结果集中的记录转换成实体类对象
        // 5 释放资源(关闭连接)
        DbHelper.closeAll(con, pst, null);
        return rows;
    }

    @Override
    public int update(Object data) throws Exception {
        // 1 创建数据库连接对象Connection
        return 0;
    }

    @Override
    public int delete(int id) throws Exception {
        // 1 创建数据库连接对象Connection
        return 0;
    }

    public List findAll() throws Exception {
        // 1 创建数据库连接对象Connection
        Connection con = DbHelper.getConnection();
        // 2 编写sql语句，创建命令对象PreparedStatement
        String sql = getFindAllSql();
        PreparedStatement pst = con.prepareStatement(sql);

        // 3 执行命令：executeUpdate ,executeQuery
        ResultSet rs = pst.executeQuery();
        // 4 处理执行的结果：ResultSet结果集
        // 结果集中的记录转换成实体类对象
        List list = new ArrayList();
        while (rs.next()) {
            // 将一条记录转换成java对象
            Object data = rsToObject(rs);
            // 将对象加入到集合中
            list.add(data);
        }

        // 5 释放资源(关闭连接)
        DbHelper.closeAll(con, pst, rs);

        return list;
    }

    // 根据id主键查一个对象
    public Object findOne(int id) throws Exception {
        return null;
    }

    // 按照条件来查所有满足条件的记录
    // 构造条件的数据：一般是属于某些属性的值；实体类
    // UserQueryModel SubjectQueryModel
    public List findByCondition(BaseQueryModel queryModel) throws Exception {
        // 1 创建数据库连接对象Connection
        Connection con = DbHelper.getConnection();
        // 2 编写sql语句，创建命令对象PreparedStatement
        String sql = getFindConditionSql(queryModel);
        PreparedStatement pst = con.prepareStatement(sql);

        // 3 执行命令：executeUpdate ,executeQuery
        ResultSet rs = pst.executeQuery();
        // 4 处理执行的结果：ResultSet结果集
        // 结果集中的记录转换成实体类对象
        List list = new ArrayList();
        while (rs.next()) {
            // 将一条记录转换成java对象
            Object data = rsToObject(rs);
            // 将对象加入到集合中
            list.add(data);
        }

        // 5 释放资源(关闭连接)
        DbHelper.closeAll(con, pst, rs);

        return list;
    }

    // 产生sql语句的抽象方法，留给子类类实现
    public abstract String getInsertSql(Object data);

    public abstract  String getFindAllSql();
    public abstract String getFindConditionSql(BaseQueryModel queryModel);
    
    // 将结果集中的一条记录转换成一个JAva对象
    public abstract Object rsToObject(ResultSet rs) throws Exception;
}

