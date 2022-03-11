package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Exam {
	
	private String studentId;   //student id
	private String examId;   //exam id
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  //return date format
	@DateTimeFormat(pattern = "yyyy-MM-dd") //receive date type
	private Date examDate;
	private String[] examSlot;   //exam time, can be multiple
	private Boolean examPay;  //true -> payment completed
	private String examNote;
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String[] getExamSlot() {
		return examSlot;
	}
	public void setExamSlot(String[] examSlot) {
		this.examSlot = examSlot;
	}
	public Boolean getExamPay() {
		return examPay;
	}
	public void setExamPay(Boolean examPay) {
		this.examPay = examPay;
	}
	public String getExamNote() {
		return examNote;
	}
	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}
	@Override
	public String toString() {
		return "Exam [studentId=" + studentId + ", examId=" + examId + ", examDate=" + examDate + ", examSlot="
				+ Arrays.toString(examSlot) + ", examPay=" + examPay + ", examNote=" + examNote + "]";
	}
	
}

