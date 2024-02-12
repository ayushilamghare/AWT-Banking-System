package dao;
import exception.*;
import pojo.*;
import java.sql.SQLException;
import pojo.Account.*;
public interface AccountDao {
	
	void addAccount(Account obj) throws SQLException, BankSystemException;
	Account getAccount(int accNo, int pin)throws SQLException, BankSystemException;
	Account getAccount(int accNo)throws SQLException, BankSystemException;
	Account getAllAccounts() throws SQLException;
	void depositMoney(double amount) throws SQLException, BankSystemException;
	void withdrawMoney(double amount) throws SQLException, BankSystemException;
	void fundTransfer(int accNo, double amount )throws SQLException, BankSystemException;
	void removeAccount(int accNo)throws SQLException;
	void cleanUpDao() throws SQLException;
	
	
	
	

	
}
