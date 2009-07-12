package sjm.examples.book.introduction;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show how to recognize terminals in a string.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowTerminal {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {
		String s = "steaming hot coffee";
		Assembly a = new TokenAssembly(s);
		IParser p = new Word();

		while (true) {
			a = p.bestMatch(a);
			if (a == null) {
				break;
			}
			System.out.println(a);
		}

	}
}
