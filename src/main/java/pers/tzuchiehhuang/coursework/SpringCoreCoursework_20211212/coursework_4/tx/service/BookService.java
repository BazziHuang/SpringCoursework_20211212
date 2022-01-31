package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.service;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;

public interface BookService {
	void buyOne(Integer wid, Integer bid) throws OrderException;
	void buyMany(Integer wid, Integer... bids) throws OrderException;
	void buyLogger(Integer wid, Integer bid, Integer quantity, Boolean success);
}
