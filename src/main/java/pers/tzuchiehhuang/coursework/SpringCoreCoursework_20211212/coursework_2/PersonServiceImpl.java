package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public boolean append(Person person) {
		return personDao.create(person);
	}

	@Override
	public List<Person> findAllPersons() {
		return personDao.readAll();
	}

	@Override
	public List<Person> getPerson(String name) {
		return findAllPersons().stream().filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	@Override
	public List<Person> getPerson(Date birth) {
		String birthday = getBirthday(birth);
		return findAllPersons().stream().filter(p -> getBirthday(p.getBirth()).equals(birthday))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getPerson(int age, int options) {
		if (options > 0)
			return findAllPersons().stream().filter(p -> p.getAge() >= age).collect(Collectors.toList());
		if (options < 0)
			return findAllPersons().stream().filter(p -> p.getAge() <= age).collect(Collectors.toList());
		return findAllPersons().stream().filter(p -> p.getAge() == age).collect(Collectors.toList());

	}

	@Override
	public boolean writePersons(List<Person> people) {
		return personDao.write(people);
	}

	@Override
	public boolean updateBirthDay(Person person, Date newBirth) {
		return personDao.update(person, newBirth);
	}

	@Override
	public boolean deletePerson(Person person) {
		return personDao.remove(person);
	}

	private String getBirthday(Date date) {
		LocalDate birthDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String birthday = birthDate.get(ChronoField.MONTH_OF_YEAR) + " " + birthDate.get(ChronoField.DAY_OF_MONTH);
		return birthday;
	}


}
