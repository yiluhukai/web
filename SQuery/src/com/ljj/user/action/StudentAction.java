package com.ljj.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ljj.admin.service.AdminService;
import com.ljj.domain.Student;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class StudentAction extends ActionSupport implements ModelDriven<Student>
{
	private AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	private Student s=new Student();	
	public void setS(Student s) {
		this.s = s;
	}
	public Student getModel() {
		return s;
	}
	private Integer num;	
	public void setNum(Integer num) {
		this.num = num;
	}
	/*
	 * ajax请求
	 */
    public String queryPoint() throws IOException
    { 
    	//查询学生对象
    	Student s1=adminService.queryStu(s.getSnum());
    	 //取消SecondCategory类和其他属性关联是转化成json数据时产生的死循环
		JsonConfig config = new JsonConfig();	 
		config.setJsonPropertyFilter(new PropertyFilter(){	 
        public boolean apply(Object source, String name, Object value) 
        {	
        if(name.equals("category") || name.equals("products")) 
        {	
		            return true;		
	    } 
        else 
	    { 
	            return false;	
	    }	
	  }	 
	 });
		JSONArray jsonArray = JSONArray.fromObject(s1,config);
        HttpServletResponse response=ServletActionContext.getResponse();
 	    response.setContentType("text/html;charset=UTF-8");
 	    response.getWriter().println(jsonArray.toString());
    	
    	return NONE;
    }
    /**
     * ajax得确认和有问题
     * @return
     * @throws IOException
     */
   public String ensurePoint() throws IOException
   {
	   String message="";
	   Student s1=adminService.queryStu(s.getSnum());
	   if(num==1)
	   {
	      s1.setState(1);	      
	      message="确认完成";
	   }
	   else
	   {  
		   s1.setState(2);
		   message="错误信息已提交";
	   }
	   adminService.updateStu(s1);
	   HttpServletResponse response=ServletActionContext.getResponse();
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().println(message);
	   return NONE;
   }
	
}
