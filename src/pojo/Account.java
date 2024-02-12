package pojo;

import java.sql.Date;
import java.time.LocalDate;


public class Account {
	
	private int accNo;	//PK
	private int pin;
	private String firstName;
	private String lastName;
	private String email;	//UNIQUE
	private Date dob;
	private Double balance;
	private AccType accountType;
	private KYC kyc;//Not in DB
	private Date creationDate;
	private String stringAccType;
	
	public String getStringAccType() {
		return stringAccType;
	}


	public void setStringAccType(String stringAccType) {
		this.stringAccType = stringAccType;
	}


	public Account(int pin, String firstName, String lastName, String email, Date dob, Double balance,
			String stringAccString)
	{
		this.pin = pin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.balance = balance;
		this.accountType= accountType.valueOf(stringAccString);
		this.stringAccType = accountType.toString();
		this.kyc = null;
		this.creationDate = Date.valueOf(LocalDate.now());
		
	}


	public Account() {
		// TODO Auto-generated constructor stub
	}


	public int getAccNo()
	{
		return accNo;
	}


	public void setAccNo(int accNo)
	{
		this.accNo = accNo;
	}


	public int getPin()
	{
		return pin;
	}


	public void setPin(int pin)
	{
		this.pin = pin;
	}


	public String getFirstName()
	{
		return firstName;
	}


	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}


	public String getLastName()
	{
		return lastName;
	}


	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getEmail()
	{
		return email;
	}


	public void setEmail(String email)
	{
		this.email = email;
	}


	public Date getDob()
	{
		return dob;
	}


	public void setDob(Date dob)
	{
		this.dob = dob;
	}


	public Double getBalance()
	{
		return balance;
	}


	public void setBalance(Double balance)
	{
		this.balance = balance;
	}


	public AccType getAccountType()
	{
		return accountType;
	}


	public void setAccountType(AccType accountType)
	{
		this.accountType = accountType;
	}


	public KYC getKyc()
	{
		return kyc;
	}


	public void setKyc(KYC kyc)
	{
		this.kyc = kyc;
	}


	public Date getCreationDate()
	{
		return creationDate;
	}


	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}


	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", pin=" + pin + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dob=" + dob + ", balance=" + balance + ", accountType=" + accountType
				+ ", kyc=" + kyc + ", creationDate=" + creationDate + "]";
	}
	
	

	
	

	
	

}
