package cn.jxau.service;

import java.util.List;

import cn.jxau.entity.Subject;
import cn.jxau.entity.User;

public interface SubjectService {
	public void add(Subject subject,User user) throws Exception;
	public List getSubjects() throws Exception;
	public Subject getSubject(int id) throws Exception;
	public void modify(Subject subject, User attribute) throws Exception;
}
