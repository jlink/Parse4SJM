package sjm.examples.book.tests;

import sjm.parse.Parser;
import sjm.parse.tokens.TokenTester;

/**
 * Test the query parser from class <code>VolumeQuery
 * </code>.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowVolumeTest {
	/**
	 * Test the query parser from class <code>VolumeQuery
	 * </code>.
	 */
	public static void main(String[] args) {
		Parser p = VolumeQuery.query();
		TokenTester tt = new TokenTester(p);
		tt.test();
	}
}
