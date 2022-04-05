package lab1_2;

import java.util.Scanner;

//IR = part of a CPU's control unit that holds the instruction currently being executed 
/* execute simple arithmetic operation
 * add R1, R2, and R0
 * this instruction adds the contents of source registers R1 and R2 and stores registers R1 and R2, and stores the result in destination register R0
 * this addition can be executed as follows:
 * 1. the registers R0, R1 and R2 are extracted from the IR
 * 2. the contents of R1 and R2 are passed to the ALU for addition
 * 3. the output of the ALU is transferred to R0
 */

public class IR {
	// the registers R0, R1 and R2 are extracted from the IR
	private int R0; // destination register
	private int R1; // source register
	private int R2; // source register

	public IR() {
		R0 = 0;
		R1 = 0;
		R2 = 0;
	}

	public int getR0() {
		return R0;
	}

	public void setR0(int r0) {
		R0 = r0;
	}

	public int getR1() {
		return R1;
	}

	public void setR1(int r1) {
		R1 = r1;
	}

	public int getR2() {
		return R2;
	}

	public void setR2(int r2) {
		R2 = r2;
	}

	public static void main(String[] args) {
		IR ir = new IR();
		//display initial state of registers
		System.out.println("Initial state: ");
		ir.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
		
		Scanner sc = new Scanner(System.in);
		// get 1st input
		System.out.print("\nEnter the first number: ");
		// 1st input is set to R1
		ir.setR1(sc.nextInt());
		// display current state of registers
		ir.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

		// get 2nd input
		System.out.print("\nEnter the second number: ");
		// 2nd input is set to R2
		ir.setR2(sc.nextInt());
		// display current state of registers
		ir.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

		ALU alu = new ALU();
		// contents of R1 and R2 are passed to ALU for addition
		// the output is set to R0
		ir.setR0(alu.addition(ir.getR1(), ir.getR2()));

		System.out.println("\nThe sum of " + ir.getR1() + " + " + ir.getR2() + " is " + ir.getR0());
		ir.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

	}

	public void printStatus(int x, int y, int z) {
		System.out.println("   [R1 value]: " + x);
		System.out.println("   [R2 value]: " + y);
		System.out.println("   [R0 value]: " + z);

	}
}