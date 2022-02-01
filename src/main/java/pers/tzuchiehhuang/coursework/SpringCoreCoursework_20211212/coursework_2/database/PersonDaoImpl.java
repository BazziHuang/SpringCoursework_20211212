package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.database;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private JsonDB jsonDB;

	@Override
	public boolean create(Person person) {
		boolean check = false;
		try {
			check = jsonDB.add(person);
		} catch (IOException e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}

	@Override
	public List<Person> readAll() {
		List<Person> people = null;
		try {
			people = jsonDB.queryAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public boolean write(List<Person> people) {
		boolean check = false;
		try {
			jsonDB.writeJson(people);
			check = true;
		} catch (IOException e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}

	@Override
	public boolean remove(Person person) {
		boolean check = false;
		try {
			check= jsonDB.delete(person);
		} catch (IOException e) {
			e.printStackTrace();
			check= false;
		}
		return check;
	}

	@Override
	public boolean update(Person person, Date birth) {
		boolean check = false;
		try {
			check= jsonDB.updateBirth(person, birth);
		} catch (IOException e) {
			e.printStackTrace();
			check= false;
		}
		return check;
	}

}
