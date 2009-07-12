package sjm.examples.book.tests;

import sjm.examples.book.robot.RobotParser;
import sjm.parse.tokens.TokenTester;

/**
 * Test the <code>RobotParser</code> class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class ShowRobotTest {
	/**
	 * Test the <code>RobotParser</code> class.
	 */
	public static void main(String[] args) {
		new TokenTester(RobotParser.start()).test();
	}
}
