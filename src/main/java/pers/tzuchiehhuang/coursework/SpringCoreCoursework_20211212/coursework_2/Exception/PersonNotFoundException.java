package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception;

public class PersonNotFoundException extends Exception {
	
	
	public PersonNotFoundException(String message) {
		super(message);
	}
	
	public String toString() {
		return getMessage();
	}

}
