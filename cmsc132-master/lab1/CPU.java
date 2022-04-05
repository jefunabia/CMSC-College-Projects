package lab1;

import java.util.Scanner;

/*
 * execute simple arithmetic operation
 * add R1, R2, and R0
 * this instruction adds the contents of source registers R1 and R2 and stores registers R1 and R2, and stores the result in destination register R0
 * this addition can be executed as follows:
 * 1. the registers R0, R1 and R2 are extracted from the IR
 * 2. the contents of R1 and R2 are passed to the ALU for addition
 * 3. the output of the ALU is transferred to R0
 */

public class CPU {
	private ALU alu;
	private IR ir;

	// CPU contains ALU, Control Unit and Registers
	public CPU(ALU alu, IR ir) {
		this.alu = alu;
		this.ir = ir;
	}

	public static void main(String[] args) {
		IR ir = new IR();
		ALU alu = new ALU();
		CPU cpu = new CPU(alu, ir);
		
		Scanner sc = new Scanner(System.in);

		cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());
		
		// get 1st input
		System.out.print("\nEnter the first number: ");
		// 1st input is set to R1
		ir.setR1(sc.nextInt());
		cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

		// get 2nd input
		System.out.print("\nEnter the second number: ");
		// 2nd input is set to R2
		ir.setR2(sc.nextInt());
		cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

		// contents of R1 and R2 are passed to ALU for addition
		// the output is set to R0
		ir.setR0(alu.addition(ir.getR1(), ir.getR2()));

		System.out.println("\nThe sum of " + ir.getR1() + " + " + ir.getR2() + " is " + ir.getR0());
		cpu.printStatus(ir.getR1(), ir.getR2(), ir.getR0());

	}

	public void printStatus(int x, int y, int z) {
		System.out.println("   [R1 value]: " + x);
		System.out.println("   [R2 value]: " + y);
		System.out.println("   [R0 value]: " + z);

	}

}
