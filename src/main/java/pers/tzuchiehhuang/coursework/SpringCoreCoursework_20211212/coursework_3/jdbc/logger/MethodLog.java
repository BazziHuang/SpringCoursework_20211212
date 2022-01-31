package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.logger;

import java.util.Date;

public class MethodLog {
	private String method;
	private Date time;
	
	public MethodLog(String method, Date time) {
		this.method = method;
		this.time = time;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "MethodLog [method=" + method + ", time=" + time + "]";
	}
	

}
