package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CourseworkTestController {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		PersonController controller = ctx.getBean("personController", PersonController.class);
		// controller.printAllPersons();
		// controller.addPerson("King", 1974, 8, 22);
		// controller.printAllPersons();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1943, 8, 3);
		Date birth = calendar.getTime();
		// Person jean= new Person("Jean", birth);
		//controller.deleteAllPersonsByName("Herry");
		controller.addPerson("Herry", 2001, 11, 30);
		// controller.getPersonInfoByName("Bob");
		// controller.getPersonsByBirth(8, 22);

		// controller.revisePersonBirthday("Jean", 1988, 12, 5);
		//controller.revisePersonBirthday("Tommie", 1943, 9, 3, 1949, 9, 5);
		// controller.deletePerson("Jean", 1993, 11, 26);
		// controller.printInfo(controller.getPersonsByAge(30), "not found!");

		controller.printAllPersons();

	}

}
