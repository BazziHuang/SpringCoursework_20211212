package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception;

public class MutiplePersonsException extends Exception {
	
	public MutiplePersonsException(String message) {
		super(message);
	}
	
	public String toString() {
		return getMessage();
	}


}
