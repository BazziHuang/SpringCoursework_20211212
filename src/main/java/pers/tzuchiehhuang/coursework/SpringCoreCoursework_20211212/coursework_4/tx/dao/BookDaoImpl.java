package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.InsufficientAmount;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.InsufficientQuantity;



@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getPrice(Integer bid) {
		String sql= "select price from book where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql= "select amount from stock where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql= "select money from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}
	
	private String getWalletName(Integer wid) {
		String sql= "select wname from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, String.class, wid);
	}
	
	private String getBookName(Integer bid) {
		String sql= "select bname from book where bid=?";
		return jdbcTemplate.queryForObject(sql, String.class, bid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity {
		Integer currentAmount= getStockAmount(bid);
		if(currentAmount<=0){
			throw new InsufficientQuantity(String.format("This book id %d do not have enough inventory, now: %d.", bid, currentAmount));
		}else if(amount>currentAmount) {
			throw new InsufficientQuantity(String.format("This book id %d do not have enough inventory, now: %d, you need: %d.", bid, currentAmount, amount));
		}
		String sql= "update stock set amount= amount-? where bid=?";
		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount {
		Integer currentMoney= getWalletMoney(wid);
		if(currentMoney<=0) {
			throw new InsufficientAmount(String.format("Wallet id %d has no money, now: %d.", wid, currentMoney));
		}else if(currentMoney<money) {
			throw new InsufficientAmount(String.format("Wallet id %d do not have enough money, now: %d, you need: %d.", wid, currentMoney, money));
		}
		String sql= "update wallet set money= money-? where wid=?";
		return jdbcTemplate.update(sql, money, wid);
	}

	//-----------------------------------Logger------------------------------------------------
	@Override
	public Integer orderLogger(Integer wid, Integer bid, Integer quantity, Boolean success) {
		/*
		 * parameter "success", if purchase is completed, will give it true, otherwise false.
		 */
		String sql= "Insert into order_log(wid, wname, bid, bname, quantity, total, success) values(?, ?, ?, ?, ?, ?, ?)";
		String wname= getWalletName(wid);
		String bname= getBookName(bid);
		Integer price= getPrice(bid);
		return jdbcTemplate.update(sql,wid, wname, bid, bname, quantity, quantity*price, success);
	}

}
