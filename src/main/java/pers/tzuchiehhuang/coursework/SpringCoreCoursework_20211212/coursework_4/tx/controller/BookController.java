package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.InsufficientQuantity;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.service.BookService;



@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Integer bid) {
		Boolean sucess= true;
		try {
			bookService.buyOne(wid, bid);
			System.out.println("wid:"+ wid + " Single buyBook OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("Inventory not enough: " + e);
			sucess= false;
		} catch (OrderException e) {
			System.err.println("Balance not enough: " + e);
			sucess= false;
		} finally {
			bookService.buyLogger(wid, bid, 1, sucess);
		}
	}

	public void buyBooks(Integer wid, Integer... bids) {
		Boolean sucess= true;
		Map<Integer, Integer> buyingMap= Arrays.asList(bids).stream().collect(Collectors.toMap(
				i->i,
				i->1,
				(a,b)->a+b
				));
		try {
			bookService.buyMany(wid, bids);
			System.out.println("wid:"+ wid +" Mutiple buyBooks OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("Inventory not enough: " + e);
			sucess= false;
		} catch (OrderException e) {
			System.err.println("Balance not enough: " + e);
			sucess= false;
		} finally {
			for(Map.Entry<Integer, Integer> buying: buyingMap.entrySet()) {
				bookService.buyLogger(wid, buying.getKey(), buying.getValue(), sucess);
			}
		}
		
	}

}
