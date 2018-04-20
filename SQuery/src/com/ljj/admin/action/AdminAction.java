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
	//����Ա����
    private Admin admin=new Admin();   
	public Admin getModel() {
		return admin;
	}
	//����ѧ��������
	private Integer category;	
	public void setCategory(Integer category) {
		this.category = category;
	}
	//�ļ���·�����ļ���
    private  File athlete;
    private String athleteFileName;
	public String getAthleteFileName() throws UnsupportedEncodingException {
		  return EncdFileName(athleteFileName,ServletActionContext.getRequest().getHeader("User-Agent"));
	}
	//�ļ����ص�����
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
	//ע��ҵ���
	private AdminService adminService;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public InputStream getDownloadFile()
	{
		return ServletActionContext.getServletContext().getResourceAsStream("/temp/students.xls");
	}	
	/*
	 * ��ת������Աҳ��
	 */
	public String execute()
	{
		return "login";
	}
	/*
	 * ����Ա��½
	 */
   public String adminLogin()
   {   
	   //��ѯ�û����Ƿ����
	   Admin existAdmin=adminService.findAdmin(admin);
	   if(existAdmin==null)
	   {
		   this.addActionError("�û��������벻��ȷ��");
		   return INPUT;
	   }
	   else
	   {
		   ActionContext.getContext().getSession().put("admin",existAdmin);
		   return SUCCESS;
	   }
	   
   }
   /*
    * ����ҳ��
    */
  public String importMessagePage()
  {
	 return   "importMessagePage";
  }
  /*
   * ��������ɼ�
   */
  public String importData()
  {
	  ImportExecl poi = new ImportExecl();
      //��excel�ж�������
	    if(athlete==null)
	    { 
	    	ActionContext.getContext().getSession().put("error","�ļ�������");
	    	return "importerror";
	    }
	    else
	    {
		    List<List<String>> list = poi.read(athlete.toString(),athleteFileName);
		    if (list != null)
		    {
			 //����ֶε�λ������
			  List<String> cellList = list.get(0);
			  int index=0;int arr[]=new int[7];
			  for(String li:cellList)
			  {
				String str=li.replace("\n","");
				if(str.equals("����"))
					arr[0]=index++;			
				else if(str.equals("�ܳɼ�"))
				    arr[1]=index++;
				else if(str.equals("ƽ���ɼ�"))
				    arr[2]=index++;
				else if(str.equals("ƽ��ѧ�ּ���"))
				    arr[3]=index++;
				else if(str.equals("ѧ�ּ���"))
				    arr[4]=index++;
				else if(str.equals("ѧ��"))
				    arr[5]=index++;
				else if(str.equals("�����༶"))
				    arr[6]=index++;
				else
					index++;
			  }
			//�ӵڶ��п�ʼ��ȡ
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
   * ��ת������
   */
  public String  exportMessagePage()
  {	 
	  return "exportMessagePage";
  }
  /*
   * ����excel
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
