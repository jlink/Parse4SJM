package sjm.examples.book.tests;

import sjm.examples.book.logic.LogikusParser;
import sjm.parse.tokens.TokenTester;

/**
 * Test the <code>Logikus</code> parser class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowLogikusTester {
	/**
	 * Test the <code>LogikusParser</code> parser class.
	 */
	public static void main(String[] args) {
		new TokenTester(LogikusParser.start()).test();
	}
}
