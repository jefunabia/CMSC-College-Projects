package lab3;

import java.util.LinkedList;
import java.util.Scanner;

public class CPU {
	private ALU alu;
	private IR ir;

	public CPU(ALU alu, IR ir) {
		this.alu = alu;
		this.ir = ir;
	}

	public static void main(String[] args) {
		IR ir = new IR();
		ALU alu = new ALU();
		CPU cpu = new CPU(alu, ir);
		int validInput = 0; // flag, 0 if input contains any invalid input. 1 if all inputs are valid
		
		do {
			// display requirements
			System.out.println("********************************************************");
			System.out.println("Please enter 5 decimal numbers which are greater than 15\n(Please separate each number with a space):");
			System.out.println("********************************************************");
			
			// ask for inputs, split the string using " " as the delimiter
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			String strarray[] = input.split(" ");
			int intarray[] = new int[strarray.length];

			// convert string to int and place each int into an array
			for (int i = 0; i < intarray.length; i++) {
				intarray[i] = Integer.parseInt(strarray[i]);
			}

			// checking of inputs
			for (int i = 0; i < intarray.length; i++) {
				if (intarray.length != 5 || intarray[i] < 16) {
					System.out.println("\nYou entered a number less than 16 or you lacked/exceeded 5 inputs.\nPlease try again!\n");
					validInput = 0;
					break;
				} else {	
					validInput = 1;
				}
			}
			
			// if all inputs are valid, print entered values and their corresponding binary values
			if(validInput == 1) {
				System.out.println("[Binary Values] ");
				for (int i = 0; i < intarray.length; i++) {
					System.out.println(intarray[i] + "   :     " + Integer.toBinaryString(intarray[i]));
				}
			
				/*
				// print initial registers values
				System.out.println("\n[Register Values]");
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());*/
				
				// transfer 1st input to R1
				ir.setR1(intarray[0]);
				// transfer 2nd input to R2
				ir.setR2(intarray[1]);
				// display current registers
				
				// shrink
				System.out.println("\n[Register Values]");
				// place only 4 bits with 1st input
				StringBuilder builder = new StringBuilder(Integer.toBinaryString(ir.getR1()));
				builder.deleteCharAt(0);
				// place only 4 bits with 2nd input
				StringBuilder builder2 = new StringBuilder(Integer.toBinaryString(ir.getR2()));
				builder2.deleteCharAt(0);
				// print new inputs
				System.out.println("[R1 value]: " + Integer.toBinaryString(ir.getR1()) + " --> " + builder.toString() + "\n[R2 value]: " + Integer.toBinaryString(ir.getR2()) + " --> " + builder2.toString());
				System.out.println("[R0 value]: " + Integer.toBinaryString(ir.getR0()));
				
				
				
				System.out.println("\nAddition Process: ");
				// 1st string to char array
				char[] charArray = builder.toString().toCharArray();
				/*
				for(char output : charArray) {
					System.out.print(output);
				}
				*/
				System.out.println(" ");
				// 2nd string to char array
				char[] charArray2 = builder2.toString().toCharArray();
				/*
				for(char output2 : charArray2) {
					System.out.print(output2);
				}
				*/
				// get 1st column:
				int a = Integer.parseInt(charArray[3] + "");
				int b = Integer.parseInt(charArray2[3] + "");
				
				int c = Integer.parseInt(charArray[2] + "");
				int d = Integer.parseInt(charArray2[2] + "");
				
				int e = Integer.parseInt(charArray[1] + "");
				int f = Integer.parseInt(charArray2[1] + "");
				
				int g = Integer.parseInt(charArray[0] + "");
				int h = Integer.parseInt(charArray2[0] + "");
				
				System.out.println(charArray[3] + " + " + charArray2[3] + " = " + Integer.toBinaryString(a+b));	
				System.out.println(charArray[2] + " + " + charArray2[2] + " = " + Integer.toBinaryString(c+d));	
				System.out.println(charArray[1] + " + " + charArray2[1] + " = " + Integer.toBinaryString(e+f));	
				System.out.println(charArray[0] + " + " + charArray2[0] + " = " + Integer.toBinaryString(g+h));	
				
				System.out.println("-----------------");
				// add start
				System.out.println("\nNext State:");
				//ir.setR0(alu.addition(ir.getR1(), ir.getR2()));
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

				System.out.println("\nNext State:");
				ir.setR0(alu.addition(ir.getR1(), ir.getR2()));
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
				
				// next
				ir.setR1(intarray[2]);
				ir.setR2(intarray[3]);
				System.out.println("\nNext State:");
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
				
				// add
				ir.setR0(alu.addition(ir.getR0(),ir.getR1()));
				ir.setR0(alu.addition(ir.getR0(), ir.getR2()));
				System.out.println("\nNext State:");
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
				
				// next
				ir.setR1(intarray[4]);
				//ir.setR0(alu.addition(ir.getR0(),ir.getR1()));
				System.out.println("\nNext State:");
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
				
				// add
				System.out.println("\nNext State:");
				ir.setR0(alu.addition(ir.getR0(),ir.getR1()));
				cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
			}
		} while (validInput == 0);
	}
	
	public void printStatus(int x, int y, int z) {
		System.out.println("[R1 value]: " + Integer.toBinaryString(x) + " (" + x + ")");
		System.out.println("[R2 value]: " + Integer.toBinaryString(y)+ " (" + y + ")");
		System.out.println("[R0 value]: " + Integer.toBinaryString(z)+ " (" + z + ")");

	}
	
	public void moduloStuff(int number) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		while (number > 0) {
		    stack.push( number % 10 );
		    number = number / 10;
		}

		while (!stack.isEmpty()) {
		    System.out.println(stack.pop());
		}
	}
}
