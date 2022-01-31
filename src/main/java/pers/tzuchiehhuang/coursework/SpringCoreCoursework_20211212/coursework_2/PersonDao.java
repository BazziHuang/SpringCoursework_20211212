package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Date;
import java.util.List;

public interface PersonDao {
	
	boolean create(Person person);
	
	public List<Person> readAll();
	
	boolean write(List<Person> people);
	
	boolean remove(Person person);
	
	boolean update(Person person, Date newBirth);
	
}
