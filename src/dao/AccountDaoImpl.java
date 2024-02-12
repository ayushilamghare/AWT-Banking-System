package dao;

import static DButils.DButils.*;
import java.sql.*;
import static main.MainCaller.*;
import exception.BankSystemException;
import pojo.AccType;
import pojo.Account;
import pojo.KYC;

public class AccountDaoImpl implements AccountDao {

	Connection con;

	public AccountDaoImpl() throws SQLException {
		con = establishConnection();
	}

	@Override
	public void addAccount(Account obj) throws SQLException, BankSystemException {

		PreparedStatement pst = con.prepareStatement("insert into BankAccount values(?,?,?,?,?,?,?,?,?)");
		pst.setInt(1, obj.getAccNo());
		pst.setInt(2, obj.getPin());
		pst.setString(3, obj.getFirstName().toUpperCase());
		pst.setString(4, obj.getLastName().toUpperCase());
		try {
			pst.setString(5, obj.getEmail());

		} catch (Exception e) {
			throw new BankSystemException("EMAIL Already Exists!");
		}
		pst.setDate(6, obj.getDob());
		pst.setDouble(7, obj.getBalance());
		pst.setString(8, obj.getAccountType().toString());
		pst.setDate(9, obj.getCreationDate());
		int insertRow = pst.executeUpdate();
		if (insertRow > 0)
			System.out.println("success");
		pst.close();
	}

	@Override
	public void removeAccount(int accNo) throws SQLException {
		PreparedStatement pst = con.prepareStatement("Delete from BankAccount where accNo=?");
		pst.setInt(1, accNo);
		int rowsAffected = pst.executeUpdate();

		pst.close();

	}

	@Override
	public void cleanUpDao() throws SQLException {
		con.close();
		closeConnection();
	}

	@Override
	public Account getAccount(int accNo, int pin) throws SQLException, BankSystemException {
		Account obj = new Account();
		PreparedStatement pst = con.prepareStatement("select * from bankaccount where accNo = ? and pin = ?");
		pst.setInt(1, accNo);
		pst.setInt(2, pin);
		ResultSet rst = pst.executeQuery();
		if (rst.next()) {
			obj = new Account(rst.getInt(2), rst.getString(3), rst.getString(4), rst.getString(5),
					Date.valueOf(rst.getString(6)), rst.getDouble(7), rst.getString(8));
			obj.setAccNo(rst.getInt(1));
		} else {
			throw new BankSystemException("Account Does Not Exists!");
		}
		return obj;
	}

	@Override
	public Account getAllAccounts() throws SQLException {
		PreparedStatement pst = con.prepareStatement("select * from BankAccount");
		ResultSet rst = pst.executeQuery();

		while (rst.next()) {
			System.out.println(rst.getInt(1) + " " + rst.getString(2) + " " + rst.getString(3));
		}
		pst.close();
		rst.close();
		return null;
	}

	@Override
	public void fundTransfer(int accNo, double amount) throws SQLException, BankSystemException {
		int accountNoSrc = LoggedInUserAccount.getAccNo();
		int accountNoDes = accNo;
//		if(kycAccount==null)
//			throw new BankSystemExceptiom("KYC is not Done!");
		
		double amountSrc = LoggedInUserAccount.getBalance() - amount;
		LoggedInUserAccount.setBalance(amountSrc);
		PreparedStatement pst = con.prepareStatement("UPDATE bankaccount SET balance = ? WHERE accNO =?");
		pst.setDouble(1, amountSrc);
		pst.setInt(2, LoggedInUserAccount.getAccNo());
		int rowsAffected = pst.executeUpdate();
		
		Account acc2 = getAccount(accNo);

		double amountDes = acc2.getBalance() +amount;
		acc2.setBalance(amountDes);
		pst = con.prepareStatement("UPDATE bankaccount SET balance = ? WHERE accNO =?");
		pst.setDouble(1, amountDes);
		pst.setInt(2, acc2.getAccNo());
		rowsAffected = pst.executeUpdate();

		pst.close();
	}

	@Override
	public void withdrawMoney(double amount) throws SQLException, BankSystemException {
		double newAmount = LoggedInUserAccount.getBalance() - amount;
		if (newAmount < LoggedInUserAccount.getAccountType().getMinBalance())
			throw new BankSystemException(
					"Mininum Bal shouble be " + LoggedInUserAccount.getAccountType().getMinBalance());
		PreparedStatement pst = con.prepareStatement("UPDATE bankaccount SET balance = ? WHERE accNO =?");
		pst.setDouble(1, newAmount);
		pst.setInt(2, LoggedInUserAccount.getAccNo());
		int rowsAffected = pst.executeUpdate();

		pst.close();

		LoggedInUserAccount.setBalance(newAmount);
	}

	@Override
	public void depositMoney(double amount) throws SQLException, BankSystemException {
		amount = LoggedInUserAccount.getBalance() + amount;
		if (amount < LoggedInUserAccount.getAccountType().getMinBalance())
			throw new BankSystemException(
					"Mininum Bal shouble be " + LoggedInUserAccount.getAccountType().getMinBalance());
		PreparedStatement pst = con.prepareStatement("UPDATE bankaccount SET balance = ? WHERE accNO =?");
		pst.setDouble(1, amount);
		pst.setInt(2, LoggedInUserAccount.getAccNo());
		int rowsAffected = pst.executeUpdate();

		pst.close();

		LoggedInUserAccount.setBalance(amount);

	}

	@Override
	public Account getAccount(int accNo) throws SQLException, BankSystemException {
		Account obj = new Account();
		PreparedStatement pst = con.prepareStatement("select * from bankaccount where accNo = ?");
		pst.setInt(1, accNo);
		ResultSet rst = pst.executeQuery();
		if (rst.next()) {
			obj = new Account(rst.getInt(2), rst.getString(3), rst.getString(4), rst.getString(5),
					Date.valueOf(rst.getString(6)), rst.getDouble(7), rst.getString(8));
			obj.setAccNo(rst.getInt(1));
		} else {
			throw new BankSystemException("Account Does Not Exists!");
		}
		return obj;
	}

}
