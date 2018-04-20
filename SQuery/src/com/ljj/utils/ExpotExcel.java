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
	     * @功能：手工构建一个简单格式的Excel 
	     */  	  
	    public static void expotExcel(List list,String path)  
	    {  
	        // 第一步，创建一个webbook，对应一个Excel文件  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet("学生表一");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        sheet.setDefaultColumnWidth((short) 15);//设置默认的列宽	        
	        row.setHeight((short) 400);
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
	        style.setWrapText(true);// 指定单元格自动换行
	        // 设置这些样式
			style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 生成一个字体
			HSSFFont font = wb.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// 把字体应用到当前的样式
			style.setFont(font);
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("学号");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("姓名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("班级");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("学分绩点");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 4);  
	        cell.setCellValue("平均成绩");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 5);  
	        cell.setCellValue("平均学分绩点");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 6);  
	        cell.setCellValue("状态");  
	        cell.setCellStyle(style); 
	        String s=""; 
	        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，    
	        for (int i = 0; i < list.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 1); 
	            Student stu = (Student) list.get(i);  
	            // 第四步，创建单元格，并设置值  
	            row.createCell((short) 0).setCellValue(stu.getSnum());  
	            row.createCell((short) 1).setCellValue(stu.getSname());  
	            row.createCell((short) 2).setCellValue(stu.getSclass());  	            
	            //row.createCell((short)3).setCellValue(stu.getTotalScore());
	            row.createCell((short)3).setCellValue(Math.round(stu.getTotalPoint()*100)*0.01d);
	            row.createCell((short)4).setCellValue(Math.round(stu.getAvgScore()*100)*0.01d);
	            row.createCell((short)5).setCellValue(Math.round(stu.getAvgPoint()*100)*0.01d);	          
	            if(stu.getState()==0)
	            	s="未确定";
	            else if(stu.getState()==1)
	            	s="已确定";
	            else if(stu.getState()==2)
	            	s="有问题";
	            row.createCell((short)6).setCellValue(s);
	        }  
	        // 第六步，将文件存到指定位置  
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


