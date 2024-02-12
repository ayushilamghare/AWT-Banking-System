package services;

import java.sql.SQLException;

import dao.kycDao;
import dao.kycDaoImpl;
import exception.BankSystemException;
import pojo.Account;
import pojo.KYC;

public class kycServiceImpl implements KycService{
	
	private kycDao kycDao;
	
	public kycServiceImpl() throws SQLException {
		kycDao= new kycDaoImpl();
	}

	@Override
	public void addKyc(KYC kyc, Account obj) throws SQLException {
		kycDao.addKyc(kyc, obj);
		kycDao.cleanUpDao();
	}

	@Override
	public KYC getAccount(int kycId) throws SQLException, BankSystemException {
		// TODO Auto-generated method stub
		return kycDao.getAccount(kycId);
	}
	

}
