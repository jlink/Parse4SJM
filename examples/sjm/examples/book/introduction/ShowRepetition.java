package sjm.examples.book.introduction;

import java.util.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * Show that a <code>Repetition</code> object creates 
 * multiple interpretations.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowRepetition {
	/**
	 * Just a little demo.
	 */
	public static void main(String[] args) {
		String s = "steaming hot coffee";
		Assembly a = new TokenAssembly(s);
		Parser p = new Repetition(new Word());

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(a);

		System.out.println(p.match(v));
	}
}
