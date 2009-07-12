package sjm.examples.book.mechanics;

import java.util.HashSet;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class shows that an alternation can, by itself, 
 * create a collection of possible matches.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAlternationSet {
	/**
	 * Show that an alternation can, by itself, create a 
	 * collection of possible matches.
	 */
	public static void main(String args[]) throws Exception {

		// assignment = Word '=' assignment | Word;

		Alternation assignment = new Alternation();

		assignment.add(new Sequence().add(new Word()).add(new Symbol('=').discard()).add(assignment));
		assignment.add(new Word());

		String s = "i = j = k = l = m";

		HashSet<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly(s));

		System.out.println(assignment.match(v));
	}
}
