package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_6.case03.entity;

public class ExamPay {
	
	private String name;
	private Boolean isPaid;
	
	public ExamPay() {
	}

	public ExamPay(String name, Boolean isPaid) {
		this.name = name;
		this.isPaid = isPaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Override
	public String toString() {
		return "ExamPay [name=" + name + ", isPaid=" + isPaid + "]";
	}
	

}
