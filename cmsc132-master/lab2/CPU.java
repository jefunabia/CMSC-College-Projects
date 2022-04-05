package lab2;

import java.util.Scanner;


/**
 * @author Jaii 
 *  add x1, r0 
 *  this instruction adds the contents of the memory
 *  location x to register r0 and stores the result in r0
 *  
 *  this addition can be executed as follows: 
 *  1. the memory location x is extracted from ir and loaded into mar 
 *  2. as a result of memory read operation, the contents of x are loaded 	into mdr
 *  3. the contents of mdr are added to the contents of r0.  
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
		ALU alu = new ALU();
		IR ir = new IR();
		CPU cpu = new CPU(alu, ir);
		MAR mar = new MAR();
		MDR mdr = new MDR();
		Scanner sc = new Scanner(System.in);
		
		//display current state of registers
		System.out.println("Initial state:");
		cpu.printStatus(ir.getR0(), ir.getX());

		// get input (X)
		System.out.print("\nEnter a number: ");
		ir.setX(sc.nextInt());
		
		// input is assigned to an address -> IR
		// x (memory location) is extracted from ir and loaded into mar 		
		System.out.println("\n   MAR [X address]: " + mar.load(ir.getX()));

		// memory read operation = the contents of x are loaded 	into mdr
		System.out.println("   MDR [X value]: " + mdr.load(ir));
		
		// the contents of mdr are added to the contents of r0
		// result is stored in R0
		//System.out.println(ir.getX() + " + " + ir.getR0() + " = " + ir.getR0());
		ir.setR0(alu.addition(ir.getR0(), mdr.load(ir)));
		// display final status
		
		System.out.println("\nCurrent state:");
		cpu.printStatus(ir.getR0(), ir.getX());

	}

	public void printStatus(int x, int y) {
		System.out.println("   [R0 value]: " + x);
		System.out.println("   [X value]: " + y);

	}

}