package sjm.examples.book.design;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show how to use an assembly's stack.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowStack {
	/**
	 * Show how to use an assembly's stack.
	 */
	public static void main(String args[]) {

		Parser p = new Repetition(new Num());
		Assembly a = p.completeMatch(new TokenAssembly("2 4 6 8"));
		System.out.println(a);
	}
}
