package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;



public class BookDaoTest {

	public static void main(String[] args) throws OrderException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext4.xml");
		BookDao bookDao= ctx.getBean("bookDaoImpl", BookDao.class);
		
		//bookDao.updateStock(1, 10);
		bookDao.updateWallet(1, 200);
		System.out.println(bookDao.getPrice(1));
		System.out.println(bookDao.getStockAmount(1));
		System.out.println(bookDao.getWalletMoney(1));
		
	}

}
