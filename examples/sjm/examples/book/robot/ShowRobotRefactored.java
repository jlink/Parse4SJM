package sjm.examples.book.robot;

import sjm.parse.IParser;
import sjm.parse.tokens.TokenAssembly;

/**
 * Show how to use a parser class that arranges its 
 * subparsers as methods.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRobotRefactored {
	/** 
	 * Show how to use a parser class that arranges its subparsers 
	 * as methods.
	 */
	public static void main(String[] args) {
		IParser p = new RobotRefactored().command();
		String s = "place carrier at WB500_IN";
		System.out.println(p.bestMatch(new TokenAssembly(s)));
	}
}
