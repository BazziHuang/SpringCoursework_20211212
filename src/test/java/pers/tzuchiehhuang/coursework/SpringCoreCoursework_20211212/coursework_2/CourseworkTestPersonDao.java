package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.database.PersonDao;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.database.PersonDaoImpl;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2.entity.Person;

public class CourseworkTestPersonDao {
	
	public static void main(String arg[]) {
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonDao dao= ctx.getBean("personDaoImpl", PersonDaoImpl.class);
		Calendar birthday=  Calendar.getInstance();
		birthday.set(1993, 10, 26);
		Date lisaBirth= birthday.getTime();
		Person Lisa= new Person("Lisa", lisaBirth);
		System.out.println(dao.readAll());
		//System.out.println(dao.create(Lisa));
		//System.out.println(dao.readAll());
	}

}
