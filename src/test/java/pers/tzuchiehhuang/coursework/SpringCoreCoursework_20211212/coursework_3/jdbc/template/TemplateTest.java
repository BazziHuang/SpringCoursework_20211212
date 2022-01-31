package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.template;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.logger.JsonDB;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_3.jdbc.template.EmpDao;



public class TemplateTest {

	public static void main(String[] args) throws IOException{
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext3.xml");
		EmpDao empDao= ctx.getBean("empDaoImpl", EmpDao.class);
		System.out.println(empDao.queryAll());
		
		
		//For testing, read log.json file
		JsonDB jsonDB= ctx.getBean("jsonDB", JsonDB.class);
		jsonDB.readMethodLog();

	}

}
