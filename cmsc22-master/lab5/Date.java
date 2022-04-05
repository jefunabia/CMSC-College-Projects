package lab5;

public class Date {

	private int day;
	private int month;
	private int year;

	//constructors
	public Date(){
		this.day = 1;
		this.month = 1;
		this.year = 1000;
	}
	public Date(int d, int m, int y){
		this.day = d;
		this.month = m;
		this.year = y;
		
	}

	//getters
	public int getDay(){
		return day;	
	}
	public int getMonth(){
		return month;	
	}
	public int getYear(){
		return year;	
	}

	//setters
	public void setDay(int d){
		// odd months have 31 days
		if((month % 2 != 0) && (d < 1 || d > 31)){
			throw new IllegalArgumentException("Invalid day for odd month.");
		}
		// even months have 30 days
		else if((month % 2 == 0) && (d < 1 || d > 30)){
			throw new IllegalArgumentException("Invalid day for even month.");
		}
		// February only has 28 days
		else if((month == 2) && (d < 1 || d > 28)){
			throw new IllegalArgumentException("Invalid day for February.");
		}
		// Leap year only has 29 days
		else if((year % 4 == 0) && (month == 2) && (d < 1 || d > 29)){
			throw new IllegalArgumentException("Invalid day for February.");
		}
		else{
			this.day = d;
		}
	}
	
	public void setMonth(int m){
		if(m < 1 || m > 12){
			throw new IllegalArgumentException("Invalid Month.");
		}
		else{
			this.month = m;
		}
	}
	public void setYear(int y){
		if(y < 1000 || y > 9999){
			throw new IllegalArgumentException("Invalid Year.");
		}
		else{
			this.year = y;
		}
	}

	//functions
	public void setDate(int d, int m, int y){
		if(m >= 1 || m <= 12){
			this.month = m;
			}
		if(y <= 1000 || y >= 9999){
			this.year = y;
			}
		// odd months have 31 days
		if((month % 2 != 0) && (d >= 1 || d <= 31)){
			this.day = d;
		}
		// even months have 30 days
		else if((month % 2 == 0) && (d >= 1 || d <= 30)){
			this.day = d;
		}
		// February only has 28 days
		else if((month == 2) && (d >= 1 && d <= 28)){
			this.day = d;
		}
		// Leap year only has 29 days
		else if((year % 4 == 0) && (month == 2) && (d >= 1 || d <= 29)){
			this.day = d;
		}
		else{
			throw new IllegalArgumentException("Invalid Date.");
		}
		
	}

	public String toString(){
		return String.format("%02d/%02d/%d", day, month, year);
	}
	
}