package pers.tzuchiehhuang.coursework.SpringmvcCoursework_20220213.coursework_5.case03.entity;

public class ExamSlot {
	
	private String slot;
	private String id;
	
	public ExamSlot() {
		
	}
	
	public ExamSlot(String slot, String id) {
		this.slot = slot;
		this.id = id;
	}

	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ExamSlot [slot=" + slot + ", id=" + id + "]";
	}
	
	

}

