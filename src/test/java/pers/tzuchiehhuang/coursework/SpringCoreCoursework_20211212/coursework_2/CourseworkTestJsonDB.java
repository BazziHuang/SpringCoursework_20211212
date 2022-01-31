package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseworkTestJsonDB {
	
	public static void main(String[] args) throws Exception{
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext2.xml");
		
		JsonDB jsonDB= ctx.getBean("jsonDB", JsonDB.class);
		
		System.out.println(jsonDB.queryAll());
		
		Calendar calendar= Calendar.getInstance();
		calendar.set(1992, 7, 22);
		Date birthA= calendar.getTime();
		calendar.set(1993, 10, 11);
		Date birthB= calendar.getTime();
		//System.out.println(jsonDB.reviseBirth(new Person("Molly", birthA), birthB));
		Person amy= new Person("Amy", birthB);
		jsonDB.add(amy);
		//System.out.println(jsonDB.queryAll());
		
		//jsonDB.deletePersonByName("Randy");
		
		//jsonDB.deletePerson(new Person("Randy", calendar.getTime()));
		
		System.out.println(jsonDB.queryAll());

	}

}
