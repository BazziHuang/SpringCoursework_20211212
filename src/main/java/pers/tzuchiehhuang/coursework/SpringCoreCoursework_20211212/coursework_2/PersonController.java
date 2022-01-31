package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.MutiplePersonsException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonFormatException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.Exception.PersonNotFoundException;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	// 1-1
	public boolean addPerson(String name, int year, int month, int day) throws InputMismatchException{
		Date birth = getBirthDate(year, month, day);
		return addPerson(name, birth);
	}

	// 1-2
	public boolean addPerson(String name, Date birth) throws InputMismatchException{
		Person person = new Person();
		String newName= nameFormatter(name);
		birthFomatCheck(birth);
		person.setName(newName);
		person.setBirth(birth);
		return addPerson(person);
	}

	// 1-3
	public boolean addPerson(Person person) throws InputMismatchException{
		return personService.append(person);
	}

	// 2
	public void printAllPersons() {
		List<Person> people = personService.findAllPersons();
		System.out.println("+-----------------------------------------------+");
		System.out.println("|        name        |   age   |    birthday    |");
		System.out.println("+--------------------+---------+----------------+");

		for (Person p : people) {
			LocalDate birthday = p.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.printf("| %-18s | %4d    | %12s   |\n", p.getName(), p.getAge(), birthday);
			System.out.println("+--------------------+---------+----------------+");
		}
	}

	// 3
	public List<Person> getPersonsByName(String name) {
		return personService.getPerson(name);
		// printInfo(people, name + " is not exists!");
	}

	// 4-1
	public List<Person> getPersonsByBirth(int month, int day) {
		Date birth = getBirthDate(2000, month, day);
		return getPersonsByBirth(birth);
	}

	// 4-2
	public List<Person> getPersonsByBirth(Date birth) {
		return personService.getPerson(birth);
		// printInfo(people, "No one birth at this day!");
	}

	// 5
	/**
	 * This method will find person whose age older or younger than entering age.
	 * option greater than zero means find older, on the other hand find younger.
	 * If no entering option or entering zero will find equal-age.
	 * @param age
	 * @param options
	 * @return
	 */
	public List<Person> getPersonsByAge(int age, int options) {
		return personService.getPerson(age, options);
	}

	public List<Person> getPersonsByAge(int age) {
		return personService.getPerson(age, 0);
	}

	// 6-1
	/**
	 * If there are more than one person with this name, this method will do
	 * nothing.
	 * @param name
	 * @param year
	 * @param month
	 * @param day
	 */
	public boolean updatePersonBirthday(String name, int year, int month, int day) throws PersonNotFoundException, MutiplePersonsException{
		List<Person> persons = personService.getPerson(name);
		if (persons.size() > 1) {
			throw new MutiplePersonsException("More then one persons named "+ name);
		}
		if (persons.isEmpty()) {
			throw new PersonNotFoundException(name+" not exists!");
		}
		Person person = persons.get(0);
		Date newBirth = getBirthDate(year, month, day);
		birthFomatCheck(newBirth);
		return personService.updateBirthDay(person, newBirth);

	}

	// 6-2
	public boolean updatePersonBirthday(String name, int oldYear, int oldMonth, int oldDay, int newYear, int newMonth,
			int newDay) {
		Person person = new Person();
		Date oldBirth = getBirthDate(oldYear, oldMonth, oldDay);
		person.setName(name);
		person.setBirth(oldBirth);
		return updatePersonBirthday(person, newYear, newMonth, newDay);
	}

	// 6-3
	public boolean updatePersonBirthday(Person person, int year, int month, int day) throws InputMismatchException{
		Date newBirth = getBirthDate(year, month, day);
		birthFomatCheck(newBirth);
		return personService.updateBirthDay(person, newBirth);
	}

	// 7-1
	/**
	 * This method will delete all person who with this name.
	 * 
	 * @param name
	 */
	public boolean deleteAllPersonsByName(String name) throws PersonNotFoundException {
		List<Person> people = personService.findAllPersons();
		if (people.removeIf(p -> p.getName().equalsIgnoreCase(name))) {
			return personService.writePersons(people);
		}
		throw new PersonNotFoundException(name +" not exists!");
	}
	
	//7-2
	public boolean deletePerson(String name) throws MutiplePersonsException, PersonNotFoundException{
		List<Person> persons = personService.getPerson(name);
		if (persons.size() > 1) {
			throw new MutiplePersonsException("More then one persons named "+ name);
		}
		if (persons.isEmpty()) {
			throw new PersonNotFoundException(name+" not exists!");
		}
		List<Person> people= personService.findAllPersons();
		people.removeIf(p -> p.getName().equalsIgnoreCase(name));
		return personService.writePersons(people);
	}

	// 7-3
	public boolean deletePerson(String name, int year, int month, int day) {
		Date birth = getBirthDate(year, month, day);
		return deletePerson(name, birth);
	}

	// 7-4
	public boolean deletePerson(String name, Date birth) {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		return deletePerson(person);
	}

	// 7-5
	public boolean deletePerson(Person person) {
		return personService.deletePerson(person);
	}
	
	/**
	 * Convert the entering data to Date type.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private Date getBirthDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}
	
	/**
	 * Check the entering birth date is valid.
	 * @param birth
	 * @throws PersonFormatException if the date after now-time, or before 1900.
	 */
	private void birthFomatCheck(Date birth) throws PersonFormatException{
		Date now= new Date();
		if(birth.after(now)) {
			throw new PersonFormatException("Entering birthday is after now time, please check!");
		}
		LocalDate localDate = birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year= localDate.get(ChronoField.YEAR);
		int month= localDate.get(ChronoField.MONTH_OF_YEAR);
		int day= localDate.get(ChronoField.DAY_OF_MONTH);
		if(year<1900)
			throw new PersonFormatException("The person was born before 1900, please check!");
		if(month<=0 || month>12 || day<=0 || day>31) {
			throw new PersonFormatException("Birthday format not correct, please check!");
		}
		
	}
	
	/**
	 * Check the entering person name whether contains special characters, and revise the name first letter to uppercase.
	 * @param name
	 * @return The name witch fist-letter is uppercase. 
	 * @throws PersonFormatException if contains special characters.
	 */
	private String nameFormatter(String name) throws PersonFormatException{
		final String SPECIAL= "!\"#$%&'()*+,-./0123456789:;<=>?@[\\]^_`{|}~";
		String newName= name.trim();
		for(int i=0; i<SPECIAL.length(); i++) {
			if(newName.contains(SPECIAL.substring(i,i+1))) {
				throw new PersonFormatException("The person's name contains special characters, adding person fail! Please check!");
			}
		}
		newName= newName.substring(0,1).toUpperCase()+newName.substring(1);
		for(int i=0;;i++) {
			i= newName.indexOf(" ", i);
			if(i==-1)
				break;
			newName= newName.substring(0,i+1)+newName.substring(i+1,i+2).toUpperCase()+newName.substring(i+2);
		}
		return newName;
	}
}
