package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Exam {
	private String studentId; // 學員代號
	private String examId; // 考試代號
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  // 返回日期型態
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 接收日期類型
	private Date examDate; // 考試日期
	
	private String[] examSlot;// 考試時段
	private Boolean examPay; // 繳費狀況：true(已繳費)、false(未繳費)
	private String examNote; // 考況備註
	
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
	public String[] getExamSlot() {
		return examSlot;
	}
	public void setExamSlot(String[] examSlot) {
		this.examSlot = examSlot;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
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
		return "Exam [studentId=" + studentId + ", examId=" + examId + ", examSlot=" + Arrays.toString(examSlot)
				+ ", examDate=" + examDate + ", examPay=" + examPay + ", examNote=" + examNote + "]";
	}
	
	
}