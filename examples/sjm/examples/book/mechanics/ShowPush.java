package sjm.examples.book.mechanics;

import java.util.Stack;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show the value of not pushing the element a terminal 
 * matches.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowPush {
	/**
	 * Show the value of not pushing the element a terminal 
	 * matches.
	 */
	public static void main(String[] args) {

		Parser open = new Symbol('(').discard();
		Parser close = new Symbol(')').discard();
		Parser comma = new Symbol(',').discard();

		Num num = new Num();

		IParser coord = new Sequence().add(open).add(num).add(comma).add(num).add(comma).add(num).add(close);

		Assembly out = coord.bestMatch(new TokenAssembly("(23.4, 34.5, 45.6)"));

		Stack<? extends Object> s = out.getStack();
		while (!s.empty()) {
			System.out.println(s.pop());
		}
	}
}
