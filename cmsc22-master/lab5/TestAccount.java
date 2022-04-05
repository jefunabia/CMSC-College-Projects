/*
 * A Test Driver for the Account class.
 */
package lab5;

public class TestAccount {
	public static void main(String[] args){
		Account acc1 = new Account(121, 50.2234);
		Account acc2 = new Account(122);
		Account acc3 = new Account(123, 50.789);
		System.out.println(acc1);
		acc1.credit(20);
		System.out.println(acc1);
		acc1.debit(10);
		System.out.println(acc1);
		acc1.debit(80);
		System.out.println(acc1);
		
		
		System.out.println(acc2);
		System.out.println(acc3);
		
		
		acc3.credit(12);
		System.out.println(acc3);
		
		
		
	}

}
