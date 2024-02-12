package utils;

import exception.BankSystemException;
import pojo.AccType;

public class BasicValidations {
	
	
		
		public static double validateAmount(String amount) throws BankSystemException {
			if (amount.isBlank() || amount.isEmpty() || amount.trim() == null)
				throw new BankSystemException("Balance is Invalid!");

			try {
				double newAmount = Double.parseDouble(amount);
				return newAmount;
				
			} catch (Exception e) {
				throw new BankSystemException("AMOUNT MUST BE IN NUMERIC FORMAT!");
			}
			
			
		}

}
