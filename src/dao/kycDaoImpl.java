package dao;

import static DButils.DButils.establishConnection;

import pojo.Account;
import pojo.KYC;
import static DButils.DButils.*;
import java.sql.*;

import exception.BankSystemException;

import static main.MainCaller.*;
public class kycDaoImpl implements kycDao{
	Connection con;
	
	public kycDaoImpl() throws SQLException{
		con =establishConnection();}

	@Override
	public void addKyc(KYC kyc, Account obj) throws SQLException {
		PreparedStatement pst = con.prepareStatement("insert into kyc values(?,?,?,?)");
		pst.setInt(1, obj.getAccNo());
		pst.setString(2, kyc.getCity().toUpperCase());
		pst.setInt(3, Integer.parseInt(kyc.getPincode()));// should be int
		pst.setString(4, kyc.getAadharNo());
		int insertRow = pst.executeUpdate();
		if(insertRow>0)
			System.out.println("success");
		pst.close();
	}

	@Override
	public  KYC getAccount(int kycId) throws SQLException, BankSystemException {
		KYC obj = new KYC();
		PreparedStatement pst = con.prepareStatement("select * from kyc where kycId = ?");
		pst.setInt(1, kycId);
		ResultSet rst = pst.executeQuery();
		if (rst.next()) {
			obj = new KYC (rst.getString(2), rst.getString(3), rst.getString(4));
					
			obj.setKycId(rst.getInt(1));
		} else {
			return null;
		}
		;
		return obj;
	}

	@Override
	public void removeKyc(int kycId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUpDao() throws SQLException {
		con.close();
		closeConnection();
	}

	

}
