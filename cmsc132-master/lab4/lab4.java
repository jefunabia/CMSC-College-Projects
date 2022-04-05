package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lab4 {
	public static void main(String[] args) {
		System.out.println("=== PIPELINING ===");

		// SET A
		System.out.println("[SET A] Please enter any numbers for the 1st set:");
		// ask for inputs, split the string using " " as the delimiter
		Scanner setA_sc = new Scanner(System.in);
		String setA_input = setA_sc.nextLine();
		String setA_string[] = setA_input.split(" ");
		List<Integer> setA = new ArrayList<Integer>();

		// convert string to int and place each int into an array
		for (int i = 0; i < setA_string.length; i++) {
			setA.add(Integer.parseInt(setA_string[i]));
		}

		// SET B
		System.out.println("[SET B] Please enter any numbers for the 2nd set:");
		Scanner setB_sc = new Scanner(System.in);
		String setB_input = setB_sc.nextLine();
		String setB_string[] = setB_input.split(" ");
		List<Integer> setB = new ArrayList<Integer>();

		// convert string to int and place each int into an array
		for (int i = 0; i < setB_string.length; i++) {
			setB.add(Integer.parseInt(setB_string[i]));
		}

		System.out.println("\nThe numbers are: ");
		System.out.println("SET A: " + setA);
		System.out.println("SET B: " + setB);

		// PIPELINING START
		List<Integer> setA_sums = new ArrayList<Integer>();
		List<Integer> setB_sums = new ArrayList<Integer>();

		int R1 = setA.get(0) + setA.get(1);
		setA_sums.add(R1);
		int S1 = setB.get(0) + setB.get(1);
		setB_sums.add(S1);
		int time_count = 2;
		
		System.out.println("\n\n[TIME 1]");
		System.out.println(setA.get(0) + " + " + setA.get(1) + " = " + R1);

		// SET A VARIABLES
		int a_sums_index = 0; 
		int a_count = 2;
		int temp_sum_a;
		// SET B VARIABLES
		int ba = 0;
		int bb = 1;
		int b_sums_index = 0; 
		int b_count = 2;
		int temp_sum_b;
		int setA_complete = 0;
		
		// THE DEADLY LOOP
		for (int i = 2; i <= setB.size(); i++) {
			System.out.println("\n\n[TIME " + time_count + "]");
			if(i == setA.size()) {
				setA_complete = 1;
			}
			
			
			if(setA_complete == 0) {
				temp_sum_a = setA_sums.get(a_sums_index) + setA.get(a_count);
				setA_sums.add(temp_sum_a);
				System.out.println(setA_sums.get(a_sums_index) + " + " + setA.get(a_count) + " = " + temp_sum_a);

				if(a_count < setA.size() - 1) {
					a_count++;
					a_sums_index++; 
				}
			}

	
				
				if (time_count == 2 && ba == 0 && bb == 1) {
					System.out.println(setB.get(ba) + " + " + setB.get(bb) + " = " + S1);
					ba = 0;
					bb = 0;
				}
				
				else {
					temp_sum_b = setB_sums.get(b_sums_index) + setB.get(b_count);
					setB_sums.add(temp_sum_b);
					System.out.println(setB_sums.get(b_sums_index) + " + " + setB.get(b_count) + " = " + temp_sum_b);
					
					if(b_count != setB.size() - 1) {
						b_sums_index++; 
						b_count++;
					}
				
				}
				


	
			time_count++;
		}
		System.out.println("\nSet A sums: " + setA_sums);
		System.out.println("Set B sums: " + setB_sums);

}
}
