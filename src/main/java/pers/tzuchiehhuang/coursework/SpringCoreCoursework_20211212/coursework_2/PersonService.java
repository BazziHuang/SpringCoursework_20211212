package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Date;
import java.util.List;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity.Person;

public interface PersonService {
	
	boolean append(Person person);
	List<Person> findAllPersons();
	boolean havePerson(Person person);
	List<Person> getPerson(String name);
	List<Person> getPerson(Date birth);
	List<Person> getPerson(Integer age, AgeOptions option);
	boolean writePersons(List<Person> people);
	boolean deletePerson(Person person);
	boolean updateBirthDay(Person person, Date birth);
	

}
