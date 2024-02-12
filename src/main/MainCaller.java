package main;

import gui.Login;
import pojo.Account;
import pojo.KYC;

public class MainCaller {
	
	public static Account LoggedInUserAccount = null;
	public static void main(String[] args)
	{try {
		Login loginObj = new Login();
		loginObj.setVisible(true);
		
	} catch (Exception e) {
		e.getStackTrace();
	}
	}

}
