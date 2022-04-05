/*
 * A Test Driver for the Date class.
 */
package lab5;

public class TestDate {
   public static void main(String[] args) {
      // Test constructor and toString()
      Date d1 = new Date(2016, 4, 6);
      System.out.println(d1);  // toString()
 
      // Test Setters and Getters
      d1.setYear(2012);
      d1.setMonth(12);
      d1.setDay(23);
      System.out.println(d1);  // run toString() to inspect the modified instance
      System.out.println("Year is: " + d1.getYear());
      System.out.println("Month is: " + d1.getMonth());
      System.out.println("Day is: " + d1.getDay());
 
      // Test setDate()
      d1.setDate(2988, 1, 2);
      System.out.println(d1);  // toString()
      
      /*Date d2 = new Date(30 , 2, 2016);
      System.out.println(d2);
      d2.setYear(2012);
      d2.setMonth(1);
      d2.setDay(30);
      System.out.println(d2);
      System.out.println("Year is: " + d2.getYear()); 
      System.out.println("Month is: " + d2.getMonth());
      System.out.println("Day is: " + d2.getDay());
   	*/
   }
}

