package sjm.examples.book.mechanics;

import java.util.*;

import sjm.parse.*;
import sjm.parse.tokens.*;

/**
 * This class shows that a <code>Sequence</code> match is equivalent to a series
 * of <code>match()</code> calls.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0
 */
public class ShowSequenceManual {
	/**
	 * Show that a <code>Sequence</code> match is equivalent to a series of
	 * <code>match()</code> calls.
	 */
	public static void main(String[] args) {

		Parser hello = new Literal("Hello");
		Parser world = new Literal("world");
		Parser bang = new Symbol('!');

		Parser s = new Sequence().add(hello).add(world).add(bang);

		Set<Assembly> v = new HashSet<Assembly>();
		v.add(new TokenAssembly("Hello world!"));

		System.out.println(bang.match(world.match(hello.match(v))));

		System.out.println(s.match(v));
	}
}
