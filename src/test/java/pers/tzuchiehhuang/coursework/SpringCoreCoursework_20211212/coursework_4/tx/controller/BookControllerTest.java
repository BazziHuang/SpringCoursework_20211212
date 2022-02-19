package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;


public class BookControllerTest {

	public static void main(String[] args) throws OrderException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
		BookController bookController= ctx.getBean("bookController", BookController.class);
		//bookController.buyBook(2, 1);
		bookController.buyBooks(2, 1, 2, 5);
	}

}
