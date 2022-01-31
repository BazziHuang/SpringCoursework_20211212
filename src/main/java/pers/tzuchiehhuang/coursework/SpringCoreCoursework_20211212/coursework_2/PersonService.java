package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Date;
import java.util.List;

public interface PersonService {
	
	boolean append(Person person);
	List<Person> findAllPersons();
	List<Person> getPerson(String name);
	List<Person> getPerson(Date birth);
	List<Person> getPerson(int age, int options);
	boolean writePersons(List<Person> people);
	boolean deletePerson(Person person);
	boolean updateBirthDay(Person person, Date birth);
	

}
