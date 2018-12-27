package cn.jxau.service.impl;

import java.util.Date;
import java.util.List;

import cn.jxau.core.exception.ReTryException;
import cn.jxau.dao.OptionDao;
import cn.jxau.dao.SubjectDao;
import cn.jxau.dao.impl.OptionDaoImpl;
import cn.jxau.dao.impl.SubjectDaoImpl;
import cn.jxau.entity.Option;
import cn.jxau.entity.OptionQueryModel;
import cn.jxau.entity.Subject;
import cn.jxau.entity.SubjectQueryModel;
import cn.jxau.entity.User;
import cn.jxau.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{
	private SubjectDao subjectDao;
	private OptionDao optionDao;
	public SubjectServiceImpl(){
		this.subjectDao=new SubjectDaoImpl();
		this.optionDao = new OptionDaoImpl();
	}
	
	@Override
	public void add(Subject subject,User user) throws Exception {
		//�����Ŀ����ı���
		if(subject.getTitle()==null || subject.getTitle().trim().length()==0){
			throw new ReTryException("��Ŀ����ı��ⲻ��Ϊ��");
		}
		
		//������ѡ�������
		for(Object data:subject.getOptions()){
			Option option = (Option)data;
			if(option.getContent()==null || option.getContent().trim().length()==0){
				throw new ReTryException("ÿ����Ŀѡ������ݲ���Ϊ��");
			}
		}
		//��Ŀѡ��������ܵ���2��
		if(subject.getOptions().size()<2){
			throw new ReTryException("ѡ���������ܵ���2");
		}
		
		//����ѡ������ݲ�һ��
		for(int i=0;i<subject.getOptions().size();i++){
			Option first = (Option)subject.getOptions().get(i);
			for(int j=i+1;j<subject.getOptions().size();j++){
				Option next = (Option)subject.getOptions().get(j);
				if(first.getContent().equals(next.getContent())){
					throw new ReTryException("ÿ��ѡ������ݲ���һ��");
				}
			}
		}
		//ͬһ�������˲��ܷ���ͬ�������ͶƱ��Ŀ
		SubjectQueryModel queryModel=new SubjectQueryModel();
		queryModel.setTitle(subject.getTitle());
		queryModel.getMaster().setId(user.getId());
		List list = subjectDao.getByCondition(queryModel);
		if(list!=null && list.size()>0){
			throw new ReTryException("ͬһ�������˲��ܷ���ͬ�������ͶƱ��Ŀ");
		}
		
		//���ÿ�ʼʱ�䣬����ʱ��ͷ�����
		long startTime=new Date().getTime();
		subject.setStartTime(startTime);
		subject.setEndTime(startTime+1*24*60*60*1000);
		subject.setMaster(user);
		//��������
		subjectDao.insert(subject);
		int subjectId = subjectDao.getGenerateId();
		for(int i=0;i<subject.getOptions().size();i++){
			Option op = (Option)subject.getOptions().get(i);
			op.setIndex(i+1);
			op.setSubjectId(subjectId);
			optionDao.insert(op);
		}
	}

	@Override
	public List getSubjects() throws Exception {
		List list = subjectDao.getAll();
		if(list!=null && list.size()>0){
			for(Object data:list){
				Subject subject = (Subject)data;
				OptionQueryModel queryModel = new OptionQueryModel();
				queryModel.setSubjectId(subject.getId());
				subject.setOptions(optionDao.getByCondition(queryModel));
				subject.setUserCount(subjectDao.getUserCount(subject.getId()));
			}
		}
		return list;
	}

	@Override
	public Subject getSubject(int id) throws Exception {
		Subject subject = (Subject)subjectDao.getModel(id);
		if(subject!=null){			
			OptionQueryModel queryModel = new OptionQueryModel();
			queryModel.setSubjectId(subject.getId());
			subject.setOptions(optionDao.getByCondition(queryModel));
			subject.setUserCount(subjectDao.getUserCount(subject.getId()));
		}
		return subject;
	}

	@Override
	public void modify(Subject subject, User attribute) throws Exception {
		//�Ѿ�����ͶƱ��¼������������޸�
		if(subjectDao.getUserCount(subject.getId())>0){
			throw new Exception("�Ѿ�����ͶƱ��¼���������޸�");
		}
		//��ʼ�޸�
		subjectDao.update(subject);
		optionDao.deleteOptions(subject.getId());
		for(int i=0;i<subject.getOptions().size();i++){
			Option op = (Option)subject.getOptions().get(i);
			op.setIndex(i+1);
			op.setSubjectId(subject.getId());
			optionDao.insert(op);
		}
	}
}
