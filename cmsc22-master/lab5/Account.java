package lab5;

public class Account {
	
	private int accountNumber;
	private double balance;
	
	//constructors
	public Account(){
		balance = 0;
		accountNumber = 000;
	}
	
	public Account(int accountNumber, double balance){
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public Account(int accountNumber){
		this.accountNumber = accountNumber;
	}

	//getters
	public int getAccountNumber(){
		return accountNumber;
		
	}
	
	public double getBalance(){
		return balance;
	}
	
	//setters
	public void setBalance(double balance){
		if(balance < 0){
			throw new IllegalArgumentException("Invalid.");
		}
		else{
			this.balance = balance;	
		}
	}
	
	//methods
	public void credit(double amount){
		balance = amount + balance;
		balance = (double) Math.round(balance * 100) / 100;
	}
	
	public void debit(double amount){
		if(balance >= amount){
			balance = balance - amount;
			balance = (double) Math.round(balance * 100) / 100;
		}
		else{
			System.out.println("Amount withdrawn exceeds the current balance!");
		}
		
	}
	
	public String toString(){
		return "A/C no:" + this.accountNumber + ", Balance = " + this.balance;
	}
	


} 
