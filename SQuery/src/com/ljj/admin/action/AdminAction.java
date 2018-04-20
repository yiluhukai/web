package com.ljj.admin.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import org.apache.struts2.ServletActionContext;

import com.ljj.admin.service.AdminService;
import com.ljj.domain.Admin;
import com.ljj.domain.Student;
import com.ljj.utils.ExpotExcel;
import com.ljj.utils.ImportExecl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import sun.misc.BASE64Encoder;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>
{
	//管理员对象
    private Admin admin=new Admin();   
	public Admin getModel() {
		return admin;
	}
	//导出学生得类型
	private Integer category;	
	public void setCategory(Integer category) {
		this.category = category;
	}
	//文件的路径和文件名
    private  File athlete;
    private String athleteFileName;
	public String getAthleteFileName() throws UnsupportedEncodingException {
		  return EncdFileName(athleteFileName,ServletActionContext.getRequest().getHeader("User-Agent"));
	}
	//文件下载得类型
	public String getContenType() {
		return ServletActionContext.getServletContext().getMimeType(athleteFileName) ;
	}
	public void setContenType(String contenType) {
		this.contenType = contenType;
	}
	public String contenType;
	
	public Integer getCategory() {
		return category;
	}
	public void setAthlete(File athlete) {
		this.athlete = athlete;
	}
	public void setAthleteFileName(String athleteFileName) {
		this.athleteFileName = athleteFileName;
	}
	//注入业务层
	private AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public InputStream getDownloadFile()
	{
		return ServletActionContext.getServletContext().getResourceAsStream("/temp/students.xls");
	}	
	/*
	 * 跳转到管理员页面
	 */
	public String execute()
	{
		return "login";
	}
	/*
	 * 管理员登陆
	 */
   public String adminLogin()
   {   
	   //查询用户名是否存在
	   Admin existAdmin=adminService.findAdmin(admin);
	   if(existAdmin==null)
	   {
		   this.addActionError("用户名或密码不正确！");
		   return INPUT;
	   }
	   else
	   {
		   ActionContext.getContext().getSession().put("admin",existAdmin);
		   return SUCCESS;
	   }
	   
   }
   /*
    * 导入页面
    */
  public String importMessagePage()
  {
	 return   "importMessagePage";
  }
  /*
   * 导入比赛成绩
   */
  public String importData()
  {
	  ImportExecl poi = new ImportExecl();
      //从excel中读书数据
	    if(athlete==null)
	    { 
	    	ActionContext.getContext().getSession().put("error","文件不存在");
	    	return "importerror";
	    }
	    else
	    {
		    List<List<String>> list = poi.read(athlete.toString(),athleteFileName);
		    if (list != null)
		    {
			 //解除字段的位置限制
			  List<String> cellList = list.get(0);
			  int index=0;int arr[]=new int[7];
			  for(String li:cellList)
			  {
				String str=li.replace("\n","");
				if(str.equals("姓名"))
					arr[0]=index++;			
				else if(str.equals("总成绩"))
				    arr[1]=index++;
				else if(str.equals("平均成绩"))
				    arr[2]=index++;
				else if(str.equals("平均学分绩点"))
				    arr[3]=index++;
				else if(str.equals("学分绩点"))
				    arr[4]=index++;
				else if(str.equals("学号"))
				    arr[5]=index++;
				else if(str.equals("行政班级"))
				    arr[6]=index++;
				else
					index++;
			  }
			//从第二行开始读取
			 for (int i = 1; i < list.size()-1; i++)
			 {	
				cellList = list.get(i);	
				Student s=adminService.queryStu(cellList.get(arr[5]));
				if(s==null)
				{
				 s=new Student();
				s.setSname(cellList.get(arr[0]));
				s.setSnum(cellList.get(arr[5]));
				s.setSclass(cellList.get(arr[6]));
				s.setState(0);
				s.setAvgPoint(Double.parseDouble(cellList.get(arr[3])));
				s.setAvgScore(Double.parseDouble(cellList.get(arr[2])));
                s.setTotalPoint(Double.parseDouble(cellList.get(arr[4])));
               // s.setTotalScore(Integer.parseInt(cellList.get(arr[1]).split("\\.")[0]));
			    s.setTotalScore(0);
                adminService.saveStu(s);
				}
			 }
		   }
		   return "import";	
	    }	
  }
  /*
   * 跳转到分类
   */
  public String  exportMessagePage()
  {	 
	  return "exportMessagePage";
  }
  /*
   * 下载excel
   */
  public String exportData()
  {    
	  List<Student> list;
	  if(category==4)
	  {
	  list=adminService.queryStu();
	  }
	  else 
	  {
		  list=adminService.queryStu(category);
	  }
	  if(list!=null)
	  {
	  String uploadPath=ServletActionContext.getServletContext().getRealPath("/temp");
	  ExpotExcel.expotExcel(list,uploadPath); 
	  }
	  System.out.println(athleteFileName);
	  return "exportData";
  }
  
  public String EncdFileName(String name,String agent) throws UnsupportedEncodingException
  {
	  if(agent.contains("FireFox"))
		  name="?UTF-8?B"+new BASE64Encoder().encode(name.getBytes("TTF-8"))+"?=";
	  else
		  name=URLEncoder.encode(name,"UTF-8");
	  return name;
  }
  public String scorequery()
  {
	  return "scorequery";
  }
}
