package services;

import java.sql.SQLException;

import dao.AccountDao;
import dao.AccountDao.*;
import exception.BankSystemException;
import pojo.Account;

public interface AccountServices {
	
	void addAccount(Account obj) throws SQLException, BankSystemException;
	Account getAccount(int accNo, int pin)throws SQLException, BankSystemException;
	void withdrawMoney(double amount) throws SQLException, BankSystemException;
	void depositMoney(double amount) throws SQLException, BankSystemException;
	void fundTransfer(int accNo, double amount )throws SQLException, BankSystemException;
}
