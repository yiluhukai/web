package com.ljj.utils;
import java.io.FileOutputStream;
import java.rmi.server.ExportException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ljj.domain.Student;

public class ExpotExcel {
	    /** 
	     * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel 
	     */  	  
	    public static void expotExcel(List list,String path)  
	    {  
	        // ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
	        HSSFSheet sheet = wb.createSheet("ѧ����һ");  
	        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        sheet.setDefaultColumnWidth((short) 15);//����Ĭ�ϵ��п�	        
	        row.setHeight((short) 400);
	        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ָ����Ԫ��ֱ���ж���
	        style.setWrapText(true);// ָ����Ԫ���Զ�����
	        // ������Щ��ʽ
			style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// ����һ������
			HSSFFont font = wb.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// ������Ӧ�õ���ǰ����ʽ
			style.setFont(font);
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("ѧ��");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("����");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("�༶");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("ѧ�ּ���");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 4);  
	        cell.setCellValue("ƽ���ɼ�");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 5);  
	        cell.setCellValue("ƽ��ѧ�ּ���");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 6);  
	        cell.setCellValue("״̬");  
	        cell.setCellStyle(style); 
	        String s=""; 
	        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���    
	        for (int i = 0; i < list.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 1); 
	            Student stu = (Student) list.get(i);  
	            // ���Ĳ���������Ԫ�񣬲�����ֵ  
	            row.createCell((short) 0).setCellValue(stu.getSnum());  
	            row.createCell((short) 1).setCellValue(stu.getSname());  
	            row.createCell((short) 2).setCellValue(stu.getSclass());  	            
	            //row.createCell((short)3).setCellValue(stu.getTotalScore());
	            row.createCell((short)3).setCellValue(Math.round(stu.getTotalPoint()*100)*0.01d);
	            row.createCell((short)4).setCellValue(Math.round(stu.getAvgScore()*100)*0.01d);
	            row.createCell((short)5).setCellValue(Math.round(stu.getAvgPoint()*100)*0.01d);	          
	            if(stu.getState()==0)
	            	s="δȷ��";
	            else if(stu.getState()==1)
	            	s="��ȷ��";
	            else if(stu.getState()==2)
	            	s="������";
	            row.createCell((short)6).setCellValue(s);
	        }  
	        // �����������ļ��浽ָ��λ��  
	        try  
	        {  
	            FileOutputStream fout = new FileOutputStream(path+"/students.xls");  
	            wb.write(fout);  
	            fout.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	    } 
	     
}  


