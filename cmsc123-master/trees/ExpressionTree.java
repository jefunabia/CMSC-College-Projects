package trees;

/*
 * Miguel Carlo Fernandez
 * Janelle Unabia
 * CMSC 123
 */

import java.util.Stack;

public class ExpressionTree {
	static Stack<String> stack = new Stack<String>();
	public static int eval(MyTreeNode root) {
		if(root.getLeft() == null && root.getRight() == null){
			stack.push(root.getData());
		}
		else{
			eval(root.getLeft());
			eval(root.getRight());
			stack.push(root.getData());
			String operator = stack.pop();
			int resRight = Integer.parseInt(stack.pop());
			int resLeft = Integer.parseInt(stack.pop());
			switch (operator){
				case "+":
					stack.push(String.valueOf((resLeft + resRight)));
					break;
				case "-":
					stack.push(String.valueOf((resLeft - resRight)));
					break;
				case "*":
					stack.push(String.valueOf((resLeft * resRight)));
					break;
				case "/":
					stack.push(String.valueOf((resLeft / resRight)));
					break;
				case "^":
					stack.push(String.valueOf(((int)Math.pow(resLeft, resRight))));
			}
		}
		return Integer.parseInt(stack.peek());
	}
}