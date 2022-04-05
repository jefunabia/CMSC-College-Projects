package identifier_checker;

import java.util.Scanner;
/**
 * @author Jaii
 * Regular expression: l(l|d)*(_(l|d)+)*
 * states[row][col]
 * Given on the current state, where will each of the inputs go?
 * State	|  letter(0)	underscore(1)	digit(2)	symbol(3)
 * q0		|	q1			q4				q4			q4
 * q1		|	q1			q2				q1			q4
 * q2		|	q3			q4				q3			q4
 * q3		|	q3			q2				q3			q4
 * q4		|	q4			q4				q4			q4
 */
public class IdentifierChecker {
	public static void main(String[] args) {
		int nextState = 0;
		int currentState = 0;
		int letter = 0;
		int underscore = 1;
		int digit = 2;
		int symbol = 3;
		int[][] states = {
			{1, 4, 4, 4},	// state 0 - initial state
			{1, 2, 1, 4},	// state 1 - final state
			{3, 4, 3, 4},	// state 2 
			{3, 2, 3, 4},	// state 3 - final state
			{4, 4, 4, 4}	// state 4 - dead state
		};
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter an identifier: ");
		String str = sc.nextLine();
		
		// loop through the string until its last character
		for(int i = 0; i < str.length(); i++) {
			// check if current input/char is a letter
			if(Character.isLetter(str.charAt(i))) {
				nextState = letter;
			}
			// check if current input/char is a digit
			else if(Character.isDigit(str.charAt(i))) {
				nextState = digit;
			}
			// check if current input/char is an underscore
			else if(str.charAt(i) == '_') {
				nextState = underscore;
			}
			// check if current input/char is another symbol
			else {
				nextState = symbol;
			}
			// based on DFA/table
			currentState = states[currentState][nextState];
		}
		
		// if the the loop is finished, proceed here
		// if the current state is a final state, then it is a valid identifier
		if(currentState == 1 || currentState == 3) {
			System.out.println(str + " is a valid identifier!");
		}
		// otherwise, it is an invalid identifier
		else {
			System.out.println(str + " is an invalid identifier.");
		}
	}
}
