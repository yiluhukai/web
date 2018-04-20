package com.ljj.admin.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ljj.domain.Admin;
import com.ljj.domain.Student;


public class AdminDao extends HibernateDaoSupport
{
    /*
     * 查询用户是否存在
     */
	public Admin findAdmin(Admin admin) 
	{
		String hql="from Admin where aname=? and apwd=?";
	    List<Admin> ad=this.getHibernateTemplate().find(hql,admin.getAname(),admin.getApwd());
	    if(ad!=null&&ad.size()>0)
	       return ad.get(0);
	    else
	       return null; 
	}
    /*
     * 查询学生对象
     */
	public Student queryStu(String snum) {
	   Student s=this.getHibernateTemplate().get(Student.class,snum);
	   if(s!=null)
		   return s;
	   return null;
	}
	/*
	 * 保存学生对象
	 */
	public void saveStu(Student s) {
	   this.getHibernateTemplate().save(s);
	}
	/*
	 * 查询学生对象
	 */
	public List<Student> queryStu() {
		String hql="from Student order by avgPoint desc";
		List<Student> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0)
			return  list;
		return null;
	}
	/*
	 * 更新学生对象
	 */
	public void updateStu(Student s1) 
	{
	   this.getHibernateTemplate().update(s1);		
	}
	/*
	 * 查询学生
	 */
	public List<Student> queryStu(Integer category) {
		String hql="from Student where state=? order by avgPoint desc";
		List<Student> list=this.getHibernateTemplate().find(hql,category);
		if(list!=null&&list.size()>0)
			return  list;
		return null;
	}
}
