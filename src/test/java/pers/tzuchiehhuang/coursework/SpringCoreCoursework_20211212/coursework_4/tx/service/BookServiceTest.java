package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;


public class BookServiceTest {

	public static void main(String[] args) throws OrderException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
		BookService bookService= ctx.getBean("bookServiceImpl", BookService.class);
		//bookService.buyOne(1, 1);
		bookService.buyMany(1, 2, 2, 3, 1, 2, 3, 2, 2);
		
	}

}
