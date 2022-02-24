package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.entity;

public class ExamSubject {
	private String id;
	private String name;
	
	public ExamSubject() {
		
	}

	public ExamSubject(String id, String name) {
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "ExamSubject [id=" + id + ", name=" + name + "]";
	}
	
	
}
