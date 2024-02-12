package utils;

import java.util.Map;
import java.util.Scanner;

import exception.*;
import pojo.*;

import exception.BankSystemException;
import pojo.Account;
import pojo.KYC;

public class KycValidation {

	public static KYC validateKyc(String city, String pincode, String aadharNo)
			throws BankSystemException {
		validateCity(city);
		validatepinCode(pincode);
		validateAdhaarno(aadharNo);

		KYC kyc = new KYC(city, pincode, aadharNo);
        return kyc;
	}

	public static void validatepinCode(String pincode) throws BankSystemException {
		char[] pincodeValidate = pincode.toCharArray();
		for (char c : pincodeValidate) {
			if (Character.isLetter(c))
				throw new BankSystemException("Enter proper pincode");
			if ((pincode.length() < 6) || pincode.length() > 6)
				throw new BankSystemException("Enter 6 digit pincode");

		}
	}

	public static void validateCity(String city) throws BankSystemException {
		char[] citystring = city.toCharArray();

		for (char c : citystring) {
			if (!Character.isLetter(c) || c == ' ') {
				throw new BankSystemException("Invalid City");
			}

		}

	}

	public static void validateAdhaarno(String adharno) throws BankSystemException {
		char[] pincodeValidate = adharno.toCharArray();
		for (char c : pincodeValidate) {
			if (Character.isLetter(c) || c == ' ')
				throw new BankSystemException("Enter proper Aadhaar Number");
			if ((adharno.length() < 12) || adharno.length() > 12)
				throw new BankSystemException("Enter 12 digit number");

		}
	}

//	public static Account kycCheck(int accNo, Map<Integer, Account> customers) throws BankSystemExceptiom {
//
//		Account newObj = customers.get(accNo);
//		if (newObj == null)
//			throw new BankSystemExceptiom("Account Number does not exists.");
////		if(newObj.isKycDone())
////			throw new BankSystemExceptiom("KYC is already done");
//
//		return newObj;
//
//	}

}
