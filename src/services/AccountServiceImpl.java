package services;

import java.sql.SQLException;

import dao.AccountDao;
import dao.AccountDaoImpl;
import exception.BankSystemException;
import pojo.Account;

public class AccountServiceImpl implements AccountServices{
	
	private AccountDao dao;
	
	public AccountServiceImpl() throws SQLException {
		dao = new AccountDaoImpl();
	}
	public Account getAccount(int accNO, int pin) throws SQLException, BankSystemException {
		return dao.getAccount(accNO, pin);
		
	}

	@Override
	public void addAccount(Account obj) throws SQLException, BankSystemException {
		dao.addAccount(obj);
		dao.cleanUpDao();
	}
	@Override
	public void withdrawMoney(double amount) throws SQLException, BankSystemException {
		dao.withdrawMoney(amount);
		
	}
	@Override
	public void depositMoney(double amount) throws SQLException, BankSystemException {
		dao.depositMoney(amount);
		
	}
	@Override
	public void fundTransfer(int accNo, double amount) throws SQLException, BankSystemException {
		dao.fundTransfer(accNo, amount);;
		
	}

}
