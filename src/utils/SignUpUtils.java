package utils;

import exception.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import pojo.AccType;
import pojo.AccType.*;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.xml.sax.ErrorHandler;

import gui.*;
import pojo.Account;
import pojo.Account.*;

public class SignUpUtils {

	public static Account validateAll(String firstName, String lastName, String email, String dob, String balance,
			String pin, String accType) throws BankSystemException {
		AccType acc = valiadteAccType(accType);
		validateFirstName(firstName);
		validateLastName(lastName);
		validateEmail(email);
		Date dobAcc = validateDob(dob);
		double balanceAcc = validatebalance(balance, acc);
		int pinCode = validatePin(pin);
		Account obj = new Account(pinCode, firstName, lastName, email, dobAcc, balanceAcc, accType);
		return obj;

	}

	private static int validatePin(String pin) throws BankSystemException {
		if (pin.isBlank() || pin.isEmpty() || pin.trim() == null)
			throw new BankSystemException("Pin is Invalid!");
		if (pin.length() != 6)
			throw new BankSystemException("Invalid PINCODE");
		int pincode = Integer.parseInt(pin);
		return pincode;
	}

	private static AccType valiadteAccType(String accType) {
		return AccType.valueOf(accType.toUpperCase());
	}

	private static double validatebalance(String balance, AccType acc) throws BankSystemException {
		if (balance.isBlank() || balance.isEmpty() || balance.trim() == null)
			throw new BankSystemException("Balance is Invalid!");

		double balanceAcc = Double.parseDouble(balance);
		if (balanceAcc < acc.getMinBalance())
			throw new BankSystemException("\"Minimum balance for " + acc.name() + " is :" + acc.getMinBalance());

		return balanceAcc;
	}

	private static Date validateDob(String dob) throws BankSystemException {
		LocalDate dateofbirth = LocalDate.parse(dob);
		LocalDate currentDate = LocalDate.now();

		if (dateofbirth.isAfter(currentDate))
			throw new BankSystemException("Add correct date of birth, please.");

		int age = Period.between(dateofbirth, currentDate).getYears();
		if (age < 18)
			throw new BankSystemException("Age should be 18+");

		return Date.valueOf(dateofbirth);

	}

	private static void validateEmail(String email) throws BankSystemException {
		if (!email.endsWith("@gmail.com"))
			throw new BankSystemException("Email is INVALID");

	}

	private static void validateLastName(String lastName) throws BankSystemException {

		if (lastName.isBlank() || lastName.isEmpty() || lastName.trim() == null)
			throw new BankSystemException("Last Name is INVALID");
		char ch[] = lastName.toCharArray();
		for (char c : ch) {
			if (!Character.isLetter(c) || c == ' ') {
				throw new BankSystemException("Last Name must not contain SPACES!");
			}
		}
	}

	private static void validateFirstName(String firstName) throws BankSystemException {

		if (firstName.isBlank() || firstName.isEmpty() || firstName.trim() == null)
			throw new BankSystemException("First Name is INVALID");
		char ch[] = firstName.toCharArray();
		for (char c : ch) {
			if (!Character.isLetter(c) || c == ' ') {
				throw new BankSystemException("First Name must not contain SPACES!");
			}
		}
	}

}
