package sjm.examples.book.mechanics;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show that <code>Parser.bestMatch()</code> matches a 
 * parser against an input as far as possible.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowBestMatch {
	/**
	 * Show that <code>Parser.bestMatch()</code> matches a 
	 * parser against an input as far as possible.
	 */
	public static void main(String[] args) {

		Alternation a = new Alternation();

		a.add(new Literal("steaming"));
		a.add(new Literal("hot"));

		Repetition adjectives = new Repetition(a);

		TokenAssembly ta = new TokenAssembly("hot hot steaming hot coffee");

		System.out.println(adjectives.bestMatch(ta));
	}
}
