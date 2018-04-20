package com.ljj.admin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ljj.admin.dao.AdminDao;
import com.ljj.domain.Admin;
import com.ljj.domain.Student;

@Transactional
public class AdminService {
   //ע��dao
	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
   /*
    * ��ѯ�û��Ƿ����
    */
	public Admin findAdmin(Admin admin) {
	
		return adminDao.findAdmin(admin);
	}
	/*
	 * ����id��ѯѧ��
	 */
	public Student queryStu(String snum) 
	{
		return adminDao.queryStu(snum);
	}
	/*
	 * ����ѧ������
	 */
	public void saveStu(Student s) 
	{
	   adminDao.saveStu(s);
	}
	/*
	 * ��ѯѧ������
	 */
			
	public List<Student> queryStu() {
		
		return adminDao.queryStu();
	}
	/*
	 * ����ѧ������
	 */
	public void updateStu(Student s1) 
	{
		adminDao.updateStu(s1);
	}
	/*
	 * ��ѯѧ��
	 */
	public List<Student> queryStu(Integer category) {
	
		return adminDao.queryStu(category);
	}	
}
