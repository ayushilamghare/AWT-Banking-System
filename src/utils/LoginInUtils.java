package utils;
import services.*;
import exception.BankSystemException;
public class LoginInUtils {
	
	public static void validateLoginCreds(String accNo, String pin) throws BankSystemException{
		int newAccNo= validateAccNo(accNo);
		int newPin = validatePin(pin);
		
	}

	public static int validatePin(String pin) throws BankSystemException {
		int newPin;
		if(pin.isEmpty()|| pin.isBlank())
			throw new BankSystemException("Enter PIN");
		if(pin.length()!=6)
			throw new BankSystemException("INVALID PIN");
		try {
			 newPin = Integer.parseInt(pin);
			 return newPin;
		} catch (Exception e) {
			 throw new BankSystemException("PIN MUST BE IN NUMERIC FORMAT!");
		}
		
	}

	public static int validateAccNo(String accNo) throws BankSystemException {
		int newAccNo;
		
		if(accNo.isEmpty()|| accNo.isBlank())
			throw new BankSystemException("Enter Account Number");

		try {
			 newAccNo = Integer.parseInt(accNo);
			 return newAccNo;
		} catch (Exception e) {
			 throw new BankSystemException("ACCOUNT NO. MUST BE IN NUMERIC FORMAT!");
		}
		
		
	}
	
	
}
