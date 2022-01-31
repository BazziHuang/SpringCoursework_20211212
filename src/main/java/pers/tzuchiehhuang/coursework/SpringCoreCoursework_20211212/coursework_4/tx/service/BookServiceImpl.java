package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.dao.BookDao;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.OrderException;



@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;

	@Transactional(propagation = Propagation.REQUIRED,
			rollbackFor = {OrderException.class}
			)
	@Override
	public void buyOne(Integer wid, Integer bid) throws OrderException {
		//1. update inventory of book at stock table
		bookDao.updateStock(bid, 1);
		//2. get book price from book table.
		Integer price= bookDao.getPrice(bid);
		//3. update wallet balance at wallet table
		bookDao.updateWallet(wid, price);
		
	}

	@Transactional(propagation = Propagation.REQUIRED,
			rollbackFor = {OrderException.class}
			)
	//getConnection(), setAutoCommit(false), commit()
	@Override
	public void buyMany(Integer wid, Integer... bids) throws OrderException {
		for(Integer bid: bids) {
			buyOne(wid, bid);
		}
		//money not enough -> transaction failed -> commit() rollback
	}

	@Override
	public void buyLogger(Integer wid, Integer bid, Integer quantity, Boolean success) {
		bookDao.orderLogger(wid, bid, quantity, success);
	}

}
