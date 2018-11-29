package cn.jxau.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 * 1> 一个字段=>属性
 * 2> 给每个属性提供getter和setter访问器
 * 3> 考虑重载构造方法(一定要保留默认的构造方法)
 * @author hxp
 * 2018年11月29日 上午8:43:49
 */
public class User implements Serializable {
    //null代表id中没有值
    private Integer id;
    private String name;
    private String pwd;
    //1 不在线，2 在线
    private int online;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public int getOnline() {
        return online;
    }
    public void setOnline(int online) {
        this.online = online;
    }
    
    
}
