package cn.jxau.pojo;

import  cn.jxau.util.base.BaseQueryModel;

/**
 * 查询条件值的子类：继承对应的实体类，
 * 实现BaseQueryModel接口
 * @author hxp
 * 2018年11月29日 上午8:44:11
 */
public class UserQueryModel extends User implements BaseQueryModel{
    //有特殊的查询条件值，则增加属性
}
