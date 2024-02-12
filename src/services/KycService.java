package services;

import java.sql.SQLException;

import exception.BankSystemException;
import pojo.Account;
import pojo.KYC;

public interface KycService {
	
	void addKyc(KYC kyc, Account obj) throws SQLException;
	KYC getAccount(int kycId) throws SQLException, BankSystemException;
}
