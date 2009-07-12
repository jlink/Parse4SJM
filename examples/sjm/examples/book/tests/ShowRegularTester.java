package sjm.examples.book.tests;

import sjm.examples.book.regular.RegularParser;
import sjm.parse.chars.CharacterTester;

/**
 * Test the <code>RegularParser</code> class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowRegularTester {
	/**
	 * Test the <code>RegularParser</code> class.
	 */
	public static void main(String[] args) {
		new CharacterTester(RegularParser.start()).test();
	}
}
