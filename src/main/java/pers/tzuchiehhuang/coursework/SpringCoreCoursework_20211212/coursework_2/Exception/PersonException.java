package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception;

public class PersonException extends Exception{
	
	public PersonException(String message) {
		super(message);
	}
	
	public String toString() {
		return getMessage();
	}

}
