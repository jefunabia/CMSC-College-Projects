package trees;

/*
 * Miguel Carlo Fernandez
 * Janelle Unabia
 * CMSC 123
 */

import static org.junit.Assert.*;

public class ExpressionTreeTest {

	@org.junit.Test
	  public void testEval() throws Exception {
	    MyTreeNode five = new MyTreeNode("5", null, null);
	    MyTreeNode four = new MyTreeNode("4", null, null);
	    MyTreeNode two = new MyTreeNode("2", null, null);
	    MyTreeNode three = new MyTreeNode("3", null, null);
	    MyTreeNode times1 = new MyTreeNode("*", five, four);
	    MyTreeNode times2 = new MyTreeNode("*", two, three);
	    MyTreeNode plus = new MyTreeNode("+", times1, times2);
	    assertEquals(26, ExpressionTree.eval(plus));
	  }
	
	//(300 - 273.15) * 9/5 + 32
	@org.junit.Test
	public void testEvalWithDivide() throws Exception {
		MyTreeNode k = new MyTreeNode("300", null, null);
		MyTreeNode cons = new MyTreeNode("273", null, null);
		MyTreeNode nine = new MyTreeNode("9", null, null);
		MyTreeNode five = new MyTreeNode("5", null, null);
		MyTreeNode thirtytwo = new MyTreeNode("32", null, null);
		MyTreeNode minus = new MyTreeNode("-", k, cons);
		MyTreeNode times = new MyTreeNode("*", minus, nine);
		MyTreeNode divide = new MyTreeNode("/", times, five);
		MyTreeNode plus = new MyTreeNode("+", divide, thirtytwo);
	    assertEquals(80, ExpressionTree.eval(plus));
	  }
	
	//e = mc^2
	@org.junit.Test
	public void testEvalWithPower() throws Exception {
		MyTreeNode m = new MyTreeNode("10", null, null);
		MyTreeNode c = new MyTreeNode("30", null, null);
		MyTreeNode two = new MyTreeNode("2", null, null);
		MyTreeNode cSquared = new MyTreeNode("^", c, two);
		MyTreeNode mc = new MyTreeNode("*", m, cSquared);
	    assertEquals(9000, ExpressionTree.eval(mc));
	  }
}
