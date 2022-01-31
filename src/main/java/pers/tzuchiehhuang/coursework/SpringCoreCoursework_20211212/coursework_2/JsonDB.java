package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonFormatException;

@Component
public class JsonDB {

	@Autowired
	private Gson gson;
	private static final Path PATH = Paths.get("src/main/java/pers/tzuchiehhuang/coursework/SpringCoreCoursework_20211212/coursework_2/person.json");

	
	/**
	 * Get a list contain all person data from person.json
	 * @return List<Person>
	 * @throws Exception
	 */
	public List<Person> queryAll() throws Exception {

		String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type = new TypeToken<ArrayList<Person>>() {
		}.getType();
		List<Person> people = gson.fromJson(jsonStr, type);

		// Calculate Person's age
		LocalDate today = LocalDate.now();
		for (Person p : people) {
			LocalDate bitrhday = getBirthLocalDate(p.getBirth());
			p.setAge(Period.between(bitrhday, today).getYears());
		}
		// Calculate Person's age
		
		return people;
	}
	

	/**
	 * Add a new person data.
	 * @param person
	 * @return true if add success, otherwise return false.
	 * @throws Exception
	 */
	public boolean add(Person person) throws Exception {
		List<Person> people = queryAll();
	
		//****************************  Homework 1  *********************************
		/*
		 * The method isSamePerson(Person p1, Person p2) will check if p1 and p2 have same name
		 * and same birthday (ignore time detail).
		 */
		boolean samePerson = people.stream().anyMatch(p -> isSamePerson(p, person));

		if (samePerson) {
			return false;
		}
		if(person.getName()==null || person.getBirth()==null) {
			throw new NullPointerException();
		}
		people.add(person);
		writeJson(people);
		return true;
	}
	
	/**
	 * ******************************  Homework 1  **********************************
	 * Check two Person whether have same name and same birthday (ignore time detail).
	 * 
	 * @param Person p1
	 * @param Person p2
	 * @return true if p1 and p2 have same name and same birth.
	 */
	private boolean isSamePerson(Person p1, Person p2) {
		if (!p1.getName().equalsIgnoreCase(p2.getName()))
			return false;
		if (!getBirthLocalDate(p1.getBirth()).equals(getBirthLocalDate(p2.getBirth())))
			return false;
		return true;
	}

	/**
	 * Write new List<Person> into person.json file.
	 * 
	 * @param List<Person> people
	 * @throws IOException
	 */
	public void writeJson(List<Person> people) throws Exception {
		String newJsonStr = gson.toJson(people);
		Files.write(PATH, newJsonStr.getBytes("UTF-8"));
	}

	/**
	 * Delete person from person.json
	 * @param person
	 * @return true if person exists and delete success, otherwise return false.
	 * @throws Exception
	 */
	public boolean delete(Person person) throws Exception {
		List<Person> people = queryAll();
		boolean check = people.removeIf(p -> isSamePerson(p, person));
		if (check)
			writeJson(people);
		return check;
	}

	/**
	 * Update person's birthday.
	 * @param person
	 * @param date
	 * @return If person not exists will return false.
	 * @throws Exception
	 */
	public boolean updateBirth(Person person, Date date) throws Exception {
		if(!delete(person))
			return false;
		if(date==null) {
			throw new PersonFormatException("Birthdate can not be null!");
		}
		List<Person> people = queryAll();
		person.setBirth(date);
		people.add(person);
		writeJson(people);
		return true;
	}

	/**
	 * Convert getBith() data type from Date to LocalDate
	 * 
	 * @param birth from Person.getBirth()
	 * @return birth as LocalDate type
	 */
	private LocalDate getBirthLocalDate(Date birth) {
		return birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
