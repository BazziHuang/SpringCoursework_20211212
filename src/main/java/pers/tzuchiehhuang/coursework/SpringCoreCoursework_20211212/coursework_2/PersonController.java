package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.LocalDate;
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
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	// 1-1 Create new person data.
	public boolean addPerson(String name, Integer year, Integer month, Integer day) throws PersonFormatException {
		Date birth = getBirthDate(year, month, day);
		return addPerson(name, birth);
	}

	// 1-2
	public boolean addPerson(String name, Date birth) throws PersonFormatException {
		if (name == null || name.trim().length() == 0) {
			throw new PersonFormatException("Input name can not be null, failing to create person data! Please check.");
		}
		Person person = new Person();
		String newName = nameFormatter(name);
		birthFomatCheck(birth);
		person.setName(newName);
		person.setBirth(birth);
		return personService.append(person);
	}

	// 2 Print all persons.
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

	// 3 Print person's detail by name.
	public List<Person> getPersonsByName(String name) throws PersonFormatException, PersonNotFoundException {
		if (name == null || name.trim().length() == 0) {
			throw new PersonFormatException("Input person name can not be null, please try again.");
		}
		List<Person> persons = personService.getPerson(name);
		if (persons.isEmpty()) {
			throw new PersonNotFoundException(name + " is not exists!");
		}
		return persons;
	}

	// 4 Print person's detail by birthday.
	public List<Person> getPersonsByBirth(Integer month, Integer day) throws PersonNotFoundException {
		Date birth = getBirthDate(2000, month, day);
		List<Person> persons = personService.getPerson(birth);
		if (persons.isEmpty()) {
			throw new PersonNotFoundException("No one birth on " + month + "/" + day + ".");
		}
		return persons;
	}

	// 5
	/**
	 * Print person's detail by age.
	 * This method will find person whose age older or younger than entering age.
	 * 
	 * @param age
	 * @param AgeOptions
	 * @return List<Person>
	 * @throws PersonFormatException
	 * @throws PersonNotFoundException
	 */
	public List<Person> getPersonsByAge(Integer age, AgeOptions options)
			throws PersonFormatException, PersonNotFoundException {
		if (age == null || age < 0) {
			throw new PersonFormatException("Input age can not be null or be negative, please try again.");
		}
		List<Person> persons = personService.getPerson(age, options);
		if (persons.isEmpty()) {
			throw new PersonNotFoundException("Person not found!");
		}
		return persons;
	}

	public List<Person> getPersonsByAge(Integer age) throws PersonNotFoundException {
		List<Person> persons = personService.getPerson(age, AgeOptions.SAMEAGE);
		if (persons.isEmpty()) {
			throw new PersonNotFoundException("No matched person found!");
		}
		return persons;
	}

	// 6-1
	/**
	 * Update person's birthday.
	 * If there are more than one person with this name, this method will throw MutiplePersonsException.
	 * 
	 * @param name
	 * @param year
	 * @param month
	 * @param day
	 * @throws PersonNotFoundException
	 * @throws MutiplePersonsException
	 * @throws PersonFormatException
	 */
	public boolean updatePersonBirthday(String name, Integer year, Integer month, Integer day)
			throws PersonNotFoundException, MutiplePersonsException, PersonFormatException {
		List<Person> persons = getPersonsByName(name);
		if (persons.size() > 1) {
			throw new MutiplePersonsException("More then one persons named " + name + ".");
		}
		if (persons.isEmpty()) {
			throw new PersonNotFoundException(name + " not exists!");
		}
		Person person = persons.get(0);
		Date newBirth = getBirthDate(year, month, day);
		birthFomatCheck(newBirth);
		return personService.updateBirthDay(person, newBirth);

	}

	// 6-2
	public boolean updatePersonBirthday(String name, Integer oldYear, Integer oldMonth, Integer oldDay, Integer newYear,
			Integer newMonth, Integer newDay) throws PersonNotFoundException, PersonFormatException {
		Person person = new Person();
		Date oldBirth = getBirthDate(oldYear, oldMonth, oldDay);
		String newName= nameFormatter(name);
		person.setName(newName);
		person.setBirth(oldBirth);
		if (!personService.havePerson(person)) {
			throw new PersonNotFoundException(
					name + " birthday: " + oldYear + "/" + oldMonth + "/" + oldDay + " not exists!");
		}
		return updatePersonBirthday(person, newYear, newMonth, newDay);
	}

	// 6-3
	public boolean updatePersonBirthday(Person person, Integer year, Integer month, Integer day)
			throws PersonFormatException {
		Date newBirth = getBirthDate(year, month, day);
		birthFomatCheck(newBirth);
		return personService.updateBirthDay(person, newBirth);
	}

	// 7-1
	/**
	 * Delete person.
	 * This method will delete all person who with this name.
	 * 
	 * @param name
	 * @throws PersonFormatException
	 */
	public boolean deleteAllPersonsByName(String name) throws PersonNotFoundException, PersonFormatException {
		if (name == null || name.trim().length() == 0) {
			throw new PersonFormatException("Input name can not be null!");
		}
		List<Person> people = personService.findAllPersons();
		if (people.removeIf(p -> p.getName().equalsIgnoreCase(name))) {
			return personService.writePersons(people);
		}
		throw new PersonNotFoundException(name + " not exists!");
	}

	// 7-2
	public boolean deletePerson(String name)
			throws MutiplePersonsException, PersonNotFoundException, PersonFormatException {
		List<Person> persons = personService.getPerson(name);
		if (persons.size() > 1) {
			throw new MutiplePersonsException("More then one persons named " + name);
		}
		if (persons.isEmpty()) {
			throw new PersonNotFoundException(name + " not exists!");
		}
		List<Person> people = personService.findAllPersons();
		people.removeIf(p -> p.getName().equalsIgnoreCase(name));
		return personService.writePersons(people);
	}

	// 7-3
	public boolean deletePerson(String name, Integer year, Integer month, Integer day) throws PersonNotFoundException {
		Date birth = getBirthDate(year, month, day);
		Person person = new Person();
		person.setName(name.trim());
		person.setBirth(birth);
		if (!personService.havePerson(person)) {
			throw new PersonNotFoundException(name + " birthday: " + year + "/" + month + "/" + day + " not exists!");
		}
		return personService.deletePerson(person);
	}

	/**
	 * Convert the entering data to Date type.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private Date getBirthDate(Integer year, Integer month, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * Check the entering birth date is valid.
	 * 
	 * @param birth
	 * @throws PersonFormatException if the date after now-time, or before 1900.
	 */
	private void birthFomatCheck(Date birth) throws PersonFormatException {
		Date now = new Date();
		if (birth.after(now)) {
			throw new PersonFormatException("Entering birthday is after now time, please check!");
		}
		LocalDate localDate = birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.get(ChronoField.YEAR);
		int month = localDate.get(ChronoField.MONTH_OF_YEAR);
		int day = localDate.get(ChronoField.DAY_OF_MONTH);
		if (year < 1900)
			throw new PersonFormatException("The person was born before 1900, please check!");
		if (month <= 0 || month > 12 || day <= 0 || day > 31) {
			throw new PersonFormatException("Birthday format not correct, please check!");
		}

	}

	/**
	 * Check the entering person name whether contains special characters, and
	 * revise the name first letter to uppercase.
	 * 
	 * @param name
	 * @return The name witch fist-letter is uppercase.
	 * @throws PersonFormatException if contains special characters.
	 */
	private String nameFormatter(String name) throws PersonFormatException {
		final String SPECIAL = "!\"#$%&'()*+,-./0123456789:;<=>?@[\\]^_`{|}~";
		String newName = name.trim();
		for (int i = 0; i < SPECIAL.length(); i++) {
			if (newName.contains(SPECIAL.substring(i, i + 1))) {
				throw new PersonFormatException(
						"The person's name contains special characters, failing to create person data! Please check!");
			}
		}
		newName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
		for (int i = 0;; i++) {
			i = newName.indexOf(" ", i);
			if (i == -1)
				break;
			newName = newName.substring(0, i + 1) + newName.substring(i + 1, i + 2).toUpperCase()
					+ newName.substring(i + 2);
		}
		return newName;
	}
}
