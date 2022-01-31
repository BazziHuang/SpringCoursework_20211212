package pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.dao;

import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.InsufficientAmount;
import pers.tzuchiehhuang.coursework.SpringCoreCoursework_20211212.coursework_4.tx.exception.InsufficientQuantity;

public interface BookDao {
	Integer getPrice(Integer bid);
	Integer getStockAmount(Integer bid);
	Integer getWalletMoney(Integer wid);
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity;
	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount;
	Integer orderLogger(Integer wid, Integer bid, Integer quantity, Boolean success);
}
