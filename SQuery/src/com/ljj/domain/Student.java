package com.ljj.domain;

public class Student {
   private  String snum;
   private  String sname;
   private  String sclass;
   private  Integer totalScore;
   private  double totalPoint;
   private  double avgScore;
   private  double  avgPoint;
   private  Integer state;

public String getSnum() {
	return snum;
}
public void setSnum(String snum) {
	this.snum = snum;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getSclass() {
	return sclass;
}
public void setSclass(String sclass) {
	this.sclass = sclass;
}
public Integer getTotalScore() {
	return totalScore;
}
public void setTotalScore(Integer totalScore) {
	this.totalScore = totalScore;
}
public double getTotalPoint() {
	return totalPoint;
}
public void setTotalPoint(double totalPoint) {
	this.totalPoint = totalPoint;
}
public double getAvgScore() {
	return avgScore;
}
public void setAvgScore(double avgScore) {
	this.avgScore = avgScore;
}
public double getAvgPoint() {
	return avgPoint;
}
public void setAvgPoint(double avgPoint) {
	this.avgPoint = avgPoint;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
  
}
