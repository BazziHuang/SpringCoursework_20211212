package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception;

import java.util.InputMismatchException;

public class PersonFormatException extends InputMismatchException{
	
	public PersonFormatException(String message) {
		super(message);
	}
	
	public String toString() {
		return getMessage();
	}

}
