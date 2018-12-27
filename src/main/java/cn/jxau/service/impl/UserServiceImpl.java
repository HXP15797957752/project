package cn.jxau.service.impl;

import java.util.List;

import cn.jxau.core.exception.ReTryException;
import cn.jxau.core.format.Md5Class;
import cn.jxau.dao.UserDao;
import cn.jxau.dao.impl.UserDaoImpl;
import cn.jxau.entity.User;
import cn.jxau.entity.UserQueryModel;
import cn.jxau.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void register(User user) throws Exception {
		// �û�������Ϊ����֤
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new ReTryException("�û��˺Ų���Ϊ��");
		}
		// ��¼���벻��Ϊ�պͳ�������6λ��֤
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new ReTryException("��¼���벻��Ϊ�ջ������볤�Ȳ���6λ");
		}
		// ���������ȷ�����벻һ��
		if (!user.getPwd().equals(user.getConfirmPwd())) {
			throw new ReTryException("�����ȷ�����벻һ��");
		}
		// �û��˺Ų����ظ�
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		List list = userDao.getByCondition(queryModel);
		if (list.size() > 0) {
			throw new ReTryException("�û��˺��Ѿ���ע��");
		}

		// �û�������뾭������
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		// �û�����״̬Ϊ������
		user.setOnline(1);

		// �����û����ݵ����ݿ�
		userDao.insert(user);
	}

	@Override
	public User login(User user) throws Exception{
		// �û�������Ϊ����֤
		if (user.getName() == null || user.getName().trim().length() == 0) {
			throw new ReTryException("�û��˺Ų���Ϊ��");
		}
		// ��¼���벻��Ϊ�պͳ�������6λ��֤
		if (user.getPwd() == null || user.getPwd().trim().length() < 6) {
			throw new ReTryException("��¼���벻��Ϊ�ջ������볤�Ȳ���6λ");
		}
	
		//�����û����������ȡ�û�����
		User target = null;
		UserQueryModel queryModel = new UserQueryModel();
		queryModel.setName(user.getName());
		queryModel.setPwd(Md5Class.stringToMd5(user.getPwd()));
		List list = userDao.getByCondition(queryModel);
		if(list!=null && list.size()>0){
			//��������д���Ԫ�أ����ȡ���û�����
			target = (User)list.get(0);
			
			//����û��Ƿ��Ѿ�����
			if(target.getOnline()==2){
				throw new ReTryException("�û��Ѿ�����");
			}
			else{
				//��¼�ɹ����޸��û�����״̬
				target.setOnline(2);
				userDao.update(target);
			}
		}
		else{
			//������û���û��������¼ʧ��
			throw new ReTryException("�û��������ڻ����������");
		}
		
		return target;
	}

	@Override
	public User getUser(int id) throws Exception {
		User user = (User)userDao.getModel(id);
		return user;
	}

	@Override
	public void online(User user,boolean inOrOut) {		
		try {
			if(inOrOut){
				user.setOnline(2);
			}
			else{
			    user.setOnline(1);
			}
			
			userDao.update(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
