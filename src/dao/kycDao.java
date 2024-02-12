package dao;

import java.sql.SQLException;

import exception.BankSystemException;
import pojo.Account;
import pojo.KYC;

public interface kycDao {
	void addKyc(KYC kyc, Account obj) throws SQLException;
	KYC getAccount(int kycId) throws SQLException, BankSystemException;
	void removeKyc(int kycId)throws SQLException;
	void cleanUpDao() throws SQLException;

}
