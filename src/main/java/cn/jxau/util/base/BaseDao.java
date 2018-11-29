package cn.jxau.util.base;

import java.util.List;

/**
 *  数据访问类的父接口
 *   声明通用的增删改查的方法
 * @author hxp
 * 2018年11月28日 下午3:15:41
 */
public interface BaseDao {
    //增
    //int:新增返回值就是新增的行数
    //Object data:应用里氏代换原则
    public int insert(Object data) throws Exception;
    //数据中都有id，自动增长的，作为主键
    //新增是数据中没有id值，修改时，数据中必须有id值
    public int update(Object data) throws Exception;
    //按照id来删除
    public int delete(int id) throws Exception;
    
    //编写查询方法
    //查表中的所有记录
    public List findAll() throws Exception;
    //根据id主键查一个对象
    public Object findOne(int id) throws Exception;
    //按照条件来查所有满足条件的记录
    //构造条件的数据：一般是属于某些属性的值；实体类
    //UserQueryModel  SubjectQueryModel
    public List findByCondition(BaseQueryModel queryModel) throws Exception;
    
}
    