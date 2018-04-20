package com.ljj.admin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ljj.admin.dao.AdminDao;
import com.ljj.domain.Admin;
import com.ljj.domain.Student;

@Transactional
public class AdminService {
   //注入dao
	private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
   /*
    * 查询用户是否存在
    */
	public Admin findAdmin(Admin admin) {
	
		return adminDao.findAdmin(admin);
	}
	/*
	 * 根据id查询学生
	 */
	public Student queryStu(String snum) 
	{
		return adminDao.queryStu(snum);
	}
	/*
	 * 保存学生对象
	 */
	public void saveStu(Student s) 
	{
	   adminDao.saveStu(s);
	}
	/*
	 * 查询学生对象
	 */
			
	public List<Student> queryStu() {
		
		return adminDao.queryStu();
	}
	/*
	 * 更新学生对象
	 */
	public void updateStu(Student s1) 
	{
		adminDao.updateStu(s1);
	}
	/*
	 * 查询学生
	 */
	public List<Student> queryStu(Integer category) {
	
		return adminDao.queryStu(category);
	}	
}
