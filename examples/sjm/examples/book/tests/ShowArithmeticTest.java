package sjm.examples.book.tests;

import sjm.examples.book.arithmetic.ArithmeticParser;
import sjm.parse.tokens.TokenTester;

/**
 * Test the <code>ArithmeticParser</code> class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowArithmeticTest {
	/**
	 * Test the <code>ArithmeticParser</code> class.
	 */
	public static void main(String[] args) {
		new TokenTester(ArithmeticParser.start()).test();
	}
}
